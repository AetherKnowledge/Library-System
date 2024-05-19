
package com.librarysystem.handlers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.librarysystem.objects.Category;

public final class CategoryHandler implements ObjectHandler{

    private static Connection con;
    private static boolean categoryUpdating = false;
    private static boolean hasStarted = false;
    private static ArrayList<Category> categoryList = new ArrayList<>();
    
    @Override
    public void startManager(Connection connection){
        con = connection;
        categoryList = OfflineHandler.loadCategoriesOffline();
        if (categoryList.isEmpty()) CategoryHandler.loadCategoriesOnline();
        if (categoryList.isEmpty()) CategoryHandler.addStandardCategories();
        hasStarted = true;
    }
    
    public static void addCategory(Category cat){
        categoryUpdating = true;
        categoryList.add(cat);
        
        String parentCategory, parentCategoryID, category, categoryID, categoryName;
        int booksInTotal;
        
        parentCategory = cat.getParentCategory();
        parentCategoryID = cat.getParentCategoryID();
        category = cat.getCategory();
        categoryID = cat.getCategoryID();
        categoryName = cat.getCategoryName();
        booksInTotal = cat.getBooksInTotal();
        
        String queryRegister = "INSERT into category(parentCategory, parentCategoryID, category, categoryID, categoryName, booksInTotal, lastUpdated) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = con.prepareStatement(queryRegister);
            st.setString(1, parentCategory);
            st.setString(2, parentCategoryID);
            st.setString(3, category);
            st.setString(4, categoryID);
            st.setString(5, categoryName);
            st.setInt(6, booksInTotal);
            st.setTimestamp(7, cat.getLastUpdated());
            st.executeUpdate();
            
            OfflineHandler.saveCategoriesOffline(categoryList);
            categoryList.sort(Comparator.comparing(Category::getCategoryID));
            System.out.println("Category " + cat.getCategoryName()+ " added successfully");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Category","Error",0);
        }
        
        categoryUpdating = false;
    }
    
    public static void updateCategory(Category category){
        categoryUpdating = true;
        
        String updateBooksInTotal = "UPDATE category SET booksInTotal = ? " + "WHERE categoryID = '" + category.getCategoryID() + "'";
        try {
            PreparedStatement st = con.prepareStatement(updateBooksInTotal);
            st.setInt(1, category.getBooksInTotal());
            st.executeUpdate();
            
            categoryList.stream().filter(cat -> cat.getCategoryID().equals(category.getCategoryID())).collect(Collectors.toList()).forEach(cat -> cat = category);
            OfflineHandler.saveCategoriesOffline(categoryList);
            System.out.println("Category " + category.getCategoryName()+ " updated");
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        categoryUpdating = false;
    }
    
    public static void removeCategory(Category cat){
        categoryUpdating = true;

        String queryRegister = "DELETE FROM category WHERE categoryID = '" + cat.getCategoryID()+ "'";
        try {
            PreparedStatement st = con.prepareStatement(queryRegister);
            st.executeUpdate();
            
            categoryList.remove(cat);
            OfflineHandler.saveCategoriesOffline(categoryList);
            
            System.out.println("Category " + cat.getCategoryName()+ " removed");
        } catch (SQLException ex) {
            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Category","Error",0);
        }
        
        categoryUpdating = false;
    }
    
    private static void loadCategoriesOnline(){
        categoryUpdating = true;
        
        try {
            String query = "SELECT * FROM category";
            ResultSet resultSet = con.createStatement().executeQuery(query);
            
            
            while (resultSet.next()) {
                categoryList.add(getCategory(resultSet));
            }
            
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CategoryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        categoryUpdating = false;
    }
    
    private static Category getCategory(ResultSet rs) throws SQLException, IOException{
        String parentCategory = rs.getString("parentCategory");
        String parentCategoryID = rs.getString("parentCategoryID");
        String category = rs.getString("category");
        String categoryID = rs.getString("categoryID");
        String categoryName = rs.getString("categoryName");
        int booksInTotal = rs.getInt("booksInTotal");
        Timestamp timestamp = rs.getTimestamp("lastUpdated");

        Category newCat = new Category(parentCategory,parentCategoryID,category,categoryID,categoryName,booksInTotal,timestamp);
        for (int i = 0; i < booksInTotal; i++) {
            newCat.addBook();
        }
        
        return newCat;
    }

    public static boolean hasCategoriesUpdated(){
        boolean hasCategoriesUpdated = false;
        categoryUpdating = true;
        try {
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                String query = "SELECT lastUpdated FROM category WHERE categoryID = '" + category.getCategoryID() + "'";
                ResultSet resultSet = con.createStatement().executeQuery(query);
                
                while (resultSet.next()) {
                    if (!category.getLastUpdated().equals(resultSet.getTimestamp("lastUpdated"))) {
                        String getCategory = "SELECT * FROM category WHERE categoryID = '" + category.getCategoryID() + "'";
                        ResultSet getCategoryResult = con.createStatement().executeQuery(getCategory);
                        getCategoryResult.next();
                        
                        System.out.println(category.getLastUpdated());

                        categoryList.set(i, getCategory(getCategoryResult));
                        System.out.println("Category "+ getCategoryResult.getString("categoryName") + " Changed");
                        hasCategoriesUpdated = true;
                    }
                }
            }
            
            String query = "SELECT categoryID FROM category";
            ResultSet resultSet = con.createStatement().executeQuery(query);
            
            ArrayList<String> cacheCategoryIDs = new ArrayList<>();
            for (Category category : categoryList) {
                cacheCategoryIDs.add(category.getCategoryID());
            }
            
            ArrayList<String> serverCategoryIDs = new ArrayList<>();
            while (resultSet.next()){
                serverCategoryIDs.add(resultSet.getString("categoryID"));
            }
            
            if (categoryList.removeIf(category -> !serverCategoryIDs.contains(category.getCategoryID()))) {
                hasCategoriesUpdated = true;
                System.out.println("Category Removed");
            }
            
            if (serverCategoryIDs.size() > categoryList.size()) {
                for (String id : serverCategoryIDs) {
                    if (!cacheCategoryIDs.contains(id)) {
                        String getCategory = "SELECT * FROM category WHERE categoryID = '" + id + "'";
                        ResultSet getCategoryResult = con.createStatement().executeQuery(getCategory);
                        getCategoryResult.next();
                        
                        categoryList.add(getCategory(getCategoryResult));
                        hasCategoriesUpdated = true;
                        System.out.println("Book "+ getCategoryResult.getString("categoryName") + " Added");
                    }
                }
            }
            
        } 
        catch (SQLException | IOException ex) {
            Logger.getLogger(CategoryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        categoryUpdating = false;
        if (hasCategoriesUpdated) {
            System.out.println("Categories updated");
        }
        return hasCategoriesUpdated;
    }
    
    public static Category getCategory(String id){
        for (Category category : categoryList) {
            if (category.getCategoryID().equals(id)) {
                return category;
            }
        }
        return null;
    }
    
    public static void addStandardCategories() {
        categoryList.add(new Category("None", "None", "0", "Computer science, information & general works", Timestamp.valueOf(LocalDateTime.now())));
        categoryList.add(new Category("None", "None", "1", "Philosophy & Psychology", Timestamp.valueOf(LocalDateTime.now())));
        categoryList.add(new Category("None", "None", "2", "Religion", Timestamp.valueOf(LocalDateTime.now())));
        categoryList.add(new Category("None", "None", "3", "Social Sciences", Timestamp.valueOf(LocalDateTime.now())));
        categoryList.add(new Category("None", "None", "4", "Language", Timestamp.valueOf(LocalDateTime.now())));
        categoryList.add(new Category("None", "None", "5", "Science", Timestamp.valueOf(LocalDateTime.now())));
        categoryList.add(new Category("None", "None", "6", "Technology", Timestamp.valueOf(LocalDateTime.now())));
        categoryList.add(new Category("None", "None", "7", "Arts & recreation", Timestamp.valueOf(LocalDateTime.now())));
        categoryList.add(new Category("None", "None", "8", "Literature", Timestamp.valueOf(LocalDateTime.now())));
        categoryList.add(new Category("None", "None", "9", "History & geography", Timestamp.valueOf(LocalDateTime.now())));
        for (Category category : categoryList) {
            addCategory(category);
        }
    }

    public static boolean isCategoryUpdating() {
        return categoryUpdating;
    }

    public static ArrayList<Category> getCategoryList() {
        return categoryList;
    }
    
    public static boolean hasStarted() {
        return hasStarted;
    }
    
}
