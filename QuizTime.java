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

    // Checks if a string is already in the list
    private static boolean isAlreadyPrinted(String[] printedPalindromes, int count, String str) {
        for (int i = 0; i < count; i++) {
            if (printedPalindromes[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    // Finds unique palindromic substrings using recursion
    private static void findPalindromes(String str, int start, int end, String[] printedPalindromes, int[] count) {
        if (str.length() > 100) { // Check input length
            System.out.println(QuizTimeConstants.STRING_LENGTH_ERROR);
            return;
        }
        if (start > str.length()) return; // Base case
        if (end > str.length()) { // Move to next starting index
            findPalindromes(str, start + 1, start + 2, printedPalindromes, count);
            return;
        }
        String substring = str.substring(start, end);
        if (isPalindrome(substring) && !isAlreadyPrinted(printedPalindromes, count[0], substring)) {
            printedPalindromes[count[0]++] = substring; // Store unique palindrome
            System.out.println(substring);
        }
        findPalindromes(str, start, end + 1, printedPalindromes, count); // Extend substring
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean runAgain = true;

        while (runAgain) {
            // Input string
            System.out.println(QuizTimeConstants.ENTER_STRING);
            String input = scanner.nextLine();
            System.out.println(QuizTimeConstants.OUTPUT_PREFIX + input + QuizTimeConstants.OUTPUT_SUFFIX);

            // Initialize storage
            String[] printedPalindromes = new String[1000];
            int[] count = {0};

            // Find palindromes
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
