package com.september.forms;

import com.september.Entry;
import com.september.God;
import com.september.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import static com.september.God.base;

public class EntryPage {
    private JButton saveButton;
    public JPanel rootPanel;
    private JButton backButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel warning;
    private JButton wButton;
    private JButton copyButton;
    private boolean masked = true;


    public EntryPage(JFrame parent, int index){
        textField1.setText(base.get(index).title);
        textField2.setText(base.get(index).login);
        passwordField1.setText(base.get(index).password);
        passwordField2.setText(base.get(index).password);
        wButton.setIcon(new ImageIcon("images/eye.svg"));
        backButton.addActionListener(e -> {
            parent.setContentPane(new DatabasePage(parent).rootPanel);
            parent.getContentPane().repaint();
            parent.getContentPane().revalidate();
        });


        copyButton.addActionListener(e->{
            StringSelection stringSelection = new StringSelection(base.get(index).password);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });

        passwordField1.setEchoChar('*');
        passwordField2.setEchoChar('*');

        wButton.addActionListener(e ->{
            if (masked){
                passwordField1.setEchoChar((char)0);
                passwordField2.setEchoChar((char)0);
            }else{
                passwordField1.setEchoChar('*');
                passwordField2.setEchoChar('*');
            }
            masked = !masked;
        });

        saveButton.addActionListener(e -> {
            warning.setText("");
            String password1 = new String(passwordField1.getPassword());
            String password2 = new String(passwordField2.getPassword());

            if (!password1.equals(password2)){
                warning.setText("Passwords does not match!");
            }else{
                Entry temp = new Entry(textField1.getText(),textField2.getText(),password1);
                base.set(index, temp);
                Utils.saveBase();
                parent.setContentPane(new DatabasePage(parent).rootPanel);
                parent.getContentPane().repaint();
                parent.getContentPane().revalidate();
            }
        });

    }
}
