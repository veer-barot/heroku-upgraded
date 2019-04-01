/*
 * The MIT License
 *
 * Copyright 2019 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package herokusample;

import java.io.File;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * The application that starts the Embedded Tomcat Engine.
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // Initialize Tomcat on a given Port
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8888";
        }
        tomcat.setPort(Integer.valueOf(webPort));

        // Setup the basic Web Context at / based on webappDirLocation above
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

        // Add the ApplicationConfig as a new Resource
        ResourceConfig resCfg = new ResourceConfig(new ApplicationConfig().getClasses());
        // Register the Jackson Feature to allow JSON transcoding
        resCfg.register(org.glassfish.jersey.jackson.JacksonFeature.class);
        // Setup the RESTful Application at /api
        ServletContainer srvCtr = new ServletContainer(resCfg);
        tomcat.addServlet(ctx, "jersey-container-servlet", srvCtr);
        ctx.addServletMapping("/api/*", "jersey-container-servlet");
        
        System.out.println("Listening on http://localhost:" + webPort);

        // Start the server, and let it listen
        tomcat.start();
        tomcat.getServer().await();
    }
}
