//IMPORTS EVERYWHERE


import com.google.gdata.client.GoogleAuthTokenFactory.UserToken;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.Query;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.docs.DocumentEntry;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.docs.FolderEntry;
import com.google.gdata.data.docs.PresentationEntry;
import com.google.gdata.data.docs.RevisionFeed;
import com.google.gdata.data.docs.SpreadsheetEntry;
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import com.google.gdata.data.Link;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.docs.RevisionEntry;
import com.google.gdata.data.docs.RevisionFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class MainWindowOld extends javax.swing.JFrame {
    public DocsService service;
    DefaultTableModel dtm = new DefaultTableModel();
    String currentUser = "";
    String currentPass = "";
    String publicFileName = "";

    /** Creates new form MainWindowOld */
    public MainWindowOld() {
        initComponents();
        service = new DocsService("SecureNotepad");

        //DEBUG CODE
        tf_Username.setText("cryptoproject11");
        tf_Password.setText("cryptography");
        //END DEBUG CODE

        tableView.setModel(dtm);
        dtm.addColumn("Document Name");
        dtm.addColumn("Document ID");

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tf_Username = new javax.swing.JTextField();
        lbl_Username = new javax.swing.JLabel();
        lbl_Password = new javax.swing.JLabel();
        tf_Password = new javax.swing.JTextField();
        btn_Login = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        btn_UpdateFile = new javax.swing.JButton();
        btn_CreateFile = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        tf_FilePassword = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btn_DeleteSelectedFile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        lbl_Username.setText("Username:");

        lbl_Password.setText("Password:");

        btn_Login.setText("Login");
        btn_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LoginActionPerformed(evt);
            }
        });

        tableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableView);

        btn_UpdateFile.setText("Update Selected File");
        btn_UpdateFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateFileActionPerformed(evt);
            }
        });

        btn_CreateFile.setText("Create New File");
        btn_CreateFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateFileActionPerformed(evt);
            }
        });

        jLabel1.setText("File Password:");

        btn_DeleteSelectedFile.setText("Delete Selected File");
        btn_DeleteSelectedFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteSelectedFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_UpdateFile, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_Login, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_Username)
                            .addComponent(lbl_Password))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Username, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(tf_Password, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tf_FilePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_CreateFile, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_DeleteSelectedFile, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Username))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Password))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Login)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_FilePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_UpdateFile)
                        .addGap(14, 14, 14)
                        .addComponent(btn_DeleteSelectedFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(btn_CreateFile)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CreateFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateFileActionPerformed
        
        createFileWindow cfw = new createFileWindow();
        cfw.setVisible(true);
    }//GEN-LAST:event_btn_CreateFileActionPerformed

    private void btn_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LoginActionPerformed

        //check if the text field boxes are empty
        if(tf_Username.getText().equals(("")) ||
                tf_Password.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No username or password given");
        }
        else
        {
            currentUser = tf_Username.getText();
            currentPass = tf_Password.getText();

            //unneccessary message dialog below, re-enable if you'd like
            //JOptionPane.showMessageDialog(null, "User: " + currentUser + " logged in with Password: " + currentPass);

            //COMPLETED: LOGGING INTO GOOGLE CODE HERE
            try
            {
                service.setUserCredentials(currentUser, currentPass);
            } 
            catch (AuthenticationException e)
            {
                System.out.println("Invalid Username/Password Combination");
            }
            
            /*****************************************/

            //COMPLETED: ADD FILLING OF TABLE HERE

            try
            {
                DocumentListFeed feed = null;

                URL url = new URL("https://docs.google.com/feeds/default/private/full/-/document");
                feed = service.getFeed(url, DocumentListFeed.class);

                for (DocumentListEntry entry : feed.getEntries())
                {
                    //System.out.println(entry.getTitle().getPlainText() + " || " + entry.getResourceId());
                    dtm.addRow(new Object[] {entry.getTitle().getPlainText(), entry.getResourceId()});
                }
            }
            catch (MalformedURLException m) {} catch (ServiceException s) {} catch (IOException i) {}

            //utilize dtm.addRow(new Object[] {document[i], documentPass[i]})
        }
    }//GEN-LAST:event_btn_LoginActionPerformed

    private void btn_UpdateFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateFileActionPerformed

        //make sure we were given a password
        if(tf_FilePassword.getText().equals(("")))
        {
            JOptionPane.showMessageDialog(null, "No file password given");
        }
        else
        {
            //figure out which row is selected
            int indexSelected = tableView.getSelectedRow();

            if (indexSelected == -1) //no row has been selected
            {
                JOptionPane.showMessageDialog(null, "No row selected, please select a row");
            }
            else
            {
                //set up strings for later use
                String filePass = tf_FilePassword.getText();
                String fileName = tableView.getValueAt(indexSelected, 0).toString();
                String fileID = tableView.getValueAt(indexSelected, 1).toString();
                String fileText = "";

                //COMPLETED: DOWNLOAD FILE FROM GOOGLE
                String downloadURL = ("https://docs.google.com/feeds/download/documents/Export?exportFormat=txt&id="+
                        fileID.substring(9)); //substring(8) removes "document:"
                MediaContent mc = new MediaContent();
                mc.setUri(downloadURL);
                try
                {
                    MediaSource ms = service.getMedia(mc);

                    InputStream inStream = null;
                    FileOutputStream outStream = null;
                    try
                    {
                        inStream = ms.getInputStream();
                        outStream = new FileOutputStream(fileName + ".txt");

                        int c;
                        while ((c = inStream.read()) != -1)
                        {
                            outStream.write(c);
                        }
                    }
                    finally
                    {
                        if (inStream != null)
                        {
                            inStream.close();
                        }
                        if (outStream != null)
                        {
                            outStream.flush();
                            outStream.close();
                        }
                    }
                }catch (MalformedURLException m) {} catch (ServiceException s) {} catch (IOException i) {}

                /********************************/

                //TO DO: CONFIRM HMAC IS CORRECT

                //there will be an IF-ELSE statement here.
                //if hmac incorrect, display an error
                //else, continue with business as usual

                /********************************/

                //TO DO: DECRYPT FILE USING filePass AND fileID

                //reading of the file's content
                try
                {
                    BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
                    String str;
                    while ((str = br.readLine()) != null)
                    {
                        fileText += (str);
                    }
                    br.close();
                } catch (IOException e) {}


                /********************************/
                //Open Update File Window for Editing
                updateFileWindow ufw = new updateFileWindow();
                ufw.setVisible(true);
                ufw.setFileProperties(fileName, fileText);
            }
        }
    }//GEN-LAST:event_btn_UpdateFileActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void btn_DeleteSelectedFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteSelectedFileActionPerformed
        if(tf_FilePassword.getText().equals(("")))
        {
            JOptionPane.showMessageDialog(null, "No file password given");
        }
        else
        {
            //figure out which row is selected
            int indexSelected = tableView.getSelectedRow();

            if (indexSelected == -1) //no row has been selected
            {
                JOptionPane.showMessageDialog(null, "No row selected, please select a row");
            }
            else
            {
                //set up strings for later use
                String filePass = tf_FilePassword.getText();
                String fileName = tableView.getValueAt(indexSelected, 0).toString();
                String fileID = tableView.getValueAt(indexSelected, 1).toString();

                //TO DO: CONFIRM HMAC IS CORRECT

                /********************************/
                
                //COMPLETED: DELETE FILE FROM GOOGLE
                try
                {
                    URL delURL = new URL("https://docs.google.com/feeds/default/private/full/" + fileID);

                    service.delete(delURL, service.getEntry(delURL, DocumentListEntry.class).getEtag());
                }
                catch (MalformedURLException m) {} catch (ServiceException s) {} catch (IOException i) {}
            }
        }
    }//GEN-LAST:event_btn_DeleteSelectedFileActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindowOld().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CreateFile;
    private javax.swing.JButton btn_DeleteSelectedFile;
    private javax.swing.JButton btn_Login;
    private javax.swing.JButton btn_UpdateFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl_Password;
    private javax.swing.JLabel lbl_Username;
    private javax.swing.JTable tableView;
    private javax.swing.JTextField tf_FilePassword;
    private javax.swing.JTextField tf_Password;
    private javax.swing.JTextField tf_Username;
    // End of variables declaration//GEN-END:variables

}
