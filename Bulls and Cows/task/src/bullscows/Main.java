package bullscows;

import java.util.Scanner;

public class Main {
    static int cows = 0;
    static int bulls = 0;

    static String secretKey = "9305";

    final static int CODE_LENGTH = 4;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        grader(secretKey.toCharArray(), scanner.next().toCharArray());
        if (cows == 0 && bulls == 0) {
            System.out.printf("Grade: None. The secret code is %s.\n", secretKey);
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s.\n", bulls, cows, secretKey);
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
}
