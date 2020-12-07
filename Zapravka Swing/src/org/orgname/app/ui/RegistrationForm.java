package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

import static org.orgname.app.database.manager.UserEntityManager.isIsUserExists;

public class RegistrationForm extends BaseForm {
    private final UserEntityManager userEntityManager = new UserEntityManager(Application.getInstance().getDatabase());
    private JPanel mainPanel;
    private JPanel registrationPanel;
    private JTextField reg_loginField;
    private JPasswordField reg_passwordField;
    private JTextField reg_name_surnameField;
    private JTextField reg_phoneField;
    private JButton registrationButton;
    private JPanel enterPanel;
    private JButton enterButton;
    private JLabel regTitle;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel nameLabel;
    private JLabel phoneLable;
    private JLabel enterTitle;
    private JLabel text2Label;
    private JLabel marginLabel;

    public RegistrationForm() {
        setContentPane(mainPanel);
        initProperties();
        initButtons();
        setVisible(true);
    }

    private void initButtons() {
        enterButton.addActionListener(e -> {
            back();
        });
        registrationButton.addActionListener(e -> {
            String login = reg_loginField.getText();
            if(UserEntity.isLoginIncorrect(login)) {
                DialogUtil.showError(this, "Логин введен некорректно");
                return;
            }
            String password = new String(reg_passwordField.getPassword());
            if(UserEntity.isPasswordIncorrect(password)) {
                DialogUtil.showError(this, "Пароль введен некорректно");
                return;
            }
            String phone = reg_phoneField.getText();
            if(UserEntity.isPhoneIncorrect(phone)) {
                DialogUtil.showError(this, "Номер должен содержать 7 цифр");
                return;
            }
            UserEntity user = new UserEntity(
                    login,
                    password,
                    reg_name_surnameField.getText(),
                    Integer.parseInt(phone)
            );
            try {
                userEntityManager.add_user_account(user);
                dispose();
                if (isIsUserExists()) {
                    setVisible(false);
                    new MainForm(user);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void back() {
        dispose();
        new AuthontifForm();
    }

    private void initProperties() {
        //Margin
        regTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));
        text2Label.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        loginLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        phoneLable.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        enterTitle.setBorder(BorderFactory.createEmptyBorder(0,0,22,0));
        marginLabel.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));

        enterButton.setBorderPainted(false);
        enterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
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

        registrationButton.setBorder(new EmptyBorder(10, 65, 10, 65));
        registrationButton.setBorderPainted(false);
        registrationButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registrationButton.setBackground(new Color(32, 153, 129));
                registrationButton.setText("<html><font color='white'>Регистрация</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                registrationButton.setBackground(new Color(39, 193, 167));
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
