package RiemannNMK;

import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class MinRule extends Riemann {

	/**
	 * Finds the area of one rectangle. The height of each rectangle is either the
	 * given polynomial's y value of the left-most or right-most x point of the
	 * slice -- it chooses the smaller of the two. Then, the base is the length
	 * between the left and right x coordinates of the slice.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one rectangle with height=y value of either the left or
	 *         right x coordinate (whichever one is smaller)
	 */

	public double slice(Polynomial poly, double sleft, double sright) {
		// base of the slice
		double base = sright - sleft;
		// names the height of the rectangle to be the y value of the left-most x
		// coordinate
		double yMin = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		// if the y value of the left-most x coordinate is smaller than that of the
		// right-most, it keeps the same height
		// if the right-most x has a smaller y value, it replaces the height to be that
		// value
		if (poly.evaluate(sleft).getTerms()[0].getTermDouble() > poly.evaluate(sright).getTerms()[0].getTermDouble()) {
			yMin = poly.evaluate(sright).getTerms()[0].getTermDouble();
		}
		// returns area (b*h)
		return yMin * base;
	}

	/**
	 * Plots one rectangular slice on a PlotFrame. This rectangle has the same base
	 * as the slice (given left and right x values). The height of each rectangle is
	 * either the given polynomial's y value of the left-most or right-most x point
	 * of the slice -- it chooses the smaller of the two. The coordinate of the
	 * center is (1/2 base, 1/2 height = 1/2 y of smaller x).
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
		double base = sright - sleft;
		// names the height of the rectangle to be the y value of the left-most x
		// coordinate
		double yMin = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		// if the y value of the left-most x coordinate is smaller than that of the
		// right-most, it keeps the same height
		// if the right-most x has a smaller y value, it replaces the height to be that
		// value
		if (poly.evaluate(sleft).getTerms()[0].getTermDouble() > poly.evaluate(sright).getTerms()[0].getTermDouble()) {
			yMin = poly.evaluate(sright).getTerms()[0].getTermDouble();
		}
		// (center x, center y, width, height);
		// creates rectangle with (midpoint of base, 1/2 of height, base, and absolute
		// value of the height)
		// height is absolute value so that it can plot the rectangles when the area is
		// negative
		DrawableShape rect = DrawableShape.createRectangle((sright + sleft) / 2, yMin / 2, base, Math.abs(yMin));
		rect.setMarkerColor(Color.green, Color.blue); // (color border, color interior)
		pframe.addDrawable(rect);
	}
}
