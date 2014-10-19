/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @author Jack Graham <grahamj5@tcnj.edu>
 * @version 1.0
 * @since 2014-02-27
 * 
 * Spring 2014 
 * CSC 230 Lab 1 Change of Base + Ackermann
 */

import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int menu = 0;
		
		while(menu == 0){
			menu = 3;
			System.out.println("Base Changerer and Ackermann Solverer:");
			System.out.println("Enter 1 to use the Base Changerer, Enter 2 to use the Ackermann Solverer");
			menu = input.nextInt();
			if(menu == 1){
				System.out.println("Base Changerer:");
				System.out.println("This program uses recursion to convert a base 10 number into a base b number, where b < 10.");
	
				System.out.println("\nEnter your desired base.");
				int base = input.nextInt();
				System.out.println("\nEnter your number to convert.");
				int inputNum = input.nextInt();
				
				convert(base,inputNum);
				System.out.println("\nYour converted number is: " + convert(base,inputNum));
			}else if(menu == 2){
				System.out.println("\n\nAckermann Solverer:");
				System.out.println("This program uses recursion to the famous Ackermann function created by Wilhelm Ackermann in the late 1920's.");
				
				System.out.println("Enter the m value: ");
				int m = input.nextInt();
				System.out.println("Enter the n value: ");
				int n = input.nextInt();
				
				ackermann(m,n);
				System.out.println("\nYour solved function is: " + ackermann(m,n));
			}else{
				System.out.println("Please enter 1 or 2");
				menu = 0;
			}
		}
		

	}

	public static String convert(int base, int inputNum){
		int quotient = (inputNum/base);
		int remainder = (inputNum%base);

		if((inputNum/base) == 0){
			return "" + remainder;
		}else{
			return("" + convert(base,quotient) + remainder );
		}
	}
	
	public static int ackermann(int m, int n){
		int end = 0;
		if(m==0){
			end = (n+1);
		}else if(m > 0 && n == 0){
			end = (ackermann((m-1),1));
		}else if(m > 0 && n > 0){
			end = (ackermann((m-1),(ackermann(m,(n-1)))));
		}
		return end;
	}
}
