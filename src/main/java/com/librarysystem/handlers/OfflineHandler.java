
package com.librarysystem.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.librarysystem.LibrarySystem;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.Category;
import com.librarysystem.objects.GraphData;
import com.librarysystem.objects.IssuedBook;
import com.librarysystem.objects.User;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OfflineHandler {

    private static final String CATEGORIES_PATH = "Data/categories.sav";
    private static final String BOOKS_PATH = "Data/books.sav";
    private static final String ISSUED_BOOKS_PATH = "Data/borrowed_books.sav";
    private static final String USERS_PATH = "Data/users.sav";
    private static final String GRAPH_DATA_PATH = "Data/graph_data.sav";
    private static final Path path = Paths.get("Data");
    
    public static void saveUsersOffline(ArrayList<User> USERS) {
        System.out.println("Saving Users Offline...");
        try {
            File file = new File(USERS_PATH);
            if (!file.exists()) {
                System.out.println("File does not exist");
                file.createNewFile();
            }
            FileOutputStream fileOutStream = new FileOutputStream(file);
            ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
            objOutStream.writeObject(USERS);
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Saving Users Offline Failed");
        }
    }
    
    public static void saveGraphDataOffline(ArrayList<GraphData> DATA) {
        System.out.println("Saving Graph Data Offline...");
        try {
            File file = new File(GRAPH_DATA_PATH);
            if (!file.exists()) {
                if (!Files.exists(path)) Files.createDirectories(path);
                System.out.println("File does not exist");
                file.createNewFile();
            }
            FileOutputStream fileOutStream = new FileOutputStream(file);
            ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
            objOutStream.writeObject(DATA);
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Saving Graph Data Offline Failed");
        }
    }

    public static void saveIssuedBooksOffline(ArrayList<IssuedBook> ISSUED_BOOKS) {
        System.out.println("Saving Issued Books Offline...");
        try {
            File file = new File(ISSUED_BOOKS_PATH);
            if (!file.exists()) {
                if (!Files.exists(path)) Files.createDirectories(path);
                System.out.println("File does not exist");
                file.createNewFile();
            }
            FileOutputStream fileOutStream = new FileOutputStream(file);
            ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
            objOutStream.writeObject(ISSUED_BOOKS);
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Saving Issued Books Offline Failed");
        }
    }

    public static void saveCategoriesOffline(ArrayList<Category> CATEGORIES) {
        System.out.println("Saving Categories Offline...");
        try {
            File file = new File(CATEGORIES_PATH);
            if (!file.exists()) {
                if (!Files.exists(path)) Files.createDirectories(path);
                System.out.println("File does not exist");
                file.createNewFile();
            }
            FileOutputStream fileOutStream = new FileOutputStream(file);
            ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
            objOutStream.writeObject(CATEGORIES);
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Saving Categories Offline Failed");
        }
    }
    
    public static void saveBooksOffline(ArrayList<Book> BOOKS) {
        System.out.println("Saving Books Offline...");
        try {
            File file = new File(BOOKS_PATH);
            if (!file.exists()) {
                if (!Files.exists(path)) Files.createDirectories(path);
                System.out.println("File does not exist");
                file.createNewFile();
            }
            FileOutputStream fileOutStream = new FileOutputStream(file);
            ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
            objOutStream.writeObject(BOOKS);
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Saving Books Offline Failed");
        }
    }
    
    public static ArrayList<User> loadUsersOffline() {
        System.out.println("Loading Users Offline...");
        try (FileInputStream fileInputStream = new FileInputStream(USERS_PATH)) {
            ObjectInputStream objOutStream = new ObjectInputStream(fileInputStream);
            ArrayList<User> cacheUsers = new ArrayList<>();
            if (objOutStream.readObject() instanceof ArrayList<?> objects) {
                for (Object object : objects) {
                    if (object instanceof User user) {
                        cacheUsers.add(user);
                    } else {
                        throw new IOException("Invalid User Object");
                    }
                }
            } 
            else {
                throw new IOException("Invalid User Cache");
            }
            System.out.println("Loading Users Cache Success.");
            return cacheUsers;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Users cache not found.");
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Users Cache Corrupted.");
        }
        return new ArrayList<>();
    }

    public static ArrayList<Book> loadBooksOffline() {
        System.out.println("Loading Books Offline...");
        try (FileInputStream fileInputStream = new FileInputStream(BOOKS_PATH)) {
            ObjectInputStream objOutStream = new ObjectInputStream(fileInputStream);
            ArrayList<Book> cacheBooks = new ArrayList<>();
            if (objOutStream.readObject() instanceof ArrayList<?> objects) {
                for (Object object : objects) {
                    if (object instanceof Book book) {
                        cacheBooks.add(book);
                    } else {
                        throw new IOException("Invalid Book Object");
                    }
                }
            } else {
                throw new IOException("Invalid Book Cache");
            }
            System.out.println("Loading Books Cache Success.");
            return cacheBooks;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Books cache not found.");
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Books Cache Corrupted.");
        }
        return new ArrayList<>();
    }

    public static ArrayList<IssuedBook> loadIssuedBooksOffline() {
        System.out.println("Loading Issued Books Offline...");
        try (FileInputStream fileInputStream = new FileInputStream(ISSUED_BOOKS_PATH)) {
            ObjectInputStream objOutStream = new ObjectInputStream(fileInputStream);
            ArrayList<IssuedBook> cacheBooks = new ArrayList<>();
            if (objOutStream.readObject() instanceof ArrayList<?> objects) {
                for (Object object : objects) {
                    if (object instanceof IssuedBook book) {
                        cacheBooks.add(book);
                    } else {
                        throw new IOException("Invalid Book Object");
                    }
                }
            } else {
                throw new IOException("Invalid Issued Book Cache");
            }
            System.out.println("Loading Issued Books Cache Success.");
            return cacheBooks;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Issued Books cache not found.");
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Issued Books Cache Corrupted.");
        }
        return new ArrayList<>();
    }

    public static ArrayList<Category> loadCategoriesOffline() {
        System.out.println("Loading Categories Offline...");
        try (FileInputStream fileInputStream = new FileInputStream(CATEGORIES_PATH)) {
            ObjectInputStream objOutStream = new ObjectInputStream(fileInputStream);
            ArrayList<Category> cacheCategories = new ArrayList<>();
            if (objOutStream.readObject() instanceof ArrayList<?> objects) {
                for (Object obj : objects) {
                    if (obj instanceof Category category) {
                        cacheCategories.add(category);
                    } else {
                        throw new IOException("Invalid Category Object");
                    }
                }
            } else {
                throw new IOException("Invalid Category Cache");
            }
            System.out.println("Saving Categories Cache Success.");
            return cacheCategories;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Categories cache not found.");
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error loading Categories : " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Categories Cache Corrupted.");
        }
        return new ArrayList<>();
    }
    
    public static ArrayList<GraphData> loadGraphDataOffline() {
        System.out.println("Loading Graph Data Offline...");
        try (FileInputStream fileInputStream = new FileInputStream(GRAPH_DATA_PATH)) {
            ObjectInputStream objOutStream = new ObjectInputStream(fileInputStream);
            ArrayList<GraphData> cacheUsers = new ArrayList<>();
            if (objOutStream.readObject() instanceof ArrayList<?> objects) {
                for (Object object : objects) {
                    if (object instanceof GraphData data) {
                        cacheUsers.add(data);
                    } else {
                        throw new IOException("Invalid Graph Data Object");
                    }
                }
            } 
            else {
                throw new IOException("Invalid Graph Data Cache");
            }
            System.out.println("Loading Graph Data Cache Success.");
            return cacheUsers;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Graph Data cache not found.");
        } catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Graph Data Cache Corrupted.");
        }
        return new ArrayList<>();
    }
                
}
