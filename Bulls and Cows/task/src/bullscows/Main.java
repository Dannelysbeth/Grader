package bullscows;

import java.util.Scanner;

public class Main {
    static int cows = 0;
    static int bulls = 0;
    static String secretKey = null;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length:");
        int digitNum = scanner.nextInt();
        if (digitNum > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
        } else {
            generateRandomNumber(digitNum);
            System.out.println("Okay, let's start a game! ");
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
