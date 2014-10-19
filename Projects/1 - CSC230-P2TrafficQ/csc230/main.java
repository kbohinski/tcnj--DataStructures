/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @version 1.0
 * @since 2014-04-06
 * 
 * Spring 2014 
 * CSC 230 Project 2 TrafficQ
 * 
 * main.java
 */

package csc230;

import java.util.*;
import java.lang.Enum;
import java.util.*;
import java.io.*;

import jsjf.*;
import jsjf.exceptions.*;

public class main {

	/**
	 * main method, completes simulation
	 * @param args
	 */
	public static void main(String[] args) {
		Intersection i1 = new Intersection();
		i1.runSimulation();
		i1.closeWriters();
	}
}