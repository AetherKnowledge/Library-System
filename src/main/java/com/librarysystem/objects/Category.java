
package com.librarysystem.objects;

import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Timestamp;

public class Category implements Serializable{

    private final String parentCategory;
    private final String parentCategoryID;
    private final String category;
    private final String categoryID;
    private final String categoryName;
    private final Timestamp lastUpdated;
    private int booksInTotal = 0;
    
    public Category(String parentCategory, String parentCategoryID, String category, String categoryName, Timestamp lastUpdated){
        this.lastUpdated = lastUpdated;
        this.category = category;
        this.categoryName = categoryName;
        this.parentCategoryID = parentCategoryID;
        
        if (parentCategory.length() == 4 && !parentCategory.equals("None")) {
            StringBuilder sb = new StringBuilder();
            sb.append(parentCategory);
            sb.insert(3, ".");
            this.parentCategory = sb.toString();
        }
        else this.parentCategory = parentCategory;
        
        String id = parentCategory;
        id += category;
        if(id.length() < 3){
            for (int i = id.length(); i < 3; i++) {
                id += "0";
            }
        }
        
        if (id.length() > 3 && !id.contains(".")) {
            StringBuilder sb = new StringBuilder();
            sb.append(id);
            sb.insert(3, ".");
            id = sb.toString();
        }
        
        if (parentCategory.equals("None")) {
            categoryID = category + "00";
        }
        else categoryID = id;
        
    }
    
    public Category(String parentCategory, String parentCategoryID, String category, String categoryID, String categoryName,int booksInTotal, Timestamp lastUpdated){
        this.parentCategory = parentCategory;
        this.parentCategoryID = parentCategoryID;
        this.category = category;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.booksInTotal = booksInTotal;
        this.lastUpdated = lastUpdated;
        
    }
    
    @Override
    public String toString(){
        return categoryID + " - " + categoryName;
    }
    
    
    public String getDataString(){
        String toReturn = "";
        toReturn += "\nParent Category Num : " + parentCategory + "\n";
        toReturn += "Parent Category ID : " + parentCategoryID + "\n";
        toReturn += "Category Num : " + category + "\n";
        toReturn += "Category ID : " + categoryID + "\n";
        toReturn += "Category Name : " + categoryName + "\n";
        toReturn += "Books in Total : " + Integer.toString(booksInTotal) + "\n";
        
        return toReturn;
    }
        
    public String getParentCategoryID() {
        return parentCategoryID;
    }    
    
    public void addBook(){
        booksInTotal++;
    }
    
    public void removeBook(){
        if (booksInTotal-- < 0) {
            JOptionPane.showMessageDialog(new JFrame(), "Total books cannot be negative");
            return;
        }
        
        booksInTotal--;
    }
    
    public int getBooksInTotal() {
        return booksInTotal;
    }
    
    public String getCategory() {
        return category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
}
