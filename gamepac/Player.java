package gamepac;

import java.util.Random;

public class Player {
	private final String Name;
	private double hp;
	private final double atk;
	private final double def;
	private final double serve;
	private double originalHp;
	private static int victoryCounter;

	private static Player instance;

	// constructor
	private Player() {
		this.Name = "Hero";
		this.hp = 150;
		this.atk = 30;
		this.def = 12;
		this.serve = 10;
		this.originalHp = this.hp;
		Player.setVictoryCounter(0);
	}

	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}

	// return player Name
	public String getName() {
		return Name;
	}

	// return health points
	public Double getHp() {
		return hp;
	}

	// return attack
	public Double getAtk() {
		return atk;
	}

	// return defense
	public Double getDef() {
		return def;
	}

	public static void setHp(double newPlayerHp) {
	}

	public double serve() {
		// Get the player's base attack (Serve)
		double baseServe = this.serve;

		// Add additional random number between 1 and 15
		Random random = new Random();
		double randomBonus = random.nextInt(15) + 1;

		// Calculate the total Serve (Serve + random bonus)
		double totalServe = baseServe + randomBonus;
		UpdateHighScoreFile.setFriendshipScore(totalServe);
		System.out.printf("%s: %.2f %n", "Your Serve ", totalServe);
		return totalServe;
	}

	public double attack() {
		// Get the player's base attack (Atk)
		double baseAtk = this.atk;

		// Add additional random number between 1 and 10
		Random random = new Random();
		double randomBonus = random.nextInt(10) + 1;

		// Calculate the total attack (Atk + random bonus)
		double totalAtk = baseAtk + randomBonus;
		System.out.printf("%s: %.2f %n", "Your Attack ", totalAtk);
		return totalAtk;
	}

	// when the player is attacked
	public double takeDamage(double totalAtk) {
		this.hp -= totalAtk;
		return hp;
	}
	
	// when the player heals
	public double heal() {
		double totalHeal = 0;
		if (StartController.getHealthPotions() >= 1) {
			Random random = new Random();
			totalHeal = random.nextInt(70) + 70;
			this.hp += totalHeal;
			if (this.hp > 150) {
				this.hp = 150;
			}
			StartController.setHealthPotions(StartController.getHealthPotions() - 1);
			System.out.printf("%s: %.2f %n", "Your Heal ", totalHeal);
			return totalHeal;
		} else {
			System.out.println("No remaining Health Potions");
		}
		return totalHeal;
	}

	// return String representation of player object
	@Override
	public String toString() {
		return String.format("%s: %s %s: %.2f %s: %.2f %s: %.2f", "Name", getName(), "Health", getHp(), "Attack",
				getAtk(), "Defence", getDef());
	}

	// reset player's health after each victory, or after defeat in case we play
	// again
	public void resetHealth() {
		this.hp = 150;
	}

	// increment counter to increase enemy difficulty after each player victory
	public static void incrementVictoryCounter() {
		setVictoryCounter(getVictoryCounter() + 1);
		System.out.printf("Victory Counter: %d\n", victoryCounter);
	}

	public static int getVictoryCounter() {
		return victoryCounter;
	}

	public static void setVictoryCounter(int victoryCounter) {
		Player.victoryCounter = victoryCounter;
	}

	public double getServe() {
		return serve;
	}

	public double getOriginalHp() {
		return originalHp;
	}
} // end of Player
