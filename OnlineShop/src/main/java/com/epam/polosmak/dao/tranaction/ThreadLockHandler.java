package com.epam.polosmak.dao.tranaction;

import java.sql.Connection;

public class ThreadLockHandler {
    private static final ThreadLocal<Connection> CONNECTION_POOL = new ThreadLocal<>();

    public static Connection getConnection() {
        return CONNECTION_POOL.get();
    }

    public static void setConnection(Connection connection) {
        CONNECTION_POOL.set(connection);
    }
}
