package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utilities.Point;

public class ControlsPanel extends JPanel implements ActionListener {
	private RoadMapPanel roadMapPanel=null;
	private int controlsLength = Point.maxX;
    private int controlsWidth = Point.maxY;
	/**
	 * Create the panel.
	 */

	public ControlsPanel(RoadMapPanel roadMapPanel) {
		this.roadMapPanel = roadMapPanel;
        setLayout(null);
        setPreferredSize(new Dimension(controlsWidth,30));

        JButton createRoadSystem = new JButton("create Road System");
        createRoadSystem.setLocation(0,0);
        createRoadSystem.setSize(160, 30);
        createRoadSystem.addActionListener(this);
        add(createRoadSystem);

        JButton Start = new JButton("Start");
        Start.setLocation(160,0);
        Start.setSize(160, 30);
        Start.addActionListener(this);
        add(Start);
        
        JButton Stop = new JButton("Stop");
        Stop.setLocation(320,0);
        Stop.setSize(160, 30);
        Stop.addActionListener(this);
        add(Stop);
        
        JButton Resume = new JButton("Resume");
        Resume.setLocation(480,0);
        Resume.setSize(160, 30);
        Resume.addActionListener(this);
        add(Resume);

        JButton info = new JButton("info");
        info.setLocation(640,0);
        info.setSize(160, 30);
        info.addActionListener(this);
        add(info);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
        
		case "create Road System":
			this.roadMapPanel.CreateRoadSystem();
			
			break;
		case "Start": 
			roadMapPanel.startRace();
			/*
			if(this.roadMapPanel.getCurrentMap()!=null)
			this.roadMapPanel.setObservationFinished(false);
			*/
			break;
		case "Stop": 
			try {
				this.roadMapPanel.wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Resume": 
			this.roadMapPanel.notifyAll();
			break;
		case "info": 
			break;
		
			}
		
	}

	

}
