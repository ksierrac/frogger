import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


/**
 * A MovingGameObject has a vector (changeX and changeY), an angle, an image,
 * an age, and a maximum age.
 * 
 * @author Kim
 *
 */

public class MovingGameObject extends GameObject {

	protected MyVector vector;
	protected double angle;
	protected Image myImage;
	protected int age;
	protected int maxAge;

	/**
	 * Create a MovingScreenObject at a particular location, size, and image.
	 * Age will be initialized to zero.
	 * 
	 * @param location
	 *            The starting location.
	 * @param size
	 *            The starting size.
	 * @param angle
	 *            The object's orientation.
	 */
	public MovingGameObject(Point location, Rectangle size, Image i,
			double angle) {
		super(location, size);
		myImage = i;
		this.angle = angle;
		age = 0;
		maxAge = Integer.MAX_VALUE;

	}

	/**
	 * To move an object, add its vector to its location. Check for boundary
	 * conditions. Increase the age by 1.
	 */
	public void move() {

		location.x += vector.getChangeX();
		location.y += vector.getChangeY();

		if (location.x > FrogScreen.screenWidth) {
			location.x -= FrogScreen.screenWidth;
		}
		if (location.x < 0) {
			location.x += FrogScreen.screenWidth;
		}

		if (location.y > FrogScreen.screenHeight) {
			location.y -= FrogScreen.screenHeight;
		}
		if (location.y < 0) {
			location.y += FrogScreen.screenHeight;
		}

		age++;

	}

	/**
	 * Return true if the objects collide.
	 * 
	 * @param otherObj
	 *            The other moving object.
	 * @return True if there is a collision; false, otherwise.
	 */
	public boolean collide(MovingGameObject otherObj) {
		Rectangle otherR = otherObj.getSize();
		otherR.setLocation(otherObj.getLocation());
		this.getSize().setLocation(this.getLocation());
		if (otherR.intersects(this.getSize())) {
			return true;
		}
		return false;
	}

	/**
	 * What to do if destroyed.
	 */
	public void destruct() {

	}

	/**
	 * Draw the object by displaying its image.
	 */
	public void draw(Graphics g) {
		// reminder ... later we want to rotate image by
		// changeX and changeY
		Graphics2D g2 = (Graphics2D) g;
		if (this instanceof Frogs) {
			Frogs frog = (Frogs) this;

			// AffineTransform identity = new AffineTransform();
			AffineTransform trans = new AffineTransform();
			// trans.setTransform(identity);
			trans.translate(location.x, location.y);
			//trans.scale(0.25, 0.25);

			trans.rotate(Math.toRadians(frog.getAngle()),
					myImage.getWidth(null) / 2, myImage.getHeight(null) / 2);

			g2.drawImage(myImage, trans, null);
		} else {
			g2.drawImage(myImage, location.x, location.y, size.width,
					size.height, null);
		}
	}
		
	

	/**
	 * Retrieve the vector.  
	 * @return the vector
	 */
	public MyVector getVector() {
		return vector;
	}

	/**
	 * Change the vector.
	 * @param vector
	 *            the vector to set
	 */
	public void setVector(MyVector vector) {
		this.vector = vector;
	}

	/**
	 * Retreive the image.
	 * @return the myImage
	 */
	public Image getMyImage() {
		return myImage;
	}

	/**
	 * Change the image. 
	 * @param myImage
	 *            the myImage to set
	 */
	public void setMyImage(Image myImage) {
		this.myImage = myImage;
	}

	/**
	 * Retreive the angle.
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Change the angle.
	 * @param angle
	 *            the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * Retrieve the age.
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Change the age.
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Retrieve the maximum age.
	 * @return the maxAge
	 */
	public int getMaxAge() {
		return maxAge;
	}

	/**
	 * Change the maximum age.
	 * @param maxAge
	 *            the maxAge to set
	 */
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

