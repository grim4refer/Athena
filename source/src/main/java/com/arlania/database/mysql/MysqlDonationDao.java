package com.arlania.database.mysql;

import com.arlania.database.Database;
import com.arlania.database.DatabaseType;
import com.arlania.database.error.PersistenceException;
import com.arlania.database.model.Donation;
import com.arlania.database.table.DonationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static com.google.common.base.Preconditions.checkNotNull;

public final class MysqlDonationDao implements DonationDao {

	public static final DonationDao INSTANCE = new MysqlDonationDao();

	private static final Logger log = LogManager.getLogger();

	private static final String MYSQL_FIND_ALL_UNCLAIMED_BY_NAME =
		"SELECT id, item_name, item_number, quantity, dateline " +
		"FROM yanille_store.payments " +
		"WHERE claimed=0 AND player_name=?";

	private static final String MYSQL_SET_CLAIMED_BY_ID =
		"UPDATE yanille_store.payments " +
		"SET claimed=1 " +
		"WHERE id=?";

	@Override
	public List<Donation> findAndCloseAllByName(String name) {
		checkNotNull(name);
		List<Donation> list = new ArrayList<>();
		try (var connection = Database.getDataSource(DatabaseType.SHOP).getConnection()) {
			try (var statement = connection.prepareStatement(MYSQL_FIND_ALL_UNCLAIMED_BY_NAME)) {
				statement.setString(1, name);
				var resultSet = statement.executeQuery();
				while (resultSet.next()) {
					list.add(unwrap(resultSet));
				}
				// If we have no results, return an empty list.
				// Otherwise we will first close each result.
				if (list.isEmpty()) return Collections.emptyList();
			}

			try (var statement = connection.prepareStatement(MYSQL_SET_CLAIMED_BY_ID)) {
				for (Donation donation : list) {
					statement.setInt(1, donation.getId());
					statement.addBatch();
				}
				int[] updateCounts = statement.executeBatch();
				// Ensure that all the votes really did change.
				for (int i = 0; i < list.size(); i++) {
					Donation donation = list.get(i);
					if (updateCounts[i] == 1) {
						log.warn("Found and claimed donation (id: {}) for {}.",
						          donation.getId(), name);
					} else {
						log.warn("Failed to close donation (id: {}) for {}. Skipping it.",
						         donation.getId(), name);
						list.remove(donation);
					}
				}
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		return list;
	}

	private Donation unwrap(ResultSet resultSet) throws SQLException {
		var id = resultSet.getInt("id");
		var productId = resultSet.getInt("item_number");
		var quantity = resultSet.getInt("quantity");
		var name = resultSet.getString("item_name");
		var time = resultSet.getTimestamp("dateline").toLocalDateTime();
		return new Donation(id, productId, quantity, name, time);
	}

}
