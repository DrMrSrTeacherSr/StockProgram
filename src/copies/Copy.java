package copies;
//import java.awt.Component;
//import java.util.ArrayList;
//
//import processing.core.*;
//
//public class Display extends PApplet{
//	private static final long serialVersionUID = 1L;
//	
//	private DataGrabber grabber;
//	private Analysis analysis;
//	
//	private int bottomThickness = 150;
//	private int sideThickness = 10;
//	private int boxSeperation = 5;
//	private HScrollbar scrollbar;
//	private int box1X, box1Y, box1H, box1W,box2X, box2Y, box2H, box2W;
//	private TextBox numberOfPointsShown;
//	private int numberOfPoints; 
//	private ArrayList<DataPoint> rawList = new ArrayList<DataPoint>();
//	private ArrayList<DataPoint> arithList = new ArrayList<DataPoint>();
//	private ArrayList<DataPoint> logList = new ArrayList<DataPoint>();
//	ArrayList<DataPoint> myCussingList = new ArrayList<DataPoint>();
//	private int stockRange = 150;
//	private Button logButton;
//	private TextBox testEditableBox;
//	private String typed = "";
//	private String ticker = "DIA";
//	private TextBox stockTicker;
//	private String lastTicker = "DIA";
//	
//	public void setup() {
//		  grabber = new DataGrabber(ticker);
//		  rawList = grabber.run();
//		  if(rawList.size() == 0){
//			  ticker = lastTicker;
//			  setup();
//		  }
//		  numberOfPoints = rawList.size();
//		  getDiffLists();
//		  analysis = new Analysis(rawList,arithList,logList);
//		 // cussThisImDoingItMyWay();
//		  
//		  
//		  size(1400, 800);
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
//		  scrollbar = new HScrollbar(box2X + 10, box2Y + 15, box2W - 20, 15, 10);
//		  logButton = new Button(box2X + 10 ,box2Y + 60,75,35,"Arith","Log",11,95,126,75,155,201);
//		  testEditableBox = new TextBox(box2X + 45,box2Y + 30,50,20,"",true,14);
//		  numberOfPointsShown = new TextBox(box2X + 10,box2Y + 30, 18, "" + getSliderPos(), false,14);
//		  stockTicker = new TextBox(box1X + 10, box1Y + 10,18,ticker + ": " + rawList.get(0).getClosingPrice(),false,14);
//		  
//	}
//	
//	public void keyPressed() {
//		testEditableBox.keyPressed();
//	}
//	
//	private void getDiffLists(){
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
//			logHeight =  (stockRange * 2 * Math.log(1 + (logBase - 1) * (high - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//			logOpen =  (stockRange * 2 * Math.log(1 + (logBase - 1) * (open - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//			logClose =  (stockRange * 2 * Math.log(1+ (logBase - 1) * (close - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//			logLow =  (stockRange * 2 *  Math.log(1+ (logBase - 1) * (low - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//
//			point = new DataPoint(rawList.get(i).getDate(), logOpen, logHeight, logLow, logClose,rawList.get(i).getVolume(),rawList.get(i).getId());
//            logList.add(point);
//            
//            
//            arithHeight = (stockRange * 2 * (high - maxLow)/(maxHigh - maxLow));
//            arithOpen = (stockRange * 2 * (open - maxLow)/(maxHigh - maxLow));
//            arithClose =  (stockRange * 2 * (close - maxLow)/(maxHigh - maxLow));
//            arithLow =(stockRange * 2 * (low - maxLow)/(maxHigh - maxLow));
//			
//			point = new DataPoint(rawList.get(i).getDate(), arithOpen, arithHeight, arithLow, arithClose,rawList.get(i).getVolume(),rawList.get(i).getId());
//            arithList.add(point);
//            
//		}		
//	}
//			
//	public void draw() {
//		background(80,87,79);
//		drawBackground();
//		drawScrollbar();
//		drawText();
//		drawButtons();
//		drawVolume();
//		drawStock();
//	}
//	
//	private void drawStock(){
//		stroke(0);
//		fill(255);
//		
//		double maxHigh;
//		double maxLow;
//		float middle = box1Y + (box1H-125)/2;
//		maxHigh = rawList.get(0).getHighestPrice();
//		maxLow = rawList.get(0).getLowestPrice();
//		double maxDiff =  maxHigh - maxLow;
//			
//		double fixedHeight;
//		double fixedOpen;
//		double fixedClose;
//		double fixedLow;
//		
//		for(int i = 0; i < getSliderPos(); i++){
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
//		
//		//line(box1X + 10, middle + stockRange, box1X + box1W -  10, middle + stockRange);
//		//line(box1X + 10, middle - stockRange, box1X + box1W -  10, middle - stockRange);
//
//		
//		
//		for(int i = 0; i < getSliderPos(); i++){
//			double high = rawList.get(i).getHighestPrice();
//			double open = rawList.get(i).getOpeningPrice();
//			double close = rawList.get(i).getClosingPrice();
//			double low = rawList.get(i).getLowestPrice();
//			double logBase = Math.E;
//	
//			
//			if(logButton.hasBeenClicked == true){
//				fixedHeight =  (stockRange * 2 * Math.log(1 + (logBase - 1) * (high - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//				fixedOpen =  (stockRange * 2 *  Math.log(1 + (logBase - 1) * (open - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//				fixedClose =  (stockRange * 2 *  Math.log(1+ (logBase - 1) * (close - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//				fixedLow =  (stockRange * 2 *  Math.log(1+ (logBase - 1) * (low - maxLow)/(maxHigh - maxLow))/Math.log(logBase));
//				
//				logList.get(i).setCurrentTempHigh((middle + stockRange - fixedHeight));
//				logList.get(i).setCurrentTempOpen((middle + stockRange - fixedOpen));
//				logList.get(i).setCurrentTempClose((middle + stockRange - fixedClose));
//				logList.get(i).setCurrentTempLow((middle + stockRange - fixedLow));
//				
//				
//
//			} else {
//				fixedHeight = (stockRange * 2 * (high - maxLow)/(maxHigh - maxLow));
//				fixedOpen = (stockRange * 2 *  (open - maxLow)/(maxHigh - maxLow));
//				fixedClose =  (stockRange * 2 *  (close - maxLow)/(maxHigh - maxLow));
//				fixedLow =(stockRange * 2 *  (low - maxLow)/(maxHigh - maxLow));
//				
//				arithList.get(i).setCurrentTempHigh((middle + stockRange - fixedHeight));
//				arithList.get(i).setCurrentTempOpen((middle + stockRange - fixedOpen));
//				arithList.get(i).setCurrentTempClose((middle + stockRange - fixedClose));
//				arithList.get(i).setCurrentTempLow((middle + stockRange - fixedLow));
//			}
////			if(i < 500){
//				
////			double h = (stockRange * 2 * (myCussingList.get(i).getHighestPrice() - maxLow)/(maxHigh - maxLow));
////			double o = (stockRange * 2 * (myCussingList.get(i).getOpeningPrice() - maxLow)/(maxHigh - maxLow));
////			double c =  (stockRange * 2 * (myCussingList.get(i).getClosingPrice() - maxLow)/(maxHigh - maxLow));
////			double l =(stockRange * 2 * (myCussingList.get(i).getLowestPrice() - maxLow)/(maxHigh - maxLow));
////			
////			line((box1W - 20) * (getSliderPos() - i -1)/(getSliderPos() + 1) + 17 + ((box1W - 20) * (1)/(getSliderPos() + 1))/2, 
////					
////					 (float) (middle + stockRange + h),
////					 
////					 (box1W - 20) * (getSliderPos() - i -1)/(getSliderPos()+1) + 17 + ((box1W - 20) * (1)/(getSliderPos() + 1))/2, 
////					 
////					 (float) (middle + stockRange + l));
////				
////			rect((box1W - 20) * (getSliderPos() - i-1)/(getSliderPos() + 1) + 18,
////						
////					(float) (middle + stockRange +  o),
////						 
////					(box1W - 20) * (1)/(getSliderPos() + 1) - 2,
////						 
////					(float) ((float) (middle + stockRange + c) - (middle + stockRange +  o)));
////			}
//			
//			if(open < close){
//				fill(0,255,0);
//			}else{
//				fill(255,0,0);
//			}
//			
////			ArrayList<DataPoint> maxs = analysis.getRawMaxs();
////			ArrayList<DataPoint> mins = analysis.getRawMins();
////			 for(int j = 0; j < maxs.size();  j++){
////				 if(arithList.get(i).getId() == maxs.get(j).getId()){
////					 fill(255);
////				 }
////				 if(arithList.get(i).getId() == mins.get(j).getId()){
////					 fill(0);
////				 }
////				 if(arithList.get(i).getId() == mins.get(j).getId() &&(arithList.get(i).getId() == maxs.get(j).getId())){
////					 fill(255/2);
////				 }
////			 }
//			
//			line((box1W - 20) * (getSliderPos() - i -1)/getSliderPos() + 17 + ((box1W - 20) * (1)/getSliderPos())/2, 
//					
//				 (float) (middle + stockRange - fixedHeight),
//				 
//				 (box1W - 20) * (getSliderPos() - i -1)/getSliderPos() + 17 + ((box1W - 20) * (1)/getSliderPos())/2, 
//				 
//				 (float) (middle + stockRange - fixedLow));
//			
//			rect((box1W - 20) * (getSliderPos() - i-1)/getSliderPos() + 18,
//					
//					 (float) (middle + stockRange -  fixedOpen),
//					 
//					 (box1W - 20) * (1)/getSliderPos() - 2,
//					 
//					(float) ((float) (middle + stockRange - fixedClose) - (middle + stockRange -  fixedOpen)));
//						
//		}
////		
////		ArrayList<Line> lines = analysis.getArithMaxTrendLines();
////		
////		for(int i = 0; i < lines.size();i++){
////			ArrayList<DataPoint> points = lines.get(i).getPoints();
////			for(int j = 1;j < points.size(); j++){
////				
////				System.out.println(points.get(j).getId());
////				int p1 = points.get(j-1).getId();
////				int p2 = points.get(j).getId();
////				
////				
////				
////				line((box1W - 20) * (getSliderPos() - p1 -1)/getSliderPos() + 17 + ((box1W - 20) * (1)/getSliderPos())/2, 
////						
////						 (float) arithList.get(p1).getCurrentTempHigh(),
////						 
////						 (box1W - 20) * (getSliderPos() - p2 - 1)/getSliderPos() + 17 + ((box1W - 20) * (1)/getSliderPos())/2, 
////						 
////						 (float) arithList.get(p2).getCurrentTempHigh());
////
////			}
////		}
//		
//		
//
//	}
//	
//	
//	private void cussThisImDoingItMyWay(){
//		ArrayList<DataPoint> list = new ArrayList<DataPoint>();
//		int magicNumber = 100;
//		
//		double close;
//		double high;
//		double low;
//		double open;
//		
//		int counter = 0;
//		
//		for(int i = 0;i < 500;i++){
//			
//			for(int j = 0; j < magicNumber;j++){
//				list.add(rawList.get(i+j));
//			}
//			System.out.println(list.size());
//			
//			close = camsHelperClose(list);
//			high = camsHelperHigh(list);
//			low = camsHelperLow(list);
//			open = camsHelperOpen(list);
//			
//
//			
//			myCussingList.add(new DataPoint(-1, open, high, low, close, -1, i));
//			
//			
//			
////			if(i%10 == 0){
////				System.out.println(": " + i);
////			} else {
////				System.out.print(": " + i);
////			}
//			
//		}
//		
//
//		
////		double differenceHighLow =  low - high; 
////		double differenceOpenClose =  open - close;
//		
//		//System.out.println(open + ": " + high + ": " + low + ": " + close);
//		//double somethingElse = differenceHighLow/differenceOpenClose;
//		//System.out.println(somethingElse);
//		
//	}
//	
//	private double camsHelperClose(ArrayList<DataPoint> list){
//		double bigChanges = 0;
//		int bigCounter = 1;
//		
//		for(int l = 0; l < list.size(); l++)
//		{
//			double changes = 0;
//			int counter = 1;
//			
//			int c = 0;
//			for(int i = 0; i < 8; i++){
//				if(Math.pow(2, i) < l)
//					c=i;
//			}
//			
//			for(int i= 1; i < c; i++){
//			
//				int pre = (int) Math.pow(2, i - 1);
//				int post = (int) Math.pow(2, i);
//				int[] lastPoints = new int[pre + 1];
//			
//				int prePoint = 0;
//				int postPoint = l;
//			
//				for(int j = 0; j <= pre;j++){
//					lastPoints[j] = l *(j)/pre;
//				}
//			
//				for(int j = 1; j < post;j++){
//				
//				
//				
//					for(int k = 1; k <= pre; k++){			
//						if(lastPoints[k] > l *(j)/post){
//							postPoint = lastPoints[k];
//							prePoint =  lastPoints[k-1];
//							k = pre;
//						}
//					
//					}
//				
//					if(prePoint != (int)(l * (j)/post)){
//
//						double predictedPoint = (list.get(prePoint).getClosingPrice() + list.get(postPoint-1).getClosingPrice())/2;
//						int point = (int)(l * (j)/post);
//						changes += Math.abs((point-predictedPoint)/predictedPoint);
//					counter++;
//					}
//					
//				}
//
//			}
//			double numberWithProbablyNoImportance = changes/counter;
//			bigChanges += numberWithProbablyNoImportance;
//			bigCounter++;
//		}
//		
//		double anotherNumberWithProbablyNoImportance = bigChanges/bigCounter;
//		return anotherNumberWithProbablyNoImportance;
//	}
//	private double camsHelperOpen(ArrayList<DataPoint> list){
//		double bigChanges = 0;
//		int bigCounter = 1;
//		
//		for(int l = 0; l < list.size(); l++)
//		{
//			double changes = 0;
//			int counter = 1;
//			
//			int c = 0;
//			for(int i = 0; i < 8; i++){
//				if(Math.pow(2, i) < l)
//					c=i;
//			}
//			
//			for(int i= 1; i < c; i++){
//			
//				int pre = (int) Math.pow(2, i - 1);
//				int post = (int) Math.pow(2, i);
//				int[] lastPoints = new int[pre + 1];
//			
//				int prePoint = 0;
//				int postPoint = l;
//			
//				for(int j = 0; j <= pre;j++){
//					lastPoints[j] = l *(j)/pre;
//				}
//			
//				for(int j = 1; j < post;j++){
//				
//				
//				
//					for(int k = 1; k <= pre; k++){			
//						if(lastPoints[k] > l *(j)/post){
//							postPoint = lastPoints[k];
//							prePoint =  lastPoints[k-1];
//							k = pre;
//						}
//					
//					}
//				
//					if(prePoint != (int)(l * (j)/post)){
//
//						double predictedPoint = (list.get(prePoint).getOpeningPrice() + list.get(postPoint-1).getOpeningPrice())/2;
//						int point = (int)(l * (j)/post);
//						changes += Math.abs((point-predictedPoint)/predictedPoint);
//						counter++;
//					}
//					
//				}
//
//			}
//			double numberWithProbablyNoImportance = changes/counter;
//			bigChanges += numberWithProbablyNoImportance;
//			bigCounter++;
//		}
//		
//		double anotherNumberWithProbablyNoImportance = bigChanges/bigCounter;
//		return anotherNumberWithProbablyNoImportance;
//	}
//	private double camsHelperHigh(ArrayList<DataPoint> list){
//		double bigChanges = 0;
//		int bigCounter = 1;
//		
//		for(int l = 0; l < list.size(); l++)
//		{
//			double changes = 0;
//			int counter = 1;
//			
//			int c = 0;
//			for(int i = 0; i < 8; i++){
//				if(Math.pow(2, i) < l)
//					c=i;
//			}
//			
//			for(int i= 1; i < c; i++){
//			
//				int pre = (int) Math.pow(2, i - 1);
//				int post = (int) Math.pow(2, i);
//				int[] lastPoints = new int[pre + 1];
//			
//				int prePoint = 0;
//				int postPoint = l;
//			
//				for(int j = 0; j <= pre;j++){
//					lastPoints[j] = l *(j)/pre;
//				}
//			
//				for(int j = 1; j < post;j++){
//				
//				
//				
//					for(int k = 1; k <= pre; k++){			
//						if(lastPoints[k] > l *(j)/post){
//							postPoint = lastPoints[k];
//							prePoint =  lastPoints[k-1];
//							k = pre;
//						}
//					
//					}
//				
//					if(prePoint != (int)(l * (j)/post)){
//
//						double predictedPoint = (list.get(prePoint).getHighestPrice() + list.get(postPoint-1).getHighestPrice())/2;
//						int point = (int)(l * (j)/post);
//						changes += Math.abs((point-predictedPoint)/predictedPoint);
//						counter++;
//					}
//					
//				}
//
//			}
//			double numberWithProbablyNoImportance = changes/counter;
//			bigChanges += numberWithProbablyNoImportance;
//			bigCounter++;
//		}
//		
//		double anotherNumberWithProbablyNoImportance = bigChanges/bigCounter;
//		return anotherNumberWithProbablyNoImportance;
//	}
//	private double camsHelperLow(ArrayList<DataPoint> list){
//		double bigChanges = 0;
//		int bigCounter = 1;
//		
//		for(int l = 0; l < list.size(); l++)
//		{
//			double changes = 0;
//			int counter = 1;
//			
//			int c = 0;
//			for(int i = 0; i < 8; i++){
//				if(Math.pow(2, i) < l)
//					c=i;
//			}
//			
//			for(int i= 1; i < c; i++){
//			
//				int pre = (int) Math.pow(2, i - 1);
//				int post = (int) Math.pow(2, i);
//				int[] lastPoints = new int[pre + 1];
//			
//				int prePoint = 0;
//				int postPoint = l;
//			
//				for(int j = 0; j <= pre;j++){
//					lastPoints[j] = l *(j)/pre;
//				}
//			
//				for(int j = 1; j < post;j++){
//				
//				
//				
//					for(int k = 1; k <= pre; k++){			
//						if(lastPoints[k] > l *(j)/post){
//							postPoint = lastPoints[k];
//							prePoint =  lastPoints[k-1];
//							k = pre;
//						}
//					
//					}
//				
//					if(prePoint != (int)(l * (j)/post)){
//
//						double predictedPoint = (list.get(prePoint).getLowestPrice() + list.get(postPoint-1).getLowestPrice())/2;
//						int point = (int)(l * (j)/post);
//						changes += Math.abs((point-predictedPoint)/predictedPoint);
//						counter++;
//					}
//					
//				}
//
//			}
//			double numberWithProbablyNoImportance = changes/counter;
//			bigChanges += numberWithProbablyNoImportance;
//			bigCounter++;
//		}
//		
//		double anotherNumberWithProbablyNoImportance = bigChanges/bigCounter;
//		return anotherNumberWithProbablyNoImportance;
//	}
//	
//	private void drawVolume(){
//		stroke(0);
//		fill(255);
//		int maxVolume = rawList.get(0).getVolume();
//		for(int i = 0; i < getSliderPos(); i++){
//			int volume = rawList.get(i).getVolume();
//			if(volume > maxVolume)
//				maxVolume = volume;
//		}
//		int height;
//		for(int i = 1; i <= getSliderPos(); i++){
//			int volume = rawList.get(i-1).getVolume();
//			if(volume != 0)	{
//				height = (int) (100 * ((double)volume)/maxVolume);
//			} else {
//				height = 0;
//			}
//
//			
//			rect((box1W - 20) * (getSliderPos()-i)/getSliderPos()  + 18,
//				  box1Y + box1H - 25,
//				  (box1W - 20)/getSliderPos() - 2,
//				  -height);
//		}
//		
//	}
//
//	private void drawButtons(){
//		logButton.displayBox();
//		logButton.displayText();
//	}
//
//	private void drawText(){
//		numberOfPointsShown.newText("" + getSliderPos());
//		numberOfPointsShown.displayBox();
//		numberOfPointsShown.displayText();
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
//	}
//	
//	private void drawScrollbar(){
//		scrollbar.update();
//		scrollbar.display();
//	}
//	
//	private int getSliderPos(){
//		return (int)((((scrollbar.getPos() - 21)/(box2W - 20)))*(numberOfPoints - 1) + 1.5);
//	}
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
//				 lastTicker = ticker;
//				 ticker = typed;
//				 typed = "";
//				 setup();
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
//				drawStock();
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
