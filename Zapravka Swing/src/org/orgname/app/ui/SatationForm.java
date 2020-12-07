package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.StationEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.StationEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class SatationForm extends BaseForm {
    private final StationEntityManager stationEntityManager = new StationEntityManager(Application.getInstance().getDatabase());
    private final UserEntity user;

    private JPanel mainPanel;
    private JPanel navMenu;
    private JButton fuelButton;
    private JButton gasButton;
    private JButton firmButton;
    private JButton accountButton;
    private JPanel mainContent;
    private JTextField searchField;
    private JTextArea stationArea;
    private JScrollPane stationScrollPanel;
    private JLabel marginLabel;

    public SatationForm(UserEntity user) {
        this.user = user;
        setContentPane(mainPanel);
        initArea();
        initProperties();
        initButtton();
        setVisible(true);
    }

    private void initArea() {
        try {
            List<StationEntity> stations = StationEntityManager.getAllStations();
            StringBuilder sb = new StringBuilder();
            for (StationEntity s : stations) {
//                stationArea.setText(Arrays.toString(new String[]{
//                        "№ %d\n".formatted(s.getGas_station_code()),
//                        "Название: %s\n".formatted(s.getGas_station_name()),
//                        "Адрес: %s\n".formatted(s.getGas_station_address())
//                }));
                sb.append("№ ").append(s.getGas_station_code()).append("\n");
                sb.append("Название: ").append(s.getGas_station_name()).append("\n");
                sb.append("Адрес: ").append(s.getGas_station_address()).append("\n");
                sb.append("\n");
            }
            stationArea.setText(sb.toString());
            stationArea.setEditable(false);
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
    }

    private void initProperties() {
        stationArea.setBorder(null);
        stationScrollPanel.setBorder(null);
        fuelButton.setBorder(null);
        gasButton.setBorder(null);
        firmButton.setBorder(null);
        searchField.setBorder(null);
        accountButton.setBorder(null);

        marginLabel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));


        fuelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fuelButton.setBorder(null);
                fuelButton.setBackground(new Color(76, 75, 72));
                fuelButton.setText("<html><font color='#209981'>Топливо</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                fuelButton.setBorder(null);
                fuelButton.setBackground(new Color(54, 53, 51));
                fuelButton.setText("<html><font color='#847F81'>Топливо</font></html>");
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
