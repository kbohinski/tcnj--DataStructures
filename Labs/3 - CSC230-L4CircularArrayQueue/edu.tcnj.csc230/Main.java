/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @author Andrew Miller <millea12@tcnj.edu>
 * @version 1.0
 * @since 2014-04-07
 *
 * Spring 2014
 * CSC 230 Lab 4 Circluar Array Queue
 *
 * Main.java
 */

/** Setting Package**/
package edu.tcnj.csc230;

/** jsjf Imports**/
import jsjf.*;
import jsjf.exceptions.*;

/** java Imports**/
import java.net.*;
import java.util.*;
import java.io.*;

public class Main {
	public static void main (String args[]) throws IOException{
		try{
			/** Creating Variables **/
		 	CircularArrayQueue<String> circ = new CircularArrayQueue<String>();
		 	File file = new File("lab4.inp");
		 	Scanner scan = new Scanner(file);

		 	/** Reading File **/
		 	while(scan.hasNextLine()){

				String temp = scan.next();	

				if(temp.equals("size"))
				    System.out.println("The size of the queue is currently " + circ.size());

				if(temp.equals("isEmpty")){
				   String check = "is not ";
				   if(circ.isEmpty()){
					 check = "is ";
				   } /** end if (circ.isEmpty()) **/
				   System.out.println("The queue "+ check + "empty");		   
				} /** end if (temp.equals("isEmpty")) **/


				if(temp.equals("dequeue")){
				   try{
				    	System.out.println(circ.dequeue());
				   }catch(EmptyCollectionException e){
				   		/** catching error if the queue is empty **/
						System.out.println("Nothing to dequeue");
				   } /** end try catch **/
				} /** end if (temp.equals("dequeue")) **/

				if(temp.equals("toString"))
				    System.out.println(circ.toString());

				if(temp.equals("first")){
				    try{
				    	System.out.println(circ.first());
				    }catch(EmptyCollectionException e){
				    	/** catching error if the queue is empty **/
		                System.out.println("Nothing to peek");
		            } /** end try catch **/
				} /** end if (temp.equals("first")) **/

				if(temp.equals("enqueue")){
				    scan.useDelimiter("\n");
				    String tmpStringIn = scan.next();
				    tmpStringIn = tmpStringIn.substring(1);
				    circ.enqueue(tmpStringIn);
				    scan.reset();	
				} /** end if (temp.equals("enqueue")) **/

		    } /** end while(scan.hasNextLine()) **/
		}catch(FileNotFoundException e){
			System.out.println("No file named lab4.imp has been found.");
		}
    } /** end main method **/
} /** end class **/
