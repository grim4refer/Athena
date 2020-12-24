package mysql.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;


public class Vote implements Runnable {
	
	public static final String HOST = "198.12.15.35";
	public static final String USER = "yanillen_vote";
	public static final String PASS = "kQq=?J_-r8Pc";													
	public static final String DATABASE = "yanillen_vote";

	private Player player;
	private Connection conn;
	private Statement stmt;

	public Vote(Player c) {
		this.player = c;
	}

	@Override
	public void run() {
		try {
			if (!connect(HOST, DATABASE, USER, PASS)) {
				return;
			}
			player.isVoting = true;
			String name = player.getUsername().replace(" ", "_");
			ResultSet rs = executeQuery("SELECT * FROM fx_votes WHERE username='"+name+"' AND claimed=0");
			if(rs.next() == false) {
				player.sendMessage("You do not have any votes too claim.");
				destroy();
				player.isVoting = false;
				return;
			}
			while (rs.next()) {
				player.getInventory().add(19670, 1);//ITEM ID -- AMONT
				player.sendMessage("Thanks for voting!");
				World.sendMessage("[<img=10>][@red@Voting@bla@] @red@" + player.getUsername()
						+ "@bla@ has just voted Thanks for @red@Contributing@bla@!");

				System.out.println("claim");

				rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
				rs.updateRow();
			}
			player.isVoting = false;
			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
			return true;
		} catch (SQLException e) {
			System.out.println("Failing connecting to database!");
			return false;
		}
	}

	public void destroy() {
        try {
    		conn.close();
        	conn = null;
        	if (stmt != null) {
    			stmt.close();
        		stmt = null;
        	}
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

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
