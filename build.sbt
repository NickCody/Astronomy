name := "Astronomy"

version := "0.1"

scalaVersion := "2.13.1"

resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies  ++= Seq(
  // Last stable release
  "org.scalanlp" %% "breeze" % "1.0",

  // Native libraries are not included by default. add this if you want them
  // Native libraries greatly improve performance, but increase jar sizes.
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "1.0",

  // The visualization library is distributed separately as well.
  // It depends on LGPL code
  "org.scalanlp"        %%  "breeze-viz"      % "1.0",
  "org.jogamp.gluegen"  %   "gluegen-rt-main" % "2.3.2",
  "org.jogamp.jogl"     %   "jogl-all-main"   % "2.3.2",
  "org.jogamp.gluegen"  %   "gluegen"         % "2.3.2",
  "com.aparapi"         %   "aparapi"         % "1.10.1-SNAPSHOT",
  "org.dyn4j"           %   "dyn4j"           % "3.4.0"
)



