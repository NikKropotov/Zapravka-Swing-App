package org.orgname.app.database.entity;

public class UserEntity {
    private int id;
    private String login;
    private String pass;


    public UserEntity(int id, String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
    }

    public UserEntity(String login, String pass, int age, String job) {
        this(-1, login, pass);
    }

    public static boolean isLoginIncorrect(String s) {
        return s.length() < 3 || s.length() > 20;
    }

    public static boolean isPasswordIncorrect(String s) {
        return s.length() < 3 || s.length() > 32;
    }

    public static boolean isAgeIncorrect(String s) {
        try {
            int i = Integer.parseInt(s);
            return i <= 0;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isJobIncorrect(String s) {
        if (s != null) {
            return s.length() > 50;
        }
        return false;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
