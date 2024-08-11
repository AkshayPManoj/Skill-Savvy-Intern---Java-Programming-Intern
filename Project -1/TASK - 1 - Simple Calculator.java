//TASK - 1 - Simple Calculator

import java.util.*;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double num1, num2;
        char operator;

        System.out.println("Welcome to the Basic Calculator!");
        System.out.print("Enter the first number: ");
        num1 = getDoubleInput(scanner);

        System.out.print("Enter the operator (+, -, *, /): ");
        operator = getOperator(scanner);

        System.out.print("Enter the second number: ");
        num2 = getDoubleInput(scanner);

        double result = calculate(num1, num2, operator);
        if (Double.isNaN(result)) {
            System.out.println("Error: Invalid operation or division by zero!");
        } else {
            System.out.println("Result: " + result);
        }

        scanner.close();
    }

    // Function to get double input from user with error handling
    private static double getDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next(); // consume invalid input
        }
        return scanner.nextDouble();
    }

    // Function to get operator input from user with error handling
    private static char getOperator(Scanner scanner) {
        char operator;
        while (true) {
            String input = scanner.next();
            if (input.length() == 1) {
                operator = input.charAt(0);
                if (operator == '+' || operator == '-' || operator == '*' || operator == '/') {
                    break;
                }
            }
            System.out.print("Invalid operator! Please enter +, -, *, or /: ");
        }
        return operator;
    }

    // Function to perform calculation based on operator
    private static double calculate(double num1, double num2, char operator) {
        double result = Double.NaN; // default result for invalid operations
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                }
                break;
        }
        return result;
    }
}


