package com.agileengine

import java.io.{BufferedWriter, File, FileWriter}

import com.typesafe.scalalogging.LazyLogging
import org.jsoup.Jsoup
import org.jsoup.nodes.{Attribute, Element}

import scala.collection.JavaConversions._
import scala.util.{Failure, Success, Try}

object Exam extends LazyLogging {

  type Result = (Element, Element)

  private[this] val Utf8Charset = "UTF-8"
  private[this] val TargetId    = "make-everything-ok-button"

  private[this] val OutputFile  = "output.xml"
  private[this] val writer      = new  BufferedWriter(new FileWriter(OutputFile))

  def main(args: Array[String]): Unit = {

    process(args) match {
      case Success((original, diff)) =>
        logger.info(s"Process has finished without errors ")
        writer.append(s"Original Element: ${original.toString}")
        writer.append(s"Diff Element:     ${diff.toString}")
        writer.close()
      case Failure(exception) => logger.error("Something went wrong", exception)
    }
  }

  private[this] def process(args: Array[String]): Try[Result] = Try {
    val originalHtml = args(0)
    val diffHtml     = args(1)

    val originalNode = findElementBy(originalHtml)
    val diffNode     = findElementBy(diffHtml, originalNode).orNull

    originalNode -> diffNode
  }

  private[this] def findElementBy(html: String): Element =
    Jsoup.parse(new File(html), Utf8Charset).getElementById(TargetId)

  private[this] def findElementBy(html: String, element: Element): Option[Element] = Jsoup.parse(new File(html), Utf8Charset)
    .getAllElements.toList.find { diffElement =>
      element.attributes()
        .asList()
        .exists(exists(diffElement))
    }

  private[this] def exists(diffElement: Element)(attribute: Attribute): Boolean =
    attribute.getValue ==
      diffElement.attr(attribute.getKey)

}
