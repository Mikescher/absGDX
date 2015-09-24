package de.samdev.absgdx.framework.math;

import de.samdev.absgdx.framework.util.exceptions.NonInstantiableException;

/**
 * This class contains common math operations (missing in java.math)
 */
public class AbsMath {

	private AbsMath() throws NonInstantiableException { throw new NonInstantiableException(); }

	/**
	 * Returns the biggest parameter by value
	 * 
	 * @param v0 parameters
	 * @param v  parameters
	 * 
	 * @return MAX(PARAMS[])
	 */
	public static int imax(int v0, int... v) {
		for (int v_n : v) {
			v0 = Math.max(v0, v_n);
		}
		return v0;
	}

	/**
	 * Returns the smallest parameter by value
	 * 
	 * @param v0 parameters
	 * @param v  parameters
	 * 
	 * @return MIN(PARAMS[])
	 */
	public static int imin(int v0, int... v) {
		for (int v_n : v) {
			v0 = Math.min(v0, v_n);
		}
		return v0;
	}
}
