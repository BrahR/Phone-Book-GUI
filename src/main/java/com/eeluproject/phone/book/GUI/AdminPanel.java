/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.eeluproject.phone.book.GUI;

import com.eeluproject.phone.book.Classes.Admin;
import com.eeluproject.phone.book.Classes.BookedContact;
import com.eeluproject.phone.book.Classes.Contact;
import com.eeluproject.phone.book.Classes.User;
import com.eeluproject.phone.book.Classes.Validator;
import com.eeluproject.phone.book.Utils.CountryUtils;
import com.eeluproject.phone.book.Utils.Utils;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Khmeu
 */
public class AdminPanel extends javax.swing.JFrame {

  public AdminPanel() {
    initComponents();
    setValuesFromSelectedRow();
    setTitle("Book Phone"); // Set the title of the JFrame
    ImageIcon icon = new ImageIcon("/com/eeluproject/phone/book/Assets/Icons/4298389.png");
    setIconImage(icon.getImage()); // Set the program icon
    allowedCountry.setText(
        "Allowed Country : " + CountryUtils.getCountryAndCode());

    refreshTables();
    ArrayList<String> formattedList = new ArrayList<>();

    for (String[] country : CountryUtils.countryNames) {
      formattedList.add(country[0] + " (+" + country[1] + ")");
    }
    // Create a DefaultComboBoxModel with the countryList
    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(
        formattedList.toArray(new String[0]));

    // Set the model to the existing JComboBox
    jComboBox1.setModel(comboBoxModel);
    triggerSelect = false;
    jComboBox1.setSelectedIndex(CountryUtils.selectedIndex);
    triggerSelect = true;
  }

  private static void refreshTables() {
    populateAdminBookedTable();
    populateSystemEmailsTable();
  }

  private static void populateAdminBookedTable() {
    DefaultTableModel adminBookedModel = (DefaultTableModel) adminBooked.getModel();
    adminBookedModel.setRowCount(0); // Clear existing rows
    ArrayList<Contact> contacts = BookedContact.getValidContacts();

    // foreach brought to you by javascript
    contacts
        .stream()
        .forEach(contact -> {
          adminBookedModel.addRow(
              new Object[] {
                  contact.getId(),
                  contact.getEmail(),
                  contact.getName(),
                  contact.getPhone(),
                  contact.getAddress(),
              });
        });

    // } else {
    // String emailToRemove = co.getEmail();
    // iterator.remove();
    // BookedContacts.deleteBookedContactsViaEmail(emailToRemove);
    // Registration.dncraseCount(emailToRemove);
    // }

    // Notify the table model that the data has changed
    SwingUtilities.invokeLater(() -> adminBookedModel.fireTableDataChanged());
  }

  private static void populateSystemEmailsTable() {
    DefaultTableModel systemEmailsModel = (DefaultTableModel) systemEmails.getModel();
    systemEmailsModel.setRowCount(0);

    // foreach brought to you by javascript
    User.users
        .stream()
        .forEach(user -> {
          if (user.getRole() == "admin")
            return;
          systemEmailsModel.addRow(
              new Object[] {
                  user.getId(),
                  user.getEmail(),
                  BookedContact.getBookedContactsByEmail(user.getEmail()).size(),
              });
        });

    systemEmailsModel.fireTableDataChanged();
  }

  private static void setValuesFromSelectedRow() {
    int selectedRow = adminBooked.getSelectedRow();

    if (selectedRow != -1) {
      String username = adminBooked.getValueAt(selectedRow, 2).toString();
      String phoneValue = adminBooked.getValueAt(selectedRow, 3).toString();
      String addressValue = adminBooked.getValueAt(selectedRow, 4).toString();
      phoneNumber.setText(phoneValue);
      Name.setText(username);
      addressF.setText(addressValue);
    }
  }

  boolean triggerSelect = false;

  /**
   * Creates new form NewJFrame
   */

  private void printBookedActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_printBookedActionPerformed
    JOptionPane.showMessageDialog(
        null,
        "WIP",
        "Soon TM",
        JOptionPane.INFORMATION_MESSAGE);
  } // GEN-LAST:event_printBookedActionPerformed

  private void printAccountsActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_printAccountsActionPerformed
    JOptionPane.showMessageDialog(
        null,
        "WIP",
        "Soon TM",
        JOptionPane.INFORMATION_MESSAGE);
  } // GEN-LAST:event_printAccountsActionPerformed

  private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    if (triggerSelect == false)
      return;
    try {
      int selectedCountry = jComboBox1.getSelectedIndex();

      CountryUtils.selectedIndex = selectedCountry;
      CountryUtils.setCountry(selectedCountry);
      CountryUtils.setCountryCode(selectedCountry);
      allowedCountry.setText(
          "Allowed Country is : " + CountryUtils.getCountryAndCode());
      System.out.println(CountryUtils.getCountry());

      // Update tables on the EDT
      SwingUtilities.invokeLater(() -> refreshTables());

      // Repaint the JFrame to ensure the UI is updated
      repaint();

      JOptionPane.showMessageDialog(
          null,
          "Allowed Country has been changed to: " +
              CountryUtils.getCountryAndCode() +
              ", Invalid phones will be deleted",
          "Country Updated",
          JOptionPane.INFORMATION_MESSAGE);

      populateAdminBookedTable();
    } catch (Exception e) {
    }
  }

  private void LogoutActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_LogoutActionPerformed
    LoginPanel loginFrame = new LoginPanel();
    loginFrame.setVisible(true);
    loginFrame.pack();
    loginFrame.setLocationRelativeTo(null);
    this.dispose();
  } // GEN-LAST:event_LogoutActionPerformed

  // GEN-LAST:event_deleteButton1MouseClicked

  private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
    int rowCount = adminBooked.getSelectedRowCount();
    if (!Utils.displayError(
        new Validator(
            rowCount == 1,
            "Please select one row at a time",
            "Invalid Input"))) {
      return;
    }
    int selectedRow = adminBooked.getSelectedRow();
    System.out.println(selectedRow);
    int selectedRowId = (int) adminBooked.getValueAt(selectedRow, 0);
    String name = Name.getText();
    String phone = phoneNumber.getText();
    String address = addressF.getText();

    DefaultTableModel model = (DefaultTableModel) adminBooked.getModel();
    ArrayList<Validator> inputs = new ArrayList<>(
        Arrays.asList(
            new Validator(
                Utils.validatePhoneNumber(phone),
                "Invalid phone format",
                "Invalid input"),
            new Validator(
                Utils.validateName(name),
                "Invalid name",
                "Invalid input"),
            new Validator(
                Utils.validateAddress(address),
                "Invalid address: address should only consist of English letters, numbers, commas, and dots",
                "Invalid input")));

    if (!Utils.displayErrors(inputs)) {
      return;
    }

    try {
      ArrayList<Validator> err1 = new ArrayList<>(
          Arrays.asList(
              new Validator(
                  Utils.isDuplicated(selectedRowId, phone),
                  "This Phone Number is registered with another contact",
                  "Duplicated Phone Number")));

      if (!Utils.displayErrors(err1)) {
        return;
      }

      model.setValueAt(name, selectedRow, 2);
      model.setValueAt(phone, selectedRow, 3);
      model.setValueAt(address, selectedRow, 4);
      // Utils.setValues( exception error
      // model,
      // new ArrayList<Object>(Arrays.asList(name, phone, address)),
      // selectedRowId
      // );
      System.out.println("Edited");
      Contact contact = BookedContact.findById(selectedRowId);

      contact.setAddress(address);
      contact.setName(name);
      contact.setPhone(phone);

      JOptionPane.showMessageDialog(
          null,
          "Account has been updated",
          "Successful update",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_clearButtonActionPerformed
    String name = Name.getText();
    String phone = phoneNumber.getText();
    String address = addressF.getText();
    if (!phone.isEmpty() || !name.isEmpty() || !address.isEmpty()) {
      Name.setText("");
      phoneNumber.setText("");
      addressF.setText("");

      return;
    }

    JOptionPane.showMessageDialog(
        null,
        "Nothing to clear",
        "invalid Click",
        JOptionPane.WARNING_MESSAGE);
  } // GEN-LAST:event_clearButtonActionPerformed

  private void deleteAccountActionPerformed(java.awt.event.ActionEvent evt) {
    int rowCount = systemEmails.getRowCount();
    int[] selectedRows = systemEmails.getSelectedRows();

    ArrayList<Validator> validateSelectedRows = new ArrayList<>(
        Arrays.asList(
            new Validator(
                rowCount > 0,
                "Please Select the row you want to delete",
                "invalid input"),
            new Validator(
                selectedRows.length > 0,
                "There are no rows to delete",
                "Invalid Input")));

    if (!Utils.displayErrors(validateSelectedRows))
      return;

    try {
      DefaultTableModel modelEmails = (DefaultTableModel) systemEmails.getModel();

      for (int i = selectedRows.length - 1; i >= 0; i--) {
        int row = selectedRows[i];
        String emailToDelete = (String) modelEmails.getValueAt(row, 1);

        modelEmails.removeRow(row);
        // deletes both user and their contacts
        User.delete(emailToDelete);
      }

      refreshTables();

      JOptionPane.showMessageDialog(
          null,
          "Selected bookings have been deleted successfully.",
          "Deletion Successful",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(
          null,
          "Something bad happened" + e.getMessage(),
          "Internal Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void deleteBookActionPerformed(java.awt.event.ActionEvent evt) {
    int rowCount = adminBooked.getRowCount();

    int[] selectedRows = adminBooked.getSelectedRows();
    System.out.println(selectedRows.length);
    ArrayList<Validator> validateSelectedRows = new ArrayList<>(
        Arrays.asList(
            new Validator(
                rowCount > 0,
                "Please Select the row you want to delete",
                "invalid input"),
            new Validator(
                selectedRows.length > 0,
                "There are no rows to delete",
                "Invalid Input")));

    if (!Utils.displayErrors(validateSelectedRows))
      return;

    try {
      DefaultTableModel model = (DefaultTableModel) adminBooked.getModel();

      for (int i = selectedRows.length - 1; i >= 0; i--) {
        int row = selectedRows[i];
        String en = (String) model.getValueAt(row, 3);

        model.removeRow(row);
        BookedContact.deleteByPhone(en);
      }
      refreshTables();
      JOptionPane.showMessageDialog(
          null,
          "Selected bookings have been deleted successfully.",
          "Deletion Successful",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(
          null,
          "Something bad happened" + e.getMessage(),
          "Internal Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
    String name = Name.getText();
    String phone = phoneNumber.getText();
    String address = addressF.getText();

    DefaultTableModel model = (DefaultTableModel) adminBooked.getModel();
    String id = JOptionPane.showInputDialog(
        "Please Enter the id that will be linked to that booking:");

    ArrayList<Validator> inputs = new ArrayList<>(
        Arrays.asList(
            new Validator(
                Admin.findById(Integer.parseInt(id)) != null,
                "The entered id does not exist",
                "Invalid input"),
            new Validator(
                Utils.validatePhoneNumber(phone),
                "Invalid phone format",
                "Invalid input"),
            new Validator(
                Utils.validateName(name) && !name.isEmpty(),
                "Invalid name",
                "Invalid input"),
            new Validator(
                Utils.validateAddress(address),
                "Invalid address: address should only consist of English letters, numbers, commas, and dots",
                "Invalid input"),
            new Validator(
                !Utils.isDuplicated(phone),
                "Duplicated Phone Number",
                "Invalid Phone Number")));

    if (!Utils.displayErrors(inputs))
      return;
    try {
      User loginObject = Admin.findById(Integer.parseInt(id));
      new BookedContact(
          new Contact(
              name,
              phone,
              address,
              loginObject.getEmail(),
              loginObject.getId()));
      model.addRow(
          new Object[] {
              loginObject.getId(),
              loginObject.getEmail(),
              name,
              phone,
              address,
          });

      refreshTables();
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(
          null,
          "An unexpected error occurred: " + e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  // GEN-LAST:event_addButtonActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
    // (optional) ">
    /*
     * If Nimbus (introduced in Java SE 6) is not available, stay with the default
     * look and feel.
     * For details see
     * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger
          .getLogger(AdminPanel.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger
          .getLogger(AdminPanel.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger
          .getLogger(AdminPanel.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger
          .getLogger(AdminPanel.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {
    jTextField2 = new javax.swing.JTextField();
    jPanel1 = new javax.swing.JPanel();
    userDashboard = new javax.swing.JPanel();
    Name = new javax.swing.JTextField();
    phoneNumber = new javax.swing.JTextField();
    clearButton = new javax.swing.JButton();
    addButton = new javax.swing.JButton();
    deleteBook = new javax.swing.JButton();
    updateButton = new javax.swing.JButton();
    NameLable = new javax.swing.JLabel();
    phoneLable1 = new javax.swing.JLabel();
    addressF = new javax.swing.JTextField();
    NameLable1 = new javax.swing.JLabel();
    deleteAccount = new javax.swing.JButton();
    javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    systemEmails = new javax.swing.JTable();
    javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
    adminBooked = new javax.swing.JTable();
    Logout = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jComboBox1 = new javax.swing.JComboBox<>();
    printBooked = new javax.swing.JButton();
    printAccounts = new javax.swing.JButton();
    allowedCountry = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();

    jTextField2.setText("jTextField1");
    jTextField2.setName("jTextField2"); // NOI18N

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setName("Form"); // NOI18N
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanel1.setBackground(new java.awt.Color(25, 118, 211));
    jPanel1.setName("jPanel1"); // NOI18N
    jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    userDashboard.setBackground(new java.awt.Color(25, 118, 211));
    userDashboard.setBorder(
        new javax.swing.border.SoftBevelBorder(
            javax.swing.border.BevelBorder.RAISED,
            java.awt.Color.white,
            java.awt.Color.white,
            java.awt.Color.white,
            java.awt.Color.white));
    userDashboard.setForeground(new java.awt.Color(255, 255, 255));
    userDashboard.setToolTipText("");
    userDashboard.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
    userDashboard.setName("userDashboard"); // NOI18N
    userDashboard.setOpaque(false);
    userDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    Name.setText("sad");
    Name.setToolTipText("");
    Name.setName("Name"); // NOI18N
    userDashboard.add(
        Name,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 361, 49));

    phoneNumber.setText("01000627666");
    phoneNumber.setName("phoneNumber"); // NOI18N
    userDashboard.add(
        phoneNumber,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 360, 49));

    clearButton.setBackground(new java.awt.Color(240, 240, 240));
    clearButton.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
    clearButton.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/eraser.png"))); // NOI18N
    clearButton.setText("Clear");
    clearButton.setBorder(null);
    clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    clearButton.setName("clearButton"); // NOI18N
    clearButton.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearButtonActionPerformed(evt);
          }
        });
    userDashboard.add(
        clearButton,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, 165, 41));

    addButton.setBackground(new java.awt.Color(240, 240, 240));
    addButton.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
    addButton.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/add.png"))); // NOI18N
    addButton.setText("Book");
    addButton.setBorder(null);
    addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    addButton.setName("addButton"); // NOI18N
    addButton.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            addButtonActionPerformed(evt);
          }
        });
    userDashboard.add(
        addButton,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 165, 41));

    deleteBook.setBackground(new java.awt.Color(240, 240, 240));
    deleteBook.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
    deleteBook.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/delete.png"))); // NOI18N
    deleteBook.setText("Delete Booking");
    deleteBook.setBorder(null);
    deleteBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    deleteBook.setName("deleteBook"); // NOI18N
    deleteBook.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteBookActionPerformed(evt);
          }
        });
    userDashboard.add(
        deleteBook,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 165, 41));

    updateButton.setBackground(new java.awt.Color(240, 240, 240));
    updateButton.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
    updateButton.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/updated.png"))); // NOI18N
    updateButton.setText("Update");
    updateButton.setBorder(null);
    updateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    updateButton.setName("updateButton"); // NOI18N
    updateButton.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            updateButtonActionPerformed(evt);
          }
        });
    userDashboard.add(
        updateButton,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 610, 165, 41));

    NameLable.setBackground(new java.awt.Color(255, 255, 255));
    NameLable.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
    NameLable.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/signature.png"))); // NOI18N
    NameLable.setLabelFor(NameLable);
    NameLable.setText("Name");
    NameLable.setName("NameLable"); // NOI18N
    userDashboard.add(
        NameLable,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 392, -1));

    phoneLable1.setBackground(new java.awt.Color(255, 255, 255));
    phoneLable1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
    phoneLable1.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/smartphone.png"))); // NOI18N
    phoneLable1.setLabelFor(phoneNumber);
    phoneLable1.setText("Phone Number");
    phoneLable1.setName("phoneLable1"); // NOI18N
    userDashboard.add(
        phoneLable1,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, -1));

    addressF.setText("sad123");
    addressF.setToolTipText("");
    addressF.setName("addressF"); // NOI18N
    userDashboard.add(
        addressF,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 361, 49));

    NameLable1.setBackground(new java.awt.Color(255, 255, 255));
    NameLable1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
    NameLable1.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/address.png"))); // NOI18N
    NameLable1.setLabelFor(NameLable);
    NameLable1.setText("Address");
    NameLable1.setName("NameLable1"); // NOI18N
    userDashboard.add(
        NameLable1,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 398, -1));

    deleteAccount.setBackground(new java.awt.Color(240, 240, 240));
    deleteAccount.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
    deleteAccount.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/com/eeluproject/phone/book/Assets/Icons/delete.png"))); // NOI18N
    deleteAccount.setText("Delete Account");
    deleteAccount.setBorder(null);
    deleteAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    deleteAccount.setName("deleteAccount"); // NOI18N
    deleteAccount.addMouseListener(
        new java.awt.event.MouseAdapter() {
          public void mouseClicked(java.awt.event.MouseEvent evt) {
            deleteAccountMouseClicked(evt);
          }
        });
    deleteAccount.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteAccountActionPerformed(evt);
          }
        });
    userDashboard.add(
        deleteAccount,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 165, 41));

    jScrollPane2.setName("jScrollPane2"); // NOI18N

    systemEmails.setBackground(new java.awt.Color(225, 225, 225));
    systemEmails.setBorder(
        javax.swing.BorderFactory.createEtchedBorder(
            javax.swing.border.EtchedBorder.RAISED));
    systemEmails.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
    systemEmails.setModel(
        new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Email", "Number of books" }) {
          Class<Object>[] types = new Class[] {
              java.lang.Integer.class,
              java.lang.String.class,
              java.lang.Integer.class,
          };
          boolean[] canEdit = new boolean[] { false, false, false };

          public Class<Object> getColumnClass(int columnIndex) {
            return types[columnIndex];
          }

          public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
          }
        });
    systemEmails.setFocusable(false);
    systemEmails.setGridColor(new java.awt.Color(255, 255, 255));
    systemEmails.setName("systemEmails"); // NOI18N
    systemEmails.setShowGrid(true);
    jScrollPane2.setViewportView(systemEmails);

    userDashboard.add(
        jScrollPane2,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 490, 489));

    jScrollPane3.setName("jScrollPane3"); // NOI18N

    adminBooked.setBackground(new java.awt.Color(225, 225, 225));
    adminBooked.setBorder(
        javax.swing.BorderFactory.createEtchedBorder(
            javax.swing.border.EtchedBorder.RAISED));
    adminBooked.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
    adminBooked.setModel(
        new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Email", "Name", "Phone Number", "Address" }) {
          Class<Object>[] types = new Class[] {
              java.lang.Integer.class,
              java.lang.String.class,
              java.lang.String.class,
              java.lang.String.class,
              java.lang.String.class,
          };
          boolean[] canEdit = new boolean[] { false, false, false, false, false };

          public Class<Object> getColumnClass(int columnIndex) {
            return types[columnIndex];
          }

          public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
          }
        });
    adminBooked.setFocusable(false);
    adminBooked.setGridColor(new java.awt.Color(255, 255, 255));
    adminBooked.setName("adminBooked"); // NOI18N
    adminBooked.setShowGrid(true);
    adminBooked.getTableHeader().setReorderingAllowed(false);
    jScrollPane3.setViewportView(adminBooked);

    userDashboard.add(
        jScrollPane3,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 40, 490, 489));

    Logout.setBackground(new java.awt.Color(25, 118, 211));
    Logout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    Logout.setForeground(new java.awt.Color(255, 255, 255));
    Logout.setText("Logout");
    Logout.setBorder(null);
    Logout.setContentAreaFilled(false);
    Logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Logout.setName("Logout"); // NOI18N
    Logout.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            LogoutActionPerformed(evt);
          }
        });
    userDashboard.add(
        Logout,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 700, -1, -1));

    jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
    jLabel2.setText("Allowed Country for booking");
    jLabel2.setName("jLabel2"); // NOI18N
    userDashboard.add(
        jLabel2,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 250, 30));

    jComboBox1.setModel(
        new javax.swing.DefaultComboBoxModel<>(
            new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jComboBox1.setName("jComboBox1"); // NOI18N
    jComboBox1.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox1ActionPerformed(evt);
          }
        });
    userDashboard.add(
        jComboBox1,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 100, 40));

    printBooked.setText("Print Booked  data");
    printBooked.setName("printBooked"); // NOI18N
    printBooked.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            printBookedActionPerformed(evt);
          }
        });
    userDashboard.add(
        printBooked,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 670, 170, 40));

    printAccounts.setText("Print Accounts");
    printAccounts.setName("printAccounts"); // NOI18N
    printAccounts.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            printAccountsActionPerformed(evt);
          }
        });
    userDashboard.add(
        printAccounts,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 170, 40));

    allowedCountry.setFont(new java.awt.Font("Segoe UI Semibold", 1, 15)); // NOI18N
    allowedCountry.setText("Allowed Country :");
    allowedCountry.setName("allowedCountry"); // NOI18N
    userDashboard.add(
        allowedCountry,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 220, 20));

    jPanel1.add(
        userDashboard,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1520, 780));

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("X");
    jLabel1.setName("jLabel1"); // NOI18N
    jLabel1.addMouseListener(
        new java.awt.event.MouseAdapter() {
          public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel1MouseClicked(evt);
          }
        });
    jPanel1.add(
        jLabel1,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 0, 20, 30));

    getContentPane()
        .add(
            jPanel1,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1520, 790));
    jPanel1.getAccessibleContext().setAccessibleName("Book phone");

    pack();
  } // </editor-fold>

  protected void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
  }

  protected void deleteAccountMouseClicked(java.awt.event.MouseEvent evt) {
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton Logout;
  private static javax.swing.JTextField Name;
  private javax.swing.JLabel NameLable;
  private javax.swing.JLabel NameLable1;
  private javax.swing.JButton addButton;
  private static javax.swing.JTextField addressF;
  static javax.swing.JTable adminBooked;
  private javax.swing.JLabel allowedCountry;
  private javax.swing.JButton clearButton;
  private javax.swing.JButton deleteAccount;
  private javax.swing.JButton deleteBook;
  private static javax.swing.JComboBox<String> jComboBox1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JTextField jTextField2;
  private javax.swing.JLabel phoneLable1;
  private static javax.swing.JTextField phoneNumber;
  private javax.swing.JButton printAccounts;
  private javax.swing.JButton printBooked;
  static javax.swing.JTable systemEmails;
  private javax.swing.JButton updateButton;
  private javax.swing.JPanel userDashboard;
  // End of variables declaration//GEN-END:variables
}
