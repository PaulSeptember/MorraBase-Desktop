package com.september;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class NewBaseWindow{
    NewBaseWindow(Frame frame){
    }

    static void call(JFrame frame){
        Utils.clean(frame);

        JTextField fieldName = new JTextField(16);
        frame.getContentPane().add(fieldName);

        JTextField fieldPassword = new JTextField(16);
        frame.getContentPane().add(fieldPassword);

        JButton setName = new JButton("Set Name");
        setName.addActionListener(e -> {
            String name = fieldName.getText();
            if (name.isEmpty())
                return;
            else{
                JFileChooser j = new JFileChooser("d:");
                int ret = j.showSaveDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter fw = new FileWriter(j.getSelectedFile().getAbsolutePath() + ".morra");
                        fw.close();
                    }catch (Exception exc){
                        exc.printStackTrace();
                    }
                }
            }
            //BaseWindow.create(frame);
        });
        frame.getContentPane().add(setName);
        frame.repaint();
        frame.revalidate();
    }
}
