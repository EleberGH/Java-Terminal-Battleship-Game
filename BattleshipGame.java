import java.util.Random;
import java.util.Scanner;

public class BattleshipGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final int SHIPS = 5;
        final int GRID_WIDTH = 5;
        int shipsFound = 0;
        int rounds = 0;
        int guessesMade = 0;
        String grid = "";
        String nonseenGrid = "";
        int player1Score = 0;
        int player2Score = 0;
        int player3Score = 0;

        // Equaling founders to 0 to silence the compiler.
        int firstShipFounder = 0; 
        int lastShipFounder = 0;

        for (int i = 0; i < GRID_WIDTH * GRID_WIDTH; i++) {
            grid += "~";
            nonseenGrid += "0";
        }

        int shipsPlaced = 0;
        while (shipsPlaced < SHIPS) {
            int position =  new Random().nextInt(GRID_WIDTH * GRID_WIDTH);

            if (nonseenGrid.charAt(position) == '0') {
                nonseenGrid = nonseenGrid.substring(0, position) + "1"
                + nonseenGrid.substring(position + 1, nonseenGrid.length());
                shipsPlaced++;
            }
        }
            System.out.println("Welcome to Battleship!\nThe grid is " + GRID_WIDTH + "x" + GRID_WIDTH +
            ". There are " + SHIPS + " hidden ships.");
            System.out.println("Misses cost 1 point. First ship gives 3 bonus points. Last ship gives bonus 5 points.");

        gridPrint(GRID_WIDTH, grid);

        while (shipsFound < SHIPS) {
            rounds++;
            System.out.println("###### Round #" + rounds);

            for (int player = 1; player <= 3 && shipsFound < SHIPS; player++) {
                System.out.println("## Player" + player + " ##");
                System.out.println("Previous Grid:");
                
                gridPrint(GRID_WIDTH, grid);

                System.out.print("Guess row: ");
                int row = scan.nextInt();
                System.out.print("Guess column: ");
                int column = scan.nextInt();
                int position = (row) * GRID_WIDTH + (column);

                if (row < 0 || row > GRID_WIDTH - 1 || column < 0 || column > GRID_WIDTH - 1) {
                    System.out.println("Invalid guess. Try again.");
                    player--;
                } else if (nonseenGrid.charAt(position) == '1') {
                    guessesMade++;
                    shipsFound++;
                    System.out.println("Hit!");

                    if (player == 1) {
                        player1Score++;
                    } else if (player == 2) {
                        player2Score++;
                    } else {
                        player3Score++;
                    }

                    if (shipsFound == 1) {
                        System.out.println("First ship bonus! +3 points!");

                        if(player == 1){
                            player1Score += 3;
                            firstShipFounder = 1;
                        }
                        if(player == 2) {
                            player2Score += 3;
                            firstShipFounder = 2;
                        }
                        if(player == 3) {
                            player3Score += 3;
                            firstShipFounder = 3;
                        }
                    }
                     if(shipsFound == 5) {
                        System.out.println("Last ship bonus! +5 points!");

                        if(player == 1){
                            player1Score += 5;
                            lastShipFounder = 1;
                        }
                        if(player == 2) {
                            player2Score += 5;
                            lastShipFounder = 2;
                        }
                        if(player == 3) {
                            player3Score += 5;
                            lastShipFounder = 3;
                        }
                     }
                     System.out.println("Current Grid:");

                     grid = grid.substring(0, position) + "X" + grid.substring(position + 1, grid.length());
                     nonseenGrid = nonseenGrid.substring(0, position) + "2"
                     + nonseenGrid.substring(position + 1, nonseenGrid.length());
 
                     gridPrint(GRID_WIDTH, grid);

                } else if (nonseenGrid.charAt(position) == '0') {
                    guessesMade++;
                    System.out.println("Miss!");
                    System.out.println("Current Grid:");

                    grid = grid.substring(0, position) + "O" + grid.substring(position + 1, grid.length());
                    nonseenGrid = nonseenGrid.substring(0, position) + "3"
                    + nonseenGrid.substring(position + 1, nonseenGrid.length());

                    gridPrint(GRID_WIDTH, grid);

                    if (player == 1 && player1Score > 0) {
                        player1Score--;
                    } else if (player == 2 && player2Score > 0) {
                        player2Score--;
                    } else if (player == 3 && player3Score > 0) {
                        player3Score--;
                    }

                } else if (nonseenGrid.charAt(position) == '2') {
                    System.out.println("Already hit this ship!");
                    System.out.println("Current Grid:");

                    gridPrint(GRID_WIDTH, grid);
                } else if (nonseenGrid.charAt(position) == '3') {
                    System.out.println("Already guessed here!");
                    System.out.println("Current Grid:");

                    gridPrint(GRID_WIDTH, grid);
                }
            }
        }
        System.out.println("*********************\nGame finished!");
        System.out.println("Total rounds played: " + rounds);
        System.out.println("Total guesses: " + guessesMade);
        System.out.println("Player1 score: " + player1Score);
        System.out.println("Player2 score: " + player2Score);
        System.out.println("Player3 score: " + player3Score);
        System.out.println("Player who found the first ship: Player" + firstShipFounder);
        System.out.println("Player who found the last ship: Player" + lastShipFounder);
        if (player1Score > player2Score && player1Score > player3Score) {
            System.out.println("Winner: Player1");
        } else if (player2Score > player1Score && player2Score > player3Score) {
            System.out.println("Winner: Player2");
        } else if (player3Score > player1Score && player3Score > player2Score) {
            System.out.println("Winner: Player3");
        } else {
            System.out.println("It's a tie!");
        }
        scan.close();
    }

    private static void gridPrint(final int GRID_WIDTH, String grid) {
        for (int i = 0; i < GRID_WIDTH * GRID_WIDTH; i++) {
            System.out.print(grid.charAt(i) + " ");

            if ((i + 1) % GRID_WIDTH == 0) {
                System.out.println();
            }
        }
    }
}

