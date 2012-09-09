package controllers

import play.api._
import play.api.mvc._

import models.User._
import models.Document._
import models.mongoContext._

import com.codahale.jerkson.Json
import com.mongodb.casbah.Imports._

import play.api.libs.json.JsValue
import org.slf4j.LoggerFactory
import org.slf4j.Logger


object Application extends Controller {
  val logger = LoggerFactory.getLogger(Application.getClass)

  def allUsers() = Action {
    OkJson(User.findAll)
  }

  def allDocuments = Action {
  	OkJson(Document.findAll)
  }

  def newDocument = Action(parse.json) { request =>
    Document save Document(request.body)
    
  	Ok("done")
  }

  def OkJson(json: Any) = Ok(Json.generate(json)).as("application/json")
}