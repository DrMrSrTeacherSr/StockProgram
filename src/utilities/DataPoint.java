package utilities;

import java.util.HashMap;


public class DataPoint {

	/**
	 * This class stores an object of a stock containing the date, opening price, closing price, highest price, lowest price, and volume.
	 */
	
	private HashMap<String, Double> mapOfData = new HashMap<String, Double>();
	private HashMap<Integer, String> mapOfZigs = new HashMap<Integer, String>();
	
	public DataPoint(double date, double openingPrice, double highestPrice, double lowestPrice, double closingPrice, double volume, double id) {
		mapOfData.put("date", date);
		mapOfData.put("open", openingPrice);
		mapOfData.put("high", highestPrice);
		mapOfData.put("low", lowestPrice);
		mapOfData.put("close", closingPrice);
		mapOfData.put("volume", volume);
		mapOfData.put("id", id);
	}
	
	public Double get(String str){
		return mapOfData.get(str);
	}


	public void set(String str,double value) {
		mapOfData.put(str, value);
	}

	public String getZig(int percent) {
		return mapOfZigs.get(percent);
	}

	public void setZig(int percent, String str) {
		mapOfZigs.put(percent, str);
	}
	
}
