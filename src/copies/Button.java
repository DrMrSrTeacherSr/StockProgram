//package utilities;
//
//import java.awt.Color;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import processing.core.PApplet;
//
//public class Button{
//	
//	PApplet window;
//	
//	int width, height;
//	float xpos, ypos;
//	
//	ArrayList<String> names;
//	HashMap<String,ArrayList<Integer>> variables;
//	HashMap<String,Color> color;
//	
//	int currentCycle = 0;
//	
//	boolean mouseWasNotPressed;
//	
//	String name;
//	TextBox textBox;
//	
//	
//	public Button(PApplet window, float xpos, float ypos, int width, int height, String name,int cycle,int fontSize) {
//		this.window = window;
//		this.width = width;
//		this.height = height;
//		this.xpos = xpos;
//		this.ypos = ypos;
//		this.name = name;
//		cycles = cycle;
//		textBox = new TextBox(window, xpos, ypos, width, height,false, name, fontSize);
//		
//		mouseWasNotPressed = true;
//
//	}
//	
//	public void setName(String name){
//		this.name = name;
//	}
//
//	public void displayButton(){
//		window.stroke(0);
//			window.fill(buttons.get(name).getColor().getRed(),buttons.get(name).getColor().getGreen(),buttons.get(name).getColor().getBlue());
//			textBox.newText(buttons.get(name).getName()); 
//			
//		if(overButton() && mouseWasNotPressed && window.mousePressed){
//			currentCycle += 1;
//			currentCycle %= cycles;
//			mouseWasNotPressed = !window.mousePressed;
//		} else if (!mouseWasNotPressed && !window.mousePressed){
//			mouseWasNotPressed = true;
//		}
//		
//		window.rect(xpos,ypos,width,height,2);
//		textBox.displayText();
//	}
//	
//	public boolean isClicked(){
//		if(overButton() && window.mousePressed){
//			return true;
//		}
//		else{
//			return false;
//		}
//	}
//	
//	public int getCurrentCycle(){
//		return currentCycle;
//	}
//
//	public boolean overButton() {
//	    if (window.mouseX > xpos && window.mouseX < xpos+width &&
//	       window.mouseY > ypos && window.mouseY < ypos+height) {
//	      return true;
//	    } else {
//	      return false;
//	    }
//	}
//	
//
//}
//
