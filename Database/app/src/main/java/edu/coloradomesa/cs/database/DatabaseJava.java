package edu.coloradomesa.cs.database;

import android.content.Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB {
    private Connection connection = null;
    private Context context = null;
    public void setContext(Context context) {
        this.context = context;
    }

    public String getUser() {
        return context.getString(R.string.databaseUser);
    }
    public String getHost() {
        return context.getString(R.string.databaseHost);
    }
    public int getPort() {
        return Integer.parseInt(context.getString(R.string.databasePort));
    }

    public String getPassword() {
        return context.getString(R.string.databasePassword);
    }
    public String getName() {
        return context.getString(R.string.databaseName);
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("org.postgresql.Driver");
            String address = "jdbc:postgresql://" + getHost() + ":" + getPort() + "/" + getName();
            connection = DriverManager
                    .getConnection(address, getUser(), getPassword());
        }
        return connection;
    }
}