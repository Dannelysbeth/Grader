package bullscows;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static int cows = 0;
    static int bulls = 0;
    static String secretKey = null;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        int digitNum = scanner.nextInt();

        System.out.println("Input the number of possible symbols in the code:");
        int charAmount = scanner.nextInt();
        if (digitNum > charAmount) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there only %d characters selected.\n", digitNum, charAmount);
        } else {
            generateRandomNumberSimple(digitNum, charAmount);
            String stars = "*";
            for (int i = 0; i < digitNum - 1; i++) {
                stars += "*";
            }
//            char maxSymbol = charAmount > 10 ? charAmount +
            if (charAmount > 10) {
                char maxSymbol = (char) (96 + charAmount - 10);
                System.out.printf("The secret is prepared: %s (0-9, a-%c).\n", stars, maxSymbol);
            } else {
                int maxSymbol = charAmount - 1;
                System.out.printf("The secret is prepared: %s (0-%d).\n", stars, maxSymbol);
            }

            System.out.println("Okay, let's start a game!");
            int turn = 1;
            while (bulls != digitNum) {
                System.out.printf("Turn %d:\n", turn);
                grader(secretKey.toCharArray(), scanner.next().toCharArray());
                if (cows == 0 && bulls == 0) {
                    System.out.println("Grade: None");
                } else {
                    System.out.printf("Grade: %d bull(s) and %d cow(s).\n", bulls, cows);
                }
                turn++;
            }
            System.out.println("Congratulations! You guessed the secret code.");
        }


    }


    public static void grader(char[] secretCode, char[] userCode) {
        cows = 0;
        bulls = 0;
        for (int i = 0; i < secretCode.length; i++) {
            for (int j = 0; j < userCode.length; j++) {
                if (secretCode[i] == userCode[j]) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
    }

    public static void generateRandomNumberSimple(int digitNumber, int charAmount) {
        Set<Character> digits = new HashSet<>();
        secretKey = "";
        while (digits.size() < digitNumber) {
            int charValue = (int) (Math.random() * charAmount);
            charValue = charValue > 10 ? charValue + 87 : charValue + 48;
            digits.add((char) charValue);
        }
        digits.forEach(d -> {
            secretKey += d;
        });

    }

    public static void generateRandomNumber(int digitNumber) {
        while (secretKey == null || secretKey.length() < digitNumber) {
            String pseudoRandomNumber = String.valueOf(System.nanoTime());
            if (secretKey == null) {
                int i = pseudoRandomNumber.length() - 1;
                while (Character.getNumericValue(pseudoRandomNumber.charAt(i)) == 0) {
                    i--;
                }
                secretKey = String.valueOf(pseudoRandomNumber.charAt(i));
            }
            for (int i = pseudoRandomNumber.length() - 1; i >= 0; i--) {
                if (secretKey.length() == digitNumber) {
                    return;
                }
                for (int j = 0; j < secretKey.length(); j++) {
                    if (secretKey.charAt(j) == pseudoRandomNumber.charAt(i)) {
                        break;
                    } else if (j == secretKey.length() - 1) {
                        secretKey = secretKey + pseudoRandomNumber.charAt(i);
                        break;
                    }
                }
            }
        }
    }
}
