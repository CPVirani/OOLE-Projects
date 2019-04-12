name := "sketch3.0"

version := "0.1"


scalaVersion := "2.12.3"

publishArtifact in Test := true
libraryDependencies ++= Seq("org.eclipse.jdt" % "org.eclipse.jdt.core" % "3.10.0")
libraryDependencies ++= Seq("junit" % "junit" % "4.12" % "test")
// https://mvnrepository.com/artifact/org.eclipse.jdt/org.eclipse.jdt.annotation
libraryDependencies ++= Seq("org.eclipse.jdt" % "org.eclipse.jdt.annotation" % "2.0.0")
// https://mvnrepository.com/artifact/org.eclipse.jdt.core.compiler/ecj
libraryDependencies ++= Seq("org.eclipse.jdt.core.compiler" % "ecj" % "4.4.2")
// https://mvnrepository.com/artifact/org.apache.commons/commons-io
libraryDependencies ++= Seq("org.apache.commons" % "commons-io" % "1.3.2")
// https://mvnrepository.com/artifact/org.eclipse.core/org.eclipse.core.runtime
libraryDependencies ++= Seq("org.eclipse.core" % "org.eclipse.core.runtime" % "3.7.0")
// https://mvnrepository.com/artifact/org.osgi/org.osgi.core
libraryDependencies ++= Seq("org.osgi" % "org.osgi.core" % "4.3.0" % "provided")
// https://mvnrepository.com/artifact/javassist/javassist
libraryDependencies ++=Seq("javassist" % "javassist" % "3.12.1.GA")


javaSource in Compile := baseDirectory.value / "src"
javaSource in Test := baseDirectory.value / "test"
mainClass in (Compile,run) := Some("MutationLauncher.TestCases")
