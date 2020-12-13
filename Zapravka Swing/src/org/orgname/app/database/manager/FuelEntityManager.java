package org.orgname.app.database.manager;

import org.orgname.app.database.entity.FuelEntity;
import org.orgname.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuelEntityManager {
    private static MysqlDatabase database;

    public FuelEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public static List<FuelEntity> getAllFuels() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT DISTINCT * FROM fuel_station";
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(sql);

            List<FuelEntity> fuels = new ArrayList<>();
            while (result.next()) {
                fuels.add(new FuelEntity(
                        result.getString("fuel_type"),
                        result.getString("gas_station_name"),
                        result.getInt("price_one_litr")
                ));
            }
            return fuels;
        }
    }

    public static List<FuelEntity> getAllOnlyFuels() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT DISTINCT * FROM fuel_station_fuel";
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(sql);

            List<FuelEntity> fuels = new ArrayList<>();
            while (result.next()) {
                fuels.add(new FuelEntity(
                        result.getInt("id_fuel"),
                        result.getString("fuel_type"),
                        result.getInt("price_one_litr"),
                        result.getInt("fuel_amount"),
                        result.getInt("id_gas_station"),
                        result.getString("gas_station_address"),
                        result.getString("gas_station_name")
                ));
            }
            return fuels;
        }
    }

    public int deleteById(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM fuel WHERE id_fuel=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            return s.executeUpdate();
        }
    }

    public FuelEntity getFuelById(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM fuel WHERE id_fuel=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet result = s.executeQuery();
            if (result.next()) {
                return new FuelEntity(
                        result.getInt("id_fuel"),
                        result.getInt("fuel_code"),
                        result.getString("fuel_type"),
                        result.getString("unit"),
                        result.getInt("price_one_litr"),
                        result.getInt("fuel_amount"),
                        result.getInt("firm_id_firm")
                );
            }
            return null;
        }
    }

    public static List<FuelEntity> getFuelByStation(String name) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT DISTINCT * FROM fuel_station_fuel where gas_station_name = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, name);
            ResultSet result = s.executeQuery();

            List<FuelEntity> fuels = new ArrayList<>();
            while (result.next()) {
                fuels.add(new FuelEntity(
                        result.getInt("id_fuel"),
                        result.getString("fuel_type"),
                        result.getInt("price_one_litr"),
                        result.getInt("fuel_amount"),
                        result.getInt("id_gas_station"),
                        result.getString("gas_station_address"),
                        result.getString("gas_station_name")
                ));
            }
            return fuels;
        }
    }

}
