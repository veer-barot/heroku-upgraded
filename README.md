# Heroku + JAX-RS + EmbeddedTomcat Sample

This sample is a combination of things, some drafted from the Heroku documentation,
and others drafted from a collection of stalwart JAX-RS adventurers.

The Sources:

- The core tutorial: https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat
- Some added context: http://www.sortedset.com/embedded-tomcat-jersey/
- The path that helped make sense of my error messages: http://jersey.576304.n2.nabble.com/Getting-MessageBodyWriter-not-found-for-media-type-application-json-Error-td7581682.html
- The Jersey docs that actually answered how to register features: https://jersey.github.io/documentation/latest/deployment.html

This provides a very basic "Fake Data" API (no POST/PUT/DELETE) that is wildly
inconsistent. The API is consumed by a simple AngularJS table generator.

The biggest tricks I dealt with on my conversion from Glassfish/Wildfly to
Embedded Tomcat were:
- @ApplicationPath is not used, instead the path is determined when adding servlets
- The JSON MessageBodyWriter is not added by Jersey by default, and must be registered
