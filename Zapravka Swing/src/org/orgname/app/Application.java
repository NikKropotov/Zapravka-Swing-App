package org.orgname.app;

import org.orgname.app.ui.AuthontifForm;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.DialogUtil;
import org.orgname.app.util.MysqlDatabase;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class Application {
    private static Application instance;

    private final MysqlDatabase database = new MysqlDatabase("localhost", "zapravka", "root", "1234");

    private Application() {
        instance = this;
        initDatabase();
        initUi();
        new AuthontifForm();
    }

    private void initDatabase() {
        try (Connection c = database.getConnection()) {
        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.showError("Ошибка подключения к бд");
            System.exit(-1);
        }
    }

    private void initUi() {
        BaseForm.setBaseApplicationTitle("Zapravochka");
        BaseForm.setBaseApplicationIcon(
                Toolkit.getDefaultToolkit().getImage(Application.class.getClassLoader().getResource("icon.png"))
        );

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MysqlDatabase getDatabase() {
        return database;
    }

    public static Application getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        new Application();
    }
}
