package com.athena.world.content.skill.impl.dungeoneering;
import com.athena.model.GameObject;
import com.athena.model.Position;
import com.athena.world.entity.impl.npc.NPC;

/**
 * I couldn't be arsed to put all npc spawns in the enum.
 * @author Gabriel Hannason
 */
public enum DungeoneeringFloor {


	FIRST_FLOOR(
			new Position(2451, 4935), new Position(2448, 4939),
			new GameObject[] { 
					new GameObject(1276, new Position(2451, 4939)),
					new GameObject(45803, new Position(2455, 4940)), new GameObject(1767, new Position(2477, 4940)) },
			new NPC[][] {
					{ 
					// COMPLEX 1
					
					// 1st part
					new NPC(1265, new Position(2440, 4958)), new NPC(1265, new Position(2443, 4954)),
					new NPC(1265, new Position(2444, 4958)),
					
					// 2nd part
					new NPC(2437, new Position(2460, 4965)), new NPC(2437, new Position(2460, 4961)),
					
					// 3rd part
					new NPC(1880, new Position(2474, 4958)), new NPC(1880, new Position(2477, 4954)),
					new NPC(1880, new Position(2474, 4955)),
					
					// 4th part
					new NPC(6102, new Position(2473, 4940))
					},
					
					{ 
					// COMPLEX 2
						
					// 1st part
					new NPC(1459, new Position(2441, 4958)), new NPC(1459, new Position(2441, 4954)),
					new NPC(1459, new Position(2444, 4954)),
					
					//2nd part
					new NPC(6102, new Position(2460, 4965)), new NPC(6102, new Position(2457, 4961)),
					
					//3rd part
					new NPC(4392, new Position(2477, 4958)), new NPC(4392, new Position(2474, 4958)),
					
					//4th part
					new NPC(52, new Position(2473, 4940)), new NPC(52, new Position(2470, 4938))
					},
					
					{
					// COMPLEX 3
						
					// 1st part
					new NPC(52, new Position(2441, 4958)), new NPC(52, new Position(2441, 4955)),
					new NPC(52, new Position(2443, 4954)), new NPC(52, new Position(2445, 4956)),
					new NPC(52, new Position(2443, 4958)),
					
					
					// 2nd part
					new NPC(52, new Position(2458, 4961)), new NPC(52, new Position(2461, 4961)),
					new NPC(52, new Position(2461, 4966)),new NPC(52, new Position(2458, 4966)),
					new NPC(4392, new Position(2458, 4963)), new NPC(4392, new Position(2461, 4963)),
					
					// 3rd part
					new NPC(4413, new Position(2476, 4956)),
					
					// 4th part
					new NPC(6313, new Position(2472, 4940)) 
					},
					
					{ 
					// COMPLEX 4
						
					// 1st part
					new NPC(4413, new Position(2445, 4956)),
					
					// 2nd part
					new NPC(6313, new Position(2460, 4964)),
					
					// 3rd part
					new NPC(4419, new Position(2475, 4956)), new NPC(4419, new Position(2478, 4956)),
					
					// 4th part
					new NPC(9939, new Position(2472, 4940)) } });
	
	DungeoneeringFloor(Position entrance, Position smuggler, GameObject[] objects, NPC[][] npcs) {
		this.entrance = entrance;
		this.smugglerPosition = smuggler;
		this.objects = objects;
		this.npcs = npcs;
	}

	private Position entrance, smugglerPosition;
	private GameObject[] objects;
	private NPC[][] npcs;
	
	public Position getEntrance() {
		return entrance;
	}

	public Position getSmugglerPosition() {
		return smugglerPosition;
	}

	public GameObject[] getObjects() {
		return objects;
	}
	
	public NPC[][] getNpcs() {
		return npcs;
	}

	public static DungeoneeringFloor forId(int id) {
		for(DungeoneeringFloor floors : DungeoneeringFloor.values()) {
			if(floors != null && floors.ordinal() == id) {
				return floors;
			}
		}
		return null;
	}
}