package com.arlania.database.table;



import com.arlania.database.model.Donation;

import java.util.List;

public interface DonationDao {

	List<Donation> findAndCloseAllByName(String name);

}
