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

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class DBUtils {

    public static Connection getConnection() throws URISyntaxException, SQLException {

        if (System.getenv("LOCALHOST").equals("TRUE")) {            
            String dbUrl = "jdbc:derby://localhost:1527/sample";
            return DriverManager.getConnection(dbUrl, "app", "app");
        } else {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String driver = "postgresql";
            String extra = "?sslmode=require";
            String dbUrl = "jdbc:" + driver + "://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + extra;
            return DriverManager.getConnection(dbUrl, username, password);
        }

        
    }
}
