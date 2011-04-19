//IMPORTS EVERYWHERE

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.DocumentEntry;
import com.google.gdata.data.docs.DocumentListEntry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.Color;
import java.net.URL;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class updateFileWindow extends javax.swing.JFrame {

    String fileName = "";
    String fileText = "";
    DocsService service;
    String fileID;
    /** Creates new form updateFileWindow */
    public updateFileWindow(DocsService s) {
        initComponents();
        service  = s;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        tf_FilePassword = new javax.swing.JTextField();
        btn_UpdateFile = new javax.swing.JButton();
        tf_FileContent = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbox_Password = new java.awt.Checkbox();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel3.setText("File Password:");

        btn_UpdateFile.setText("Update File");
        btn_UpdateFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateFileActionPerformed(evt);
            }
        });

        jLabel1.setText("File Content");

        cbox_Password.setLabel("Change File's Password?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(tf_FileContent, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbox_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_FilePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_UpdateFile, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_FileContent, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(cbox_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_FilePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(btn_UpdateFile)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_UpdateFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateFileActionPerformed

        //make sure all boxes have been filled out
        if (tf_FileContent.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter the content of the file");
        } 
        else if (tf_FilePassword.getText().equals("") && cbox_Password.getState() == true)
        {
            JOptionPane.showMessageDialog(null, "Please enter the new password of the file");
        }
        else if (cbox_Password.getState() == true)
        {
            //TO DO: RE-ENCRYPT FILE WITH THIS NEW PASSWORD
            String newPass = tf_FilePassword.getText();
        }
        else //update the file now
        {
            //recall that fileName is set when update button is pressed in Main Window
            String fileContent = tf_FileContent.getText();
            String filePassword = tf_FilePassword.getText();

            //TODO: Verify that user knows password for file deletion

            //Delete old version from gdocs
            try{
            URL delURL = new URL("https://docs.google.com/feeds/default/private/full/" + fileID);

            service.delete(delURL, service.getEntry(delURL, DocumentListEntry.class).getEtag());
            }catch(Exception e){
                System.err.println("Error deleting file from gdocs");
            }
            /***********************************/

            //upload file in google docs
            try{
                Random rand = new Random();
                File file;

                do{
                    String fname = "TMP"+rand.nextInt(Integer.MAX_VALUE)+".txt";
                    file = new File(fname);
                }while(file.isFile());

                System.out.println("tmp file " + file.getName());
                file.createNewFile();
                System.out.println("File created");

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileContent.getBytes());
                fos.close();
                System.out.println("Contents written");

                String mimeType = DocumentListEntry.MediaType.fromFileName(file.getName()).getMimeType();
                DocumentEntry newDocument = new DocumentEntry();
                newDocument.setFile(file, mimeType);
                newDocument.setTitle(new PlainTextConstruct(fileName));

                service.insert(new URL("https://docs.google.com/feeds/default/private/full"), newDocument);
                file.delete();
            }catch(Exception e){
                System.err.println("Error writing to file");
            }

        this.dispose();


        }
}//GEN-LAST:event_btn_UpdateFileActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new updateFileWindow(null).setVisible(true);
            }
        });
    }


     public void setFileProperties(String fname, String ftext, String fid)
    {
        fileName = fname;
        fileText = ftext;
        tf_FileContent.setText(fileText);
        fileID = fid;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_UpdateFile;
    private java.awt.Checkbox cbox_Password;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tf_FileContent;
    private javax.swing.JTextField tf_FilePassword;
    // End of variables declaration//GEN-END:variables

}
