//  ComplexDriver.cpp

//  Skeleton of driver program to test the implementation of the 
//  Complex class
//  For use in class

//  ** Add your maintenance documentation where appropriate **

#include <iostream>
#include "ComplexStep0.h"

using namespace std;

main()
{
    Complex comp1 (1, -1), comp2 (-2, 7), comp3;      // some complex numbers for our demonstration
    
    //    Complex comp4 (6);
	

    // write simple statements that test the various functions
    // for example,
    cout << "The Complex number comp1 is ";
    comp1.displayComplex();
    cout << endl;
    cout << "The Complex number comp2 is ";
    comp2.displayComplex();
    cout << endl;
	cout << "Input a complex number: ";
	comp3.inputComplex();
    cout << endl;
    cout << "The Complex number comp3 is ";
    comp3.displayComplex();
    cout << endl;
/*
	cout << "The Complex number comp4 is ";
    comp4.displayComplex();
    cout << endl;
*/	
	
	cout << "Adding comp1 and comp2 using member function add results in: ";
	comp3 = comp1.add(comp2);
    comp3.displayComplex();
    cout << endl;
	
	/*
    cout << "Adding 5 and comp2 using nonmember function add results in: ";
	comp3 = add(5,comp2);
    comp3.displayComplex();
    cout << endl;
			*/
	cout << "Exiting program. Bye." << endl << endl;
}
