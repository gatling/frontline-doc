enablePlugins(GatlingPlugin, FrontLinePlugin)

// If you want to package simulations from the 'it' scope instead
// inConfig(IntegrationTest)(_root_.io.gatling.frontline.sbt.FrontLinePlugin.frontlineSettings(IntegrationTest))

scalaVersion := "{param1}"
scalacOptions := Seq("-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation", "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

val gatlingVersion = "{param2}"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % "test"
// only required if you intend to use the gatling-sbt plugin
libraryDependencies += "io.gatling" % "gatling-test-framework" % gatlingVersion % "test"
