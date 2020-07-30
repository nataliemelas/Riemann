package RiemannNMK;

import polyfun.Polynomial;

public class TestRS {
	public static void main(String[] args) {
		LeftHandRule LHR = new LeftHandRule(); // LeftHandRule extends Riemann
		RightHandRule RHR = new RightHandRule(); // RightHandRule extends Riemann
		TrapezoidRule TR = new TrapezoidRule(); // TrapezoidRule extends Riemann
		OverallMinRule OMiR = new OverallMinRule(); // TrapezoidRule extends Riemann
		SimpsonsRule SR = new SimpsonsRule();
		SimpsonsRuleWayTwo SRWT = new SimpsonsRuleWayTwo();

		Polynomial g = new Polynomial(new double[] { 0, 0, 3 }); // p=3x^2
		Polynomial s = new Polynomial(new double[] { -6, 2, 1, 0, -7 });
		Polynomial t = new Polynomial(new double[] { 0, 1, 1 });
		Polynomial p = new Polynomial(new double[] { 3, -1, .2, .05 }); // p=2x^3 + 3x^2-6x+3

		System.out.println(LHR.rs(g, 0.0, 1.0, 2000) + "\n"); // the approximate area under 3x^2, from 0 to 1, using the
																// left hand rule

		System.out.println(RHR.rs(g, 0.0, 1.0, 2000) + "\n"); // the approximate area under 3x^2, from 0 to 1, using the
																// right hand rule

		System.out.println(TR.rs(s, 0.0, 1.0, 2000) + "\n"); // the approximate area under 3x^2, from 0 to 1, using the
																// trapezoid rule

		System.out.println("min rule: " + OMiR.rs(t, -2.0, 2.0, 8) + "\n"); // the approximate area under 3x^2, from 0
																			// to 1, using the
		// midpoint rule

		System.out.println("first way: " + SR.rs(t, -5, 0, 18) + "\n");
		System.out.println("second way: " + SRWT.rs(t, -5, 0, 18) + "\n");

		double xMin = -11.0;
		double xMax = 5.0;

		System.out.println(LHR.rs(p, xMin, xMax, 10)); // the left hand rule from x=0 to x=2, with 10 rectangles

		System.out.println(RHR.rs(p, xMin, xMax, 10)); // the right hand rule from x=0 to x=2, with 10 rectangles

		System.out.println(TR.rs(p, xMin, xMax, 10)); // the trapezoid hand rule from x=0 to x=2, with 10 rectangles

		System.out.println(OMiR.rs(p, xMin, xMax, 10)); // the midpoint rule from x=0 to x=2, with 10 rectangles
	}
}
