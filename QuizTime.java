import java.util.Scanner;

public class QuizTime {

    // Checks if a string is a palindrome
    private static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Checks for duplicate strings
    private static boolean isAlreadyPrinted(String[] printedPalindromes, int count, String str) {
        for (int i = 0; i < count; i++) {
            if (printedPalindromes[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    // Finds unique palindrome substring
    private static void findPalindromes(String str, int start, int end, String[] printedPalindromes, int[] count) {
        if (str.length() > 100) { 
            System.out.println(QuizTimeConstants.STRING_LENGTH_ERROR);
            return;
        }
        if (start > str.length()) return; 
        if (end > str.length()) { 
            findPalindromes(str, start + 1, start + 2, printedPalindromes, count);
            return;
        }
        String substring = str.substring(start, end);
        if (isPalindrome(substring) && !isAlreadyPrinted(printedPalindromes, count[0], substring)) {
            printedPalindromes[count[0]++] = substring; 
            System.out.println(substring);
        }
        findPalindromes(str, start, end + 1, printedPalindromes, count); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean runAgain = true;

        while (runAgain) {
            System.out.println(QuizTimeConstants.ENTER_STRING);
            String input = scanner.nextLine();
            System.out.println(QuizTimeConstants.OUTPUT_PREFIX + input + QuizTimeConstants.OUTPUT_SUFFIX);
            String[] printedPalindromes = new String[1000];
            int[] count = {0};
            findPalindromes(input, 0, 1, printedPalindromes, count);

            // Ask to run again
            boolean validResponse = false;
            while (!validResponse) {
                System.out.println(QuizTimeConstants.RUN_AGAIN_PROMPT);
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes")) {
                    runAgain = true;
                    validResponse = true;
                } else if (response.equals("no")) {
                    runAgain = false;
                    validResponse = true;
                } else {
                    System.out.println(QuizTimeConstants.INVALID_INPUT_MESSAGE);
                }
            }
        }
        scanner.close();
    }
}
