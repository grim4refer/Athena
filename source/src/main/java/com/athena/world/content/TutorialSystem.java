package com.athena.world.content;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Position;
import com.athena.world.entity.impl.player.Player;

public class TutorialSystem {
	
	public enum TutorialAreas {
		MARKETPLACE("@yel@Market place", 2504, 2539, 1, "@or2@ROCNAR SELLS AND BUYS ITEMS!", 1),
		WHATEVER("@red@Graveyard Guard", 2535, 2542, 1, "@cya@GUARD'S MINIGAME IS EASY AND HAS GOOD REWARDS!", 6);
		
		private String area;
		private String text;
		private int absX;
		private int absY;
		private int height;
		private int updateAreaTick;
		
		private TutorialAreas(String area, int absX, int absY, int height, String text, int updateAreaTick) {
			this.area = area;
			this.absX = absX;
			this.absY = absY;
			this.height = height;
			this.text = text;
			this.updateAreaTick = updateAreaTick;
		}
	}
	
	public static void startTutorial(Player player) {
		TaskManager.submit(new Task(1, player, false) {
			int tick = 0;
			int times = 2;
			TutorialAreas tutArea = null;

			@Override
			public void execute() {
				if (tick == 1 || tick == 6) {
					tutArea = TutorialAreas.values()[(int) TutorialAreas.values().length - times];
					player.getPacketSender().sendInterfaceRemoval();
					player.getPacketSender().sendMessage(tutArea.text);
					player.moveTo(new Position(tutArea.absX, tutArea.absY, tutArea.height));
					times -= 1;
				}
				if(times == 0 && tick == tutArea.updateAreaTick + 4) {
					player.moveTo(new Position(2529, 2527, 1));
					//BankPin.init(player, false);
					StartScreen.open(player);
					stop();
				}
				tick++;
			}
		});
	}

}
