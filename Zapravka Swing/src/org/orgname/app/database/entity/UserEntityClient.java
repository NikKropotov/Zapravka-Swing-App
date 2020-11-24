package org.orgname.app.database.entity;

public class UserEntityClient {
    private int id_client;
    private String client_fio;
    private String client_account_amount;
    private int client_phone;
    private int account_id_account;

    public UserEntityClient(int id_client, String client_fio, String client_account_amount, int client_phone, int account_id_account) {
        this.id_client = id_client;
        this.client_fio = client_fio;
        this.client_account_amount = client_account_amount;
        this.client_phone = client_phone;
        this.account_id_account = account_id_account;
    }

//    public UserEntityClient(String client_fio, int client_phone) {
//        this(-1, client_fio, client_phone);
//    }

    public static boolean isNameIncorrect(String s) {
        return s.length() > 40;
    }

    public static boolean isPhoneIncorrect(String s) {
        return s.length() > 7;
    }

    @Override
    public String toString() {
        return "UserEntityClient{" +
                "id_client=" + id_client +
                ", client_fio='" + client_fio + '\'' +
                ", client_account_amount='" + client_account_amount + '\'' +
                ", client_phone=" + client_phone +
                ", account_id_account=" + account_id_account +
                '}';
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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

    public int getAccount_id_account() {
        return account_id_account;
    }

    public void setAccount_id_account(int account_id_account) {
        this.account_id_account = account_id_account;
    }
}

