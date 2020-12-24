package com.athena.database.table;



import com.athena.database.model.Donation;

import java.util.List;

public interface DonationDao {

	List<Donation> findAndCloseAllByName(String name);

}
