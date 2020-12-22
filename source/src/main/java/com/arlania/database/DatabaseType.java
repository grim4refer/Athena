package com.arlania.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;

public enum DatabaseType {

    GAME(new HikariPool(            new HikariConfig("data/sql/game_sql.properties"))),
    SHOP(new HikariPool(            new HikariConfig("data/sql/shop_sql.properties"))),
    WEB(new HikariPool(             new HikariConfig("data/sql/web_sql.properties"))),
    HIGHSCORES(new HikariPool(      new HikariConfig("data/sql/highscores_sql.properties"))),
    FORUM(new HikariPool(           new HikariConfig("data/sql/forum_sql.properties")));


    private HikariPool pool;
    
        DatabaseType(HikariPool pool) {
            this.pool = pool;
        }

    public HikariPool getPool() {
        return pool;
    }
}
