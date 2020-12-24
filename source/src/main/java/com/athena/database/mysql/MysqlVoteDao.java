package com.athena.database.mysql;


import com.athena.database.Database;
import com.athena.database.DatabaseType;
import com.athena.database.error.PersistenceException;
import com.athena.database.model.Vote;
import com.athena.database.table.VoteDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public final class MysqlVoteDao implements VoteDao {

	public static final VoteDao INSTANCE = new MysqlVoteDao();

	private static final Logger log = LogManager.getLogger();

	private static final String MYSQL_FIND_ALL_UNCLAIMED_BY_NAME =
		"SELECT id, username, started, ip_address, site_id " +
		"FROM yanille_vote.fx_votes " +
		"WHERE claimed=0 AND username=? AND callback_date IS NOT NULL";

	private static final String MYSQL_SET_CLAIMED_BY_ID =
		"UPDATE yanille_vote.fx_votes " +
		"SET claimed=1 " +
		"WHERE id=?";

	@Override
	public List<Vote> findAndCloseAllByName(String name) {
		checkNotNull(name);
		List<Vote> list = new ArrayList<>();
		try (Connection connection = Database.getDataSource(DatabaseType.WEB).getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(MYSQL_FIND_ALL_UNCLAIMED_BY_NAME)) {
				statement.setString(1, name.replaceAll(" ", "_"));
				var resultSet = statement.executeQuery();
				while (resultSet.next()) {
					list.add(unwrap(resultSet));
				}
				// If we have no results, return an empty list.
				// Otherwise we will first close each result.
				if (list.isEmpty()) return Collections.emptyList();
			}

			try (var statement = connection.prepareStatement(MYSQL_SET_CLAIMED_BY_ID)) {
				for (Vote vote : list) {
					statement.setInt(1, vote.getId());
					statement.addBatch();
				}
				int[] updateCounts = statement.executeBatch();
				// Ensure that all the votes really did change.
				for (int i = 0; i < list.size(); i++) {
					Vote vote = list.get(i);
					if (updateCounts[i] == 1) {
						log.warn("Found and claimed vote (id: {}) for {}.",
						          vote.getId(), vote.getName());
					} else {
						log.warn("Failed to close vote (id: {}) for {}. Skipping it.",
						         vote.getId(), vote.getName());
						list.remove(vote);
					}
				}
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		return list;
	}

	private Vote unwrap(ResultSet resultSet) throws SQLException {
		var id = resultSet.getInt("id");
		var name = resultSet.getString("username");
		var ip = resultSet.getString("ip_address");
		var time = resultSet.getTimestamp("started").toLocalDateTime();
		var siteId = resultSet.getInt("site_id");
		return new Vote(id, name, ip, time, siteId, true); // must be claimed from findAndCloseAllByName
	}

}
