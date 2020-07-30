package RiemannNMK;

import polyfun.Polynomial;
import org.opensourcephysics.frames.*;

public class TestRSPlot {
	public static void main(String[] args) {
		LeftHandRule LHR = new LeftHandRule(); // LeftHandRule extends Riemann
		RightHandRule RHR = new RightHandRule(); // RightHandRule extends Riemann
		TrapezoidRule TR = new TrapezoidRule(); // TrapezoidRule extends Riemann
		MidpointRule MR = new MidpointRule(); // MidpointRule extends Riemann
		OverallMinRule OMiR = new OverallMinRule(); // OverallMinRule extends Riemann
		MaxRule MaR = new MaxRule();// OverallMaxRule extends Riemann
		OverallMaxRule OMaR = new OverallMaxRule(); // OverallMinRule extends Riemann
		SimpsonsRule SR = new SimpsonsRule(); //Random extends Riemann
		LeftHandHourglassRule LHGR = new LeftHandHourglassRule();

		Polynomial p = new Polynomial(new double[] { 3, -6, 3 }); // p=3x^2-6x+3
		Polynomial hia = new Polynomial(new double[] { 3, -1, .2, .05}); // p=2x^3 + 3x^2-6x+3
		Polynomial z = new Polynomial(new double[] {0, 0, -1});
		Polynomial hi = new Polynomial(new double[] { 3, -1, .2, -1}); // p=2x^3 + 3x^2-6x+3

		PlotFrame f = new PlotFrame("x", "y", "Left Hand Riemann Sum Graph");
		f.setPreferredMinMaxX(-1, 3);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);

		PlotFrame g = new PlotFrame("x", "y", "Right Hand Riemann Sum Graph");
		g.setPreferredMinMaxX(-1, 3);
		g.setDefaultCloseOperation(3);
		g.setVisible(true);
		
		PlotFrame h = new PlotFrame("x", "y", "Trapezoid Riemann Sum Graph");
		h.setPreferredMinMaxX(-1, 3);
		h.setDefaultCloseOperation(3);
		h.setVisible(true);
		
		PlotFrame j = new PlotFrame("x", "y", "Midpoint Riemann Sum Graph");
		j.setPreferredMinMaxX(-1, 3);
		j.setDefaultCloseOperation(3);
		j.setVisible(true);
		
		PlotFrame k = new PlotFrame("x", "y", "Min Overall Riemann Sum Graph");
		k.setPreferredMinMaxX(-1, 3);
		k.setDefaultCloseOperation(3);
		k.setVisible(true);
		
		PlotFrame i = new PlotFrame("x", "y", "Max Riemann Sum Graph");
		i.setPreferredMinMaxX(-1, 3);
		i.setDefaultCloseOperation(3);
		i.setVisible(true);
		
		PlotFrame m = new PlotFrame("x", "y", "Mid Hourglass Rule Graph");
		m.setPreferredMinMaxX(-1, 3);
		m.setDefaultCloseOperation(3);
		m.setVisible(true);
		
		PlotFrame n = new PlotFrame("x", "y", "Overall Max Riemann Sum Graph");
		n.setPreferredMinMaxX(-1, 3);
		n.setDefaultCloseOperation(3);
		n.setVisible(true);
		
		PlotFrame r = new PlotFrame("x", "y", "Simpson's Riemann Sum Graph");
		r.setPreferredMinMaxX(-1, 3);
		r.setDefaultCloseOperation(3);
		r.setVisible(true);

		double xMin = -1;
		double xMax = 2;
		
		LHR.rsPlot(f, p, 1, 0.01, xMin, xMax, 10); // the left hand rule from x=0 to x=2, with 10 rectangles

		RHR.rsPlot(g, p, 1, 0.01,xMin, xMax, 10); // the right hand rule from x=0 to x=2, with 10 rectangles
		
		TR.rsPlot(h, p, 1, 0.01, xMin, xMax, 10); // the trapezoid hand rule from x=0 to x=2, with 10 rectangles

		MR.rsPlot(j, p, 1, 0.01, xMin, xMax, 10); // the midpoint rule from x=0 to x=2, with 10 rectangles
		
		OMiR.rsPlot(k, z, 1, 0.01, 1, 3, 1); // the overall min rule from x=0 to x=2, with 10 rectangles
		
		MaR.rsPlot(i, p, 1, 0.01, xMin, xMax, 10); // the max rule from x=0 to x=2, with 10 rectangles
		
		LHGR.rsPlot(m, p, 1, 0.01, 0, 2, 10); // the min rule from x=0 to x=2, with 10 rectangles
		
		OMaR.rsPlot(n, p, 1, 0.01, xMin, xMax, 10); // the overall max rule from x=0 to x=2, with 10 rectangles
		
		SR.rsPlot(r, hi, 1, 0.01, -1, 1, 100); // the overall max rule from x=0 to x=2, with 10 rectangles
	}
}
