package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;

import javax.swing.*;

public class FuelForm extends BaseForm {
    private final UserEntityManager userEntityManager = new UserEntityManager(Application.getInstance().getDatabase());
    private Object mainForm;

    private JPanel mainPanel;
    private JTable table1;
    private JButton button1;

    public FuelForm(AuthontifForm authontifForm) {
        this.mainForm = mainForm;
        setContentPane(mainPanel);

//        initBoxes();
//        initButtons();

        setVisible(true);
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
