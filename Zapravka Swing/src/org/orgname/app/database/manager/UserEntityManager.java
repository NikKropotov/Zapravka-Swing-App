package org.orgname.app.database.manager;

import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class UserEntityManager {
    private MysqlDatabase database;
    private static boolean isUserExists = false;

    public static boolean isIsUserExists() {
        return isUserExists;
    }

    public UserEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public UserEntity getById(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM client WHERE id_client=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet result = s.executeQuery();
            if (result.next()) {
                return new UserEntity(
                        result.getInt("id_client"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("client_fio"),
                        result.getString("client_account_amount"),
                        result.getInt("client_phone")
                );
            }
            return null;
        }
    }

    public UserEntity getByLoginAndPassword(String login, String password) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM client WHERE login=? AND password=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, login);
            s.setString(2, password);
            ResultSet result = s.executeQuery();
            if (result.next()) {
                isUserExists = true;
                System.out.print("User exist " + login + " " + password);
                showMessageDialog(null, "Вход в аккаунт!");
                return new UserEntity(
                        result.getInt("id_client"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("client_fio"),
                        result.getString("client_account_amount"),
                        result.getInt("client_phone"),
                        result.getString("account_type")
                );
            } else {
                showMessageDialog(null, "Проверьте правильность написания данных!");
            }
            return null;
        }
    }

    public UserEntity getByLogin(String login) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM client WHERE login=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, login);
            ResultSet result = s.executeQuery();
            if (result.next()) {
                isUserExists = true;
                System.out.print("User exist " + login);
                showMessageDialog(null, "Пароль изменен!");
                return new UserEntity(
                        result.getInt("id_client"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("client_fio"),
                        result.getString("client_account_amount"),
                        result.getInt("client_phone"),
                        result.getString("account_type")
                );
            } else {
                showMessageDialog(null, "Логин введен некорректно");
            }
            return null;
        }
    }


    public List<UserEntity> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM client";
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(sql);

            List<UserEntity> users = new ArrayList<>();
            while (result.next()) {
                users.add(new UserEntity(
                        result.getInt("id_client"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("client_fio"),
                        result.getString("client_account_amount"),
                        result.getInt("client_phone")
                ));
            }
            return users;
        }
    }

    public int update(String login, String password) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE client SET password=? WHERE login=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, password);
            s.setString(2, login);
            return s.executeUpdate();
        }
    }

    public int deleteById(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM client WHERE id_client=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            return s.executeUpdate();
        }
    }

    public void add_user_account(UserEntity user) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO client(login, password, client_fio, client_account_amount, client_phone, account_type) values(?,?,?,?,?,?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
            s.setString(3, user.getClient_fio());
            s.setInt(4, user.setClient_account_amount_def());
            s.setString(5, String.valueOf(user.getClient_phone()));
            s.setString(6, user.setAccount_type_def());
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                isUserExists = true;
                user.setId_client(keys.getInt(1));
                return;
            }
            throw new SQLException("User not added");
        }
    }
}
