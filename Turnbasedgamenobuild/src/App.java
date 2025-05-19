import java.util.Scanner;
import java.util.Random;
import java.util.Stack;
public class App {
    public static void main(String[] args) throws Exception {
         //System.out.println("Hello world!");

        Stack<Integer> undo_stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        Random random = new Random();
        //int randomchance = random.nextInt(100); // Generates a random number between 0 and 99
        int playerhp = 100;
        int enemyhp = 100;
        int GameTimer = 0;
        int playerDamage = 10;
        int enemyDamage = 5;
        int Thunderboltdamage = 20;
        int Tackledamage = 10;
        int poisondotdamage = 5;
        int poisonduration = 3;
        Stack<Integer> backtrack_stack = new Stack<>();
        backtrack_stack.push(enemyhp);
        
        

        while (true) {
            // Check if it's the player's turn
            
            if (GameTimer % 2 == 0) {
                System.out.println("\n----------------------------------------\nYour turn.");
                System.out.println("Player HP: " + playerhp);
                System.out.println("Enemy HP: "+ enemyhp);
                System.out.println("What should you do?");
                System.out.println(">>> Attack");
                System.out.println(">>> Skill");
                System.out.println(">>> Defend");
                System.out.println(">>> Flee\n-----------------------------------------\n");
                String in = scanner.nextLine();
                System.out.println("");
                int randomchanceatk = random.nextInt(100); // Generates a random number between 0 and 99
                int randomchancecrit = random.nextInt(100); // Generates a random number between 0 and 99
               

                switch (in.toLowerCase()) {
                    case "attack":
                        System.out.println("You attacked the enemy!");
                        
                        int chancetohitattack = 75; // 75% chance to attack
                        int chancetohit = 20; // 20% chance to use a skill
                        if (randomchanceatk <= chancetohitattack && randomchancecrit <= chancetohit) {
                            System.out.println("You hit the enemy! Citical hit!");
                            enemyhp -= playerDamage * 2; // Critical hit doubles the damage
                        } else if (randomchanceatk <= chancetohitattack && randomchancecrit > chancetohit) {
                            System.out.println("You hit the enemy!");
                            enemyhp -= playerDamage; // Normal hit
                        } else {
                            System.out.println("You missed the attack!");
                        }
                        System.out.println("Enemy HP: " + enemyhp);
                        break;
                    case "defend":
                        playerhp -= enemyDamage / 2;
                        System.out.println("You defended against the enemy's attack!");
                        System.out.println("Player HP: " + playerhp);
                        break;
                    case "flee":
                        System.out.println("You fled from the battle!");
                        System.out.println("Game Over");
                        return; // Exit the game
                    case "skill":
                        System.out.println("Select Skill:\n\t>>> Heal\t>>> Poison mist\n\t>>> Fireball\n\t>>> Back");
                        String skill = scanner.nextLine();
                        switch (skill.toLowerCase()) {
                            case "heal":
                                playerhp += 20;
                                System.out.println("You healed yourself!");
                                break;
                            case "poison mist":
                                enemyhp -= 5;
                                System.out.println("You used Poison Mist!");
                                int dotdamage = 5;
                                int statuseffect = dotdamage;
                                break;
                            case "fireball":
                                enemyhp -= 25;
                                System.out.println("You used Fireball!");
                                break;
                            case "back":
                                System.out.println("Player HP: " + playerhp);
                                System.out.println("Enemy HP: "+ enemyhp);
                                System.out.println("What should you do?");
                                System.out.println(">>> Attack");
                                System.out.println(">>> Skill");
                                System.out.println(">>> Defend");
                                System.out.println(">>> Flee");
                                break;
                            default:
                                System.out.println("Invalid skill. Please try again.");
                        }
                        System.out.println("Enemy HP: " + enemyhp);
                        break;
                    default:
                        System.out.println("Invalid action You missed your chance.");
                }
                /* 
                if (enemyhp <= 0) {
                System.out.println("You defeated the enemy!");
                } else {
                    backtrack_stack.push(enemyhp);
                }
                */
                GameTimer++;
                if (enemyhp < 0) {
                    System.out.println("You defeated the enemy!");
                    break;
                }
            
            } else if (GameTimer % 2 != 0){
                System.out.println("Enemy's turn.");
                int randomaction = random.nextInt(3); // Generates a random number between 0 and 99
                int randomhitchance = random.nextInt(100); // Generates a random number between 0 and 99
                int randomcritchance = random.nextInt(100); // Generates a random number between 0 and 99
                int randombacktrackchance = random.nextInt(100); // Generates a random number between 0 and 99

                if (randombacktrackchance < 30) {
                    System.out.println("Enemy  activated passive skill! Backtrack!");
                    // Enemy backtracks to a previous state

                    enemyhp = backtrack_stack.peek();
                    System.out.println("Enemy HP: " + enemyhp);
                } else {
                    backtrack_stack.push(enemyhp);
                }
                if (randomaction == 0) {
                    System.out.println("Enemy attacks!");
                    if (randomhitchance < 75 && randomcritchance < 20) {
                        System.out.println("Enemy hit you! Critical hit!");
                        playerhp -= enemyDamage * 2; // Critical hit doubles the damage
                    } else if (randomhitchance < 75 && randomcritchance > 20) {
                        System.out.println("Enemy hit you!");
                        playerhp -= enemyDamage; // Normal hit
                    } else {
                        System.out.println("Enemy missed the attack!");
                    }
                } else if (randomaction == 1) {
                    System.out.println("Enemy is guarding!");
                    //reduce damage taken by 50%
                } else if (randomaction == 2) {
                    System.out.println("Enemy used a skill!");
                    int randomskillaction = random.nextInt(4); // Generates a random number between 0 and 3
                    switch (randomskillaction) {
                        case 1:
                            System.out.println("Enemy used Thunderbolt!");
                            if (randomhitchance < 70 &&  randomcritchance < 10) {
                                System.out.println("It's a critical hit!");
                                playerhp -= Thunderboltdamage * 2; // Critical hit doubles the damage
                            } else if (randomhitchance < 70 && randomcritchance > 10) {
                                System.out.println("Enemy hit you!");
                                playerhp -= Thunderboltdamage; // Normal hit
                            } else {
                                System.out.println("Enemy missed the attack!");
                            }
                            break;
                        case 2:
                            System.out.println("Enemy used Tackle!");
                            if (randomhitchance < 70 &&  randomcritchance < 10) {
                                System.out.println("It's a critical hit!");
                                playerhp -= Tackledamage * 2; // Critical hit doubles the damage
                            } else if (randomhitchance < 70 && randomcritchance > 10) {
                                System.out.println("Enemy hit you!");
                                playerhp -= Tackledamage; // Normal hit
                            } else {
                                System.out.println("Enemy missed the attack!");
                            }
                            break;
                        case 3:
                            System.out.println("Enemy used Heal!");
                            enemyhp += 20;
                            break;
                        default:
                            System.out.println("Code Error");
                    }
                } else {
                    System.out.println("AI Invalid output Error. Please try again.");
                    break;
                }
            GameTimer++;
        }
            if (playerhp <= 0) {
                System.out.println("You were defeated by the enemy!");
                break;
            }
        }
    }

    static boolean isOddorEven(int i) {
        boolean oddOrEven;
        int GameTimer = i;
        if (i % 2 == 0) {
            System.out.println("Your turn.");
            oddOrEven = true;
            return oddOrEven;
        } else {
            oddOrEven = false;
            System.out.println("Enemy's turn.");
            } /*else {
                // Random chance for enemy to use a skill
                Random randomskillchance = new Random();
                int randomchance = randomskillchance.nextInt(100); // Generates a random number between 0 and 99
                int chancetohitattack = 75; // 75% chance to attack
                int chancetohit = 20; // 20% chance to use a skill
                int thunderboltparalyzechance = 10; // 10% chance to paralyze

                if (randomchance < 80) {
                    System.out.println("Enemy uses a skill!");
                    switch (int randomskillchance = randomskillchance.nextInt(4)) {
                        case 1:
                            System.out.println("Enemy used Thunderbolt!");
                            if (randomchance < thunderboltparalyzechance) {
                                System.out.println("Enemy used Thunderbolt! You are paralyzed!");
                                
                            } else {
                                System.out.println("Enemy used Thunderbolt!");
                            }
                            
                            break;
                        case 2:
                            System.out.println("Enemy used Poison Mist!");
                            break;
                        case 3:
                            System.out.println("Enemy used Heal!");
                            break;
                        default:
                            System.out.println("Enemy attacks!");
                    }
                } else {
                    System.out.println("Enemy attacks!");
                }
                System.out.println("Enemy uses a skill!");
            }*/
            return oddOrEven;
        }
    }

    /*
    static void Paralyzedurationcounter() {
        // TODO Auto-generated method stub
        int paralyzeduration = 3;
        int paralyze = 0;
        while (paralyzeduration > 0) {
            System.out.println("You are paralyzed for " + paralyzeduration + " turns!");
            paralyzeduration--;
        }
    }
    */

