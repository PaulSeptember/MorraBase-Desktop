package com.september;

import com.september.forms.DatabasePage;
import com.september.forms.StartPage;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MorraPas");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(new StartPage(frame).rootPanel);
        frame.repaint();
        frame.revalidate();
    }


}
