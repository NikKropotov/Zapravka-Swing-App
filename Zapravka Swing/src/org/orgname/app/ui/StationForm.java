package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.FirmEntity;
import org.orgname.app.database.entity.StationEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.FirmEntityManager;
import org.orgname.app.database.manager.StationEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class StationForm extends BaseForm {
    private final StationEntityManager stationEntityManager = new StationEntityManager(Application.getInstance().getDatabase());
    private DefaultListModel<String> stationListModel = new DefaultListModel<String>();
    private final UserEntity user;

    private JPanel mainPanel;
    private JButton fuelButton;
    private JButton gasButton;
    private JButton firmButton;
    private JButton accountButton;
    private JTextField searchField;
    private JLabel marginLabel;
    private JList stationList;
    private JPanel navMenu;
    private JLabel logoLabel;
    private JButton statisticButton;
    private JLabel mainLabe;
    private JButton editStationsButton;
    private JPanel mainContent;
    private JScrollPane stationListScroll;

    public StationForm(UserEntity user) {
        this.user = user;
        setContentPane(mainPanel);
        initUserType();
        initList();
        initProperties();
        initButtton();
        setVisible(true);
    }

    private void initUserType() {
        if (user.getAccount_type().equals("Admin")) {
            statisticButton.setVisible(true);
            editStationsButton.setVisible(true);
        }
    }

    private void initList() {
        try {
            List<StationEntity> stations = StationEntityManager.getAllStations();
            for (StationEntity s : stations) {
                stationListModel.addElement("<html><div style='margin-left: 10px; margin-bottom: 20px; margin-top: 10px'>№ "
                        + s.getGas_station_code() + "\n"
                        + "<br>" + s.getGas_station_name() + "\n"
                        + "<br>Адрес: " + s.getGas_station_address() + "<br/>" + "</div></html>");
            }
            stationList.setModel(stationListModel);

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
        fuelButton.addActionListener(e -> {
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
        navMenu.setPreferredSize(new Dimension(160, -1));
//        stationListScroll.setBorder(null);
        fuelButton.setBorder(null);
        gasButton.setBorder(null);
        firmButton.setBorder(null);
        searchField.setBorder(null);
        accountButton.setBorder(null);
        statisticButton.setBorder(null);
        editStationsButton.setBorder(null);

        editStationsButton.setBackground(new Color(39, 193, 167));
        editStationsButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        editStationsButton.setBorderPainted(false);

        editStationsButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        editStationsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editStationsButton.setBackground(new Color(32, 153, 129));
                editStationsButton.setText("<html><font color='white'>Редактировать</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editStationsButton.setBackground(new Color(39, 193, 167));
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
}
