package com.athena.database;

import com.zaxxer.hikari.pool.HikariPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Database {

	private static final Logger log = LogManager.getLogger();

	public static void init() {
		try {
			for(DatabaseType db : DatabaseType.values()) {
				pools.put(db, db.getPool());
			}

		}catch (Exception e) {
		log.error("Error connecting to Database!", e);
	}

}

	private static HikariPool pool;

	private static Map<DatabaseType, HikariPool> pools = new LinkedHashMap<>();

	public static DataSource getDataSource(DatabaseType type) {
		final HikariPool pool = type.getPool();
		if (pool == null)
			throw new IllegalStateException(type.name()+" pool is unavailable");
		return pool.getUnwrappedDataSource();
	}

	public static void shutdown() {
		log.warn("Shutting down database connection...");


		pools.forEach((type, pool) -> {
			if (pool != null) {
				try {
					pool.shutdown();
					pool = null;
					log.warn("{} db pool shut down", type.name());
				} catch (Exception e) {
					log.warn("Error shutting {} db pool down.", type.name());
					Thread.currentThread().interrupt();
				}
			}
		});
	}
}
