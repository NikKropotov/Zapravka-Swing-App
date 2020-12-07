package org.orgname.app.ui;

import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import java.awt.*;

public class AccountInfo extends BaseForm {

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

    public AccountInfo(UserEntity user) {
        this.user = user;
        setContentPane(mainPanel);
        initButtton();
        initProperties();
        setVisible(true);

        accountInfoArea.setEditable(false);
        initUserData();
        setVisible(true);

    }

    public void initUserData() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ID: ").append(user.getId_client()).append("\n")
                .append("  Логин: ").append(user.getLogin()).append("\n")
                .append("  ФИО: ").append(user.getClient_fio()).append("\n")
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
    }

    private void back() {
        dispose();
        new MainForm(user);
    }

    private void initProperties() {
        fuelButton.setBorder(null);
        gasButton.setBorder(null);
        firmButton.setBorder(null);
        backButton.setBorder(null);

        fuelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fuelButton.setBorder(null);
                fuelButton.setBackground(new Color(76, 75, 72));
                fuelButton.setText("<html><font color='#209981'>Заправки</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                fuelButton.setBorder(null);
                fuelButton.setBackground(new Color(54, 53, 51));
                fuelButton.setText("<html><font color='#847F81'>Заправки</font></html>");
            }
        });
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
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBorder(null);
                backButton.setBackground(new Color(76, 75, 72));
                backButton.setText("<html><font color=white>Назад</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBorder(null);
                backButton.setBackground(new Color(54, 53, 51));
                backButton.setText("<html><font color=white>Назад</font></html>");
            }
        });
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