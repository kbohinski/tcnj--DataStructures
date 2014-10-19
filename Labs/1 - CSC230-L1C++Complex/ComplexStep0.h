///  Complex.h : complex numbers

//  Class defintion for complex numbers 

//  ** Add maintenance and interface documentation where appropriate **

#if ! defined (COMPLEX_H)
#define COMPLEX_H

#include <iostream>

using namespace std;

class Complex
{
private:
    float real;
    float imag;
	
public:
	// add methods as needed
    Complex ();
    Complex (float, float);
   // Complex (float = 0, float = 0);

    const Complex add (const Complex &) const;
 
	void inputComplex (void);

	void displayComplex (void);

};


#endif  // COMPLEX_H




