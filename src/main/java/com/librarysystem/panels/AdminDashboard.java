
package com.librarysystem.panels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import com.librarysystem.Frame;
import com.librarysystem.handlers.BookHandler;
import com.librarysystem.handlers.GraphHandler;
import com.librarysystem.handlers.IssuedBooksHandler;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.GraphData;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.components.RoundedBorder;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler;

public class AdminDashboard extends MyPanel{
    
    Image people = Utilities.getImage("/textures/members.png").getImage();
    Image books = Utilities.getImage("/textures/books.png").getImage();
    XYChart booksChart;

    public AdminDashboard() {
        initComponents();
        
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                Frame.closeDropdown();
            }
        });
        
//        addBooksGraph();
    }
    
    @Override
    public void refreshItems(){
        userCountLabel.setText(Integer.toString(UserHandler.getUserCount()));
        onlineCountLabel.setText(Integer.toString(UserHandler.getOnlineCount()));
        booksCountLabel.setText(Integer.toString(BookHandler.getBookCount()));
        borrowedCountLabel.setText(Integer.toString(IssuedBooksHandler.getIssuedBooksCount()));
        updateBooksGraph();
    }
    
    private void updateBooksGraph(){
        if (booksChart == null) {
            addBooksGraph();
        }
        
        ArrayList<GraphData> graphData = GraphHandler.getGraphData();
        double[][] data = new double[4][graphData.size()];
        for (int i = 0; i < graphData.size(); i++) {
            data[0][i] = graphData.get(i).getDate().getDayOfMonth();
            data[1][i] = graphData.get(i).getTotalBooksCount();
            data[2][i] = graphData.get(i).getIssuedBooksCount();
            data[3][i] = graphData.get(i).getReturnedBooksCount();
        }
        
        booksChart.updateXYSeries("Total Books", data[0], data[1], null);
        booksChart.updateXYSeries("Issued Books", data[0], data[2], null);
        booksChart.updateXYSeries("Returned Books", data[0], data[3], null);
        booksChartPanel.revalidate();
        booksChartPanel.repaint();
    }
    
    private void addBooksGraph(){
        booksChartPanel.removeAll();
        ArrayList<GraphData> graphData = GraphHandler.getGraphData();
        double[][] data = new double[4][graphData.size()];
        for (int i = 0; i < graphData.size(); i++) {
            data[0][i] = graphData.get(i).getDate().getDayOfMonth();
            data[1][i] = graphData.get(i).getTotalBooksCount();
            data[2][i] = graphData.get(i).getIssuedBooksCount();
            data[3][i] = graphData.get(i).getReturnedBooksCount();
        }
        
        booksChart = QuickChart.getChart("Books Data this Month", "Date", "Books", "Total Books", data[0], data[1]);
        booksChart.addSeries("Issued Books", data[0], data[2]);
        booksChart.addSeries("Returned Books", data[0], data[3]);
        booksChart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        booksChart.getStyler().setBaseFont(new Font("Roboto",Font.PLAIN,12));
//        booksChart.getStyler().setLegendVisible(false);
        JPanel chartPanel = new XChartPanel<>(booksChart);
        
        booksChartPanel.add(chartPanel);
    }

    @Override
    public void resize() {
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        booksChartPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        loggedInUsersLabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        userCountLabel = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        loggedInUsersLabel3 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        onlineCountLabel = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        loggedInUsersLabel4 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        booksCountLabel = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        loggedInUsersLabel5 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        borrowedCountLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(980, 620));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        panel1.setBackground(new java.awt.Color(224, 224, 224));
        panel1.setPreferredSize(new java.awt.Dimension(930, 80));
        panel1.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dashboard");
        panel1.add(jLabel13);
        jLabel13.setBounds(30, 10, 270, 29);
        jLabel13.setForeground(PalleteColors.SIDEBAR_MAIN_COLOR);

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(145, 145, 145));
        jLabel10.setText("Dashboard / admin_dashboard");
        panel1.add(jLabel10);
        jLabel10.setBounds(30, 50, 270, 15);

        add(panel1, java.awt.BorderLayout.NORTH);

        jPanel8.setBackground(new java.awt.Color(204, 0, 204));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(224, 224, 224));
        jPanel1.setPreferredSize(new java.awt.Dimension(980, 720));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(224, 224, 224));
        jPanel2.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(224, 224, 224));
        jPanel4.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel1.add(jPanel4, java.awt.BorderLayout.LINE_END);

        jPanel23.setBackground(new java.awt.Color(224, 224, 224));
        jPanel23.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel1.add(jPanel23, java.awt.BorderLayout.SOUTH);

        jPanel24.setBackground(new java.awt.Color(224, 224, 224));
        jPanel24.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel1.add(jPanel24, java.awt.BorderLayout.LINE_START);

        booksChartPanel.setBackground(new java.awt.Color(65, 78, 101));
        booksChartPanel.setLayout(new java.awt.BorderLayout());
        jPanel1.add(booksChartPanel, java.awt.BorderLayout.CENTER);
        booksChartPanel.setBorder(new RoundedBorder(3,0));

        jPanel8.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(224, 224, 224));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 35, 5));

        jPanel6.setBackground(new java.awt.Color(65, 78, 101));
        jPanel6.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel6.setLayout(new java.awt.BorderLayout());

        loggedInUsersLabel.setBackground(new java.awt.Color(255, 255, 255));
        loggedInUsersLabel.setForeground(new java.awt.Color(0, 0, 0));
        loggedInUsersLabel.setText("icn");
        loggedInUsersLabel.setOpaque(true);
        loggedInUsersLabel.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel6.add(loggedInUsersLabel, java.awt.BorderLayout.WEST);
        Image changedColor = Utilities.changeImageColor(people, new Color(95,192,170));
        changedColor = changedColor.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        loggedInUsersLabel.setText("");
        loggedInUsersLabel.setIcon(new ImageIcon(changedColor));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(100, 75));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total Users");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(84, 25));
        jPanel10.add(jLabel2, java.awt.BorderLayout.NORTH);

        userCountLabel.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        userCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userCountLabel.setText("135");
        userCountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(userCountLabel, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel6);
        jPanel6.setBorder(new RoundedBorder(3,0));

        jPanel15.setBackground(new java.awt.Color(65, 78, 101));
        jPanel15.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel15.setLayout(new java.awt.BorderLayout());

        loggedInUsersLabel3.setBackground(new java.awt.Color(255, 255, 255));
        loggedInUsersLabel3.setForeground(new java.awt.Color(0, 0, 0));
        loggedInUsersLabel3.setText("icn");
        loggedInUsersLabel3.setOpaque(true);
        loggedInUsersLabel3.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel15.add(loggedInUsersLabel3, java.awt.BorderLayout.WEST);
        changedColor = Utilities.changeImageColor(people, new Color(170,95,192));
        changedColor = changedColor.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        loggedInUsersLabel3.setText("");
        loggedInUsersLabel3.setIcon(new ImageIcon(changedColor));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(100, 75));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Logged In Users");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setPreferredSize(new java.awt.Dimension(84, 25));
        jPanel16.add(jLabel8, java.awt.BorderLayout.NORTH);

        onlineCountLabel.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        onlineCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        onlineCountLabel.setText("12");
        onlineCountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel16.add(onlineCountLabel, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel15);
        jPanel15.setBorder(new RoundedBorder(8,0));
        jPanel15.setBorder(new RoundedBorder(3,0));

        jPanel17.setBackground(new java.awt.Color(65, 78, 101));
        jPanel17.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel17.setLayout(new java.awt.BorderLayout());

        loggedInUsersLabel4.setBackground(new java.awt.Color(255, 255, 255));
        loggedInUsersLabel4.setForeground(new java.awt.Color(0, 0, 0));
        loggedInUsersLabel4.setText("icn");
        loggedInUsersLabel4.setOpaque(true);
        loggedInUsersLabel4.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel17.add(loggedInUsersLabel4, java.awt.BorderLayout.WEST);
        changedColor = Utilities.changeImageColor(books, new Color(142,85,122));
        changedColor = changedColor.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        loggedInUsersLabel4.setText("");
        loggedInUsersLabel4.setIcon(new ImageIcon(changedColor));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setPreferredSize(new java.awt.Dimension(100, 75));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Total Books");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setPreferredSize(new java.awt.Dimension(84, 30));
        jPanel18.add(jLabel11, java.awt.BorderLayout.NORTH);

        booksCountLabel.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        booksCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        booksCountLabel.setText("264");
        booksCountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel18.add(booksCountLabel, java.awt.BorderLayout.CENTER);

        jPanel17.add(jPanel18, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel17);
        jPanel17.setBorder(new RoundedBorder(8,0));
        jPanel17.setBorder(new RoundedBorder(3,0));

        jPanel19.setBackground(new java.awt.Color(65, 78, 101));
        jPanel19.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel19.setLayout(new java.awt.BorderLayout());

        loggedInUsersLabel5.setBackground(new java.awt.Color(255, 255, 255));
        loggedInUsersLabel5.setForeground(new java.awt.Color(0, 0, 0));
        loggedInUsersLabel5.setText("icn");
        loggedInUsersLabel5.setOpaque(true);
        loggedInUsersLabel5.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel19.add(loggedInUsersLabel5, java.awt.BorderLayout.WEST);
        changedColor = Utilities.changeImageColor(books, new Color(97,163,82));
        changedColor = changedColor.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        loggedInUsersLabel5.setText("");
        loggedInUsersLabel5.setIcon(new ImageIcon(changedColor));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(100, 75));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Books Issued");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.setPreferredSize(new java.awt.Dimension(84, 25));
        jPanel20.add(jLabel14, java.awt.BorderLayout.NORTH);

        borrowedCountLabel.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        borrowedCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        borrowedCountLabel.setText("53");
        borrowedCountLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel20.add(borrowedCountLabel, java.awt.BorderLayout.CENTER);

        jPanel19.add(jPanel20, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel19);
        jPanel19.setBorder(new RoundedBorder(8,0));
        jPanel19.setBorder(new RoundedBorder(3,0));

        jPanel8.add(jPanel3, java.awt.BorderLayout.NORTH);

        add(jPanel8, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        refreshItems();
    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel booksChartPanel;
    private javax.swing.JLabel booksCountLabel;
    private javax.swing.JLabel borrowedCountLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel loggedInUsersLabel;
    private javax.swing.JLabel loggedInUsersLabel3;
    private javax.swing.JLabel loggedInUsersLabel4;
    private javax.swing.JLabel loggedInUsersLabel5;
    private javax.swing.JLabel onlineCountLabel;
    private java.awt.Panel panel1;
    private javax.swing.JLabel userCountLabel;
    // End of variables declaration//GEN-END:variables

}
