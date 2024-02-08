import java.util.Scanner;
import java.util.Random;


public class NumberGuessingGame {
    Scanner scan = new Scanner(System.in);

    void gameWelcome() {
        System.out.println("\n=================================================");
        System.out.println("Welcome to our Number Guessing Game");
        System.out.println("Number Guessing Game Menu: \n \t 1 ... Easy Mode\n \t 2 ... Medium Mode \n \t 3 ... Hard Mode \n \t 0 ... QUITING");
        System.out.print("Your choice: ");
        int level = scan.nextInt();
        System.out.println("=================================================");
        gameStart(level);
        System.out.println("=================================================\n");
    }

    void gameStart(int level) {
        switch (level) {
            case 1:
                System.out.println("Easy Mode \nGuess a number between 0 and 10, you have 3 guesses.");
                gamePlay(3, 11);
                break;
            case 2:
                System.out.println("Medium Mode \nGuess a number between 0 and 50, you have 5 guesses.");
                gamePlay(5, 51);
                break;
            case 3:
                System.out.println("Hard Mode \nGuess a number between 0 and 100, you have 7 guesses.");
                gamePlay(7, 101);
                break;
            case 0:
                System.out.println("GoodBye, Hope you play again soon");
                System.exit(0);
            default:
                System.out.println("Invalid input");
                gameWelcome();
                break;
        }
    }

    void gamePlay(int guesses, int maxNum) {
        Random random = new Random();
        int num = random.nextInt(maxNum);

        System.out.print("Input your guess: ");
        for (int i = 1; i <= guesses; i++) {
            int guess = scan.nextInt();
            if (guess < num) {
                System.out.println("Guess Higher." + " Previous guess: " + guess);
            } else if (guess > num) {
                System.out.println("Guess Lower." + " Previous guess: " + guess);
            }
            else if (guess == num) {
                System.out.print("YOU WON!! :) :) Your guess " + guess + " was correct.");
                gameWelcome();
                break;
            }
            System.out.print("Try again you have " + (guesses - i) + " Guesses left: ");
        }
        System.out.println("You Lost! The correct number was " + num + "! Good luck next time.");
        gameWelcome();
    }

}
