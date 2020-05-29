package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class RoadFrame extends JFrame {

	private MenuBar mb=new MenuBar(this);
	private RoadMapPanel roadMapPanel=null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		RoadFrame rf=new RoadFrame();
	}
	 */

	/**
	 * Create the frame.
	 */
	public RoadFrame() {
		super("Competition");
		updateFrame();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/*
	 * 1) set menu bar
	 * 2) getMyContentPane()
	 */
	public void updateFrame(){
		//add the menu bar
		this.setJMenuBar(mb.getMenuBar());
		this.setSize(400,400);  
		this.setLayout(null);  
		this.setVisible(true);
		
		
        this.setContentPane(getMyContentPane());
        this.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true); 
    }
	/*
	 * 
	 */
	public JPanel getMyContentPane(){ 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); 
        if (roadMapPanel==null) {
        	roadMapPanel = new RoadMapPanel();
        	roadMapPanel.setRoadFrame(this); 
        }
        else 
        	roadMapPanel.initRoadMap();
        
        mainPanel.add(roadMapPanel,BorderLayout.NORTH); 
        mainPanel.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.CENTER);
        mainPanel.add(new ControlsPanel(roadMapPanel),BorderLayout.SOUTH);
        
        return mainPanel;
    }

	public RoadMapPanel getRoadMapPanel() {	return roadMapPanel;}
	
	
	public static void main(String[] args) {
		new	RoadFrame();
	}
}
