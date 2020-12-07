package org.orgname.app.database.entity;

public class UserEntity {
    private int id_client;
    private String login;
    private String password;
    private String client_fio;
    private String client_account_amount;
    private int client_phone;
    private String account_type;

    public UserEntity(int id_client, String login, String password, String client_fio,
                      String client_account_amount, int client_phone, String account_type) {
        this.id_client = id_client;
        this.login = login;
        this.password = password;
        this.client_fio = client_fio;
        this.client_account_amount = client_account_amount;
        this.client_phone = client_phone;
        this.account_type = account_type;
    }

    public UserEntity(int id_client, String login, String password, String client_fio, String client_account_amount,
                      int client_phone) {
        this.id_client = id_client;
        this.login = login;
        this.password = password;
        this.client_fio = client_fio;
        this.client_account_amount = client_account_amount;
        this.client_phone = client_phone;
    }

    public UserEntity(String login, String password, String client_fio, String client_account_amount, int client_phone) {
        this(-1, login, password, client_fio, client_account_amount, client_phone);
    }

    public UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserEntity(String login, String password, String client_fio, int client_phone) {
        this.login = login;
        this.password = password;
        this.client_fio = client_fio;
        this.client_phone = client_phone;
    }

    public static boolean isLoginIncorrect(String s) {
        return s.length() < 3 || s.length() > 20;
    }

    public static boolean isPasswordIncorrect(String s) {
        return s.length() < 3 || s.length() > 32;
    }

    public static boolean isPhoneIncorrect(String s) {
        return s.length() != 7;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id_client=" + id_client +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", client_fio='" + client_fio + '\'' +
                ", client_account_amount='" + client_account_amount + '\'' +
                ", client_phone=" + client_phone +
                '}';
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClient_fio() {
        return client_fio;
    }

    public void setClient_fio(String client_fio) {
        this.client_fio = client_fio;
    }

    public String getClient_account_amount() {
        return client_account_amount;
    }

    public void setClient_account_amount(String client_account_amount) {
        this.client_account_amount = client_account_amount;
    }

    public int getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(int client_phone) {
        this.client_phone = client_phone;
    }

    public int setClient_account_amount_def() {
        this.client_account_amount = client_account_amount;
        client_account_amount = String.valueOf(0);
        return Integer.parseInt(client_account_amount);
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String setAccount_type_def(){
        this.account_type = account_type;
        account_type = String.valueOf("User");
        return account_type;
    }
}
