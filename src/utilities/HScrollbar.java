package utilities;

import processing.core.PApplet;

public class HScrollbar {

	private PApplet window;

	private int swidth, sheight; // width and height of bar
	private float xpos, ypos; // x and y position of bar
	private float spos, newspos; // x position of slider
	private float sposMin, sposMax; // max and min values of slider
	private int loose; // how loose/heavy
	private boolean over; // is the mouse over the slider?
	private boolean locked;
	private float ratio;

	HScrollbar(PApplet window, float xp, float yp, int sw, int sh, int l) {
		
		this.window = window;
		
		swidth = sw;
		sheight = sh;
		int widthtoheight = sw - sh;
		ratio = (float) sw / (float) widthtoheight;
		xpos = xp;
		ypos = yp - sheight / 2;
		spos = xpos;// + swidth/2 - sheight/2;
		newspos = spos;
		sposMin = xpos;
		sposMax = xpos + swidth - sheight;
		loose = l;
	}

	void update() {
		if (overEvent()) {
			over = true;
		} else {
			over = false;
		}
		if (window.mousePressed && over) {
			locked = true;
		}
		if (!window.mousePressed) {
			locked = false;
		}
		if (locked) {
			newspos = constrain(window.mouseX - sheight / 2, sposMin, sposMax);
		}
		if (Math.abs(newspos - spos) > 1) {
			spos = spos + (newspos - spos) / loose;
		}
	}

	float constrain(float val, float minv, float maxv) {
		return Math.min(Math.max(val, minv), maxv);
	}

	boolean overEvent() {
		if (window.mouseX > xpos && window.mouseX < xpos + swidth && window.mouseY > ypos
				&& window.mouseY < ypos + sheight) {
			return true;
		} else {
			return false;
		}
	}

	void display() {
		window.noStroke();
		window.fill(204);
		window.rect(xpos, ypos, swidth, sheight);
		if (over || locked) {
			window.fill(0, 0, 0);
		} else {
			window.fill(102, 102, 102);
		}
		window.rect(spos, ypos, sheight, sheight);
	}

	public float getPos() {
		// Convert spos to be values between
		// 0 and the total width of the scrollbar
		return spos * ratio;
	}
}
