import java.util.Scanner;

public class GuessingNumber{
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.println("What is your name");
        String Name = scr.nextLine() ;
        System.out.println(" Select the number between 1 to 10");
        int number = scr.nextInt();
        int randNum = (int)
                ((Math.random()*10) +1);
        if (number == randNum){
            System.out.println("Your guessed number was "  + number  +   " My number is also "   +  randNum + "." );
            System.out.println("Congratulations!, " + Name);
            System.out.println("you have guessed number correctly");

        } else{
            System.out.println("you guessed number was "  +  number  + " but my number was " + randNum +". ");
            System.out.println("Sorry! for this time,"+ Name + ". This number isn't my number. Please try again..");
        }



    }
}