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
        }
    }

    private void initScore() {
        priceLabel.setText(String.valueOf(fuelEntity.getPrice_one_litr()));
        int price = fuelEntity.getPrice_one_litr();
        mathButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                totalLabel.setText(price + " * " + amount);
                double score = price * amount;
                scoreLabel.setText(String.valueOf(score));
            }
            catch (Exception i){
                i.printStackTrace();
                DialogUtil.showError("Количество введено некорректно");
            }
        });
    }

//    private void initTable() {
//        fuelTable.getTableHeader().setReorderingAllowed(false);
//
//        table2Model = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        //получение записи по двойному клику
//        fuelTable.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent mouseEvent) {
//                JTable table = (JTable) mouseEvent.getSource();
//                Point point = mouseEvent.getPoint();
//                int row = table.rowAtPoint(point);
//
//                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
//
//                    Object[] rowValues = new Object[table2Model.getColumnCount()];
//                    for (int i = 0; i < table2Model.getColumnCount(); i++) {
//                        rowValues[i] = table2Model.getValueAt(row, i);
//                    }
//                    System.out.println(Arrays.toString(rowValues));
//                }
//            }
//        });
//
//        fuelTable.setModel(table2Model);
//        table2Model.addColumn("Топливо");
//        table2Model.addColumn("Заправка");
//        table2Model.addColumn("Цена за литр");
//
//        fuelTable.setAutoCreateRowSorter(true);
//    }
//
//    private void loadTableData() {
//        try {
//            List<FuelEntity> fuel = FuelEntityManager.getFuelByStation(fuelEntity.getGas_station_address());
//            for (FuelEntity f : fuel) {
//                table2Model.addRow(new Object[]{
//                        f.getFuel_type(),
//                        f.getGas_station_name(),
//                        f.getPrice_one_litr()
//                });
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            DialogUtil.showError("Не удалось загрузить данные");
//        }
//    }

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
    }

    private void initProperties() {
        fuelButton.setBorder(null);
        gasButton.setBorder(null);
        firmButton.setBorder(null);
        accountButton.setBorder(null);
        backButton.setBorder(null);
        mathButton.setBorder(null);
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

//    public DefaultTableModel getTable2Model() {
//        return table2Model;
//    }

    public CustomTableModel<FuelEntity> getTableModel() {
        return table2Model;
    }
}
