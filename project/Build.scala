import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "wiki.server"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
    	"se.radley" %% "play-plugins-salat" % "1.0.9"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      routesImport += "se.radley.plugin.salat.Binders._",
  		templatesImport += "org.bson.types.ObjectId",

  		// for lift
      resolvers += "OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
    )
}