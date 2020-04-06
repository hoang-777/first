package com.springboot.tick;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TickController {
	
	@Autowired
	public TickService tickService;
	
	@RequestMapping("/ticks")
	public List<Tick> getAllTicks() {
		
		System.out.println(System.currentTimeMillis() - tickService.getAllTicks().get(0).getTimestamp());
		
		return tickService.getAllTicks();
		
	}

	@RequestMapping("/ticks/{id}")
	public Tick getTicks(@PathVariable String id) {
		
		return tickService.getTicks(id);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/ticks")
	public void addTick(@RequestBody Tick tick) {
		
		//throw new ApiRequestException("ouch");
		
		tickService.addTick(tick);
		
		System.out.println(System.currentTimeMillis() - tick.getTimestamp());
		
	}
	
	


}
