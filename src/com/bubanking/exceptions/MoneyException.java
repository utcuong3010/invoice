package com.bubanking.exceptions;

public class MoneyException extends Exception{

	/**
	 * 
	 */
	private long sumMoney;
	private long totalMoney;
	private static final long serialVersionUID = 1L;
	public MoneyException(long sum, long total) {
		super();
		this.sumMoney = sum;
		this.totalMoney = total;
		
	}
	
	@Override
	public String toString() {
		return "[ Money with error: sum of money=" + sumMoney + " and total of money=" + totalMoney + "]"; 
	}

}
