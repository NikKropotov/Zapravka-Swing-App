package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.FirmEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.FirmEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class FirmForm extends BaseForm {
    private final FirmEntityManager firmEntityManager = new FirmEntityManager(Application.getInstance().getDatabase());
    private DefaultListModel<String> firmListModel = new DefaultListModel<String>();
    private final UserEntity user;

    private JPanel mainPanel;
    private JPanel navMenu;
    private JLabel logoLabel;
    private JButton fuelButton;
    private JButton gasButton;
    private JButton firmButton;
    private JButton accountButton;
    private JButton statisticButton;
    private JPanel mainContent;
    private JLabel mainLabe;
    private JTextField searchField;
    private JButton editFirmsButton;
    private JList firmList;
    private JScrollPane firmListScroll;
    private JLabel marginLabel;

    public FirmForm(UserEntity user) {
        this.user = user;
        setContentPane(mainPanel);
        initUserType();
        initButtton();
        initProperties();
        initList();

        setVisible(true);
    }

    private void initUserType() {
        if (user.getAccount_type().equals("Admin")) {
            statisticButton.setVisible(true);
            editFirmsButton.setVisible(true);
        }
    }

    private void initList() {
        try {
            List<FirmEntity> firms = FirmEntityManager.getAllFirm();
            for (FirmEntity s : firms) {
                firmListModel.addElement("<html><div style='margin-left: 10px; margin-bottom: 20px; margin-top: 10px'>" + s.getFirm_name() + "\n"
                        + "<br>Адрес: " + s.getFirm_address() + "\n"
                        + "<br>Номер телефона: " + s.getFirm_phone() + "</div></html>");
            }
            firmList.setModel(firmListModel);

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
        fuelButton.addActionListener(e -> {
            dispose();
            new MainForm(user);
        });
        statisticButton.addActionListener(e -> {
            dispose();
            new StatisticForm(user);
        });

        // add edit button
    }

    private void initProperties() {
        fuelButton.setBorder(null);
        gasButton.setBorder(null);
        firmButton.setBorder(null);
        accountButton.setBorder(null);
        searchField.setBorder(null);
        statisticButton.setBorder(null);
        editFirmsButton.setBorder(null);

        editFirmsButton.setBackground(new Color(39, 193, 167));
        editFirmsButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        editFirmsButton.setBorderPainted(false);

        editFirmsButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        editFirmsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editFirmsButton.setBackground(new Color(32, 153, 129));
                editFirmsButton.setText("<html><font color='white'>Редактировать</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editFirmsButton.setBackground(new Color(39, 193, 167));
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
