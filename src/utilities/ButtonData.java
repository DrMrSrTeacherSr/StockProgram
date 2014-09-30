package utilities;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;


public class ButtonData {
	
	private ArrayList<String> names;
	private ArrayList<int[]> variables;
	private ArrayList<Color> colors;
	

	public ButtonData(ArrayList<String> names,ArrayList<int[]> variables, ArrayList<Color> colors){
		this.names = names;
		this.variables = variables;
		this.colors = colors;
	}
	
	public ButtonData(String name, int[] vars, Color color){
		names = new ArrayList<String>();
		variables = new ArrayList<int[]>();
		colors = new ArrayList<Color>();
		names.add(name);
		variables.add(vars);
		colors.add(color);
	}

	public  ArrayList<String> getNames() {
		return names;
	}
		
	public ArrayList<int[]> getVars() {
		return variables;
	}
	
	public ArrayList<Color> getColors() {
		return colors;
	}

	public void add(String name, int[] vars, Color color){
		names.add(name);
		variables.add(vars);
		colors.add(color);
	}

}
