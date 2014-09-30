package utilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

public class Button{
	
	PApplet window;
	
	int width, height;
	float xpos, ypos;
	
	private ArrayList<String> names;
	private ArrayList<int[]> variables;
	private ArrayList<Color> color;
	
	ArrayList<Integer> currentValues;
	
	int currentCycle = 0;
	
	boolean mouseWasNotPressed = true;
	
	TextBox textBox;
	EditableTextBox[] boxes;
	
	
	public Button(PApplet window, ButtonData data,float xpos, float ypos, int width, int height, int fontSize) {
		this.window = window;
		this.width = width;
		this.height = height;
		this.xpos = xpos;
		this.ypos = ypos;
		names = data.getNames();
		variables = data.getVars();
		color = data.getColors();
				
		textBox = new TextBox(window, xpos, ypos, width, height,false, names.get(currentCycle), fontSize);

		
		
	}

	public void displayButton(){
		window.stroke(0);
		window.fill(color.get(currentCycle).getRed(),color.get(currentCycle).getGreen(),color.get(currentCycle).getBlue());
		String str = names.get(currentCycle)+"\n";
		for(int i = 0; i < variables.get(currentCycle).length; i++){
			str += variables.get(currentCycle)[i] +",";
		}
		textBox.newText(str); 
		window.rect(xpos,ypos,width,height,2);
		textBox.displayText();
			
		
		
		
		if(overButton() && mouseWasNotPressed && window.mousePressed){
			currentCycle += 1;
			currentCycle %= names.size();
			boxes = new EditableTextBox[variables.get(currentCycle).length];
			for(int i = 0; i < variables.get(currentCycle).length; i++){
				boxes[i] = new EditableTextBox(window,xpos + width +20 + 80*i ,ypos,75,35, false, 20, 4, i+"");
				boxes[i].displayBox(255);
				boxes[i].displayText();
			}
			mouseWasNotPressed = !window.mousePressed;
		} else if (!mouseWasNotPressed && !window.mousePressed){
			mouseWasNotPressed = true;
		}
		
		for(int i = 0; i < variables.get(currentCycle).length; i++){
			boxes[i].displayBox(255);
			boxes[i].displayText();
		}
	}
	
	public String keyPressed(){
		String str;
		for(int i = 0; i < variables.get(currentCycle).length; i++){
			str = boxes[i].keyPressed();
			if(str != null && str != ""){
				variables.get(currentCycle)[i] = Integer.parseInt(str);
				return variables.get(currentCycle)[i] + "-" + i;
			}
		}
		
		return null;
	}
	
	
	public boolean isClicked(){
		if(overButton() && window.mousePressed){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int getCurrentCycle(){
		return currentCycle;
	}
	
	public String getName(){
		return names.get(currentCycle);
	}
	
	public int getNumVariables(){
		return variables.get(currentCycle).length;
	}
	
	public int[] getAllVariables(){
		return variables.get(currentCycle);
	}
	
	public boolean setvariable(int position, int value){
		if(variables.get(currentCycle).length < position){
			variables.get(currentCycle)[position] = value;		
			return true;
		} else {
			return false;
		}
	}
	
	public Integer getVariable(int position){
		if(variables.get(currentCycle).length < position){	
			return variables.get(currentCycle)[position];
		} else {
			return null;
		}
	}

	
	public Color getColor(){
		return color.get(currentCycle);
	}

	public boolean overButton() {
	    if (window.mouseX > xpos && window.mouseX < xpos+width &&
	       window.mouseY > ypos && window.mouseY < ypos+height) {
	      return true;
	    } else {
	      return false;
	    }
	}
	
	@Override
	public String toString(){
		String str = names.get(currentCycle);
		for(int value : variables.get(currentCycle)){
			str += "-" + value;
		}
		return str;
	}
	

}

