package com.kevin.comparison.vertx;

import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;

public class PGUtil {

        public final static PgConnectOptions connectOptions = new PgConnectOptions()
                        .setPort(5432)
                        .setHost("localhost")
                        .setDatabase("feed")
                        .setUser("postgres")
                        .setPassword("postgres");
        public final static PoolOptions poolOptions = new PoolOptions().setName("vertx").setMaxSize(10);

}
