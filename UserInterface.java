package RiemannNMK;

import java.util.Scanner;

import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;

public class UserInterface {
	public static void main(String[] args) {
		boolean input = true;
		while (input == true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Welcome to your own Area Under a Curve Calculator!");
			System.out.println("Here, you get to input your own polynomial.");
			System.out.println("What is the highest degree in your polynomial?");
			if (!scan.hasNextInt()) {
				break;
			}
			int maxDegree = scan.nextInt();
			double[] coefs = new double[maxDegree + 1];
			System.out.println(
					"Nice one! Please input the coefficient of the term in your polynomial which has the lowest degree");
			if (!scan.hasNextDouble()) {
				break;
			}
			coefs[0] = scan.nextDouble();
			for (int i = 1; i < maxDegree + 1; i++) {
				System.out.println(
						"Now, please input the coefficient of the term in your polynomial which is one degree higher");
				coefs[i] = scan.nextDouble();
				if (!scan.hasNextDouble()) {
					break;
				}
			}
			Polynomial poly = new Polynomial(coefs);
			System.out.println("");
			System.out.println(
					"Great choice of polynomial! When I graph it, how far away do you want each point to be from each other?");
			if (!scan.hasNextDouble()) {
				break;
			}
			double precision = scan.nextDouble();
			System.out.println("What's the value of the lower boundary of the area you want to calculate?");
			if (!scan.hasNextDouble()) {
				break;
			}
			double leftX = scan.nextDouble();
			System.out.println("And the value of the upper boundary?");
			if (!scan.hasNextDouble()) {
				break;
			}
			double rightX = scan.nextDouble();
			System.out.println("Next, How many sub-intervals would you like?");
			if (!scan.hasNextInt()) {
				break;
			}
			int subintervals = scan.nextInt();
			System.out.println();
			System.out.println(
					"And lastly, how would you like to calculate the area? There are your options, please input the names AS WRITTEN (but no parenthesis needed): ");
			System.out.println("Left Hand Hourglass Rule (uses hourglasses with height of left-most x point of slice)");
			System.out.println(
					"Midpoint Hourglass Rule (uses hourglasses with height of midpoint of the base of the slice)");
			System.out
					.println("Right Hand Hourglass Rule (uses hourglasses with height of right-most x point of slice)");
			System.out.println("Left Hand Rule (uses rectangles with height of left-most x point of slice)");
			System.out.println("Midpoint Rule (uses rectangles with height of midpoint of the base of the slice)");
			System.out.println("Right Hand Rule (uses rectangles with height of right-most x point of slice)");
			System.out.println("Overall Max Rule (uses rectangles with height of greatest x point of slice)");
			System.out.println(
					"Maximum Rule (uses rectangles with height as either the left-most x or right-most x value of slice, whichever is greater)");
			System.out.println("Overall Min Rule (uses rectangles with height of smallest x point of slice)");
			System.out.println(
					"Minimum Rule (uses rectangles with height as either the left-most x or right-most x value of slice, whichever is smaller)");
			System.out.println(
					"Random Rule (uses rectangles with a height corresponding to a ramdomly selected x value of the slice)");
			System.out.println(
					"Simpsons Rule (uses three points on the graph to create parabolas for every slice -- for this, you can only input an even number of slices)");
			System.out.println(
					"Simpsons Rule Way Two (uses a combination of the midpoint and trapezoid rules to find the area of parabolas for every slice -- for this, you can only input an even number of slices)");
			System.out.println(
					"Trapezoid Rule (uses trapezoids with the base equal to the subinterval and the two heights as y-values of the left-most and right-most x values of each slice)");
			System.out.println(" ");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please input the rules you would like to use (ex. 'Midpoint rule'): ");
			if (!scanner.hasNextLine()) {
				break;
			}
			String rules = scanner.nextLine().toLowerCase();
			if (rules.contains("left hand hourglass")) {
				LeftHandHourglassRule LHHR = new LeftHandHourglassRule(); // LeftHandRule extends Riemann
				PlotFrame a = new PlotFrame("x", "y", "Left Hand Hourglass Rule");
				a.setDefaultCloseOperation(3);
				a.setVisible(true);
				LHHR.rsPlot(a, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Left Hand Hourglass Rule: " + LHHR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("midpoint hourglass")) {
				MidpointHourglassRule MHR = new MidpointHourglassRule(); // LeftHandRule extends Riemann
				PlotFrame b = new PlotFrame("x", "y", "Midpoint Hourglass Rule");
				b.setDefaultCloseOperation(3);
				b.setVisible(true);
				MHR.rsPlot(b, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Midpoint Hourglass Rule: " + MHR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("right hand hourglass")) {
				RightHandHourglassRule RHHR = new RightHandHourglassRule(); // RightHandHourglassRule extends Riemann
				PlotFrame c = new PlotFrame("x", "y", "Right Hand Hourglass Rule");
				c.setDefaultCloseOperation(3);
				c.setVisible(true);
				RHHR.rsPlot(c, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Right Hand Hourglass Rule: " + RHHR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("left hand rule")) {
				LeftHandRule LHR = new LeftHandRule(); // LeftHandRule extends Riemann
				PlotFrame d = new PlotFrame("x", "y", "Left Hand Rule");
				d.setDefaultCloseOperation(3);
				d.setVisible(true);
				LHR.rsPlot(d, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Left Hand Rule: " + LHR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("right hand rule")) {
				RightHandRule RHR = new RightHandRule(); // RightHandRule extends Riemann
				PlotFrame e = new PlotFrame("x", "y", "Right Hand Rule");
				e.setDefaultCloseOperation(3);
				e.setVisible(true);
				RHR.rsPlot(e, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Right Hand Rule: " + RHR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("midpoint rule")) {
				MidpointRule MR = new MidpointRule(); // MidpointRule extends Riemann
				PlotFrame f = new PlotFrame("x", "y", "Midpoint Rule");
				f.setDefaultCloseOperation(3);
				f.setVisible(true);
				MR.rsPlot(f, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Midpoint Rule: " + MR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("overall max")) {
				OverallMaxRule OMaR = new OverallMaxRule(); // OverallMaxRule extends Riemann
				PlotFrame g = new PlotFrame("x", "y", "Overall Max Rule");
				g.setDefaultCloseOperation(3);
				g.setVisible(true);
				OMaR.rsPlot(g, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Overall Maximum Rule: " + OMaR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("maximum")) {
				MaxRule MaxR = new MaxRule(); // MaxRule extends Riemann
				PlotFrame h = new PlotFrame("x", "y", "Maximum Rule");
				h.setDefaultCloseOperation(3);
				h.setVisible(true);
				MaxR.rsPlot(h, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Maximum Rule: " + MaxR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("overall min")) {
				OverallMinRule OMiR = new OverallMinRule(); // OverallMinRule extends Riemann
				PlotFrame i = new PlotFrame("x", "y", "Overall Min Rule");
				i.setDefaultCloseOperation(3);
				i.setVisible(true);
				OMiR.rsPlot(i, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Overall Minimum Rule: " + OMiR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("minimum")) {
				MinRule MinR = new MinRule(); // MinRule extends Riemann
				PlotFrame j = new PlotFrame("x", "y", "Minimum Rule");
				j.setDefaultCloseOperation(3);
				j.setVisible(true);
				MinR.rsPlot(j, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Minimum Rule: " + MinR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("random")) {
				RandomRule RR = new RandomRule(); // RandomRule extends Riemann
				PlotFrame k = new PlotFrame("x", "y", "Random Rule");
				k.setDefaultCloseOperation(3);
				k.setVisible(true);
				RR.rsPlot(k, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Random Rule: " + RR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("simpsons rule")) {
				SimpsonsRule SR = new SimpsonsRule(); // SimpsonsRule extends Riemann
				PlotFrame l = new PlotFrame("x", "y", "Simpsons Rule");
				l.setDefaultCloseOperation(3);
				l.setVisible(true);
				SR.rsPlot(l, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Simpsons Rule: " + SR.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("way two")) {
				SimpsonsRuleWayTwo SRWT = new SimpsonsRuleWayTwo(); // SimpsonsRule extends Riemann
				PlotFrame l = new PlotFrame("x", "y", "Simpsons Rule Way Two");
				l.setDefaultCloseOperation(3);
				l.setVisible(true);
				SRWT.rsPlot(l, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Simpsons Rule: " + SRWT.rs(poly, leftX, rightX, subintervals));
			}
			if (rules.contains("trapezoid")) {
				TrapezoidRule TR = new TrapezoidRule(); // TrapezoidRule extends Riemann
				PlotFrame m = new PlotFrame("x", "y", "Trapezoid Rule");
				m.setDefaultCloseOperation(3);
				m.setVisible(true);
				TR.rsPlot(m, poly, 1, precision, leftX, rightX, subintervals);
				System.out.println("Area of Trapezoid Rule: " + TR.rs(poly, leftX, rightX, subintervals));
			}
			
			//calculates actual area by splitting up polynomial into smaller accumulation functions
			double Aarea = 0;
			for (int i = 0; i < coefs.length; i++) {
				Aarea = Aarea + coefs[i] * ((Math.pow(rightX, (i + 1))) / (i + 1))
						- (coefs[i] * ((Math.pow(leftX, (i + 1))) / (i + 1)));
			}
			System.out.println("The actual area is " + Aarea);
			
			// ACCUMULATION FUNCTION OPTION
			System.out.println(
					"Would you like to graph one or accumulation functions? If yes, please specifcy which rules:");
			if (!scanner.hasNextLine()) {
				break;
			}
			String acc = scanner.nextLine().toLowerCase();
			if (acc.contains("left hand hourglass")) {
				LeftHandHourglassRule LHHR = new LeftHandHourglassRule(); // LeftHandRule extends Riemann
				PlotFrame a = new PlotFrame("x", "y", "Left Hand Hourglass Rule Accumulation Function");
				a.setDefaultCloseOperation(3);
				a.setVisible(true);
				LHHR.rsAcc(a, poly, 1, precision, leftX);
			}
			if (acc.contains("midpoint hourglass")) {
				MidpointHourglassRule MHR = new MidpointHourglassRule(); // LeftHandRule extends Riemann
				PlotFrame z = new PlotFrame("x", "y", "Midpoint Hourglass Rule Accumulation Function");
				z.setDefaultCloseOperation(3);
				z.setVisible(true);
				MHR.rsAcc(z, poly, 1, precision, leftX);
			}
			if (acc.contains("right hand hourglass")) {
				RightHandHourglassRule RHHR = new RightHandHourglassRule(); // RightHandHourglassRule extends Riemann
				PlotFrame c = new PlotFrame("x", "y", "Right Hand Hourglass Rule Accumulation Function");
				c.setDefaultCloseOperation(3);
				c.setVisible(true);
				RHHR.rsAcc(c, poly, 1, precision, leftX);
			}
			if (acc.contains("left hand rule")) {
				LeftHandRule LHR = new LeftHandRule(); // LeftHandRule extends Riemann
				PlotFrame d = new PlotFrame("x", "y", "Left Hand Rule Accumulation Function");
				d.setDefaultCloseOperation(3);
				d.setVisible(true);
				LHR.rsAcc(d, poly, 1, precision, leftX);
			}
			if (acc.contains("right hand rule")) {
				RightHandRule RHR = new RightHandRule(); // RightHandRule extends Riemann
				PlotFrame e = new PlotFrame("x", "y", "Right Hand Rule Accumulation Function");
				e.setDefaultCloseOperation(3);
				e.setVisible(true);
				RHR.rsAcc(e, poly, 1, precision, leftX);
			}
			if (acc.contains("midpoint rule")) {
				MidpointRule MR = new MidpointRule(); // MidpointRule extends Riemann
				PlotFrame f = new PlotFrame("x", "y", "Midpoint Rule Accumulation Function");
				f.setDefaultCloseOperation(3);
				f.setVisible(true);
				MR.rsAcc(f, poly, 1, precision, leftX);
			}
			if (acc.contains("overall max")) {
				OverallMaxRule OMaR = new OverallMaxRule(); // OverallMaxRule extends Riemann
				PlotFrame g = new PlotFrame("x", "y", "Overall Max Rule Accumulation Function");
				g.setDefaultCloseOperation(3);
				g.setVisible(true);
				OMaR.rsAcc(g, poly, 1, precision, leftX);
			}
			if (acc.contains("maximum")) {
				MaxRule MaxR = new MaxRule(); // MaxRule extends Riemann
				PlotFrame h = new PlotFrame("x", "y", "Maximum Rule Accumulation Function");
				h.setDefaultCloseOperation(3);
				h.setVisible(true);
				MaxR.rsAcc(h, poly, 1, precision, leftX);
			}
			if (acc.contains("overall min")) {
				OverallMinRule OMiR = new OverallMinRule(); // OverallMinRule extends Riemann
				PlotFrame i = new PlotFrame("x", "y", "Overall Min Rule Accumulation Function");
				i.setDefaultCloseOperation(3);
				i.setVisible(true);
				OMiR.rsAcc(i, poly, 1, precision, leftX);
			}
			if (acc.contains("minimum")) {
				MinRule MinR = new MinRule(); // MinRule extends Riemann
				PlotFrame j = new PlotFrame("x", "y", "Minimum Rule Accumulation Function");
				j.setDefaultCloseOperation(3);
				j.setVisible(true);
				MinR.rsAcc(j, poly, 1, precision, leftX);
			}
			if (acc.contains("random")) {
				RandomRule RR = new RandomRule(); // RandomRule extends Riemann
				PlotFrame k = new PlotFrame("x", "y", "Random Rule Accumulation Function");
				k.setDefaultCloseOperation(3);
				k.setVisible(true);
				RR.rsAcc(k, poly, 1, precision, leftX);
			}
			if (acc.contains("simpsons")) {
				SimpsonsRule SR = new SimpsonsRule(); // SimpsonsRule extends Riemann
				PlotFrame l = new PlotFrame("x", "y", "Simpsons Rule Accumulation Function");
				l.setDefaultCloseOperation(3);
				l.setVisible(true);
				SR.rsAcc(l, poly, 1, precision, leftX);
			}
			if (rules.contains("way two")) {
				SimpsonsRuleWayTwo SRWT = new SimpsonsRuleWayTwo(); // SimpsonsRule extends Riemann
				PlotFrame l = new PlotFrame("x", "y", "Simpsons Rule Way Two Accumulation Function");
				l.setDefaultCloseOperation(3);
				l.setVisible(true);
				SRWT.rsAcc(l, poly, 1, precision, leftX);
			}
			if (acc.contains("trapezoid")) {
				TrapezoidRule TR = new TrapezoidRule(); // TrapezoidRule extends Riemann
				PlotFrame m = new PlotFrame("x", "y", "Trapezoid Rule Accumulation Function");
				m.setDefaultCloseOperation(3);
				m.setVisible(true);
				TR.rsAcc(m, poly, 1, precision, leftX);
			}
			scan.close();
			scanner.close();
		}
		System.out.println("I'm sorry, you did not follow the directions. Please restart.");
	}
}
