package RiemannNMK;
import org.opensourcephysics.display.Trail;

import polyfun.*;

public class PolyPractice {
	/* eval
	 *  takes a polynomial and a double and it returns a double.
	 *  The value of the returned double is the polynomial evaluated
	 *  at the input double.
	 */
	public void eval(Polynomial p, double x){
		p.evaluate(x).getTerms()[0].getTermDouble();
	}
	/* addXsquared
	 *  takes a polynomial and returns void.
	 *  It prints the sum of the polynomial x^2 and the input polynomial,
	 *  and it graphs this sum of polynomials on a PlotFrame.
	 */
	public void addXsqaured(Polynomial p) {
		Polynomial poly = new Polynomial(new double[] {0,0,1});
		p.plus(poly);
		Trail trail = new Trail();
		
	}
}
