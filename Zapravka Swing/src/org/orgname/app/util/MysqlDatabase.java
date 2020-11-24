package org.orgname.app.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MysqlDatabase
{
    private final String address;
    private final int port;
    private final String db;
    private final String user;
    private final String pass;

    private MysqlDataSource source;

    public MysqlDatabase(String address, int port, String db, String user, String pass) {
        this.address = address;
        this.port = port;
        this.db = db;
        this.user = user;
        this.pass = pass;
    }

    public MysqlDatabase(String address, String db, String user, String pass) {
        this(address, 3306, db, user, pass);
    }

    public Connection getConnection() throws SQLException
    {
        if(source == null) {
            source = new MysqlDataSource();

            source.setServerName(address);
            source.setPort(port);
            source.setDatabaseName(db);
            source.setUser(user);
            source.setPassword(pass);

            source.setCharacterEncoding("UTF-8");
            source.setServerTimezone("UTC");
        }

        return source.getConnection();
    }
}
