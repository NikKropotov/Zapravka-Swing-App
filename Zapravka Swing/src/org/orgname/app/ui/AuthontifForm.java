package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;
import static org.orgname.app.database.manager.UserEntityManager.isIsUserExists;

public class AuthontifForm extends BaseForm {
    private final UserEntityManager userEntityManager = new UserEntityManager(Application.getInstance().getDatabase());
    private JPanel mainPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton enterButton;
    private JButton regButton;
    private JLabel forgotpassButton;
    private JTextArea textArea1;

    public AuthontifForm() {
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }

    private void initButtons() {
        regButton.addActionListener(e -> {
            setVisible(false);
            new RegistrationForm(this);
        });
        enterButton.addActionListener(e -> {
            try {
                initialaze_account();
                if (isIsUserExists()) {
                    setVisible(false);
                    new FuelForm(this);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void initialaze_account() throws SQLException {
        String login = loginField.getText();
        char[] password = passwordField.getPassword();
        userEntityManager.getByLoginAndPassword(login, password);
    }

    @Override
    public int getFormWidth() {
        return 500;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }
}
