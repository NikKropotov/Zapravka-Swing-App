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
//    public FuelEntity getById(int id) throws SQLException {
//        try (Connection c = database.getConnection()) {
//            String sql = "SELECT * FROM fuel WHERE id_client=?";
//            PreparedStatement s = c.prepareStatement(sql);
//            s.setInt(1, id);
//            ResultSet result = s.executeQuery();
//            if (result.next()) {
//                return new UserEntity(
//                        result.getInt("id_client"),
//                        result.getString("login"),
//                        result.getString("password"),
//                        result.getString("client_fio"),
//                        result.getString("client_account_amount"),
//                        result.getInt("client_phone")
//                );
//            }
//            return null;
//        }
//    }

    public static List<FuelEntity> getAllFuels() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM fuel_station";
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

}
