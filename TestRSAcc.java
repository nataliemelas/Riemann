package RiemannNMK;

import polyfun.Polynomial;
import org.opensourcephysics.frames.*;

public class TestRSAcc {
	public static void main(String[] args) {
		LeftHandRule LHR = new LeftHandRule(); // LeftHandRule extends Riemann
		SimpsonsRule SR = new SimpsonsRule(); // SimpsonsRule extends Riemann
		SimpsonsRuleWayTwo SRWT = new SimpsonsRuleWayTwo(); // SimpsonsRuleWayTwo extends Riemann
		
		Polynomial p = new Polynomial(new double[] { 0, 1, 1 }); // p=x^2+x

		PlotFrame f = new PlotFrame("x", "y", "Left Hand Rule Accumulation Function Graph");
		f.setPreferredMinMaxX(-3, 3);
		f.setDefaultCloseOperation(3);
		//f.setVisible(true);

		PlotFrame g = new PlotFrame("x", "y", "Simpsons Way Two Accumulation Function Graph");
		g.setPreferredMinMaxX(-3, 3);
		g.setDefaultCloseOperation(3);
		g.setVisible(true);

		PlotFrame i = new PlotFrame("x", "y", "Simpsons Rule Accumulation Function Graph");
		i.setPreferredMinMaxX(-3, 3);
		i.setDefaultCloseOperation(3);
		i.setVisible(true);

		LHR.rsAcc(f, p, 2, .01, -1.0); // plots the left hand rule acccumulation function of x^2+x, with base x=-1;

		SRWT.rsAcc(g, p, 2, .01, -1.0); // plots the Simpsons Rule Way Two acccumulation function of x^2+x, with base x=-1;

		SR.rsAcc(i, p, 1, .01, -1.0); // plots the Simpsons rule acccumulation function of x^2+x, with base x=-1;
	}
}