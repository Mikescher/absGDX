package de.samdev.absgdx.framework.math;

import de.samdev.absgdx.framework.util.exceptions.NonInstantiableException;

/**
 * This class contains mathematical methods for Float Operations 
 * 
 * mostly convenient methods that are normally in double precision
 */
public class FloatMath {
	/** the mathematical constant PI in float precision */
	public static final float PI = (float) Math.PI;

	private FloatMath() throws NonInstantiableException { throw new NonInstantiableException(); }
	
	/**
	 * Calculates the square (of a float)
	 * 
	 * @param x the input value
	 * @return x * x
	 */
	public static float fsquare(float x) {
		return x*x;
	}

	/**
	 * Calculates the pythagoras (a*a + b*b) (of a float)
	 * 
	 * @param a the first input value
	 * @param b the second input value
	 * @return a^2 + b^2
	 */
	public static float fpyth(float a, float b) {
		return a*a + b*b;
	}
	
	/**
	 * Calculates the square-root (as a float)
	 * 
	 * @param x the input value
	 * @return sqrt(x)
	 */
	public static float fsqrt(float x) {
		return (float) Math.sqrt(x);
	}
	
	/**
	 * Calculates the square-root (as a float)
	 * 
	 * @param x the input value
	 * @return sqrt(x)
	 */
	public static float fsqrt(double x) {
		return (float) Math.sqrt(x);
	}

	/**
	 * Get the maximum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @return MAX(a, b)
	 */
	public static float fmax(float a, float b) {
		return Math.max(a, b);
	}
	
	/**
	 * Get the maximum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @return MAX(a, b, c)
	 */
	public static float fmax(float a, float b, float c) {
		return Math.max(a, Math.max(b, c));
	}
	
	/**
	 * Get the maximum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @return MAX(a, b, c, d)
	 */
	public static float fmax(float a, float b, float c, float d) {
		return Math.max(a, Math.max(b, Math.max(c, d)));
	}
	
	/**
	 * Get the maximum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @return MAX(a, b, c, d, e)
	 */
	public static float fmax(float a, float b, float c, float d, float e) {
		return Math.max(a, Math.max(b, Math.max(c, Math.max(d, e))));
	}
	
	/**
	 * Get the maximum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @param f input value [6]
	 * @return MAX(a, b, c, d, e)
	 */
	public static float fmax(float a, float b, float c, float d, float e, float f) {
		return Math.max(a, Math.max(b, Math.max(c, Math.max(d, Math.max(e, f)))));
	}
	
	/**
	 * Get the maximum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @return MAX(a, b)
	 */
	public static float fnaturalmax(float a, float b) {
		if (Float.isNaN(a)) return b;
		if (Float.isNaN(b)) return a;
		return Math.max(a, b);
	}
	
	/**
	 * Get the maximum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @return MAX(a, b, c)
	 */
	public static float fnaturalmax(float a, float b, float c) {
		return fnaturalmax(a, fnaturalmax(b, c));
	}
	
	/**
	 * Get the maximum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @return MAX(a, b, c, d)
	 */
	public static float fnaturalmax(float a, float b, float c, float d) {
		return fnaturalmax(a, fnaturalmax(b, fnaturalmax(c, d)));
	}
	
	/**
	 * Get the maximum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @return MAX(a, b, c, d, e, f)
	 */
	public static float fnaturalmax(float a, float b, float c, float d, float e) {
		return fnaturalmax(a, fnaturalmax(b, fnaturalmax(c, fnaturalmax(d, e))));
	}
	
	/**
	 * Get the maximum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @param f input value [6]
	 * @return MAX(a, b, c, d, e, f)
	 */
	public static float fnaturalmax(float a, float b, float c, float d, float e, float f) {
		return fnaturalmax(a, fnaturalmax(b, fnaturalmax(c, fnaturalmax(d, fnaturalmax(e, f)))));
	}

	/**
	 * Get the maximum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @param f input value [6]
	 * @param g input value [7]
	 * @param h input value [8]
	 * @param i input value [9]
	 * @return MAX(a, b, c, d, e, f, g, h, i)
	 */
	public static float fnaturalmax(float a, float b, float c, float d, float e, float f, float g, float h, float i) {
		return fnaturalmax(a, fnaturalmax(b, fnaturalmax(c, fnaturalmax(d, fnaturalmax(e, fnaturalmax(f, fnaturalmax(g, fnaturalmax(h, i))))))));
	}
	
	/**
	 * Get the minimum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @return MIN(a, b)
	 */
	public static float fmin(float a, float b) {
		return Math.min(a, b);
	}
	
	/**
	 * Get the minimum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @return MIN(a, b, c)
	 */
	public static float fmin(float a, float b, float c) {
		return Math.min(a, Math.min(b, c));
	}
	
	/**
	 * Get the minimum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @return MIN(a, b, c, d)
	 */
	public static float fmin(float a, float b, float c, float d) {
		return Math.min(a, Math.min(b, Math.min(c, d)));
	}
	
	/**
	 * Get the minimum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @return MIN(a, b, c, d, e)
	 */
	public static float fmin(float a, float b, float c, float d, float e) {
		return Math.min(a, Math.min(b, Math.min(c, Math.min(d, e))));
	}
	
	/**
	 * Get the minimum of the input values
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @param f input value [6]
	 * @return MIN(a, b, c, d, e)
	 */
	public static float fmin(float a, float b, float c, float d, float e, float f) {
		return Math.min(a, Math.min(b, Math.min(c, Math.min(d, Math.min(e, f)))));
	}

	/**
	 * Get the minimum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @return MIN(a, b)
	 */
	public static float fnaturalmin(float a, float b) {
		if (Float.isNaN(a)) return b;
		if (Float.isNaN(b)) return a;
		return Math.min(a, b);
	}
	
	/**
	 * Get the minimum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @return MIN(a, b, c)
	 */
	public static float fnaturalmin(float a, float b, float c) {
		return fnaturalmin(a, fnaturalmin(b, c));
	}
	
	/**
	 * Get the minimum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @return MIN(a, b, c, d)
	 */
	public static float fnaturalmin(float a, float b, float c, float d) {
		return fnaturalmin(a, fnaturalmin(b, fnaturalmin(c, d)));
	}
	
	/**
	 * Get the minimum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @return MIN(a, b, c, d, e, f)
	 */
	public static float fnaturalmin(float a, float b, float c, float d, float e) {
		return fnaturalmin(a, fnaturalmin(b, fnaturalmin(c, fnaturalmin(d, e))));
	}
	
	/**
	 * Get the minimum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @param f input value [6]
	 * @return MIN(a, b, c, d, e, f)
	 */
	public static float fnaturalmin(float a, float b, float c, float d, float e, float f) {
		return fnaturalmin(a, fnaturalmin(b, fnaturalmin(c, fnaturalmin(d, fnaturalmin(e, f)))));
	}
	
	/**
	 * Get the minimum of the input values
	 * If one value is NaN then the other one is returned
	 * If both values are NaN then NaN is returned
	 * 
	 * @param a input value [1]
	 * @param b input value [2]
	 * @param c input value [3]
	 * @param d input value [4]
	 * @param e input value [5]
	 * @param f input value [6]
	 * @param g input value [7]
	 * @param h input value [8]
	 * @param i input value [9]
	 * @return MIN(a, b, c, d, e, f, g, h, i)
	 */
	public static float fnaturalmin(float a, float b, float c, float d, float e, float f, float g, float h, float i) {
		return fnaturalmin(a, fnaturalmin(b, fnaturalmin(c, fnaturalmin(d, fnaturalmin(e, fnaturalmin(f, fnaturalmin(g, fnaturalmin(h, i))))))));
	}
	
	/**
	 * Converts a angle in degree to one in radians
	 * 
	 * @param degree the angle in degree
	 * @return the angle in radians
	 */
	public static float toRadians(double degree) {
		return (float) Math.toRadians(degree);
	}

	/**
	 * Converts a angle in radians to one in degree
	 * 
	 * @param rad the angle in radians
	 * @return the angle in degree
	 */
	public static float toDegrees(double rad) {
		return (float) Math.toDegrees(rad);
	}

	/**
	 * Calculates the sinus
	 * 
	 * @param angle the angle in radians
	 * @return the sinus  of the angle
	 */
	public static float fsin(double angle) {
		return (float) Math.sin(angle);
	}

	/**
	 * Calculates the cosinus
	 * 
	 * @param angle the angle in radians
	 * @return the cosinus  of the angle
	 */
	public static float fcos(double angle) {
		return (float) Math.cos(angle);
	}

	/**
	 * Calculates the tangens
	 * 
	 * @param angle the angle in radians
	 * @return the tangens  of the angle
	 */
	public static float ftan(double angle) {
		return (float) Math.tan(angle);
	}

	/**
	 * Calculates the absolute value
	 * 
	 * @param f the input value
	 * @return |f|
	 */
	public static float fabs(float f) {
		return Math.abs(f);
	}

	/**
	 * Returns the sign of the input value
	 * 
	 * @param f the input value
	 * @return -1, 0, or 1  (in special cases NaN, see {@link java.lang.Math#signum(double) signum})
	 */
	public static float fsignum(float f) {
		return Math.signum(f);
	}

	/**
	 * Calculates Atan2
	 * 
	 * @see java.lang.Math#atan2(double, double)
	 * 
	 * @param y the ordinate coordinate 
	 * @param x the abscissa coordinate
	 * @return the theta component of the point (r, theta) in polar coordinates that corresponds to the point (x, y) in Cartesian coordinates.
	 */
	public static float fatan2(float y, float x) {
		return (float) Math.atan2(y, x);
	}
}
