package RiemannNMK;

import java.awt.Color;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.*;

public abstract class Riemann {
	int SimpLoops = -10; // initializes this variable to -10 to allow for the class to determine when it
							// is calculating a singular slice versus more slices
	double sum = 0; // the sum of all of the y values in the Simpsons rule using the particular
					// formula
	double s = 0; // makes for-loop variable global
	double intervals; // allows SimpsonsRule to determine how many subintervals there are

	/**
	 * Calculates a Riemann sum by adding up the slices (using a particular rule) to
	 * get the full area.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param left
	 *            the left-most x point (lower boundary of area)
	 * @param right
	 *            the right-most x point (upper boundary of area)
	 * @param subintervals
	 *            the number of sections into which the area is divided (all with a
	 *            uniform base)
	 * @return area of the given domain under the curve
	 */
	public double rs(Polynomial poly, double left, double right, int subintervals) {
		//I used two different methods to calculate the area of the Simpsons Rule
		//One method, "SimpsonsRule" required alterations to this rs function, which
		//is why it is more complicated
		
		// "Reinitializes" the global variables used in SimpsonsRule because they need
		// to be at 0 when calculating the accumulation function (when rs is on a loop)
		sum = 0;
		s = 0;
		SimpLoops = 0;
		intervals = subintervals; // makes global variable take specific user value
		double area = 0; // create area variable and set to zero
		double base = (right - left) / subintervals; // gets the base of each slice

		// loops for one more than the given amount of subintervals
		for (s = 0; s < intervals + 1; s = s + 1) {
			
			// this if statement is used by every class except for SimpsonsRule
			// it calculates the area using the given amount of subintervals
			if (s < intervals) {
				// adds the area of that slice to the overall area of the section
				area = area + slice(poly, left, left + base);
				// takes the next point on the x axis
				left = left + base;
				// allows for the SimpsonsRule class to determine if it is an even or add
				// numbered slice
				SimpLoops = SimpLoops + 1;
			}
			
			// Because simpsons uses the y values of the x-coordinates, it needs one more
			// y value than the number of subintervals (ex. f(4) = f(0) + 4*f(1) + 2*f(2) +
			// 4*f(3) + f(4))
			// This takes the last value (following the example, f(4)) and adds it to the
			// sum in order to calculate the area using the simpsons rule
			if (s == intervals && SimpLoops == -4) {
				// tells the class it is the last y value
				SimpLoops = -1;
				// adds the next value to the sum and makes SimpLoops = -2
				area = area + slice(poly, left, left + base);
				// finally, puts all of the y-values calculated ("sum") into the simpsons
				// formula
				if (SimpLoops == -2) {
					area = (base / 3) * sum;
				}
			}
		}
		return area; // returns the area over the given domain
	}

	/**
	 * Graphs the polynomial whose Riemann sum is being calculated and also draws
	 * the corresponding shapes whose areas are summed in the calculation of the
	 * Riemann sum.
	 * 
	 * @param pframe
	 *            The specific PlotFrame on which the slice will be graphed
	 * @param poly
	 *            the polynomial given by the user
	 * @param index
	 *            groups functions by number, so each one with the same number can,
	 *            for example, have the same color
	 * @param precision
	 *            the minimum difference between the x-coordinates of a pair of
	 *            plotted points
	 * @param left
	 *            the left-most x point (lower boundary of area)
	 * @param right
	 *            the right-most x point (upper boundary of area)
	 * @param subintervals
	 *            the number of sections into which the area is divided (all with a
	 *            uniform base)
	 */
	public void rsPlot(PlotFrame pframe, Polynomial poly, int index, double precision, double left, double right,
			int subintervals) {
		// loops to plot of "poly" each point "precision" units away
		for (double i = left; i <= right; i = i + precision) {
			pframe.append(index, i, poly.evaluate(i).getTerms()[0].getTermDouble());
		}
		// colors the graph of the poly
		pframe.setMarkerColor(index, Color.red);
		// gets the base of each slice
		double base = (right - left) / subintervals;

		// each slice is added to the plot frame
		// you add the amount of slices determined by the number of subintervals given
		for (double j = 0; j < subintervals; j = j + 1) {
			// plots each slice
			slicePlot(pframe, poly, left, left + base);
			// moves on the x axis by the length of the base to change the left-x and
			// right-x coordinates for the next slice
			left = left + base;
		}
	}

	/**
	 * Graphs an accumulation function. This is a more advanced topic than basic
	 * Riemann sums, and so we'll come back to it later in the assignment.
	 * 
	 * @param pframe
	 *            The specific PlotFrame on which the slice will be graphed
	 * @param poly
	 *            the polynomial given by the user
	 * @param index
	 *            groups functions by number, so each one with the same number can,
	 *            for example, have the same color
	 * @param precision
	 *            the minimum difference between the x-coordinates of a pair of
	 *            plotted points
	 * @param base
	 *            the starting x coordinate of the area calculated to make the
	 *            accumulation function
	 */
	public void rsAcc(PlotFrame pframe, Polynomial poly, int index, double precision, double base) {
		// creates new trail
		Trail function = new Trail();
		// adds a point to the trail which is the x point and the area under the curve
		for (double i = -25; i < 25; i = i + precision) {
			// the accumulation function gives you the area under the curve for a given x,
			// so that is your y-coordinate
			function.addPoint(i, rs(poly, base, i, 100));
		}
		pframe.addDrawable(function);
	}

	/**
	 * Uses a particular Riemann sum rule to calculate an approximation of the area
	 * under the graph of the given polynomial and over one interval on the x-axis.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one slice with height depending on rule given
	 */
	public abstract double slice(Polynomial poly, double sleft, double sright);

	/**
	 * Uses a particular Riemann sum rule to draw the region whose area is
	 * calculated by slice.
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
	public abstract void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright);
}
