package com.shop.yi.common.util.excel;

/**
 * 
 * @ClassName: ExcelException 
 * @Description: TODO
 * @author: 国
 * @date: 2017年5月28日 下午12:10:24
 */
public class ExcelException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3168610399429851026L;
	/**
	 * 
	 * @Title:ExcelException
	 * @Description:TODO
	 */
	public ExcelException() {
	}
	
	public ExcelException(String message) {
		super(message);
	}
	public ExcelException(Throwable cause) {
		super(cause);
	}
	public ExcelException(String message, Throwable cause) {
		super(message, cause);
	}
}
