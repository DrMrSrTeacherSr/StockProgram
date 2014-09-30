import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PFont;
import utilities.Button;
import utilities.ButtonData;
import utilities.DataPoint;
import utilities.EditableTextBox;
import utilities.HoverBox;
import utilities.TextBox;

public class Display extends PApplet{
	private static final long serialVersionUID = 1L;
	
	private Analysis analysis;
	
	private int bottomBoxThickness;
	private int middleBoxThickness;


	private int sideThickness = 10;
	private int boxSeperation = 5;
	private int sideBoxWidth;
	private int volumeThickness; 
	private int box1X, box1Y, box1H, box1W,box2X, box2Y, box2H, box2W,box3X, box3Y, box3H, box3W, box4X, box4Y, box4H, box4W,box5X, box5Y, box5H, box5W;
	HashMap<String, Analysis> map = new HashMap<String, Analysis>();
	private float stockRange;
	
	private String currentOverlay = "none";
	private String currentIndicator = "none";
	private String currentGraph = "arith";
	
	private String indicatorOverlayValues;
	
	//private HashMap<String,ButtonData> buttons = new HashMap<String,ButtonData>();
//	private HashMap<String,EditableTextBox[]> variableValues= new HashMap<String, EditableTextBox[]>();

	
	private Button logButton;
	private Button overlayButton;
	private Button indicatorButton;
	
	private EditableTextBox newStockBox;
	private String ticker = "DIA";
	private TextBox stockTicker;
	private TextBox currentOverlayValue;
	private TextBox currentIndicatorValue;
	private String lastTicker = "DIA";
	
	
	public void setup() {
		changeShownStock(ticker);

		
		size(1300, 800);
		background(80,87,79);
		  
		bottomBoxThickness = (int) (height * 100.0/800);
		middleBoxThickness = (int) (height * 200.0/800);
		stockRange = (float) (height * 300.0/800);
		sideBoxWidth = (int) (width * 50.0/1300);
		volumeThickness = (int) (height * 100.0/800);
		  
		box1X = sideThickness;
		box1Y = sideThickness;
		box1W = width - 2 * sideThickness - sideBoxWidth;
		box1H = height - sideThickness - middleBoxThickness - bottomBoxThickness; 
		    
		box2X = sideThickness;
		box2Y = height - middleBoxThickness - bottomBoxThickness + boxSeperation;
		box2W = width - 2 * sideThickness - sideBoxWidth;
		box2H = middleBoxThickness  - boxSeperation; 
		    
		box3X = sideThickness;
		box3Y = height - bottomBoxThickness + boxSeperation;
		box3W = width - 2 * sideThickness;
		box3H = bottomBoxThickness - sideThickness - boxSeperation;
		
		box4X = width - 2 *  sideThickness - sideBoxWidth;
		box4Y = sideThickness;
		box4W = sideBoxWidth + sideThickness;
		box4H = height - sideThickness - middleBoxThickness - bottomBoxThickness; 
		    
		box5X = width - 2 *  sideThickness - sideBoxWidth;
		box5Y = height - middleBoxThickness - bottomBoxThickness + boxSeperation;
		box5W = sideBoxWidth + sideThickness;
		box5H = middleBoxThickness  - boxSeperation; 

		
		logButton = new Button(this,setUpArithLog(),box3X + 70,box3Y + 20,75,35,20);
		overlayButton = new Button(this,setUpOverlays(),box3X + 250,box3Y + 5,75,35,10);
		indicatorButton = new Button(this,setUpIndicators(),box3X + 250 ,box3Y + 45,75,35,10);
		
		newStockBox = new EditableTextBox(this,box3X + 10,box3Y + 20, 50, 20, false, 14, 4,"stock");
	
		stockTicker = new TextBox(this,box1X + 10, box1Y + 2,-1,18,true,ticker + ": " + analysis.get("rawList").get(0).get("close") + indicatorOverlayValues,16); 
		currentOverlayValue = new TextBox(this,box3X + 150,box3Y + 5,75,35,true,"test1",16);
		currentIndicatorValue = new TextBox(this,box3X + 150,box3Y + 45,75,35,true,"test2",16);


	}

	
	
	private ButtonData setUpArithLog(){
		ArrayList<String> arithLogNames = new ArrayList<String>();
		arithLogNames.add("Arithmatic");	
		arithLogNames.add("Logarithmic");
		
		ArrayList<int[]> arithLogVars = new ArrayList<int[]>();
		arithLogVars.add(new int[0]);
		arithLogVars.add(new int[0]);
		
		ArrayList<Color> arithLogColors = new ArrayList<Color>();
		arithLogColors.add(new Color(11,95,126));
		arithLogColors.add(new Color(75,155,201));

		return new ButtonData(arithLogNames,arithLogVars,arithLogColors);
	}
	
	private ButtonData setUpOverlays(){
		ButtonData data = new ButtonData("None",new int[0],new Color(255,255,255));
		data.add("Simple MA",new int[1],new Color(0,160,176));
		data.add("Exponetial MA",new int[1],new Color(204,51,63));
		data.add("Zigzag",new int[1],new Color(235,104,65));
		data.add("Bollinger Bands",new int[1],new Color(237,201,81));
		data.add("CE",new int[1],new Color(127,184,251));
		data.add("Price Channel",new int[1],new Color(254,213,247));
		
		
		return data;
	}
	
	private ButtonData setUpIndicators(){
		ButtonData data = new ButtonData("None",new int[0],new Color(255,255,255));
		data.add("MACD",new int[2],new Color(11,95,126));
		data.add("ATR",new int[1],new Color(14,206,74));
		data.add("ADL",new int[0],new Color(14,6,174));
		data.add("Aroon",new int[1],new Color(100,136,14));
		data.add("Vortex",new int[1],new Color(100,100,100));
		data.add("Ultimate",new int[3],new Color(234,123,55));
		data.add("Stochastic",new int[3],new Color(142,123,155));
		data.add("Relative Strength",new int[1],new Color(2,163,175));
		
		return data;
	}
		
	public void changeShownStock(String ticker){
		if(!map.containsKey(ticker)){	
			analysis = new Analysis(new DataGrabber(ticker).run());	
			if(analysis.get("rawList").size() == 0){
				System.out.println("Stock with ticker: "+ ticker + " Does Not Exist");
				ticker = lastTicker;
				changeShownStock(ticker);
				analysis = map.get(ticker);
		    } else {
			System.out.println("New Stock Loaded: " + ticker );
			lastTicker = ticker;
			analysis.createData();
			map.put(ticker,analysis);
		    }
		} else {	
			System.out.println("Old Stock Loaded: " + ticker );
			analysis = map.get(ticker);
		}
		this.ticker = ticker;	
		if(stockTicker != null)
			stockTicker.newText(ticker + ": " + analysis.get("rawList").get(0).get("close") + indicatorOverlayValues);
	}
	
	public void keyPressed() {
		
		 overlayButton.keyPressed();
		 indicatorButton.keyPressed();
		 String newStock = newStockBox.keyPressed();
		 if(key == ENTER && newStock != null  && newStock != ""){
			 changeShownStock(newStock);
		 }
		 
	}
	
	public void draw() {
		background(80,87,79);
		drawBackground();
		drawText();
		drawButtons();
		drawOverlay();
		drawIndicator();
		drawHistograms();
		//drawScaleLines();
		
	}
	
	private void drawScaleLines() {
		//Stock
		drawScaleLines(box1X + 10,        box1Y + 20,
					   box1W + box4W - 10,box1Y + 20,
					   box1X + 10,        box1Y + 20 + stockRange ,
					   box1W + box4W - 10,box1Y + 20 + stockRange,
					   10,true,analysis.getMax("rawList","high"),analysis.getMin("rawList","high"));
		//Volume
		drawScaleLines(box1X + 10,        box1Y + box1H - 10 - volumeThickness,
				       box1W + box4W - 10,box1Y + box1H - 10 - volumeThickness,
				       box1X + 10,        box1Y + box1H - 10,
				       box1W + box4W - 10,box1Y + box1H - 10,
				       3,false,analysis.getMax("rawList","volume"),0);
		
		if(indicatorButton.getCurrentCycle() == 1){
			drawScaleLines(box2X + 10,    box2Y + 10,
					   box2W + box4W - 10,box2Y + 10,
					   box2X + 10,        box2Y + box2H,
					   box2W + box4W - 10,box2Y + box2H,
					   10,true,analysis.getMax("raw-MACD" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + 
							   									  buttons.get(currentIndicator).getValues()[1],
							   									  "MACD" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + 
							   									                 buttons.get(currentIndicator).getValues()[1]),
					   analysis.getMin("raw-MACD" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + 
							   						      buttons.get(currentIndicator).getValues()[1] ,
							   						      "MACD" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + 
							   						                     buttons.get(currentIndicator).getValues()[1]));
//		
//			drawScaleLines(box2X + 10,    box2Y + (box2H) - 10,
//				   	   box2W + box4W - 10,box2Y + (box2H) - 10,
//				   	   box2X + 10,        box2Y + (int)(box2H/2.0),
//				   	   box2W + box4W - 10,box2Y + (int)(box2H/2.0),
//				   	   5,true,-analysis.getMax("raw-MACD"+ "-" + 
//				   	   buttons.get(currentIndicator).getValues()[0] + "-" + 
//				   	   buttons.get(currentIndicator).getValues()[1],"open"),0);
		} else if(indicatorButton.getCurrentCycle() == 2){
			drawScaleLines(box2X + 10,    box2Y + 10,
					   box2W + box4W - 10,box2Y + 10,
					   box2X + 10,        box2Y + box2H - 10,
					   box2W + box4W - 10,box2Y + box2H - 10,
					   10,true,analysis.getMax("raw-ATR"+ "-" + 	   	
					   buttons.get(currentIndicator).getValues()[0],
					   "ATR" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0]),
					   analysis.getMin("raw-ATR"+ "-" + 	   	
					   buttons.get(currentIndicator).getValues()[0],
					   "ATR" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0]));
		} else if(indicatorButton.getCurrentCycle() == 3){
			drawScaleLines(box2X + 10,    box2Y + 10,
					   box2W + box4W - 10,box2Y + 10,
					   box2X + 10,        box2Y + box2H - 10,
					   box2W + box4W - 10,box2Y + box2H - 10,
					   10,true,analysis.getMax("raw-ADL","ADL" + "-" + "RAW"),analysis.getMin("raw-ADL","ADL" + "-" + "RAW"));
		} else if(indicatorButton.getCurrentCycle() == 4){
			drawScaleLines(box2X + 10,    box2Y + 10,
					   box2W + box4W - 10,box2Y + 10,
					   box2X + 10,        box2Y + box2H - 10,
					   box2W + box4W - 10,box2Y + box2H - 10,
					   10,true,100,0);
		} else if(indicatorButton.getCurrentCycle() == 5){
			drawScaleLines(box2X + 10,    box2Y + 10,
					   box2W + box4W - 10,box2Y + 10,
					   box2X + 10,        box2Y + box2H - 10,
					   box2W + box4W - 10,box2Y + box2H - 10,
					   10,true,analysis.getMax("raw-VI" + "-" + 
					   buttons.get(currentIndicator).getValues()[0],"PMV" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0]),
					   analysis.getMin("raw-VI" + "-" + 
					   buttons.get(currentIndicator).getValues()[0],"PMV" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0]));
		}
		else if(indicatorButton.getCurrentCycle() == 6){
			drawScaleLines(box2X + 10,    box2Y + 10,
					   box2W + box4W - 10,box2Y + 10,
					   box2X + 10,        box2Y + box2H - 10,
					   box2W + box4W - 10,box2Y + box2H - 10,
					   10,true,100,0);
		} else if(indicatorButton.getCurrentCycle() == 7){
			drawScaleLines(box2X + 10,    box2Y + 10,
					   box2W + box4W - 10,box2Y + 10,
					   box2X + 10,        box2Y + box2H - 10,
					   box2W + box4W - 10,box2Y + box2H - 10,
					   10,true,100,0);
			
		} else if(indicatorButton.getCurrentCycle() == 8){
			drawScaleLines(box2X + 10,    box2Y + 10,
					   box2W + box4W - 10,box2Y + 10,
					   box2X + 10,        box2Y + box2H - 10,
					   box2W + box4W - 10,box2Y + box2H - 10,
					   10,true,100,0);
		}
	}

	private void drawBackground(){
		noStroke();
	    fill(255);
	    
		rect(box1X,box1Y,box1W,box1H);
		rect(box2X,box2Y,box2W,box2H);
		rect(box3X, box3Y,box3W,box3H);
		rect(box4X,box4Y,box4W,box4H);
		rect(box5X, box5Y,box5W,box5H);
	}
	
	private void drawScaleLines(int x1Top,int y1Top,int x2Top,int y2Top,int x1Bot,float y1Bot,int x2Bot,float y2Bot, double iterations,boolean hasLog, double maxValue, double minValue){
		double diff = y1Bot - y1Top;
		double logBase = Math.E;
		double roundedValue;
		
		if(logButton.getCurrentCycle() == 1 && hasLog) {
			for(int i = 0; i <= iterations; i++){
				roundedValue = Math.round((maxValue - (iterations - i)/iterations*(maxValue - minValue))*100)/100.0;
				new TextBox(this,x2Top - sideBoxWidth + 2, (float)(y1Bot - (Math.log(1 + (logBase - 1) * (i)/(iterations))/Math.log(logBase))*diff) - 10, -1, 10, true, "" + roundedValue, 9).displayText();
				if(i == 0 || i == iterations){
					stroke(0,200);
				} else {
					stroke(100,50);
				}
				line(x1Top, (float)(y1Bot - (Math.log(1 + (logBase - 1) * (i)/(iterations))/Math.log(logBase))*diff), x2Top, (float)(y2Bot - (Math.log(1 + (logBase - 1) * (i)/(iterations))/Math.log(logBase))*diff));
			}
		}  else {
			for(int i = 0; i <= iterations; i++){
				roundedValue = Math.round((maxValue - i/iterations*(maxValue - minValue))*100)/100.0;
				new TextBox(this,x2Top - sideBoxWidth + 2, (float)(y2Top + i/iterations*diff) - 10, -1, 10, true,"" + roundedValue, 9).displayText();
				if(i == 0 || i ==iterations){
					stroke(0,200);
				} else {
					stroke(100,50);
				}
				line(x1Top, (float)(y1Top + i/iterations*diff), x2Top, (float)(y2Top + i/iterations*diff));
			}
		}
	}
	
	private void drawHistograms(){
		String arithLog;
		
		if(logButton.getCurrentCycle() == 1){
			arithLog = "log-";
		} else {
			arithLog = "arith-";
		}
		drawHistogram("rawVolume",box1Y + box1H - 10,volumeThickness,"volume");
		if(indicatorButton.getCurrentCycle() == 1){
			drawHistogram(arithLog + indicatorButton.toString() + "-null",
						  box2Y + box2H/2,(box2H - 20)/2,
					      indicatorButton.toString() + "-Histogram");
		}
	}
	
	private void drawOverlay(){
		String arithLog;
		String saved;
		indicatorOverlayValues = "";
		
		if(logButton.getCurrentCycle() == 1){
			arithLog = "log_";
		} else {
			arithLog = "arith_";
		}
		
		
		switch(overlayButton.getCurrentCycle()){
			case 1: saved = "close";break;//Simple Moving Average
			case 2: saved = "close";break;//Exponential MA
			case 3: saved = "high-low";break;//Zigzag
			case 4: saved = "high_mid_low";break;//BB
			case 5: saved = "long_short";break;//CE
			case 6: saved = "high_mid_low";break;//PC
			
			default: saved = "";
		}
				
		if(overlayButton.getCurrentCycle() != 0){
		drawLine(arithLog + overlayButton.toString() +"_" + saved,
				 box1Y + 20, stockRange, new Color(0,0,0));
		}
		
		if(overlayButton.getCurrentCycle() != 0){
			drawLine("raw_" + overlayButton.toString() +"_" + saved,
					 box1Y + 20, stockRange, new Color(0,0,0));
			}
		
		if(logButton.getCurrentCycle() == 1){
			arithLog = "log-";
		} else {
			arithLog = "arith-";
		}
		
		drawCandle(arithLog + "basic",box1Y + 20, stockRange);
	}
	
	private void drawIndicator(){
		String arithLog;
		String saved;
		
		if(logButton.getCurrentCycle() == 1){
			arithLog = "log_";
		} else {
			arithLog = "arith_";
		}
		

		switch(indicatorButton.getCurrentCycle()){
			case 1: saved = "null_EMA"; break;//MACD
			case 2: saved = "null";break;//ATR
			case 3: saved = "null";break;//ADL
			case 4: saved = "Up_Down_Difference";break;//Aroon
			case 5: saved = "Positive_Negative";break;//Vortex
			case 6: saved = "null";break;//Ultimate
			case 7: saved = "null";break;//Stochastic 
			case 8: saved = "null";break;//Relative Strength
			
			default: saved = "";
		}
		
		if(indicatorButton.getCurrentCycle() != 0){
			drawLine(arithLog + indicatorButton.toString() +"_" + saved,
				box2Y + 10,box2H - 20, new Color(0,0,0));
		}
		
		if(overlayButton.getCurrentCycle() != 0){                                       //NEED TO MAKE METHOD
			drawLine("raw_" + indicatorButton.toString() +"_" + saved,
					 0, 0, new Color(0,0,0));
			}
	}
	
	private void getCurrentValues(){
		
	}
	
	private void drawZigZag(String dataName, String saved, float offset,float stockRange,boolean isColor, Color color,ArrayList<DataPoint>  drawArithList,ArrayList<DataPoint>  drawLogList,String drawStr,Color drawColor){
		ArrayList<DataPoint> list, listLarge, drawToList,scaledList;
		listLarge = analysis.get("rawList");
		list = analysis.get(dataName);
		if(logButton.getCurrentCycle() == 1){	
			drawToList = drawLogList;
			scaledList = analysis.get("log-" + "basic");
			currentGraph = "log";
		} else {
			drawToList = drawArithList;
			scaledList = analysis.get("arith-" + "basic");
			currentGraph = "arith";
		}
		int size = list.size();
		int sizelarge = listLarge.size();
		int middle = ((box1W - 20) * (1)/sizelarge)/2;


		
		for(int i = size - 1; i > 0 ; i--){
			
			//System.out.println(list.get(58).get(saved));
			
			if(list.get(i).get(saved) != null && list.get(i).get(saved) != 0){
				int k = 1;
				while(list.get(i - k).get(saved) == 0){
					
					k++;
				}
				
				if(list.get(i - k).get(saved) != null){
					
					if(isColor && drawToList != null){
						noStroke();
						fill(drawColor.getRed(),drawColor.getGreen(),drawColor.getBlue(),drawColor.getAlpha());
		
						quad((float)((box1W - 20) * (sizelarge - list.get(i - k).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), (float) (offset + stockRange*(1 - list.get(i - k).get(saved))),
			 
								(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), (float) (offset + stockRange*(1 - list.get(i).get(saved))),
								(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)),(float) (offset + stockRange*(1 - drawToList.get(i).get(drawStr))),
								(float)((box1W - 20) * (sizelarge - list.get(i - k).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)),(float) (offset + stockRange*(1 - drawToList.get(i - k).get(drawStr))));
					}
					
			
					
					
					stroke(color.getRed(),color.getGreen(),color.getBlue());
					fill(255);
					String str1 = list.get(i).getZig(Integer.parseInt(dataName.split("-")[2]));
					String str2 = list.get(i - k).getZig(Integer.parseInt(dataName.split("-")[2]));
					line((float)((box1W - 20) * (sizelarge - list.get(i - k).get("id") - 1)/sizelarge + 15   + ((box1W - 20) * (1)/sizelarge)), 
					
							(float) (offset + stockRange * (1 - scaledList.get(i - k).get(str2))),
				 
							(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 15 + ((box1W - 20) * (1)/sizelarge)), 
				 
							(float) (offset + stockRange * (1 - scaledList.get(i).get(str1))));
				}
				//i = i - k + 1;
					
			}
		}	
	
		
	}
		
	private void drawLine(String dataName,float offset,float stockRange, Color color){
		ArrayList<DataPoint> list, listLarge;
		listLarge = analysis.get("rawList");
		


		for(int j = 0; j < dataName.split("_").length - 2;j++){
		
			String saved = dataName.split("_")[1]  + "-" + dataName.split("_")[j + 2];
			list = analysis.get(dataName.split("_")[0] + "-" + dataName.split("_")[1]);
			if(list != null){
				int size = list.size();
				int sizelarge = listLarge.size();
		
				int middle = ((box1W - 20) * (1)/sizelarge)/2;

		
				for(int i = 1; i < size; i++){
					if(list.get(i).get(saved) != null && list.get(i - 1).get(saved) != null){
//						if(isColor && drawToList != null){
//							noStroke();
//							fill(drawColor.getRed(),drawColor.getGreen(),drawColor.getBlue(),drawColor.getAlpha());
//			
//							quad((float)((box1W - 20) * (sizelarge - list.get(i - 1).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), (float) (offset + stockRange*(1 - list.get(i - 1).get(saved))),
//				 
//								(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), (float) (offset + stockRange*(1 - list.get(i).get(saved))),
//								(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)),(float) (offset + stockRange*(1 - drawToList.get(i).get(drawStr))),
//								(float)((box1W - 20) * (sizelarge - list.get(i - 1).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)),(float) (offset + stockRange*(1 - drawToList.get(i - 1).get(drawStr))));
//						}
						stroke(color.getRed(),color.getGreen(),color.getBlue());
						fill(255);
						stroke(0);
						line((float)((box1W - 20) * (sizelarge - list.get(i - 1).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), 
								(float) (offset + stockRange * (1 - list.get(i - 1).get(saved))),
								(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle+ ((box1W - 20) * (1)/sizelarge)), 			 
								(float) (offset + stockRange * (1 - list.get(i).get(saved))));
						
						if(list.get(i-1).get("id") == 0 && dataName.split("_")[0].equals("raw")){
							int temp = (int) (list.get(i - 1).get(saved)*100 + .5);
							indicatorOverlayValues += temp/100.0+", "; 
						}
						
					}		
				}	
			}
		}
	
		
	}
	
	private void drawCandle(String dataName, float offset,float stockRange){
		stroke(0);
		fill(255);
		ArrayList<DataPoint> list;
		HoverBox box;
		ArrayList<DataPoint> rawlist = analysis.get("rawList");
		Color color;
//		if(logButton.getCurrentCycle() == 1){		
//			currentGraph = "log";
//		} else {
//			currentGraph = "arith";
//		}
		list = analysis.get(dataName);
		int size = list.size();
		
		
		for(int i = 0; i < (size/analysis.get("rawList").size() * size);i++){
			

			if(list.get(i).get("open") < list.get(i).get("close")){
				color = new Color(0,255,0);
			}else{
				color = new Color(255,0,0);
			}
			
			line((box1W - 20) * (size - i -1)/size + 17 + ((box1W - 20) * (1)/size)/2, 
					
				 (float) (offset + stockRange*(1-list.get(i).get("high"))),
				 
				 (box1W - 20) * (size - i -1)/size+ 17 + ((box1W - 20) * (1)/size)/2, 
				 
				 (float) (offset + stockRange*(1-list.get(i).get("low"))));

			box = new HoverBox(this,(box1W - 20) * (size - i-1)/size + 17, 
					           (float) (offset + stockRange*(1 - list.get(i).get("open"))),
					           (box1W - 20) * (1)/size, 
					           (int) ((offset + stockRange * (1 - list.get(i).get("close"))) - (offset + stockRange*(1 - list.get(i).get("open")))),
					            "H:" + rawlist.get(i).get("high") + "\nL:" + rawlist.get(i).get("low") + "\nO:" + rawlist.get(i).get("open") +"\nC:"+ rawlist.get(i).get("close")+"\nId:"+ rawlist.get(i).get("id"),color);
			
			box.drawHoverBox();
			
						
		}	
		
	}
		
	private void drawHistogram(String dataName, float topOffset,float range,String str){
		stroke(0);
		fill(255);
		ArrayList<DataPoint> list;
		
		list = analysis.get(dataName);
		if(list != null){
			ArrayList<Double> volumeList = new ArrayList<Double>(); 
			double size = analysis.get("rawList").size();
			double othersize = list.size();
			
			for(int i = 0; i < list.size();i++){
				volumeList.add(list.get(i).get(str));			}
		
		
			for(int i = 0;i < othersize;i++){
				if(volumeList.get(i) != null){			
					rect((float)((box1W - 20) * (size - (list.get(i).get("id")))/(size + 1)  + 17),
							topOffset,
							(float)((box1W - 20)/size - 2),
							(float) (-volumeList.get(i)*range));

				}
			}
		}

	}
	
	private void drawButtons(){
		logButton.displayButton();
		indicatorButton.displayButton();
		overlayButton.displayButton();
	}

	private void drawText(){
		stockTicker.newText(analysis.get("rawList").get(0).get("close") + ": " + indicatorOverlayValues);
		newStockBox.displayBox(0);
		newStockBox.displayText();
		stockTicker.displayText();
		String overlayText = "";
		String indicatorText = "";
		currentOverlayValue.newText(overlayText);
		currentIndicatorValue.newText(indicatorText);
		currentOverlayValue.displayText();
		currentIndicatorValue.displayText();
	}

}

