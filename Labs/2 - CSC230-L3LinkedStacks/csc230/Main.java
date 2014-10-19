/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @author Jack Graham <grahamj5@tcnj.edu>
 * @version 1.0
 * @since 2014-04-02
 *
 * Spring 2014
 * CSC 230 Lab 3 LinkedStacks
 *
 * Main.class
 */

package csc230;

import jsjf.*;

import java.net.*;
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		LinkedStack<String> functionTest = new LinkedStack<String>();
		String returnedResult = new String();
		returnedResult = "";
		int returnedInts = -1;
		boolean returnedBool = false;
		String[] tmpString = new String[20];

		try {
			FileReader inF = new FileReader("lab3.inp");
			BufferedReader in = new BufferedReader(inF);
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				try {
					tmpString = inputLine.split(" ");

					if (tmpString[0].equals("push")) {
						/* Read rest of the line. */
						String strIn = inputLine.substring(5).replaceAll("\"",
								"");
						System.out.println("pushing " + strIn);
						functionTest.push(strIn);

					} else if (tmpString[0].equals("pop")) {
						System.out.println("popping");
						returnedResult = functionTest.pop();
						System.out.println(returnedResult);

					} else if (tmpString[0].equals("peek")) {
						System.out.println("peeking");
						returnedResult = functionTest.peek();
						System.out.println(returnedResult);

					} else if (tmpString[0].equals("size")) {
						System.out.println("getting size");
						returnedInts = functionTest.size();
						System.out.println("" + returnedInts);

					} else if (tmpString[0].equals("isEmpty")) {
						System.out.println("checking if empty");
						returnedBool = functionTest.isEmpty();
						System.out.println(returnedBool);

					} else if (tmpString[0].equals("toString")) {
						System.out.println("getting string");
						returnedResult = functionTest.toString();
						System.out.println(returnedResult);

					} else {
						IOException e = new IOException();
						throw (e);
					}

				} catch (IOException e) {
					System.err.println("Bad line, attempting to continue.");
				}
			}
			in.close();
		} catch (IOException e) {
			System.err.println("Error Reading File.");
		}
	}
}
