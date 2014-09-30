package utilities;

import java.awt.Color;

import processing.core.PApplet;


public class HoverBox{
	
	private PApplet window;
	
	private float xpos, ypos, width, height;
	private TextBox textBox;
	private int r,g,b;
	
	public HoverBox(PApplet window, float xpos, float ypos, float width, float height,String str,Color color) {
		this.window = window;
		this.xpos = xpos;
		this.ypos = ypos;
		this.width = width;
		this.height = height;
		int yp;
		if(ypos - 85 < 0){
			yp = (int) (ypos + 25);
		} else{
			yp = (int) (ypos - 85);
		}
		textBox = new TextBox(window, xpos + 5,yp, (float) (window.textWidth(str)/1.1),90,false,str,12);
		r = color.getRed();
		g  = color.getGreen();
		b = color.getBlue();
	}

	public void drawHoverBox(){
		if(overEvent()){
			textBox.displayBox(255);
			textBox.displayText();
			window.fill(0,0,0);
		} else {
			window.fill(r,g,b);
		}
		window.rect(xpos,ypos,width,height);
	}
	
	boolean overEvent() {
		if (window.mouseX > xpos && window.mouseX < xpos + width && window.mouseY > ypos && window.mouseY < ypos + height) {
			return true;
		} else if(window.mouseX > xpos && window.mouseX < xpos + Math.abs(width) && window.mouseY < ypos && window.mouseY > ypos+ height){
		    return true;
		} else {
		    return false;
		}
	}
	
}