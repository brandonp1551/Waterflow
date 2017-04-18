package myClasses;

import java.awt.Color;
import java.awt.Dimension;

/**
 * this class creates the visual representation of the map, as well as handling any UI elements
 */

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
	private JPanel mapPanel;
	private int hgap = 5; //horizontal gap between map tiles
	private int vgap = 5; //vertical gap between map tiles
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //for getting screen width
	private int panelMaxW = (int) screenSize.getWidth(); //max width for panels is screen width
	private int panelMaxH = 50;
	
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
		assembleGUI(mapPanel());
		
		
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
		display.setTitle("Topographical Map | Waterflow Final Project"); //sets JFrame to take up entire screen
		display.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		display.setLocationRelativeTo(null);
		displayPanel = new JPanel();
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		display.add(displayPanel);
	}
	
	/**
	 * creates and supplies initial info for the status panel JPanel
	 * @return the status JPanel
	 */
	private JPanel statusPanel(){
		statusPanel = new JPanel();
		statusPanel.setMaximumSize(new Dimension(panelMaxW, panelMaxH));
		statusLabel = new JLabel("Please enter flood parameters.");
		statusPanel.add(statusLabel);
		return statusPanel;
	}
	
	/**
	 * creates the config panel (for picking the flood location and how much water)
	 * @return the config JPanel
	 */
	private JPanel configPanel(){
		configPanel = new JPanel();
		configPanel.setMaximumSize(new Dimension(panelMaxW, panelMaxH));
		floodUnitsField = new JTextField("Water Units");
		xCoordField = new JTextField("X Coordinate");
		yCoordField = new JTextField("Y Coordinate");
		configPanel.add(xCoordField);
		configPanel.add(yCoordField);
		configPanel.add(floodUnitsField);
		JButton configButton = new JButton("Enter");
		configPanel.add(configButton);
		configButton.addActionListener(new ActionListener(){ //listener for when enter button is pressed
			@Override
			public void actionPerformed(ActionEvent arg0){
				//init local vars
				int x = xCoord;
				int y = yCoord;
				int fUnits = floodUnits;
				
				try{
					x = Integer.parseInt(xCoordField.getText());
					y = Integer.parseInt(yCoordField.getText());
					fUnits = Integer.parseInt(floodUnitsField.getText());
					if(x > m.getWidth() || x < 0 || y > m.getHeight() || y < 0){ //validate x, y coordinates
						alert("Please enter flood coordinates within map boundaries.");
					}else{
						setFloodLocation(x, y);
						floodUnits = fUnits;
						displayPanel.remove(configPanel);
						displayPanel.revalidate();
						displayPanel.repaint();
						alert("Input accepted. Starting flood.");
						startFlood();
					}
				} catch(Exception NumberFormatException){
					alert("Please enter integer values.");
				}
			}          
	      });
		return configPanel;
	}
	
	/**
	 * creates the map panel for the GUI and calls the map to be drawn for the first time
	 * @return the map JPanel
	 */
	public JPanel mapPanel(){
		mapPanel = new JPanel();
		mapPanel.setLayout(new GridLayout(m.getWidth(), m.getHeight(), hgap, vgap));
		JPanel tmpPanel;
		for(int x = 0; x < m.getWidth(); x++){
			for (int y = 0; y < m.getHeight(); y++){
				tmpPanel = new JPanel();
				tmpPanel.setBackground(getColor(m.getNode(x, y)));
				double nodeElevation = m.getNode(x, y).getLevel();
				JLabel eLabel = new JLabel("E: " + nodeElevation);
				tmpPanel.add(eLabel);
				JLabel cLabel = new JLabel("(" + x + ", " + y + ")");
				tmpPanel.add(cLabel);
				mapPanel.add(tmpPanel);
			}
		}
		return mapPanel;
	}
	
	/**
	 * redraws the entire map panel
	 */
	public void redrawMap(){
		displayPanel.remove(mapPanel);
		displayPanel.revalidate();
		displayPanel.repaint();
		assembleGUI(mapPanel());
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
	 * responsible for actually starting the flood simulation on map m
	 */
	private void startFlood(){
		
	}
	
	/**
	 * changes status text
	 * @param text String to be the status text
	 */
	private void alert(Object text){
		statusLabel.setText(text.toString());
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(() -> {
			try{
				m = new TopographicalMap("C:/Users/tcj/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/Eclipse/Waterflow/WaterflowProject/src/dataSets/Mountain.csv");
			}catch (IOException e) { //file not found, etc
				e.printStackTrace();
			}
			new GraphicalTopMap(m); //generates display
        });
	}
	
	/**
	 * @param n Node to get the color of
	 * @return returns the color used to represent the passed Node
	 */
	public Color getColor(Node n){
		int cVal = colorValue(n.getLevel(), m.getMinLevel(), m.getMaxLevel());
		if(n.isWet()){
			return new Color(0, 0, cVal);
		}else{
			return new Color(cVal, cVal, 0);
		}
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