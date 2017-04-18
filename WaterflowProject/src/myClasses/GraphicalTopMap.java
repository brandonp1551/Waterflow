package myClasses;

/**
 * this class creates the visual representation of the map
 */

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphicalTopMap {
	final static int COLORMIN = 100; //minimum value allowed for a color
	final static int COLORMAX = 250; //maximum value allowed for a color
	static TopographicalMap m = null;
	static int floodUnits = 250; //number of water units to flood
	static int xCoord = 0; //x coordinate for starting flood
	static int yCoord = 0; //y coordinate for starting flood
	
	//swing variables
	private JFrame display;
	private JPanel displayPanel;
	private JPanel statusPanel;
	private JLabel statusLabel;
	private JPanel configPanel;
	private JTextField xCoordField;
	private JTextField yCoordField;
	private JTextField floodUnitsField;
	
	/**
	 * constructor
	 * @param m takes in a TopographicalMap to create a visual representation of
	 */
	public GraphicalTopMap(TopographicalMap map){
		m = map; //makes ivar the same map as passed to the constructor
		initUI();
		
	}
	
	/**
	 * initializes the gui
	 */
	private void initUI(){
		displayFrame();
		assembleGUI(statusPanel());
		assembleGUI(configPanel());
		
		
		display.setVisible(true);
	}
	
	/**
	 * adds all the gui components to the JFrame
	 */
	private void assembleGUI(JPanel panel){		
		displayPanel.add(panel);
	}
	
	/**
	 * creates and sets attributes for JFrame
	 */
	private void displayFrame(){
		display = new JFrame();
		display.setTitle("Topographical Map | Waterflow Final Project");
		display.setSize(300, 200);
		display.setLocationRelativeTo(null);
		displayPanel = new JPanel();
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		display.add(displayPanel);
	}
	
	/**
	 * creates and supplies initial info for the status panel JPanel
	 */
	private JPanel statusPanel(){
		statusPanel = new JPanel();
		statusLabel = new JLabel("Please enter water parameters");
		statusPanel.add(statusLabel);
		return statusPanel;
	}
	
	/**
	 * creates the config panel (for picking the flood location and how much water)
	 */
	
	private JPanel configPanel(){
		configPanel = new JPanel();
		floodUnitsField = new JTextField("Water Units");
		xCoordField = new JTextField("X Coordinate");
		yCoordField = new JTextField("Y Coordinate");
		configPanel.add(xCoordField);
		configPanel.add(yCoordField);
		configPanel.add(floodUnitsField);
		JButton configButton = new JButton("Enter");
		configPanel.add(configButton);
		configButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					xCoord = Integer.parseInt(xCoordField.getText());				
					yCoord = Integer.parseInt(yCoordField.getText());
					floodUnits = Integer.parseInt(floodUnitsField.getText());
				}
				catch(Exception NumberFormatException){
					alert("Please enter integer values");
				}
			}          
	      });
		return configPanel;
	}
	
	/**
	 * sets the flood location
	 * @param x coordinate for flood start
	 * @param y coordinate for flood start
	 */
	private void setFloodLocation(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	/**
	 * changes status text
	 * @param text String to be the status text
	 */
	private void alert(String text){
		statusLabel.setText(text);
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(() -> {
			try {
				m = new TopographicalMap("C:/Users/tcj/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/Eclipse/Waterflow/WaterflowProject/src/dataSets/Mountain.csv");
			} catch (IOException e) { //file not found, etc
				e.printStackTrace();
			}
			new GraphicalTopMap(m); //generates display
        });
	}
	
	 /**
	  * takes a value and the min & max for all related values, returning the relative intensity of the color for the value within its range
	  * @param value value to be converted to color range
	  * @param max maximum for comparing level of color 
	  * @param min minimum for comparing level of color
	  */
	private int colorValue(Double value, Double min, Double max){
		return (int) /*convert answer to int before returning*/ (((value - min) * (COLORMAX - COLORMIN)) / (max - min)) + COLORMIN;
	}
}