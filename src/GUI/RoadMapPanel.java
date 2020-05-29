package GUI;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utilities.Point;
import utilities.Timer;

import javax.swing.JFrame;
import javax.swing.JSlider;

import components.Driving;
import components.Map;
import components.Road;
import components.Vehicle;

public class RoadMapPanel extends JPanel /*implements Runnable */{
	private int mapLength = Point.maxY;
    private int mapWidth = Point.maxX;
    private boolean observationFinished = false; 
	
    int numberOfJunctions=0;
    int numberOfVehicles=0;
    Driving newDrive=null;
    private Map currentMap=null;
	private RoadFrame roadFrame=null;
	private ArrayList<Vehicle> vehicles=null;
	private int drivingTime;
	private ArrayList<Timer> allTimedElements=null;
	JLabel picLabel1 = new JLabel();
	Canvas mapCanvas=new DrawingMap();

	public Canvas getMapCanvas() {
		return mapCanvas;
	}

	public void setMapCanvas(Canvas mapCanvas) {
		this.mapCanvas = mapCanvas;
	}

	public JLabel getPicLabel() {
		return picLabel1;
	}

	public void setPicLabel(JLabel picLabel) {
		this.picLabel1 = picLabel;
	}

	/**
	 * Create the application.
	 */
	public RoadMapPanel() {
		setLayout(null);
		initRoadMap();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	 public void initRoadMap(){
	        this.removeAll();
	        setPreferredSize(new Dimension(mapWidth,mapLength));
	        //JLabel picLabel1 = new JLabel();
	        picLabel1.setLocation(0, 0);
	        picLabel1.setSize(mapWidth,mapLength+80);
	        add(picLabel1);
	        //Canvas mapCanvas=new DrawingMap();
	        picLabel1.setVisible(true);
	        picLabel1.add(mapCanvas);
	        mapCanvas.setSize(mapWidth, mapLength);	 
	        if(currentMap!=null) {
	        	((DrawingMap)mapCanvas).setMap(currentMap);
	        }
	        if (newDrive!=null)
	        	((DrawingMap)mapCanvas).setV(newDrive.getVehicles());
	       // picLabel1.setBackground(new Color(255,255,0));
	       // this.picLabel=picLabel1;
	        
    
	}
	 public void abcd() {
			setBackground(Color.blue);
			
		}
	 public void abcdd() {
			picLabel1.setBackground(Color.blue);
			
		}
	 
	 
	public RoadFrame getRoadFrame() {
		return roadFrame;
	}

	public  void setRoadFrame(RoadFrame roadFrame) {
		this.roadFrame = roadFrame;
	}
	
	/**
	 * CREATE ROADS WINDOW
	 */
	public void  CreateRoadSystem() {
		final int  windowLength = 600;
	    final int windowWidth = 300;
	    
		final JFrame  parent = new JFrame();
		parent.setSize(windowLength,windowWidth+35);
		parent.getContentPane().setLayout(null);
		
		JButton okButton = new JButton();
		JButton cancelButton = new JButton();

		okButton.setText("ok");
		cancelButton.setText("cancel");
		okButton.setLocation(0,windowWidth-30);
		okButton.setSize(windowLength/2 , 30);
		cancelButton.setLocation(windowLength/2,windowWidth-30);
		cancelButton.setSize(windowLength/2, 30);
		
		parent.getContentPane().add(okButton);
        parent.getContentPane().add(cancelButton);
        
        JSlider slider = new JSlider();
        slider.setMinimum(3);
        slider.setMaximum(20);
        slider.setBounds(10, 65, 566, 38);
        parent.getContentPane().add(slider);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);

        slider.setValue(7);
        
        slider.setPaintLabels(true);
          
        JSlider slider_1 = new JSlider();
        slider_1.setPaintLabels(true);
        slider_1.setMinorTickSpacing(1);
        slider_1.setMaximum(50);
        slider_1.setBounds(10, 162, 566, 59);
        parent.getContentPane().add(slider_1);
        slider_1.setMajorTickSpacing(5);
        slider_1.setPaintTicks(true);
        slider_1.setValue(5);

        JLabel lblNewLabel = new JLabel("Number of Junctions");
        lblNewLabel.setBounds(246, 42, 125, 13);
        parent.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Number of vehicles");
        lblNewLabel_1.setBounds(246, 141, 125, 13);
        parent.getContentPane().add(lblNewLabel_1);
        
        parent.setVisible(true);

        okButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberOfJunctions=slider.getValue();
            	numberOfVehicles=slider_1.getValue();
            	
				try {
					newDrive = new Driving(numberOfJunctions, numberOfVehicles);
				} catch (InterruptedException e) {
					System.exit(1);
					e.printStackTrace();
				}
            	currentMap= newDrive.getMap();
            	vehicles=newDrive.getVehicles();
            	roadFrame.updateFrame();
            	parent.setVisible(false); 
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	parent.setVisible(false);; 
            }
        });
        
         parent.setVisible(true);
	}

	public boolean isObservationFinished() {
		return observationFinished;
	}

	public void setObservationFinished(boolean observationFinished) {
		this.observationFinished = observationFinished;
	}
	public Map getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}

	public int getNumberOfJunctions() {
		return numberOfJunctions;
	}

	public void setNumberOfJunctions(int numberOfJunctions) {
		this.numberOfJunctions = numberOfJunctions;
	}

	public int getNumberOfVehicles() {
		return numberOfVehicles;
	}

	public void setNumberOfVehicles(int numberOfVehicles) {
		this.numberOfVehicles = numberOfVehicles;
	}

	public Driving getNewDrive() {
		return newDrive;
	}

	public void setNewDrive(Driving newDrive) {
		this.newDrive = newDrive;
	}
	}
