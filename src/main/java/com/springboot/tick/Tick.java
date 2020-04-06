package com.springboot.tick;


public class Tick {
	
	private String instrument;
	private Double price;
	private Long timestamp;
	
	public Tick() {
		
		
	}
	
	public Tick(String instrument, Double price, Long timestamp) {
		super();
		this.instrument = instrument;
		this.price = price;
		this.timestamp = timestamp;
	}
	
	public String getInstrument() {
		
		return instrument;
	}
	public void setInstrument(String id) {
		
		this.instrument = id;
	}
	public Double getPrice() {
		
		return price;
	}
	public void setName(Double price) {
		
		this.price = price;
	}
	
	public Long getTimestamp() {
		
		return timestamp;		
	}
	public void setTimestamp(Long timestamp) {
		
		this.timestamp = timestamp;
		
	}

	
	
}