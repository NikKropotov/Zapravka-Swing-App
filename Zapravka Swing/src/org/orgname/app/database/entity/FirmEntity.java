package org.orgname.app.database.entity;

public class FirmEntity {
    private int id_firm;
    private String firm_name;
    private String firm_address;
    private String firm_phone;

    public FirmEntity(int id_firm, String firm_name, String firm_address, String firm_phone) {
        this.id_firm = id_firm;
        this.firm_name = firm_name;
        this.firm_address = firm_address;
        this.firm_phone = firm_phone;
    }

    public FirmEntity(String firm_name, String firm_address, String firm_phone) {
        this.firm_name = firm_name;
        this.firm_address = firm_address;
        this.firm_phone = firm_phone;
    }

    @Override
    public String toString() {
        return "FirmEntity{" +
                "id_firm=" + id_firm +
                ", firm_name='" + firm_name + '\'' +
                ", firm_address='" + firm_address + '\'' +
                ", firm_phone='" + firm_phone + '\'' +
                '}';
    }

    public int getId_firm() {
        return id_firm;
    }

    public void setId_firm(int id_firm) {
        this.id_firm = id_firm;
    }

    public String getFirm_name() {
        return firm_name;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    public String getFirm_address() {
        return firm_address;
    }

    public void setFirm_address(String firm_address) {
        this.firm_address = firm_address;
    }

    public String getFirm_phone() {
        return firm_phone;
    }

    public void setFirm_phone(String firm_phone) {
        this.firm_phone = firm_phone;
    }
}
