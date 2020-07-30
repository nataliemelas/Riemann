package RiemannNMK;

import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class SimpsonsRuleWayTwo extends Riemann {
	/**
	 * This is another way of finding the area using the Simpson's rule. I created
	 * both because when adding this one up in rs, it does not give the same exact
	 * value as SimpsonsRule (though it is only off by very small decimals), which
	 * gets the same value as different online calculators that I check. This method
	 * finds the area of one slice. According to the simpson's rule, the area of one
	 * slice is (2*area using midpoint rule + area using trapezoid rule)/3.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one parabolic slice
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		// creates variable with midpoint area
		double midArea = poly.evaluate(sleft + ((sright - sleft) / 2)).getTerms()[0].getTermDouble() * (sright - sleft);
		// creates variable to store trapezoid area
		double trapArea = (sright - sleft) * (poly.evaluate(sleft).getTerms()[0].getTermDouble()
				+ poly.evaluate(sright).getTerms()[0].getTermDouble()) / 2;
		// finds area
		return (2 * midArea + trapArea) / 3;
	}

	/**
	 * Draws the region whose area is calculated by slice
	 * 
	 * @param pframe
	 *            The specific PlotFrame on which the slice will be graphed
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 */
	public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
		pframe.setVisible(true); // can see it
		pframe.setSquareAspect(true); // making graph a square
		pframe.setDefaultCloseOperation(3); // allowing it to close easily
		double HalfBase = (sright - sleft) / 2;

		// this section solves a system of equations involving three linear equations
		// the three linear equations are in the form y=ax^2 +bx + c
		// however, the x's and y's are plugged in, so the equations become linear
		// then, we solve the equations by finding the determinant of four 3x3 matrices
		// this is called Cramer's Method.
		double x0 = sleft; // first x value
		double x1 = sleft + HalfBase; // second x value
		double x2 = sright; // third x value
		double y0 = poly.evaluate(x0).getTerms()[0].getTermDouble(); // y value of first x
		double y1 = poly.evaluate(x1).getTerms()[0].getTermDouble();// y value of second x
		double y2 = poly.evaluate(x2).getTerms()[0].getTermDouble();// y value of third x

		// simpson's rule uses three equations with the x and y's as the points you
		// have, so the equations are (and each row of the matrix corresponds to a
		// different equation):
		// row 1: y0=a(x0^2) +b(x0) + c
		// row 2: y1=a(x1^2) +b(x1) + c
		// row 3: y2=a(x2^2) +b(x2) + c

		// this matrix has the a values, b values, and c values by columns going left to
		// right
		double RegMatrix = (x0 * x0) * (x1 - x2) - x0 * (x1 * x1 + -x2 * x2) + (x1 * x1 * x2 - x2 * x2 * x1);
		// this matrix has the equation values, b values, and c values by columns going
		// left to right
		double AMatrix = y0 * (x1 - x2) - x0 * (y1 + -y2) + (y1 * x2 - y2 * x1);
		// this matrix has the a values, equation values, and c values by columns going
		// left to right
		double BMatrix = (x0 * x0) * (y1 - y2) - y0 * (x1 * x1 + -x2 * x2) + (x1 * x1 * y2 - x2 * x2 * y1);
		// this matrix has the a values, b values, and equation values by columns going
		// left to right
		double CMatrix = (x0 * x0) * (x1 * y2 - x2 * y1) - x0 * (x1 * x1 * y2 - x2 * x2 * y1)
				+ y0 * (x1 * x1 * x2 - x2 * x2 * x1);
		// part of Cramer's Method says that the values of the three unknowns are the
		// determinants of the matrix without their coefficients divided by the
		// determinant of the regular matrix (only the coefficients)
		double a = AMatrix / RegMatrix;
		double b = BMatrix / RegMatrix;
		double c = CMatrix / RegMatrix;

		Polynomial parabola = new Polynomial(new double[] { c, b, a }); // p=x^2

		// graphs a lot of small rectangles to fill in the area under the parabola just
		// found
		// technically, this area is not equal to the area found in the simpson's rule
		// however, the degree of difference is so small that it does not detract enough
		// from its visual benefit for it to not be worth having
		for (double j = x0 + .001; j < x2; j = j + .001) { // takes slice from left x to right x of that slice
			pframe.append(2, j, parabola.evaluate(j).getTerms()[0].getTermDouble());
			DrawableShape rect = DrawableShape.createRectangle(j - .0005,
					poly.evaluate(j - .0005).getTerms()[0].getTermDouble() / 2, .001,
					Math.abs(poly.evaluate(j - .0005).getTerms()[0].getTermDouble()));
			// (center x, center y, width, height);
			pframe.addDrawable(rect);
		}
		pframe.setMarkerColor(2, Color.black);

	}
}
