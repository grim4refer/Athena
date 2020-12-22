package mysql.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import com.arlania.model.PlayerRights;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.entity.impl.player.Player;

/**
 * Using this class: To call this class, it's best to make a new thread. You can
 * do it below like so: new Thread(new Donation(player)).start();
 */
public class Donation implements Runnable {

	public static final String HOST = "198.12.15.35";
	public static final String USER = "yanillen_store";
	public static final String PASS = "8(uVGjfq;GBA";													
	public static final String DATABASE = "yanillen_store";

	private Player player;
	private Connection conn;
	private Statement stmt;

	/**
	 * The constructor
	 *  no sec
	 * @param player
	 */
	public Donation(Player player) {
		this.player = player;
	}
	@Override
	public void run() {
		if (System.currentTimeMillis() - player.lastDonationClaim < TimeUnit.SECONDS.toMillis(30)) {
			player.sendMessage("@red@You cant claim for another 30 seconds, please wait.");
			return;
		}
		try {
			if (!connect(HOST, DATABASE, USER, PASS)) {
				return;
			}
			String name = player.getUsername().replace("_", " ");
			ResultSet rs = executeQuery("SELECT * FROM payments WHERE player_name='"+name+"' AND status='Completed' AND claimed=0");
			while (rs.next()) {
				int item_number = rs.getInt("item_number");
				double paid = rs.getDouble("amount");
				int quantity = 1 * rs.getInt("quantity");
				switch (item_number) {// add products according to their ID in
										// the ACP			
				case 96: 
					player.getInventory().add(619, quantity); 
					player.sendMessage("Thanks for donating!");
					World.sendMessage("[<img=10>][@red@Donation@bla@] @red@" + player.getUsername()
							+ "@bla@ has just donated Thanks for @red@Contributing@bla@!");
					Misc.println_debug(""+player.getUsername()+" has just donated $"+paid+"");
					break; 
					
				case 97: 
					player.getInventory().add(15356, quantity); 
					player.sendMessage("Thanks for donating!");
					World.sendMessage("[<img=10>][@red@Donation@bla@] @red@" + player.getUsername()
							+ "@bla@ has just donated Thanks for @red@Contributing@bla@!");
					Misc.println_debug(""+player.getUsername()+" has just donated $"+paid+"");
					break;

				case 98: 
					player.getInventory().add(15355, quantity); 
					player.sendMessage("Thanks for donating!");
					World.sendMessage("[<img=10>][@red@Donation@bla@] @red@" + player.getUsername()
							+ "@bla@ has just donated Thanks for @red@Contributing@bla@!");
					Misc.println_debug(""+player.getUsername()+" has just donated $"+paid+"");
					break; 
					
				case 99: 
					player.getInventory().add(15359, quantity); 
					player.sendMessage("Thanks for donating!");
					World.sendMessage("[<img=10>][@red@Donation@bla@] @red@" + player.getUsername()
							+ "@bla@ has just donated Thanks for @red@Contributing@bla@!");
					Misc.println_debug(""+player.getUsername()+" has just donated $"+paid+"");
					break; 
					
				case 100: 
					player.getInventory().add(15358, quantity); 
					player.sendMessage("Thanks for donating!");
					World.sendMessage("[<img=10>][@red@Donation@bla@] @red@" + player.getUsername()
							+ "@bla@ has just donated Thanks for @red@Contributing@bla@!");
					Misc.println_debug(""+player.getUsername()+" has just donated $"+paid+"");
					break; 
				} 
				rs.updateInt("claimed", 1); // set claimed(row) to 1. (= cant claim twice)
				rs.updateRow(); // send update to sql.
				player.lastDonationClaim = System.currentTimeMillis(); 
				Misc.println_debug("Purchased Name: "+player.getUsername()+" - information saved upon donation.");
				Misc.println_debug("Purchased Amount: "+paid+" - information saved upon donation.");
				Misc.println_debug("Host Address: "+player.getHostAddress()+" - information has been uploaded.");
				Misc.println_debug("Serial Number: "+player.getSerialNumber()+" - information has been uploaded.");
				Misc.println_debug("Payment Method: "+player.getMethod()+" - information has been uploaded.");
				Misc.println_debug("Claimed At: "+player.getClaimedAt()+" - information has been uploaded.");
			}
			player.getClaimedAt(); //reload ur server, got to check something. okay
			player.getMethod();
			player.getSerialNumber();
			player.getHostAddress();
			destroy(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, pass);
			System.out.println("YES LORD!");
			return true;
		} catch (SQLException e) {
			System.out.println("Failing connecting to database!");
			return false; //done updatekk sec
		}
	}

	/**
	 * Disconnects from the MySQL server and destroy the connection and
	 * statement instances
	 */
	public void destroy() {
		try {
			conn.close();
			conn = null;
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes an update query on the database
	 * 
	 * @param query
	 * @see {@link Statement#executeUpdate}
	 */
	public int executeUpdate(String query) {
		try {
			this.stmt = this.conn.createStatement(1005, 1008);
			int results = stmt.executeUpdate(query);
			return results;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	/**
	 * Executres a query on the database
	 * 
	 * @param query
	 * @see {@link Statement#executeQuery(String)}
	 * @return the results, never null
	 */
	public ResultSet executeQuery(String query) {
		try {
			this.stmt = this.conn.createStatement(1005, 1008);
			ResultSet results = stmt.executeQuery(query);
			return results;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}