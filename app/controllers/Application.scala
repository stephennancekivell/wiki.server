package controllers

import play.api._
import play.api.mvc._
import models.User._
import models.Document._
import com.codahale.jerkson.Json
import play.api.libs.json.JsValue

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def allUsers() = Action {
    OkJson(User.findAll)
  }

  def allDocuments = Action {
  	OkJson(Document.findAll)
  }

  def OkJson(json: Any) = Ok(Json.generate(json)).as("application/json")
}