package com.september.forms;

import com.september.Entry;
import com.september.God;
import com.september.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DatabasePage {
    private JButton addNewButton;
    public JPanel rootPanel;
    private JTable table1;
    private JButton exitButton;
    private JButton saveButton;
    private JButton settingsButton;
    private JButton backButton;

    private JFrame parent;

    class PopUpDemo extends JPopupMenu {

        JMenuItem editItem;
        JMenuItem deleteItem;
        public PopUpDemo() {
            editItem = new JMenuItem("Edit");
            add(editItem);
            deleteItem = new JMenuItem("Delete");
            add(deleteItem);
        }
    }

    public void revalidateTable(){
        String[][] logins = new String[God.base.base.size()][3];

        for(int i = 0; i < God.base.base.size(); i++){
            logins[i][0] = God.base.get(i).title;
            logins[i][1] = God.base.get(i).login;
            StringBuilder sb = new StringBuilder();
            for(int q = 0; q < God.base.get(i).password.length(); q++)
                sb.append("*");
            logins[i][2] = sb.toString();
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
    }

    DatabasePage(JFrame parent){
        System.out.print(God.base.base.size());
        //Vector<String> a = new Vector<>(God.base.base.size());
        //Entry[] a = (Entry[])God.base.base.toArray();
        String[][] logins = new String[God.base.base.size()][3];

        for(int i = 0; i < God.base.base.size(); i++){
            logins[i][0] = God.base.get(i).title;
            logins[i][1] = God.base.get(i).login;
            StringBuilder sb = new StringBuilder();
            for(int q =0; q < God.base.get(i).password.length(); q ++)
                sb.append("*");
            logins[i][2] = sb.toString();
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

        //table1.getSelectionModel().setSelectionInterval(0, 0);
        /*table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
        });*/

        table1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    System.out.println("Double tap on entry number : " + row);
                    int[] selectedRow = table1.getSelectedRows();
                    //table1.getSelectionModel().setSelectionInterval(selectedRow[0], selectedRow[0]);
                    parent.setContentPane(new EntryPage(parent,row).rootPanel);
                    parent.getContentPane().repaint();
                    parent.getContentPane().revalidate();
                }
                if (mouseEvent.getButton() != MouseEvent.BUTTON1 && table.getSelectedRow() != -1){
                    PopUpDemo menu = new PopUpDemo();
                    System.out.println("Right mouse clicked on row: " + row);
                    table1.setRowSelectionInterval(row,row);
                    MouseEvent e = mouseEvent;
                    menu.editItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.print("kek");
                            parent.setContentPane(new EntryPage(parent,row).rootPanel);
                            parent.getContentPane().repaint();
                            parent.getContentPane().revalidate();
                            revalidateTable();
                        }
                    });
                    menu.deleteItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.print("kek");
                            God.base.deleteEntry(table.getSelectedRow());
                            revalidateTable();
                        }
                    });
                    menu.show(e.getComponent(), e.getX(), e.getY());

                }
            }
        });
        saveButton.addActionListener(e ->{
            Utils.saveBase();
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
