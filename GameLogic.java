import java.util.Random;
import java.util.Scanner;

public class GameLogic {

    public static void main(String[] args) {

        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        //Health
        Health gameHealth = new Health();

        //Characters
        Characters enemyA = new Characters();

        //Players
        Player harry = new Player();

        //Rooms
        Rooms location = new Rooms();

        //Weapons
        Weapons weapon = new Weapons();

        boolean running = true;

        System.out.println("Harry Potter and the RPG'd Chamber of Secrets!\n");
        System.out.println("This is a game based inspired by the wonderful book, Harry Potter and the Chamber of Secrets\n");
        System.out.println("SCENARIO:\n");
        System.out.println("After seeing the memory Dumbledore had stored of Tom Riddle, Harry Potter seeks to unlock the Chamber of Secrets in order to save Jinny Weasley who had been taken by Tom Riddle\n");
        System.out.println("You are Harry Potter and your mission is to save Jinny Weasley\n");
        System.out.println("You must act fast, young wizard. Time is a matter of life or DEATH!\n");
        System.out.println("--------------------------------------");
        // initiate game
        GAME:
        while (running) {

            String building = location.area[rand.nextInt(location.area.length)];
            System.out.println("\t# " + "You are in: " + building + " #\n");

            String harryWeapon = weapon.weaponAttack[rand.nextInt(weapon.weaponAttack.length)];
            System.out.println("\t# " + "You are equipped with: " + harryWeapon + " #\n");

            int enemyHealth = rand.nextInt(enemyA.maxEnemyHealth);
            String enemy = enemyA.enemies[rand.nextInt(enemyA.enemies.length)];
            System.out.println("\t# " + enemy + " has appeared! #\n");

            while (enemyHealth > 0 && harry.health <= 100) {
                System.out.println("\tYour HP: " + harry.health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\t" + "You are in: " + building);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack: Sectumsempra");
                System.out.println("\t2. Drink some pumpkin juice");
                System.out.println("\t3. Run!");
                if (harry.health > 5) {
                    harry.health -= 5;
                }
                if (harry.health < 1) {
                    System.out.println("\tYou limp out of " + building + " beaten by " + enemy);
                    System.out.println("\tYou have been defeated. Better luck next time!");
                    System.out.println("############################");
                    System.out.println("  Thank you for playing! ");
                    System.out.println("  You're a wizard, Harry! ");
                    System.out.println("############################");
                    System.exit(0);
                    break;
                } else if (harry.health > 100) {
                    harry.health = 100;
                    System.out.println("Maximum HP is " + harry.health);
                    System.out.println("You are at full health");
                }else{
                    //hello;
                }

                String input = in.nextLine();
                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(harry.attackDmg);
                    int damageTaken = rand.nextInt(enemyA.enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    harry.health -= damageTaken;

                    System.out.println("\t> You attack the " + enemy + " with " + harryWeapon +" for " + damageDealt + " damage");
                    System.out.println("\t> You have received " + damageTaken + " in retaliation");

                    if (harry.health < 1) {
                        System.out.println("\t You have taken too much damage, you are too weak to go on");
                        System.out.println("\t You have been defeated. Better luck next time!");
                        System.out.println("############################");
                        System.out.println("  Thank you for playing! ");
                        System.out.println("  You're a wizard, Harry! ");
                        System.out.println("############################");
                        System.exit(0);
                    }
                } else if (input.equals("2")) {
                    if (harry.numHealthPots > 0 && harry.health > 0) {
                        harry.health += harry.healthPotionHealAmount;
                        harry.numHealthPots--;
                        System.out.println("\t> You drank a health potion, healed for: " + harry.healthPotionHealAmount + " HP."
                                + "\n\t> You now have " + harry.numHealthPots + " health potions left.\n");
                    }
                } else if (input.equals("3")) {
                    System.out.println("\t> You ran away from " + enemy);
                    System.out.println("\t> You exit the " + building);
                    continue GAME;
                } else if (input.equals("quit")){
                System.out.println("\t> You run away from the " + enemy);
                System.out.println("############################");
                System.out.println("  Thank you for playing! ");
                System.out.println("  You're a wizard, Harry! ");
                System.out.println("############################");
                System.exit(0);
            }   else {
                    System.out.println("\tInvalid command");
                }
            }


            System.out.println("--------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println("10 points to Gryffindor, bravo!");
            if (harry.health > 0) {
                System.out.println(" # You have " + harry.health + "HP left # ");
            }else{
                System.out.println("You have been defeated. Better luck next time!");
            }
            // If the random number is less than 10 it drops
            if (rand.nextInt(100) < harry.healthPotionDropChance) {
                harry.numHealthPots++;
                System.out.println(" # The " + enemy + " dropped a pumpkin juice potion. # ");
                System.out.println(" # You now have " + harry.numHealthPots + " health potion(s). # ");
            }
            System.out.println("--------------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue battling to defeat the horcrux");
            System.out.println("2. Exit the Chamber of Secrets");
            String input = in.nextLine();

            while (!input.equals("1") && !input.equals("2")) {
                System.out.println("invalid command");
                input = in.nextLine();

            }
            if (input.equals("1") && harry.health > 0) {
                System.out.println("You continue your adventure.");
            } else if (input.equals("2")) {
                System.out.println("You exited the Chamber of Secrets and swiftly found yourself in Madam Pomfrey's hopsital ward.");
                break;
            }
        }
        System.out.println("############################");
        System.out.println("  Thank you for playing! ");
        System.out.println("  You're a wizard, Harry! ");
        System.out.println("############################");
    }
}

