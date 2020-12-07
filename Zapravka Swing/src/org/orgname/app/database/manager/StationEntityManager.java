package org.orgname.app.database.manager;

import org.orgname.app.database.entity.FuelEntity;
import org.orgname.app.database.entity.StationEntity;
import org.orgname.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StationEntityManager {
    private static MysqlDatabase database;

    public StationEntityManager(MysqlDatabase database) {
        this.database = database;
    }


    public static List<StationEntity> getAllStations() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT gas_station_code, gas_station_name, gas_station_address FROM gas_station";
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(sql);

            List<StationEntity> stations = new ArrayList<>();
            while (result.next()) {
                stations.add(new StationEntity(
                        result.getInt("gas_station_code"),
                        result.getString("gas_station_address"),
                        result.getString("gas_station_name")
                ));
            }
            return stations;
        }
    }
}
