package com.september.forms;

import com.september.Entry;
import com.september.God;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DatabasePage {
    private JButton addNewButton;
    public JPanel rootPanel;
    private JTable table1;
    private JButton exitButton;
    private JButton saveButton;
    private JButton settingsButton;
    private JButton backButton;

    private JFrame parent;

    DatabasePage(JFrame parent){
        System.out.print(God.base.base.size());
        //Vector<String> a = new Vector<>(God.base.base.size());
        //Entry[] a = (Entry[])God.base.base.toArray();
        String[][] logins = new String[God.base.base.size()][3];

        for(int i = 0; i < God.base.base.size(); i++){
            logins[i][0] = God.base.get(i).title;
            logins[i][1] = God.base.get(i).login;
            logins[i][2] = "*".repeat(God.base.get(i).password.length());
            System.out.print(God.base.get(i).login);
        }
        String[] title = {"Title", "Login", "Password"};
        table1.setFillsViewportHeight(true);

        //JScrollPane scrollPane = new JScrollPane(myTable);
        table1.setModel(new DefaultTableModel(logins, title){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });
        table1.setSelectionMode(0);

        table1.getSelectionModel().setSelectionInterval(0, 0);
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                System.out.println("SASAI");
                int[] selectedRow = table1.getSelectedRows();
                System.out.println(selectedRow[0]);
                table1.getSelectionModel().setSelectionInterval(selectedRow[0], selectedRow[0]);
                parent.setContentPane(new EntryPage(parent,selectedRow[0]).rootPanel);
                parent.getContentPane().repaint();
                parent.getContentPane().revalidate();
            }
        });
        exitButton.addActionListener(e -> {
            parent.setContentPane(new StartPage(parent).rootPanel);
            parent.getContentPane().repaint();
            parent.getContentPane().revalidate();
        });
        addNewButton.addActionListener(e -> {
            God.base.add(new Entry("","",""));
            parent.setContentPane(new EntryPage(parent,God.base.base.size() - 1).rootPanel);
            parent.getContentPane().repaint();
            parent.getContentPane().revalidate();
        });
        parent.repaint();
        parent.revalidate();
    }
}
