package me.vem.utils.math;

import java.awt.Point;
import java.nio.ByteBuffer;

import me.vem.utils.io.Compressable;

/**
 * I'll document this class **LATER**. There's a ton of methods and it's past midnight. I'm just getting this stuff done.
 * @author Vemahk
 */
public class Vector implements Compressable{

	public static final Vector origin = new Vector();
	
	private float x, y;
	
	public Vector() { this(0, 0); }
	public Vector(Vector v) { this(v.x, v.y); }
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() { return x; }
	public int roundX() { return Math.round(x); }
	public int floorX() { return (int) Math.floor(x); }
	public int ceilX () { return (int) Math.ceil (x); }
	
	public float getY() { return y; }
	public int roundY() { return Math.round(y); }
	public int floorY() { return (int) Math.floor(y); }
	public int ceilY () { return (int) Math.ceil (y); }
	
	public Point round() { return new Point(roundX(), roundY()); }
	public Point floor() { return new Point(floorX(), floorY()); }
	public Point ceil() { return new Point(ceilX(), ceilY()); }
	
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector v) {
		this.set(v.x, v.y);
	}
	
	public void offset(float dx, float dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void offset(Vector dv) {
		this.offset(dv.x, dv.y);
	}
	
	public void offsetX(float dx) { this.x += dx; }
	public void offsetY(float dy) { this.y += dy; }
	
	public float getMagnetude() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public float getMagSq() {
		return x*x + y*y;
	}
	
	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y);
	}
	
	public Vector sub(Vector v) {
		return new Vector(x - v.x, y - v.y);
	}
	
	public Vector scale(float scalar) {
		return new Vector(x * scalar, y * scalar);
	}
	
	public float dot(Vector v) { 
		return x * v.x + y * v.y;
	}
	
	public Vector inverseMag(float numerator) {
		float nx = this.x;
		float ny = this.y;
		
		if(nx == 0 && ny == 0) 
			return Vector.origin;
		
		float mag = getMagnetude();
		float prop = numerator / mag / mag;
		
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
	
	public static Vector pointDiff(float x1, float y1, float x2, float y2) {
		return new Vector(x1 - x2, y1-y2);
	}
}