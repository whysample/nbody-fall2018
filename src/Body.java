
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	/**
	 * Constructor that helps to initialize a body object with given instance variables using the 
	 * parameters recorded. 
	 * @param xpos is of type double that represents the object body's position on the x axis
	 * @param ypos is of type double that represents the object body's position on the y axis
	 * @param xvel is of type double that represents the object body's velocity in the x direction
	 * @param yvel is of type double that represents the object body's velocity in the y direction
	 * @param mass is of type String that represents the object body's mass
	 * @param file
	 */
	public Body(double xpos, double ypos, double xvel, double yvel, double mass, String file) {
		myXPos=xpos;
		myYPos=ypos;
		myXVel=xvel;
		myYVel=yvel;
		myMass=mass;
		myFileName=file;
	}
	/**
	 * Constructor that helps to initialize a body object by copying the object b's values. 
	 * @param b is an object that represents a body that can be copied to pass on its values.
	 */
	public Body(Body b) {
		myXPos=b.getX();
		myYPos=b.getY();
		myXVel=b.getXVel();
		myYVel=b.getYVel();
		myMass=b.getMass();
		myFileName=b.getName();
	}
	/**
	 * Returns the calculated distance between two object bodies. These bodies include this body and the b body that 
	 * is listed in the parameter.
	 * @param b an object that represents a body and the distance between this object and b is measured
	 * @return a value of type double that represents the distance of the two objects
	 */
	public double calcDistance(Body b) {
		double distX=this.myXPos-b.getX();
		double distY=this.myYPos-b.getY();
		double distance= Math.sqrt((distX*distX)+(distY*distY));
		return distance;
	}
	/**
	 * Returns the force between two objects representing bodies, specifically it represents the force exerted on this object
	 * from the object b in the parameter
	 * @param b an object that represents a body and is used to measure its force on this object
	 * @return a value of type double that represents the force of the body b on the this object.
	 */
	public double calcForceExertedBy(Body b) {
		double grav=6.67*Math.pow(10, -11);
		double force=grav*this.getMass()*b.getMass()/(this.calcDistance(b)*this.calcDistance(b));
		return force;
	}
	/**
	 * Returns the force between two objects representing bodies in the x direction, specifically it represents the force on the x axis 
	 * exerted on this object from the object b in the parameter
	 * @param b is an object that represents a body that acts on this object
	 * @return a value of type double that represents the force of the body b on the this object in the x direction. 
	 */
	public double calcForceExertedByX(Body b) {
		double forceX=calcForceExertedBy(b)*(b.getX()-this.myXPos)/calcDistance(b);
		return forceX;
	}
	/**
	 * Returns the force between two objects representing bodies in the y direction, specifically it represents the force on the y axis 
	 * exerted on this object from the object b in the parameter
	 * @param b is an object that represents a body that acts on this object
	 * @return a value of type double that represents the force of the body b on the this object in the y direction. 
	 */
	public double calcForceExertedByY(Body b) {
		double forceY=calcForceExertedBy(b)*(b.getY()-this.myYPos)/calcDistance(b);
		return forceY;
	}
	/**
	 * Returns the total forces in the x direction between all the object bodies in the bodies array on this object.
	 * @param bodies is an array that contains all the body objects 
	 * @return a value of type double that represents the total net force in the x direction for this body
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double count=0.0;
		for (int k=0;k<bodies.length;k++) {
			if(! bodies[k].equals(this)) {
				count=count+calcForceExertedByX(bodies[k]);
			}
		}
		return count;
	}
	/**
	 * Returns the total forces in the y direction between all the object bodies in the bodies array on this object.
	 * @param bodies is an array that contains all the body objects 
	 * @return a value of type double that represents the total net force in the y direction for this body
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double count=0.0;
		for (int k=0;k<bodies.length;k++) {
			if(! bodies[k].equals(this)) {
				count=count+calcForceExertedByY(bodies[k]);
			}
		}
		return count;
	}
	/**
	 * A manipulator method that updates a body object's variables during the simulation by using time, the force in the x direction,
	 * and the force in the y direction of the specific body. 
	 * @param dt is a variable of type double which represents the amount of change in time
	 * @param xf is a variable of type double which represents force in the x direction for a specific body
	 * @param yf is a variable of type double which represents force in the y direction for a specific body
	 */
	public void update(double dt,double xf, double yf) {
		double ax=xf/getMass();
		double ay=yf/getMass();
		double nvy=getYVel()+dt*ay;
		double nvx=getXVel()+dt*ax;
		double nx=getX()+dt*nvx;
		double ny=getY()+dt*nvy;
		myXPos=nx;
		myYPos=ny;
		myXVel=nvx;
		myYVel=nvy;
	}
	/**
	 * Creates the images of the planets on the output screen
	 */
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	/**
	 * Used as a getter method to obtain the object's velocity in the y direction 
	 * @return the private instance variable myYVel that is of type double that represents
	 * the velocity of the object in the y direction
	 */
	public double getYVel() {
		return myYVel;
	}
	/**
	 * Used as a getter method to obtain the object's velocity in the x direction
	 * @return the private instance variable myXVel that is of type double that represents
	 * the velocity of the object in the x direction
	 */
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Used as a getter method to obtain the object's total mass
	 * @return the private instance variable myMass that is of type double that represents
	 * the object's total mass
	 */
	public double getMass() {
		return myMass;
	}
	/**
	 * Used as a getter method to obtain the object's position on the x axis
	 * @return the private instance variable myXPos that is of type double that represents
	 * the object's position in the x direction
	 */
	public double getX() {
		return myXPos;
	}
	/**
	 * Used as a getter method to obtain the object's position on the y axis
	 * @return the private instance variable myYPos that is of type double that represents
	 * the object's position in the y direction
	 */
	public double getY() {
		return myYPos;
	}
	/**
	 * Used as a getter method to obtain the file saved in the string myFileName
	 * @return the private instance variable myFileName that is the file used to obtain the data of the 
	 * values of the bodies
	 */
	public String getName() {
		return myFileName;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
