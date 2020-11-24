package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

import static org.orgname.app.database.manager.UserEntityManager.isIsUserExists;

public class RegistrationForm extends BaseForm {
    private final UserEntityManager userEntityManager = new UserEntityManager(Application.getInstance().getDatabase());
    private final AuthontifForm mainForm;

    private JPanel mainPanel;
    private JTextField reg_loginField;
    private JPasswordField reg_passwordField;
    private JTextField reg_name_surnameField;
    private JTextField reg_phoneField;
    private JButton registrationButton;
    private JButton enterButton;

    public RegistrationForm(AuthontifForm mainForm) {
        this.mainForm = mainForm;
        setContentPane(mainPanel);

//        initBoxes();
        initButtons();

        setVisible(true);
    }

    private void initButtons() {
        enterButton.addActionListener(e -> {
            back();
        });
        registrationButton.addActionListener(e -> {
            try {
                create_account();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (isIsUserExists()) {
                setVisible(false);
//                    new FuelForm(this);
            }
        });

    }

    private void create_account() throws SQLException {
        String reg_login = reg_loginField.getText();
        char[] password = reg_passwordField.getPassword();
        String user_name = reg_name_surnameField.getText();
        String user_phone = reg_phoneField.getText();
        userEntityManager.add_user_account(reg_login, password);
        userEntityManager.add_user_client(user_name, user_phone);
    }



    private void back() {
        dispose();
        mainForm.setVisible(true);
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
