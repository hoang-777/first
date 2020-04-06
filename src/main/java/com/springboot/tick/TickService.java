package com.springboot.tick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springboot.exception.ApiRequestCorrect;
import com.springboot.exception.ApiRequestException;


@EnableScheduling
@Service
public class TickService {
	
		public static List<Tick> ticks = new ArrayList<>(Arrays.asList(
				new Tick("IBM.N", 142D, System.currentTimeMillis()),
				new Tick("IBM.N", 142.5D, System.currentTimeMillis()+3000),
				new Tick("IBM.N", 142.8D, System.currentTimeMillis()+6000),
				new Tick("IBM.N", 140.3D, System.currentTimeMillis()+10000),
				new Tick("IBM.N", 147.8D, System.currentTimeMillis()+14000),
				new Tick("IBM.N", 150.5D, System.currentTimeMillis()+15000),
				new Tick("IBM.N", 149.6D, System.currentTimeMillis()+16000),
				new Tick("IBM.N", 149.1D, System.currentTimeMillis()+19000),
				new Tick("IBM.N", 151.2D, System.currentTimeMillis()+24000),
				new Tick("GOOG", 1413.8D, System.currentTimeMillis()),
				new Tick("GOOG", 1420.5D, System.currentTimeMillis()+3000),
				new Tick("GOOG", 1422.8D, System.currentTimeMillis()+6000),
				new Tick("GOOG", 1400.8D, System.currentTimeMillis()+10000),
				new Tick("GOOG", 1427.1D, System.currentTimeMillis()+14000),
				new Tick("GOOG", 1426.2D, System.currentTimeMillis()+15000),
				new Tick("GOOG", 1429.0D, System.currentTimeMillis()+16000),
				new Tick("GOOG", 1430.8D, System.currentTimeMillis()+17000),
				new Tick("GOOG", 1434.1D, System.currentTimeMillis()+19000),
				new Tick("GOS", 136.4D, System.currentTimeMillis()),
				new Tick("GOS", 142.5D, System.currentTimeMillis()+2000),
				new Tick("GOS", 138.6D, System.currentTimeMillis()+7000),
				new Tick("GOS", 139.1D, System.currentTimeMillis()+9000),
				new Tick("GOS", 140.0D, System.currentTimeMillis()+10000),
				new Tick("GOS", 141.1D, System.currentTimeMillis()+12000),
				new Tick("GOS", 142.0D, System.currentTimeMillis()+14000),
				new Tick("GOS", 140.3D, System.currentTimeMillis()+15500),
				new Tick("GOS", 141.9D, System.currentTimeMillis()+16000),
				new Tick("GOS", 143.2D, System.currentTimeMillis()+17000),
				new Tick("GOS", 142.4D, System.currentTimeMillis()+19000)
				));
		
		public List<Tick> getAllTicks(){
			
			return ticks; 
			
		}
		
		public Tick getTicks(String id) {
			
			return ticks.stream().filter(t -> t.getInstrument().equals(id)).findFirst().get();
		}
	
		public void addTick(Tick tick) {
			

			ticks.add(tick);
			validateTimestamp(tick);

			
		}
		public static List<Tick> getTransactions() {
			return ticks;
			}
		
		@Scheduled(fixedRate = 1000, initialDelay = 0)
		public synchronized void clearOld() {
			
			ticks.removeIf(
						transaction -> (System.currentTimeMillis() - transaction.getTimestamp()) > 60000);
		}


		public void validateTimestamp(Tick tick) {
			
			if(System.currentTimeMillis() - tick.getTimestamp() > 6000L) {
				
				System.out.println(System.currentTimeMillis() - tick.getTimestamp());
				throw new ApiRequestException("old data");
			}else {
				throw new ApiRequestCorrect("recent data");

				
			}
			
		}
		
}
