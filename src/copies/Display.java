//import java.awt.Color;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import processing.core.PApplet;
//import processing.core.PFont;
//import utilities.DataPoint;
//
//public class Display extends PApplet{
//	private static final long serialVersionUID = 1L;
//	
//	private Analysis analysis;
//	
//	private int bottomBoxThickness;
//	private int middleBoxThickness;
//
//
//	private int sideThickness = 10;
//	private int boxSeperation = 5;
//	private int sideBoxWidth;
//	private int volumeThickness; 
//	private int box1X, box1Y, box1H, box1W,box2X, box2Y, box2H, box2W,box3X, box3Y, box3H, box3W, box4X, box4Y, box4H, box4W,box5X, box5Y, box5H, box5W;
//	HashMap<String, Analysis> map = new HashMap<String, Analysis>();
//	private float stockRange;
//	
//	private String currentOverlay = "none";
//	private String currentIndicator = "none";
//	private String currentGraph = "arith";
//	
//	private HashMap<String,ButtonData> buttons = new HashMap<String,ButtonData>();
//	private HashMap<String,EditableTextBox[]> variableValues= new HashMap<String, EditableTextBox[]>();
//
//	
//	private Button logButton;
//	private Button overlayButton;
//	private Button indicatorButton;
//	private int numIndicators = 9;
//	private int numOverlays = 7;
//	
//	private EditableTextBox newStockBox;
//	private String ticker = "DIA";
//	private TextBox stockTicker;
//	private TextBox currentOverlayValue;
//	private TextBox currentIndicatorValue;
//	private String lastTicker = "DIA";
//	
//	
//	public void setup() {
//		changeShownStock(ticker);
//
//		
//		size(1300, 800);
//		background(80,87,79);
//		  
//		bottomBoxThickness = (int) (height * 100.0/800);
//		middleBoxThickness = (int) (height * 200.0/800);
//		stockRange = (float) (height * 300.0/800);
//		sideBoxWidth = (int) (width * 50.0/1300);
//		volumeThickness = (int) (height * 100.0/800);
//		  
//		box1X = sideThickness;
//		box1Y = sideThickness;
//		box1W = width - 2 * sideThickness - sideBoxWidth;
//		box1H = height - sideThickness - middleBoxThickness - bottomBoxThickness; 
//		    
//		box2X = sideThickness;
//		box2Y = height - middleBoxThickness - bottomBoxThickness + boxSeperation;
//		box2W = width - 2 * sideThickness - sideBoxWidth;
//		box2H = middleBoxThickness  - boxSeperation; 
//		    
//		box3X = sideThickness;
//		box3Y = height - bottomBoxThickness + boxSeperation;
//		box3W = width - 2 * sideThickness;
//		box3H = bottomBoxThickness - sideThickness - boxSeperation;
//		
//		box4X = width - 2 *  sideThickness - sideBoxWidth;
//		box4Y = sideThickness;
//		box4W = sideBoxWidth + sideThickness;
//		box4H = height - sideThickness - middleBoxThickness - bottomBoxThickness; 
//		    
//		box5X = width - 2 *  sideThickness - sideBoxWidth;
//		box5Y = height - middleBoxThickness - bottomBoxThickness + boxSeperation;
//		box5W = sideBoxWidth + sideThickness;
//		box5H = middleBoxThickness  - boxSeperation; 
//		    
//		setUpSomeButtons();
//		
//		logButton = new Button(box3X + 70,box3Y + 20,75,35,currentGraph,2,20);
//		overlayButton = new Button(box3X + 250,box3Y + 5,75,35,currentOverlay,numOverlays,20);
//		indicatorButton = new Button(box3X + 250 ,box3Y + 45,75,35,currentIndicator,numIndicators,20);
//		
//		newStockBox = new EditableTextBox(box3X + 10,box3Y + 20, 50, 20, false, 14, 4,"stock");
//	
//		stockTicker = new TextBox(box1X + 10, box1Y + 2,-1,18,true,ticker + ": " + analysis.get("rawList").get(0).get("close"),16); 
//		currentOverlayValue = new TextBox(box3X + 150,box3Y + 5,75,35,true,"test1",16);
//		currentIndicatorValue= new TextBox(box3X + 150,box3Y + 45,75,35,true,"test2",16);
//
//	}
//	
//	private void addVariableValues(String name, int num,int overlayOrIndicator){
//		EditableTextBox[] texts = new EditableTextBox[num];
//		String type = "overlay";
//		if(overlayOrIndicator == 1) 
//			type = "indicator";
//		for(int i = 0; i < num; i++){
//			texts[i] = new EditableTextBox(box3X + 330 + 80*i ,box3Y + 5 + 40 * overlayOrIndicator,75,35, false, 20, 4, type + i);
//		}
//		variableValues.put(name,texts);
//	}
//	
//	private void setUpSomeButtons(){
//		buttons.put("arith",new ButtonData("Arithmatic",0,new Color(11,95,126)));
//		buttons.put("log",new ButtonData("Logarithmic",0,new Color(75,155,201)));
//		
//		buttons.put("none",new ButtonData("None",0,new Color(255,255,255)));
//		buttons.put("SMA",new ButtonData("Simple MA",1,new Color(0,160,176)));
//		addVariableValues("SMA",1,0);
//		buttons.put("EMA",new ButtonData("Exponetial MA",1,new Color(204,51,63)));
//		addVariableValues("EMA",1,0);
//		buttons.put("ZIG",new ButtonData("Zigzag",1,new Color(235,104,65)));
//		addVariableValues("ZIG",1,0);
//		buttons.put("BB",new ButtonData("Bollinger Bands",1,new Color(237,201,81)));
//		addVariableValues("BB",1,0);
//		buttons.put("CE",new ButtonData("CE ",1,new Color(127,184,251)));
//		addVariableValues("CE",1,0);
//		buttons.put("PC",new ButtonData("Price Channel",1,new Color(254,213,247)));
//		addVariableValues("PC",1,0);
//		
//		buttons.put("none",new ButtonData("None",0,new Color(255,255,255)));
//		buttons.put("MACD",new ButtonData("MACD",2,new Color(11,95,126)));
//		addVariableValues("MACD",2,1);
//		buttons.put("ATR",new ButtonData("ATR",1,new Color(14,206,74)));
//		addVariableValues("ATR",1,1);
//		buttons.put("ADL",new ButtonData("ADL",0,new Color(14,6,174)));
//		addVariableValues("ADL",0,1);
//		buttons.put("A",new ButtonData("Aroon",1,new Color(100,136,14)));
//		addVariableValues("A",1,1);
//		buttons.put("VI",new ButtonData("Vortex",1,new Color(100,100,100)));
//		addVariableValues("VI",1,1);
//		buttons.put("UO",new ButtonData("Ultimate",3,new Color(234,123,55)));
//		addVariableValues("UO",3,1);
//		buttons.put("STO",new ButtonData("Stochastic",3,new Color(142,123,155)));
//		addVariableValues("STO",3,1);
//		buttons.put("RSI",new ButtonData("Relative Strength",1,new Color(2,163,175)));
//		addVariableValues("RSI",1,1);
//
//	}
//	
//	public void changeShownStock(String ticker){
//		if(!map.containsKey(ticker)){	
//			analysis = new Analysis(new DataGrabber(ticker).run());	
//			if(analysis.get("rawList").size() == 0){
//				System.out.println("Stock with ticker: "+ ticker + " Does Not Exist");
//				ticker = lastTicker;
//				changeShownStock(ticker);
//				analysis = map.get(ticker);
//		    } else {
//			System.out.println("New Stock Loaded: " + ticker );
//			lastTicker = ticker;
//			analysis.createData();
//			map.put(ticker,analysis);
//		    }
//		} else {	
//			System.out.println("Old Stock Loaded: " + ticker );
//			analysis = map.get(ticker);
//		}
//		this.ticker = ticker;	
//		if(stockTicker != null)
//			stockTicker.newText(ticker + ": " + analysis.get("rawList").get(0).get("close"));
//	}
//	
//	public void preformAction(String id, String value){
//		switch (id.substring(0, id.length()-1)){
//			case "stoc":
//				changeShownStock(value);
//				break;
//			case "indicator":
//				buttons.get(currentIndicator).setValues(Integer.parseInt(id.substring(id.length()-1)), value);
//				break;
//			case "overlay":
//				buttons.get(currentOverlay).setValues(Integer.parseInt(id.substring(id.length()-1)), value);
//				break;
//			default:
//		}
//	}
//	
//	public void keyPressed() {
//		 newStockBox.keyPressed();
//		 if(variableValues.get(currentOverlay) != null){
//			 for(int j = 0; j < variableValues.get(currentOverlay).length; j++){
//				 variableValues.get(currentOverlay)[j].keyPressed();
//			 }
//		 }
//		 if (variableValues.get(currentIndicator) != null)
//			 for(int j = 0; j < variableValues.get(currentIndicator).length; j++){
//				 variableValues.get(currentIndicator)[j].keyPressed();
//			 }
//	}
//	
//	public void draw() {
//		background(80,87,79);
//		drawBackground();
//		drawText();
//		drawButtons();
//		drawStocks();
//		drawHistograms();
//		drawScaleLines();
//		
//	}
//	
//	private void drawScaleLines() {
//		//Stock
//		drawScaleLines(box1X + 10,        box1Y + 20,
//					   box1W + box4W - 10,box1Y + 20,
//					   box1X + 10,        box1Y + 20 + stockRange ,
//					   box1W + box4W - 10,box1Y + 20 + stockRange,
//					   10,true,analysis.getMax("rawList","high"),analysis.getMin("rawList","high"));
//		//Volume
//		drawScaleLines(box1X + 10,        box1Y + box1H - 10 - volumeThickness,
//				       box1W + box4W - 10,box1Y + box1H - 10 - volumeThickness,
//				       box1X + 10,        box1Y + box1H - 10,
//				       box1W + box4W - 10,box1Y + box1H - 10,
//				       3,false,analysis.getMax("rawList","volume"),0);
//		
//		if(indicatorButton.getCurrentCycle() == 1){
//			drawScaleLines(box2X + 10,    box2Y + 10,
//					   box2W + box4W - 10,box2Y + 10,
//					   box2X + 10,        box2Y + box2H,
//					   box2W + box4W - 10,box2Y + box2H,
//					   10,true,analysis.getMax("raw-MACD" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + 
//							   									  buttons.get(currentIndicator).getValues()[1],
//							   									  "MACD" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + 
//							   									                 buttons.get(currentIndicator).getValues()[1]),
//					   analysis.getMin("raw-MACD" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + 
//							   						      buttons.get(currentIndicator).getValues()[1] ,
//							   						      "MACD" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + 
//							   						                     buttons.get(currentIndicator).getValues()[1]));
////		
////			drawScaleLines(box2X + 10,    box2Y + (box2H) - 10,
////				   	   box2W + box4W - 10,box2Y + (box2H) - 10,
////				   	   box2X + 10,        box2Y + (int)(box2H/2.0),
////				   	   box2W + box4W - 10,box2Y + (int)(box2H/2.0),
////				   	   5,true,-analysis.getMax("raw-MACD"+ "-" + 
////				   	   buttons.get(currentIndicator).getValues()[0] + "-" + 
////				   	   buttons.get(currentIndicator).getValues()[1],"open"),0);
//		} else if(indicatorButton.getCurrentCycle() == 2){
//			drawScaleLines(box2X + 10,    box2Y + 10,
//					   box2W + box4W - 10,box2Y + 10,
//					   box2X + 10,        box2Y + box2H - 10,
//					   box2W + box4W - 10,box2Y + box2H - 10,
//					   10,true,analysis.getMax("raw-ATR"+ "-" + 	   	
//					   buttons.get(currentIndicator).getValues()[0],
//					   "ATR" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0]),
//					   analysis.getMin("raw-ATR"+ "-" + 	   	
//					   buttons.get(currentIndicator).getValues()[0],
//					   "ATR" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0]));
//		} else if(indicatorButton.getCurrentCycle() == 3){
//			drawScaleLines(box2X + 10,    box2Y + 10,
//					   box2W + box4W - 10,box2Y + 10,
//					   box2X + 10,        box2Y + box2H - 10,
//					   box2W + box4W - 10,box2Y + box2H - 10,
//					   10,true,analysis.getMax("raw-ADL","ADL" + "-" + "RAW"),analysis.getMin("raw-ADL","ADL" + "-" + "RAW"));
//		} else if(indicatorButton.getCurrentCycle() == 4){
//			drawScaleLines(box2X + 10,    box2Y + 10,
//					   box2W + box4W - 10,box2Y + 10,
//					   box2X + 10,        box2Y + box2H - 10,
//					   box2W + box4W - 10,box2Y + box2H - 10,
//					   10,true,100,0);
//		} else if(indicatorButton.getCurrentCycle() == 5){
//			drawScaleLines(box2X + 10,    box2Y + 10,
//					   box2W + box4W - 10,box2Y + 10,
//					   box2X + 10,        box2Y + box2H - 10,
//					   box2W + box4W - 10,box2Y + box2H - 10,
//					   10,true,analysis.getMax("raw-VI" + "-" + 
//					   buttons.get(currentIndicator).getValues()[0],"PMV" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0]),
//					   analysis.getMin("raw-VI" + "-" + 
//					   buttons.get(currentIndicator).getValues()[0],"PMV" + "-" + "RAW" + "-" + buttons.get(currentIndicator).getValues()[0]));
//		}
//		else if(indicatorButton.getCurrentCycle() == 6){
//			drawScaleLines(box2X + 10,    box2Y + 10,
//					   box2W + box4W - 10,box2Y + 10,
//					   box2X + 10,        box2Y + box2H - 10,
//					   box2W + box4W - 10,box2Y + box2H - 10,
//					   10,true,100,0);
//		} else if(indicatorButton.getCurrentCycle() == 7){
//			drawScaleLines(box2X + 10,    box2Y + 10,
//					   box2W + box4W - 10,box2Y + 10,
//					   box2X + 10,        box2Y + box2H - 10,
//					   box2W + box4W - 10,box2Y + box2H - 10,
//					   10,true,100,0);
//			
//		} else if(indicatorButton.getCurrentCycle() == 8){
//			drawScaleLines(box2X + 10,    box2Y + 10,
//					   box2W + box4W - 10,box2Y + 10,
//					   box2X + 10,        box2Y + box2H - 10,
//					   box2W + box4W - 10,box2Y + box2H - 10,
//					   10,true,100,0);
//		}
//	}
//
//	private void drawBackground(){
//		noStroke();
//	    fill(255);
//	    
//		rect(box1X,box1Y,box1W,box1H);
//		rect(box2X,box2Y,box2W,box2H);
//		rect(box3X, box3Y,box3W,box3H);
//		rect(box4X,box4Y,box4W,box4H);
//		rect(box5X, box5Y,box5W,box5H);
//	}
//	
//	private void drawScaleLines(int x1Top,int y1Top,int x2Top,int y2Top,int x1Bot,float y1Bot,int x2Bot,float y2Bot, double iterations,boolean hasLog, double maxValue, double minValue){
//		double diff = y1Bot - y1Top;
//		double logBase = Math.E;
//		double roundedValue;
//		
//		if(logButton.getCurrentCycle() == 1 && hasLog) {
//			for(int i = 0; i <= iterations; i++){
//				roundedValue = Math.round((maxValue - (iterations - i)/iterations*(maxValue - minValue))*100)/100.0;
//				new TextBox(x2Top - sideBoxWidth + 2, (float)(y1Bot - (Math.log(1 + (logBase - 1) * (i)/(iterations))/Math.log(logBase))*diff) - 10, -1, 10, true, "" + roundedValue, 9).displayText();
//				if(i == 0 || i == iterations){
//					stroke(0,200);
//				} else {
//					stroke(100,50);
//				}
//				line(x1Top, (float)(y1Bot - (Math.log(1 + (logBase - 1) * (i)/(iterations))/Math.log(logBase))*diff), x2Top, (float)(y2Bot - (Math.log(1 + (logBase - 1) * (i)/(iterations))/Math.log(logBase))*diff));
//			}
//		}  else {
//			for(int i = 0; i <= iterations; i++){
//				roundedValue = Math.round((maxValue - i/iterations*(maxValue - minValue))*100)/100.0;
//				new TextBox(x2Top - sideBoxWidth + 2, (float)(y2Top + i/iterations*diff) - 10, -1, 10, true,"" + roundedValue, 9).displayText();
//				if(i == 0 || i ==iterations){
//					stroke(0,200);
//				} else {
//					stroke(100,50);
//				}
//				line(x1Top, (float)(y1Top + i/iterations*diff), x2Top, (float)(y2Top + i/iterations*diff));
//			}
//		}
//	}
//	
//	private void drawHistograms(){
//		String arithLog;
//		
//		if(logButton.getCurrentCycle() == 1){
//			arithLog = "log-";
//		} else {
//			arithLog = "arith-";
//		}
//		drawHistogram("rawVolume",box1Y + box1H - 10,volumeThickness,"volume");
//		if(indicatorButton.getCurrentCycle() == 1){
//			drawHistogram(arithLog + "MACD"  + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + buttons.get(currentIndicator).getValues()[1],
//					      box2Y + box2H/2,(box2H - 20)/2,
//					      "MACD" + "-" +"HISTOGRAM"+ "-" + buttons.get(currentIndicator).getValues()[0] + "-" + buttons.get(currentIndicator).getValues()[1]);
//		}
//	}
//	
//	private void drawStocks(){
//		String arithLog;
//		
//		if(logButton.getCurrentCycle() == 1){
//			arithLog = "log-";
//		} else {
//			arithLog = "arith-";
//		}
//		
//		
//		if(overlayButton.getCurrentCycle() == 1){
//			currentOverlay = "SMA";
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "SMA"+"-" + buttons.get(currentOverlay).getValues()[0] +"-" +"close",
//					 box1Y + 20, stockRange,false,new Color(0,0,0),null,null,"",null);
//		} else if(overlayButton.getCurrentCycle() == 2){ 
//			currentOverlay = "EMA";
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "EMA"+ "-" + buttons.get(currentOverlay).getValues()[0] + "-" + "close",
//					 box1Y + 20, stockRange,false,new Color(0,0,0),null,null,"",null);	
//		}else if(overlayButton.getCurrentCycle() == 3){
//			currentOverlay = "ZIG";
//			drawZigZag("raw-" + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "ZIG" + "-" + buttons.get(currentOverlay).getValues()[0],
//					 box1Y + 20, stockRange,false,new Color(0,0,0),null,null,"",null);
////			drawLine(analysis.get("arith-Zigzag-20"),analysis.get("log-Zigzag-20"),"close",box1Y + 20, stockRange,false,new Color(10,134,32),null,null,"",null);
//			
//		} else if(overlayButton.getCurrentCycle() == 4){
//			currentOverlay = "BB";
//			Color colorBB = new Color(69,171,229,25);
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "BB-high"+ "-" + buttons.get(currentOverlay).getValues()[0],
//					 box1Y + 20, stockRange,true,new Color(69,171,229),
//					 analysis.get("arith-" + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0]),
//					 analysis.get("log-"   + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0]),
//					 "BB-low"+ "-" + buttons.get(currentOverlay).getValues()[0],
//					 colorBB);
//			
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "BB-low"+ "-" + buttons.get(currentOverlay).getValues()[0],
//					 box1Y + 20, stockRange,false,new Color(69,171,229),null,null,"",null);
//			
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "BB-mid"+ "-" + buttons.get(currentOverlay).getValues()[0],
//					 box1Y + 20, stockRange,false,new Color(69,171,229),null,null,"",null);
//			
//		}else if(overlayButton.getCurrentCycle() == 5){
//			currentOverlay = "CE";
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "CE-long" + "-" + +buttons.get(currentOverlay).getValues()[0],
//					 box1Y + 20, stockRange,false,new Color(0,0,255),null,null,"",null);
//			
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "CE-short" + "-" + +buttons.get(currentOverlay).getValues()[0],
//					 box1Y + 20, stockRange,false,new Color(255,0,0),null,null,"",null);
//			
//		}else if(overlayButton.getCurrentCycle() == 6){
//			currentOverlay = "PC";
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "PC-high" + "-" + buttons.get(currentOverlay).getValues()[0],
//					 box1Y + 20, stockRange,true,new Color(255,127,232),
//					 analysis.get("arith-" + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0]),
//					 analysis.get("log-"   + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0]),
//					 "PC-low" + "-" + buttons.get(currentOverlay).getValues()[0],
//					 new Color(254,213,247,125));
//			
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "PC-mid" + "-" +buttons.get(currentOverlay).getValues()[0],
//					 box1Y + 20, stockRange,false,new Color(255,127,232),null,null,"",null);
//			
//			drawLine(arithLog + currentOverlay + "-" + buttons.get(currentOverlay).getValues()[0],
//					 "PC-low" + "-" +buttons.get(currentOverlay).getValues()[0],
//					 box1Y + 20, stockRange,false,new Color(255,127,232),null,null,"",null);
//		} else {
//			currentOverlay = "none";
//		}
////		drawLine(analysis.get("arith-SMAE-20-10"),analysis.get("log-SMAE-20-10"),"high",box1Y + 20, stockRange,true,new Color(10,134,32),analysis.get("arith-SMAE-20-10"),analysis.get("log-SMAE-20-10"),"low",new Color(10,134,32,25));
////		drawLine(analysis.get("arith-SMAE-20-10"),analysis.get("log-SMAE-20-10"),"close",box1Y + 20, stockRange,false,new Color(10,134,32),null,null,"",null);
////		drawLine(analysis.get("arith-SMAE-20-10"),analysis.get("log-SMAE-20-10"),"low",box1Y + 20, stockRange,false,new Color(10,134,32),null,null,"",null);
//
//// Deal with SMAE later
//
//
//		
//		drawCandle(arithLog + "basic",box1Y + 20, stockRange);
//		
//		
//		
//		
//		if(indicatorButton.getCurrentCycle() == 1){
//			currentIndicator = "MACD";
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + buttons.get(currentIndicator).getValues()[1],
//					 "MACD" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + buttons.get(currentIndicator).getValues()[1],
//					 box2Y + 10,(box2H - 20),false,new Color(0,0,0),null,null,"",null);	
//			
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + buttons.get(currentIndicator).getValues()[1],
//					 "EMA" + "-" + "9" + "-" + "MACD" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + buttons.get(currentIndicator).getValues()[1],
//					 box2Y + 10,(box2H - 20),false,new Color(255,0,0),null,null,"",null);	
//			
//		} else if(indicatorButton.getCurrentCycle() == 2){
//			currentIndicator = "ATR";
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0],
//					 "ATR" + "-" + buttons.get(currentIndicator).getValues()[0],
//					 box2Y + 10,box2H - 20,false,new Color(0,0,0),null,null,"",null);
//		
//		}  else if(indicatorButton.getCurrentCycle() == 3){
//			currentIndicator = "ADL";
//			drawLine(arithLog + currentIndicator,
//					 "ADL",
//					 box2Y + 10,(box2H - 20),false,new Color(255,0,0),null,null,"",null);
//		
//		} else if(indicatorButton.getCurrentCycle() == 4){
//			currentIndicator = "A";
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0],
//					 "AU"+ "-" + buttons.get(currentIndicator).getValues()[0],
//					 box2Y + 10,box2H - 20,false,new Color(0,255,0),null,null,"",null);
//			
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0],
//					 "AD"+ "-" + buttons.get(currentIndicator).getValues()[0],
//					 box2Y + 10,box2H - 20,false,new Color(255,0,0),null,null,"",null);
//			
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0],
//					"ADIF"+ "-" + buttons.get(currentIndicator).getValues()[0],
//					box2Y + 10,(box2H - 20)/2,false,new Color(0,0,255),null,null,"",null);
//			
//		} else if(indicatorButton.getCurrentCycle() == 5){
//			currentIndicator = "VI";
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0],
//					 "PVM"+ "-" + buttons.get(currentIndicator).getValues()[0],
//					 box2Y + 10,box2H - 20,false,new Color(0,255,0),null,null,"",null);
//			
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0],
//					 "MVM"+ "-" + buttons.get(currentIndicator).getValues()[0],
//					 box2Y + 10,box2H - 20,false,new Color(255,0,0),null,null,"",null);
//			
//		} else if(indicatorButton.getCurrentCycle() == 6){
//			currentIndicator = "UO";
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0]+ "-" + buttons.get(currentIndicator).getValues()[1]+ "-" + buttons.get(currentIndicator).getValues()[2],
//					 "UO" + "-" +  buttons.get(currentIndicator).getValues()[0]+ "-" + buttons.get(currentIndicator).getValues()[1]+ "-" + buttons.get(currentIndicator).getValues()[2],
//					 box2Y + 10,box2H - 20,false,new Color(0,0,255),null,null,"",null);
//			
//		} else if(indicatorButton.getCurrentCycle() == 7){
//			currentIndicator = "STO";
//			if(buttons.get(currentIndicator).getValues()[2] < 1){
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0]+ "-" + buttons.get(currentIndicator).getValues()[1]+ "-" + buttons.get(currentIndicator).getValues()[2],
//					 "SOK-fast" + "-" + buttons.get(currentIndicator).getValues()[0],
//					 box2Y + 10,box2H - 20,false,new Color(0,0,0),null,null,"",null);
//			
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0]+ "-" + buttons.get(currentIndicator).getValues()[1]+ "-" + buttons.get(currentIndicator).getValues()[2],
//					 "SMA" +"-" + buttons.get(currentIndicator).getValues()[1] + "-" + "SOK-fast" + "-" + buttons.get(currentIndicator).getValues()[0],
//					 box2Y + 10,box2H - 20,false,new Color(255,0,0),null,null,"",null);
//			} else {
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0]+ "-" + buttons.get(currentIndicator).getValues()[1]+ "-" + buttons.get(currentIndicator).getValues()[2],
//					 "SOK-full" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + buttons.get(currentIndicator).getValues()[1],
//					 box2Y + 10,box2H - 20,false,new Color(0,0,0),null,null,"",null);
//			
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0]+ "-" + buttons.get(currentIndicator).getValues()[1]+ "-" + buttons.get(currentIndicator).getValues()[2],
//					 "SMA" +"-" + buttons.get(currentIndicator).getValues()[1] + "-" + "SOK-full" + "-" + buttons.get(currentIndicator).getValues()[0] + "-" + buttons.get(currentIndicator).getValues()[1],
//					 box2Y + 10,box2H - 20,false,new Color(255,0,0),null,null,"",null);
//			}
//		} else if(indicatorButton.getCurrentCycle() == 8){
//			currentIndicator = "RSI";
//			drawLine(arithLog + currentIndicator + "-" + buttons.get(currentIndicator).getValues()[0],
//					 "RSI" + "-" + buttons.get(currentIndicator).getValues()[0],
//					 box2Y + 10,box2H - 20,false,new Color(12,150,200),null,null,"",null);
//		} else {
//			currentIndicator = "none";
//		}
//
//		logButton.setName(currentGraph);
//		overlayButton.setName(currentOverlay);
//		indicatorButton.setName(currentIndicator);
//	}
//	
//	private void drawZigZag(String dataName, String saved, float offset,float stockRange,boolean isColor, Color color,ArrayList<DataPoint>  drawArithList,ArrayList<DataPoint>  drawLogList,String drawStr,Color drawColor){
//		ArrayList<DataPoint> list, listLarge, drawToList,scaledList;
//		listLarge = analysis.get("rawList");
//		list = analysis.get(dataName);
//		if(logButton.getCurrentCycle() == 1){	
//			drawToList = drawLogList;
//			scaledList = analysis.get("log-" + "basic");
//			currentGraph = "log";
//		} else {
//			drawToList = drawArithList;
//			scaledList = analysis.get("arith-" + "basic");
//			currentGraph = "arith";
//		}
//		int size = list.size();
//		int sizelarge = listLarge.size();
//		int middle = ((box1W - 20) * (1)/sizelarge)/2;
//
//
//		
//		for(int i = size - 1; i > 0 ; i--){
//			
//			//System.out.println(list.get(58).get(saved));
//			
//			if(list.get(i).get(saved) != null && list.get(i).get(saved) != 0){
//				int k = 1;
//				while(list.get(i - k).get(saved) == 0){
//					
//					k++;
//				}
//				
//				if(list.get(i - k).get(saved) != null){
//					
//					if(isColor && drawToList != null){
//						noStroke();
//						fill(drawColor.getRed(),drawColor.getGreen(),drawColor.getBlue(),drawColor.getAlpha());
//		
//						quad((float)((box1W - 20) * (sizelarge - list.get(i - k).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), (float) (offset + stockRange*(1 - list.get(i - k).get(saved))),
//			 
//								(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), (float) (offset + stockRange*(1 - list.get(i).get(saved))),
//								(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)),(float) (offset + stockRange*(1 - drawToList.get(i).get(drawStr))),
//								(float)((box1W - 20) * (sizelarge - list.get(i - k).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)),(float) (offset + stockRange*(1 - drawToList.get(i - k).get(drawStr))));
//					}
//					
//			
//					
//					
//					stroke(color.getRed(),color.getGreen(),color.getBlue());
//					fill(255);
//					String str1 = list.get(i).getZig(Integer.parseInt(dataName.split("-")[2]));
//					String str2 = list.get(i - k).getZig(Integer.parseInt(dataName.split("-")[2]));
//					line((float)((box1W - 20) * (sizelarge - list.get(i - k).get("id") - 1)/sizelarge + 15   + ((box1W - 20) * (1)/sizelarge)), 
//					
//							(float) (offset + stockRange * (1 - scaledList.get(i - k).get(str2))),
//				 
//							(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 15 + ((box1W - 20) * (1)/sizelarge)), 
//				 
//							(float) (offset + stockRange * (1 - scaledList.get(i).get(str1))));
//				}
//				//i = i - k + 1;
//					
//			}
//		}	
//	
//		
//	}
//		
//	private void drawLine(String dataName, String saved, float offset,float stockRange,boolean isColor, Color color,ArrayList<DataPoint>  drawArithList,ArrayList<DataPoint>  drawLogList,String drawStr,Color drawColor){
//		ArrayList<DataPoint> list, listLarge, drawToList;
//		listLarge = analysis.get("rawList");
//		list = analysis.get(dataName);
//		if(logButton.getCurrentCycle() == 1){		
//			drawToList = drawLogList;
//			currentGraph = "log";
//		} else {
//			drawToList = drawArithList;
//			currentGraph = "arith";
//		}
//		int size = list.size();
//		int sizelarge = listLarge.size();
//		int middle = ((box1W - 20) * (1)/sizelarge)/2;
//		int breakPoint = dataName.indexOf('-') + 1;
//
//		
//		for(int i = 1; i < size; i++){
//			if(list.get(i).get(saved) != null && list.get(i - 1).get(saved) != null){
//				if(isColor && drawToList != null){
//					noStroke();
//					fill(drawColor.getRed(),drawColor.getGreen(),drawColor.getBlue(),drawColor.getAlpha());
//			
//					quad((float)((box1W - 20) * (sizelarge - list.get(i - 1).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), (float) (offset + stockRange*(1 - list.get(i - 1).get(saved))),
//				 
//							(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), (float) (offset + stockRange*(1 - list.get(i).get(saved))),
//							(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)),(float) (offset + stockRange*(1 - drawToList.get(i).get(drawStr))),
//							(float)((box1W - 20) * (sizelarge - list.get(i - 1).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)),(float) (offset + stockRange*(1 - drawToList.get(i - 1).get(drawStr))));
//				}
//			
//				stroke(color.getRed(),color.getGreen(),color.getBlue());
//				fill(255);
//				line((float)((box1W - 20) * (sizelarge - list.get(i - 1).get("id") - 1)/sizelarge + 17 + middle + ((box1W - 20) * (1)/sizelarge)), 
//					
//					(float) (offset + stockRange * (1 - list.get(i - 1).get(saved))),
//				 
//					(float)((box1W - 20) * (sizelarge - list.get(i).get("id") - 1)/sizelarge + 17 + middle+ ((box1W - 20) * (1)/sizelarge)), 
//				 
//					(float) (offset + stockRange * (1 - list.get(i).get(saved))));
//			
//			}		
//		}	
//	
//		
//	}
//	
//	private void drawCandle(String dataName, float offset,float stockRange){
//		stroke(0);
//		fill(255);
//		ArrayList<DataPoint> list;
//		HoverBox box;
//		ArrayList<DataPoint> rawlist = analysis.get("rawList");
//		Color color;
////		if(logButton.getCurrentCycle() == 1){		
////			currentGraph = "log";
////		} else {
////			currentGraph = "arith";
////		}
//		list = analysis.get(dataName);
//		int size = list.size();
//		
//		
//		for(int i = 0; i < (size/analysis.get("rawList").size() * size);i++){
//			
//
//			if(list.get(i).get("open") < list.get(i).get("close")){
//				color = new Color(0,255,0);
//			}else{
//				color = new Color(255,0,0);
//			}
//			
//			line((box1W - 20) * (size - i -1)/size + 17 + ((box1W - 20) * (1)/size)/2, 
//					
//				 (float) (offset + stockRange*(1-list.get(i).get("high"))),
//				 
//				 (box1W - 20) * (size - i -1)/size+ 17 + ((box1W - 20) * (1)/size)/2, 
//				 
//				 (float) (offset + stockRange*(1-list.get(i).get("low"))));
//
//			box = new HoverBox((box1W - 20) * (size - i-1)/size + 17, 
//					           (float) (offset + stockRange*(1 - list.get(i).get("open"))),
//					           (box1W - 20) * (1)/size, 
//					           (int) ((offset + stockRange * (1 - list.get(i).get("close"))) - (offset + stockRange*(1 - list.get(i).get("open")))),
//					            "H:" + rawlist.get(i).get("high") + "\nL:" + rawlist.get(i).get("low") + "\nO:" + rawlist.get(i).get("open") +"\nC:"+ rawlist.get(i).get("close")+"\nId:"+ rawlist.get(i).get("id"),color);
//			
//			box.drawHoverBox();
//			
//						
//		}	
//		
//	}
//		
//	private void drawHistogram(String dataName, float topOffset,float range,String str){
//		stroke(0);
//		fill(255);
//		ArrayList<DataPoint> list;
//		list = analysis.get(dataName);
//		ArrayList<Double> volumeList = new ArrayList<Double>(); 
//		double size = analysis.get("rawList").size();
//		double othersize = list.size();
//		for(int i = 0; i < list.size();i++){
//			volumeList.add(list.get(i).get(str));
//		}
//		
//		
//		for(int i = 0;i < othersize;i++){
//			if(volumeList.get(i) != null){	
//				rect((float)((box1W - 20) * (size - (list.get(i).get("id")))/(size + 1)  + 17),
//						topOffset,
//						(float)((box1W - 20)/size - 2),
//						(float) (-volumeList.get(i)*range));
//
//			}
//		}
//
//	}
//	
//	private void drawButtons(){
//		logButton.displayButton();
//		indicatorButton.displayButton();
//		overlayButton.displayButton();
//	}
//
//	private void drawText(){
//
//		newStockBox.displayBox(0);
//		newStockBox.displayText();
//		stockTicker.displayText();
//		String overlayText = "";
//		String indicatorText = "";
//		
//		if(variableValues.get(currentOverlay) != null) {
//			for(int i = 0; i < variableValues.get(currentOverlay).length; i++){
//				variableValues.get(currentOverlay)[i].displayBox(0);
//				variableValues.get(currentOverlay)[i].displayText();
//				overlayText += buttons.get(currentOverlay).getValues()[i] + ",";
//			}
//		}
//		if(variableValues.get(currentIndicator) != null) {
//			for(int i = 0; i < variableValues.get(currentIndicator).length; i++){
//				variableValues.get(currentIndicator)[i].displayBox(0);
//				variableValues.get(currentIndicator)[i].displayText();
//				indicatorText += buttons.get(currentIndicator).getValues()[i] + ",";
//			}
//		}
//		currentOverlayValue.newText(overlayText);
//		currentIndicatorValue.newText(indicatorText);
//		currentOverlayValue.displayText();
//		currentIndicatorValue.displayText();
//	}
//
//	class TextBox {
//		float width;
//		float height, fontSize;
//		float xpos, ypos;
//		String text;
//		PFont f;
//		boolean adjusting;
//		
//		public TextBox(float xpos, float ypos, float width, float height, boolean adjusting, String text,float fontSize){
//			this.xpos = xpos;
//			this.ypos = ypos;
//			this.width = width;
//			this.height = height;
//			this.text = text;
//			f = createFont("Arial",fontSize,true);
//	 		this.fontSize = fontSize;
//	 		this.adjusting = adjusting;
//		}
//
//		
//		public void newText(String newText){
//			text = newText;
//		}
//		
//		public void displayText(){
//			stroke(0);
//		    textFont(f,fontSize);
//		    fill(0);
//		    float newFontSize = fontSize;
//		    while(textWidth("" + text) > width && !adjusting){
//		    	textFont(f,newFontSize--);
//		    }
//		    text("" + text,xpos + 2,ypos + fontSize);
//		    
//		    if(adjusting){
//				width = textWidth(text) + 4;
//			}	
//		}
//		
//		public void displayBox(int alpha){
//			stroke(0);
//		    fill(255,alpha);
//		    rect(xpos, ypos, width, height,2);
//		}
//		
//	}
//	
//	class EditableTextBox extends TextBox{
//
//		String value;
//		int letterLimit;
//		String id;
//		
//		public EditableTextBox(float xpos, float ypos, int width, int height, boolean adjusting, int fontSize, int letterLimit, String id) {
//			super(xpos, ypos, width, height, adjusting, "", fontSize);
//			value = "";
//			this.letterLimit = letterLimit;
//			this.id = id;
//		}
//		
//		public EditableTextBox(TextBox textbox, int letterLimit) {
//			super(textbox.xpos, textbox.ypos, textbox.width, textbox.height, textbox.adjusting, "", textbox.fontSize);
//			value = "";
//			this.letterLimit = letterLimit;
//		}
//		
//		boolean overEvent() {
//			if (mouseX > xpos && mouseX < xpos + width && mouseY > ypos && mouseY < ypos + height) {
//				return true;
//			} else if(mouseX > xpos && mouseX < xpos + Math.abs(width) && mouseY < ypos && mouseY > ypos+ height){
//			    return true;
//			} else {
//			    return false;
//			}
//		}
//		
//		public void keyPressed() {
//			if(overEvent()){
//				if (key == BACKSPACE) {
//					if (value.length() > 0) {
//						value = value.substring(0, value.length()-1);
//					}
//				}
//			
//			 
//				if (key == ENTER) {
//					preformAction(id,value);
//					value = "";
//				}
//				else if (value.length() < letterLimit && keyCode != SHIFT && keyCode != BACKSPACE || (textWidth(value + key) > width && !super.adjusting && value.length() < letterLimit)){
//					value = value + key;
//					value = value.toUpperCase();
//				}
//				newText(value);
//			}
//		}
//	}
//	
//	class HoverBox{
//		
//		float xpos, ypos, width, height;
//		TextBox textBox;
//		int r,g,b;
//		
//		public HoverBox(float xpos, float ypos, float width, float height,String str,Color color) {
//			this.xpos = xpos;
//			this.ypos = ypos;
//			this.width = width;
//			this.height = height;
//			int yp;
//			if(ypos - 85 < 0){
//				yp = (int) (ypos + 25);
//			} else{
//				yp = (int) (ypos - 85);
//			}
//			textBox = new TextBox(xpos + 5,yp, (float) (textWidth(str)/1.1),90,false,str,12);
//			r = color.getRed();
//			g  = color.getGreen();
//			b = color.getBlue();
//		}
//
//		public void drawHoverBox(){
//			if(overEvent()){
//				textBox.displayBox(255);
//				textBox.displayText();
//				fill(0,0,0);
//			} else {
//				fill(r,g,b);
//			}
//			rect(xpos,ypos,width,height);
//		}
//		
//		boolean overEvent() {
//			if (mouseX > xpos && mouseX < xpos + width && mouseY > ypos && mouseY < ypos + height) {
//				return true;
//			} else if(mouseX > xpos && mouseX < xpos + Math.abs(width) && mouseY < ypos && mouseY > ypos+ height){
//			    return true;
//			} else {
//			    return false;
//			}
//		}
//		
//	}
//	
//	class HScrollbar {
//
//		  int swidth, sheight;    // width and height of bar
//		  float xpos, ypos;       // x and y position of bar
//		  float spos, newspos;    // x position of slider
//		  float sposMin, sposMax; // max and min values of slider
//		  int loose;              // how loose/heavy
//		  boolean over;           // is the mouse over the slider?
//		  boolean locked;
//		  float ratio;
//
//		  HScrollbar (float xp, float yp, int sw, int sh, int l) {
//		    swidth = sw;
//		    sheight = sh;
//		    int widthtoheight = sw - sh;
//		    ratio = (float)sw / (float)widthtoheight;
//		    xpos = xp;
//		    ypos = yp-sheight/2;
//		    spos = xpos;// + swidth/2 - sheight/2;
//		    newspos = spos;
//		    sposMin = xpos;
//		    sposMax = xpos + swidth - sheight;
//		    loose = l;
//		  }
//
//		  void update() {
//		    if (overEvent()) {
//		      over = true;
//		    } else {
//		      over = false;
//		    }
//		    if (mousePressed && over) {
//		      locked = true;
//		    }
//		    if (!mousePressed) {
//		      locked = false;
//		    }
//		    if (locked) {
//		      newspos = constrain(mouseX-sheight/2, sposMin, sposMax);
//		    }
//		    if (abs(newspos - spos) > 1) {
//		      spos = spos + (newspos-spos)/loose;
//		    }
//		  }
//
//		  float constrain(float val, float minv, float maxv) {
//		    return min(max(val, minv), maxv);
//		  }
//
//		  boolean overEvent() {
//		    if (mouseX > xpos && mouseX < xpos+swidth &&
//		       mouseY > ypos && mouseY < ypos+sheight) {
//		      return true;
//		    } else {
//		      return false;
//		    }
//		  }
//
//		  void display() {
//		    noStroke();
//		    fill(204);
//		    rect(xpos, ypos, swidth, sheight);
//		    if (over || locked) {
//		      fill(0, 0, 0);
//		    } else {
//		      fill(102, 102, 102);
//		    }
//		    rect(spos, ypos, sheight, sheight);
//		  }
//
//		  public float getPos() {
//		    // Convert spos to be values between
//		    // 0 and the total width of the scrollbar
//		    return spos * ratio;
//		  }
//		}
//
//	class Button{
//		
//		int width, height;
//		float xpos, ypos;
//		int currentCycle = 0;
//		int cycles;
//		boolean mouseWasNotPressed;
//		TextBox textBox;
//		String name;
//		
//		public Button(float xpos, float ypos, int width, int height,String name,int cycle,int fontSize) {
//			this.width = width;
//			this.height = height;
//			this.xpos = xpos;
//			this.ypos = ypos;
//			this.name = name;
//			cycles = cycle;
//			textBox = new TextBox(xpos, ypos, width, height,false, name, fontSize);
//			
//			mouseWasNotPressed = true;
//
//		}
//		
//		public void setName(String name){
//			this.name = name;
//		}
//
//		public void displayButton(){
//			stroke(0);
//				fill(buttons.get(name).getColor().getRed(),buttons.get(name).getColor().getGreen(),buttons.get(name).getColor().getBlue());
//				textBox.newText(buttons.get(name).getName()); 
//				
//			if(overButton() && mouseWasNotPressed && mousePressed){
//				currentCycle += 1;
//				currentCycle %= cycles;
//				mouseWasNotPressed = !mousePressed;
//			} else if (!mouseWasNotPressed && !mousePressed){
//				mouseWasNotPressed = true;
//			}
//			
//			rect(xpos,ypos,width,height,2);
//			textBox.displayText();
//		}
//		
//		public boolean isClicked(){
//			if(overButton() && mousePressed){
//				return true;
//			}
//			else{
//				return false;
//			}
//		}
//		
//		public int getCurrentCycle(){
//			return currentCycle;
//		}
//
//		public boolean overButton() {
//		    if (mouseX > xpos && mouseX < xpos+width &&
//		       mouseY > ypos && mouseY < ypos+height) {
//		      return true;
//		    } else {
//		      return false;
//		    }
//		}
//		
//
//	}
//
//	
//}
//
