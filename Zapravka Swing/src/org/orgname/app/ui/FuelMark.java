package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.FuelEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.FuelEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.CustomTableModel;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class FuelMark extends BaseForm {
    private final FuelEntityManager fuelEntityManager = new FuelEntityManager(Application.getInstance().getDatabase());
    private final UserEntity user;
    private final FuelEntity fuelEntity;
    private final int rowIndex;
    private CustomTableModel<FuelEntity> table2Model;
//    private DefaultTableModel table2Model;

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
    private JLabel priceLabel;
    private JLabel totalLabel;
    private JLabel scoreLabel;
    private JButton mathButton;
    private JScrollPane tableScrollPane;
    private JButton statisticButton;
    private JButton editFuelButton;
    private JButton deleteFuelButton;
    private String fuel_name;
    private String gas_name;
    private String price;

    public FuelMark(UserEntity user, FuelEntity fuelEntity, int rowIndex) {
        this.user = user;
        this.fuelEntity = fuelEntity;
        this.rowIndex = rowIndex;
        setContentPane(mainPanel);
        fuelArea.setText(String.valueOf(fuelEntity));
        initUserType();
        initScore();
        initButtton();
        initTable();
//        loadTableData();
        initProperties();
        System.out.println(fuelEntity.getGas_station_address());
        System.out.println(fuelEntity);
        setVisible(true);
    }

    private void initUserType() {
        if (user.getAccount_type().equals("Admin")) {
            statisticButton.setVisible(true);
            editFuelButton.setVisible(true);
            deleteFuelButton.setVisible(true);
        }
    }

    private void initScore() {
        priceLabel.setText(fuelEntity.getPrice_one_litr() + "₽");
        int price = fuelEntity.getPrice_one_litr();
        mathButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount < 0) {
                    DialogUtil.showError(this, "Количество введено некорректно");
                    return;
                }
                totalLabel.setText(price + "₽" + " * " + amount + "л");
                double score = price * amount;
                scoreLabel.setText(score + "₽");
            } catch (Exception i) {
                i.printStackTrace();
                DialogUtil.showError("Количество введено некорректно");
            }
        });
    }

    private void initTable() {
        fuelTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && fuelTable.getSelectedRow() != -1) {
                    int row = fuelTable.rowAtPoint(e.getPoint());
                    dispose();
                    new FuelMark(user, table2Model.getValues().get(row), row);
                }
            }
        });

        fuelTable.getTableHeader().setReorderingAllowed(false);
        try {
            table2Model = new CustomTableModel<>(
                    FuelEntity.class,
                    FuelEntityManager.getFuelByStation(fuelEntity.getGas_station_address()),
                    new String[]{
                            "Топливо",
                            "Заправка",
                            "Цена за литр"
                    }
            );
            fuelTable.setModel(table2Model);

            fuelTable.getColumnModel().getColumn(2).setHeaderValue("Топливо");
            fuelTable.getColumnModel().getColumn(4).setHeaderValue("Заправка");
            fuelTable.getColumnModel().getColumn(6).setHeaderValue("Цена за литр");

            fuelTable.getColumnModel().getColumn(0).setMinWidth(0);
            fuelTable.getColumnModel().getColumn(0).setMaxWidth(0);
            fuelTable.getColumnModel().getColumn(0).setWidth(0);
            fuelTable.getColumnModel().getColumn(1).setMinWidth(0);
            fuelTable.getColumnModel().getColumn(1).setMaxWidth(0);
            fuelTable.getColumnModel().getColumn(1).setWidth(0);
            fuelTable.getColumnModel().getColumn(3).setMinWidth(0);
            fuelTable.getColumnModel().getColumn(3).setMaxWidth(0);
            fuelTable.getColumnModel().getColumn(3).setWidth(0);
            fuelTable.getColumnModel().getColumn(5).setMinWidth(0);
            fuelTable.getColumnModel().getColumn(5).setMaxWidth(0);
            fuelTable.getColumnModel().getColumn(5).setWidth(0);
            fuelTable.getColumnModel().getColumn(7).setMinWidth(0);
            fuelTable.getColumnModel().getColumn(7).setMaxWidth(0);
            fuelTable.getColumnModel().getColumn(7).setWidth(0);
            fuelTable.getColumnModel().getColumn(8).setMinWidth(0);
            fuelTable.getColumnModel().getColumn(8).setMaxWidth(0);
            fuelTable.getColumnModel().getColumn(8).setWidth(0);
            fuelTable.getColumnModel().getColumn(9).setMinWidth(0);
            fuelTable.getColumnModel().getColumn(9).setMaxWidth(0);
            fuelTable.getColumnModel().getColumn(9).setWidth(0);
            fuelTable.getColumnModel().getColumn(10).setMinWidth(0);
            fuelTable.getColumnModel().getColumn(10).setMaxWidth(0);
            fuelTable.getColumnModel().getColumn(10).setWidth(0);
            fuelTable.setAutoCreateRowSorter(true);
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
        backButton.addActionListener(e -> {
            dispose();
            new MainForm(user);
        });
        firmButton.addActionListener(e -> {
            dispose();
            new FirmForm(user);
        });
        statisticButton.addActionListener(e -> {
            dispose();
            new StatisticForm(user);
        });
        editFuelButton.addActionListener(e -> {
            new editFuelSubFrom(this, fuelEntity);
        });
        deleteFuelButton.addActionListener(e -> {
            System.out.println(fuelEntity.getId_fuel());
            System.out.println(fuelEntity.getId_gas_station());
            if (DialogUtil.showConfirm(this, "Вы точно хотите удалить данную запись?")) {
                try {
                    fuelEntityManager.deleteFuel_Has_StationById(fuelEntity.getId_fuel(), fuelEntity.getId_gas_station());
                    fuelEntityManager.deleteFuelById(fuelEntity.getId_fuel());
                    table2Model.fireTableDataChanged();
                } catch (SQLException throwables) {
                    DialogUtil.showError(this, "Не удалось удалить");
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void initProperties() {
        fuelButton.setBorder(null);
        gasButton.setBorder(null);
        firmButton.setBorder(null);
        accountButton.setBorder(null);
        backButton.setBorder(null);
        mathButton.setBorder(null);
        statisticButton.setBorder(null);
        editFuelButton.setBorder(null);
        deleteFuelButton.setBorder(null);

        deleteFuelButton.setBackground(new Color(39, 193, 167));
        deleteFuelButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        deleteFuelButton.setBorderPainted(false);

        deleteFuelButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        deleteFuelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteFuelButton.setBackground(new Color(32, 153, 129));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteFuelButton.setBackground(new Color(39, 193, 167));
            }
        });

        editFuelButton.setBackground(new Color(39, 193, 167));
        editFuelButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        editFuelButton.setBorderPainted(false);

        editFuelButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        editFuelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editFuelButton.setBackground(new Color(32, 153, 129));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editFuelButton.setBackground(new Color(39, 193, 167));
            }
        });

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

        mathButton.setBackground(new Color(39, 193, 167));
        mathButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mathButton.setBorderPainted(false);

        mathButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        mathButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mathButton.setBackground(new Color(32, 153, 129));
                mathButton.setText("<html><font color='white'>Посчитать</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mathButton.setBackground(new Color(39, 193, 167));
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
    }

    @Override
    public int getFormWidth() {
        return 900;
    }

    @Override
    public int getFormHeight() {
        return 600;
    }

    public CustomTableModel<FuelEntity> getTableModel() {
        return table2Model;
    }
}
