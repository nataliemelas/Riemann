package RiemannNMK;

import org.omg.Messaging.SyncScopeHelper;

import polyfun.Polynomial;

public class TestSlice {
	public static void main(String[] args) {
		LeftHandRule LHR = new LeftHandRule(); // LeftHandRule extends Riemann
		RightHandRule RHR = new RightHandRule(); // RightHandRule extends Riemann
		TrapezoidRule TR = new TrapezoidRule(); // TrapezoidRule extends Riemann
		MinRule MR = new MinRule(); // MidpointRule extends Riemann
		OverallMinRule OMR = new OverallMinRule(); // OverallMinRule extends Riemann
		SimpsonsRule SR = new SimpsonsRule(); // SimpsonsRule extends Riemann
		
		Polynomial p = new Polynomial(new double[] { 0, 4, 0, 3 }); // p=3x^3+4x
		Polynomial px = new Polynomial(new double[] { 0, 0, 1 }); // p=x^2
		Polynomial ps = new Polynomial(new double[] { 9, 0, -1 }); // p=x^2
		Polynomial s = new Polynomial(new double[] {-6, 2, 1, 0, -7});

		System.out.println(LHR.slice(p, 1, 1.1) + "\n"); // the area of a left hand rule slice of 3x^3+4x, from x=1 to
															// x=1.1

		System.out.println(RHR.slice(p, 1, 1.1) + "\n"); // the area of a rightt hand rule slice of 3x^3+4x, from x=1 to
															// x=1.1

		System.out.println(TR.slice(p, 1, 1.1) + "\n"); // the area of a trapezoid rule slice of 3x^3+4x, from x=1 to
														// x=1.1

		System.out.println(MR.slice(px, 2, 4) + "\n"); // the area of a midpoint rule slice of px=x^2, from x=1 to x=4
		
		System.out.println(OMR.slice(px, 2, 4) + "\n"); // the area of a midpoint rule slice of px=x^2, from x=1 to x=4
	
		System.out.println("here: " + SR.slice(px, 0, 4) + "\n");
	}
}
