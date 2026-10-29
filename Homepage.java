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
public class Homepage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Homepage.class.getName());

    /**
     * Creates new form Homepage
     */
    public Homepage() {
        initComponents();
        loadTotalFiles();
        loadRecentFiles();
         loadTicketSummary();
          loadRecentTickets();
         setSize(1280, 780);
    setMinimumSize(new java.awt.Dimension(1000, 650));
    setLocationRelativeTo(null);
    }
    
    private void loadRecentTickets() {

    String sql = "SELECT ticket_number, "
            + "CONCAT(building, ' - ', laboratory) AS lab_location, "
            + "status "
            + "FROM problem_reports "
            + "WHERE user_id = ? "
            + "ORDER BY reported_at DESC "
            + "LIMIT 5";

    javax.swing.table.DefaultTableModel model =
            new javax.swing.table.DefaultTableModel(
                    new Object[]{"Ticket No.", "Laboratory", "Status"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

    try (Connection conn = DBConnection.getConnection()) {

        if (conn == null) {
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, Session.userId);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("ticket_number"),
                        rs.getString("lab_location"),
                        rs.getString("status")
                    });
                }

                tblRecentTickets.setModel(model);
            }
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Failed to load recent tickets: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private void loadTicketSummary() {

    String sql = "SELECT "
            + "COUNT(CASE WHEN LOWER(TRIM(status)) = 'pending' "
            + "THEN 1 END) AS pending_count, "
            + "COUNT(CASE WHEN LOWER(TRIM(status)) "
            + "IN ('in progress', 'in_progress', 'in-progress') "
            + "THEN 1 END) AS progress_count, "
            + "COUNT(CASE WHEN LOWER(TRIM(status)) = 'resolved' "
            + "THEN 1 END) AS resolved_count "
            + "FROM problem_reports WHERE user_id = ?";

    try (Connection conn = DBConnection.getConnection()) {

        if (conn == null) {
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, Session.userId);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    lblPendingCount.setText(
                            String.valueOf(rs.getInt("pending_count"))
                    );

                    lblInProgressCount.setText(
                            String.valueOf(rs.getInt("progress_count"))
                    );

                    lblResolvedCount.setText(
                            String.valueOf(rs.getInt("resolved_count"))
                    );
                }
            }
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Failed to load ticket summary: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    
private void loadRecentFiles() {

    String sql = "SELECT file_name, file_type, file_size "
               + "FROM files "
               + "WHERE user_id = ? "
               + "ORDER BY upload_date DESC "
               + "LIMIT 5";

    javax.swing.table.DefaultTableModel model =
        new javax.swing.table.DefaultTableModel(
            new Object[]{"File Name", "Type", "Size"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

    try (Connection conn = DBConnection.getConnection()) {

        if (conn == null) {
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, Session.userId);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("file_name"),
                        rs.getString("file_type"),
                        rs.getString("file_size")
                    });
                }

                tblRecentFiles.setModel(model);
            }
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Failed to load recent files:\n" + ex.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

    
private void loadTotalFiles() {
    String sql = "SELECT COUNT(*) AS total "
               + "FROM files WHERE user_id = ?";

    try (Connection conn = DBConnection.getConnection()) {

        if (conn == null) {
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, Session.userId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    lblTotalFilesValue.setText(
                        String.valueOf(rs.getInt("total"))
                    );
                }
            }
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Failed to load your files: " + ex.getMessage(),
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblLabTicketsTitle = new javax.swing.JPanel();
        pnlTotalFilesCard = new javax.swing.JPanel();
        lblTotalFilesValue = new javax.swing.JLabel();
        lblTotalFilesTitle = new javax.swing.JLabel();
        lblMyFilesTitle = new javax.swing.JLabel();
        lblLabTicketsSubtitle = new javax.swing.JLabel();
        btnUploadNewFile = new javax.swing.JButton();
        lblRecentTickets = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRecentFiles = new javax.swing.JTable();
        pnlPendingCard = new javax.swing.JPanel();
        lblPendingCount = new javax.swing.JLabel();
        lblPendingTitle = new javax.swing.JLabel();
        lblMyFilesTitle2 = new javax.swing.JLabel();
        lblMyFilesSubtitle1 = new javax.swing.JLabel();
        pnlInProgressCard = new javax.swing.JPanel();
        lblInProgressCount = new javax.swing.JLabel();
        lblInProgressTitle = new javax.swing.JLabel();
        pnlResolvedCard = new javax.swing.JPanel();
        lblResolvedCount = new javax.swing.JLabel();
        lblResolvedTitle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRecentTickets = new javax.swing.JTable();
        lblMyFilesTitle3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnMyAccount1 = new javax.swing.JButton();
        btnMyAccount = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnMyFiles = new javax.swing.JButton();
        btnUploadFile = new javax.swing.JButton();
        btnMyTickets = new javax.swing.JButton();
        btnReportProblem2 = new javax.swing.JButton();

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 204, 0));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel2.setBackground(new java.awt.Color(53, 89, 71));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 780));

        jPanel3.setBackground(new java.awt.Color(248, 255, 242));
        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 720));

        lblLabTicketsTitle.setBackground(new java.awt.Color(52, 86, 69));

        lblTotalFilesValue.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTotalFilesValue.setText("0");

        lblTotalFilesTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalFilesTitle.setText("TOTAL FILES");

        javax.swing.GroupLayout pnlTotalFilesCardLayout = new javax.swing.GroupLayout(pnlTotalFilesCard);
        pnlTotalFilesCard.setLayout(pnlTotalFilesCardLayout);
        pnlTotalFilesCardLayout.setHorizontalGroup(
            pnlTotalFilesCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalFilesCardLayout.createSequentialGroup()
                .addGroup(pnlTotalFilesCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTotalFilesCardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotalFilesTitle))
                    .addGroup(pnlTotalFilesCardLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblTotalFilesValue)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTotalFilesCardLayout.setVerticalGroup(
            pnlTotalFilesCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalFilesCardLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblTotalFilesTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalFilesValue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMyFilesTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMyFilesTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblMyFilesTitle.setText("LABORATORY TICKETS");

        lblLabTicketsSubtitle.setForeground(new java.awt.Color(255, 255, 255));
        lblLabTicketsSubtitle.setText("Track your reproted laboratory problems");

        btnUploadNewFile.setBackground(new java.awt.Color(163, 204, 163));
        btnUploadNewFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUploadNewFile.setText("+ Upload new file");
        btnUploadNewFile.addActionListener(this::btnUploadNewFileActionPerformed);

        lblRecentTickets.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblRecentTickets.setForeground(new java.awt.Color(255, 255, 255));
        lblRecentTickets.setText("RECENTLY UPLOADED");

        tblRecentFiles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "File Name", "Type", "Size"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRecentFiles);

        lblPendingCount.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblPendingCount.setText("0");

        lblPendingTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPendingTitle.setText("PENDING");

        javax.swing.GroupLayout pnlPendingCardLayout = new javax.swing.GroupLayout(pnlPendingCard);
        pnlPendingCard.setLayout(pnlPendingCardLayout);
        pnlPendingCardLayout.setHorizontalGroup(
            pnlPendingCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendingCardLayout.createSequentialGroup()
                .addGroup(pnlPendingCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPendingCardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPendingTitle))
                    .addGroup(pnlPendingCardLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblPendingCount)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        pnlPendingCardLayout.setVerticalGroup(
            pnlPendingCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendingCardLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblPendingTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPendingCount)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        lblMyFilesTitle2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMyFilesTitle2.setForeground(new java.awt.Color(255, 255, 255));
        lblMyFilesTitle2.setText("MY FILES");

        lblMyFilesSubtitle1.setForeground(new java.awt.Color(255, 255, 255));
        lblMyFilesSubtitle1.setText("Your perosnal file workspace");

        lblInProgressCount.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblInProgressCount.setText("0");

        lblInProgressTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblInProgressTitle.setText("IN PROGRESS");

        javax.swing.GroupLayout pnlInProgressCardLayout = new javax.swing.GroupLayout(pnlInProgressCard);
        pnlInProgressCard.setLayout(pnlInProgressCardLayout);
        pnlInProgressCardLayout.setHorizontalGroup(
            pnlInProgressCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInProgressCardLayout.createSequentialGroup()
                .addGroup(pnlInProgressCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInProgressCardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblInProgressTitle))
                    .addGroup(pnlInProgressCardLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblInProgressCount)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInProgressCardLayout.setVerticalGroup(
            pnlInProgressCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInProgressCardLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblInProgressTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInProgressCount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblResolvedCount.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblResolvedCount.setText("0");

        lblResolvedTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblResolvedTitle.setText("RESOLVED");

        javax.swing.GroupLayout pnlResolvedCardLayout = new javax.swing.GroupLayout(pnlResolvedCard);
        pnlResolvedCard.setLayout(pnlResolvedCardLayout);
        pnlResolvedCardLayout.setHorizontalGroup(
            pnlResolvedCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResolvedCardLayout.createSequentialGroup()
                .addGroup(pnlResolvedCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResolvedCardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblResolvedTitle))
                    .addGroup(pnlResolvedCardLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblResolvedCount)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pnlResolvedCardLayout.setVerticalGroup(
            pnlResolvedCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResolvedCardLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblResolvedTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResolvedCount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblRecentTickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ticket No.", "Laboratory", "Numbers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblRecentTickets);

        lblMyFilesTitle3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMyFilesTitle3.setForeground(new java.awt.Color(255, 255, 255));
        lblMyFilesTitle3.setText("RECENTLY UPLOADED");

        javax.swing.GroupLayout lblLabTicketsTitleLayout = new javax.swing.GroupLayout(lblLabTicketsTitle);
        lblLabTicketsTitle.setLayout(lblLabTicketsTitleLayout);
        lblLabTicketsTitleLayout.setHorizontalGroup(
            lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblLabTicketsTitleLayout.createSequentialGroup()
                .addGroup(lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblLabTicketsTitleLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(lblMyFilesTitle2)
                        .addGap(364, 364, 364)
                        .addComponent(lblMyFilesTitle))
                    .addGroup(lblLabTicketsTitleLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                                .addComponent(btnUploadNewFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlTotalFilesCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(lblLabTicketsTitleLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblMyFilesTitle3)))
                        .addGap(112, 112, 112)
                        .addGroup(lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(lblLabTicketsTitleLayout.createSequentialGroup()
                                .addComponent(pnlPendingCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlInProgressCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlResolvedCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)
                            .addComponent(lblRecentTickets))))
                .addGap(525, 532, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblLabTicketsTitleLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(lblMyFilesSubtitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLabTicketsSubtitle)
                .addGap(630, 630, 630))
        );
        lblLabTicketsTitleLayout.setVerticalGroup(
            lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblLabTicketsTitleLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMyFilesTitle2)
                    .addComponent(lblMyFilesTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLabTicketsSubtitle)
                    .addComponent(lblMyFilesSubtitle1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlPendingCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTotalFilesCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInProgressCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlResolvedCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUploadNewFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecentTickets))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lblLabTicketsTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblLabTicketsTitleLayout.createSequentialGroup()
                        .addComponent(lblMyFilesTitle3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(163, 204, 163));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagess/573-5731936_coc-college-logo-vector-phinma-cagayan-de-oro-removebg-preview.png"))); // NOI18N
        jLabel8.setText("jLabel8");

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(53, 89, 71));
        jLabel2.setText("CITE FILE STORAGE");

        btnMyAccount1.setText("👤 My Account");
        btnMyAccount1.addActionListener(this::btnMyAccount1ActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMyAccount1)
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnMyAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLabTicketsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(lblLabTicketsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        btnMyAccount.setBackground(new java.awt.Color(163, 204, 163));
        btnMyAccount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMyAccount.setText("MY ACCOUNT");
        btnMyAccount.addActionListener(this::btnMyAccountActionPerformed);

        btnLogout.setBackground(new java.awt.Color(163, 204, 163));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setText("LOG OUT");
        btnLogout.setBorderPainted(false);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("MENU BAR");

        btnMyFiles.setBackground(new java.awt.Color(163, 204, 163));
        btnMyFiles.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMyFiles.setText("MY FILES");
        btnMyFiles.setBorderPainted(false);
        btnMyFiles.addActionListener(this::btnMyFilesActionPerformed);

        btnUploadFile.setBackground(new java.awt.Color(163, 204, 163));
        btnUploadFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUploadFile.setText("UPLOAD FILE");
        btnUploadFile.setBorderPainted(false);
        btnUploadFile.addActionListener(this::btnUploadFileActionPerformed);

        btnMyTickets.setBackground(new java.awt.Color(163, 204, 163));
        btnMyTickets.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMyTickets.setText("MY TICKETS");
        btnMyTickets.addActionListener(this::btnMyTicketsActionPerformed);

        btnReportProblem2.setBackground(new java.awt.Color(163, 204, 163));
        btnReportProblem2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReportProblem2.setText("REPORT A PROBLEM");
        btnReportProblem2.addActionListener(this::btnReportProblem2ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnMyFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnMyAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnUploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReportProblem2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMyTickets, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMyFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReportProblem2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMyTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMyAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadFileActionPerformed
        Uploadfile2 uploadfile2 = new Uploadfile2();
        uploadfile2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUploadFileActionPerformed

    private void btnMyFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyFilesActionPerformed
        MyFiles2 myFiles2 = new MyFiles2();
        myFiles2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMyFilesActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnMyAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyAccountActionPerformed
        MyAccount myAccount = new MyAccount();
        myAccount.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMyAccountActionPerformed

    private void btnMyAccount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyAccount1ActionPerformed
        MyAccount myAccount = new MyAccount();
myAccount.setVisible(true);
this.dispose();
    }//GEN-LAST:event_btnMyAccount1ActionPerformed

    private void btnMyTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyTicketsActionPerformed
        MyTickets ticket = new MyTickets();
ticket.setVisible(true);
this.dispose();  
    }//GEN-LAST:event_btnMyTicketsActionPerformed

    private void btnReportProblem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportProblem2ActionPerformed
        ReportProblem report = new ReportProblem();
report.setVisible(true);
this.dispose(); 
    }//GEN-LAST:event_btnReportProblem2ActionPerformed

    private void btnUploadNewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadNewFileActionPerformed
        Uploadfile2 uploadfile2 = new Uploadfile2();
        uploadfile2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUploadNewFileActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Homepage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyAccount;
    private javax.swing.JButton btnMyAccount1;
    private javax.swing.JButton btnMyFiles;
    private javax.swing.JButton btnMyTickets;
    private javax.swing.JButton btnReportProblem2;
    private javax.swing.JButton btnUploadFile;
    private javax.swing.JButton btnUploadNewFile;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInProgressCount;
    private javax.swing.JLabel lblInProgressTitle;
    private javax.swing.JLabel lblLabTicketsSubtitle;
    private javax.swing.JPanel lblLabTicketsTitle;
    private javax.swing.JLabel lblMyFilesSubtitle1;
    private javax.swing.JLabel lblMyFilesTitle;
    private javax.swing.JLabel lblMyFilesTitle2;
    private javax.swing.JLabel lblMyFilesTitle3;
    private javax.swing.JLabel lblPendingCount;
    private javax.swing.JLabel lblPendingTitle;
    private javax.swing.JLabel lblRecentTickets;
    private javax.swing.JLabel lblResolvedCount;
    private javax.swing.JLabel lblResolvedTitle;
    private javax.swing.JLabel lblTotalFilesTitle;
    private javax.swing.JLabel lblTotalFilesValue;
    private javax.swing.JPanel pnlInProgressCard;
    private javax.swing.JPanel pnlPendingCard;
    private javax.swing.JPanel pnlResolvedCard;
    private javax.swing.JPanel pnlTotalFilesCard;
    private javax.swing.JTable tblRecentFiles;
    private javax.swing.JTable tblRecentTickets;
    // End of variables declaration//GEN-END:variables
}
