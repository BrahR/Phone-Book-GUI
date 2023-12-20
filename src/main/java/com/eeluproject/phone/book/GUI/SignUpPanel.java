package com.eeluproject.phone.book.GUI;

import com.eeluproject.phone.book.Classes.Admin;
import com.eeluproject.phone.book.Classes.User;
import com.eeluproject.phone.book.Classes.Validator;
import com.eeluproject.phone.book.Utils.Utils;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SignUpPanel extends javax.swing.JFrame {

  public SignUpPanel() {
    initComponents();
    txtusername.setBackground(new java.awt.Color(0, 0, 0, 1));
    txtpass.setBackground(new java.awt.Color(0, 0, 0, 1));
    txtconfirmpass.setBackground(new java.awt.Color(0, 0, 0, 1));
  }

  private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_txtusernameActionPerformed
    // TODO add your handling code here:
  } // GEN-LAST:event_txtusernameActionPerformed

  private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) { // GEN-FIRST:event_jLabel2MouseClicked
    System.exit(0);
  } // GEN-LAST:event_jLabel2MouseClicked

  private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {} // GEN-FIRST:event_jCheckBox1ActionPerformed // GEN-LAST:event_jCheckBox1ActionPerformed

  private void registerActionPerformed(java.awt.event.ActionEvent evt) {
    String filledEmail = txtusername.getText();
    String filledPassword = txtpass.getText();
    String filledConfirmPassword = txtconfirmpass.getText();

    ArrayList<Validator> validations = new ArrayList<>();

    validations.add(
      new Validator(
        Utils.validatePassword(filledPassword),
        "Invalid password, please enter a valid one",
        "Invalid input"
      )
    );

    validations.add(
      new Validator(
        filledPassword.equals(filledConfirmPassword),
        "Passwords aren't identical",
        "Invalid input"
      )
    );

    validations.add(
      new Validator(
        Admin.findByEmail(filledEmail) == null,
        "Already registered email",
        "Duplicated email"
      )
    );

    validations.add(
      new Validator(
        Utils.validateEmail(filledEmail),
        "Invalid email",
        "Invalid input"
      )
    );

    if (!Utils.displayErrors(validations)) return;

    // If all conditions pass, proceed with registration

    int id = Utils.generateRandomNumber();
    User user = User.register(id, filledEmail, filledPassword);

    JOptionPane.showMessageDialog(
      null,
      "Please note Your ID is :" + user.getId(),
      "Registration Complete.",
      JOptionPane.WARNING_MESSAGE
    );

    // Proceed with the rest of your application logic (e.g., opening a new frame)
    UserPanel app = new UserPanel(user);
    app.setVisible(true);
    app.pack();
    app.setLocationRelativeTo(null);
    this.dispose();
  }

  // GEN-LAST:event_registerActionPerformed

  private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) { // GEN-FIRST:event_jLabel10MouseClicked
    System.exit(0);
  } // GEN-LAST:event_jLabel10MouseClicked

  private void txtpassActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_txtpassActionPerformed
    // TODO add your handling code here:
  } // GEN-LAST:event_txtpassActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_jButton2ActionPerformed
    LoginPanel LoginFrame = new LoginPanel();
    LoginFrame.setVisible(true);
    LoginFrame.pack();
    LoginFrame.setLocationRelativeTo(null);
    this.dispose();
  } // GEN-LAST:event_jButton2ActionPerformed

  private void Disable2MouseClicked(java.awt.event.MouseEvent evt) { // GEN-FIRST:event_Disable2MouseClicked
    txtconfirmpass.setEchoChar((char) 0);
    Disable2.setVisible(false);
    Disable2.setEnabled(false);
    Enable2.setVisible(true);
    Enable2.setEnabled(true);
  } // GEN-LAST:event_Disable2MouseClicked

  private void Enable2MouseClicked(java.awt.event.MouseEvent evt) { // GEN-FIRST:event_Enable2MouseClicked
    txtconfirmpass.setEchoChar((char) 8226);
    Disable2.setVisible(true);
    Disable2.setEnabled(true);
    Enable2.setVisible(false);
    Enable2.setEnabled(false);
  } // GEN-LAST:event_Enable2MouseClicked

  private void Disable1MouseClicked(java.awt.event.MouseEvent evt) { // GEN-FIRST:event_Disable1MouseClicked
    txtpass.setEchoChar((char) 0);
    Disable1.setVisible(false);
    Disable1.setEnabled(false);
    Enable1.setVisible(true);
    Enable1.setEnabled(true);
  } // GEN-LAST:event_Disable1MouseClicked

  private void Enable1MouseClicked(java.awt.event.MouseEvent evt) { // GEN-FIRST:event_Enable1MouseClicked
    txtpass.setEchoChar((char) 8226);
    Disable1.setVisible(true);
    Disable1.setEnabled(true);
    Enable1.setVisible(false);
    Enable1.setEnabled(false);
  } // GEN-LAST:event_Enable1MouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel Disable1;
  private javax.swing.JLabel Disable2;
  private javax.swing.JLabel Enable1;
  private javax.swing.JLabel Enable2;
  private javax.swing.JButton jButton2;
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel15;
  private javax.swing.JLabel jLabel16;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JButton register;
  private javax.swing.JPasswordField txtconfirmpass;
  private javax.swing.JPasswordField txtpass;
  private javax.swing.JTextField txtusername;

  // End of variables declaration//GEN-END:variables

  private void initComponents() {
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    jPanel1 = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel11 = new javax.swing.JLabel();
    Disable2 = new javax.swing.JLabel();
    Enable2 = new javax.swing.JLabel();
    Disable1 = new javax.swing.JLabel();
    Enable1 = new javax.swing.JLabel();
    jLabel14 = new javax.swing.JLabel();
    jCheckBox1 = new javax.swing.JCheckBox();
    jLabel15 = new javax.swing.JLabel();
    jLabel16 = new javax.swing.JLabel();
    register = new javax.swing.JButton();
    jLabel6 = new javax.swing.JLabel();
    jButton2 = new javax.swing.JButton();
    jLabel10 = new javax.swing.JLabel();
    txtusername = new javax.swing.JTextField();
    txtconfirmpass = new javax.swing.JPasswordField();
    txtpass = new javax.swing.JPasswordField();
    jPanel2 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setUndecorated(true);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanel1.setBackground(new java.awt.Color(25, 118, 211));
    jPanel1.setPreferredSize(new java.awt.Dimension(420, 380));
    jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel2.setBackground(new java.awt.Color(255, 255, 255));
    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel2.setText("X");
    jLabel2.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          jLabel2MouseClicked(evt);
        }
      }
    );
    jPanel1.add(
      jLabel2,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 230, 90)
    );

    jLabel4.setForeground(new java.awt.Color(255, 255, 255));
    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel4.setText(
      "Welcome to Your Digital Phonebook: Connect, Organize, Thrive!"
    );
    jPanel1.add(
      jLabel4,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 70, 510, -1)
    );

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel3.setText("SignUp");
    jPanel1.add(
      jLabel3,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 20, 510, -1)
    );

    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(199, 226, 255));
    jLabel5.setText("Email");
    jPanel1.add(
      jLabel5,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 380, -1)
    );

    jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel7.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/icons8_user_20px_1.png")
      )
    ); // NOI18N
    jPanel1.add(
      jLabel7,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(1584, 169, 47, 43)
    );

    jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel8.setForeground(new java.awt.Color(199, 226, 255));
    jLabel8.setText("Confirm Password");
    jPanel1.add(
      jLabel8,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 230, 30)
    );

    jLabel9.setForeground(new java.awt.Color(255, 255, 255));
    jLabel9.setText("_________________________________________");
    jPanel1.add(
      jLabel9,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1)
    );

    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel11.setForeground(new java.awt.Color(199, 226, 255));
    jLabel11.setText("Create Password");
    jPanel1.add(
      jLabel11,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 270, 26)
    );

    Disable2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Disable2.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/icons8_invisible_20px_1.png")
      )
    ); // NOI18N
    Disable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Disable2.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          Disable2MouseClicked(evt);
        }
      }
    );
    jPanel1.add(
      Disable2,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 41, 34)
    );

    Enable2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Enable2.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/icons8_eye_20px_1.png")
      )
    ); // NOI18N
    Enable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Enable2.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          Enable2MouseClicked(evt);
        }
      }
    );
    jPanel1.add(
      Enable2,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 41, 34)
    );

    Disable1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Disable1.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/icons8_invisible_20px_1.png")
      )
    ); // NOI18N
    Disable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Disable1.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          Disable1MouseClicked(evt);
        }
      }
    );
    jPanel1.add(
      Disable1,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 41, 34)
    );

    Enable1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Enable1.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/icons8_eye_20px_1.png")
      )
    ); // NOI18N
    Enable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Enable1.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          Enable1MouseClicked(evt);
        }
      }
    );
    jPanel1.add(
      Enable1,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 41, 34)
    );

    jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel14.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/icons8_user_20px_1.png")
      )
    ); // NOI18N
    jPanel1.add(
      jLabel14,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 41, 34)
    );

    jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
    jCheckBox1.setForeground(new java.awt.Color(199, 226, 255));
    jCheckBox1.setText("i accept all terms & conditions");
    jCheckBox1.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          jCheckBox1ActionPerformed(evt);
        }
      }
    );
    jPanel1.add(
      jCheckBox1,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1)
    );

    jLabel15.setForeground(new java.awt.Color(255, 255, 255));
    jLabel15.setText("_________________________________________");
    jPanel1.add(
      jLabel15,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1)
    );

    jLabel16.setForeground(new java.awt.Color(255, 255, 255));
    jLabel16.setText("_________________________________________");
    jPanel1.add(
      jLabel16,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1)
    );

    register.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    register.setForeground(new java.awt.Color(25, 118, 211));
    register.setText("REGISTER");
    register.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    register.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          registerActionPerformed(evt);
        }
      }
    );
    jPanel1.add(
      register,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 340, 38)
    );

    jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(199, 226, 255));
    jLabel6.setText("Already have an account?");
    jPanel1.add(
      jLabel6,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, -1, -1)
    );

    jButton2.setBackground(new java.awt.Color(25, 118, 211));
    jButton2.setForeground(new java.awt.Color(199, 226, 255));
    jButton2.setText("Login now");
    jButton2.setBorder(null);
    jButton2.setContentAreaFilled(false);
    jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton2.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          jButton2ActionPerformed(evt);
        }
      }
    );
    jPanel1.add(
      jButton2,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, -1, -1)
    );

    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    jLabel10.setForeground(new java.awt.Color(255, 255, 255));
    jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel10.setText("x");
    jLabel10.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          jLabel10MouseClicked(evt);
        }
      }
    );
    jPanel1.add(
      jLabel10,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(380, -20, 50, 60)
    );

    txtusername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    txtusername.setForeground(new java.awt.Color(255, 255, 255));
    txtusername.setText("ali@gmail.com");
    txtusername.setBorder(null);
    txtusername.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          txtusernameActionPerformed(evt);
        }
      }
    );
    jPanel1.add(
      txtusername,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 280, -1)
    );

    txtconfirmpass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    txtconfirmpass.setForeground(new java.awt.Color(255, 255, 255));
    txtconfirmpass.setText("Gh9513572680$");
    txtconfirmpass.setBorder(null);
    jPanel1.add(
      txtconfirmpass,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 280, -1)
    );

    txtpass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    txtpass.setForeground(new java.awt.Color(255, 255, 255));
    txtpass.setText("Gh9513572680$");
    txtpass.setBorder(null);
    txtpass.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          txtpassActionPerformed(evt);
        }
      }
    );
    jPanel1.add(
      txtpass,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 280, -1)
    );

    getContentPane()
      .add(
        jPanel1,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 420, 440)
      );
    jPanel1.getAccessibleContext().setAccessibleName("");

    jPanel2.setBackground(new java.awt.Color(255, 255, 255));
    jPanel2.setPreferredSize(new java.awt.Dimension(500, 378));
    jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel1.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/bg-login.png")
      )
    ); // NOI18N
    jPanel2.add(
      jLabel1,
      new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 540, 362)
    );

    getContentPane()
      .add(
        jPanel2,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 440)
      );

    pack();
  } // </editor-fold>//GEN-END:initComponents
}
