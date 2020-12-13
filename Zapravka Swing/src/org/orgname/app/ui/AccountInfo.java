package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.DailySaleEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.DailySaleEntityManager;
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

public class AccountInfo extends BaseForm {
    private final DailySaleEntityManager dailySaleEntityManager = new DailySaleEntityManager(Application.getInstance().getDatabase());
    private DefaultTableModel histoyTableModel;
    private final UserEntity user;
    private JPanel mainPanel;
    private JPanel navMenu;
    private JButton fuelButton;
    private JButton gasButton;
    private JButton firmButton;
    private JButton backButton;
    private JButton exitButton;
    private JPanel mainContent;
    private JTextArea accountInfoArea;
    private JScrollPane tableScrollPane;
    private JTable historyTable;
    private JLabel marginLabel;
    private JLabel margin2Label;
    private JButton statisticButton;

    public AccountInfo(UserEntity user) {
        this.user = user;
        setContentPane(mainPanel);
        initUserType();
        initButtton();
        initProperties();
        setVisible(true);
        initTable();
        loadTableData();
        accountInfoArea.setEditable(false);
        initUserData();
        setVisible(true);

    }

    private void initUserType() {
        if (user.getAccount_type().equals("Admin")) {
            statisticButton.setVisible(true);
        }
    }

    public void initUserData() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ID: ").append(user.getId_client()).append("\n")
                .append("  Логин: ").append(user.getLogin()).append("\n")
                .append("  Пользователь: ").append(user.getClient_fio()).append("\n")
                .append("  Телефон: ").append(user.getClient_phone()).append("\n")
                .append("  Счёт: ").append(user.getClient_account_amount()).append("\n");

        accountInfoArea.setEditable(false);
        accountInfoArea.setText(sb.toString());
    }

    private void initButtton() {
        backButton.addActionListener(e -> {
            back();
        });
        exitButton.addActionListener(e -> {
            if (DialogUtil.showConfirm(this, "Вы точно хотите выйти из аккаунта?")) {
                dispose();
                new AuthontifForm();
            }
        });
        fuelButton.addActionListener(e -> {
            dispose();
            new MainForm(user);
        });
        gasButton.addActionListener(e -> {
            dispose();
            new StationForm(user);
        });
        firmButton.addActionListener(e -> {
            dispose();
            new FirmForm(user);
        });
        statisticButton.addActionListener(e -> {
            dispose();
            new StatisticForm(user);
        });
    }

    private void back() {
        dispose();
        new MainForm(user);
    }

    private void initTable() {
        historyTable.getTableHeader().setReorderingAllowed(false);

        histoyTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //получение записи по двойному клику
        historyTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);

                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    Object[] rowValues = new Object[histoyTableModel.getColumnCount()];
                    for (int i = 0; i < histoyTableModel.getColumnCount(); i++) {
                        rowValues[i] = histoyTableModel.getValueAt(row, i);
                    }
                    System.out.println(Arrays.toString(rowValues));
                }
            }
        });

        historyTable.setModel(histoyTableModel);
        histoyTableModel.addColumn("Дата и время");
        histoyTableModel.addColumn("Марка топлива");
        histoyTableModel.addColumn("Количество");
        histoyTableModel.addColumn("Счёт");

        historyTable.setAutoCreateRowSorter(true);
    }

    private void loadTableData() {
        try {
            List<DailySaleEntity> sales = DailySaleEntityManager.getHistorySale(user.getId_client());
            for (DailySaleEntity s : sales) {
                histoyTableModel.addRow(new Object[]{
                        s.getDaily_sale_date(),
                        s.getDaily_fuel_type(),
                        s.getDaily_fuel_amount(),
                        s.getSale_price()
                });
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DialogUtil.showError("Не удалось загрузить данные");
        }
    }

    private void initProperties() {
        fuelButton.setBorder(null);
        gasButton.setBorder(null);
        firmButton.setBorder(null);
        backButton.setBorder(null);
        statisticButton.setBorder(null);

        statisticButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                statisticButton.setBorder(null);
                statisticButton.setText("<html><font color='#2EE5C6'>Статистика</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                statisticButton.setBorder(null);
                statisticButton.setText("<html><font color='#847F81'>Статистика</font></html>");
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
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBorder(null);
                backButton.setText("<html><font color='#2EE5C6'>Назад</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBorder(null);
                backButton.setText("<html><font color=white>Назад</font></html>");
            }
        });
        exitButton.setBackground(new Color(39, 193, 167));
        exitButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        exitButton.setBorderPainted(false);

        exitButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        exitButton.setBorderPainted(false);
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(32, 153, 129));
                exitButton.setText("<html><font color='white'>Выход</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(39, 193, 167));
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
