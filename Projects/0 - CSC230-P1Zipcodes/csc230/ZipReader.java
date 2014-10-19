/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @version 1.0
 * @since 2014-03-24
 * 
 * Spring 2014 
 * CSC 230 Project 1 Zipcodes
 * 
 * ZipReader.java
 */

package csc230;

import java.net.*;
import java.util.*;
import java.io.*;

public class ZipReader {

	private int maxNumLines = 50000;
	private int currLine = 0;
	private ZipCodeLocation[] zcl = new ZipCodeLocation[maxNumLines];

	public ZipCodeLocation[] getZcl() {
		return zcl;
	}

	public int getMnl() {
		return maxNumLines;
	}

	public void read() throws IOException {
		URL zipCodes = new URL(
				"http://www.tcnj.edu/~mmmartin/Courses/CSC250/DataSources/zipcodes.txt");
		URLConnection netConn = zipCodes.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				zipCodes.openStream()));

		String inputLine = "";
		zipCodes.openConnection();

		while ((inputLine = in.readLine()) != null) {
			inputLine = inputLine.replaceAll("\"+", "");
			String[] tmpString = inputLine.split(",");

			// tmpString[0] = tmpString[0].replaceAll("\\s*", "");
			// tmpString[1] = tmpString[1].replaceAll("\\s*", "");
			// tmpString[2] = tmpString[2].replaceAll("\\s*", "");

			try {
				zcl[currLine] = new ZipCodeLocation(
						Double.parseDouble(tmpString[0]),
						Double.parseDouble(tmpString[1]),
						Double.parseDouble(tmpString[2]), tmpString[3],
						tmpString[4], tmpString[5], tmpString[6]);
			} catch (NumberFormatException e) {
				zcl[currLine] = new ZipCodeLocation((double) 0, (double) 0,
						(double) 0, tmpString[3], tmpString[4], tmpString[5],
						tmpString[6]);
			}

			/*
			 * if (tmpString[0] == "" || tmpString[1] == "" || tmpString[2] ==
			 * "") { //Dont store elements }else{
			 * 
			 * }
			 */

			currLine++;
		}
		maxNumLines = currLine;
		in.close();
	}

	public ZipCodeLocation[] findState(String a) {
		ZipCodeLocation[] zclStateFlitered = new ZipCodeLocation[maxNumLines];
		int numMatches = 0;

		for (int i = 0; i < maxNumLines; i++) {
			if (zcl[i].getState().equals(a)) {
				zclStateFlitered[numMatches] = zcl[i];
				numMatches++;
			}
		}

		ZipCodeLocation[] zclToReturn = new ZipCodeLocation[numMatches];
		for (int i = 0; i < numMatches; i++) {
			zclToReturn[i] = zclStateFlitered[i];
		}

		Sorting.mergeSort(zclToReturn);
		return zclToReturn;
	}

	public String[] findCountyDeliveryMethod(String a) {
		ZipCodeLocation[] zclDeliveryTownFlitered = new ZipCodeLocation[maxNumLines];
		String[] zclDeliveryCountyFiltered = new String[maxNumLines];
		int numTownMatches = 0;
		int numCountyMatches = 0;

		for (int i = 0; i < maxNumLines; i++) {
			if (zcl[i].getDelivType().equals(a)) {
				zclDeliveryTownFlitered[numTownMatches] = zcl[i];
				numTownMatches++;
			}
		}

		HashSet<String> hs = new HashSet<String>();

		for (int i = 0; i < numTownMatches; i++) {
			for (int u = i + 1; u < numTownMatches; u++) {
				if (i != u
						&& zclDeliveryTownFlitered[i].getCounty().equals(
								zclDeliveryTownFlitered[u].getCounty())) {
					numCountyMatches++;
					hs.add(zclDeliveryTownFlitered[i].getCounty());
				}
			}
		}

		String[] zclToReturn = new String[hs.size()];
		zclToReturn = hs.toArray(zclToReturn);

		Sorting.mergeSort(zclToReturn);
		return zclToReturn;
	}

	public ZipCodeLocation[] uniqueZip() {
		HashSet<ZipCodeLocation> hs = new HashSet<ZipCodeLocation>();

		for (int i = 0; i < maxNumLines; i++) {
			for (int u = i + 1; u < maxNumLines; u++) {
				float tmpLat_I = (float) zcl[i].getLat().floatValue();
				float tmpLat_U = (float) zcl[u].getLat().floatValue();
				float tmpLon_I = (float) zcl[i].getLon().floatValue();
				float tmpLon_U = (float) zcl[u].getLon().floatValue();
				if (i != u && zcl[i].getZipCodes() != 0 && tmpLat_I == tmpLat_U && tmpLon_I == tmpLon_U
						&& zcl[i].getZipCodes() != zcl[u].getZipCodes()) {
					hs.add(zcl[i]);
				}
			}
		}

		ZipCodeLocation[] zclToReturn = new ZipCodeLocation[hs.size()];
		hs.toArray(zclToReturn);
		for (int i = 0; i < zclToReturn.length; i++) {
			zclToReturn[i].setCounty("");
			zclToReturn[i].setDelivType("");
		}
		
		Sorting.mergeSort(zclToReturn);
		return zclToReturn;
	}
	
	public String uniqueDelivType() {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		for(int i = 0; i < maxNumLines; i++){
			if (!hm.containsKey(zcl[i].getDelivType())) {
				/* New Delivery Type, add a NEW key. */
				hm.put(zcl[i].getDelivType(), 1);
			} else {
				/* Modify Existing Key */
				Integer tmp = (Integer) hm.get(zcl[i].getDelivType());
				hm.put(zcl[i].getDelivType(), tmp.intValue() + 1);
			}
		}
		return hm.toString();
	}
	
	public Integer[] springfield() {
		Integer[] springfieldZips = new Integer[maxNumLines];
		int numMatches = 0;
		
		for (int i = 0; i < maxNumLines; i++) {
			if (zcl[i].getTown().toLowerCase().contains("springfield")) {
				springfieldZips[numMatches] = zcl[i].getZipCodes().intValue();
				numMatches++;
			}
		}
		
		
		Integer[] toReturn = new Integer[numMatches];
		for (int i = 0; i < numMatches; i++) {
			toReturn[i] = springfieldZips[i];
		}
		return toReturn;
	}
}
