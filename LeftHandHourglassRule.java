package RiemannNMK;

import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;

public class LeftHandHourglassRule extends Riemann {

	/**
	 * Finds the area of one hourglass. An "hourglass" is two triangles on top of
	 * each other: each triangle's base is on the base of the rectangle that would
	 * make up the left-hand rule, and they share a point at the center of that
	 * rectangle. The area is 1/2 base * height for the triangles. The height is 1/2
	 * of the y value of the left x point, and the base is just the length between
	 * the left and right points given. Since it is two triangles, the area =
	 * 2*(1/2*base*height) = base*height
	 * 
	 * @param poly
	 *            the polynomial given by the user
	 * @param sleft
	 *            the left-most x point (lower boundary of slice)
	 * @param sright
	 *            the right-most x point (upper boundary of slice)
	 * @return the area of one hourglass with height=y value of left x coordinate
	 */
	public double slice(Polynomial poly, double sleft, double sright) {
		// creates a double "base" which is the base of the slice
		double base = sright - sleft;
		// sets the height of the slice to the y value of the left-most x point because
		// this is the LEFT HAND hourglass rule
		double height = poly.evaluate(sleft).getTerms()[0].getTermDouble() / 2;
		// we are looking for the area of the two triangles, so we take 2 times the area
		// formula for a triangle (1/2*b*h)
		return base * height;
	}

	/**
	 * Plots one slice (one hourglass -- two triangles on top of each other with
	 * each triangle's base as the base of the rectangle that would make up the
	 * left-hand rule and the two triangles share a point at the center of that
	 * would-be rectangle.) It starts a trail and adds points in order of how they
	 * are connected. It goes from the bottle left corner to the center of the
	 * would-be rectangle (the top vertex of the bottom triangle). Then, it travels
	 * back down to the bottom right and across y=0 to the left, creating the first
	 * triangle. Then, it forms the top triangle by connecting the bottom left
	 * corner (where the trail left off) to the top right vertex (which is found by
	 * taking the right x coordinate of the slice and the y value of the left-most x
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
		pframe.setVisible(true); // we can can see the coordinate plane
		pframe.setSquareAspect(true); // makes the coordinate plane a square
		pframe.setDefaultCloseOperation(3); // allows you to close out of the pop-up graphs easily

		// creates base variable as the base of the slice like in Slice function above
		double base = sright - sleft;
		// also like in Slice function, this creates the height = the y value of the
		// left-most x point
		double height = poly.evaluate(sleft).getTerms()[0].getTermDouble();

		Trail tri = new Trail(); // creates trail which is going to connect the points of the hourglass
		tri.addPoint(sleft, 0); // bottom left vertex of bottom triangle
		tri.addPoint(sleft + (.5 * base), (0.5 * height)); // top middle vertex of bottom triangle
		tri.addPoint(sright, 0); // bottom right vertex of bottom triangle
		tri.addPoint(sleft, 0); // bottom left vertex of bottom triangle
		tri.addPoint(sright, height); // top right vertex of top triangle
		tri.addPoint(sleft, height); // top left vertex of top triangle
		tri.addPoint(sleft + (.5 * base), (0.5 * height)); // bottom middle vertex of top triangle
		tri.color = Color.blue; // colors the trail blue
		pframe.addDrawable(tri); // adds trail to the graph
	}
}
