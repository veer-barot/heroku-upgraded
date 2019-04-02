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

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
@Path("/dbsample")
public class DBSampleREST {

    @GET
    @Produces("application/json")
    public List<Thing> getAll() throws URISyntaxException, SQLException {
        List<Thing> things = new ArrayList<Thing>();
            Connection conn = DBUtils.getConnection();
            String result = "";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sample");
            while (rs.next()) {
                Thing t = new Thing();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                things.add(t);
            }
            return things;   
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public List<Thing> getOne(@PathParam("id") int id) throws URISyntaxException, SQLException {
        List<Thing> things = new ArrayList<Thing>();
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM sample WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Thing t = new Thing();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                things.add(t);
            }
            return things;   
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void editOne(@PathParam("id") int id, Thing thing) throws URISyntaxException, SQLException {
        Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE sample SET id = ?, name = ?, WHERE id = ?");
            pstmt.setInt(1, thing.getId());
            pstmt.setString(2, thing.getName());
            pstmt.setInt(3, id);
            pstmt.executeUpdate();         
    }
    
    @POST
    @Path("{id}")
    @Consumes("application/json")
    public void addOne(Thing thing) throws URISyntaxException, SQLException {
        Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO sample (id, name) VALUES (?, ?)");
            pstmt.setInt(1, thing.getId());
            pstmt.setString(2, thing.getName());
            pstmt.executeUpdate();         
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public void deleteOne(@PathParam("id") int id) throws URISyntaxException, SQLException {
        Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM sample WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();         
    }
}
