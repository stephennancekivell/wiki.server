package utils

import play.api._
import play.api.mvc._

import com.codahale.jerkson.Json

object ControllerUtils extends Controller {

	def OkJson(json: Any) = Ok(Json.generate(json)).as("application/json")

}