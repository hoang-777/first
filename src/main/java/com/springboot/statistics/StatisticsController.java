package com.springboot.statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StatisticsController {
	
	@Autowired
	private StatisticsService statisticsService;
	
	@RequestMapping("/statistics")
	public Statistics getAllStatistics() {
		
		
		return statisticsService.getAllStatistics();
		
	}
	
	@RequestMapping("/statistics/{id}")
	public Statistics getStatistics(@PathVariable String id) {
		
		return statisticsService.getStatistics(id);
		
	}

	
	
	
	
}
