package me.vem.utils.math;

import java.awt.Point;
import java.nio.ByteBuffer;

import me.vem.utils.io.Compressable;

/**
 * A class designed to represent 2-Dimensional Vectors.
 * 
 * @author Vemahk
 */
public class Vector implements Compressable{
	
	/**
	 * The X component for this vector. Assumes standard position (i.e. that the vector originates from the origin).
	 */
	private float x;
	
	/**
	 * The Y component for this vector. Assumes standard position (i.e. that the vector originates from the origin).
	 */
	private float y;
	
	/**
	 * Default constructor: initializes a Vector at the origin.
	 */
	public Vector() {
		this(0, 0);
	}
	
	/**
	 * A copy constructor. Technically a concept from C++ that isn't widely implemented in JAVA.<br>
	 * Essentially just copies the coordinates from the given Vector into a new one.
	 * @param v The vector to be copied.
	 */
	public Vector(Vector v) { this(v.x, v.y); }
	
	/**
	 * Creates a vector with the given X and Y components.
	 * @param x The given X component.
	 * @param y The given Y component.
	 */
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return The float value of this Vector's X component.
	 */
	public float getX() { return x; }
	
	/**
	 * @return The rounded int of this Vector's X component.
	 */
	public int roundX() { return Math.round(x); }
	
	/**
	 * @return The floored int of this Vector's X component.
	 */
	public int floorX() { return (int) Math.floor(x); }
	
	/**
	 * @return The ceilinged int of this Vector's X component.
	 */
	public int ceilX () { return (int) Math.ceil (x); }
	
	/**
	 * @return The float value of this Vector's Y component;
	 */
	public float getY() { return y; }
	
	/**
	 * @return The rounded int of this Vector's Y component.
	 */
	public int roundY() { return Math.round(y); }
	
	/**
	 * @return The floored int of this Vector's Y component.
	 */
	public int floorY() { return (int) Math.floor(y); }
	
	/**
	 * @return The ceilinged int of this Vector's Y component.
	 */
	public int ceilY () { return (int) Math.ceil (y); }
	
	/**
	 * @return A java.awt.Point whose x, y components represent this vector's rounded x and y components.
	 */
	public Point round() { return new Point(roundX(), roundY()); }
	
	/**
	 * @return A java.awt.Point whose x, y components represent this vector's floored x and y components.
	 */
	public Point floor() { return new Point(floorX(), floorY()); }
	
	/**
	 * @return A java.awt.Point whose x, y components represent this vector's ceilinged x and y components.
	 */
	public Point ceil() { return new Point(ceilX(), ceilY()); }
	
	/**
	 * Sets the X component of this Vector to the given x float.
	 * @param x
	 */
	public void setX(float x) { this.x = x; }
	
	/**
	 * Sets the Y component of this Vector to the given y float.
	 * @param y
	 */
	public void setY(float y) { this.y = y; }
	
	/**
	 * Sets the X and Y components of this Vector to the given x and y floats.
	 * @param x
	 * @param y
	 */
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the X and Y components of this Vector to the x and y components of the given Vector v.
	 * @param v
	 */
	public void set(Vector v) {
		this.set(v.x, v.y);
	}
	
	/**
	 * Adds the given dx (change in x) and dy (change in y) to the X and Y components of this Vector.
	 * @param dx
	 * @param dy
	 */
	public void offset(float dx, float dy) {
		this.x += dx;
		this.y += dy;
	}
	
	/**
	 * Adds the components of the given Vector 'dv' to this vector.
	 * @param dv
	 */
	public void offset(Vector dv) {
		this.offset(dv.x, dv.y);
	}
	
	/**
	 * Adds the given dx (change in x) to the X component of this Vector.
	 * @param dx
	 */
	public void offsetX(float dx) { this.x += dx; }
	
	/**
	 * Adds to given dy (change in y) to the Y component of this Vector.
	 * @param dy
	 */
	public void offsetY(float dy) { this.y += dy; }
	
	/**
	 * @return The magnitude, or length, of this Vector.
	 */
	public float getMagnitude() {
		return (float) Math.sqrt(getMagSq());
	}

	/**
	 * This method is meant to be used in such cases where the exact length is not necessary.<br>
	 * The case for this being that Math.sqrt is quite inefficient and is preferred to be avoided.
	 * @return The square of the magnitude of this Vector.
	 */
	public float getMagSq() {
		return dot(this);
	}
	
	/**
	 * @param v
	 * @return A new Vector whose components are the sum of this Vector and the given Vector 'v'.
	 */
	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y);
	}
	
	/**
	 * @param v
	 * @return A new Vector whose components are the difference of this Vector and the given Vector 'v'.
	 */
	public Vector sub(Vector v) {
		return new Vector(x - v.x, y - v.y);
	}
	
	/**
	 * @param scalar
	 * @return A new Vector which representative of this Vector multiplied by the given scalar. 
	 */
	public Vector scale(float scalar) {
		return new Vector(x * scalar, y * scalar);
	}
	
	/**
	 * The dot product is the sum of the products of each component of a vector with the corresponding component of another vector.<br>
	 * Thus, in 2-Dimensions, it is the sum of the products of the x components of the two vectors and the y components of the two vectors.<br>
	 * <br>
	 * That being said, the dot product of a vector with itself is the square of its length.
	 * @param v
	 * @return The dot product of this Vector and the given Vector 'v'.
	 */
	public float dot(Vector v) { 
		return x * v.x + y * v.y;
	}
	
	/**
	 * A screwy method that I don't recommend using...<br><br>
	 * 
	 * @param numerator
	 * @return A new Vector whose magnitude is the given numerator / the magnitude squared of this vector. 
	 * The new vector points in the same direction as the original vector. 
	 * I told you it was screwy.
	 */
	public Vector inverseMag(float numerator) {
		float nx = this.x;
		float ny = this.y;
		
		if(nx == 0 && ny == 0) 
			return new Vector();
		
		float prop = numerator / getMagSq();
		
		nx *= prop;
		ny *= prop;
		
		return new Vector(nx, ny);
	}
	
	@Override public int writeSize() { return 8; }
	
	@Override
	public ByteBuffer writeTo(ByteBuffer buf) {
		return buf.putFloat(x).putFloat(y);
	}
	
	@Override
	public Object clone(){
		return new Vector(this);
	}
	
	public String toString() {
		return String.format("%.3f,%.3f", x, y);
	}
	
	/**
	 * Creates an x-y Vector based on a polar vector of magnitude r and direction th (theta).
	 * @param r Magnitude
	 * @param th Angle (in radians)
	 * @return
	 */
	public static Vector fromPolar(float r, float th) {
		return new Vector(r * (float)Math.cos(th), r * (float)Math.sin(th));
	}
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return A vector whose components can be represented by (x1-x2), (y1-y2).
	 */
	public static Vector pointDiff(float x1, float y1, float x2, float y2) {
		return new Vector(x1 - x2, y1-y2);
	}
}