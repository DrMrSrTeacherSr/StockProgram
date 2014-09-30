//import java.util.ArrayList;
//import java.util.HashMap;
//
//import processing.core.*;
//
//public class Display extends PApplet{
//	private static final long serialVersionUID = 1L;
//	
//	private Analysis analysis;
//	
//	private int bottomThickness = 150;
//	private int sideThickness = 10;
//	private int boxSeperation = 5;
//	private HScrollbar scrollbar;
//	private int box1X, box1Y, box1H, box1W,box2X, box2Y, box2H, box2W;
//	private TextBox numberOfPointsShown;
//
//	HashMap<String, ArrayList<DataPoint>> mapOfLists = new HashMap<String, ArrayList<DataPoint>>();
//	private float stockRange = 325;
//	private Button logButton;
//	private TextBox testEditableBox;
//	private String typed = "";
//	private String ticker = "DIA";
//	private TextBox stockTicker;
//	private String lastTicker = "DIA";
//	private double zigzagPercent = 3;
//	
//	public void setup() {
//		if(!mapOfLists.containsKey(ticker)){		
//			mapOfLists.put(ticker,new DataGrabber(ticker).run());
//			if(mapOfLists.get(ticker).size() == 0){
//				mapOfLists.remove(ticker);
//				ticker = lastTicker;
//				changeShownStock(ticker);
//		  }	  
//		  getDiffLists(ticker,stockRange);
//		  analysis = new Analysis(mapOfLists.get(ticker),mapOfLists.get("arith" + ticker),mapOfLists.get("log" + ticker));	
//		  mapOfLists.put(ticker +"zigzag", analysis.zigzag(zigzagPercent));
//		  System.out.println("new");
//		} else {	
//			System.out.println("old");
//		}
//		
//		  size(1400, 900);
//		  background(80,87,79);
//		  
//		    box1X = sideThickness;
//		    box1Y = sideThickness;
//		    box1W = width - 2 * sideThickness;
//		    box1H = height - sideThickness - bottomThickness;
//		    
//		    box2X = sideThickness;
//		    box2Y = height - bottomThickness + boxSeperation;
//		    box2H = bottomThickness - sideThickness-boxSeperation;
//		    box2W = width - 2 * sideThickness;
//		  
//		  
//		  //scrollbar = new HScrollbar(box2X + 10, box2Y + 15, box2W - 20, 15, 10);
//		  logButton = new Button(box2X + 10 ,box2Y + 60,75,35,"Arith","Log",11,95,126,75,155,201);
//		  testEditableBox = new TextBox(box2X + 45,box2Y + 30,50,20,"",true,14);
////		  numberOfPointsShown = new TextBox(box2X + 10,box2Y + 30, 18, "" + getSliderPos(), false,14);
//		  stockTicker = new TextBox(box1X + 10, box1Y + 2,18,ticker + ": " + mapOfLists.get(ticker).get(0).getClosingPrice(),false,14);
//		  
//		
//		  
//	}
//	
//	public void changeShownStock(String ticker){
//		if(!mapOfLists.containsKey(ticker)){		
//			mapOfLists.put(ticker,new DataGrabber(ticker).run());
//			if(mapOfLists.get(ticker).size() == 0){
//				mapOfLists.remove(ticker);
//				ticker = lastTicker;
//				changeShownStock(ticker);
//			}	  
//			lastTicker = ticker;
//			
//		    getDiffLists(ticker,stockRange);
//		    analysis = new Analysis(mapOfLists.get(ticker),mapOfLists.get("arith" + ticker),mapOfLists.get("log" + ticker));	
//		    mapOfLists.put(ticker +"zigzag", analysis.zigzag(zigzagPercent));
//		    System.out.println("new");
//		} else {	
//			System.out.println("old");
//		}
//		this.ticker = ticker;
//		stockTicker.newText(ticker + ": " + mapOfLists.get(ticker).get(0).getClosingPrice());
//	}
//	
//	public void keyPressed() {
//		testEditableBox.keyPressed();
//	}
//	
//	private void getDiffLists(String listName, double stockRange){
//		ArrayList<DataPoint> rawList = mapOfLists.get(listName);
//		ArrayList<DataPoint> arithList = new ArrayList<DataPoint>();
//		ArrayList<DataPoint> logList = new ArrayList<DataPoint>();
//		double maxHigh = rawList.get(0).getHighestPrice();
//		double maxLow = rawList.get(0).getLowestPrice();
//		double maxDiff =  maxHigh - maxLow;
//		DataPoint point;
//		
//		for(int i = 0; i < rawList.size(); i++){
//			double high = rawList.get(i).getHighestPrice();
//			if(high > maxHigh)
//				maxHigh = high;
//			if(high < maxLow){
//				maxLow = high;
//			}
//			if (maxHigh - maxLow > maxDiff)
//				maxDiff = maxHigh - maxLow ;
//		}
//		
//		for(int i = 0; i < rawList.size(); i++){
//			double high = rawList.get(i).getHighestPrice();
//			double open = rawList.get(i).getOpeningPrice();
//			double close = rawList.get(i).getClosingPrice();
//			double low = rawList.get(i).getLowestPrice();
//			
//			double  logHeight, arithHeight;
//			double  logOpen, arithOpen;
//			double  logClose, arithClose;
//			double  logLow, arithLow;
//			double logBase = Math.E;
//			
//			logHeight =  (stockRange * Math.log(1 + (logBase - 1) * (high - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//			logOpen =  (stockRange * Math.log(1 + (logBase - 1) * (open - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//			logClose =  (stockRange * Math.log(1+ (logBase - 1) * (close - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//			logLow =  (stockRange * Math.log(1+ (logBase - 1) * (low - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//
//			point = new DataPoint(rawList.get(i).getDate(), logOpen, logHeight, logLow, logClose,rawList.get(i).getVolume(),rawList.get(i).getId());
//            logList.add(point);
//            
//            
//            arithHeight = (stockRange * (high - maxLow)/(maxHigh - maxLow));
//            arithOpen = (stockRange * (open - maxLow)/(maxHigh - maxLow));
//            arithClose =  (stockRange * (close - maxLow)/(maxHigh - maxLow));
//            arithLow =(stockRange * (low - maxLow)/(maxHigh - maxLow));
//			
//			point = new DataPoint(rawList.get(i).getDate(), arithOpen, arithHeight, arithLow, arithClose,rawList.get(i).getVolume(),rawList.get(i).getId());
//            arithList.add(point);
//            
//		}	
//		mapOfLists.put("log" + listName, logList);
//		mapOfLists.put("arith" + listName, arithList);
//	}
//			
//	public void draw() {
//		background(80,87,79);
//		drawBackground();
////		drawScrollbar();
//		drawText();
//		drawButtons();
//		drawVolumes();
//		drawStocks();
//		
//	}
//	
//	
//	private void drawVolumes(){
//		drawVolume(ticker,box1Y + 20 + stockRange + 125,125);
//	}
//	
//	private void drawStocks(){
//		drawCandle(ticker,box1Y + 20, stockRange);
//		drawZigzag(ticker);	
//		drawLine(ticker, box1Y + 20 + stockRange + 135,box1H - (box1Y + 20 + stockRange + 135 + 20));	
//		
//	}
//	
//	private void drawZigzag(String listName){
//		stroke(0);
//		fill(255);
//		ArrayList<DataPoint> list = mapOfLists.get(listName);
//		ArrayList<DataPoint> zigzag = mapOfLists.get(listName + "zigzag");
//
//
//		
//			if(logButton.hasBeenClicked == true){
//				for(int i = 1; i < zigzag.size(); i++){
//					
//					line((box1W - 20) * (list.size() - zigzag.get(i - 1).getId() -1)/list.size() + 17 + ((box1W - 20) * (1)/list.size())/2, 
//				
//							(float) mapOfLists.get("log"+listName).get(zigzag.get(i - 1).getId()).getCurrentTempClose(),
//				 
//							(box1W - 20) * (list.size() - zigzag.get(i).getId() - 1)/list.size() + 17 + ((box1W - 20) * (1)/list.size())/2, 
//				 
//							(float) mapOfLists.get("log"+listName).get(zigzag.get(i).getId()).getCurrentTempClose());
//				}
//			} else {
//				for(int i = 1; i < zigzag.size(); i++){
//				
//					line((box1W - 20) * (list.size() - zigzag.get(i - 1).getId() -1)/list.size() + 17 + ((box1W - 20) * (1)/list.size())/2, 
//				
//							(float) mapOfLists.get("arith"+listName).get(zigzag.get(i - 1).getId()).getCurrentTempClose(),
//				 
//							(box1W - 20) * (list.size() - zigzag.get(i).getId() - 1)/list.size() + 17 + ((box1W - 20) * (1)/list.size())/2, 
//				 
//							(float) mapOfLists.get("arith"+listName).get(zigzag.get(i).getId()).getCurrentTempClose());
//				}
//			}
//	}
//	
//	private void drawLine(String listName, float topOffset,float stockRange){
//		stroke(0);
//		fill(255);
//		ArrayList<DataPoint> list = mapOfLists.get(listName);
//		double maxHigh;
//		double maxLow;
//		double offset = topOffset;
//		maxHigh = list.get(0).getHighestPrice();
//		maxLow = list.get(0).getLowestPrice();
//		double maxDiff =  maxHigh - maxLow;
//
//		double fixedCloseOne;
//		double fixedCloseTwo;
//		
//
//			for(int i = 0; i < (list.size()/251.0 * list.size()); i++){
//				double high = list.get(i).getClosingPrice();
//				if(high > maxHigh)
//					maxHigh = high;
//				if(high < maxLow){
//					maxLow = high;
//				}
//				if (maxHigh - maxLow > maxDiff)
//					maxDiff = maxHigh - maxLow ;
//			}
//		
//
//		
//		for(int i = 1; i < (list.size()/251.0 *list.size()); i++){
//			double closeOne = list.get(i-1).getClosingPrice();
//			double closeTwo = list.get(i).getClosingPrice();
//			double logBase = Math.E;
//	
//			
//			if(logButton.hasBeenClicked == true){
//				fixedCloseOne =  (stockRange *  Math.log(1+ (logBase - 1) * (closeOne - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//				fixedCloseTwo =  (stockRange *  Math.log(1+ (logBase - 1) * (closeTwo - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//
//				//logList.get(i-1).setCurrentTempClose((offset + stockRange - fixedCloseOne));
//				//logList.get(i).setCurrentTempClose((offset + stockRange - fixedCloseTwo));
//
//				
//
//			} else {
//				fixedCloseOne =  (stockRange *(closeOne - maxLow)/(maxHigh - maxLow));
//				fixedCloseTwo =  (stockRange *(closeTwo - maxLow)/(maxHigh - maxLow));
//				
//				//arithList.get(i).setCurrentTempClose((offset + stockRange - fixedCloseOne));
//				//arithList.get(i).setCurrentTempClose((offset + stockRange - fixedCloseTwo));
//
//			}
//			
//			
//		line((float)((box1W - 20) * ((list.size()/250.0 * list.size()) - (i - 1) - 1)/(list.size()/250.0 * list.size()) + 17 ), 
//					
//			 (float) (offset + stockRange - fixedCloseOne),
//				 
//			 (float)((box1W - 20) * ((list.size()/250.0 * list.size()) - i - 1)/(list.size()/250.0 * list.size()) + 17 ), 
//				 
//			 (float) (offset + stockRange - fixedCloseTwo));
//			
//						
//		}	
//		
//	}
//	
//	private void drawCandle(String listName,float topOffset,float stockRange){
//		stroke(0);
//		fill(255);
//		ArrayList<DataPoint> list = mapOfLists.get(listName);
//		ArrayList<DataPoint> logList = mapOfLists.get("log"+listName);
//		ArrayList<DataPoint> arithList = mapOfLists.get("arith"+listName);
//		double maxHigh;
//		double maxLow;
//		double offset = topOffset;
//		maxHigh = list.get(0).getHighestPrice();
//		maxLow = list.get(0).getLowestPrice();
//		double maxDiff =  maxHigh - maxLow;
//			
//		double fixedHeight;
//		double fixedOpen;
//		double fixedClose;
//		double fixedLow;
//		
//		for(int i = 0; i <  list.size(); i++){
//			double high = list.get(i).getHighestPrice();
//			if(high > maxHigh)
//				maxHigh = high;
//			if(high < maxLow){
//				maxLow = high;
//			}
//			if (maxHigh - maxLow > maxDiff)
//				maxDiff = maxHigh - maxLow ;
//		}
//		
//		
//		for(int i = 0; i < list.size(); i++){
//			double high = list.get(i).getHighestPrice();
//			double open = list.get(i).getOpeningPrice();
//			double close = list.get(i).getClosingPrice();
//			double low = list.get(i).getLowestPrice();
//			double logBase = Math.E;
//	
//			
//			if(logButton.hasBeenClicked == true){
//				fixedHeight =  (stockRange * Math.log(1 + (logBase - 1) * (high - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//				fixedOpen =  (stockRange * Math.log(1 + (logBase - 1) * (open - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//				fixedClose =  (stockRange *  Math.log(1+ (logBase - 1) * (close - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//				fixedLow =  (stockRange * Math.log(1+ (logBase - 1) * (low - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//				
//				logList.get(i).setCurrentTempHigh((offset + stockRange - fixedHeight));
//				logList.get(i).setCurrentTempOpen((offset + stockRange - fixedOpen));
//				logList.get(i).setCurrentTempClose((offset + stockRange - fixedClose));
//				logList.get(i).setCurrentTempLow((offset + stockRange - fixedLow));
//				
//				
//
//			} else {
//				fixedHeight = (stockRange * (high - maxLow)/(maxHigh - maxLow));
//				fixedOpen = (stockRange * (open - maxLow)/(maxHigh - maxLow));
//				fixedClose =  (stockRange *(close - maxLow)/(maxHigh - maxLow));
//				fixedLow =(stockRange * (low - maxLow)/(maxHigh - maxLow));
//				
//				arithList.get(i).setCurrentTempHigh((offset + stockRange - fixedHeight));
//				arithList.get(i).setCurrentTempOpen((offset + stockRange - fixedOpen));
//				arithList.get(i).setCurrentTempClose((offset + stockRange - fixedClose));
//				arithList.get(i).setCurrentTempLow((offset + stockRange - fixedLow));
//			}
//
//			if(open < close){
//				fill(0,255,0);
//			}else{
//				fill(255,0,0);
//			}
//			
//			line((box1W - 20) * (list.size() - i -1)/list.size() + 17 + ((box1W - 20) * (1)/list.size())/2, 
//					
//				 (float) (offset + stockRange - fixedHeight),
//				 
//				 (box1W - 20) * (list.size() - i -1)/list.size()+ 17 + ((box1W - 20) * (1)/list.size())/2, 
//				 
//				 (float) (offset + stockRange - fixedLow));
//			
//			rect((box1W - 20) * (list.size() - i-1)/list.size() + 18,
//					
//					 (float) (offset + stockRange -  fixedOpen),
//					 
//					 (box1W - 20) * (1)/list.size() - 2,
//					 
//					(float) ((float) (offset + stockRange - fixedClose) - (offset + stockRange -  fixedOpen)));
//						
//		}	
//		
//	}
//	
//	private void drawVolume(String listName,float topOffset,float range){
//		stroke(0);
//		fill(255);
//		ArrayList<DataPoint> rawList = mapOfLists.get(listName);
//		int maxVolume = rawList.get(0).getVolume();
//		for(int i = 0; i <rawList.size(); i++){
//			int volume = rawList.get(i).getVolume();
//			if(volume > maxVolume)
//				maxVolume = volume;
//		}
//		int height;
//		for(int i = 1; i <= rawList.size(); i++){
//			int volume = rawList.get(i-1).getVolume();
//			if(volume != 0)	{
//				height = (int) (range * ((double)volume)/maxVolume);
//			} else {
//				height = 0;
//			}
//
//			
//			rect((box1W - 20) * (rawList.size()-i)/rawList.size()  + 18,
//				  topOffset,
//				  (box1W - 20)/rawList.size() - 2,
//				  -height);
//		}
//		
//		
//		
//	}
//
//	private void drawButtons(){
//		logButton.displayBox();
//		logButton.displayText();
//	}
//
//	private void drawText(){
////		numberOfPointsShown.newText("" + getSliderPos());
////		numberOfPointsShown.displayBox();
////		numberOfPointsShown.displayText();
//		testEditableBox.newText(typed);
//		testEditableBox.displayBox();
//		testEditableBox.displayText();
//		stockTicker.displayText();
//		
//	}
//	
//	private void drawBackground(){
//		noStroke();
//	    fill(255);
//	    
//		rect(box1X,box1Y,box1W,box1H);
//		rect(box2X, box2Y,box2W,box2H);
//		
//		stroke(0);
//		//line(box1X + 10,box1Y + 20 + stockRange + 135, box1X + box1W -  10, box1Y + 20 + stockRange + 135);
//		//line(box1X + 10, box1X + 20, box1X + box1W -  10,box1X + 20);
//		//line(box1X + 10, (float)(box1X + 20 + stockRange), box1X + box1W -  10, (float)(box1X + 20 + stockRange));
//
//	}
//	
////	private void drawScrollbar(){
////		scrollbar.update();
////		scrollbar.display();
////	}
//
//	
////	private int getSliderPos(){
////		return (int)((((scrollbar.getPos() - 21)/(box2W - 20)))*(mapOfLists.get(ticker).size() - 1) + 1.5);
////	}
//
//	class TextBox {
//		float width;
//		int height, fontSize;
//		float xpos, ypos;
//		String text;
//		PFont f;
//		boolean isEditable;
//		boolean adjusting = false;
//		
//		
//		public TextBox(float xpos, float ypos, int height, String text, boolean isEditable,int fontSize){
//			this.xpos = xpos;
//			this.ypos = ypos;
//			this.height = height;
//			this.text = text;
//			this.width = textWidth(text) + 4;
//			f = createFont("Arial",fontSize,true);
//			this.isEditable = isEditable;
//			adjusting = true;
//			this.fontSize = fontSize;
//		}	
//		
//		public TextBox(float xpos, float ypos, int width, int height, String text, boolean isEditable,int fontSize){
//			this.xpos = xpos;
//			this.ypos = ypos;
//			this.width = width;
//			this.height = height;
//			this.text = text;
//			f = createFont("Arial",fontSize,true);
//	 		this.isEditable = isEditable;
//	 		this.fontSize = fontSize;
//		}
//	
//		public void keyPressed() {
//
//			 if (key == BACKSPACE) {
//				 if (typed.length() > 0) {
//					 typed = typed.substring(0, typed.length()-1);
//				 }
//			 }
//			
//			 
//			 if (key == ENTER) {
//				// lastTicker = ticker;
//				// ticker = typed;
//				 changeShownStock(typed);
//				 typed = "";
//			 }
//			 else if (typed.length() < 4 && keyCode != SHIFT && keyCode != BACKSPACE){
//				 typed = typed+key;
//				 typed = typed.toUpperCase();
//			 }
//		}
//		
//		public void newText(String newText){
//			text = newText;
//		}
//		
//		public void displayText(){
//			stroke(0);
//		    textFont(f,fontSize);
//		    fill(0);
//		    text("" + text,xpos + 2,ypos + fontSize);
//		    if(adjusting){
//				width = textWidth(text) + 4;
//			}	
//		}
//		
//		public void displayBox(){
//			stroke(0);
//		    fill(255,0);
//		    rect(xpos, ypos, width, height);
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
//	class Button extends TextBox{
//		
//		int width, height;
//		float xpos, ypos;
//		int r1,g1,b1,r2,g2,b2;
//		boolean isClicked;
//		String str1 ;
//		String str2;
//		boolean hasBeenClicked;
//		boolean mouseWasNotPressed;
//		
//		public Button(float xpos, float ypos, int width, int height,String str1,String str2, int r1, int g1, int b1, int r2, int g2, int b2) {
//			super(xpos,ypos,width,height,"",false,20);
//			this.width = width;
//			this.height = height;
//			this.xpos = xpos;
//			this.ypos = ypos;
//			this.r1 = r1;
//			this.g1 = g1;
//			this.b1 = b1;
//			this.r2 = r2;
//			this.g2 = g2;
//			this.b2 = b2;
//			isClicked = false;
//			this.str1 = str1;
//			this.str2 = str2;
//			hasBeenClicked = false;
//			mouseWasNotPressed = true;
//
//		}
//
//		public void displayBox(){
//			stroke(0);
//			if(!hasBeenClicked){
//				fill(r1,g1,b1);
//				text = str1; 
//			} else {
//				fill(r2,g2,b2);
//				text = str2; 
//			}
//			if(overButton() && mouseWasNotPressed && mousePressed){
//				hasBeenClicked = !hasBeenClicked;
//				drawStocks();
//			}
//			mouseWasNotPressed = !mousePressed;
//			rect(xpos,ypos,width,height);
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
//		public void mouseReleased(){
//			
//		}
//
//		public boolean overButton() {
//		    if (mouseX > xpos && mouseX < xpos+width &&
//		       mouseY > ypos && mouseY < ypos+height) {
//		      return true;
//		    } else {
//		      return false;
//		    }
//		  }
//	}
//
//	
//}
//
