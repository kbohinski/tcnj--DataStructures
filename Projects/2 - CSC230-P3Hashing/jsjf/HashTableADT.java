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
package jsjf;

/** Setting Imports **/
import java.util.*;

import jsjf.exceptions.ElementNotFoundException;

public class HashTableADT<T> implements Iterable<T> {
	private LinkedList<T>[] list;
	private int numCollisions;
	private int length;
	private int cells;
	private int openCells;

	/**
	 * Constructor: Creates a hashtable using the indicated size
	 * 
	 * @param cells
	 */
	public HashTableADT(int cells) {
		this.cells = cells;
		numCollisions = 0;
		length = 0;
		openCells = cells;

		list = new LinkedList[cells];

		for (int i = 0; i < cells; i++) {
			list[i] = new LinkedList<T>();
		}
	}

	/**
	 * Adds an element
	 * 
	 * @param element
	 */
	public void add(T element) {
		int position = element.hashCode();
		int hashKey = Math.abs(position % list.length);
		if (list[hashKey].size() == 0) {
			(list[hashKey]).add(element);
			length++;
			openCells--;
		} else {
			(list[hashKey]).add(element);
			length++;
			numCollisions++;
		}
	}

	/**
	 * Checks to see if an indicated element is in the hashtable
	 * 
	 * @param element
	 * @return boolean indicating if the element exists
	 */
	public boolean contains(T element) {
		for (int i = 0; i < cells; i++) {
			LinkedList<T> temp = list[i];
			for (int j = 0; j < temp.size(); j++) {
				if (element.equals(temp.get(j))) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Returns an element in the hashtable
	 * 
	 * @param element
	 * @return the element requested
	 * @throws ElementNotFoundException
	 */
	public T getItem(T element) throws ElementNotFoundException {
		for (int i = 0; i < cells; i++) {
			LinkedList<T> temp = list[i];
			for (int j = 0; j < temp.size(); j++) {
				if (element.equals(temp.get(j))) {
					return list[i].get(j);
				}
			}
		}

		throw new ElementNotFoundException("HashTableADT");
	}

	/**
	 * Removes an element in the hashtable
	 * 
	 * @param element
	 * @throws ElementNotFoundException
	 */
	public void removeItem(T element) throws ElementNotFoundException {
		for (int i = 0; i < cells; i++) {
			LinkedList<T> temp = list[i];
			for (int j = 0; j < temp.size(); j++) {
				if (element == temp.get(j)) {
					list[i].remove(j);
					if (list[i].size() == 0) {
						length--;
						openCells++;
					} else {
						length--;
						numCollisions--;
					}
				}
			}
		}

		throw new ElementNotFoundException("HashTableADT");
	}

	/**
	 * Checks if the hashtable is empty
	 * 
	 * @return boolean indicating if hashtable is empty
	 */
	public boolean isEmpty() {
		if (length == 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Checks num of elements collided in hashtable
	 * 
	 * @return int num of elements collided in hashtable
	 */
	public int getNumCollisions() {
		return numCollisions;
	}

	/**
	 * Checks num of elements in hashtable
	 * 
	 * @return int num of elements in hashtable
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Checks the number of open cells
	 * 
	 * @return int of the number of open cells
	 */
	public int getOpenCells() {
		return openCells;
	}

	@Override
	public Iterator<T> iterator() {
		HashSet<T> hs = new HashSet<T>();

		for (int i = 0; i < cells; i++) {
			LinkedList<T> temp = list[i];
			hs.addAll(temp);
		}

		Iterator<T> iter = hs.iterator();

		return iter;
	}
}
