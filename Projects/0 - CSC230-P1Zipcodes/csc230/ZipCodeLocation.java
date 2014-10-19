/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @version 1.0
 * @since 2014-03-24
 * 
 * Spring 2014 
 * CSC 230 Project 1 Zipcodes
 * 
 * ZipCodeLocation.java
 */

package csc230;

import java.io.IOException;
import java.text.*;

public class ZipCodeLocation implements Comparable<ZipCodeLocation> {

	Double zipCodes;
	Double lat;
	Double lon;
	String town;
	String state;
	String county;
	String delivType;
	
	public ZipCodeLocation(Double zipCodes, Double lat, Double lon,
			String town, String state, String county, String delivType) {
		this.zipCodes = zipCodes;
		this.lat = lat;
		this.lon = lon;
		this.town = town;
		this.state = state;
		this.county = county;
		this.delivType = delivType;
	}

	public Double getZipCodes() {
		return zipCodes;
	}

	public void setZipCodes(Double zipCodes) {
		this.zipCodes = zipCodes;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDelivType() {
		return delivType;
	}

	public void setDelivType(String delivType) {
		this.delivType = delivType;
	}
	
	public String toString(){
		try{
			String tempStringLat = new String();
			String tempStringLon = new String();
			if (lat > 0){
				tempStringLat = "+00.000000";
			}else{
				tempStringLat = "00.000000";
			}if (lon > 0){
				tempStringLon = "+00.000000";
			}else{
				tempStringLon = "00.000000";
			}return "" + customFormat("00000", zipCodes) + "\t" + customFormat(tempStringLat, lat) + "\t" + customFormat(tempStringLon, lon) + "\t" + String.format("%-25s", town) + "\t" + state + "\t" + String.format("%-25s", county) + "\t" + delivType;
		}catch(NullPointerException e){
			return "" + 0 + " " + 0 + " " + 0 + " " + town + " " + state + " " + county + " " + delivType;
		}
	}

	@Override
	public int compareTo(ZipCodeLocation a) {
		int result;
		
		if(this.state.equals(a.state)){
			result = this.town.compareTo(a.town); 
		}else{
			result = this.state.compareTo(a.state);
		}
		return result;
	}
	
	private String customFormat(String pattern, double value ) {
	      DecimalFormat myFormatter = new DecimalFormat(pattern);
	      String output = myFormatter.format(value);
		return output;
	}
}
