package com.athena.database.table;



import com.athena.database.model.Vote;

import java.util.List;


public interface RefferalRewardDao {

	List<Vote> findAndCloseAllByName(String name);

}
