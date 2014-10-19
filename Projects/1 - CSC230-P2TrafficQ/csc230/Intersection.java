/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @version 1.0
 * @since 2014-04-06
 * 
 * Spring 2014 
 * CSC 230 Project 2 TrafficQ
 * 
 * Intersection.java
 */

package csc230;

import java.util.*;
import java.lang.Enum;
import java.util.*;
import java.io.*;
import java.text.*;

import jsjf.*;
import jsjf.exceptions.*;

public class Intersection{
	/** FileWriters **/
	FileWriter errors;
	FileWriter out;
	
	/** Church Northbound Traffic **/
	LinkedQueue<Vehicle> cn = new LinkedQueue<Vehicle>();
	LinkedQueue<Vehicle> cnr = new LinkedQueue<Vehicle>();
	
	/** Church Southbound Traffic **/
	LinkedQueue<Vehicle> cs = new LinkedQueue<Vehicle>();
	LinkedQueue<Vehicle> csr = new LinkedQueue<Vehicle>();
	
	/** Main Eastbound Traffic **/
	LinkedQueue<Vehicle> me = new LinkedQueue<Vehicle>();
	LinkedQueue<Vehicle> mer = new LinkedQueue<Vehicle>();
	
	/** Main Westbound Traffic **/
	LinkedQueue<Vehicle> mw = new LinkedQueue<Vehicle>();
	LinkedQueue<Vehicle> mwr = new LinkedQueue<Vehicle>();
	
	/** vehicleCount: counter used to keep track of how many vehicles are in the intersection **/
	/** waveCount:    counter used to keep track of how many vehicles are in a wave **/
	/** timer:        keeps track of time in the simulation **/
	/** low and high: used to set the bounds of the number of vehicles in a wave **/
	/** north/south/east/west: counters to keep track of total num of vehicles in each direction **/
	int vehicleCount;
	int waveCount;
	int timer;
	int low;
	int high;
	int north, south, east, west;
	Random r = new Random();
	
	/** Temp Vehicle for printing **/
	Vehicle t;
	
	/**
	 * Constructor : Creates a Intersection object from the given data.
	 */
	Intersection(){
		try{
			errors = new FileWriter(new File("errors.txt"));
			out = new FileWriter(new File("output.txt"));
		}catch(IOException e){
			System.out.println("Error making files.");
			System.exit(1);
		}
		try {
			out.write("---Start of simulation, time is set to 0.---\n");
		} catch (IOException e) {
			iHaveAnError("error while writing during constructor.");
		}
		vehicleCount = 1;
		waveCount = 0;
		timer = 0;
		low = 0;
		high = 0;
		north = 0;
		south = 0;
		east = 0;
		west = 0;
		
	}/** End Constructor **/
	
	/**
	 * Creates a new wave of Vehicles
	 * @param max max number of Vehicles in the wave
	 * @param min min number of Vehicles in the wave
	 */
	public void newWave(int max, int min){
		low = min;
		high = max;
		waveCount = low + r.nextInt((high-low+1));
		for (int i = 0; i <= waveCount; i++){
			if (vehicleCount <= 120){
				int tempDir;
				tempDir = r.nextInt(8) + 1;
				if (tempDir == 1){
					Vehicle newVehicle = new Vehicle(vehicleCount, timer, 'n');
					cs.enqueue(newVehicle);
				}else if (tempDir == 2){
					Vehicle newVehicle = new Vehicle(vehicleCount, timer, 's');
					cn.enqueue(newVehicle);
				}else if (tempDir == 3){
					Vehicle newVehicle = new Vehicle(vehicleCount, timer, 'e');
					mw.enqueue(newVehicle);
				}else if (tempDir == 4){
					Vehicle newVehicle = new Vehicle(vehicleCount, timer, 'w');
					me.enqueue(newVehicle);
				}else if (tempDir == 5){
					Vehicle newVehicle = new Vehicle(vehicleCount, timer, 'w');
					cnr.enqueue(newVehicle);
				}else if (tempDir == 6){
					Vehicle newVehicle = new Vehicle(vehicleCount, timer, 'e');
					csr.enqueue(newVehicle);
				}else if (tempDir == 7){
					Vehicle newVehicle = new Vehicle(vehicleCount, timer, 'n');
					mer.enqueue(newVehicle);
				}else if (tempDir == 8){
					Vehicle newVehicle = new Vehicle(vehicleCount, timer, 's');
					mwr.enqueue(newVehicle);
				}
				vehicleCount ++;
			} /** End if **/
		} /** End for **/
	} /** End newWave() **/
	
	/**
	 * Runs the simulation according to project spec.
	 */
	public void runSimulation(){
		newWave(12,7);
		while(cn.size() > 0 || cs.size() > 0 || me.size() > 0 || mw.size() > 0 || cnr.size() > 0 || csr.size() > 0 || mer.size() > 0 || mwr.size() > 0){
			nsLight();
			newWave(15,8);
			ewLight();
			newWave(15,3);
		}
	}
	
	/**
	 * Closes all FileWriters.
	 */
	public void closeWriters(){
		try {
			out.close();
			errors.close();
		} catch (IOException e) {
			System.exit(1);
		}
	}
	
	/**
	 * Switches light to run the simulation in the north and south direction.
	 */
	public void nsLight(){
		/** Runs for 6 seconds **/
		/** Every 3 seconds a vehicle can go **/
		try{
			if(cn.size() > 0 || cs.size() > 0 || cnr.size() > 0 || csr.size() > 0){
				out.write("---Light Changed. Now processing north/south bound traffic---\n");
				for(int i = 1; i <= 2; i++){
					timer += 3;
					if(cn.size() > 0){
						t = cn.dequeue();
						south ++;
						t.setDepartureTime(timer);
						out.write("[Time " + customFormat("00", timer) + "] Vehicle #" + t.getVehicleNum() + " (southbound) continued straight. Total wait time " + (t.getDepartureTime() - t.getArrivalTime()) + " seconds." + "\n");
					}if(cs.size() > 0){
						t = cs.dequeue();
						north ++;
						t.setDepartureTime(timer);
						out.write("[Time " + customFormat("00", timer) + "] Vehicle #" + t.getVehicleNum() + " (northbound) continued straight. Total wait time " + (t.getDepartureTime() - t.getArrivalTime()) + " seconds." + "\n");
					}if(cnr.size() > 0){
						t = cnr.dequeue();
						west ++;
						t.setDepartureTime(timer);
						out.write("[Time " + customFormat("00", timer) + "] Vehicle #" + t.getVehicleNum() + " (southbound) turned right and headed westbound. Total wait time " + (t.getDepartureTime() - t.getArrivalTime()) + " seconds." + "\n");
					}if(csr.size() > 0){
						t = csr.dequeue();
						east ++;
						t.setDepartureTime(timer);
						out.write("[Time " + customFormat("00", timer) + "] Vehicle #" + t.getVehicleNum() + " (northbound) turned right and headed eastbound. Total wait time " + (t.getDepartureTime() - t.getArrivalTime()) + " seconds." + "\n");
					}
				}
				out.write("\n");
			}else{
				timer += 6;
			}
		}catch(IOException e){
			iHaveAnError("error while writing during nsLight().");
		}
	}
	
	/**
	 * Switches light to run the simulation in the north and south direction.
	 */
	public void ewLight(){
		/** Runs for 9 seconds **/
		/** Every 3 seconds a vehicle can go **/
		try{
			if(me.size() > 0 || mw.size() > 0 || mer.size() > 0 || mwr.size() > 0){
				out.write("---Light Changed. Now processing east/west bound traffic---\n");
				for(int i = 1; i <= 3; i++){
					timer += 3;
					if(me.size() > 0){
						t = me.dequeue();
						west ++;
						t.setDepartureTime(timer);
						out.write("[Time " + customFormat("00", timer) + "] Vehicle #" + t.getVehicleNum() + " (westbound) continued straight. Total wait time " + (t.getDepartureTime() - t.getArrivalTime()) + " seconds." + "\n");
					}if(mw.size() > 0){
						t = mw.dequeue();
						east ++;
						t.setDepartureTime(timer);
						out.write("[Time " + customFormat("00", timer) + "] Vehicle #" + t.getVehicleNum() + " (eastbound) continued straight. Total wait time " + (t.getDepartureTime() - t.getArrivalTime()) + " seconds." + "\n");
					}if(mer.size() > 0){
						t = mer.dequeue();
						north ++;
						t.setDepartureTime(timer);
						out.write("[Time " + customFormat("00", timer) + "] Vehicle #" + t.getVehicleNum() + " (westbound) turned right and headed north. Total wait time " + (t.getDepartureTime() - t.getArrivalTime()) + " seconds." + "\n");
					}if(mwr.size() > 0){
						t = mwr.dequeue();
						south ++;
						t.setDepartureTime(timer);
						out.write("[Time " + customFormat("00", timer) + "] Vehicle #" + t.getVehicleNum() + " (eastbound) turned right and headed south. Total wait time " + (t.getDepartureTime() - t.getArrivalTime()) + " seconds." + "\n");
					}
				}
				out.write("\n");
			}else{
				timer += 9;
			}
		}catch(IOException e){
			iHaveAnError("error while writing during ewLight().");
		}
	}
	
	/**
	 * Prints the error to the errors.txt file as per project spec.
	 * @param error Simple description of error
	 */
	public void iHaveAnError(String error){
		try{
			errors.write("\nError: " + error);
			System.err.println("Error: " + error);
			System.exit(1);
		}catch(IOException e){
			System.exit(1);
		}
	}

	/**
	 * Returns a prettified version of the input.
	 * @param pattern Desired pattern
	 * @param value Input value
	 */
	private String customFormat(String pattern, double value ) {
	    DecimalFormat myFormatter = new DecimalFormat(pattern);
	    String output = myFormatter.format(value);
		return output;
	}
	
} /** End public class Intersection **/