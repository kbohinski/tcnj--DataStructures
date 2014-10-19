/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @version 1.0
 * @since 2014-04-30
 * 
 * Spring 2014 
 * CSC 230 Project 3 Hashing
 * 
 * Driver.java
 */

/** Setting Package **/
package edu.tcnj.csc230;

/** Setting Imports **/
import java.util.*;
import java.io.*;
import java.text.*;
import jsjf.HashTableADT;

public class Driver {

	static int lineCount = 0;
	static int numDupes = 0;

	/**
	 * main method, executes the log analysis program
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * Example line from input (word wrap)
		 * 
		 * 200.88.223.98 - - [01/Feb/2007:04:02:22 -0500]
		 * "POST /gallery/main.php HTTP/1.1" 302 -
		 * "http://cs.tcnj.edu/gallery/main.php?g2_view=comment.AddComment&g2_itemId=664&g2_return=http%3A%2F%2Fcs.tcnj.edu%2Fgallery%2Fv%2Fevents%2Falbum02%2Fcontests%2FprogrammingContest05%2F%3Fg2_GALLERYSID%3D3be9666f9c07e16b7f33e2ea8acb8dd2&g2_GALLERYSID=3be9666f9c07e16b7f33e2ea8acb8dd2&g2_returnName=album"
		 * "Opera/6.01 (Windows 98; U) [en]"
		 */
		File file = new File("access_log.txt");
		Scanner in = new Scanner(file);

		HashTableADT<HTTPLog> ht = new HashTableADT<HTTPLog>(5000);

		while (in.hasNextLine()) {
			String input = in.nextLine();
			String splitInput[] = input.split(" ");

			int errCode;
			String url;
			String requestType;
			long size;

			try {
				errCode = Integer.parseInt(splitInput[8]);
			} catch (NumberFormatException nfe) {
				errCode = 200;
			}

			url = splitInput[6];
			requestType = splitInput[5];

			try {
				size = Long.parseLong(splitInput[9]);
			} catch (NumberFormatException nfe) {
				size = 0;
			}

			/*
			 * public HTTPLog(GregorianCalendar dateTime, String name, int
			 * resCode, long numBytes) {
			 */

			/*
			 * GregorianCalendar(int year, int month, int dayOfMonth, int
			 * hourOfDay, int minute, int second)
			 */

			String tmpDate = splitInput[3];
			tmpDate = truncateStart(tmpDate, 0);
			String splitDate[] = tmpDate.split("/");
			int day = Integer.parseInt(splitDate[0]);
			String month = splitDate[1];

			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(new SimpleDateFormat("MMM").parse(month));
			} catch (ParseException e) {
				/* Do Nothing */
			}
			int monthInt = cal.get(Calendar.MONTH) + 1;

			String splitTime[] = splitDate[2].split(":");
			int year = Integer.parseInt(splitTime[0]);
			int hr = Integer.parseInt(splitTime[1]);
			int min = Integer.parseInt(splitTime[2]);
			int sec = Integer.parseInt(splitTime[3]);

			splitInput[4] = truncateEnd(splitInput[4],
					splitInput[4].length() - 3);
			int tz = Integer.parseInt(splitInput[4]) * 60 * 60 * 1000;

			GregorianCalendar tmpDT = new GregorianCalendar(year, monthInt,
					day, hr, min, sec);
			tmpDT.setTimeZone(TimeZone.getTimeZone(TimeZone.getAvailableIDs(tz)[0]));
			HTTPLog tmp = new HTTPLog(tmpDT, url, errCode, size);

			if (ht.contains(tmp)) {
				numDupes++;
				ht.getItem(tmp).incNumRequests();
				ht.getItem(tmp).incNumBytes(size);
			} else {
				ht.add(tmp);
			}

			/*
			 * try { ht.getItem(tmp).incNumRequests(); } catch
			 * (ElementNotFoundException enfe) { ht.add(tmp); }
			 */

			lineCount++;
		}

		in.close();
		
		// System.out.println(ht.toStringWithContents());

		boolean menu = true;
		int choice = 0;

		Scanner input = new Scanner(System.in);

		while (menu == true) {
			menu = false;
			choice = 0;
			System.out.println("Select your choice: ");
			System.out
					.println("1: Detail\n2: Chain\n3: Top ten\n4: Last\n5: Quit");
			choice = input.nextInt();

			if (choice == 1) {
				// Do Detail
				Iterator<HTTPLog> iter = ht.iterator();
				long totalHits = 0;
				long totalBytes = 0;
				while (iter.hasNext()) {
					HTTPLog httpTemp = iter.next();
					totalBytes += httpTemp.getNumBytes();
					totalHits += httpTemp.getNumRequests();
				}
				System.out.println("\nTotal Bytes Served: " + totalBytes);
				System.out.println("Total hits: " + totalHits);
				System.out.println("Num Ln: " + lineCount + " Num Dupe: " + numDupes);

				menu = true;
			} else if (choice == 2) {
				// do Chain
				menu = true;
			} else if (choice == 3) {
				// do top ten
				menu = true;
			} else if (choice == 4) {
				// do last
				menu = true;
				System.out.println(ht.iterator().next());
			} else if (choice == 5) {
				System.out.println("Goodbye");
				input.close();
				System.exit(1);
			} else {
				System.out.println("Your choice was not in range, try again");
				menu = true;
			}

		}
	} /* public static void main(String[] args) */

	/**
	 * @param s
	 * @param i
	 * @return
	 */
	public static String removeCharAt(String s, int i) {
		StringBuffer sbuf = new StringBuffer(s.length() - 1);
		sbuf.append(s.substring(0, i)).append(s.substring(i + 1));
		return sbuf.toString();
	}

	/**
	 * @param in
	 * @param toReplace
	 * @param replacement
	 * @return
	 */
	public static String replace(String in, String toReplace, String replacement) {
		return in.replace(toReplace, replacement);
	}

	/**
	 * @param s
	 * @param i
	 * @return
	 */
	public static String truncateEnd(String s, int i) {
		StringBuffer sbuf = new StringBuffer(s.length() + (i - s.length()));
		sbuf.append(s.substring(0, i));
		return sbuf.toString();
	}

	/**
	 * @param s
	 * @param i
	 * @return
	 */
	public static String truncateStart(String s, int i) {
		StringBuffer sbuf = new StringBuffer(s.length() + (i - s.length()));
		sbuf.append(s.substring(i + 1, s.length()));
		return sbuf.toString();
	}

} /* public class Driver */