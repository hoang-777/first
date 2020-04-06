package com.springboot.statistics;
import static java.util.Comparator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springboot.exception.ApiRequestException;
import com.springboot.tick.Tick;
import com.springboot.tick.TickService;
import java.util.logging.Level;
import java.util.logging.Logger;


@EnableScheduling
@Service
public class StatisticsService {
	
	private Statistics statistics = new Statistics(Double.NaN, Double.NaN, Double.NaN, Double.NaN, 0L, Double.NaN, Double.NaN, Double.NaN, Double.NaN);
	
	public List<Tick> ticks = TickService.getTransactions();
	
	private static Logger jlog =  Logger.getLogger("info");

	public Statistics getAllStatistics(){
		
		return statistics; 
		
	}

	public Statistics getStatistics(String id) {
		
		List<Tick> listIdentified =  ticks.stream().filter(t -> t.getInstrument().equals(id)).collect(Collectors.toList());
		
		return calculateStats(listIdentified);
		
	}


	public static double maxDrawdown(List<Double> prices) {
		
		// Modified Version from 
		// https://quant.stackexchange.com/questions/4886/fastest-algorithm-for-calculating-retrospective-maximum-drawdown
		
		if (prices.size() <= 1) return 0;
		
		double maxPrice = prices.get(0);
		
		double maxDd = 0;

		for (int i = 1; i < prices.size(); i++) {
			
			if (prices.get(i) > maxPrice) maxPrice = prices.get(i);
			
			else if (prices.get(i) < maxPrice) maxDd = Math.min(maxDd, prices.get(i) / maxPrice - 1);
			
		}

		return maxDd;

	}
	
	@Scheduled(fixedRate = 1000, initialDelay = 0)
	public synchronized void calculateStatsRecurring() {
		//Calculate Statistics Recurringly to obtain O(n) for the Second API call
		if(ticks.size()!=0) {
			
			calculateStats(ticks);
			
		}else {
			
			jlog.log(Level.INFO, "Currently no data for statistics");
			
		}
	}


	public synchronized Statistics calculateStats(List<Tick> ticks) {
		
		Map<String, List<Double>> eachTick = ticks.stream()
				.collect(Collectors.groupingBy(t -> t.getInstrument(),
						Collectors.mapping(t -> t.getPrice(),Collectors.toList()))
						);
		
		List<Double> maxDD = new ArrayList<>();
		for (Map.Entry<String, List<Double>> entry : eachTick.entrySet()) {
			maxDD.add(maxDrawdown(entry.getValue()));
		}
		
		
		Double Average = ticks
				.stream()
				.mapToDouble(x->x.getPrice())
				.average().orElse(Double.NaN);
		
		Double Max = ticks
				.stream()
				.mapToDouble(x->x.getPrice())
				.max().orElse(Double.NaN);
		
		Double Min = ticks
				.stream()
				.mapToDouble(x->x.getPrice())
				.min().orElse(Double.NaN);
		
		Double Std = Math.sqrt(ticks
				.stream()
				.mapToDouble(x->Math.pow(x.getPrice()-Average,2))
				.average().orElse(Double.NaN));

		Long Count = ticks
				.stream()
				.count();
		
		
		Double Quantile = ticks
				.stream()
				.sorted(comparing(Tick::getPrice))
				.collect(Collectors.toList()).get((int)Math.round(Count*0.05)).getPrice();
				
		
		List<Double> byTimePrice = ticks
				.stream()
				.sorted(comparing(Tick::getTimestamp)
						.reversed())
				.collect(Collectors.mapping(t -> t.getPrice(),Collectors.toList()));

		List<Long> byTime = ticks
				.stream()
				.sorted(comparing(Tick::getTimestamp)
						.reversed())
				.collect(Collectors.mapping(t -> t.getTimestamp(),Collectors.toList()));
		
		
		List<Double> weights1 = (byTime
			.stream()
			.map(x-> (double) 1 - ((double) byTime.get(0)-x)/60000)
			.collect(Collectors.toList()));
		
		Double twap = 
				IntStream.range(0, weights1.size())
						.mapToDouble(i -> byTimePrice.get(i) * weights1.get(i))
						.average().orElse(Double.NaN);

		Double twap2 = 
				IntStream.range(0, weights1.size())
						.mapToDouble(i -> byTimePrice.get(i) * Math.exp(-(1-weights1.get(i))))
						.average().orElse(Double.NaN);

		
		statistics.setAvg(Average);
		
		statistics.setMax(Max);
		
		statistics.setMin(Min);
		
		statistics.setQuantile(Quantile);

		statistics.setVolatility(Std);

		statistics.setCount(Count);
		
		statistics.setMaxDrawdown(maxDD.stream().mapToDouble(Double::doubleValue).min().getAsDouble());
		
		statistics.setTwap(twap);
		
		statistics.setTwap2(twap2);
		
		
		return statistics;
		

	}

	
	

}
