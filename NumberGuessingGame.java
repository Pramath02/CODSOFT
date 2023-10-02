import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attempts = 0;
        int roundsWon = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int guess;
            boolean hasGuessedCorrectly = false;

            System.out.println("I have selected a number between " + minRange + " and " + maxRange + ".");

            while (!hasGuessedCorrectly && attempts < 10) { // You can change the number of attempts as needed
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                attempts++;

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    roundsWon++;
                    hasGuessedCorrectly = true;
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();

            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
                System.out.println("Thanks for playing! Your total score: " + roundsWon);
            } else {
                attempts = 0; // Reset attempts for the next round
            }
        }

        scanner.close();
    }
}
