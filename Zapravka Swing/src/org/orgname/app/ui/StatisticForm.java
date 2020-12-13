package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.DailySaleEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.DailySaleEntityManager;
import org.orgname.app.database.manager.FuelEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class StatisticForm extends BaseForm {
    private final FuelEntityManager fuelEntityManager = new FuelEntityManager(Application.getInstance().getDatabase());
    private final DailySaleEntityManager dailySaleEntityManager = new DailySaleEntityManager(Application.getInstance().getDatabase());

    private final UserEntity user;
    private DefaultTableModel statisTableModel;

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
        initUserType();
        initButtton();
        initProperties();
        initTable();
        loadTableData();
        initComboBox();

        setVisible(true);
    }

    private void initUserType() {
        if (user.getAccount_type().equals("Admin")) {
            statisticButton.setVisible(true);
        }
    }

    private void initComboBox() {
        comboBox.addItem("Топ марок топлива по продажам");
        comboBox.addItem("Топ заправок");
    }

    private void initTable() {
        statTable.getTableHeader().setReorderingAllowed(false);

        statisTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //получение записи по двойному клику
        statTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);

                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    Object[] rowValues = new Object[statisTableModel.getColumnCount()];
                    for (int i = 0; i < statisTableModel.getColumnCount(); i++) {
                        rowValues[i] = statisTableModel.getValueAt(row, i);
                    }
                    System.out.println(Arrays.toString(rowValues));
                }
            }
        });

        statTable.setModel(statisTableModel);
        statisTableModel.addColumn("Топливо");
        statisTableModel.addColumn("Число продаж");

        statTable.setAutoCreateRowSorter(true);
    }

    private void loadTableData() {
        try {
            List<DailySaleEntity> sales = DailySaleEntityManager.getStatisticTopFuels();
            for (DailySaleEntity s : sales) {
                statisTableModel.addRow(new Object[]{
                        s.getDaily_fuel_type(),
                        s.getMax_fuel_type(),
                });
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DialogUtil.showError("Не удалось загрузить данные");
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
            new MainForm(user);
        });
        fuelButton.addActionListener(e -> {
            dispose();
            new MainForm(user);
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
}

