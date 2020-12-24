package com.athena.database.table;



import com.athena.database.model.Vote;

import java.util.List;

public interface VoteDao {

	List<Vote> findAndCloseAllByName(String name);

}
