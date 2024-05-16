
package com.librarysystem.handlers;

import com.librarysystem.objects.GraphData;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.sql.Statement;

public class GraphHandler implements ObjectHandler{
    
    private static Connection con;
    private static boolean graphUpdating = false;
    private static boolean hasStarted = false;
    private static ArrayList<GraphData> graphData = new ArrayList<>();
    
    @Override
    public void startManager(Connection connection){
        con = connection;
        graphData = OfflineHandler.loadGraphDataOffline();
        if (graphData.isEmpty()) loadGraphDataOnline();
        hasStarted = true;
    }
    
    public static void loadGraphDataOnline(){
        graphUpdating = true;
        graphData.removeAll(graphData);
        
        try {
            String query = "SELECT * FROM graph";
            ResultSet resultSet = con.createStatement().executeQuery(query);
            
            while (resultSet.next()) {
                graphData.add(getGraphData(resultSet));
            }
        } catch (SQLException | IOException ex) {
            System.out.println("Loading Graph online failed");
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Loading Graph online complete");
        graphUpdating = false;
    }
    
    public static boolean hasGraphUpdated(){
        boolean hasGraphUpdated = false;
        graphUpdating = true;
        try {
            for (int i = 0; i < graphData.size(); i++) {
                GraphData data = graphData.get(i);
                String query = "SELECT lastUpdated FROM graph WHERE date = ?";
                PreparedStatement pst = con.prepareCall(query);
                pst.setDate(1, Date.valueOf(data.getDate()));
                ResultSet resultSet = pst.executeQuery();

                while (resultSet.next()) {
                    if (!data.getLastUpdated().equals(resultSet.getTimestamp("lastUpdated").toLocalDateTime())) {
                        
                        String query2 = "SELECT * FROM graph WHERE date = ?";
                        PreparedStatement pst2 = con.prepareCall(query2);
                        pst2.setDate(1, Date.valueOf(data.getDate()));
                        ResultSet getGraphDataResult = pst2.executeQuery();
                        getGraphDataResult.next();

                        graphData.set(i, getGraphData(getGraphDataResult));
                        System.out.println("Graph on "+ data.getDate().toString() + " Changed");
                        hasGraphUpdated = true;
                    }
                }
            }
            
            String query = "SELECT COUNT(*) AS count FROM graph";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            
            int count = rs.getInt("count");
            if (count != graphData.size()) {
                hasGraphUpdated = true;
                graphData.removeAll(graphData);
                String getAll = "SELECT * FROM graph";
                ResultSet resultSet = con.createStatement().executeQuery(getAll);

                while (resultSet.next()) {
                    graphData.add(getGraphData(resultSet));
                }
            }
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (hasGraphUpdated) {
            System.out.println("Graph data updated");
        }
        
        graphUpdating = false;
        return hasGraphUpdated;
    }
    
    private static GraphData getGraphData(ResultSet rs) throws SQLException, IOException{
        LocalDate date = rs.getDate("date").toLocalDate();
        int totalBooks = rs.getInt("totalBooks");
        int totalUsers = rs.getInt("totalUsers");
        int issuedBooks = rs.getInt("issuedBooks");
        int returnedBooks = rs.getInt("returnedBooks");
        LocalDateTime lastUpdated = rs.getTimestamp("lastUpdated").toLocalDateTime();
        
        return new GraphData(date, totalBooks, totalUsers, issuedBooks, returnedBooks,lastUpdated);
    }

    public static boolean isGraphUpdating() {
        return graphUpdating;
    }

    public static ArrayList<GraphData> getGraphData() {
        return graphData;
    }
    
    public static boolean hasStarted() {
        return hasStarted;
    }
    
}
