package com.springboot.statistics;

public class Statistics {
	
	private Double avg;
	private Double max;
	private Double min;
	private double maxDrawdown;
	private double volatility;
	private double quantile;
	private double twap;
	private double twap2;
	private Long count;
	
	public Statistics(Double avg, Double max, Double min, Double volatility,Long count, Double maxDrawdown , Double quantile, Double twap, Double twap2){
		
		super();
		
		this.avg = avg;
		this.max = max;
		this.min = min;
		this.maxDrawdown = maxDrawdown;
		this.volatility = volatility;
		this.quantile = quantile;
		this.twap = twap;
		this.twap2 = twap2;
		this.count = count;
		

	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}
	
	public double getVolatility() {
		return volatility;
	}

	public void setVolatility(double volatility) {
		this.volatility = volatility;
	}

	public double getQuantile() {
		return quantile;
	}

	public void setQuantile(double quantile) {
		this.quantile = quantile;
	}

	public double getMaxDrawdown() {
		return maxDrawdown;
	}

	public void setMaxDrawdown(double maxDrawdown) {
		this.maxDrawdown = maxDrawdown;
	}

	public double getTwap() {
		return twap;
	}

	public void setTwap(double twap) {
		this.twap = twap;
	}
	
	public double getTwap2() {
		return twap2;
	}

	public void setTwap2(double twap2) {
		this.twap2 = twap2;
	}
	
	public double getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	

}
