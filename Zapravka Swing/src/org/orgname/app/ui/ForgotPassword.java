package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ForgotPassword extends BaseForm {
    private final UserEntityManager userEntityManager = new UserEntityManager(Application.getInstance().getDatabase());
    private JPanel mainPanel;
    private JPanel regPanel;
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JPasswordField passwordField2;
    private JButton updatePasswordButton;
    private JPanel enterPanel;
    private JButton enterButton;
    private JLabel enterTitle;
    private JLabel textLabel;
    private JLabel mainTitle;
    private JLabel marginLabel;

    public ForgotPassword() {
        setContentPane(mainPanel);
        initButtons();
        initProperties();
        setVisible(true);
    }

    private void initButtons() {
        enterButton.addActionListener(e -> {
            dispose();
            new AuthontifForm();
        });

        updatePasswordButton.addActionListener(e -> {
            try {
                UserEntity user = userEntityManager.getByLogin(loginField.getText());
                userEntityManager.update(loginField.getText(), String.valueOf(passwordField.getPassword()));
                dispose();
                new AuthontifForm();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                showMessageDialog(null, "Проверьте правильность написания данных!");
            }
        });
    }

    private void initProperties() {
        //Margin
        enterTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));
        textLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        loginLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        marginLabel.setBorder(BorderFactory.createEmptyBorder(0,0,17,0));

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

        loginLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));

        updatePasswordButton.setBorder(new EmptyBorder(10, 65, 10, 65));
        updatePasswordButton.setBorderPainted(false);
        updatePasswordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePasswordButton.setBackground(new Color(32, 153, 129));
                updatePasswordButton.setText("<html><font color='white'>Восстановить</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePasswordButton.setBackground(new Color(39, 193, 167));
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
