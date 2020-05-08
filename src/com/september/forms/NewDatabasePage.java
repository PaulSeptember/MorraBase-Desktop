package com.september.forms;

import com.september.DataBase;
import com.september.God;
import com.september.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;

public class NewDatabasePage {
    public JPanel rootPanel;
    private JButton button1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton cancelButton;

    private JFrame parent;
    NewDatabasePage(JFrame parent) {
        this.parent = parent;
        parent.getContentPane().repaint();
        parent.revalidate();

        cancelButton.addActionListener(e -> {
            parent.setContentPane(new StartPage(parent).rootPanel);
            parent.repaint();
            parent.revalidate();
        });

        button1.addActionListener(e -> {
            String name = textField1.getText();
            if (! new String(passwordField1.getPassword()).equals(new String(passwordField2.getPassword())))
                return;
            if (name.isEmpty())
                return;
            else {
                JFileChooser j = new JFileChooser("d:");

                j.setSelectedFile(new File(name ));//+ ".morra"));
                //j.showSaveDialog(parent);

                j.addChoosableFileFilter(new FileNameExtensionFilter("Morra database(.morra)", "morra"));
                j.setAcceptAllFileFilterUsed(false);
                int ret = j.showSaveDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter fw = new FileWriter(j.getSelectedFile().getAbsolutePath() + ".morra");
                        fw.close();
                        God.filename = j.getSelectedFile().getAbsolutePath() + ".morra";
                        DataBase b = new DataBase();
                        b.password = new String(passwordField1.getPassword());
                        God.base = b;
                        Utils.saveBase();
                        parent.setContentPane(new DatabasePage(parent).rootPanel);
                        parent.getContentPane().repaint();
                        parent.getContentPane().revalidate();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            }
        });
    }
}
