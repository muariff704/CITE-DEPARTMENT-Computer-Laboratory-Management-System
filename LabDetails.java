/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package citefilestorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LabDetails extends javax.swing.JFrame {
    private String selectedBuilding;
private String selectedLaboratory;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LabDetails.class.getName());

    /**
     * Creates new form LabDetails
     */
    public LabDetails() {
        initComponents();
         
    }
   public LabDetails(String building, String laboratory) {
    this();

    selectedBuilding = building;
    selectedLaboratory = laboratory;

    lblBuildingLabValue.setText(
            building + " - " + laboratory
    );

    loadReportedProblems();
     loadComputerSummary();
   }
   
   
private void loadComputerSummary() {

    String sql = "SELECT "
            + "COALESCE((SELECT total_computers "
            + "FROM laboratory_inventory "
            + "WHERE building = ? AND laboratory = ?), 0) "
            + "AS total_computers, "
            + "(SELECT COUNT(DISTINCT computer_number) "
            + "FROM problem_reports "
            + "WHERE building = ? AND laboratory = ? "
            + "AND status IN ('Pending', 'In Progress') "
            + "AND computer_number IS NOT NULL "
            + "AND TRIM(computer_number) <> '') "
            + "AS defective_computers";

    try (Connection conn = DBConnection.getConnection()) {

        if (conn == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not connect to the database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, selectedBuilding);
            pst.setString(2, selectedLaboratory);
            pst.setString(3, selectedBuilding);
            pst.setString(4, selectedLaboratory);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    int totalComputers =
                            rs.getInt("total_computers");

                    int defectiveComputers =
                            rs.getInt("defective_computers");

                    lblTotalComputersValue.setText(
                            String.valueOf(totalComputers)
                    );

                    lblDefectiveComputersValue.setText(
                            String.valueOf(defectiveComputers)
                    );
                }
            }
        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(
                this,
                "Failed to load computer summary:\n"
                        + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

   
private void loadReportedProblems() {

    DefaultTableModel model =
            (DefaultTableModel) tblReportedProblems.getModel();

    // Clear the table before loading the selected lab's tickets
    model.setRowCount(0);

    String sql = "SELECT ticket_number, computer_number, "
            + "problem_category, description, status "
            + "FROM problem_reports "
            + "WHERE building = ? AND laboratory = ? "
            + "ORDER BY reported_at DESC";

    try (Connection conn = DBConnection.getConnection()) {

        if (conn == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not connect to the database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, selectedBuilding);
            pst.setString(2, selectedLaboratory);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    String ticketNumber =
                            rs.getString("ticket_number");

                    String computerNumber =
                            rs.getString("computer_number");

                    String category =
                            rs.getString("problem_category");

                    String description =
                            rs.getString("description");

                    String status =
                            rs.getString("status");

                    String problem = category == null
                            ? ""
                            : category;

                    if (description != null
                            && !description.trim().isEmpty()) {
                        problem += ": " + description;
                    }

                    model.addRow(new Object[]{
                        ticketNumber,
                        computerNumber == null
                                ? "—" : computerNumber,
                        problem,
                        status
                    });
                }
            }
        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(
                this,
                "Failed to load reported problems:\n"
                        + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        spReportedProblems = new javax.swing.JPanel();
        lblTitile = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblLabDetailsTitle = new javax.swing.JLabel();
        lblBuildingLabValue = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTotalComputersTitle = new javax.swing.JLabel();
        lblTotalComputersValue = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblDefectiveComputersTitle = new javax.swing.JLabel();
        lblDefectiveComputersValue = new javax.swing.JLabel();
        lblTitile8 = new javax.swing.JLabel();
        tblReportedProblems1 = new javax.swing.JScrollPane();
        tblReportedProblems = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnDashBoard = new javax.swing.JButton();
        btnManageTickets = new javax.swing.JButton();
        btnBuildingsLabs = new javax.swing.JButton();
        btnAccounts1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(53, 89, 71));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel3.setBackground(new java.awt.Color(248, 255, 242));
        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 720));

        spReportedProblems.setBackground(new java.awt.Color(52, 86, 69));

        lblTitile.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitile.setForeground(new java.awt.Color(255, 255, 255));
        lblTitile.setText("Buildings & Labs");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("manage CITE Buildings and computer laboratories");

        lblLabDetailsTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblLabDetailsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblLabDetailsTitle.setText("LABORATORY DETAILES");

        lblBuildingLabValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBuildingLabValue.setForeground(new java.awt.Color(255, 255, 255));
        lblBuildingLabValue.setText("MS BUILDING CL 1");

        lblTotalComputersTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalComputersTitle.setText("TOTAL COMPUTERS");

        lblTotalComputersValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalComputersValue.setText("35");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotalComputersTitle))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lblTotalComputersValue)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalComputersTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalComputersValue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDefectiveComputersTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDefectiveComputersTitle.setText("DEFECT COMPUTERS");

        lblDefectiveComputersValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDefectiveComputersValue.setText("3");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDefectiveComputersTitle))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(lblDefectiveComputersValue)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDefectiveComputersTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDefectiveComputersValue)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        lblTitile8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitile8.setForeground(new java.awt.Color(255, 255, 255));
        lblTitile8.setText("REPORTED PROBLEMS");

        tblReportedProblems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ticket ID", "Computer", "Problem", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblReportedProblems1.setViewportView(tblReportedProblems);

        btnBack.setBackground(new java.awt.Color(163, 204, 163));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setText("Back to Buildings & Labs");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout spReportedProblemsLayout = new javax.swing.GroupLayout(spReportedProblems);
        spReportedProblems.setLayout(spReportedProblemsLayout);
        spReportedProblemsLayout.setHorizontalGroup(
            spReportedProblemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spReportedProblemsLayout.createSequentialGroup()
                .addGap(391, 391, 391)
                .addComponent(lblTitile)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(spReportedProblemsLayout.createSequentialGroup()
                .addContainerGap(295, Short.MAX_VALUE)
                .addGroup(spReportedProblemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, spReportedProblemsLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(356, 356, 356))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, spReportedProblemsLayout.createSequentialGroup()
                        .addGroup(spReportedProblemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblBuildingLabValue)
                            .addGroup(spReportedProblemsLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblLabDetailsTitle)
                            .addComponent(lblTitile8)
                            .addComponent(tblReportedProblems1)
                            .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(257, 257, 257))))
        );
        spReportedProblemsLayout.setVerticalGroup(
            spReportedProblemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spReportedProblemsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(24, 24, 24)
                .addComponent(lblLabDetailsTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBuildingLabValue)
                .addGap(18, 18, 18)
                .addGroup(spReportedProblemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(lblTitile8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblReportedProblems1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(163, 204, 163));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagess/573-5731936_coc-college-logo-vector-phinma-cagayan-de-oro-removebg-preview.png"))); // NOI18N
        jLabel8.setText("jLabel8");

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(53, 89, 71));
        jLabel2.setText("CITE LAB MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(847, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(spReportedProblems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(spReportedProblems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("MENU BAR");

        btnDashBoard.setBackground(new java.awt.Color(163, 204, 163));
        btnDashBoard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDashBoard.setText("DASHBOARD");
        btnDashBoard.setBorderPainted(false);
        btnDashBoard.addActionListener(this::btnDashBoardActionPerformed);

        btnManageTickets.setBackground(new java.awt.Color(163, 204, 163));
        btnManageTickets.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnManageTickets.setText("MANAGE TICKETS");
        btnManageTickets.addActionListener(this::btnManageTicketsActionPerformed);

        btnBuildingsLabs.setBackground(new java.awt.Color(163, 204, 163));
        btnBuildingsLabs.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuildingsLabs.setText("BUILDING & LABS");
        btnBuildingsLabs.addActionListener(this::btnBuildingsLabsActionPerformed);

        btnAccounts1.setBackground(new java.awt.Color(163, 204, 163));
        btnAccounts1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAccounts1.setText("ACCOUNTS");
        btnAccounts1.addActionListener(this::btnAccounts1ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnBuildingsLabs, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnManageTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAccounts1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1052, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuildingsLabs, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnManageTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAccounts1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1284, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashBoardActionPerformed
        AdminDashboard board = new AdminDashboard();
        board.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDashBoardActionPerformed

    private void btnManageTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageTicketsActionPerformed
        ManageTickets tickets = new ManageTickets();
        tickets.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageTicketsActionPerformed

    private void btnBuildingsLabsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuildingsLabsActionPerformed
        BuildingsandLab labs = new BuildingsandLab();
        labs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBuildingsLabsActionPerformed

    private void btnAccounts1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccounts1ActionPerformed
        Accounts acc = new Accounts();
        acc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAccounts1ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
 BuildingsandLab labs = new BuildingsandLab();
    labs.setLocationRelativeTo(null);
    labs.setVisible(true);
    this.dispose();

     
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LabDetails().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccounts1;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBuildingsLabs;
    private javax.swing.JButton btnDashBoard;
    private javax.swing.JButton btnManageTickets;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblBuildingLabValue;
    private javax.swing.JLabel lblDefectiveComputersTitle;
    private javax.swing.JLabel lblDefectiveComputersValue;
    private javax.swing.JLabel lblLabDetailsTitle;
    private javax.swing.JLabel lblTitile;
    private javax.swing.JLabel lblTitile8;
    private javax.swing.JLabel lblTotalComputersTitle;
    private javax.swing.JLabel lblTotalComputersValue;
    private javax.swing.JPanel spReportedProblems;
    private javax.swing.JTable tblReportedProblems;
    private javax.swing.JScrollPane tblReportedProblems1;
    // End of variables declaration//GEN-END:variables
}
