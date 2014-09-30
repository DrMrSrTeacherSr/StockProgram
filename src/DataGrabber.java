import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Scanner;

import utilities.DataPoint;


public class DataGrabber {

	/**
	 * This class will grab the data from Yahoo Finance and put it in a .txt file.
	 * It will parse through the data and separate the data points into DataPoint objects.
	 * 
	 * @author Cam Zawacki
	 */
	
	private String stockTicker;
	private ArrayList<DataPoint> list = new ArrayList<DataPoint>(); 
	
	/**
	 * DataGrabber creates an object with an initial stock ticker
	 * where you can grab the data for that stock and change which stock you are using
	 * 
	 * @param String stockTicker
	 */
	public DataGrabber() {
		stockTicker = "DIA";	
	}
	
	public DataGrabber(String stockTicker) {
		this.stockTicker = stockTicker;	
	}
	
	public ArrayList<DataPoint> run(){
		boolean didGrab = grabData();
		boolean didFormat = formatData(stockTicker);
		if(didGrab)
			System.out.println("Successfully grabbed data");
		else 
			System.out.println("Failed to grab data");
		if(didFormat)
			System.out.println("Successfully formatted data");
		else
			System.out.println("Failed to format data");
		return list;
	}
	
	/**
	 * getList() returns the ArrayList of DataPoint objects
	 */
	public ArrayList<DataPoint> getList() {
		return list;
	}

	/**
	 * getStockTicker() returns the name of the stock ticker
	 */
	public String getStockTicker() {
		return stockTicker;
	}

	/**
	 * setStockTicker() sets the stock ticker
	 * 
	 * @param String stockTicker
	 * 
	 */
	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}

	/**
	 * grabData() grabs the stock data of the stockTicker variable and stores it in a txt file named (stockTicker)StockData.txt
	 */
	@SuppressWarnings("resource")
	private boolean grabData(){
		URL website = null;
		try {
			//website = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + stockTicker + "&d=11&e=28&f=2013&g=d&a=0&b=3&c=1977&ignore=.csv"); //yahoo
			website = new URL("http://www.google.com/finance/historical?q=NYSEARCA%3A"+ stockTicker + "&ei=Qq7AUviZFISwqgHwSA&output=csv"); //google
		} catch (MalformedURLException e) {
			return false;		
		}
		ReadableByteChannel rbc = null;
		try {
			rbc = Channels.newChannel(website.openStream());
		} catch (IOException e1) {
			return false;		
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("" + stockTicker + "StockData.txt");
		} catch (FileNotFoundException e) {
			return false;		
		}
		try {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			return false;		
		}
		return true;
	}
	
	/**
	 * formatData() formats the data into an Arraylist of DataPoint objects
	 */
	@SuppressWarnings("resource")
	public boolean formatData(String ticker){
		int counter = 0;
		
		File file = new File("C:\\Users\\Cam\\Programmin\\Eclipse\\Stocks\\bin\\" + ticker + "StockData.txt");
		DataPoint point = null;
	    try {
	        Scanner scanner = new Scanner(file);
	        scanner.nextLine(); // skip first line
	        
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split(",");
	            
	            parts[0] = dealingWithTheDate(parts[0]);
	            
	            //parts[0] = parts[0].replace('-', ' '); //for yahoo
	            //parts[0] = parts[0].replaceAll("\\s",""); 
	            double open;
	            double high;
	            double low;
	            double close;
	            int volume;
	            
	            try{
	            	open = Double.parseDouble(parts[1]);
	            } catch (Exception e){
	            	open = 0;
	            }
	            try{
	            	high = Double.parseDouble(parts[2]);
	            } catch (Exception e){
	            	high = 0;
	            }
	            try{
	            	low = Double.parseDouble(parts[3]);
	            } catch (Exception e){
	            	low = 0;
	            }
	            try{
	            	close = Double.parseDouble(parts[4]);
	            } catch (Exception e){
	            	close = 0;
	            }
	            try{
	            	volume = Integer.parseInt(parts[5]);
	            } catch (Exception e){
	            	volume = 1;
	            } 
        
	            //point = new DataPoint(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),Integer.parseInt(parts[5]));
	            
	            point = new DataPoint(Integer.parseInt(parts[0]), open, high, low, close,volume,counter);
	            counter++;
	            list.add(point); // 0 is the first point and the last line of text file -2 is the last
	        }
	    }
	    catch (FileNotFoundException e) {
	    	return false;
	    }
		return true;
		
	}
	
	
	/**
	 * dealingWithTheDate() For all your dealing with the date needs
	 */
	private String dealingWithTheDate(String date){
		String dateInt;
		
		String[] dateParts = date.split("-");
		
		String year = "20" + dateParts[2];
		String month;
		String day = dateParts[0];
		
		switch (dateParts[1]) {
			case "Jan" : month = "01"; break;
			case "Feb" : month = "02"; break;
			case "Mar" : month = "03"; break;
			case "Apr" : month = "04"; break;
			case "May" : month = "05"; break;
			case "Jun" : month = "06"; break;
			case "Jul" : month = "07"; break;
			case "Aug" : month = "08"; break;
			case "Sep" : month = "09"; break;
			case "Oct" : month = "10"; break;
			case "Nov" : month = "11"; break;
			case "Dec" : month = "12"; break;
			default : month = "99";
		}
		
		dateInt = year + month + day;
		
		return dateInt;
	}
	
}
