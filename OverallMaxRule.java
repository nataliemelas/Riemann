package RiemannNMK;

import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class OverallMaxRule extends Riemann {

	/**
	 * Finds the area of one rectangle. The height of each rectangle is the greatest
	 * y value of any x-coordinate within the slice. Then, the base is the length
	 * between the left and right x coordinates of the slice.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one rectangle with height= the greatest y value of any
	 *         x-coordinate within the slice
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		// base of slice
		double base = sright - sleft;
		// names the height of the rectangle to be the y value of the right-most x
		// coordinate
		double yMax = poly.evaluate(sright).getTerms()[0].getTermDouble();
		// if the y value of the given x coordinate is greater than that of the
		// greatest it has encountered so far, it replaces the height to be that
		// value
		// if the greatest y so far is greater than that value, keeps the same height
		for (double i = sright; i >= sleft; i = i - .001) {
			if (poly.evaluate(i).getTerms()[0].getTermDouble() > yMax) {
				yMax = poly.evaluate(i).getTerms()[0].getTermDouble();
			}
		}
		// finds area (h*b)
		return yMax * base;
	}

	/**
	 * Plots one rectangular slice on a PlotFrame. This rectangle has the same base
	 * as the slice (given left and right x values). The height of each rectangle is
	 * the greatest y value of any x-coordinate within the slice. The coordinate of
	 * the center is (1/2 base, 1/2 height = 1/2 of greatest y).
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
		// names the height of the rectangle to be the y value of the right-most x
		// coordinate
		double yMax = poly.evaluate(sright).getTerms()[0].getTermDouble();
		// if the y value of the given x coordinate is greater than that of the
		// greatest it has encountered so far, it replaces the height to be that
		// value
		// if the greatest y so far is greater than that value, keeps the same height
		for (double i = sright; i >= sleft; i = i - .001) {
			if (poly.evaluate(i).getTerms()[0].getTermDouble() > yMax) {
				yMax = poly.evaluate(i).getTerms()[0].getTermDouble();
			}
		}
		// creates rectangle with (center x = midpoint of base, center y = 1/2 of
		// height, width = base, and height = absolute
		// value of the height)
		// height is absolute value so that it can plot the rectangles when the area is
		// negative
		DrawableShape rect = DrawableShape.createRectangle((sright + sleft) / 2, yMax / 2, base, Math.abs(yMax));
		// rect.setMarkerColor(Color.green, Color.blue);
		// (color border, color interior)
		pframe.addDrawable(rect);
	}
}
