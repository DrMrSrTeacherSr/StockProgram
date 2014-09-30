package utilities;

import processing.core.PApplet;
import processing.core.PFont;

public class TextBox {
	
	private PApplet window;
	
	protected float width;
	protected float height;

	protected float fontSize;
	protected float xpos;

	protected float ypos;
	
	protected String text;
	protected PFont f;
	
	protected boolean adjusting;
	
	public TextBox(PApplet window, float xpos, float ypos, float width, float height, boolean adjusting, String text,float fontSize){
		this.window = window;
		this.xpos = xpos;
		this.ypos = ypos;
		this.width = width;
		this.height = height;
		this.text = text;
		f = window.createFont("Arial",fontSize,true);
 		this.fontSize = fontSize;
 		this.adjusting = adjusting;
	}

	
	public void newText(String newText){
		text = newText;
	}
	
	public void displayText(){
		window.stroke(0);
	    window.textFont(f,fontSize);
	    window.fill(0);
	    float newFontSize = fontSize;
	    while(window.textWidth("" + text) > width && !adjusting){
	    	window.textFont(f,newFontSize--);
	    }
	    window.text("" + text,xpos + 2,ypos + fontSize);
	    
	    if(adjusting){
			width = window.textWidth(text) + 4;
		}	
	}
	
	public void displayBox(int alpha){
		window.stroke(0);
	    window.fill(255,alpha);
	    window.rect(xpos, ypos, width, height,2);
	}
	
}