
package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import components.Driving;
import components.Junction;
import components.Vehicle;



public class InfoTable extends JFrame{
	  
    public InfoTable(Driving d){
        super("Competitors information");
        String[] columnNames = {"Vehicle",
                             "Type",
                             "Location",
                             "Time on loc",
                             "speed"};
        
        String[][] data = new String[d.getVehicles().size()][5];
        int i=0;
     
        for (Vehicle v: d.getVehicles()) {
            data[i][0] = v.getId()+"";
            data[i][1] = ""+v.getVehicleType();
            if(v.getCurrentRoutePart() instanceof Junction)
            	data[i][2] = "Junction "+v.getCurrentRoutePart();
            else data[i][2] = "Road "+v.getCurrentRoutePart();
            data[i][3] = ""+v.getTimeOnCurrentPart();
            data[i][4] = ""+v.getVehicleType().getAverageSpeed();
            i++;
        }
                    

                    
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tabPan = new JPanel();
        tabPan.add(scrollPane);                   

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(tabPan);
        pack();
        setVisible(true); 
    }
}
