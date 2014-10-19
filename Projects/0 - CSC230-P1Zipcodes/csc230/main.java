/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @version 1.0
 * @since 2014-03-24
 * 
 * Spring 2014 
 * CSC 230 Project 1 Zipcodes
 * 
 * main.java
 */

package csc230;

import java.net.*;
import java.text.DecimalFormat;
import java.io.*;

public class main {
	public static void main(String[] args) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("zipout.txt"));
			out.write("Zipcode Readerer");
			out.newLine();
			
			//Declarations
			ZipReader zr = new ZipReader();
			ZipCodeLocation[] zclReturnedRI = new ZipCodeLocation[zr.getMnl()];
			String[] zclReturnedStandardDeliv = new String[zr.getMnl()];
			ZipCodeLocation[] zclReturnedUniqueZip = new ZipCodeLocation[zr.getMnl()];
			String returnedDelivTypes = new String();
			Integer[] springfieldZips = new Integer[zr.getMnl()];
			
			//URL Reading
			out.write("Reading URL");
			try{
				zr.read();
			}catch(IOException e) {
				e.printStackTrace();
			}
			out.newLine();
			out.write("Done Reading URL");
			
			//State Search
			out.newLine();
			out.newLine();
			out.write("Finding all elements existing in Rhode Island.");
			zclReturnedRI = zr.findState("RI");
			out.newLine();
			out.write("Printing Results");
			out.newLine();
			out.write("Zip\tLatitude\tLongitude\tTown                     \tSt\tCounty                   \tDelivery Type");
			out.newLine();
			for(int i = 0; i < zclReturnedRI.length; i++){
				out.write(zclReturnedRI[i].toString());
				out.newLine();
			}
			out.newLine();
			out.write("Done Printing Results");
			
			//Delivery Type Search
			out.newLine();
			out.newLine();
			out.write("Finding all counties whose delivery type is standard.");
			zclReturnedStandardDeliv = zr.findCountyDeliveryMethod("STANDARD");
			out.newLine();
			out.write("Printing Results");
			out.newLine();
			for(int i = 0; i < zclReturnedStandardDeliv.length; i++){
				out.write(zclReturnedStandardDeliv[i].toString());
				out.newLine();
			}
			out.newLine();
			out.write("Done Printing Results");
			
			//Unique Zip Search
			out.newLine();
			out.write("Finding all elements which have the same lat/lon but different zip codes.");
			out.newLine();
			zclReturnedUniqueZip = zr.uniqueZip();
			out.newLine();
			out.write("Printing Results");
			out.newLine();
			out.write("Zip\tLatitude\tLongitude\tTown                     \tSt");
			out.newLine();
			for(int i = 0; i < zclReturnedUniqueZip.length; i++){
				out.write(zclReturnedUniqueZip[i].toString());
				out.newLine();
			}
			out.newLine();
			out.write("Done Printing Results");
			
			//Deliv Type Search
			out.newLine();
			out.newLine();
			out.write("Finding all delivery types and amounts.");
			out.newLine();
			out.write("Printing Results");
			out.newLine();
			out.newLine();
			returnedDelivTypes = zr.uniqueDelivType();
			out.write(returnedDelivTypes);
			out.newLine();
			out.write("Done Printing Results");
			
			//Deliv Type Search
			out.newLine();
			out.newLine();
			out.write("Finding all counties USPS delivers to.");
			out.newLine();
			out.write("Printing Results");
			out.newLine();
			out.newLine();
			out.write("" + zclReturnedStandardDeliv.length);
			out.newLine();
			out.write("Done Printing Results");
			
			//Springfield Search
			out.newLine();
			out.newLine();
			out.write("Finding all towns with springfield.");
			out.newLine();
			springfieldZips = zr.springfield();
			out.write("Printing Results");
			out.newLine();
			out.newLine();
			for(int i = 0; i < zclReturnedRI.length; i++){
				out.write(customFormat("00000", springfieldZips[i]));
				out.newLine();
			}
			out.newLine();
			out.write("Done Printing Results");
			out.newLine();
			out.write("Completed Program");
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String customFormat(String pattern, double value ) {
	      DecimalFormat myFormatter = new DecimalFormat(pattern);
	      String output = myFormatter.format(value);
		return output;
	}
}
