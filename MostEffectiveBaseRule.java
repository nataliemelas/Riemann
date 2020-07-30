package RiemannNMK;

import java.util.Scanner;

import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class MostEffectiveBaseRule {
	public static void main(String[] args) {
		LeftHandRule LHR = new LeftHandRule(); // LeftHandRule extends Riemann
		RightHandRule RHR = new RightHandRule(); // RightHandRule extends Riemann
		TrapezoidRule TR = new TrapezoidRule(); // TrapezoidRule extends Riemann
		MidpointRule MR = new MidpointRule(); // Midpoint Rule extends Riemann
		System.out.println("Welcome to the Most Effective Base Rule Function in this area under a curve calculator!");
		System.out.println("The three base rules are left hand rule, right hand rule, and trapezoid rule.");
		System.out.println(
				"In order to calculate which one is the most effective, we will compare the area cacluated when using each rule to the exact area under that curve.");
		System.out.println(
				"Let's take a simple base function, x^2, and say you wanted to calculate the area under the curve from 0 to 4 with 4 subintervals.");

		Polynomial poly = new Polynomial(new double[] { 0, 0, 1 }); // creates x^2 polynomial
		// the approximate area under x^2, from 0 to 4, using the left hand rule
		System.out.println("The area according to the left hand rule is: " + LHR.rs(poly, 0.0, 4.0, 4));
		// the approximate area under x^2, from 0 to 4, using the right hand rule
		System.out.println("The area according to the right hand rule is: " + RHR.rs(poly, 0.0, 4.0, 4));
		// the approximate area under x^2, from 0 to 4, using the trapezoid rule
		System.out.println("The area according to the trapezoid rule is: " + TR.rs(poly, 0.0, 4.0, 4));
		// finds the actual area from the accumulation function for x^2 (b^3/3 with b=4)
		System.out.println("The actual area is: 21.33333");
		System.out.println("As you can see, the area approximation using the trapezoid rule is the closest.");
		// finds the percent error so the user can more clearly compare the three rules
		System.out.println(
				"Trapezoid rule has a 3.13% error, while Left Hand Rule as an error of -34.5% and Right Hand Rule has one of 40.6%."
						+ "\n");
		System.out.println("You can also see this visually.");
		// plots frame for the left hand rule
		PlotFrame l = new PlotFrame("x", "y", "Left Hand Rule");
		l.setPreferredMinMaxX(-1, 3);
		l.setDefaultCloseOperation(3);
		l.setVisible(true);
		LHR.rsPlot(l, poly, 1, 0.01, 0, 4, 4); // plots the left hand rule from x=0 to x=4, with 4 rectangles

		// plots frame for the right hand rule
		PlotFrame r = new PlotFrame("x", "y", "Right Hand Rule");
		r.setPreferredMinMaxX(-1, 3);
		r.setDefaultCloseOperation(3);
		r.setVisible(true);
		RHR.rsPlot(r, poly, 1, 0.01, 0, 4, 4); // the right hand rule from x=0 to x=4, with 4 rectangles

		// plots frame for the trapezoid rule
		PlotFrame t = new PlotFrame("x", "y", "Trapezoid Rule");
		t.setPreferredMinMaxX(-1, 3);
		t.setDefaultCloseOperation(3);
		t.setVisible(true);
		TR.rsPlot(t, poly, 1, 0.01, 0, 4, 4); // the trapezoid hand rule from x=0 to x=4, with 4 trapezoids

		// plots frame for the actual area -- this is not actually the whole area, but
		// it because there are so many subintervals, it looks like it is covering the
		// whole area, so it is useful when trying to understand the area visually
		PlotFrame a = new PlotFrame("x", "y", "Actual Area");
		a.setPreferredMinMaxX(-1, 3);
		a.setDefaultCloseOperation(3);
		a.setVisible(true);
		MR.rsPlot(a, poly, 1, 0.001, 0, 4, 10000); // the midpoint rule from x=0 to x=4, with 10000 rectangles

		System.out.println("Now, you can put in your polynomial and see the different areas.");
		Scanner scan = new Scanner(System.in);
		String end = "";
		while (end.equals("")) {
			// creating user-made polynomial
			System.out.println("What is the highest degree in your polynomial?");
			if (!scan.hasNextInt()) {
				break;
			}
			int maxDegree = scan.nextInt();
			// makes double array of the specfied length
			double[] coefs = new double[maxDegree + 1];
			System.out
					.println("Please input the coefficient of the term in your polynomial which has the lowest degree");
			if (!scan.hasNextDouble()) {
				break;
			}
			coefs[0] = scan.nextDouble();
			// adds for the next coefficients and adds them to the array
			for (int i = 1; i < maxDegree + 1; i++) {
				System.out.println(
						"Now, please input the coefficient of the term in your polynomial which is one degree higher");
				if (!scan.hasNextDouble()) {
					break;
				}
				coefs[i] = scan.nextDouble();
			}
			// creates the polynomial
			Polynomial p = new Polynomial(coefs);

			// asks for left-most X
			System.out.println("What's the value of the lower boundary of the area you want to calculate?");
			if (!scan.hasNextDouble()) {
				break;
			}
			double leftX = scan.nextDouble();

			// asks for right-most X
			System.out.println("And the value of the upper boundary?");
			if (!scan.hasNextDouble()) {
				break;
			}
			double rightX = scan.nextDouble();

			// gets number of subintervals
			System.out.println("Next, How many sub-intervals would you like?");
			if (!scan.hasNextInt()) {
				break;
			}
			int subintervals = scan.nextInt();
			
			// calculates the actual area by splitting the polynomial up into mini
			// accumulation functions
			double Aarea = 0;
			for (int i = 0; i < coefs.length; i++) {
				Aarea = Aarea + coefs[i] * ((Math.pow(rightX, (i + 1))) / (i + 1))
						- (coefs[i] * ((Math.pow(leftX, (i + 1))) / (i + 1)));
			}
			double LeftArea = LHR.rs(p, leftX, rightX, subintervals);
			double RightArea = RHR.rs(p, leftX, rightX, subintervals);
			double TrapArea = TR.rs(p, leftX, rightX, subintervals);
			
			System.out.println("Here are the values: ");
			System.out.println("Left Hand Rule: " + LeftArea + 
					" which has percent error of: " + ((Aarea - LeftArea))/Aarea  + "%");
			System.out.println("Right Hand Rule: " + RightArea + 
					" which has percent error of: " + ((Aarea - RightArea))/Aarea  + "%");
			System.out.println("Trapezoid Rule: " + TrapArea + 
					" which has percent error of: " + ((Aarea - TrapArea))/Aarea + "%");
			System.out.println("Actual Area: " + Aarea);
			end = "yes";
		}
		if (end.equals("")) {
			System.out.println("Please start again and input the correct doubles, integers, and letters when asked.");
		}
		scan.close();
	}
}
