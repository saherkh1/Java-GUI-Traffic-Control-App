package GUI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JPanel implements ActionListener {

	JMenu file,backGround,vehiclesColors,Help;
	JMenuItem i1,i2,i3,i4,i5,i6,i7,i8;
	JMenuBar menuBar = new JMenuBar();
	
	RoadFrame roadFrame;
	
	/**
	 * Create the panel.
	 */
	public MenuBar(RoadFrame roadFrame) {
		this.roadFrame=roadFrame;
		
        file=new JMenu("file");
        i1=new JMenuItem("Exit");
        i1.addActionListener(this);
        file.add(i1);
        
        backGround=new JMenu("background");
        i2=new JMenuItem("Blue");
        i2.addActionListener(this);
        i3=new JMenuItem("None"); 
        i3.addActionListener(this);
        backGround.add(i2);
        backGround.add(i3);
        
        vehiclesColors=new JMenu("vehicles colors");
        i4=new JMenuItem("Blue ");  
        i4.addActionListener(this);
        i5=new JMenuItem("Magenta");
        i5.addActionListener(this);
        i6=new JMenuItem("Orange"); 
        i6.addActionListener(this);
        i7=new JMenuItem("Random");
        i7.addActionListener(this);
        vehiclesColors.add(i4);
        vehiclesColors.add(i5);
        vehiclesColors.add(i6);
        vehiclesColors.add(i7);
        
        
        Help=new JMenu("Help");  
        i8=new JMenuItem("Help");  
        Help.add(i8);
        
        menuBar.add(file);
        menuBar.add(backGround);
        menuBar.add(vehiclesColors);
        menuBar.add(Help);
        
      

	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
        
		case "Exit":
			System.exit(0);
			break;
		case "Blue":
			roadFrame.getRoadMapPanel().setBackground(Color.BLUE);
			//setBackground("Blue");
			break;
		case "None":
			roadFrame.getRoadMapPanel().setBackground(null);
			break;
		case "Blue ":
			//setVehiclesColor("Blue");
			break;
		case "Magenta":
			//setVehiclesColor("Magenta");
			break;
		case "Orange":
			//setVehiclesColor("Orange");
			break;
		case "Random":
			//setBackground("Random");
			break;
		
	}
	
	}
}
