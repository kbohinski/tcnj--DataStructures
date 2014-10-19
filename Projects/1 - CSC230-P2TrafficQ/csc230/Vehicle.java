/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @version 1.0
 * @since 2014-04-06
 * 
 * Spring 2014 
 * CSC 230 Project 2 TrafficQ
 * 
 * Vehicle.java
 */

package csc230;

import java.util.*;
import java.lang.Enum;
import java.util.*;
import java.io.*;

import jsjf.*;
import jsjf.exceptions.*;

public class Vehicle {
	
	int vehicleNum;
	int arrivalTime;
	int departureTime;
	
	enum direction{
		N,S,E,W
	}private direction dir;
	
	enum street{
		Church,Main
	}private street str;
	
	/**
	 * Constructor : Creates a Vehicle object from the given data.
	 * 
	 * @param vehNum		Number given to car upon creation
	 * @param startTime		Time vehicle arrived at intersection.
	 * @param dirIn			Direction car is traveling.
	 */
	Vehicle(int vehNum, int startTime, char dirIn){
		vehicleNum = vehNum;
		arrivalTime = startTime;
		departureTime = -1;	
		
		if(dirIn == 'n'){
			dir = direction.N;
		}else if(dirIn == 's'){
			dir = direction.S;
		}else if(dirIn == 'e'){
			dir = direction.E;
		}else if(dirIn == 'w'){
			dir = direction.W;
		}
		
		if(dir == direction.N || dir == direction.S){
			str = street.Church;
		}else if (dir == direction.E || dir == direction.W){
			str = street.Main;
		}
		
	} /** End Constructor **/
	
	/**
	 * Returns number of the vehicle.
	 * @return The vehicle number
	 */
	public int getVehicleNum() {
		return vehicleNum;
	}
	
	/**
	 * Sets the number associated with the vehicle.
	 * @param vehicleNum The number given to the vehicle
	 */
	public void setVehicleNum(int vehicleNum) {
		this.vehicleNum = vehicleNum;
	}

	/**
	 * Returns the time of arrival for the Vehicle object.
	 * @return Arrival time for the vehicle
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Changes the time of arrival for the Vehicle object.
	 * @param arrivalTime Arrival time for the vehicle
	 */
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Returns the time of departure for the Vehicle object.
	 * @return Departure time for the vehicle
	 */
	public int getDepartureTime() {
		return departureTime;
	}

	/**
	 * Changes the time of departure for the Vehicle object.
	 * @param departureTime Departure time for vehicle
	 */
	public void setDepartureTime(int departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * Returns the direction that the vehicle is traveling in.
	 * @return Direction the vehicle is traveling in
	 */
	public direction getDir() {
		return dir;
	}

	/**
	 * Changes the direction that the vehicle is traveling in.
	 * @param dir Direction the vehicle is traveling in
	 */
	public void setDir(direction dir) {
		this.dir = dir;
	}

	/**
	 * Returns the name of the street the vehicle is traveling on.
	 * @return The street name
	 */
	public street getStr() {
		return str;
	}

	/**
	 * Sets the name of the street the vehicle is traveling on.
	 * @param str The street name
	 */
	public void setStr(street str) {
		this.str = str;
	}
	
} /** End public class Vehicle **/
