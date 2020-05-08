package com.september;

import com.september.forms.DatabasePage;
import com.september.forms.StartPage;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        db.password = "1234";
        db.add(new Entry("Pohui","sep1","123"));
        db.add(new Entry("Tozh poebat","september2","1234222"));
        db.add(new Entry("SASI","Kekus3","12341"));
        db.add(new Entry("vk.com","s4","1234"));
        db.add(new Entry("GF","sAsAi","123456"));
        System.out.print(db.getFile());

        JFrame frame = new JFrame("MorraPas");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(new StartPage(frame).rootPanel);
        //EnterWindow.create(frame);
        //Test.create(frame);
        frame.repaint();
        frame.revalidate();
    }


}
