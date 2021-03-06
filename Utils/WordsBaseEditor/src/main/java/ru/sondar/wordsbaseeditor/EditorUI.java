/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.wordsbaseeditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.parser.DOMParser;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;
import ru.sondar.documentmodel.objectmodel.WordBase;

/**
 *
 * @author GlebZemnieks
 */
public class EditorUI extends javax.swing.JFrame {

    SDDocument document;
    SDWordsBasePart wordsBase;
    DOMParser parser;
    String fileName;

    /**
     * Creates new form EditorUI
     *
     * @param fileToOpen
     */
    public EditorUI(String fileToOpen) {
        initComponents();
        document = new SDDocument();
        try {
            document.loadDocument(fileToOpen);
            wordsBase = document.getWordsBasePart();
            fileName = fileToOpen;
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(EditorUI.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (ObjectStructureException ex) {
            Logger.getLogger(EditorUI.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        updateUI();
    }

    int row = 0;
    int filter = 0;
    int word = 0;

    public final void updateUI() {
        try {
            refresh();
        } catch (Exception error) {
            Error(error);
        }
    }

    private void refresh() {
        Object[][] BaseList = new Object[wordsBase.size()][1];
        for (int i = 0; i < wordsBase.size(); i++) {
            BaseList[i][0] = wordsBase.getBaseNames().toArray()[i];
        }
        if (jTable1.getSelectedRow() != -1) {
            row = jTable1.getSelectedRow();
            filter = 0;
            word = 0;
        }
        String rowName = (String) (wordsBase.getBaseNames().toArray()[row]);
        this.jFormattedTextField1.setText(rowName);

        WordBase base = wordsBase.getList(row);
        Set<String> temp = base.getFilterNames();
        Object[][] fitlers = new Object[temp.size()][1];
        for (int i = 0; i < base.getFilterNames().size(); i++) {
            fitlers[i][0] = temp.toArray()[i];
        }
        if (jTable2.getSelectedRow() != -1) {
            filter = jTable2.getSelectedRow();
            word = 0;
        }
        String filterName = (String) ((base.getFilterNames().toArray())[filter]);
        this.jFormattedTextField2.setText(filterName);
        ArrayList<String> temp2 = base.getListRaw(filterName);
        Object[][] words = new Object[temp2.size()][1];
        for (int i = 0; i < temp2.size(); i++) {
            words[i][0] = temp2.toArray()[i];
        }
        if (jTable3.getSelectedRow() != -1) {
            word = jTable3.getSelectedRow();
        }
        try {
            this.jFormattedTextField3.setText(temp2.get(word));
        } catch (IndexOutOfBoundsException er) {
        }
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                words,
                new String[]{
                    "words"
                }
        ));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                fitlers,
                new String[]{
                    "filter"
                }
        ));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                BaseList,
                new String[]{
                    "base"
                }
        ));
        /*
        Object[][] words = new Object[wordsBase.getList()][1];
        for (int i = 0; i < words.getListCount(); i++) {
            words[i][0] = words.getBaseNames().toArray()[i];
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                words,
                new String[]{
                    "hello"
                }
       ));
         */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jFormattedTextField1.setText("GroupName");
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jFormattedTextField2.setText("jFormattedTextField2");
        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jFormattedTextField3.setText("jFormattedTextField3");

        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("-");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Save");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("~");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addComponent(jFormattedTextField3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            if (!"".equals(jFormattedTextField2.getText())) {
                wordsBase.getList(row).addFilter(jFormattedTextField2.getText());
                filter = 0;
                word = 0;
            }
            updateUI();
        } catch (Exception error) {
            Error(error);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        updateUI();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        WordBase base = wordsBase.getList(row);
        String filterName = (String) (base.getFilterNames().toArray()[this.filter]);
        if (!"".equals(jFormattedTextField3)) {
            base.add(filterName, jFormattedTextField3.getText());
        }
        updateUI();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        updateUI();
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            FileModuleWriteThread fileModule = new FileModuleWriteThread(fileName, false);
            document.saveDocument(fileModule);
            fileModule.close();
            document = new SDDocument();
            document.loadDocument(fileName);
            row = 0;
            filter = 0;
            updateUI();
        } catch (Exception error) {
            Error(error);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            WordBase base = wordsBase.getList(row);
            String filterName = (String) (base.getFilterNames().toArray()[filter]);
            if (!"".equals(jFormattedTextField3)) {
                base.removeItem(filterName, base.getListRaw(filterName).get(word));
                base.add(filterName, jFormattedTextField3.getText());
            }
            word = 0;
            updateUI();
        } catch (Exception error) {
            Error(error);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        updateUI();
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            wordsBase.addNewBase(jFormattedTextField1.getText(), new WordBase());
            jFormattedTextField1.setText("");
            row = 0;
            filter = 0;
            word = 0;
            updateUI();
        } catch (Exception error) {
            Error(error);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String name = (String) (wordsBase.getBaseNames().toArray()[row]);
            wordsBase.remove(name);
            row = 0;
            filter = 0;
            word = 0;
            updateUI();
        } catch (Exception error) {
            Error(error);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            if (!"".equals(jFormattedTextField2.getText())) {
                wordsBase.getList(row).removeFilter(jFormattedTextField2.getText());
                filter = 0;
                word = 0;
            }
            updateUI();
        } catch (Exception error) {
            Error(error);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            WordBase base = wordsBase.getList(row);
            String filterName = (String) (base.getFilterNames().toArray()[filter]);
            if (!"".equals(jFormattedTextField3)) {
                base.removeItem(filterName, jFormattedTextField3.getText());
            }
            word = 0;
            updateUI();
        } catch (Exception error) {
            Error(error);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            if (args.length > 0) {
                File file = new File(args[0]);
                if (file.exists()) {
                    new EditorUI(args[0]).setVisible(true);
                } else {
                    try {
                        file.createNewFile();
                        SDWordsBasePart base1 = new SDWordsBasePart();
                        FileModuleWriteThread write = new FileModuleWriteThread(args[0], false);
                        base1.printObjectToXML(write);
                        write.close();
                        new EditorUI(args[0]).setVisible(true);

                    } catch (IOException ex) {
                        Logger.getLogger(EditorUI.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                new EditorUI("C:\\test\\test.txt").setVisible(true);
            }
        });
    }

    public void Error(Exception error) {
        new Error(error).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
