//IMPORTS EVERYWHERE

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.DocumentEntry;
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

import com.google.gdata.data.docs.DocumentListEntry;
import java.net.URL;


public class createFileWindow extends javax.swing.JFrame {
    DocsService service;


    /** Creates new form createFileWindow */
    public createFileWindow(DocsService s) {
        service = s;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tf_FileContent = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_FileName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_FilePassword = new javax.swing.JTextField();
        btn_AddFile = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("File Content");

        jLabel2.setText("File Name:");

        tf_FileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_FileNameActionPerformed(evt);
            }
        });

        jLabel3.setText("File Password:");

        btn_AddFile.setText("Add File");
        btn_AddFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_FileName))
                    .addComponent(jLabel1)
                    .addComponent(tf_FileContent, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_FilePassword))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btn_AddFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_FileContent, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_FilePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(66, 66, 66)
                .addComponent(btn_AddFile)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AddFileActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btn_AddFileActionPerformed

        //make sure all boxes have been filled out
        if(tf_FileName.getText().equals((""))) {
            JOptionPane.showMessageDialog(null, "Please enter the name of the file");
        } else if (tf_FileContent.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the content of the file");
        } else if (tf_FilePassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the password of the file");
        } else //get here only if everything has been filled out properly
        {
            String fileName = tf_FileName.getText() + ".txt";
            String fileContent = tf_FileContent.getText();
            String filePassword = tf_FilePassword.getText();
            File newFile = new File(fileName);
            //TO DO: ENCRYPT FILE PROPERLY (add hash/salt/etc)
            if (newFile.exists())
            {
                System.out.println("created file: " + fileName);
            }

            //creation of the cleartext file
            try
            {
                FileWriter fstream = new FileWriter(newFile);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(fileContent);
                out.close();
            }catch (IOException e) {System.out.println("IO Exception Hit, file" +
                    " may not have been created properly");}

            //calculate the CS and add HMAC + Salt to it
            CryptoStore newCS = Crypto.encryptAES(newFile, filePassword);
            newCS = Crypto.calcHMAC(newFile, filePassword, newCS);

            //replace old, non-HMAC+Salt file with new file
            Crypto.createFile(newFile, newCS);
            
            //TODO: modify constructor to pass in array of filenames that already exists
            //TODO: check if specified filename already exists
            //Delete old file(check if exists?)
            try
            {
                System.out.println("Contents written");

                String mimeType = DocumentListEntry.MediaType.fromFileName(newFile.getName()).getMimeType();
                DocumentEntry newDocument = new DocumentEntry();
                newDocument.setFile(newFile, mimeType);
                newDocument.setTitle(new PlainTextConstruct(fileName));

                service.insert(new URL("https://docs.google.com/feeds/default/private/full"), newDocument);
                newFile.delete();
            }
            catch(Exception e){System.err.println("Error writing to file");}
    
            this.dispose();
        }
}//GEN-LAST:event_btn_AddFileActionPerformed

    private void tf_FileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_FileNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tf_FileNameActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createFileWindow(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_AddFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tf_FileContent;
    private javax.swing.JTextField tf_FileName;
    private javax.swing.JTextField tf_FilePassword;
    // End of variables declaration//GEN-END:variables

}
