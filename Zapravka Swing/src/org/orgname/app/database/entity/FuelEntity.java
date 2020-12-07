package org.orgname.app.database.entity;

public class FuelEntity {
    private String gas_station_name;
    private int id_fuel;
    private int fuel_code;
    private String fuel_type;
    private String unit;
    private int price_one_litr;
    private int fuel_amount;
    private int firm_id_firm;

    public FuelEntity(int id_fuel, int fuel_code, String fuel_type, String unit, int price_one_litr,
                      int fuel_amount, int firm_id_firm) {
        this.id_fuel = id_fuel;
        this.fuel_code = fuel_code;
        this.fuel_type = fuel_type;
        this.unit = unit;
        this.price_one_litr = price_one_litr;
        this.fuel_amount = fuel_amount;
        this.firm_id_firm = firm_id_firm;
    }

    public FuelEntity(String fuel_type, String gas_station_name, int price_one_litr) {
        this.fuel_type = fuel_type;
        this.gas_station_name=gas_station_name;
        this.price_one_litr = price_one_litr;
    }

    @Override
    public String toString() {
        return "FuelEntity{" +
                "id_fuel=" + id_fuel +
                ", fuel_code=" + fuel_code +
                ", fuel_type='" + fuel_type + '\'' +
                ", unit='" + unit + '\'' +
                ", price_one_litr=" + price_one_litr +
                ", fuel_amount=" + fuel_amount +
                ", firm_id_firm=" + firm_id_firm +
                '}';
    }

    public int getId_fuel() {
        return id_fuel;
    }

    public void setId_fuel(int id_fuel) {
        this.id_fuel = id_fuel;
    }

    public int getFuel_code() {
        return fuel_code;
    }

    public void setFuel_code(int fuel_code) {
        this.fuel_code = fuel_code;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice_one_litr() {
        return price_one_litr;
    }

    public void setPrice_one_litr(int price_one_litr) {
        this.price_one_litr = price_one_litr;
    }

    public int getFuel_amount() {
        return fuel_amount;
    }

    public void setFuel_amount(int fuel_amount) {
        this.fuel_amount = fuel_amount;
    }

    public int getFirm_id_firm() {
        return firm_id_firm;
    }

    public void setFirm_id_firm(int firm_id_firm) {
        this.firm_id_firm = firm_id_firm;
    }

    public String getGas_station_name() {
        return gas_station_name;
    }

    public void setGas_station_name(String gas_station_name) {
        this.gas_station_name = gas_station_name;
    }
}
