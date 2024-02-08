import java.util.Scanner;
import java.util.Random;


public class NumberGuessingGame {
    Scanner scan = new Scanner(System.in);

    void gameWelcome() {
        System.out.println("\n=================================================");
        System.out.println("Welcome to our Number Guessing Game");
        System.out.println("=================================================");
        System.out.println("Please choose difficulty level: \n \t 1 for Easy \n \t 2 for Medium \n \t 3 for Hard  \n \t 0 for QUITING");
        System.out.print("Your choice: ");
        int level = scan.nextInt();
        gameStart(level);
        System.out.println("=================================================\n");
    }

    void gameStart(int level) {
        switch (level) {
            case 1:
                System.out.println("Easy Level \n Guess a number between 0 and 10, you have 3 guesses.");
                gamePlay(3, 11);
                break;
            case 2:
                System.out.println("Medium Level  \n Guess a number between 0 and 40, you have 5 guesses.");
                gamePlay(5, 51);
                break;
            case 3:
                System.out.println("Hard Level  \n Guess a number between 0 and 100, you have 7 guesses.");
                gamePlay(5, 101);
                break;
            case 4:
                System.out.println("GoodBye, Hope you play again soon");
                break;
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
        for (int i = 1; i < guesses; i++) {
            int guess = scan.nextInt();
            if (guess == num) {
                System.out.print("YOU WON!! :) :) Your guess is correct.");
            }
            System.out.println("Your guess was not correct, try again you have " + (guesses - i) + " Guesses left");
        }
        System.out.println("You Lost! The correct number was " + num + "! Good luck next time.");
        gameWelcome();
    }


    public static void main(String[] args) {
        NumberGuessingGame game1 = new NumberGuessingGame();
        game1.gameWelcome();

    }

}
