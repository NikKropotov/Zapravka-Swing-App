package org.orgname.app.database.manager;

import org.orgname.app.database.entity.FirmEntity;
import org.orgname.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FirmEntityManager {
    private static MysqlDatabase database;

    public FirmEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public static List<FirmEntity> getAllFirm() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT firm_name, firm_address, firm_phone FROM firm;";
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(sql);

            List<FirmEntity> firms = new ArrayList<>();
            while (result.next()) {
                firms.add(new FirmEntity(
                        result.getString("firm_name"),
                        result.getString("firm_address"),
                        result.getString("firm_phone")
                ));
            }
            return firms;
        }
    }
}
