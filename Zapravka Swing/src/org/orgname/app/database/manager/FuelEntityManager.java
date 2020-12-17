package org.orgname.app.database.manager;

import org.orgname.app.database.entity.FuelEntity;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuelEntityManager {
    private static MysqlDatabase database;

    public FuelEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    private static boolean isFuelExists = false;

    public static boolean isIsFuelExists() {
        return isFuelExists;
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


    public void add_fuel(FuelEntity fuel) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO fuel(fuel_code, fuel_type, \n" +
                    "unit, price_one_litr, fuel_amount, \n" +
                    "firm_id_firm) \n" +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, fuel.getFuel_code());
            s.setString(2, fuel.getFuel_type());
            s.setString(3, fuel.getUnit());
            s.setInt(4, fuel.getPrice_one_litr());
            s.setInt(5, fuel.getFuel_amount());
            s.setInt(6, fuel.getFirm_id_firm());
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                isFuelExists = true;
                fuel.setId_fuel(keys.getInt(1));
                return;
            }
            throw new SQLException("Fuel not added");
        }
    }

    public FuelEntity getStaionByName(String name) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT id_gas_station\n" +
                    "FROM gas_station\n" +
                    "where gas_station.gas_station_name = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, name);
            ResultSet result = s.executeQuery();
            if (result.next()) {
                return new FuelEntity(
                        result.getInt("id_gas_station")
                );
            }
            return null;
        }
    }

    public FuelEntity getIDfuel(FuelEntity fuel) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT id_fuel FROM fuel\n" +
                    "where fuel_code = ?\n" +
                    " AND fuel_type = ?\n" +
                    " AND unit = ?\n" +
                    " AND price_one_litr = ?\n" +
                    " AND fuel_amount = ?\n" +
                    " AND firm_id_firm = ?\n";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, fuel.getFuel_code());
            s.setString(2, fuel.getFuel_type());
            s.setString(3, fuel.getUnit());
            s.setInt(4, fuel.getPrice_one_litr());
            s.setInt(5, fuel.getFuel_amount());
            s.setInt(6, fuel.getFirm_id_firm());
            ResultSet result = s.executeQuery();
            if (result.next()) {
                return new FuelEntity(
                        result.getInt("id_fuel")
                );
            }
            return null;
        }
    }

    public void add_fuel_station(FuelEntity fuel, FuelEntity gas) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO fuel_has_gas_station (fuel_id_fuel, gas_station_id_gas_station) \n" +
                    "VALUES (?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, fuel.getId_fuel());
            s.setInt(2, gas.getId_gas_station());
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                isFuelExists = true;
                fuel.setId_fuel_has_gas_station(keys.getInt(1));
                return;
            }
            throw new SQLException("Fuel not added");
        }
    }

    public int update_fuel_station(FuelEntity gas, int fuelEntity) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE fuel_has_gas_station SET gas_station_id_gas_station = ? WHERE fuel_id_fuel = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, gas.getId_gas_station());
            s.setInt(2, fuelEntity);
            return s.executeUpdate();
        }
    }

    public int update_fuel(FuelEntity fuel) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE `fuel` SET \n" +
                    "`fuel_code` = ?, `fuel_type` = ?, \n" +
                    "`unit` = ?, `price_one_litr` = ?, \n" +
                    "`fuel_amount` = ?, `firm_id_firm` = ? \n" +
                    "WHERE (`id_fuel` = ?);\n";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, fuel.getFuel_code());
            s.setString(2, fuel.getFuel_type());
            s.setString(3, fuel.getUnit());
            s.setInt(4, fuel.getPrice_one_litr());
            s.setInt(5, fuel.getFuel_amount());
            s.setInt(6, fuel.getFirm_id_firm());
            s.setInt(7, fuel.getId_fuel());
            return s.executeUpdate();
        }
    }

    public int deleteFuelById(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM fuel WHERE id_fuel = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            return s.executeUpdate();
        }
    }

    public int deleteFuel_Has_StationById(int id, int id_gas_station) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE  FROM fuel_has_gas_station WHERE id_fuel_has_gas_station IN (\n" +
                    "select * from (\n" +
                    "SELECT id_fuel_has_gas_station FROM fuel, fuel_has_gas_station, gas_station\n" +
                    "where fuel_id_fuel = id_fuel\n" +
                    "AND gas_station_id_gas_station = id_gas_station\n" +
                    "AND id_fuel = ? \n" +
                    "AND gas_station_id_gas_station = ? \n" +
                    ")as id\n" +
                    ")";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.setInt(2, id_gas_station);
            return s.executeUpdate();
        }
    }


}
