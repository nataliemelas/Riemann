package RiemannNMK;

import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class MidpointRule extends Riemann {
	/**
	 * Finds the area of one rectangle. Does this by taking the base and diving it
	 * by two to find the x coordinate of the middle of the subinterval. Then it
	 * takes the y-value of that and multiplies it by the base to find the area of
	 * that rectangle.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one rectangle with height=y value of mid x coordinate
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		// finds the y value of the midpoint of the base
		double ymiddle = poly.evaluate(sleft + ((sright - sleft) / 2)).getTerms()[0].getTermDouble();
		// finds the area of the rectangle with height ymiddle and the base of the
		// subinterval (the space in between sleft and sright)
		return ymiddle * (sright - sleft);
	}

	/**
	 * Plots one rectangular slice on a PlotFrame. This rectangle has the same base
	 * as the slice (given left and right x values). The height of the rectangle is
	 * the y value of the midpoint of the base. The coordinate of the center is
	 * merely (1/2 base, 1/2 height = 1/2 y of mid x).
	 * 
	 * @param pframe
	 *            PlotFrame that the slice will appear on
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
		double midpoint = (sright + sleft) / 2; // creates variable for the midpoint of the base for clarity
		
		// creates variable for y value of the midpoint of the base for clarity creating
		// rectangle with the x values and their corresponding y values
		double yMidpoint = poly.evaluate(midpoint).getTerms()[0].getTermDouble();
		
		// (center x, center y, width, height);
		// (midpoint of base, 1/2 y value of midpoint, base, absolute value of the height);
		DrawableShape rect = DrawableShape.createRectangle(midpoint, yMidpoint / 2, sright - sleft,
				Math.abs(yMidpoint)); //height is absolute value so that it can plot the rectangles when the area is negative
		rect.setMarkerColor(Color.blue, Color.green); // (color border, color interior)
		pframe.addDrawable(rect);
	}
}