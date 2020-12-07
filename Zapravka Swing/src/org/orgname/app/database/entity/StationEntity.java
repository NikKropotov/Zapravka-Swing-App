package org.orgname.app.database.entity;

public class StationEntity {
    private int id_gas_station;
    private int gas_station_code;
    private String gas_station_address;
    private String gas_station_name;
    private int firm_id_firm;

    public StationEntity(int id_gas_station, int gas_station_code, String gas_station_address,
                         String gas_station_name, int firm_id_firm) {
        this.id_gas_station = id_gas_station;
        this.gas_station_code = gas_station_code;
        this.gas_station_address = gas_station_address;
        this.gas_station_name = gas_station_name;
        this.firm_id_firm = firm_id_firm;
    }

    public StationEntity(int gas_station_code, String gas_station_address, String gas_station_name) {
        this.gas_station_code = gas_station_code;
        this.gas_station_address = gas_station_address;
        this.gas_station_name = gas_station_name;
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "id_gas_station=" + id_gas_station +
                ", gas_station_code=" + gas_station_code +
                ", gas_station_address='" + gas_station_address + '\'' +
                ", gas_station_name='" + gas_station_name + '\'' +
                ", firm_id_firm=" + firm_id_firm +
                '}';
    }

    public int getId_gas_station() {
        return id_gas_station;
    }

    public void setId_gas_station(int id_gas_station) {
        this.id_gas_station = id_gas_station;
    }

    public int getGas_station_code() {
        return gas_station_code;
    }

    public void setGas_station_code(int gas_station_code) {
        this.gas_station_code = gas_station_code;
    }

    public String getGas_station_address() {
        return gas_station_address;
    }

    public void setGas_station_address(String gas_station_address) {
        this.gas_station_address = gas_station_address;
    }

    public String getGas_station_name() {
        return gas_station_name;
    }

    public void setGas_station_name(String gas_station_name) {
        this.gas_station_name = gas_station_name;
    }

    public int getFirm_id_firm() {
        return firm_id_firm;
    }

    public void setFirm_id_firm(int firm_id_firm) {
        this.firm_id_firm = firm_id_firm;
    }
}
