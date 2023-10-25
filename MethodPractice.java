import javax.swing.*;

/**
 * CS152 Lab 4 -- Welcome to Methods.
 * Implement the methods described below with TODO comments
 * Student name: Roman Balayar
 */
public class MethodPractice {
    private static int correctTests = 0;
    private static int totalTests = 0;

    /** Clear test count variables */
    private static void clearCounts() {
        correctTests = 0;
        totalTests = 0;
    }

    /**
     * Update test count variables depending on if test passed.
     * @param correct True if test counts as correct.
     */
    private static void countTest(boolean correct) {
        if(correct) {
            correctTests++;
        }
        totalTests++;
    }

    /**
     * Print the testing results and return if all passed.
     * @return True if all tests passed.
     */
    private static boolean checkResults() {
        System.out.println("Your program passes " + correctTests +
                " out of " + totalTests + " tests");
        return correctTests == totalTests;
    }
    /**
     * Returns largest value of its arguments.
     * @param x First argument
     * @param y Second argument
     * @param z Third argument
     * @return Maximum of x, y and z
     */
    public static int maxOfThree( int x, int y, int z) {
        // TODO: REPLACE THE METHOD BODY
        if (x >= y && x >= z) {
            return x;
        } else if (y >= x && y >= z) {
            return y;
        } else {
            return z;
        }
    }

    /**
     * Is the character given a vowel?
     * A vowel is one of a, e, i, o, or u (upper or lower case)
     * @param c Character to check
     * @return True if c is a vowel, false if not
     */
    public static boolean isVowel(char c) {
        // TODO: REPLACE THE METHOD BODY
        c = Character.toLowerCase(c);
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Does the given string contain a vowel?
     * @param s String to check
     * @return True if s contains a vowel, false if not
     */
    public static boolean hasVowel(String s) {
        // TODO: REPLACE THE METHOD BODY
        for (int  i= 0; i< s.length(); i++) {
            char c = s.charAt(i);
            if( (isVowel(c))){
                return true;
            }
        }
         return  false;
    }

    /**
     * Create a new string containing the characters of the
     * given string in reverse order.
     * @param s The string to reverse
     * @return Reversed string.
     */
    public static String reverseString(String s) {
        // TODO: REPLACE THE METHOD BODY
        StringBuilder reversed = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed.append(s.charAt(i));
        }
           return reversed.toString();
    }

    /**
     * Checks if the given string is a palindrome,
     * a string that reads the same backward as forward.
     * @param s String to check
     * @return True if s is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String s) {
        // TODO: REPLACE THE METHOD BODY

        if(reverseString(s).equals(s)){
            return true;
        } else{
            return false;
        }



    }

    /**
     * Average up to five even numbers. Any odd values are
     * not included in the average.
     * @param a First value
     * @param b Second value
     * @param c Third value
     * @param d Fourth value
     * @param e Fifth value
     * @return Average of the even input values. If none are even, returns -1000.
     */
    public static double averageEvenNumbers( int a, int b, int c, int d, int e ) {
        // TODO: REPLACE THE METHOD BODY
            int sum = 0;
            int count = 0;

            if (a % 2 == 0) {
                sum += a;
                count++;
            }
            if (b % 2 == 0) {
                sum += b;
                count++;
            }
            if (c % 2 == 0) {
                sum += c;
                count++;
            }
            if (d % 2 == 0) {
                sum += d;
                count++;
            }
            if (e % 2 == 0) {
                sum += e;
                count++;
            }
            if ( count == 0){
                return -1000;
            }
            double average = (double) sum / count;
            return average;
    }


    // TODO: WRITE A METHOD FROM SCRATCH

    public static int countVowels(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                count++;
            }
        }
        return count;
    }

    
    // TODO: WRITE A METHOD FROM SCRATCH
    public static double totalMealPrice(int meal, double tip) {
        if (meal > 0 && tip >= 0 && tip <= 0.9) {
           double H = meal + meal*tip;
            return H;
        }else{
        return -1;
        }
    }




    /**
     * This code tests your program's completeness.
     * @param args Ignored
     */
    public static void main(String[] args) {

        int goodMethods = 0;

        clearCounts();
        System.out.println("testing maxOfThree");
        countTest( maxOfThree(1, 2, 3) == 3);
        countTest( maxOfThree(1, 3, 2) == 3);
        countTest( maxOfThree(3, 2, 1) == 3);
        countTest( maxOfThree(4, -5, 3) == 4);
        countTest( maxOfThree(5, 7, 0) == 7);
        countTest( maxOfThree(-1, -2, 3) == 3);
        countTest( maxOfThree(-1, -2, -3) == -1);
        if(checkResults()) { goodMethods++; }

        clearCounts();
        System.out.println("testing isVowel");
        countTest( !isVowel('x') );
        countTest( !isVowel('5') );
        countTest( !isVowel('&') );
        countTest( isVowel('A') );
        countTest( isVowel('a') );
        countTest( isVowel('E') );
        countTest( isVowel('i') );
        countTest( isVowel('o') );
        countTest( isVowel('U') );
        if(checkResults()) { goodMethods++; }

        clearCounts();
        System.out.println("testing hasVowel");
        countTest( !hasVowel("") );
        countTest( hasVowel("banana") );
        countTest( hasVowel("Old") );
        countTest( !hasVowel("rhythm") );
        countTest( !hasVowel("L33T") );
        if(checkResults()) { goodMethods++; }

        clearCounts();
        System.out.println("testing reverseString");
        countTest( reverseString("").equals(""));
        countTest( reverseString("abcde").equals("edcba"));
        countTest( reverseString("Hello, World!").equals("!dlroW ,olleH"));
        countTest( reverseString("!boj dooG").equals("Good job!"));
        if(checkResults()) { goodMethods++; }

        clearCounts();
        System.out.println("testing isPalindrome");
        countTest( isPalindrome("") );
        countTest( isPalindrome("x") );
        countTest( !isPalindrome("banana") );
        countTest( isPalindrome("madam") );
        countTest( !isPalindrome("Madam") );
        countTest( isPalindrome("racecar") );
        countTest( isPalindrome("123 456 ABCCBA 654 321") );
        if(checkResults()) { goodMethods++; }

        clearCounts();
        System.out.println("testing averageEvenNumbers");
        countTest( averageEvenNumbers(12, 13, 12, 13, 12) == 12.0);
        countTest( averageEvenNumbers(-1, 3, -5, 7, 9) == -1000.0);
        countTest( averageEvenNumbers(0, 0, 15, 0, -2) == -0.5);
        countTest( averageEvenNumbers(100, -3, 402, -2, 10) == 127.5);
        countTest( averageEvenNumbers(2, 0, 0, 0, -2) == 0.0);
        if(checkResults()) { goodMethods++; }

        // Uncomment these tests AFTER IMPLEMENTING countVowels
         clearCounts();
         System.out.println("testing countVowels");
         countTest( countVowels("L33T rhythm") == 0);
         countTest( countVowels("abcdefghijklmNOPQRSTUVWZYZ") == 5);
         countTest( countVowels("One TWO 3 four FIVE") == 7);
         if(checkResults()) { goodMethods++; }


         clearCounts();
         System.out.println("testing totalMealPrice");
         countTest( totalMealPrice(0, 0.3) == -1.0);
         countTest( totalMealPrice(10, 0.25) == 12.5);
         countTest( totalMealPrice(100, 0.5) == 150.0);
         countTest( totalMealPrice(100, 0.9) == 190.0);
         countTest( totalMealPrice(100, 0.91) == -1.0);
         countTest( totalMealPrice(120, 0.32) == 158.4);
         if(checkResults()) { goodMethods++; }

        System.out.println();
        System.out.println(goodMethods + "/8 methods pass tests");


    }

}