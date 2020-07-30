package RiemannNMK;

import java.awt.Color;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class MidpointHourglassRule extends Riemann {

	/**
	 * Finds the area of one hourglass. An "hourglass" is two triangles on top of
	 * each other: each triangle's base is on the base of the rectangle that would
	 * make up the midpoint rule, and they share a point at the center of that
	 * rectangle. The area is 1/2 base * height for the triangles. The height is 1/2
	 * of the y value of the mid x point, and the base is just the length between
	 * the left and right points given. Since it is two triangles, the area =
	 * 2*(1/2*base*height) = base*height
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one hourglass with height=y value of mid x coordinate
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		// takes the left x value and puts it into the polynomial in order to get the
		// corresponding y value
		double base = sright - sleft;
		double height = poly.evaluate(sleft + ((sright - sleft) / 2)).getTerms()[0].getTermDouble();
		// we are looking for the area of the rectangle with height yleft and the base
		// of the subinterval, which in this case is the space in between sleft and
		// sright
		return base * height;
	}

	/**
	 * Plots one slice (one hourglass -- two triangles on top of each other with
	 * each triangle's base as the base of the rectangle that would make up the
	 * midpoint rule and the two triangles share a point at the center of that
	 * would-be rectangle.) It starts a trail and adds points in order of how they
	 * are connected. It goes from the bottle left corner to the center of the
	 * would-be rectangle (the top vertex of the bottom triangle). Then, it travels
	 * back down to the bottom right and across y=0 to the left, creating the first
	 * triangle. Then, it forms the top triangle by connecting the bottom left
	 * corner (where the trail left off) to the top right vertex (which is found by
	 * taking the right x coordinate of the slice and the y value of the middle x
	 * coordinate). It then travels across the base of the triangle (slope = 0) to
	 * the left-most x point, and lastly, to the middle coordinate.
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
		// creates variable base
		double base = sright - sleft;
		// creates variable height which is the y value of the midpoint of the base
		double height = poly.evaluate(sleft + (base / 2)).getTerms()[0].getTermDouble();
		// creates a trail and adds the points of the hourglass
		Trail tri = new Trail();
		tri.addPoint(sleft, 0); // bottom left
		tri.addPoint(sleft + (.5 * base), (0.5 * height)); // middle
		tri.addPoint(sright, 0); // bottom right
		tri.addPoint(sleft, 0); // bottom left
		tri.addPoint(sright, height); // top right
		tri.addPoint(sleft, height); // top left
		tri.addPoint(sleft + (.5 * base), (0.5 * height)); // middle
		tri.color = Color.blue;
		pframe.addDrawable(tri);
	}
}
