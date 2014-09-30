package utilities;

import processing.core.PApplet;


public class EditableTextBox extends TextBox{

	private PApplet window;
	
	private String value;
	private int letterLimit;
	private String id;
	
	public EditableTextBox(PApplet window, float xpos, float ypos, int width, int height, boolean adjusting, int fontSize, int letterLimit, String id) {
		super(window,xpos, ypos, width, height, adjusting, "", fontSize);
		this.window = window;
		value = "";
		this.letterLimit = letterLimit;
		this.id = id;
	}
	
	public EditableTextBox(PApplet window, TextBox textbox, int letterLimit) {
		super(window,textbox.xpos, textbox.ypos, textbox.width, textbox.height, textbox.adjusting, "", textbox.fontSize);
		this.window = window;
		value = "";
		this.letterLimit = letterLimit;
	}
	
	public boolean overEvent() {
		if (window.mouseX > xpos && window.mouseX < xpos + width && window.mouseY > ypos && window.mouseY < ypos + height) {
			return true;
		} else if(window.mouseX > xpos && window.mouseX < xpos + Math.abs(width) && window.mouseY < ypos && window.mouseY > ypos+ height){
		    return true;
		} else {
		    return false;
		}
	}
	
	public String keyPressed() {
		if(overEvent()){
			if (window.key == window.BACKSPACE) {
				if (value.length() > 0) {
					value = value.substring(0, value.length()-1);
				}
			}
		
		 
			if (window.key == window.ENTER) {
				String returnValue = value;
				value = "";
				return returnValue;
			}
			else if (value.length() < letterLimit && window.keyCode != window.SHIFT && window.keyCode != window.BACKSPACE || (window.textWidth(value + window.key) > width && !super.adjusting && value.length() < letterLimit)){
				value = value + window.key;
				value = value.toUpperCase();
			}
			newText(value);
		}
		return null;
	}
}