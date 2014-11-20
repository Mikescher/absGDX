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
	 * Calculates the square-root (of a float)
	 * 
	 * @param x the input value
	 * @return sqrt(x)
	 */
	public static float fsqrt(float x) {
		return (float) Math.sqrt(x);
	}
}
