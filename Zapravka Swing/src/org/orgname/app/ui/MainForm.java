package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.FuelEntity;
import org.orgname.app.database.entity.StationEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.FuelEntityManager;
import org.orgname.app.database.manager.StationEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;
import org.orgname.app.util.ObjectTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainForm extends BaseForm {
    private final FuelEntityManager fuelEntityManager = new FuelEntityManager(Application.getInstance().getDatabase());
    private final StationEntityManager stationEntityManager = new StationEntityManager(Application.getInstance().getDatabase());
    private final UserEntity user;
    private DefaultTableModel tableModel;
//    private ObjectTableModel<FuelEntity> tableModel;

    private JPanel mainPanel;
    private JPanel navMenu;
    private JButton fuelButton;
    private JButton gasButton;
    private JButton firmButton;
    private JPanel mainContent;
    private JTextField searchField;
    private JTextArea fuelTextArea;
    private JButton accountButton;
    private JTable fuelsTable;
    private JButton addFuelButton;
    private JButton statisticButton;
    private JLabel logoLabel;
    private JLabel mainLabe;
    private JScrollPane tableScrollPane;
    private JScrollPane JScrollFuelPanel;

    public MainForm(UserEntity user) {
        this.user = user;
        setContentPane(mainPanel);
        initUserType();
        initButtton();
        initProperties();
        initTable();
        loadTableData();
        setVisible(true);
    }

    private void initUserType() {
        if (user.getAccount_type().equals("Admin")){
            addFuelButton.setVisible(true);
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
            new SatationForm(user);
        });
    }

//    private void initTable() {
//        fuelsTable.getTableHeader().setReorderingAllowed(false);
//
//        fuelsTable.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2 && fuelsTable.getSelectedRow() != -1) {
//                    int row = fuelsTable.rowAtPoint(e.getPoint());
//                    System.out.println(tableModel.getRowEntity(row));
//                    setVisible(false);
//                    new FuelMark(tableModel.getRowEntity(row));
//                }
//            }
//        });
//
//        tableModel = new ObjectTableModel<FuelEntity>() {
//            @Override
//            public FuelEntity getEntityFromRowData(Object[] rowData) {
//                return new FuelEntity(
//                        (String) rowData[0],
//                        (String) rowData[1],
//                        (int) rowData[2]
//                );
//            }
//
//            @Override
//            public Object[] getRowDataFromEntity(FuelEntity entity) {
//                return new Object[]{
//                        entity.getFuel_type(),
//                        entity.getGas_station_name(),
//                        entity.getPrice_one_litr()
//                };
//            }
//        };
//        fuelsTable.setModel(tableModel);
//        tableModel.addColumn("Топливо");
//        tableModel.addColumn("Заправка");
//        tableModel.addColumn("Цена за литр");
//
//        try {
//            tableModel.addRowEntities(FuelEntityManager.getAllFuels());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    private void initTable() {
        fuelsTable.getTableHeader().setReorderingAllowed(false);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //получение записи по двойному клику
        fuelsTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);

                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    Object[] rowValues = new Object[tableModel.getColumnCount()];
                    for (int i = 0; i < tableModel.getColumnCount(); i++) {
                        rowValues[i] = tableModel.getValueAt(row, i);
                    }
                    dispose();
                    try {
                        new FuelMark(Arrays.toString(rowValues), user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Arrays.toString(rowValues));
                }
                //установка значение по адресу
                //tableModel.setValueAt("new Login", 0, 1);
            }
        });

        fuelsTable.setModel(tableModel);
        tableModel.addColumn("Топливо");
        tableModel.addColumn("Заправка");
        tableModel.addColumn("Цена за литр");

        fuelsTable.setAutoCreateRowSorter(true);
    }

    private void loadTableData() {
        try {
            List<FuelEntity> fuel = FuelEntityManager.getAllFuels();
            for (FuelEntity f : fuel) {
                tableModel.addRow(new Object[]{
                        f.getFuel_type(),
                        f.getGas_station_name(),
                        f.getPrice_one_litr() + "₽"
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
        searchField.setBorder(null);
        accountButton.setBorder(null);
        statisticButton.setBorder(null);
        addFuelButton.setBorder(null);

        statisticButton.setBackground(new Color(39, 193, 167));
        statisticButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        statisticButton.setBorderPainted(false);

        statisticButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        statisticButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                statisticButton.setBackground(new Color(32, 153, 129));
                statisticButton.setText("<html><font color='white'>Статистика</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                statisticButton.setBackground(new Color(39, 193, 167));
            }
        });
        addFuelButton.setBackground(new Color(39, 193, 167));
        addFuelButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        addFuelButton.setBorderPainted(false);

        addFuelButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        addFuelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addFuelButton.setBackground(new Color(32, 153, 129));
                addFuelButton.setText("<html><font color='white'>Добавить</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addFuelButton.setBackground(new Color(39, 193, 167));
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
    }

    @Override
    public int getFormWidth() {
        return 900;
    }

    @Override
    public int getFormHeight() {
        return 600;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
