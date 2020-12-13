package org.orgname.app.database.entity;

public class DailySaleEntity {
    private int id_daily_sale;
    private String daily_fuel_type;
    private String daily_fuel_amount;
    private int client_id_client;
    private int fuel_has_gas_station_id_fuel_has_gas_station;
    private String daily_sale_date;
    private String sale_price;
    private String max_fuel_type;

    public DailySaleEntity(int id_daily_sale, String daily_fuel_type, String daily_fuel_amount,
                           int client_id_client, int fuel_has_gas_station_id_fuel_has_gas_station,
                           String daily_sale_date,
                           String sale_price, String max_fuel_type) {
        this.id_daily_sale = id_daily_sale;
        this.daily_fuel_type = daily_fuel_type;
        this.daily_fuel_amount = daily_fuel_amount;
        this.client_id_client = client_id_client;
        this.fuel_has_gas_station_id_fuel_has_gas_station = fuel_has_gas_station_id_fuel_has_gas_station;
        this.daily_sale_date = daily_sale_date;
        this.sale_price = sale_price;
        this.max_fuel_type = max_fuel_type;
    }

    public DailySaleEntity(String daily_fuel_type, String max_fuel_type) {
        this.daily_fuel_type = daily_fuel_type;
        this.max_fuel_type = max_fuel_type;
    }

    public DailySaleEntity(String daily_sale_date, String daily_fuel_type,
                           String daily_fuel_amount, String sale_price) {
        this.daily_sale_date = daily_sale_date;
        this.daily_fuel_type = daily_fuel_type;
        this.daily_fuel_amount = daily_fuel_amount;
        this.sale_price = sale_price;
    }

    @Override
    public String toString() {
        return "DailySaleEntity{" +
                "id_daily_sale=" + id_daily_sale +
                ", daily_fuel_type='" + daily_fuel_type + '\'' +
                ", daily_fuel_amount=" + daily_fuel_amount +
                ", client_id_client=" + client_id_client +
                ", fuel_has_gas_station_id_fuel_has_gas_station=" + fuel_has_gas_station_id_fuel_has_gas_station +
                ", daily_sale_date='" + daily_sale_date + '\'' +
                ", sale_price=" + sale_price +
                '}';
    }

    public int getId_daily_sale() {
        return id_daily_sale;
    }

    public void setId_daily_sale(int id_daily_sale) {
        this.id_daily_sale = id_daily_sale;
    }

    public String getDaily_fuel_type() {
        return daily_fuel_type;
    }

    public void setDaily_fuel_type(String daily_fuel_type) {
        this.daily_fuel_type = daily_fuel_type;
    }

    public String getDaily_fuel_amount() {
        return daily_fuel_amount;
    }

    public void setDaily_fuel_amount(String daily_fuel_amount) {
        this.daily_fuel_amount = daily_fuel_amount;
    }

    public int getClient_id_client() {
        return client_id_client;
    }

    public void setClient_id_client(int client_id_client) {
        this.client_id_client = client_id_client;
    }

    public int getFuel_has_gas_station_id_fuel_has_gas_station() {
        return fuel_has_gas_station_id_fuel_has_gas_station;
    }

    public void setFuel_has_gas_station_id_fuel_has_gas_station(int fuel_has_gas_station_id_fuel_has_gas_station) {
        this.fuel_has_gas_station_id_fuel_has_gas_station = fuel_has_gas_station_id_fuel_has_gas_station;
    }

    public String getDaily_sale_date() {
        return daily_sale_date;
    }

    public void setDaily_sale_date(String daily_sale_date) {
        this.daily_sale_date = daily_sale_date;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getMax_fuel_type() {
        return max_fuel_type;
    }

    public void setMax_fuel_type(String max_fuel_type) {
        this.max_fuel_type = max_fuel_type;
    }
}
