package com.athena.world.content.minigames;

/**
 * Holds different minigame attributes for a player
 * 
 * @author Gabriel Hannason
 */
public class MinigameAttributes2 {


	public class GraveyardAttributes {

		private int wave;
		private int requiredKills;
		private int level;
		private boolean entered;

		public int decrementAndGetRequiredKills() {
			return this.requiredKills--;
		}

		public int getLevel() {
			return level;
		}

		public int getRequiredKills() {
			return requiredKills;
		}

		public int getWave() {
			return wave;
		}

		public boolean hasEntered() {
			return entered;
		}

		public int incrementAndGetWave() {
			return this.wave++;
		}

		public void incrementLevel() {
			this.level++;
		}

		public GraveyardAttributes setEntered(boolean entered) {
			this.entered = entered;
			return this;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public void setRequiredKills(int requiredKills) {
			this.requiredKills = requiredKills;
		}

		public GraveyardAttributes setWave(int wave) {
			this.wave = wave;
			return this;
		}
	}

	public class NomadAttributes {
		private boolean[] questParts = new boolean[2];

		public boolean[] getQuestParts() {
			return questParts;
		}

		public boolean hasFinishedPart(int index) {
			return questParts[index];
		}

		public void reset() {
			questParts = new boolean[2];
		}

		public void setPartFinished(int index, boolean finished) {
			questParts[index] = finished;
		}

		public void setQuestParts(boolean[] questParts) {
			this.questParts = questParts;
		}
	}

	public class PestControlAttributes {

		private int damageDealt;

		public PestControlAttributes() {

		}

		public int getDamageDealt() {
			return damageDealt;
		}

		public void incrementDamageDealt(int damageDealt) {
			this.damageDealt += damageDealt;
		}

		public void setDamageDealt(int damageDealt) {
			this.damageDealt = damageDealt;
		}
	}

	public class RecipeForDisasterAttributes {
		private int wavesCompleted;
		private boolean[] questParts = new boolean[9];

		public boolean[] getQuestParts() {
			return questParts;
		}

		public int getWavesCompleted() {
			return wavesCompleted;
		}

		public boolean hasFinishedPart(int index) {
			return questParts[index];
		}

		public void reset() {
			questParts = new boolean[9];
			wavesCompleted = 0;
		}

		public void setPartFinished(int index, boolean finished) {
			questParts[index] = finished;
		}

		public void setQuestParts(boolean[] questParts) {
			this.questParts = questParts;
		}

		public void setWavesCompleted(int wavesCompleted) {
			this.wavesCompleted = wavesCompleted;
		}
	}

	private final RecipeForDisasterAttributes rfdAttributes = new RecipeForDisasterAttributes();

	private final NomadAttributes nomadAttributes = new NomadAttributes();

	/*
	 * public class SoulWarsAttributes { private int activity = 30; private int
	 * productChosen = -1; private int team = -1;
	 *
	 * public int getActivity() { return activity; }
	 *
	 * public void setActivity(int activity) { this.activity = activity; }
	 *
	 * public int getProductChosen() { return productChosen; }
	 *
	 * public void setProductChosen(int prodouctChosen) { this.productChosen =
	 * prodouctChosen; }
	 *
	 * public int getTeam() { return team; }
	 *
	 * public void setTeam(int team) { this.team = team; } }
	 */

	private final GraveyardAttributes graveyardAttributes = new GraveyardAttributes();


	public MinigameAttributes2() {
	}


	public GraveyardAttributes getGraveyardAttributes() {
		return graveyardAttributes;
	}

	public NomadAttributes getNomadAttributes() {
		return nomadAttributes;
	}

	public RecipeForDisasterAttributes getRecipeForDisasterAttributes() {
		return rfdAttributes;
	}

}
