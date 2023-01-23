package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 

public class DAOehdokas {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public DAOehdokas(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertEhdokas(Ehdokas ehdokas) throws SQLException {
        String sql = "INSERT INTO ehdokas (puolue, nimi, ika) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, ehdokas.getPuolue());
        statement.setString(2, ehdokas.getNimi());
        statement.setFloat(3, ehdokas.getIka());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Ehdokas> listAllEhdokas() throws SQLException {
        List<Ehdokas> listEhdokas = new ArrayList<>();
         
        String sql = "SELECT * FROM ehdokas";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("ehdokas_id");
            String puolue = resultSet.getString("puolue");
            String nimi = resultSet.getString("nimi");
            float ika = resultSet.getFloat("ika");
             
            Ehdokas ehdokas = new Ehdokas(id, puolue, nimi, ika);
            listEhdokas.add(ehdokas);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listEhdokas;
    }
     
    public boolean deleteEhdokas(Ehdokas ehdokas) throws SQLException {
        String sql = "DELETE FROM ehdokas where ehdokas_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, ehdokas.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateEhdokas(Ehdokas ehdokas) throws SQLException {
        String sql = "UPDATE ehdokas SET puolue = ?, nimi = ?, ika = ?";
        sql += " WHERE ehdokas_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, ehdokas.getPuolue());
        statement.setString(2, ehdokas.getNimi());
        statement.setFloat(3, ehdokas.getIka());
        statement.setInt(4, ehdokas.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    public Ehdokas getEhdokas(int id) throws SQLException {
        Ehdokas ehdokas = null;
        String sql = "SELECT * FROM ehdokas WHERE ehdokas_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String puolue = resultSet.getString("puolue");
            String nimi = resultSet.getString("nimi");
            float ika = resultSet.getFloat("ika");
             
            ehdokas = new Ehdokas(id, puolue, nimi, ika);
        }
         
        resultSet.close();
        statement.close();
         
        return ehdokas;
    }
}
