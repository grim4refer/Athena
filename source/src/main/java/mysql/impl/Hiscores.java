/*package mysql.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.athena.model.Skill;
import com.athena.world.entity.impl.player.Player;
 
public class Hiscores implements Runnable {

	public static final String HOST = "198.12.15.35";
	public static final String USER = "yanillen_hs";
	public static final String PASS = "v$%xAh=s)w?-";													
	public static final String DATABASE = "yanillen_hs";

	public static final String TABLE = "hs_users";
	
	private Player player;
	private Connection conn;
	private Statement stmt;
	
	public Hiscores(Player player) {
		this.player = player;
	}
	
	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void run() {
		try {
			if (!connect(HOST, DATABASE, USER, PASS)) {
				return;
			}
			
			String name = player.getUsername();
				
			PreparedStatement stmt2 = prepare(generateQuery(player));
			stmt2.setString(1, name);
			
			stmt2.setInt(2, player.getRights().ordinal());
			stmt2.setInt(3, player.getGameMode().ordinal());
			
			stmt2.setInt(4, player.getSkillManager().getTotalLevel()); // total level
			stmt2.setLong(5, player.getSkillManager().getTotalExp());
			
			for (int i = 0; i < 25; i++)
				stmt2.setInt(6 + i, (int)player.getSkillManager().getExperience(Skill.forId(i)));
			stmt2.execute();
			
			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement prepare(String query) throws SQLException {
		return conn.prepareStatement(query);
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
	
	// been a minute since i touched java...gimme a sec rofl xD
	public static String generateQuery(Player player) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO "+TABLE+" (");
		sb.append("username, ");
		sb.append("rights, ");
		sb.append("mode, ");
		sb.append("total_level, ");
		sb.append("overall_xp, ");
		sb.append("attack_xp, ");
		sb.append("defence_xp, ");
		sb.append("strength_xp, ");
		sb.append("constitution_xp, ");
		sb.append("ranged_xp, ");
		sb.append("prayer_xp, ");
		sb.append("magic_xp, ");
		sb.append("cooking_xp, ");
		sb.append("woodcutting_xp, ");
		sb.append("fletching_xp, ");
		sb.append("fishing_xp, ");
		sb.append("firemaking_xp, ");
		sb.append("crafting_xp, ");
		sb.append("smithing_xp, ");
		sb.append("mining_xp, ");
		sb.append("herblore_xp, ");
		sb.append("agility_xp, ");
		sb.append("thieving_xp, ");
		sb.append("slayer_xp, ");
		sb.append("farming_xp, ");
		sb.append("runecrafting_xp, ");
		sb.append("hunter_xp, ");
		sb.append("construction_xp, ");
		sb.append("summoning_xp, ");
		sb.append("dungeoneering_xp) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		
		sb.append("ON DUPLICATE KEY UPDATE ");
		sb.append("rights = "+player.getRights().ordinal()+",");
		sb.append("mode = "+player.getGameMode().ordinal()+", ");
		sb.append("total_level = "+player.getSkillManager().getTotalLevel()+", ");
		sb.append("overall_xp = "+player.getSkillManager().getTotalExp()+", ");
		
		for (int i = 0; i < Skill.values().length; i++) {
			long experience = (int)player.getSkillManager().getExperience(Skill.forId(i));
			
			sb.append(Skill.forId(i).getName()+"_xp = "+experience);
			sb.append(i == (Skill.values().length - 1) ? "" : ", "); // was 100% that. not sure why i put that there lol
		
		}
		
		return sb.toString();
	}
	
}*/