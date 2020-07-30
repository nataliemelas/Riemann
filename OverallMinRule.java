package RiemannNMK;

import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class OverallMinRule extends Riemann {

	/**
	 * Finds the area of one rectangle. The height of each rectangle is the smallest
	 * y value of any x-coordinate within the slice. Then, the base is the length
	 * between the left and right x coordinates of the slice.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one rectangle with height = the smallest y value of any
	 *         x-coordinate within the slice
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		// names the height of the rectangle to be the y value of the left-most x
		// coordinate
		double yMin = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		// if the y value of the given x coordinate is less than that of the
		// smallest it has encountered so far, it replaces the height to be that
		// value
		// if the smallest y so far is smaller than that value, keeps the same height
		for (double i = sleft; i <= sright; i = i + .0001) {
			if (poly.evaluate(i).getTerms()[0].getTermDouble() < yMin) {
				yMin = poly.evaluate(i).getTerms()[0].getTermDouble();
				// System.out.println(yMin);
			}
		}
		// returns area
		return yMin * (sright - sleft);
	}

	/**
	 * Plots one rectangular slice on a PlotFrame. This rectangle has the same base
	 * as the slice (given left and right x values). The height of each rectangle is
	 * the smallest y value of any x-coordinate within the slice. The coordinate of
	 * the center is (1/2 base, 1/2 height = 1/2 of smallest y).
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
		// names the height of the rectangle to be the y value of the left-most x
		// coordinate
		double yMin = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		// if the y value of the given x coordinate is less than that of the
		// smallest it has encountered so far, it replaces the height to be that
		// value
		// if the smallest y so far is smaller than that value, keeps the same height
		for (double i = sleft; i <= sright; i = i + .0001) {
			if (poly.evaluate(i).getTerms()[0].getTermDouble() < yMin) {
				yMin = poly.evaluate(i).getTerms()[0].getTermDouble();
				// System.out.println(yMin);
			}
		}
		// creates rectangle with (midpoint of base, 1/2 of height, base, and absolute
		// value of the height)
		// (center x, center y, width, height);
		// height is absolute value so that it can plot the rectangles when the area is
		// negative
		DrawableShape rect = DrawableShape.createRectangle((sright + sleft) / 2, yMin / 2, sright - sleft,
				Math.abs(yMin));
		// (center x, center y, width, height);
		// rect.setMarkerColor(Color.green, Color.blue); // (color border, color
		// interior)
		pframe.addDrawable(rect);
	}
}
