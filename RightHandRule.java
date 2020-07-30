package RiemannNMK;

import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;

public class RightHandRule extends Riemann {
	/**
	 * Finds the area of one rectangle. Does this by taking the right-most point of
	 * the subinterval and takes the y-value of that to find the area of that
	 * rectangle.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one rectangle with height=y value of right x coordinate
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		//base of slice
		double base = sright-sleft;
		// takes the right x value and gets its corresponding y value from the given
		// polynomial
		double yright = poly.evaluate(sright).getTerms()[0].getTermDouble();
		// gets the area of the rectangle with height yright and the base of the
		// subinterval (measurement from sleft to sright)
		return yright * base;
	}

	/**
	 * Plots one rectangular slice on a PlotFrame. This rectangle has the same base
	 * as the slice (given left and right x values). The height of the rectangle is
	 * the y value of the right-most x value of the base. The coordinate of the
	 * center is (1/2 base, 1/2 height = 1/2 y of right-most x).
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
		// creates rectangle in format of (center x, center y, width, height)
		// this code inputs (midpoint of base, midpoint of height of right x, base, y
		// value of right-most x);
		// height is absolute value so that it can plot the rectangles when the area is
		// negative
		DrawableShape rect = DrawableShape.createRectangle((sright + sleft) / 2,
				(poly.evaluate(sright).getTerms()[0].getTermDouble()) / 2, sright - sleft,
				Math.abs(poly.evaluate(sright).getTerms()[0].getTermDouble()));
		// rect.setMarkerColor(Color.green, Color.blue); // (color border, color
		// interior)
		pframe.addDrawable(rect);
	}
}
