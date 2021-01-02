package com.athena.world.content;

import com.athena.GameLoader;
import com.athena.util.Misc;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;

/*
 * @author Arlania - Arlania rsps
 */
public class TriviaBot {
	
	public static final int TIMER = 1200; //1800
	public static int botTimer = TIMER;
	
	public static int answerCount;
	public static String firstPlace;
	public static String secondPlace;
	public static String thirdPlace;

	//public static List<String> attempts = new ArrayList<>(); 

	public static void sequence() {
		
		if(botTimer > 0)
			botTimer--;
		if(botTimer <= 0) {
			botTimer = TIMER;
			didSend = false;
			askQuestion();
		}
	}
	
	public static void attemptAnswer(Player p, String attempt) {
		
		if (!currentQuestion.equals("") && attempt.replaceAll("_", " ").equalsIgnoreCase(currentAnswer)) {
			
			if (answerCount == 0) {
				answerCount++;
				p.getPointsHandler().incrementTriviaPoints(GameLoader.doubleTriviaPoints() ? 20 : 10);
				p.getPacketSender().sendMessage("You Received 10 trivia points for @red@1st Place.");
				p.getPointsHandler().refreshPanel();
				firstPlace = p.getUsername();
				World.sendMessage("@blu@[TRIVIA] @bla@[Winner:@blu@" +firstPlace+"@red@ (10 pts)@bla@]");
			} else if (answerCount == 1) {
				answerCount++;
				p.getPointsHandler().incrementTriviaPoints(GameLoader.doubleTriviaPoints() ? 14 : 7);
				p.getPacketSender().sendMessage("You Received 7 trivia points for @red@2nd Place.");
				p.getPointsHandler().refreshPanel();
				secondPlace = p.getUsername();
				World.sendMessage("@blu@[TRIVIA] @bla@[Winner:@blu@" +secondPlace+"@red@ (7 pts)@bla@]");
			} else if (answerCount == 2) {
				answerCount++;
				p.getPointsHandler().incrementTriviaPoints(GameLoader.doubleTriviaPoints() ? 10 : 5);
				p.getPacketSender().sendMessage("You Received 5 trivia points for @red@3rd Place.");
				p.getPointsHandler().refreshPanel();
				thirdPlace = p.getUsername();
				World.sendMessage("@blu@[TRIVIA] @bla@[Winner:@blu@" +thirdPlace+"@red@ (5 pts)@bla@]");
				//String[] s = Arrays.asList(attempts);
				//World.sendMessage("@blu@[TRIVIA] @red@Failed attempts: "+s);
				World.sendMessage("@red@Note: @mag@You can only answer 3 times correctly every 20 minutes!");
				currentQuestion = "";
				didSend = false;
				botTimer = TIMER;
				answerCount = 0;
			}
		} else {
			if(attempt.contains("question") || attempt.contains("repeat")){
				p.getPacketSender().sendMessage("<col=800000>"+(currentQuestion));
				return;
			}

			//attempts.add(attempt); // need to add a filter for bad strings (advs, curses)
			p.getPacketSender().sendMessage("@blu@[TRIVIA]@red@ Sorry! Wrong answer! The current question is: +");
			p.getPacketSender().sendMessage("@blu@[TRIVIA]@red@ "+(currentQuestion));
		}
		
	}
	
	public static boolean acceptingQuestion() {
		return !currentQuestion.equals("");
	}
	
	private static void askQuestion() {
		for (int i = 0; i < TRIVIA_DATA.length; i++) {
			if (Misc.getRandom(TRIVIA_DATA.length - 1) == i) {
				if(!didSend) {
					didSend = true;
				currentQuestion = TRIVIA_DATA[i][0];
				currentAnswer = TRIVIA_DATA[i][1];
				World.sendMessage(currentQuestion);
				
				
				}
			}
		}
	}
	
	public static boolean didSend = false;
	
	private static final String[][] TRIVIA_DATA = {
		{"@blu@[TRIVIA]@red@ raeldu", "Durael"},
		{"@blu@[TRIVIA]@red@ lod is wenam", "Wise Old Man"},
		{"@blu@[TRIVIA]@red@ shan", "Hans"},
		{"@blu@[TRIVIA]@red@ mizaze", "Zezima"},
		{"@blu@[TRIVIA]@red@ eifr eunr", "fire rune"},
		{"@blu@[TRIVIA]@red@ csrylat eky", "crystal key"},

		{"@blu@[TRIVIA]@red@ What rank has a silver crown on Runescape?", "Moderator"},
		{"@blu@[TRIVIA]@red@ What rank has a golden crown on Runescape?", "Administrator"},
		{"@blu@[TRIVIA]@red@ What is the max exp. in a skill?", "200M"},
		{"@blu@[TRIVIA]@red@ How much exp. do you need for level 99?", "13M"},
		{"@blu@[TRIVIA]@red@ What is the largest state in the U.S.A?", "Alaska"},
		{"@blu@[TRIVIA]@red@ What city is the most populated city on earth?", "Tokyo"},
		{"@blu@[TRIVIA]@red@ What is the strongest prayer on Runescape?", "Turmoil"},
		{"@blu@[TRIVIA]@red@ What Herblore level is required to make overloads on Runescape?", "96"},
		{"@blu@[TRIVIA]@red@ What attack level is required to wear Chaotic Melee weapons?", "80"},
		{"@blu@[TRIVIA]@red@ How many bones are there in an adult human body?", "206"},
		{"@blu@[TRIVIA]@red@ What is the deadliest insect on the planet?", "Mosquito"},
		{"@blu@[TRIVIA]@red@ What is the square root of 12 to the power of 2?", "12"},
		{"@blu@[TRIVIA]@red@ What is the color of a 10M money stack?", "Green"},
		{"@blu@[TRIVIA]@red@ What combat level is the almighty Jad?", "702"},
		{"@blu@[TRIVIA]@red@ What is the best Dungeonering armour?", "Primal"},
		{"@blu@[TRIVIA]@red@ How many brothers are there originally in Runescape?", "6"},
		{"@blu@[TRIVIA]@red@ Varrock is the capital of which kingdom?", "Misthalin"},
		{"@blu@[TRIVIA]@red@ What has four legs but can't walk?", "A table"},
		{"@blu@[TRIVIA]@red@ Which NPC is wearing a 2H-Sword and a Dragon SQ Shield?", "Vannaka"},
		{"@blu@[TRIVIA]@red@ What is the baby spider called?", "Spiderling"},
		{"@blu@[TRIVIA-QUESTION]@red@ In what year did it snow in the Sahara Desert?", "1979"},
		{"@blu@[TRIVIA-QUESTION]@red@ The more you take, the more you leave, who am I?", "Footsteps"},

		//{"@blu@[TRIVIA-FINISH-THE-LYRICS]@red@ What is love?", "Baby don't hurt me"},

		{"@blu@[TRIVIA]@red@ Guess a number 1-10?", "5"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-10?", "3"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-10?", "9"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-10?", "9"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-10?", "3"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-10?", "5"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-20?", "5"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-20?", "14"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-20?", "16"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-20?", "12"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-20?", "8"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-20?", "13"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-5?", "5"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-5?", "4"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-5?", "1"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-5?", "4"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-5?", "5"},
		{"@blu@[TRIVIA]@red@ Guess a number 1-5?", "2"},
		
		{"@blu@[TRIVIA]@red@ type the following ::anwser jdj49a39ru357cnf", "jdj49a39ru357cnf"},
		{"@blu@[TRIVIA]@red@ type the following ::anwser qpal29djeifh58cjid", "qpal29djeifh58cjid"},
		{"@blu@[TRIVIA]@red@ type the following ::anwser qd85d4r0md42u2mssd", "qd85d4r0md42u2mssd"},
		{"@blu@[TRIVIA]@red@ type the following ::anwser loski4893dhncbv7539", "loski4893dhncbv7539"},
		{"@blu@[TRIVIA]@red@ type the following ::anwser 9esmf03na9admieutapdz9", "9esmf03na9admieutapdz9"},
		{"@blu@[TRIVIA]@red@ type the following ::anwser djs83adm39s88s84masl", "djs83adm39s88s84masl"},
		{"@blu@[TRIVIA]@red@ type the following ::anwser alskpwru39020dmsa3aeamap", "alskpwru39020dmsa3aeamap"},
			{"@blu@[TRIVIA]@red@ Who invented the telephone?", "bell"},
			{"@blu@[TRIVIA]@red@ What temperature does water boil at?", "100c"},
			{"@blu@[TRIVIA]@red@ Who did Lady Diana Spencer marry?", "Prince Charles"},
			{"@blu@[TRIVIA]@red@ Who lived at 221B, Baker Street, London?", "Sherlock Holmes"},
			{"@blu@[TRIVIA]@red@ Who cut Van gogh's ear?", "himself"},
			{"@blu@[TRIVIA]@red@ Who painted the Mona Lisa?", "Da vinci"},
			{"@blu@[TRIVIA]@red@ What is the max level of a skill?", "99"},
			{"@blu@[TRIVIA]@red@ What is the max level of combat?", "138"},
			{"@blu@[TRIVIA]@red@ What is the hardest rock?", "Diamond"},
			{"@blu@[TRIVIA]@red@ Which pokemon is the number 137?", "Porygon"},
			{"@blu@[TRIVIA]@red@ What is the number of the pokemon Porygon-Z?", "474"},
			{"@blu@[TRIVIA]@red@ What is the type of the following : True ?", "Boolean"},
			{"@blu@[TRIVIA]@red@ What is the maximum value of a byte?", "127"},
			{"@blu@[TRIVIA]@red@ How many possible values a byte can have?", "255"}

	};
	
	public static String currentQuestion;
	private static String currentAnswer;
}