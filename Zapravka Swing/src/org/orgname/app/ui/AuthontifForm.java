package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class AuthontifForm extends BaseForm {
    private final UserEntityManager userEntityManager = new UserEntityManager(Application.getInstance().getDatabase());
    private JPanel mainPanel;
    private JPanel regPanel;
    private JButton regButton;
    private JPanel enterPanel;
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton enterButton;
    private JButton forgotPassword;
    private JLabel regTitleLabel;
    private JLabel regText1;
    private JLabel regText2;
    private JLabel mainTitle;
    private JLabel marginLabel;

    public AuthontifForm() {
        setContentPane(mainPanel);
        initProperties();
        initButtons();
        setVisible(true);
    }

    private void initButtons() {
        forgotPassword.addActionListener(e -> {
            dispose();
            new ForgotPassword();
        });
        regButton.addActionListener(e -> {
            dispose();
            new RegistrationForm();
        });
        enterButton.addActionListener(e -> {
            try {
                UserEntity user = userEntityManager.getByLoginAndPassword(loginField.getText(),
                        new String(passwordField.getPassword()));
                if (user == null) {
                    System.out.println("Нет такого пользователя");
                } else {
                    dispose();
                    new MainForm(user);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void initProperties() {
        //Margin
        regTitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));
        regText2.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        loginLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        mainTitle.setBorder(BorderFactory.createEmptyBorder(0,0,25,0));
        marginLabel.setBorder(BorderFactory.createEmptyBorder(0,0,17,0));

        enterButton.setBackground(new Color(39, 193, 167));
        enterButton.setBorderPainted(false);
        enterButton.setBorder(new EmptyBorder(10, 65, 10, 65));
        enterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterButton.setBackground(new Color(32, 153, 129));
                enterButton.setText("<html><font color='white'>Вход</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterButton.setBackground(new Color(39, 193, 167));
            }
        });

        forgotPassword.setBorder(null);
        forgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgotPassword.setBorder(null);
                forgotPassword.setText("<html><font color='#209981'>Забыли пароль?</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgotPassword.setBorder(null);
                forgotPassword.setText("<html><font color='#27C1A7'>Забыли пароль?</font></html>");
            }
        });

        regButton.setBorder(new EmptyBorder(10, 65, 10, 65));
        regButton.setBorderPainted(false);
        regButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regButton.setBackground(new Color(32, 153, 129));
                regButton.setText("<html><font color='white'>Регистрация</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                regButton.setBackground(new Color(39, 193, 167));
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 800;
    }

    @Override
    public int getFormHeight() {
        return 550;
    }
}
