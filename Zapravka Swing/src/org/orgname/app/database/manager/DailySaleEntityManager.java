package org.orgname.app.database.manager;

import org.orgname.app.database.entity.DailySaleEntity;
import org.orgname.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DailySaleEntityManager {
    private static MysqlDatabase database;

    public DailySaleEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public static List<DailySaleEntity> getStatisticTopFuels() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "select `daily_fuel_type`, count(*) AS max_fuel_type from daily_sale \n" +
                    "group by `daily_fuel_type` order by max_fuel_type desc;";
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(sql);

            List<DailySaleEntity> sales = new ArrayList<>();
            while (result.next()) {
                sales.add(new DailySaleEntity(
                        result.getString("daily_fuel_type"),
                        result.getString("max_fuel_type")
                ));
            }
            return sales;
        }
    }

    public static List<DailySaleEntity> getStatisticTopFuelsByDate(int m) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "select daily_fuel_type, count(*) AS max_fuel_type \n" +
                    "from daily_sale \n" +
                    "where MONTH(daily_sale_date) = ? \n" +
                    "group by `daily_fuel_type` \n" +
                    "order by max_fuel_type desc;";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, m);

            ResultSet result = s.executeQuery();

            List<DailySaleEntity> sales = new ArrayList<>();
            while (result.next()) {
                sales.add(new DailySaleEntity(
                        result.getString("daily_fuel_type"),
                        result.getString("max_fuel_type")
                ));
            }
            return sales;
        }
    }

    public static List<DailySaleEntity> getHistorySale(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "select daily_sale.`daily_sale_date`, \n" +
                    "daily_sale.`daily_fuel_type`,\n" +
                    "daily_sale.`daily_fuel_amount`, `daily_sale`.`sale_price`\n" +
                    "from `client`, daily_sale\n" +
                    "where `client`.`id_client` = daily_sale.`client_id_client`\n" +
                    "AND `client`.`id_client` = ?\n" +
                    "order by daily_sale.`daily_sale_date` desc;";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet result = s.executeQuery();

            List<DailySaleEntity> sales = new ArrayList<>();
            while (result.next()) {
                sales.add(new DailySaleEntity(
                        result.getString("daily_sale_date"),
                        result.getString("daily_fuel_type"),
                        result.getString("daily_fuel_amount"),
                        result.getString("sale_price")
                ));
            }
            return sales;
        }
    }
}
