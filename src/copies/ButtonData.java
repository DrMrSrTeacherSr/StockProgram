//package utilities;
//import java.awt.Color;
//import java.util.ArrayList;
//
//
//public class ButtonData {
//	
//	private ArrayList<String> names;
//	private HashMap<String,Color> colors;
//	private int numVariabels;
//	private int[] values;
//
//	public ButtonData(String name, int numVariabels, Color color){
//		this.color = color;
//		this.name = name;
//		this.numVariabels = numVariabels;	
//		values = new int[numVariabels];
//	}
//
//	public String getName() {
//		return name;
//	}
//		
//	public Color getColor() {
//		return color;
//	}
//
//	public int getNumVars() {
//		return numVariabels;
//	}
//
//	public void setValues(int position, String value) {
//		try{
//			values[position] = Integer.parseInt(value);
//		} catch(Exception e){
//			value = "0";
//			values[position] = Integer.parseInt(value);
//		}
//	}
//
//	public int[] getValues() {
//		return values;
//	}	
//	
//
//}
