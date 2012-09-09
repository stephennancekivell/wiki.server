package controllers

import play.api.mvc._
import utils.ControllerUtils._
import models.User._

object UserCtrl extends Controller {
  
  def allUsers() = Action {
    OkJson(User.findAll)
  }

}