package RiemannNMK;

import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;

public class LeftHandRule extends Riemann {
	/**
	 * Finds the area of one rectangle. The height of each rectangle is the given
	 * polynomial's y value of the left-most x point of the slice. The base is the
	 * length between the left and right x coordinates of the slice.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one rectangle with height=y value of left x coordinate
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		// takes the left x value and puts it into the polynomial in order to get the
		// corresponding y value
		double yleft = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		// we are looking for the area of the rectangle with height yleft and the base
		// of the subinterval, which in this case is the space in-between sleft and
		// sright
		return yleft * (sright - sleft);
	}

	/**
	 * Plots one rectangular slice on a PlotFrame. This rectangle has the same base
	 * as the slice (given left and right x values). The height of the rectangle is
	 * the y value of the left-most x value of the base. The coordinate of the
	 * center is (1/2 base, 1/2 height = 1/2 y of left-most x).
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
		// creating rectangle with the x values and their corresponding y values
		// (center x, center y, width, height);
		// (midpoint of base, 1/2 height of left x value, base, y value of left-most x)
		// height is absolute value so that it can plot the rectangles when the area is
		// negative
		DrawableShape rect = DrawableShape.createRectangle((sright + sleft) / 2,
				poly.evaluate(sleft).getTerms()[0].getTermDouble() / 2, sright - sleft,
				Math.abs(poly.evaluate(sleft).getTerms()[0].getTermDouble()));
		// rect.setMarkerColor(Color.green, Color.blue);
		// (color border, color interior)
		pframe.addDrawable(rect);
	}
}
