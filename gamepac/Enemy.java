package gamepac;

import java.util.Random;

public class Enemy {
	private String Name;
	private String Type;
	private double hp;
	private double atk;
	private double def;
	private double friend;
	private double originalHp;
	private static double difficultyModifier = 1; // default

	public static void setDifficultyModifier(double modifier) {
		difficultyModifier = modifier;
	}

	public static double getDifficultyModifier() {
		return difficultyModifier;
	}

	// constructor
	public Enemy() {
		this.hp = 100 * difficultyModifier;
		this.atk = 30 * difficultyModifier;
		this.def = 15 * difficultyModifier;
		this.friend = 0;
		this.originalHp = this.hp;
		System.out.printf("Difficulty Modifier: %f %n", difficultyModifier);
		if (StartController.getCurrentMap() == 2) {
			updateAttributes();
		}
	}

	void updateAttributes() {
		this.hp += 10;
		this.atk += 5;
		this.def += 2;
		// this.friend = 0;
		// System.out.println(Player.getVictoryCounter());
		// System.out.println(1 + Player.getVictoryCounter()/10);
		System.out.printf("Enemy HPoopie: %f, Enemy atk %f, Enemy def %f %n", this.hp, this.atk, this.def);

	}

	// return monster Name
	public String getName() {
		return Name;
	}

	// return monster Type
	public String getType() {
		return Type;
	}

	// return health points
	public Double getHp() {
		// updateAttributes();
		return hp;
	}

	// return attack
	public Double getAtk() {
		// updateAttributes();
		return atk;
	}

	// return defense
	public Double getDef() {
		// updateAttributes();
		return def;
	}

	// return friendship
	public Double getFriend() {
		return friend;
	}

	// set methods
	public String setType() {
		return Type;
	}

	public String setName() {
		return Name;
	}

	public double setHp(double newEnemyHp) {
		return hp;
	}

	public double setAtk() {
		return atk;
	}

	public double setDef() {
		return def;
	}

	public double setFriend(double newEnemyFriend) {
		return friend;
	}

	// when the monster is attacked
	public double takeDamage(double totalAtk) {
		this.hp -= totalAtk;
		return hp;
	}

	// when the monster is served
	public double friended(double totalServe) {
		this.friend += totalServe * (2 - difficultyModifier);
		if (this.atk >= 25) {
			this.atk -= 5;
		}
		if (this.def >= 5) {
			this.def -= 4;
		}
		System.out.printf("this: %f, %f%n", atk, def);
		return friend;
	}

	// when enemy attacks
	public double attack() {
		// Get the player's base attack (Atk)
		double baseAtk = this.atk;

		// Add additional random number between 1 and 10
		Random random = new Random();
		double randomBonus = random.nextInt(10) + 1;

		// Calculate the total attack (Atk + random bonus)
		double totalAtk = baseAtk + randomBonus;
		System.out.printf("%s: %.2f %n", "Enemy Attack ", totalAtk);

		return totalAtk;
	}

	// return String representation of Enemy object
	@Override
	public String toString() {
		return String.format("%s: %.2f %s: %.2f %s: %.2f %s: %.2f", "Health", getHp(), "Attack", getAtk(), "Defence",
				getDef(), "Friendship", getFriend());
	}

	public double getOriginalHp() {
		return originalHp;
	}

} // end of Enemy
