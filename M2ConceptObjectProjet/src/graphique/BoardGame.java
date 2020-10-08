package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import entity.Simulation;

public class BoardGame extends JComponent{
	
	private Simulation simulation;
	
	BoardGame(Simulation simulation)
	{
		this.simulation=simulation;
		this.simulation.init();
	}
	
	@Override
	   protected void paintComponent(Graphics g)
	   {
	      super.paintComponent(g);
		Graphics2D g2= (Graphics2D) g;
		
		int CASEDIM=20;
		ImageIcon img=null;
		
		for(int i=0;i<this.simulation.getMap().getX();i++) {
			for(int j=0;j<this.simulation.getMap().getY();j++) {
				
				g2.setPaint(Color.WHITE);
				if(this.simulation.getMap().getMap()[i][j].isObstacle()==true) {
					//load 
					g2.setPaint(Color.BLACK);
				}
				else {
					if(this.simulation.getMap().getMap()[i][j].isSafeZone()) {
						switch(this.simulation.getMap().getMap()[i][j].getSafeZoneOwner()) {
						case 0:
						    g2.setPaint(Color.BLUE);
						break;
						case 1:
							  g2.setPaint(Color.RED);
						break;
						case 2:
							  g2.setPaint(Color.GREEN);
						break;
						case 3:
							  g2.setPaint(Color.ORANGE);
						break;
					}
				}
					/*
					if(this.simulation.getMap().getMap()[i][j].isOccupied()==true) {
						img=new ImageIcon(getClass().getResource("/img/"+this.simulation.getMap().getMap()[i][j].getOccupant().getImage()));
						img.paintIcon(null, g2, (i+1)*CASEDIM,(j+1)*CASEDIM);
					}*/
				
				}
				g2.fill(new Rectangle2D.Double((i+1)*CASEDIM,(j+1)*CASEDIM,CASEDIM,CASEDIM	));
			}
		}
		g2.dispose();
	}
	
}
