/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servercazo;

import ConnectDB.Connections;
import caro.common.Room;
import caro.common.Users;
import ConnectDB.DataFunc;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author kietdang
 */
public class Main extends javax.swing.JFrame {

    static public ArrayList<ClientHandler> lstClient;

    static public ArrayList<Room> lstRoom;

    static java.util.List<Users> uslist = new ArrayList<Users>();

    static DataFunc df = new DataFunc();
    /**
     * Creates new form Main
     */
    Users us;

    public Main() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_refresh = new javax.swing.JButton();
        bt_del = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        bt_add1 = new javax.swing.JButton();
        tx_password = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tx_username = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        btnBlock = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        bt_refresh.setText("Refresh");
        bt_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_refreshActionPerformed(evt);
            }
        });

        bt_del.setText("Delete");
        bt_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_delActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Win", "Lose", "Score", "Blocked"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane1.addTab("Danh Sách User", jScrollPane1);

        bt_add1.setText("Thêm");
        bt_add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Mật khẩu");

        jLabel8.setText("Tài khoản");

        jButton1.setText("Thoát");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Thêm mới người dùng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bt_add1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tx_password)
                                .addComponent(tx_username, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(157, 157, 157))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tx_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tx_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(bt_add1))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm tài khoản", jPanel2);

        jTextPane1.setEditable(false);
        jScrollPane2.setViewportView(jTextPane1);

        jTabbedPane1.addTab("Người Chơi Đang Online", jScrollPane2);

        jTextPane2.setEditable(false);
        jScrollPane3.setViewportView(jTextPane2);

        jTabbedPane1.addTab("Log Trận Đấu", jScrollPane3);

        btnBlock.setText("Block");
        btnBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_del)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_refresh)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_del)
                    .addComponent(btnUpdate)
                    .addComponent(bt_refresh)
                    .addComponent(btnBlock))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void loadDataToJTable(JTable jTable) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng

        Connection connection = Connections.Newconnect();

        if (connection != null) {
            try {
                // Thực hiện truy vấn cơ sở dữ liệu
                String query = "SELECT id, username, win, lose, score, blocked FROM Users";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    int win = resultSet.getInt("win");
                    int lose = resultSet.getInt("lose");
                    int score = resultSet.getInt("score");
                    boolean blocked = resultSet.getBoolean("blocked");

                    // Chuyển dữ liệu từ cơ sở dữ liệu thành một hàng trong bảng
                    Object[] rowData = {id, username, win, lose, score, blocked};

                    // Thêm hàng vào mô hình bảng
                    model.addRow(rowData);
                }

                // Đóng tài nguyên
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection is null. Check your database connection.");
        }

        jTable.setModel(model); // Đặt mô hình cho JTable
    }

    private void bt_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_refreshActionPerformed
        loadDataToJTable(jTable1);
    }//GEN-LAST:event_bt_refreshActionPerformed

    private void bt_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_delActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Xin hãy chọn người dùng trước khi xóa!", "Lỗi", 1);
            return;
        }

        int userId = (int) jTable1.getValueAt(selectedRow, 0); // Lấy ID từ cột đầu tiên (0)

        // Hiển thị hộp thoại xác nhận
        int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa người dùng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (confirmResult == JOptionPane.YES_OPTION) {
            try {
                df.DeleteUser(userId);
                JOptionPane.showMessageDialog(null, "Người dùng có mã='" + userId + "' đã bị xóa khỏi danh sách!", "Hoàn tất", 1);
                loadDataToJTable(jTable1); // Cập nhật lại dữ liệu trên JTable sau khi xóa
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bt_delActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        loadDataToJTable(jTable1);

    }//GEN-LAST:event_formWindowOpened

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một người dùng để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userId = (int) jTable1.getValueAt(selectedRow, 0);
        String username = (String) jTable1.getValueAt(selectedRow, 1);
        int win = (int) jTable1.getValueAt(selectedRow, 2);
        int lose = (int) jTable1.getValueAt(selectedRow, 3);
        int score = (int) jTable1.getValueAt(selectedRow, 4);
        boolean blocked = (boolean) jTable1.getValueAt(selectedRow, 5);

        Users userToUpdate = new Users();
        userToUpdate.setId(userId);
        userToUpdate.setUsername(username);
        userToUpdate.setWin(win);
        userToUpdate.setLose(lose);
        userToUpdate.setScore(score);
        userToUpdate.setBlocked(blocked);
        // Khởi tạo đối tượng DataFunc
        DataFunc dataFuncInstance = new DataFunc();

        // Khởi tạo đối tượng UpdateUser và truyền vào đối tượng Users và DataFunc
        UpdateUser updateUserForm = new UpdateUser(userToUpdate, dataFuncInstance);

        updateUserForm.setVisible(true);
        updateUserForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Refresh JTable when UpdateUser form is closed
                loadDataToJTable(jTable1);
            }
        });
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlockActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Chưa chọn người dùng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userId = (int) jTable1.getValueAt(selectedRow, 0);
        boolean currentBlockedStatus = (boolean) jTable1.getValueAt(selectedRow, 5); // Lấy trạng thái 'Blocked' từ cột thứ 6 (index 5)

        String confirmationMessage = currentBlockedStatus
                ? "Bạn có muốn unblock user này không?"
                : "Bạn có muốn block user này không?";

        int confirmationResult = JOptionPane.showConfirmDialog(null, confirmationMessage, "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirmationResult == JOptionPane.YES_OPTION) {
            boolean newBlockedStatus = !currentBlockedStatus;
            boolean updatedSuccessfully = df.updateUserBlockedStatus(userId, newBlockedStatus);

            if (updatedSuccessfully) {
                String statusMessage = newBlockedStatus ? "User đã bị chặn!" : "User đã được mở khóa!";
                JOptionPane.showMessageDialog(null, statusMessage, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadDataToJTable(jTable1); // Gọi hàm này để làm mới dữ liệu trên JTable
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật trạng thái. Vui lòng kiểm tra cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBlockActionPerformed

    private void bt_add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add1ActionPerformed
        // TODO add your handling code here:
        String username;
        String password;

        username = tx_username.getText();
        password = String.valueOf(tx_password.getPassword());

        if (username.compareTo("") == 0 || password.compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống.", "thông báo", 1);
            return;
        }

        if (df.checkAva(df.getId(username)) == false) {
            df.register(username, password);
            JOptionPane.showMessageDialog(null, "Thêm mới người đùng thành công!", "Hoàn tất", 1);
        }
        loadDataToJTable(jTable1);
    }//GEN-LAST:event_bt_add1ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("SERVER");
                ServerListener server;
                try {
                    server = new ServerListener();
                    server.start();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                lstClient = new ArrayList<ClientHandler>();
                lstRoom = new ArrayList<Room>();
                for (int i = 0; i < 10; i++) {
                    Room temp = new Room(i);
                    lstRoom.add(temp);

                }

                new Main().setVisible(true);
            }
        });
    }

    static class ServerListener extends Thread {

        private ServerSocket serverSocket;

        ServerListener() throws IOException {
            serverSocket = ServerSocketFactory.getDefault().createServerSocket(1234);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final Socket socketToClient = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(socketToClient);
                    lstClient.add(clientHandler);
                    System.out.println("Server: " + socketToClient.getInetAddress() + " is connecting");
                    clientHandler.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add1;
    private javax.swing.JButton bt_del;
    private javax.swing.JButton bt_refresh;
    private javax.swing.JButton btnBlock;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JPasswordField tx_password;
    private javax.swing.JTextField tx_username;
    // End of variables declaration//GEN-END:variables
}
