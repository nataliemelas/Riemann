package RiemannNMK;

import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;

public class TrapezoidRule extends Riemann {
	/**
	 * Finds the area of one trapezoid. The heights of the trapezoid are the given
	 * polynomial's y value of the left-most and right-most x points of the slice.
	 * The base is the length between the left and right x coordinates of the slice.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one trapezoid with base of slice and heights = y value of
	 *         both left and right x-coordinates
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		//for a trapezoid, the base of the slice is the height of the trapezoid
		double height = sright-sleft;
		// takes the left x value and puts it into the polynomial in order to get the
		// corresponding y value
		double yleft = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		// takes the right x value and puts it into the polynomial in order to get the
		// corresponding y value
		double yright = poly.evaluate(sright).getTerms()[0].getTermDouble();
		// area of a trapezoid = h(base1 + base2)/2
		// we are looking for the area of the trapezoid with base1 = yleft and base2 =
		// yright
		// and the height is the distance between sright and sleft
		return height * (yleft + yright) / 2;
	}

	/**
	 * Plots one trapezoid slice on a PlotFrame. This trapezoid has the same base as
	 * the slice (given left and right x values). The heights of the trapezoid are
	 * the y values of the left-most and right-most x values of the base. This
	 * method creates a trail and plots the four vertices of the trapezoid ((left x,
	 * 0), (right x, 0), (right x, y of right x), and (left x, y of left x)).
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

		double yLeft = poly.evaluate(sleft).getTerms()[0].getTermDouble(); // y value of left x point
		double yRight = poly.evaluate(sright).getTerms()[0].getTermDouble(); // y value of right x point

		Trail trap = new Trail(); // make a trail
		trap.addPoint(sleft, 0); // adding the bottom left vertex
		trap.addPoint(sleft, yLeft); // connects to the y value of that point on the graph
		trap.addPoint(sright, yRight); // connects to the y value of the right-most x point in the slice
		trap.addPoint(sright, 0); // connects to right-most x point in the slice on x-axis
		trap.addPoint(sleft, 0); // completes the trapezoid
		trap.color = Color.blue;
		pframe.addDrawable(trap);
	}
}
