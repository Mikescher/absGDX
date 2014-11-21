package de.samdev.absgdx.framework.math;


/**
 * This class contains mathematical methods for Float Operations 
 * 
 * mostly convenient methods that are normally in double precision
 *
 */
public class FloatMath {
	/** the mathematical constant PI in float precision */
	public static final float PI = (float) Math.PI;

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
}
