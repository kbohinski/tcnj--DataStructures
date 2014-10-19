/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @version 1.0
 * @since 2014-04-30
 * 
 * Spring 2014 
 * CSC 230 Project 3 Hashing
 * 
 * HTTPLog.java
 */

/** Setting Package **/
package edu.tcnj.csc230;

/** Setting Imports **/
import java.util.*;

public class HTTPLog {

	/** Setting Vars **/
	GregorianCalendar dateTime;
	String name;
	int resCode;
	long numBytes;
	long numRequests;

	/**
	 * @param dateTime
	 * @param name
	 * @param resCode
	 * @param numBytes
	 */
	public HTTPLog(GregorianCalendar dateTime, String name, int resCode,
			long numBytes) {
		super();
		this.dateTime = dateTime;
		// this.name = name;
		this.name = cleanInput(name);
		this.resCode = resCode;
		this.numBytes = numBytes;
		this.numRequests = 1;
	}

	@Override
	public int hashCode() {
		int sum = 0;
		String tmpName = name;
		while (!tmpName.equals("")) {
			sum += tmpName.charAt(0);
			tmpName = tmpName.substring(1);
		}

		sum /= 7;
		sum += 1855;
		return sum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HTTPLog other = (HTTPLog) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 *  
	 * @return the dateTime
	 */
	public GregorianCalendar getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime
	 *            the dateTime to set
	 */
	public void setDateTime(GregorianCalendar dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the resCode
	 */
	public int getResCode() {
		return resCode;
	}

	/**
	 * @param resCode
	 *            the resCode to set
	 */
	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	/**
	 * @return the numBytes
	 */
	public long getNumBytes() {
		return numBytes;
	}

	/**
	 * @param numBytes
	 *            the numBytes to set
	 */
	public void setNumBytes(long numBytes) {
		this.numBytes = numBytes;
	}

	/**
	 * @return the numRequests
	 */
	public long getNumRequests() {
		return numRequests;
	}

	public void incNumRequests() {
		numRequests++;
	}

	public void incNumBytes(long size) {
		numBytes += size;
	}

	@Override
	public String toString() {
		return "HTTPLog [dateTime=" + dateTime + ", name=" + name.toString()
				+ ", resCode=" + resCode + ", numBytes=" + numBytes
				+ ", numRequests=" + numRequests + "]";
	}

	/**
	 * Cleans the input to make comparisons easier
	 * 
	 * @param input
	 * @return
	 */
	private String cleanInput(String input) {
		int charLoc = -1;
		charLoc = input.indexOf('?');
		while (charLoc > -1) {
			input = truncateEnd(input, charLoc);
			charLoc = input.indexOf('?');
		}

		charLoc = -1;
		charLoc = input.indexOf("%27");
		while (charLoc > -1) {
			input = replace(input, "%27", "'");
			charLoc = input.indexOf("%27");
		}

		charLoc = -1;
		charLoc = input.indexOf("%28");
		while (charLoc > -1) {
			input = replace(input, "%28", "(");
			charLoc = input.indexOf("%28");
		}

		charLoc = -1;
		charLoc = input.indexOf("%29");
		while (charLoc > -1) {
			input = replace(input, "%29", ")");
			charLoc = input.indexOf("%29");
		}

		charLoc = -1;
		charLoc = input.indexOf("%20");
		while (charLoc > -1) {
			input = replace(input, "%20", " ");
			charLoc = input.indexOf("%20");
		}
		charLoc = -1;
		charLoc = input.indexOf("cs.tcnj.edu");
		while (charLoc > -1) {
			input = truncateStart(input, charLoc + 10);
			charLoc = input.indexOf("cs.tcnj.edu");
		}

		/* Scan for special chars. */
		charLoc = -1;
		charLoc = input.indexOf("%7E");
		while (charLoc > -1) {
			input = replace(input, "%7E", "~");
			charLoc = input.indexOf("%7E");
		}

		charLoc = -1;
		charLoc = input.indexOf("%80");
		while (charLoc > -1) {
			input = replace(input, "%80", "€");
			charLoc = input.indexOf("%80");
		}

		charLoc = -1;
		charLoc = input.indexOf("%93");
		while (charLoc > -1) {
			input = replace(input, "%93", "“");
			charLoc = input.indexOf("%93");
		}

		charLoc = -1;
		charLoc = input.lastIndexOf('/');
		while (charLoc > -1 && charLoc == (input.length() - 1) && charLoc != 0) {
			input = removeCharAt(input, charLoc);
			charLoc = input.lastIndexOf('/');
		}

		return input;
	}

	private String removeCharAt(String s, int i) {
		StringBuffer sbuf = new StringBuffer(s.length() - 1);
		sbuf.append(s.substring(0, i)).append(s.substring(i + 1));
		return sbuf.toString();
	}

	private String replace(String in, String toReplace, String replacement) {
		return in.replace(toReplace, replacement);
	}

	private String truncateEnd(String s, int i) {
		StringBuffer sbuf = new StringBuffer(s.length() + (i - s.length()));
		sbuf.append(s.substring(0, i));
		return sbuf.toString();
	}

	private String truncateStart(String s, int i) {
		StringBuffer sbuf = new StringBuffer(s.length() + (i - s.length()));
		sbuf.append(s.substring(i + 1, s.length()));
		return sbuf.toString();
	}

} /* public class HTTPLog */