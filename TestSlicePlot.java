package RiemannNMK;

import polyfun.Polynomial;
import org.opensourcephysics.frames.*;

public class TestSlicePlot {
	public static void main(String[] args) {
		LeftHandRule LHR = new LeftHandRule(); // LeftHandRule extends Riemann
		RightHandRule RHR = new RightHandRule(); // RightHandRule extends Riemann
		MidpointRule MR = new MidpointRule(); // MidpointRule extends Riemann
		OverallMinRule OMR = new OverallMinRule(); // OverallMinRule extends Riemann
		SimpsonsRule SR = new SimpsonsRule(); // SimpsonsRule extends Riemann
		
		Polynomial p = new Polynomial(new double[] { 0, 4, 0, 3 }); // p=3x^3+4x
		Polynomial ps = new Polynomial(new double[] { 9, 0, -1 }); // p=-x^2 + 9
		Polynomial px = new Polynomial(new double[] { 0, 0, 1 }); // p=-x^2 + 9

		PlotFrame f = new PlotFrame("x", "y", "Left Hand Slice");
		f.setPreferredMinMaxX(.5, 1.5);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);

		PlotFrame g = new PlotFrame("x", "y", "Right Hand Rule Slice");
		g.setPreferredMinMaxX(.5, 1.5);
		g.setDefaultCloseOperation(3);
		g.setVisible(true);
		
		PlotFrame h = new PlotFrame("x", "y", "Midpoint Rule Slice");
		h.setPreferredMinMaxX(.5, 1.5);
		h.setDefaultCloseOperation(3);
		h.setVisible(true);
		
		PlotFrame j = new PlotFrame("x", "y", "Min Rule Slice");
		j.setPreferredMinMaxX(.5, 1.5);
		j.setDefaultCloseOperation(3);
		j.setVisible(true);
		
		PlotFrame s = new PlotFrame("x", "y", "Simpson's Rule Slice");
		s.setPreferredMinMaxX(.5, 1.5);
		s.setDefaultCloseOperation(3);
		s.setVisible(true);

		LHR.slicePlot(f, p, 1, 1.1); // a left hand rule slice of 3x^3+4x, from x=1 to x=1.1

		RHR.slicePlot(g, p, 1, 1.1); // a right hand rule slice of 3x^3+4x, from x=1 to x=1.1
		
		MR.slicePlot(h, p, 1, 1.1); // a midpoint rule slice of 3x^3+4x, from x=1 to x=1.1
		
		OMR.slicePlot(j, p, 1, 1.1); // a min rule slice of 3x^3+4x, from x=1 to x=1.1
		SR.slicePlot(s, px, 0, 4);
	}
}
