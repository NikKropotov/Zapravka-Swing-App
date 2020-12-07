package org.orgname.app.ui;

import org.orgname.app.database.entity.FuelEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.util.BaseForm;

import javax.swing.*;
import java.awt.*;

public class FuelMark extends BaseForm {
    private final UserEntity user;
    private final String rowValues;
    private JPanel mainPanel;
    private JPanel navMenu;
    private JButton fuelButton;
    private JButton gasButton;
    private JButton firmButton;
    private JButton accountButton;
    private JPanel mainContent;
    private JButton backButton;
    private JTextArea fuelArea;
    private JTextField amountField;
    private JTable fuelTable;
    private String fuel_name;
    private String gas_name;
    private String price;

    public FuelMark(String rowValues, UserEntity user) {
        this.user = user;
        this.rowValues = rowValues;
        setContentPane(mainPanel);
        initButtton();
        initProperties();
//        initObject();
//        initTable();
//        loadTableData();
        fuelArea.setText(String.valueOf(rowValues));
        System.out.println(rowValues);
        setVisible(true);
    }

//    private void initObject() {
//        System.identityHashCode(rowValues);
//        for (Object s : getRowValues()){
//            s.toString();
//        }

//        Field myField = rowValues.getClass().getDeclaredField();
//        myField.setAccessible(true);
//        return (String) myField.get(rowValues);

//        for(Object x: rowValues) {
//                x = fuel_name;
//                System.out.println(x+"\n");
//        }
//    }

    private void initButtton() {
        accountButton.addActionListener(e -> {
            dispose();
            new AccountInfo(user);
        });
        gasButton.addActionListener(e -> {
            dispose();
            new SatationForm(user);
        });
        backButton.addActionListener(e -> {
            dispose();
            new MainForm(user);
        });
    }

    private void initProperties() {
        fuelButton.setBorder(null);
        gasButton.setBorder(null);
        firmButton.setBorder(null);
        accountButton.setBorder(null);

        gasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gasButton.setBorder(null);
                gasButton.setBackground(new Color(76, 75, 72));
                gasButton.setText("<html><font color='#209981'>Заправки</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                gasButton.setBorder(null);
                gasButton.setBackground(new Color(54, 53, 51));
                gasButton.setText("<html><font color='#847F81'>Заправки</font></html>");
            }
        });

        firmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                firmButton.setBorder(null);
                firmButton.setBackground(new Color(76, 75, 72));
                firmButton.setText("<html><font color='#209981'>Фирмы</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                firmButton.setBorder(null);
                firmButton.setBackground(new Color(54, 53, 51));
                firmButton.setText("<html><font color='#847F81'>Фирмы</font></html>");
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 900;
    }

    @Override
    public int getFormHeight() {
        return 600;
    }
}
