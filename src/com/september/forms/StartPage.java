package com.september.forms;

//import com.september.FileTypeFilter;
import com.september.God;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StartPage {
    private JButton openButton;
    private JButton newButton;
    public JPanel rootPanel;

    private JFrame parent;
    public StartPage(JFrame parent){
        this.parent = parent;
        openButton.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser("D:");
            fileopen.addChoosableFileFilter(new FileNameExtensionFilter("Morra database(.morra)", "morra"));
            fileopen.setAcceptAllFileFilterUsed(false);
            int ret = fileopen.showDialog(null, "Открыть файл");
            //fileopen.addChoosableFileFilter(new FileTypeFilter(".morra","my db"));
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                try {
                    String content = Files.lines(Paths.get(file.toString())).reduce("", String::concat);
                    God.tryToOpen = content;
                    God.filename = file.toString();
                    System.out.print(content);
                    parent.setContentPane(new EnterPasswordPage(parent).rootPanel);
                    parent.getContentPane().repaint();
                    parent.getContentPane().revalidate();
                }catch (Exception exc){
                    exc.printStackTrace();
                }
                //System.out.println(file.toString());
            }
        });

        newButton.addActionListener(e -> {
            parent.setContentPane(new NewDatabasePage(parent).rootPanel);
            parent.getContentPane().repaint();
            parent.getContentPane().revalidate();
        });



    }
}
