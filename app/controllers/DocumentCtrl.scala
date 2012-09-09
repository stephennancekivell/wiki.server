package controllers

import play.api._
import play.api.mvc._

import models.Document._
import models.mongoContext._

import utils.ControllerUtils._

import com.codahale.jerkson.Json
import com.mongodb.casbah.Imports._

import play.api.libs.json.JsValue
import org.slf4j.LoggerFactory
import org.slf4j.Logger


object DocumentCtrl extends Controller {
  val logger = LoggerFactory.getLogger(DocumentCtrl.getClass)

  def allDocuments = Action {
  	OkJson(Document.findAll.map(Document.serialize(_)))
  }
  
  def getDocument(id: String) = Action { request =>
    Document.dao.findOneById(new ObjectId(id)) match {
      case Some(d) => OkJson(Document.serialize(d))
      case _ => BadRequest("does not exists " + id)
    }
  }

  def newDocument = Action(parse.json) { request =>
    saveDocument(request.body, (title, body) =>
      Document(title=title, body=body)
    )
  }
  
  def updateDocument(id: String) = Action(parse.json) { request =>
    saveDocument(request.body, (title, body) =>
      Document(new ObjectId(id), title=title, body=body)
    )    
  }
  
  def saveDocument(js: JsValue, newDocument: (String, String) => Document) = {
    val title = (js \ "title").asOpt[String]
    val body = (js \ "body").asOpt[String]
    
    (title, body) match {
      case (Some(title), Some(body)) => {
        val doc = newDocument(title, body)
        Document save doc
        OkJson(Document.serialize(doc))
      }
      case _ => BadRequest("invalid json data")
    }
  }
}