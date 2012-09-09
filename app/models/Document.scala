package models.Document

import play.api.Play.current
import java.util.Date
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import models.mongoContext._
import play.api.libs.json.JsValue

case class Document(
	id: ObjectId = new ObjectId,
	title: String,
	body: String
)

object Document extends ModelCompanion[Document, ObjectId] {
	val collection = mongoCollection("documents")
	val dao = new SalatDAO[Document, ObjectId](collection = collection) {}
	
	def serialize(d: Document) = {
	  Map("id" -> d.id.toString,
	      "title" -> d.title,
	      "body" -> d.body)
	}
}