package bullscows;

import java.util.Scanner;

public class Main {
    static int cows = 0;
    static int bulls = 0;

    static String secretKey = "";

    final static int CODE_LENGTH = 4;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        grader(secretKey.toCharArray(), scanner.next().toCharArray());
//        if (cows == 0 && bulls == 0) {
//            System.out.printf("Grade: None. The secret code is %s.\n", secretKey);
//        } else {
//            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s.\n", bulls, cows, secretKey);
//        }
        int digitNum = scanner.nextInt();
        if (digitNum > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
        } else {
            generateRandomNumber(digitNum);
            System.out.printf("The random secret number is %s.", secretKey);
        }



    }

    public static void grader(char[] secretCode, char[] userCode) {
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
        String pseudoRandomNumber = String.valueOf(System.nanoTime());
        for (int i = digitNumber; i > 0; i--) {
            secretKey = secretKey + pseudoRandomNumber.charAt(i);
        }

    }
}
