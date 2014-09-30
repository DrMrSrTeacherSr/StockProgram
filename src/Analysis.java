import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import utilities.DataPoint;


public class Analysis {

	private ArrayList<DataPoint> rawList = new ArrayList<DataPoint>();	
	private ArrayList<DataPoint> rawVolume;	
	private ArrayList<DataPoint> arithList = new ArrayList<DataPoint>();
	private ArrayList<DataPoint> logList = new ArrayList<DataPoint>();
	
	private HashMap<String, ArrayList<DataPoint>> mapOfLists = new HashMap<String, ArrayList<DataPoint>>();

	public Analysis(ArrayList<DataPoint> rawList) {
		this.rawList = rawList;
		mapOfLists.put("rawList", rawList);
	}
	
	public void createData(){
		generateArithLog();
		mapOfLists.put("arith-basic", arithList);
		mapOfLists.put("log-basic", logList);
		rawVolume = rawList;
		mapOfLists.put("rawVolume", generateUniformVolume(rawVolume, "volume"));	
	}
	
	public ArrayList<DataPoint> get(String str){
		if(!mapOfLists.containsKey(str) || mapOfLists.get(str) == null){
			String[] parts = str.split("-");
			switch (parts[1]){
			
				case "Zigzag": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, zigzag(arithList, Integer.parseInt(parts[2]),"high","low"));
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, zigzag(logList, Integer.parseInt(parts[2]),"high","low"));
					} else {
						mapOfLists.put(str, zigzag(rawList, Integer.parseInt(parts[2]),"high","low"));
					}
					return mapOfLists.get(str);
						
				}
				case "Simple MA": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, simpleMovingAverage(arithList, Integer.parseInt(parts[2]),"close"));
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, simpleMovingAverage(logList, Integer.parseInt(parts[2]),"close"));
					} else {
						mapOfLists.put(str, simpleMovingAverage(rawList, Integer.parseInt(parts[2]),"close"));
					}
					return mapOfLists.get(str);
				}
				case "Exponetial MA": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, exponentialMovingAverage(arithList, Integer.parseInt(parts[2]),"close"));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, exponentialMovingAverage(logList, Integer.parseInt(parts[2]),"close"));
						
					} else {
						mapOfLists.put(str, exponentialMovingAverage(rawList, Integer.parseInt(parts[2]),"close"));
					}
					return mapOfLists.get(str);
				}
				case "Bollinger Bands": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateBollingerBands(arithList, Integer.parseInt(parts[2])));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateBollingerBands(logList, Integer.parseInt(parts[2])));
						
					} else {
						mapOfLists.put(str, generateBollingerBands(rawList, Integer.parseInt(parts[2])));
					}
					return mapOfLists.get(str);
				}
				case "ATR": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateATR(arithList, Integer.parseInt(parts[2])));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateATR(logList, Integer.parseInt(parts[2])));
						
					} else {
						mapOfLists.put(str, generateATR(rawList, Integer.parseInt(parts[2])));
					}
					return mapOfLists.get(str);
				}
				case "CE": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateChandelierExit(arithList, Integer.parseInt(parts[2])));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateChandelierExit(logList, Integer.parseInt(parts[2])));
						
					} else {
						mapOfLists.put(str, generateChandelierExit(rawList, Integer.parseInt(parts[2])));
					}
					return mapOfLists.get(str);
				}case "MACD": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateMACD(arithList,Integer.parseInt(parts[2]),Integer.parseInt(parts[3])));	
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateMACD(logList,Integer.parseInt(parts[2]),Integer.parseInt(parts[3])));		
					} else if(parts[0].equals("raw")){
						mapOfLists.put(str, generateMACD(rawList,Integer.parseInt(parts[2]),Integer.parseInt(parts[3])));		
					}
					return mapOfLists.get(str);
				}
				case "Price Channel": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generatePriceChannels(arithList, Integer.parseInt(parts[2])));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generatePriceChannels(logList, Integer.parseInt(parts[2])));
						
					} else {
						mapOfLists.put(str, generatePriceChannels(rawList, Integer.parseInt(parts[2])));
					}
					return mapOfLists.get(str);
				} 
				case "ADL": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateAccumulationDistributionLine(arithList));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateAccumulationDistributionLine(logList));
						
					} else {
						mapOfLists.put(str, generateAccumulationDistributionLine(rawList));
					}
					return mapOfLists.get(str);
				}
				case "Aroon": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateAroon(arithList, Integer.parseInt(parts[2])));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateAroon(logList, Integer.parseInt(parts[2])));
						
					} else {
						mapOfLists.put(str, generateAroon(rawList, Integer.parseInt(parts[2])));
					}
					return mapOfLists.get(str);
				} 
				case "Vortex": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateVortexIndicator(arithList, Integer.parseInt(parts[2])));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateVortexIndicator(logList, Integer.parseInt(parts[2])));
						
					} else {
						mapOfLists.put(str, generateVortexIndicator(rawList, Integer.parseInt(parts[2])));
					}
					return mapOfLists.get(str);
				} 
				case "Ultimate": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateUltimateOscillator(arithList, Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),Integer.parseInt(parts[4])));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateUltimateOscillator(logList,  Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),Integer.parseInt(parts[4])));
						
					} else {
						mapOfLists.put(str, generateUltimateOscillator(rawList,  Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),Integer.parseInt(parts[4])));
					}
					return mapOfLists.get(str);
				}
				case "Stochastic": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateStochasticOscillator(arithList, Integer.parseInt(parts[2]),Integer.parseInt(parts[3])));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateStochasticOscillator(logList,  Integer.parseInt(parts[2]),Integer.parseInt(parts[3])));
						
					} else {
						mapOfLists.put(str, generateStochasticOscillator(rawList,  Integer.parseInt(parts[2]),Integer.parseInt(parts[3])));
					}
					return mapOfLists.get(str);
				}
				case "Relative Strength": {
					if(parts[0].equals("arith")){
						mapOfLists.put(str, generateRelativeStrengthIndex(arithList, Integer.parseInt(parts[2])));
						
					}else if(parts[0].equals("log")){
						mapOfLists.put(str, generateRelativeStrengthIndex(logList,  Integer.parseInt(parts[2])));
						
					} else {
						mapOfLists.put(str, generateRelativeStrengthIndex(rawList,  Integer.parseInt(parts[2])));
					}
					return mapOfLists.get(str);
				}
				default: {
					if(parts[0].equals("arith")){
						return mapOfLists.get("arithList");
					}else if(parts[0].equals("log")){
						return mapOfLists.get("logList");
					} else{
						return mapOfLists.get("rawList");
					}
					
				}
			}
		} else {
			return mapOfLists.get(str);
		}
	}


	private void generateArithLog(){
		double maxHigh;
		double maxLow;
		maxHigh = rawList.get(0).get("high");
		maxLow = rawList.get(0).get("low");
		double maxDiff =  maxHigh - maxLow;
			
		double fixedHeight;
		double fixedOpen;
		double fixedClose;
		double fixedLow;
		
		for(int i = 0; i <  rawList.size(); i++){
			double high = rawList.get(i).get("high");
			if(high > maxHigh)
				maxHigh = high;
			if(high < maxLow){
				maxLow = high;
			}
			if (maxHigh - maxLow > maxDiff)
				maxDiff = maxHigh - maxLow ;
		}
		
		
		for(int i = 0; i < rawList.size(); i++){
			
			double high = rawList.get(i).get("high");
			double open = rawList.get(i).get("open");
			double close = rawList.get(i).get("close");
			double low = rawList.get(i).get("low");
			double logBase = Math.E;
	
			
				fixedHeight =  (Math.log(1 + (logBase - 1) * (high - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
				fixedOpen =  (Math.log(1 + (logBase - 1) * (open - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
				fixedClose =  (Math.log(1+ (logBase - 1) * (close - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
				fixedLow =  (Math.log(1+ (logBase - 1) * (low - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
				logList.add(new DataPoint(rawList.get(i).get("date"),fixedOpen,fixedHeight,fixedLow,fixedClose,rawList.get(i).get("volume"),i));
			
				fixedHeight = ((high - maxLow)/(maxHigh - maxLow));
				fixedOpen = ((open - maxLow)/(maxHigh - maxLow));
				fixedClose =  ((close - maxLow)/(maxHigh - maxLow));
				fixedLow =((low - maxLow)/(maxHigh - maxLow));
				arithList.add(new DataPoint(rawList.get(i).get("date"),fixedOpen,fixedHeight,fixedLow,fixedClose,rawList.get(i).get("volume"),i));
				
		}

	}
	
	private void generateUniformList(ArrayList<DataPoint> list, String str){
		double maxValue = list.get(0).get(str);
		double minValue = list.get(0).get(str);
		int nullCounter = 0;
		
		for(int i = 0; i < list.size(); i++){
			double value;
			if(list.get(i).get(str) == null){
				nullCounter ++;
			} else {
				value = list.get(i).get(str);
				if(Math.abs(value) > Math.abs(maxValue))
					maxValue = Math.abs(value);
				if(value < minValue)
					minValue = value;
			}
		}
		
		double height;
		for(int i = 0; i < list.size() - nullCounter; i++){
			double value = list.get(i).get(str);
			if(value != 0)	{
				height =  (value - minValue)/(maxValue - minValue);
			} else {
				height = 0;
			}
			list.get(i).set(str,height);
		}
	}
	
	private void generateUniformList(ArrayList<DataPoint> list, String str, double min, double max){
		double maxValue = max;
		double minValue = min;
		
		double height;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).get(str) != null){
				double value = list.get(i).get(str);
				if(value != 0)	{
					height =  (value - minValue)/(maxValue - minValue);
				} else {
					height = 0;
				}
				list.get(i).set(str,height);
			}
		}
	}
	
	private ArrayList<DataPoint> generateUniformVolume(ArrayList<DataPoint> list, String str){  //					FIX THIS
		ArrayList<DataPoint> modifiedValue =  new ArrayList<DataPoint>();
		double maxVolume = list.get(0).get(str);
		
		for(int i = 0; i < list.size(); i++){
			double volume = list.get(i).get(str);
			if(Math.abs(volume) > Math.abs(maxVolume))
				maxVolume = Math.abs(volume);
		}
		
		double height;
		for(int i = 0; i < list.size(); i++){
			double volume = list.get(i).get(str);
			if(volume != 0)	{
				height =  volume/maxVolume;
			} else {
				height = 0;
			}
			modifiedValue.add(new DataPoint(list.get(i).get("date"),list.get(i).get("open"),list.get(i).get("high"),list.get(i).get("low"),list.get(i).get("close"),height,list.get(i).get("id")));
		}
		return modifiedValue;
		
	}
	
	public double getMax(String str1,String str2){
		double max = 0;
		ArrayList<DataPoint> modifiedValue = get(str1);
		if (modifiedValue.get(0).get(str2) != null)
			 max = modifiedValue.get(0).get(str2);
		
		
		for(int i = 0; i < modifiedValue.size(); i++){
		if (modifiedValue.get(i).get(str2) != null){
			double value = modifiedValue.get(i).get(str2);
			if(value >= max)
				max = Math.abs(value);
			}
		}
		return max;
	}
	
	public double getMin(String str1,String str2){
		double minValue = 1000000;
		ArrayList<DataPoint> modifiedValue = get(str1);
		
		if (modifiedValue.get(0).get(str2) != null)
			minValue = modifiedValue.get(0).get(str2);
		
		for(int i = 0; i < modifiedValue.size(); i++){
			if (modifiedValue.get(i).get(str2) != null){
				double value = modifiedValue.get(i).get(str2);
				if(value < minValue)
					minValue = value;
			}
		}
		return minValue;
	}
		
	private void generateTrueRange(ArrayList<DataPoint> list){
		
		double a = 0;
		double b = 0;
		double c = 0;
		
		list.get(list.size() - 1).set("TR", list.get(list.size() - 1).get("high") - list.get(list.size() - 1).get("low"));	
		for (int i = list.size() - 2; i >= 0  ; i--){
			a = Math.abs(list.get(i).get("high") - list.get(i + 1).get("close"));
			b = Math.abs(list.get(i).get("low") - list.get(i + 1).get("close"));
			c = Math.abs(list.get(i).get("high") - list.get(i).get("low"));
			if(a > b && a > c){
				list.get(i).set("TR", a);
			} else if (b >  a && b > c){
				list.get(i).set("TR", b);
			} else  {
				list.get(i).set("TR", c);
			}
			
		}
	}
	
	private ArrayList<DataPoint> zigzag(ArrayList<DataPoint> list, int percent, String upperStr, String lowerStr) {
		int j = 0;
		boolean raising = false;
		int previousTurningPoint, nextTurningPoint;
		double newPercent = percent;
		

		do{
			j++; // Finds start
		} while(list.get(list.size() - j).get(upperStr) == null);


		previousTurningPoint = list.size() - j;
		nextTurningPoint = previousTurningPoint;
		
		list.get(previousTurningPoint).set("ZIG" + "-" + percent, 1);
		list.get(previousTurningPoint).setZig(percent,upperStr);	

		
		
//		double possibleNewTurningPoint = 100.0 * ((list.get(lastTPId).get(upperStr) - list.get(i).get(upperStr)) / list.get(lastTPId).get(upperStr));
		
		
		for(int i = previousTurningPoint - 1; i >= 0; i--){
			
			if(raising){
				
				double possibleNextTurningPointPercent = 100.0 * ((list.get(i).get(upperStr) - list.get(previousTurningPoint).get(lowerStr)) / list.get(previousTurningPoint).get(lowerStr));
				double possiblePreviousTurningPoint = 100.0 * ((list.get(i).get(lowerStr) - list.get(nextTurningPoint).get(upperStr)) / list.get(nextTurningPoint).get(upperStr));
				
				list.get(i).set("ZIG" + "-" + percent, 0);
				list.get(i).setZig(percent,upperStr);
				
				if(possibleNextTurningPointPercent >= newPercent){
					newPercent = possibleNextTurningPointPercent;
					nextTurningPoint = i;
				}
				if (possiblePreviousTurningPoint <= -percent){
					raising = !raising;
					
					System.out.println(nextTurningPoint);
					
					list.get(nextTurningPoint).set("ZIG" + "-" + percent, 1);
					list.get(nextTurningPoint).setZig(percent,upperStr);
					
					previousTurningPoint = nextTurningPoint;
					nextTurningPoint = i;
					newPercent = percent;
				}
				
			} else {
			
				double possibleNextTurningPointPercent = 100.0 * ((list.get(i).get(lowerStr) - list.get(previousTurningPoint).get(upperStr)) / list.get(previousTurningPoint).get(upperStr));
				double possiblePreviousTurningPoint = 100.0 * ((list.get(i).get(upperStr) - list.get(nextTurningPoint).get(lowerStr)) / list.get(nextTurningPoint).get(lowerStr));
				
				System.out.println(i);
				
				list.get(i).set("ZIG" + "-" + percent, 0);
				list.get(i).setZig(percent,lowerStr);
				
				if(possibleNextTurningPointPercent <= -newPercent){
					newPercent = Math.abs(possibleNextTurningPointPercent);
					nextTurningPoint = i;
				} 
				if (possiblePreviousTurningPoint >= percent){
					raising = !raising;
					
					list.get(nextTurningPoint).set("ZIG" + "-" + percent, 1);
					list.get(nextTurningPoint).setZig(percent,lowerStr);
					
					//System.out.println(i);
					
					previousTurningPoint = nextTurningPoint;
					nextTurningPoint = i;
					newPercent = percent;
				}
				
				
				
				
			}
		}

		
		String currentPoint;
		if(raising){
			currentPoint = upperStr;
		} else {
			currentPoint = lowerStr;
		}
		list.get(0).set("ZIG" + "-" + percent, 1);
		list.get(0).setZig(percent,currentPoint);
		
		
		return list;
	}
	

	
	/*
	 * Let's assume that it rose 10% or more. Now you keep extending the
	line forward until it falls 10% from the highest high price in the
	"rising period". When this happens, connect that highest high with the
	new 10% lower price. This could result in erasing a part of the
	previous line, because you don't know that the highest high is
	actually a highest high until you see price drop 10%.
	Repeat this procedure .
	 */
	
	private ArrayList<DataPoint> simpleMovingAverage(ArrayList<DataPoint> list, int timeFrame, String str){
		
		for(int i = 0; i < list.size() - timeFrame;i++){
			if(list.get(i).get(str) != null){
				double sum = 0;
				for(int j = 0; j < timeFrame; j++){
					if(list.get(i + j).get(str) != null)
						sum += list.get(i + j).get(str);

				}
			
				double average = sum/((double)timeFrame);
				list.get(i).set("Simple MA" + "-" + timeFrame + "-" + str, average);
			}
		}
		return list;
	}
	
	private ArrayList<DataPoint> exponentialMovingAverage(ArrayList<DataPoint>list, int timeFrame, String str){
		if(timeFrame > 0){
			simpleMovingAverage(list, timeFrame, str);
		
			double multiplyer = ((double)2)/(timeFrame + 1);
			int j = 1;
			
			while(list.get(list.size() - j - timeFrame).get("Simple MA" + "-" + timeFrame + "-" + str) == null){
				j++;
			}
			double yesterday = list.get(list.size() - j - timeFrame).get("Simple MA" + "-" + timeFrame + "-" + str);
		
			for(int i = list.size() - j - timeFrame; i >= 0; i--){
				double value = (list.get(i).get(str) - yesterday) * multiplyer + yesterday;

				list.get(i).set("Exponetial MA" + "-" + timeFrame + "-" + str, value);
				yesterday = value;
			
			}
		
			return list;
		} else{
			return null;
		}
		
	}
	
	private ArrayList<DataPoint> generateMACD(ArrayList<DataPoint>list,int num1, int num2){
		if(num1 > 0 && num2 > 0){
			exponentialMovingAverage(list, num1,"close");
			exponentialMovingAverage(list, num2,"close");
			int low;
			int high;
			if(num1 < num2){
				low = num1;
				high = num2;
			} else {
				low = num2;
				high = num1;
			}
			double max = -1000;
			double min = 1000;
			for(int i = 0; i < list.size() - high;i++){
				double newValue = list.get(i).get("Exponetial MA" + "-" + low + "-" +  "close") - list.get(i).get("Exponetial MA" + "-" + high + "-" +  "close");
				if(newValue > max)
					max = newValue;
				if(newValue < min)
					min = newValue;
				list.get(i).set("MACD" + "-" + low + "-" + high +"-null", newValue);
				list.get(i).set("MACD" + "-" + "RAW" + "-" + low + "-" + high, newValue);

			} 
			exponentialMovingAverage(list, 9,"MACD" + "-" + low + "-" + high+"-null");
			for(int i = 0; i < list.size() - high - 9;i++){
				list.get(i).set("MACD" + "-" + low + "-" + high +"-Histogram",
						list.get(i).get("MACD" + "-" + low + "-" + high+"-null") - 
						list.get(i).get("Exponetial MA" + "-" + "9" + "-" + "MACD" + "-" + low + "-" + high+"-null"));
				
				

			} 
		
			generateUniformList(list, "MACD" + "-" + low + "-" + high+"-null");
			generateUniformList(list, "Exponetial MA" + "-" + "9" + "-" + "MACD" + "-" + low + "-" + high + "-null",min,max);
			for(int i = 0; i < list.size() - high - 9;i++){
				list.get(i).set("MACD" + "-" + low + "-" + high +"-Histogram",
						list.get(i).get("MACD" + "-" + low + "-" + high+"-null") - 
						list.get(i).get("Exponetial MA" + "-" + "9" + "-" + "MACD" + "-" + low + "-" + high+"-null"));
				
				list.get(i).set("MACD" + "-" + low + "-" + high+"-EMA",list.get(i).get("Exponetial MA" + "-" + "9" + "-" + "MACD" + "-" + low + "-" + high+"-null"));
				
			} 
			return list;
		} else {
			return null;
		}
	}
	
	private ArrayList<DataPoint> generateStandardDeviation(ArrayList<DataPoint> list,int timeFrame,String str){ 
		
		for(int i = 0; i < list.size() - 1 - timeFrame;i++){
			double average = 0;
			for(int j = 0; j < timeFrame;j++){
				average += list.get(i + j).get(str);
			}
			average /= timeFrame;
			double newAverage = 0;
			for(int j = 0; j < timeFrame;j++){
				newAverage += Math.pow(list.get(i + j).get(str) - average, 2);
			}
			newAverage /= timeFrame;
			newAverage = Math.sqrt(newAverage);
			list.get(i).set("SD" + "-" + timeFrame,newAverage);
		}
		
		return list;
	}

	private ArrayList<DataPoint> generateBollingerBands(ArrayList<DataPoint> list,int timeFrame){
		generateStandardDeviation(list,timeFrame,"close");
		simpleMovingAverage(list,timeFrame,"close");
		double sd = 0;
		
		for(int i = 0; i < list.size() - 1 - timeFrame;i++){
			sd = list.get(i).get("SD" + "-" + timeFrame);
			list.get(i).set("Bollinger Bands" + "-" + timeFrame + "-mid",list.get(i).get("Simple MA" + "-" + timeFrame + "-" + "close") );
			list.get(i).set("Bollinger Bands" + "-" + timeFrame + "-high", list.get(i).get("Simple MA" + "-" + timeFrame + "-" + "close") + 2*sd );
			list.get(i).set("Bollinger Bands" + "-" + timeFrame + "-low",list.get(i).get("Simple MA" + "-" + timeFrame + "-" + "close") - 2*sd );	
		}		
		return list;
	}
	
	private ArrayList<DataPoint> generateATR(ArrayList<DataPoint> list,int timeFrame){
		
		generateTrueRange(list);

		double firstAverage = 0;
		for(int i = list.size() - 1; i > list.size() - 1 - timeFrame; i--){
			
			firstAverage += list.get(i).get("TR");
			
		}
		firstAverage /= (double)timeFrame;
		
		double averageTrueValue = 0;
		for(int i  = list.size() - 1 - timeFrame; i >= 0; i--){
			averageTrueValue = (firstAverage*(timeFrame - 1) + list.get(i).get("TR"))/timeFrame;
			list.get(i).set("ATR" + "-" + timeFrame +"-null", averageTrueValue);
			list.get(i).set("ATR" + "-" + "RAW" + "-" + timeFrame, averageTrueValue);

			firstAverage = averageTrueValue;
		}	
		
		
		generateUniformList(list,"ATR" + "-" + timeFrame+"-null");
		
		return list;
	}
	
	private ArrayList<DataPoint> generateChandelierExit(ArrayList<DataPoint> list,int timeFrame){
		generateATR(list,timeFrame);

	
		for(int i = list.size() - 1 - timeFrame ; i >= 0 ;i--){
			double high = list.get(i).get("high");
			double low = list.get(i).get("low");
			for(int j = 1; j < timeFrame; j++){
				if(high < list.get(i + j).get("high"))
					high = list.get(i + j).get("high"); 
				if (low > list.get(i + j).get("low"))
					low = list.get(i + j).get("low");
			}
			
			double longCE = high - list.get(i).get("ATR" + "-" + "RAW" + "-" + timeFrame) * 3;
			double shortCE = low + list.get(i).get("ATR" + "-" + "RAW" + "-" + timeFrame) * 3;;
			
			list.get(i).set("CE" + "-" + timeFrame+ "-long",longCE);
			list.get(i).set("CE" + "-" + timeFrame + "-short",shortCE);
		}
			
		return list;
		
	}
	
	private ArrayList<DataPoint> generatePriceChannels(ArrayList<DataPoint> list,int timeFrame){

		for(int i = list.size() - 1 - timeFrame; i >= 0 ;i--){
			double max = list.get(i).get("high");
			double min = list.get(i).get("low");
			
			for(int j = 0; j < timeFrame; j++){
				if(list.get(i + j).get("high") > max){
					max = list.get(i + j).get("high");
				}
				if(list.get(i + j).get("low") < min){
					min = list.get(i + j).get("low");
				}	
				
			}
			list.get(i).set("Price Channel" + "-" + timeFrame +"-high", max);
			list.get(i).set("Price Channel" + "-" + timeFrame + "-low", min);
			list.get(i).set("Price Channel" + "-" + timeFrame + "-mid", ((max + min)/2));
		}

		return list;
	}
	
	private ArrayList<DataPoint> generateAccumulationDistributionLine(ArrayList<DataPoint> list){
		
		double previousADL = 0;
		
		for(int i = list.size() - 1; i >= 0; i --) {
			double mFM = ((list.get(i).get("close") - list.get(i).get("low")) - (list.get(i).get("high") - list.get(i).get("close")))/ (list.get(i).get("high") - list.get(i).get("low"));
			double mFV = mFM * list.get(i).get("volume");
			double aDL = mFV + previousADL;
			previousADL = aDL;
			list.get(i).set("ADL-null", aDL);
			list.get(i).set("ADL" + "-" + "RAW", aDL);
		}	
		
		generateUniformList(list, "ADL-null");
		return list;
	}
	
	private ArrayList<DataPoint> generateAroon(ArrayList<DataPoint> list, int timeFrame){
		
		double lastHigh;
		double lastLow;
		double lastHighDay;
		double lastLowDay;
		
		for(int i = list.size() - 1 - (int) timeFrame; i >= 0; i--) {
			lastHigh = list.get(i).get("high");
			lastHighDay = i;
			lastLow = list.get(i).get("low");
			lastLowDay = i;
			for(int j = 1; j < timeFrame; j++){
				if(list.get(i+j).get("high") > lastHigh){
					lastHighDay = i+j;
					lastHigh = list.get(i+j).get("high");
				}
				if(list.get(i+j).get("low") < lastLow){
					lastLowDay = i+j;
					lastLow = list.get(i+j).get("low");
				}
			}		
			double aU = ((timeFrame - (lastHighDay - i))/(double)timeFrame) * 100.0;
			double aD = ((timeFrame - (lastLowDay - i))/(double)timeFrame) * 100.0;
			list.get(i).set("Aroon"+ "-" + timeFrame + "-Up", aU);
			list.get(i).set("Aroon"+ "-" + timeFrame + "-Down", aD);
			list.get(i).set("Aroon"+ "-" + timeFrame + "-Differance", (aU - aD));
		}	
		
		generateUniformList(list, "Aroon"+ "-" + timeFrame + "-Up");
		generateUniformList(list, "Aroon"+ "-" + timeFrame + "-Down");
		
		for(int i = list.size() - 1 - (int) timeFrame; i >= 0; i--) {
			
			list.get(i).set("Aroon"+ "-" + timeFrame + "-Differance",(list.get(i).get( "Aroon"+ "-" + timeFrame + "-Up") - list.get(i).get("Aroon"+ "-" + timeFrame + "-Down")));
		}
		
		return list;
	}
	
	private ArrayList<DataPoint> generateVortexIndicator(ArrayList<DataPoint> list, int timeFrame){
		ArrayList<Double> tRValues = new ArrayList<Double>();
		
		int size = list.size() - 1;
		
		double a = 0;
		double b = 0;
		double c = 0;
		
		tRValues.add(list.get(0).get("high") - list.get(0).get("low"));
		
		for (int i = 1; i < size; i++){
			a = Math.abs(list.get(i).get("high") - list.get(i + 1).get("close"));
			b = Math.abs(list.get(i).get("low") - list.get(i + 1).get("close"));
			c = Math.abs(list.get(i).get("high") - list.get(i).get("low"));
			if(a > b && a > c){
				tRValues.add(a);
			} else if (b >  a && b > c){
				tRValues.add(b);
			} else  {
				tRValues.add(c);
			}
			
		}
		
		for(int i = list.size() - 1 - timeFrame; i >= 0; i--){
			double pVMSum = 0;
			double mVMSum = 0;
			double tRSum = 0;	
			
			for(int j = timeFrame - 1; j >= 0; j--){
				double pVM = list.get(i+j).get("high") - list.get(i+j+1).get("low");
				double mVM = list.get(i+j).get("low") - list.get(i+j+1).get("high");
				
				pVMSum += Math.abs(pVM);
				mVMSum += Math.abs(mVM);
				
				tRSum += tRValues.get(j + i);
			}
			double nPVM = pVMSum/tRSum;
			double nMVM = mVMSum/tRSum;
			list.get(i).set("Vortex"+"-"+timeFrame + "-Positive", nPVM);
			list.get(i).set("PVM"+"-"+"RAW" + "-"+timeFrame , nPVM);
			list.get(i).set("Vortex"+"-"+timeFrame + "-Negative", nMVM);
		}
		
		generateUniformList(list, "Vortex"+"-"+timeFrame + "-Positive");
		generateUniformList(list, "Vortex"+"-"+timeFrame + "-Negative");
		
		return list;
	}
	
	private ArrayList<DataPoint> generateUltimateOscillator(ArrayList<DataPoint> list, int num1, int num2, int num3){
		
		double average1 = 0;
		double average2 = 0;
		double average3 = 0;
		
		for(int i = list.size() - 1 - num3; i >=0; i--){
			double bp = 0;
			double tr = 0;
			
			for(int j = 0; j < num3; j++){
				if(list.get(i+j).get("low") > list.get(i+j+1).get("close")){
					bp += list.get(i+j).get("close") - list.get(i+j+1).get("close");
				} else {
					bp += list.get(i+j).get("close") - list.get(i+j).get("low");
				}
				if(list.get(i+j).get("high") > list.get(i+j+1).get("close")){
					if(list.get(i+j).get("low") > list.get(i+j+1).get("close")){
						tr += list.get(i+j).get("high") - list.get(i+j+1).get("close");
					} else {
						tr += list.get(i+j).get("high") - list.get(i+j).get("low");
					}
				} else {
					if(list.get(i+j).get("low") > list.get(i+j+1).get("close")){
						tr += list.get(i+j+1).get("close") - list.get(i+j+1).get("close");
					} else {
						tr += list.get(i+j+1).get("close") - list.get(i+j).get("low");
					}
				}
				
				if(j == num1 - 1){
					average1 = bp/tr;
				}
				
				if(j == num2 - 1){
					average2 = bp/tr;
				}
				
				if(j == num3 - 1){
					average3 = bp/tr;
				}	
			}
			double uo = 100 * ((4 * average1) + (2 * average2) + average3)/(7.0);
			list.get(i).set("Ultimate" + "-" + num1 + "-" + num2 + "-" + num3 + "-null", uo);
			list.get(i).set("Ultimate" + "-" + "RAW" + "-" + num1 + "-" + num2 + "-" + num3, uo);

			
		}
		
		generateUniformList(list,"Ultimate" + "-" + num1 + "-" + num2 + "-" + num3 + "-null",0,100);

		
		return list;
	}
		
	private ArrayList<DataPoint> generateStochasticOscillator(ArrayList<DataPoint> list, int timeFrame, int sma){

		
		
		for(int i = list.size() - 1 - timeFrame; i >= 0; i--){
			double k = 0;
			
			double high = list.get(i).get("high");
			double low = list.get(i).get("low");
			
			for(int j = 0; j < timeFrame; j++){
				if(high < list.get(i+j).get("high")){
					high = list.get(i+j).get("high");
				}
				if(low > list.get(i+j).get("low")){
					low = list.get(i+j).get("low");
				}	
			}
			k = (list.get(i).get("close") - low)/(high - low) * 100;
			list.get(i).set("SOK-fast" + "-" + timeFrame,k);

		}
		simpleMovingAverage(list, sma, "SOK-fast" + "-" + timeFrame);
		
		for(int i = list.size() - 1 - timeFrame - sma; i >= 0; i--){
			list.get(i).set("SOK-full" + "-" + timeFrame  + "-" + sma, list.get(i).get("Simple MA" +"-" + sma + "-" + "SOK-fast" + "-" + timeFrame));
		}
		simpleMovingAverage(list, sma, "SOK-full" + "-" + timeFrame  + "-" + sma);

		
		generateUniformList(list,"SOK-fast" + "-" + timeFrame,0,100);
		generateUniformList(list,"Simple MA" +"-" + sma + "-" + "SOK-fast" + "-" + timeFrame,0,100);
		
		generateUniformList(list,"SOK-full" + "-" + timeFrame + "-" + sma,0,100);
		generateUniformList(list,"Simple MA" +"-" + sma + "-" + "SOK-full" + "-" + timeFrame + "-" + sma,0,100);
		
		return list;
	}
	
	private ArrayList<DataPoint> generateRelativeStrengthIndex(ArrayList<DataPoint> list, int timeFrame){
		double averageGain;
		double averageLoss;
		
		double gainsI = 0;
		double lossesI = 0;
		int I = list.size() - 1 - timeFrame;
		
		for(int j = 0; j < timeFrame; j++){
			if(list.get(I + j).get("close") > list.get(I + j + 1).get("close")){
				gainsI += list.get(I + j).get("close") - list.get(I + j + 1).get("close");
			} else {
				lossesI += list.get(I + j + 1).get("close") - list.get(I + j).get("close");
			}
		}
		averageGain = gainsI/timeFrame;
		averageLoss = lossesI/timeFrame;
		
		
		for(int i = I-1; i >= 0; i--){
			double currentGain = 0;
			double currentLoss = 0;
			if(list.get(i).get("close") > list.get(i+1).get("close")){
				currentGain = list.get(i).get("close") - list.get(i+1).get("close");
			} else {
				currentLoss = list.get(i+1).get("close") - list.get(i).get("close");
			}
				
			averageGain = (averageGain * (timeFrame - 1) + currentGain)/timeFrame;
			averageLoss = (averageLoss * (timeFrame - 1) + currentLoss)/timeFrame;
			
			double rs = averageGain/averageLoss;
			double rsi = 100 - (100/(1+rs));
			list.get(i).set("Relative Strength" + "-" + timeFrame + "-null", rsi);	
		}
				generateUniformList(list, "Relative Strength" + "-" + timeFrame + "-null",0,100);	
		return list;
	}
	
//	private ArrayList<DataPoint> generateSimpleMovingEnvelope(ArrayList<DataPoint> list,int timeFrame,double percent){
//	simpleMovingAverage(list,timeFrame,"close");
//	ArrayList<DataPoint> newList = new ArrayList<DataPoint>();
//	double max = getMax("arithList", "high");
//	double min = getMin("arithList", "high");
//
//	for(int i = 0; i < sMA.size() - timeFrame; i++){
//		newList.add(new DataPoint(sMA.get(i).get("date"), sMA.get(i).get("open"),(sMA.get(i).get("close") + (.1*(max/min)*sMA.get(i).get("close"))), (sMA.get(i).get("close") - (.1*(max/min)*sMA.get(i).get("close"))), sMA.get(i).get("close"), sMA.get(i).get("volume"), i));
//	}
//	
//
//	return newList;	
//}
	
//	ArrayList<DataPoint> newList = new ArrayList<DataPoint>();

//	newList.add(new DataPoint(list.get(i).get("date"), list.get(i).get("open"),list.get(i).get("high"), list.get(i).get("low"), list.get(i).get("close"), list.get(i).get("volume"), i));

	
	
}
