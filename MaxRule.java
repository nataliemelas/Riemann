package RiemannNMK;

import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class MaxRule extends Riemann {

	/**
	 * Finds the area of one rectangle. The height of each rectangle is either the
	 * given polynomial's y value of the left-most or right-most x point of the
	 * slice -- it chooses the greater of the two. Then, the base is the length
	 * between the left and right x coordinates of the slice.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one rectangle with height=y value of either the left or
	 *         right x coordinate (whichever is greater)
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		// base of slice
		double base = sright - sleft;
		// names the height of the rectangle to be the y value of the left-most x
		// coordinate
		double yMax = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		// if the y value of the left-most x coordinate is greater than that of the
		// right-most, it keeps the same height
		// if the right-most x has a greater y value, it replaces the height to be that
		// value
		if (poly.evaluate(sleft).getTerms()[0].getTermDouble() < poly.evaluate(sright).getTerms()[0].getTermDouble()) {
			yMax = poly.evaluate(sright).getTerms()[0].getTermDouble();
		}
		// returns area (h*b)
		return yMax * base;
	}

	/**
	 * Plots one rectangular slice on a PlotFrame. This rectangle has the same base
	 * as the slice (given left and right x values). The height of each rectangle is either the
	 * given polynomial's y value of the left-most or right-most x point of the
	 * slice -- it chooses the greater of the two. The coordinate of the
	 * center is (1/2 base, 1/2 height = 1/2 y of greater x).
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
		// base of slice
		double base = sright - sleft;
		// names the height of the rectangle to be the y value of the left-most x
		// coordinate
		double yMax = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		// if the y value of the left-most x coordinate is greater than that of the
		// right-most, it keeps the same height
		// if the right-most x has a greater y value, it replaces the height to be that
		// value
		if (poly.evaluate(sleft).getTerms()[0].getTermDouble() < poly.evaluate(sright).getTerms()[0].getTermDouble()) {
			yMax = poly.evaluate(sright).getTerms()[0].getTermDouble();
		}
		// creates rectangle with (midpoint of base, 1/2 of height, base, and absolute
		// value of the height)
		// (center x, center y, width, height);
		// height is absolute value so that it can plot the rectangles when the area is
		// negative
		DrawableShape rect = DrawableShape.createRectangle((sright + sleft) / 2, yMax / 2, base, Math.abs(yMax));
		rect.setMarkerColor(Color.green, Color.blue); // (color border, color interior)
		pframe.addDrawable(rect);
	}
}
