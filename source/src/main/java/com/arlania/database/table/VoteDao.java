package com.arlania.database.table;



import com.arlania.database.model.Vote;

import java.util.List;

public interface VoteDao {

	List<Vote> findAndCloseAllByName(String name);

}
