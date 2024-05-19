
package com.librarysystem.panels.books;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.panels.MyPanel;
import java.util.Collections;
import javax.swing.DefaultComboBoxModel;

public class MyBookList extends MyPanel{
    
    private Image bookImg = Utilities.getImage("/textures/books.png").getImage();
    private Image aot = Utilities.getImage("/textures/aot.png").getImage();
    private Image clearIcon = Utilities.getImage("/textures/clear.png").getImage();
    private Image refreshIcon = Utilities.getImage("/textures/refresh.png").getImage();
    private Image categoryImg = Utilities.getImage("/textures/categories.png").getImage();
    private Image sortImg = Utilities.getImage("/textures/sort.png").getImage();
    private String selectedCategory = "None";
    private final ArrayList<BookPanel> currentBookList = new ArrayList<>();
    private final ArrayList<BookPanel> shownBooks = new ArrayList<>();
    private final ArrayList<String> categories = new ArrayList<>();
    
    public MyBookList() {
        initComponents();
        
        bookList.removeAll();
        bookList.revalidate();
        bookList.repaint();
        
        tagSearchCB.addActionListener((ActionEvent e) -> {
            if (tagSearchCB.getSelectedItem() == null) {
                return;
            }
            
            selectedCategory = (String)tagSearchCB.getSelectedItem();
            search();
            clearButton.requestFocus();
        });
        
        bookNameSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {}
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }
        });
        
        tagSearchCB.addItem("None");
        tagSearchCB.setSelectedItem("None");
    }
    
    public void addBooks(){
        List<BookPanel> bookPanels = Utilities.getUserBooksPanel(UserHandler.getCurrentUser());
        boolean changedSize = currentBookList.size()!= bookPanels.size();
        
        for (BookPanel bookPanel : bookPanels) {
            currentBookList.add(bookPanel);
        }
        
        currentBookList.sort(Comparator.comparing(BookPanel::getTitle));
        
        if (changedSize) {
            bookList.removeAll();
            bookList.revalidate();
            bookList.repaint();

            for (BookPanel bookPanel : currentBookList) {
                bookList.add(bookPanel);
                shownBooks.add(bookPanel);
                if (!categories.contains(bookPanel.getCategory().toString())) {
                    categories.add(bookPanel.getCategory().toString());
                }
                Collections.sort(categories);
            }
        }
        
        bookList.revalidate();
        bookList.repaint();

    }
    
    public void addTags(){
        tagSearchCB.removeAllItems();
        tagSearchCB.addItem("None");
        DefaultComboBoxModel model = (DefaultComboBoxModel) tagSearchCB.getModel();
        for (String category : categories) {
            model.addElement(category);
        }
    }
    
    @Override
    public void resize(){
        
        jPanel12.setLocation(getWidth() - 460, jPanel12.getY());
        sortLabel.setLocation(getWidth() - 210, sortLabel.getY());
        sortBy.setLocation(getWidth() - 190, sortBy.getY());
        clearButton.setLocation(getWidth() - 115, clearButton.getY());
        
        topPanel.setSize(getWidth() - 70, 50);
        bottomPanel.setSize(getWidth() - 70, getHeight() - 190);   
        
        int bookListWidth = bookList.getWidth() - 15;
        int bookListLayers = (shownBooks.size() * 285)/bookListWidth + 1;
        int bookListHeight = (bookListLayers * (270 + 35));
        
        if (!currentBookList.isEmpty()) {
            bookListHeight = (bookListLayers * (currentBookList.get(0).getPreferredSize().height + 35));
        }
        
        bookList.setPreferredSize(new Dimension(bookListWidth, bookListHeight));
        this.revalidate();
        this.repaint();
    }    
    
    public void removeBooks(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) tagSearchCB.getModel();
        model.removeAllElements();
        
        currentBookList.clear();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        topPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        bookNameSearch = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        sortBy = new javax.swing.JComboBox<>();
        sortLabel = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        tagsLabel = new javax.swing.JLabel();
        tagSearchCB = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JScrollPane();
        bookList = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(224, 224, 224));
        setForeground(new java.awt.Color(145, 145, 145));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        setLayout(null);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("My Book List");
        add(jLabel13);
        jLabel13.setBounds(30, 10, 140, 29);
        jLabel13.setForeground(PalleteColors.SIDEBAR_MAIN_COLOR);

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setForeground(new java.awt.Color(255, 255, 255));
        topPanel.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(145, 145, 145));
        jLabel2.setText("icn");
        jLabel2.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));
        bookImg = Utilities.changeImageColor(bookImg, PalleteColors.DROPDOWN);
        bookImg = bookImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel2.setText("");
        jLabel2.setIcon(new ImageIcon(bookImg));

        bookNameSearch.setBackground(new java.awt.Color(255, 255, 255));
        bookNameSearch.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        bookNameSearch.setForeground(new java.awt.Color(145, 145, 145));
        bookNameSearch.setText("Search Book");
        bookNameSearch.setName("bookNameSearch"); // NOI18N
        bookNameSearch.setPreferredSize(new java.awt.Dimension(120, 21));
        bookNameSearch.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                bookNameSearchInputMethodTextChanged(evt);
            }
        });
        bookNameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookNameSearchActionPerformed(evt);
            }
        });
        jPanel3.add(bookNameSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, -1, -1));
        bookNameSearch.setBorder(BorderFactory.createEmptyBorder());

        bookNameSearch.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (bookNameSearch.getText().equals("Search Book")) {
                    bookNameSearch.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (bookNameSearch.getText().isEmpty()) {
                    bookNameSearch.setText("Search Book");
                }
            }
        });

        topPanel.add(jPanel3);
        jPanel3.setBounds(150, 10, 160, 30);
        jPanel3.setBorder(new RoundedBorder(8,1));

        refreshButton.setBackground(new java.awt.Color(255, 255, 255));
        refreshButton.setForeground(new java.awt.Color(0, 0, 0));
        refreshButton.setText("R");
        refreshButton.setPreferredSize(new java.awt.Dimension(30, 30));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        topPanel.add(refreshButton);
        refreshButton.setBounds(10, 10, 30, 30);
        refreshButton.setBorder(new RoundedBorder(8,1));

        refreshButton.setText("");
        refreshIcon = Utilities.changeImageColor(refreshIcon, PalleteColors.DROPDOWN);
        refreshIcon = refreshIcon.getScaledInstance(refreshButton.getPreferredSize().width, refreshButton.getPreferredSize().height, Image.SCALE_SMOOTH);
        refreshButton.setIcon(new ImageIcon(refreshIcon));

        clearButton.setBackground(new java.awt.Color(255, 255, 255));
        clearButton.setForeground(new java.awt.Color(0, 0, 0));
        clearButton.setText("R");
        clearButton.setPreferredSize(new java.awt.Dimension(30, 30));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        topPanel.add(clearButton);
        clearButton.setBounds(865, 10, 30, 30);
        clearButton.setBorder(new RoundedBorder(8,1));

        clearButton.setText("");
        clearIcon = Utilities.changeImageColor(clearIcon, PalleteColors.DROPDOWN);
        clearIcon = clearIcon.getScaledInstance(clearButton.getPreferredSize().width, clearButton.getPreferredSize().height, Image.SCALE_SMOOTH);
        clearButton.setIcon(new ImageIcon(clearIcon));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Books Data Table");
        topPanel.add(jLabel1);
        jLabel1.setBounds(50, 20, 93, 10);

        sortBy.setBackground(new java.awt.Color(255, 255, 255));
        sortBy.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        sortBy.setForeground(new java.awt.Color(145, 145, 145));
        sortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A-Z", "Z-A" }));
        sortBy.setPreferredSize(new java.awt.Dimension(120, 21));
        sortBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortByItemStateChanged(evt);
            }
        });
        sortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByActionPerformed(evt);
            }
        });
        topPanel.add(sortBy);
        sortBy.setBounds(790, 13, 60, 21);

        sortLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        sortLabel.setForeground(new java.awt.Color(145, 145, 145));
        sortLabel.setText("icn");
        sortLabel.setPreferredSize(new java.awt.Dimension(15, 15));
        topPanel.add(sortLabel);
        sortLabel.setBounds(770, 16, 15, 15);
        sortImg = Utilities.changeImageColor(sortImg, PalleteColors.DROPDOWN);
        sortImg = sortImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        sortLabel.setText("");
        sortLabel.setIcon(new ImageIcon(sortImg));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tagsLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tagsLabel.setForeground(new java.awt.Color(145, 145, 145));
        tagsLabel.setText("icn");
        tagsLabel.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel12.add(tagsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));
        categoryImg = Utilities.changeImageColor(categoryImg, PalleteColors.DROPDOWN);
        categoryImg = categoryImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        tagsLabel.setText("");
        tagsLabel.setIcon(new ImageIcon(categoryImg));

        tagSearchCB.setBackground(new java.awt.Color(255, 255, 255));
        tagSearchCB.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tagSearchCB.setForeground(new java.awt.Color(145, 145, 145));
        tagSearchCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tagSearchCB.setPreferredSize(new java.awt.Dimension(120, 21));
        jPanel12.add(tagSearchCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 4, 200, -1));

        topPanel.add(jPanel12);
        jPanel12.setBounds(520, 10, 240, 30);
        jPanel12.setBorder(new RoundedBorder(8,1));

        add(topPanel);
        topPanel.setBounds(30, 90, 910, 50);
        topPanel.setBorder(new RoundedBorder(8,0));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(145, 145, 145));
        jLabel10.setText("Books / my_book_list");
        add(jLabel10);
        jLabel10.setBounds(30, 50, 910, 15);

        bottomPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomPanel.setDoubleBuffered(true);

        bookList.setBackground(new java.awt.Color(255, 255, 255));
        bookList.setPreferredSize(new java.awt.Dimension(865, 1000));
        bookList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 25));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setPreferredSize(new java.awt.Dimension(260, 270));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(65, 78, 101));
        jPanel6.setPreferredSize(new java.awt.Dimension(15, 18));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel4.setBackground(new java.awt.Color(52, 63, 82));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Action Drama Suspense");
        jLabel4.setPreferredSize(new java.awt.Dimension(16, 13));
        jPanel6.add(jLabel4, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.SOUTH);
        jPanel6.setBackground(PalleteColors.DROPDOWN_PRESSED);

        jPanel7.setBackground(new java.awt.Color(65, 78, 101));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Attack on Titan");
        jPanel7.add(jLabel3, java.awt.BorderLayout.CENTER);
        jPanel7.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel8.setBackground(new java.awt.Color(255, 255, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(65, 78, 101));
        jPanel10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(130, 170));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("by Hajime Isayama");
        jPanel10.add(jLabel6);

        jPanel8.add(jPanel10, java.awt.BorderLayout.WEST);
        jPanel8.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);

        jPanel11.setBackground(new java.awt.Color(65, 78, 101));

        jButton1.setBackground(new java.awt.Color(95, 192, 170));
        jButton1.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add to My List");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 18));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton1);
        jButton1.setBackground(PalleteColors.BUTTON_PRESSED_TEXT);

        jPanel8.add(jPanel11, java.awt.BorderLayout.CENTER);
        jPanel8.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);

        jPanel4.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel9.setPreferredSize(new java.awt.Dimension(130, 100));
        jPanel9.setLayout(new java.awt.BorderLayout());

        icon.setText("icon");
        jPanel9.add(icon, java.awt.BorderLayout.WEST);
        aot = aot.getScaledInstance(130, 175, Image.SCALE_SMOOTH);
        icon.setText("");
        icon.setIcon(new ImageIcon(aot));

        jPanel4.add(jPanel9, java.awt.BorderLayout.WEST);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(52, 63, 82));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Centuries ago, mankind was slaughtered to near extinction by monstrous humanoid creatures called Titans, forcing humans to hide in fear behind enormous concentric walls. What makes these giants truly terrifying is that their taste for human flesh is not born out of hunger but what appears to be out of pleasure. To ensure their survival, the remnants of humanity began living within defensive barriers, resulting in one hundred years without a single titan encounter. However, that fragile calm is soon shattered when a colossal Titan manages to breach the supposedly impregnable outer wall, reigniting the fight for survival against the man-eating abominations.\n\nAfter witnessing a horrific personal loss at the hands of the invading creatures, Eren Yeager dedicates his life to their eradication by enlisting into the Survey Corps, an elite military unit that combats the merciless humanoids outside the protection of the walls. Eren, his adopted sister Mikasa Ackerman, and his childhood friend Armin Arlert join the brutal war against the Titans and race to discover a way of defeating them before the last walls are breached.\n");
        jScrollPane1.setViewportView(jTextArea1);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        bookList.add(jPanel4);
        jPanel4.setBorder(new RoundedBorder(8,0));
        jPanel4.setBackground(PalleteColors.DROPDOWN_PRESSED);

        bottomPanel.setViewportView(bookList);
        bookList.setBorder(BorderFactory.createEmptyBorder());
        bottomPanel.setBorder(new RoundedBorder(8,0));

        add(bottomPanel);
        bottomPanel.setBounds(30, 150, 910, 390);
    }// </editor-fold>//GEN-END:initComponents
    

    private void search(){
        shownBooks.clear();
        
        for (BookPanel bookPanel : currentBookList) {
            boolean searchContainsTitle = bookPanel.getTitle().toLowerCase().contains(bookNameSearch.getText().toLowerCase());
            boolean searchContainsTags = false;
            
            if (bookPanel.getCategory().toString().equals(selectedCategory) || selectedCategory.equals("None")) {
                searchContainsTags = true;
            }
            
            if (bookNameSearch.getText().equals("") || bookNameSearch.getText().equals("Search Book")) {
                searchContainsTitle = true;
            }
            
            if (searchContainsTitle && searchContainsTags) {
                shownBooks.add(bookPanel);
            }
        }
        
        sortBooks();
        resize();
    }
    
    @Override
    public void refreshItems(){
        removeBooks();
        addBooks();
        addTags();
        search();
        resize();
    }
    
    private void clear(){
        bookNameSearch.setText("Search Book");
        categories.clear();
        selectedCategory = null;
        sortBy.setSelectedItem("A-Z");
        
        removeBooks();
        addBooks();
        search();
        resize();
    }
    
    private void sortBooks(){
        bookList.removeAll();
        bookList.revalidate();
        bookList.repaint();
        
        if (sortBy.getSelectedItem().equals("A-Z")) {
            shownBooks.sort(Comparator.comparing(BookPanel::getTitle));
        }
        else if (sortBy.getSelectedItem().equals("Z-A")) {
            shownBooks.sort(Comparator.comparing(BookPanel::getTitle).reversed());
        }
        
        for (BookPanel panel : shownBooks) {
            bookList.add(panel);
        }
        
        bookList.revalidate();
        bookList.repaint();

    }
	
	private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clear();
    }//GEN-LAST:event_clearButtonActionPerformed
	
	private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refreshItems();
    }//GEN-LAST:event_refreshButtonActionPerformed
	
    private void sortByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortByItemStateChanged
        
    }//GEN-LAST:event_sortByItemStateChanged
	
	private String oldSort = "A-Z";
	private void sortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortByActionPerformed
        if (oldSort.equals(sortBy.getSelectedItem())) {
            return;
        }
        
        sortBooks();
        oldSort = (String) sortBy.getSelectedItem();
    }//GEN-LAST:event_sortByActionPerformed
	
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        refreshItems();
        resize();
    }//GEN-LAST:event_formComponentShown

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden

    }//GEN-LAST:event_formComponentHidden
    
	private void bookNameSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookNameSearchActionPerformed
    }//GEN-LAST:event_bookNameSearchActionPerformed
	
	private void bookNameSearchInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_bookNameSearchInputMethodTextChanged
    }//GEN-LAST:event_bookNameSearchInputMethodTextChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
//        resize();
    }//GEN-LAST:event_formComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookList;
    private javax.swing.JTextField bookNameSearch;
    private javax.swing.JScrollPane bottomPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel icon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JComboBox<String> sortBy;
    private javax.swing.JLabel sortLabel;
    private javax.swing.JComboBox<String> tagSearchCB;
    private javax.swing.JLabel tagsLabel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

}
