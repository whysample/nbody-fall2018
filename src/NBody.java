	

/**
 * @author Zachary Wieshampel
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
	
		int number=s.nextInt();
		double radius=s.nextDouble();
		
		s.close();
		
		return radius;	
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
		
			int nb = s.nextInt(); // # bodies to be read
			double radius=s.nextDouble();
			Body[] bodies=new Body[nb];
			for(int k=0; k < nb; k++) {
				double xp=s.nextDouble();
				double yp=s.nextDouble();
				double xvel=s.nextDouble();
				double yvel=s.nextDouble();
				double mass=s.nextDouble();
				String file=s.next();
				bodies[k]= new Body(xp,yp,xvel,yvel,mass,file);
			}
			
			s.close();
			
			// TODO: return array of body objects read
			return bodies;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = Math.pow(10, 9);
		double dt = 1000000;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: create double arrays xforces and yforces
			// to hold forces on each body
			double[] xforces=new double[bodies.length];
			double [] yforces=new double[bodies.length];
			// TODO: loop over all bodies, calculate
			// net forces and store in xforces and yforces
			for(int o=0;o<bodies.length;o++) {
				xforces[o]=bodies[o].calcNetForceExertedByX(bodies);
				yforces[o]=bodies[o].calcNetForceExertedByY(bodies);
			}
			// TODO: loop over all bodies and call update
			// with dt and corresponding xforces, yforces values
			for(int y=0;y<bodies.length;y++) {
				bodies[y].update(dt, xforces[y], yforces[y]);
			}
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			for(Body k: bodies) {
				k.draw();
			}
		
			
			StdDraw.show(10);
		}
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",bodies[i].getX(), bodies[i].getY(),bodies[i].getXVel(), bodies[i].getYVel(), bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
