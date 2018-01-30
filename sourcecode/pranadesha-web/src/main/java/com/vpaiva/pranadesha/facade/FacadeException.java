/**
 * 
 */
package com.vpaiva.pranadesha.facade;

/**
 * Facade Exception
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
public class FacadeException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public FacadeException() {
		super();
	}
	
	/**
	 * constructor
	 * @param message Detail message
	 * @param cause The cause
	 */
	public FacadeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FacadeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FacadeException(Throwable cause) {
		super(cause);
	}

}
