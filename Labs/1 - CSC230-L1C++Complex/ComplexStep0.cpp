///  Complex.cpp : complex numbers
//  Class implementation for complex numbers 

#include "ComplexStep0.h"
#include <iostream>
#include <iomanip>


using namespace std;


	Complex::Complex ()
	{	
		real = 0;
		imag = 0;
	}


	Complex::Complex (float r, float i)
	{
		real = r;
		imag = i;	
	}	


	const Complex Complex::add (const Complex & rhsc) const 
	{
		//	Complex c, c1, c2;
		//	…
		// 	Used as: c = c1.add(c2);
		// 	Used as: c2 = c.add(c1);

		Complex comp;

		// comp.real = real + rhsc.real;
		// comp.real = (*this).real + rhsc.real;
		comp.real = this->real + rhsc.real;
		comp.imag = this->imag + rhsc.imag;

		return comp;
	}
 
	
	void Complex::inputComplex (void) 
	{
		cout << "Please enter a value for a complex number." << endl;

		// Complex c;
		// Used as: c.inputComplex();

		cout << "Real part: ";
		// cin >> real;
		// cin >> (*this).real;
		cin >> this->real;

		cout << "Imaginary part: ";
		// cin >> imag;
		// cin >> (*this).imag;
		cin >> this->imag;
	}


	void Complex::displayComplex (void)
	{
		// Complex c;
		// Used as: c.displayComplex();
                if((*this).real != 0 && (*this).imag != 0){
		  cout << setprecision(1) << std::fixed  << this->real;
                }
		if (this->imag < 0) {
			cout << setprecision(1) << std::fixed  << "-";
			if (this->imag != -1)
			  cout << (-this->imag);
                        
			cout << "i";
		} else if (this->imag >0){
			cout << "+";
			//if (this->imag != 1)
                        //Removing due to -1,1 input test
			  cout << this->imag;
			cout << "i";
		}
                 if (this->imag == 0){
                        cout << setprecision(1) << std::fixed  << this->real;
	        }
	}
