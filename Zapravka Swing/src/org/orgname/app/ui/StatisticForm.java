package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.DailySaleEntity;
import org.orgname.app.database.entity.FuelEntity;
import org.orgname.app.database.entity.StationEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.DailySaleEntityManager;
import org.orgname.app.database.manager.FuelEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.CustomTableModel;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatisticForm extends BaseForm {
    private final FuelEntityManager fuelEntityManager = new FuelEntityManager(Application.getInstance().getDatabase());
    private final DailySaleEntityManager dailySaleEntityManager = new DailySaleEntityManager(Application.getInstance().getDatabase());

    private final UserEntity user;
    //    private DefaultTableModel statisTableModel;
    private CustomTableModel<DailySaleEntity> statisTableModel;

    private JPanel mainPanel;
    private JButton fuelButton;
    private JButton gasButton;
    private JButton firmButton;
    private JButton accountButton;
    private JPanel mainContent;
    private JLabel mainLabe;
    private JScrollPane tableScrollPane;
    private JTable statTable;
    private JComboBox<String> comboBox;
    private JButton backButton;
    private JPanel navMenu;
    private JLabel logoLabel;
    private JButton statisticButton;

    public StatisticForm(UserEntity user) {
        this.user = user;
        setContentPane(mainPanel);
        initComboBox();
        initUserType();
        initButtton();
        initProperties();
        initTable();
//        loadTableData();
        setVisible(true);
    }

    private void initUserType() {
        if (user.getAccount_type().equals("Admin")) {
            statisticButton.setVisible(true);
        }
    }

    private void initButtton() {
        accountButton.addActionListener(e -> {
            dispose();
            new AccountInfo(user);
        });
        gasButton.addActionListener(e -> {
            dispose();
            new StationForm(user);
        });
        firmButton.addActionListener(e -> {
            dispose();
            new FirmForm(user);
        });
        fuelButton.addActionListener(e -> {
            dispose();
            new MainForm(user);
        });
        backButton.addActionListener(e -> {
            dispose();
            new MainForm(user);
        });
        comboBox.addActionListener(e -> {
//            loadTableDataByDate();
            initTable();
            statisTableModel.fireTableDataChanged();

        });
    }

    private void initComboBox() {
        comboBox.addItem("Январь");
        comboBox.addItem("Февраль");
        comboBox.addItem("Март");
        comboBox.addItem("Апрель");
        comboBox.addItem("Май");
        comboBox.addItem("Июнь");
        comboBox.addItem("Июль");
        comboBox.addItem("Август");
        comboBox.addItem("Сентябрь");
        comboBox.addItem("Октябрь");
        comboBox.addItem("Ноябрь");
        comboBox.addItem("Декабрь");
    }

    private void initTable() {
        statTable.getTableHeader().setReorderingAllowed(false);
        int m = 0;
        if (comboBox.getSelectedItem() == "Январь") {
            m = 1;
        }
        if (comboBox.getSelectedItem() == "Февраль") {
            m = 2;
        }
        if (comboBox.getSelectedItem() == "Март") {
            m = 3;
        }
        if (comboBox.getSelectedItem() == "Апрель") {
            m = 4;
        }
        if (comboBox.getSelectedItem() == "Май") {
            m = 5;
        }
        if (comboBox.getSelectedItem() == "Июнь") {
            m = 6;
        }
        if (comboBox.getSelectedItem() == "Июль") {
            m = 7;
        }
        if (comboBox.getSelectedItem() == "Август") {
            m = 8;
        }
        if (comboBox.getSelectedItem() == "Сентябрь") {
            m = 9;
        }
        if (comboBox.getSelectedItem() == "Октябрь") {
            m = 10;
        }
        if (comboBox.getSelectedItem() == "Ноябрь") {
            m = 11;
        }
        if (comboBox.getSelectedItem() == "Декабрь") {
            m = 12;
        }
        try {
            statisTableModel = new CustomTableModel<>(
                    DailySaleEntity.class,
                    DailySaleEntityManager.getStatisticTopFuelsByDate(m),
                    new String[]{
                            "Топливо",
                            "Число продаж"
                    }
            );
            statTable.setModel(statisTableModel);

            statTable.getColumnModel().getColumn(1).setHeaderValue("Топливо");
            statTable.getColumnModel().getColumn(7).setHeaderValue("Число продаж");

            statTable.getColumnModel().getColumn(0).setMinWidth(0);
            statTable.getColumnModel().getColumn(0).setMaxWidth(0);
            statTable.getColumnModel().getColumn(0).setWidth(0);
            statTable.getColumnModel().getColumn(2).setMinWidth(0);
            statTable.getColumnModel().getColumn(2).setMaxWidth(0);
            statTable.getColumnModel().getColumn(2).setWidth(0);
            statTable.getColumnModel().getColumn(3).setMinWidth(0);
            statTable.getColumnModel().getColumn(3).setMaxWidth(0);
            statTable.getColumnModel().getColumn(3).setWidth(0);
            statTable.getColumnModel().getColumn(4).setMinWidth(0);
            statTable.getColumnModel().getColumn(4).setMaxWidth(0);
            statTable.getColumnModel().getColumn(4).setWidth(0);
            statTable.getColumnModel().getColumn(5).setMinWidth(0);
            statTable.getColumnModel().getColumn(5).setMaxWidth(0);
            statTable.getColumnModel().getColumn(5).setWidth(0);
            statTable.getColumnModel().getColumn(6).setMinWidth(0);
            statTable.getColumnModel().getColumn(6).setMaxWidth(0);
            statTable.getColumnModel().getColumn(6).setWidth(0);
            statTable.setAutoCreateRowSorter(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DialogUtil.showError("Не удалось загрузить данные");
        }
    }


//    private void initTable() {
//        statTable.getTableHeader().setReorderingAllowed(false);
//
//        statisTableModel = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        //получение записи по двойному клику
//        statTable.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent mouseEvent) {
//                JTable table = (JTable) mouseEvent.getSource();
//                Point point = mouseEvent.getPoint();
//                int row = table.rowAtPoint(point);
//
//                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
//
//                    Object[] rowValues = new Object[statisTableModel.getColumnCount()];
//                    for (int i = 0; i < statisTableModel.getColumnCount(); i++) {
//                        rowValues[i] = statisTableModel.getValueAt(row, i);
//                    }
//                    System.out.println(Arrays.toString(rowValues));
//                }
//            }
//        });
//
//        statTable.setModel(statisTableModel);
//        statisTableModel.addColumn("Топливо");
//        statisTableModel.addColumn("Число продаж");
//
//        statTable.setAutoCreateRowSorter(true);
//    }

//    private void loadTableData() {
//        try {
//            List<DailySaleEntity> sales = DailySaleEntityManager.getStatisticTopFuels();
//            for (DailySaleEntity s : sales) {
//                statisTableModel.addRow(new Object[]{
//                        s.getDaily_fuel_type(),
//                        s.getMax_fuel_type(),
//                });
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            DialogUtil.showError("Не удалось загрузить данные");
//        }
//    }

//    private void loadTableDataByDate() {
//        int m = 0;
//        if (comboBox.getSelectedItem() == "Январь") {
//            m = 1;
//        }
//        if (comboBox.getSelectedItem() == "Февраль") {
//            m = 2;
//        }
//        if (comboBox.getSelectedItem() == "Март") {
//            m = 3;
//        }
//        if (comboBox.getSelectedItem() == "Апрель") {
//            m = 4;
//        }
//        if (comboBox.getSelectedItem() == "Май") {
//            m = 5;
//        }
//        if (comboBox.getSelectedItem() == "Июнь") {
//            m = 6;
//        }
//        if (comboBox.getSelectedItem() == "Июль") {
//            m = 7;
//        }
//        if (comboBox.getSelectedItem() == "Август") {
//            m = 8;
//        }
//        if (comboBox.getSelectedItem() == "Сентябрь") {
//            m = 9;
//        }
//        if (comboBox.getSelectedItem() == "Октябрь") {
//            m = 10;
//        }
//        if (comboBox.getSelectedItem() == "Ноябрь") {
//            m = 11;
//        }
//        if (comboBox.getSelectedItem() == "Декабрь") {
//            m = 12;
//        }
//
//        try {
//            List<DailySaleEntity> sales = DailySaleEntityManager.getStatisticTopFuelsByDate(m);
//            for (DailySaleEntity s : sales) {
//                statisTableModel.addRow(new Object[]{
//                        s.getDaily_fuel_type(),
//                        s.getMax_fuel_type(),
//                });
//            }
//            statisTableModel.fireTableDataChanged();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            DialogUtil.showError("Не удалось загрузить данные");
//        }
//    }

    private void initProperties() {
        fuelButton.setBorder(null);
        gasButton.setBorder(null);
        firmButton.setBorder(null);
        accountButton.setBorder(null);
        backButton.setBorder(null);
        statisticButton.setBorder(null);

        backButton.setBackground(new Color(39, 193, 167));
        backButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        backButton.setBorderPainted(false);

        backButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(32, 153, 129));
                backButton.setText("<html><font color='white'>Назад</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(39, 193, 167));
            }
        });

        fuelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fuelButton.setBorder(null);
                fuelButton.setText("<html><font color='#2EE5C6'>Топливо</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                fuelButton.setBorder(null);
                fuelButton.setText("<html><font color='#847F81'>Топливо</font></html>");
            }
        });

        gasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gasButton.setBorder(null);
                gasButton.setText("<html><font color='#2EE5C6'>Заправки</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                gasButton.setBorder(null);
                gasButton.setText("<html><font color='#847F81'>Заправки</font></html>");
            }
        });

        firmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                firmButton.setBorder(null);
                firmButton.setText("<html><font color='#2EE5C6'>Фирмы</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                firmButton.setBorder(null);
                firmButton.setText("<html><font color='#847F81'>Фирмы</font></html>");
            }
        });

        accountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accountButton.setBorder(null);
                accountButton.setText("<html><font color='#2EE5C6'>Аккаунт</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                accountButton.setBorder(null);
                accountButton.setText("<html><font color=white>Аккаунт</font></html>");
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

//    public DefaultTableModel getStatisTableModel() {
//        return statisTableModel;
//    }

    public CustomTableModel<DailySaleEntity> getTableModel() {
        return statisTableModel;
    }
}

