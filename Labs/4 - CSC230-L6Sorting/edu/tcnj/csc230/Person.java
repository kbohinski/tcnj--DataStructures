/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @author Maulik Patel <patelm21@tcnj.edu>
 * @version 1.0
 * @since 2014-04-24
 * 
 * Spring 2014 
 * CSC 230 Lab 6 Sorting
 * 
 * Person.java
 */

/** Setting Package **/
package edu.tcnj.csc230;

/** Setting Imports **/
import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.System.*;
import java.math.BigInteger;
import java.text.*;
import java.net.*;
import jsjf.Sorting;

public class Person implements Comparable<Person> {
	/** Making Vars * */
	private String givenName;
	private String surName;
	private String address;
	private String city;
	private String state;
	private String bloodType;
	private int zipCode;
	private String bloodTypeABOAB;
	private boolean bloodTypePosNeg;
	private int compareCount;

	/**
	 * Constructor : Creates a new Person object based on the given input data
	 * 
	 * @param givenName
	 *            The Person's first name
	 * @param surName
	 *            The Person's last name
	 * @param address
	 *            The Person's address
	 * @param city
	 *            The city in which the Person lives in
	 * @param state
	 *            The state in which the Person lives in
	 * @param bloodType
	 *            The Person's blood type
	 * @param zipCode
	 *            The zip code in which the Person lives in
	 */
	public Person(String givenName, String surName, String address,
			String city, String state, String bloodType, int zipCode) {
		this.givenName = givenName;
		this.surName = surName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.bloodType = bloodType;
		this.zipCode = zipCode;
		simplifyBloodType();
		compareCount = 0;
	}

	/** End Constructor * */

	/** Begin Getters and Setters * */

	/**
	 * Returns the zip code in which the person lives in formatted for print
	 * 
	 * @return zipCode in a format for print
	 */
	public String getZipCodeToPrint() {
		return customFormat("00000", zipCode);
	}

	/**
	 * Returns the Person's first name
	 * 
	 * @return givenName: the Person's first name
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * Returns the Person's last name
	 * 
	 * @return surName: the Person's last name
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * Returns the Person's address
	 * 
	 * @return address: the Person's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Returns the city in which the Person lives in
	 * 
	 * @return city: the city in which the Person lives in
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Returns the state in which the Person lives in
	 * 
	 * @return state: the state in which the Person lives in
	 */
	public String getState() {
		return state;
	}

	/**
	 * Returns the Person's blood type
	 * 
	 * @return bloodType: the Person's blood type
	 */
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * Returns the zip code in which the Person lives in
	 * 
	 * @return zipCode: the zip code in which the Person lives in
	 */
	public int getZipCode() {
		return zipCode;
	}

	/** Starting Setters * */

	/**
	 * Sets the Person's first name
	 * 
	 * @param givenName
	 *            : the Person's first name
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * Sets the Person's last name
	 * 
	 * @param surName
	 *            : the Person's last name
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * Sets the Person's address
	 * 
	 * @param address
	 *            : the Person's address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Sets the city in which the Person lives in
	 * 
	 * @param city
	 *            : the city in which the Person lives in
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Sets the state in which the Person lives in
	 * 
	 * @param state
	 *            : the state in which the Person lives in
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Sets the Person's blood type
	 * 
	 * @param bloodType
	 *            : the Person's blood type
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
		simplifyBloodType();
	}

	/**
	 * Sets the zip code in which the Person lives in
	 * 
	 * @param zipCode
	 *            : the zip code in which the Person lives in
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/** End Getters and Setters * */

	/**
	 * Returns a string representation of the person
	 *
	 * @return a string representation of the person
	 */
	public String toString() {
		return "" + givenName + " " + surName + " " + address + " " + city
				+ " " + state + " " + zipCode + " " + bloodTypeABOAB + " "
				+ bloodTypePosNeg;
	}

	/**
	 * Returns the count
	 * 
	 * @return compareCount: the number of comparisons made by the sorting methods.
	 */
	public int getCount() {
		return compareCount;
	}

	/**
	 * Compares a person to another person per the spec
	 * 
	 * @param The
	 *            person to compare to
	 */
	public int compareTo(Person a) {
		int result;
		this.toString();
		a.toString();
		if ((compareCount++) == compareCount && this.bloodTypeABOAB.compareTo(a.bloodTypeABOAB) != 0) {
			return this.bloodTypeABOAB.compareTo(a.bloodTypeABOAB);
		} else if ((compareCount++) == compareCount && this.bloodTypePosNeg != a.bloodTypePosNeg) {
			if ((compareCount++) == compareCount && this.bloodTypePosNeg == true) {
				return 1;
			} else {
				return -1;
			}
		}

		return 0;

		/*
		 * Previously modified Sorting.java to get count using "(countMerge++) == countMerge &&"
		 * in each while or if, found this didnt work.  Moved count to compareTo().

		/*
		 * if(!this.bloodTypeABOAB.equals(a.bloodTypeABOAB) &&
		 * this.bloodTypePosNeg != a.bloodTypePosNeg){ //Sort by blood type
		 * alphabetically, then sort based on Rhesus factor, positive first, neg
		 * second if(bloodTypeABOAB.equals("A") &&
		 * !a.bloodTypeABOAB.equals("A")){
		 * 
		 * } }else if(this.bloodTypeABOAB.equals(a.bloodTypeABOAB)){ //Sort
		 * based on Rhesus factor, positive first, neg second }else
		 * if(this.bloodTypeABOAB.equals(a.bloodTypeABOAB) &&
		 * this.bloodTypePosNeg == a.bloodTypePosNeg){ //Sort alphabetically
		 * based on state }
		 * 
		 * 
		 * return result;
		 */
	}

	/**
	 * Simplifies the blood type to prepare to compare per spec
	 */
	public void simplifyBloodType() {
		if (bloodType.length() == 2) {
			bloodTypeABOAB = "" + bloodType.charAt(0);
			char tmpChar = bloodType.charAt(1);
			if (tmpChar == '-') {
				bloodTypePosNeg = false;
			} else if (tmpChar == '+') {
				bloodTypePosNeg = true;
			}
		} else if (bloodType.length() == 3) {
			bloodTypeABOAB = "" + bloodType.charAt(0) + bloodType.charAt(1);
			char tmpChar = bloodType.charAt(2);
			if (tmpChar == '-') {
				bloodTypePosNeg = false;
			} else if (tmpChar == '+') {
				bloodTypePosNeg = true;
			}
		} else {
			System.out.println("Found an alien -- No Blood Type");
		}

		bloodTypeABOAB = bloodTypeABOAB.toUpperCase();
	}

	/**
	 * Returns a prettified version of the input.
	 * 
	 * @param pattern
	 *            Desired pattern
	 * @param value
	 *            Input value
	 */
	private String customFormat(String pattern, double value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return output;
	}
}