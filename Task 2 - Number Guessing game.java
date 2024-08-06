//Task 2 - Number Guessing game 

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame 
{

    private static final int MIN = 1;
    private static final int MAX = 100;
    
    public static void main(String[] args) 
    {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a random number between " + MIN + " and " + MAX + ". Try to guess it!");
        playGame();
    }

    private static void playGame() 
    {
        int randomNumber = generateRandomNumber();
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);
        
        while (true) 
        {
            int guess = getUserGuess(scanner);
            attempts++;
            
            if (guess < randomNumber) 
            {
                System.out.println("Too low! Try again.");
            } 
            else if (guess > randomNumber) 
            {
                System.out.println("Too high! Try again.");
            } 
            else 
            {
                System.out.println("Congratulations! You've guessed the number " + randomNumber + " correctly!");
                System.out.println("It took you " + attempts + " attempts to win the game.");
                break;
            }
        }
        
        scanner.close();
    }

    private static int generateRandomNumber() 
    {
        Random random = new Random();
        return random.nextInt(MAX - MIN + 1) + MIN;
    }

    private static int getUserGuess(Scanner scanner) 
    {
        while (true) 
        {
            System.out.print("Enter your guess (between " + MIN + " and " + MAX + "): ");
            if (scanner.hasNextInt()) 
            {
                int guess = scanner.nextInt();
                if (guess >= MIN && guess <= MAX) 
                {
                    return guess;
                } 
                else 
                {
                    System.out.println("Please enter a number between " + MIN + " and " + MAX + ".");
                }
            } 
            else 
            {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // clear the invalid input
            }
        }
    }
}
