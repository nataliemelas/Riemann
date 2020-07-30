package RiemannNMK;

import java.awt.Color;
import java.util.Random;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class RandomRule extends Riemann {

	/**
	 * Finds the area of one rectangle using a random height along the slice and the
	 * base of the slice.
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one rectangle with height= y value of a random x
	 *         coordinate within the slice
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		// defines variable as the base of the slice
		double base = sright - sleft;
		// creates a new random generator
		Random random = new Random();
		// the random generator takes a random decimal from 0-1
		// it then multiplies the base by that fraction and then adds it to the
		// left-most x point to get a random x-coordinate
		double randX = sleft + random.nextDouble() * base;
		// then, it gets the y-value of that random x-coordinate
		double height = poly.evaluate(randX).getTerms()[0].getTermDouble();
		// returns area
		return height * base;
	}

	/**
	 * Plots one rectangular slice on a PlotFrame. This rectangle has the same base
	 * as the slice (given left and right x values). The height of the rectangle is
	 * the y value of a random x-coordinate on the base. The coordinate of the
	 * center is (1/2 base, 1/2 height = 1/2 y of the random x).
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
		// creates a new random generator
		Random random = new Random();
		// the random generator takes a random decimal from 0-1
		// it then multiplies the base by that fraction and then adds it to the
		// left-most x point to get a random x-coordinate
		double randX = sleft + random.nextDouble() * base;
		// then, it gets the y-value of that random x-coordinate
		double height = poly.evaluate(randX).getTerms()[0].getTermDouble();
		// (center x, center y, width, height);
		// (midpoint of base, 1/2 height, base, absolute value of height)
		DrawableShape rect = DrawableShape.createRectangle((sright + sleft) / 2, height / 2, base, Math.abs(height));
		// rect.setMarkerColor(Color.green, Color.blue);
		// (color border, color interior)
		pframe.addDrawable(rect);
	}
}
