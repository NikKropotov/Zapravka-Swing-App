package org.orgname.app.database.manager;

import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.entity.UserEntityClient;
import org.orgname.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class UserEntityManager {
    private MysqlDatabase database;
    private static boolean isUserExists = false;

    public UserEntityManager(MysqlDatabase database) {
        this.database = database;
    }

//    public void add(UserEntity user) throws SQLException {
//        try (Connection c = database.getConnection()) {
//            String sql = "INSERT INTO account(login, pass) values(?,?)";
//            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            s.setString(1, user.getLogin());
//            s.setString(2, user.getPass());
//            s.executeUpdate();
//
//            ResultSet keys = s.getGeneratedKeys();
//            if (keys.next()) {
//                user.setId(keys.getInt(1));
//                return;
//            }
//            throw new SQLException("User not added");
//        }
//    }

    public UserEntity getById(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM account WHERE id_account=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet result = s.executeQuery();
            if (result.next()) {
                return new UserEntity(
                        result.getInt("id_account"),
                        result.getString("login"),
                        result.getString("pass")
                );
            }
            return null;
        }
    }

    public UserEntity getByLoginAndPassword(String login, char[] password) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM account WHERE login=? AND password=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, login);
            s.setString(2, String.valueOf(password));
            ResultSet result = s.executeQuery();
            if (result.next()) {
                isUserExists = true;
//                System.out.print("User exist" + login + " " + Arrays.toString(password));
                showMessageDialog(null, "Вход в аккаунт!");
                return new UserEntity(
                        result.getInt("id_account"),
                        result.getString("login"),
                        result.getString("password")
                );
            } else {
                showMessageDialog(null, "Проверьте правильность написания данных!");
            }
            return null;
        }
    }

    public static boolean isIsUserExists() {
        return isUserExists;
    }

    public List<UserEntity> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM account";
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(sql);

            List<UserEntity> users = new ArrayList<>();
            while (result.next()) {
                users.add(new UserEntity(
                        result.getInt("id_account"),
                        result.getString("login"),
                        result.getString("password")
                ));
            }
            return users;
        }
    }

    public int update(UserEntity user) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE account SET login=?, password=? WHERE id_account=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPass());
            s.setInt(5, user.getId());

            return s.executeUpdate();
        }
    }

    public int deleteById(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM account WHERE id_account=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);

            return s.executeUpdate();
        }
    }

    public void add_user_account(String reg_login, char[] password, UserEntity user) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO account(login, pass) values(?,?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, reg_login);
            s.setString(2, String.valueOf(password));
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
                return;
            }
            throw new SQLException("User not added");
        }
    }

    public void add_user_client(String user_name, String user_phone, UserEntityClient user) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO client(client_fio, client_phone) values(?,?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, user_name);
            s.setString(2, String.valueOf(user_phone));
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                user.setId_client(keys.getInt(1));
                return;
            }
            throw new SQLException("User not added");
        }
    }
}
