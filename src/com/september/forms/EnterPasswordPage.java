package com.september.forms;

import com.september.DataBase;
import com.september.God;

import javax.swing.*;

public class EnterPasswordPage {
    private JPasswordField passwordField1;
    private JButton enterButton;
    private JButton cancelButton;
    public JPanel rootPanel;
    private JLabel nameLabel;
    private JLabel errorLabel;

    private JFrame parent;
    EnterPasswordPage(JFrame parent){
        this.parent = parent;
        nameLabel.setText("Opening database: " + God.filename);

        cancelButton.addActionListener(e -> {
            parent.setContentPane(new StartPage(parent).rootPanel);
            parent.getContentPane().repaint();
            parent.getContentPane().revalidate();
        });

        enterButton.addActionListener(e -> {
            String password = new String(passwordField1.getPassword());
            DataBase db = new DataBase(God.tryToOpen);
            db.password = password;
            System.out.println(db.tryToOpen());
            if (db.isOpened){
                God.base = db;
                //System.out.println(God.base.get(0).login);
                parent.setContentPane(new DatabasePage(parent).rootPanel);
                parent.getContentPane().repaint();
                parent.getContentPane().revalidate();
            }else{
                errorLabel.setText("Wrong password...");
            }
        });

    }
}
