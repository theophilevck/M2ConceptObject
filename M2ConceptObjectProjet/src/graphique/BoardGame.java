package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Timer;

import entity.Simulation;
import entity.Team;
import entity.LivingBeing.Peon;

public class BoardGame extends JComponent implements ActionListener{
	private Image dbImage;
	private JButton startButton;
	Timer timer=new Timer(10, this);
	
	private Simulation simulation;
	
	BoardGame(Simulation simulation)
	{
		
		this.simulation=simulation;
		simulation.setBoardGame(this);
		this.simulation.init();
		
		
		this.startButton =new JButton();
		startButton.setText("start");
		startButton.setBounds(900,50,100,25);
		startButton.addActionListener(this);
		
		add(startButton);
	}
	
	public  void    actionPerformed(ActionEvent e)
    {
		
		if(e.getSource()==timer){
			
			for(Team t:simulation.getTeams()) {
				System.out.println(t.toString());
				for(Peon p:t.getPeons()) {
					p.move();
				}
			}
			repaint();// this will call at every 1 second

			
		  }
		if(e.getSource()==startButton){
			timer.start();
		}
    }

	
	@Override
	   protected void paintComponent(Graphics g)
	   {
	      super.paintComponent(g);
		Graphics2D g2= (Graphics2D) g;
		
		
		
		
		int CASEDIM=40;
		ImageIcon img=null;
		
		for(int i=0;i<this.simulation.getMap().getX();i++) {
			for(int j=0;j<this.simulation.getMap().getY();j++) {
				
				g2.setPaint(new Color(255,255,255));
				if(this.simulation.getMap().getMap()[i][j].isObstacle()==true) {
					//load 
					g2.setPaint(Color.BLACK);
					g2.fill(new Rectangle2D.Double((i+1)*CASEDIM,(j+1)*CASEDIM,CASEDIM,CASEDIM	));
				}
				else {
					if(this.simulation.getMap().getMap()[i][j].isSafeZone()) {
						switch(this.simulation.getMap().getMap()[i][j].getSafeZoneOwner()) {
						case 0:
						    g2.setPaint(Color.BLUE);
						break;
						case 1:
							  g2.setPaint(new Color(255,0,0));
						break;
						case 2:
							  g2.setPaint(Color.GREEN);
						break;
						case 3:
							  g2.setPaint(Color.ORANGE);
						break;
					}
						g2.fill(new Rectangle2D.Double((i+1)*CASEDIM,(j+1)*CASEDIM,CASEDIM,CASEDIM	));
				}
					if(this.simulation.getMap().getMap()[i][j].isOccupied()==true) {
						img=new ImageIcon(getClass().getResource(this.simulation.getMap().getMap()[i][j].getOccupant().getImage()));
						g2.fill(new Rectangle2D.Double((i+1)*CASEDIM,(j+1)*CASEDIM,CASEDIM,CASEDIM	));
						img.paintIcon(null, g2, (i+1)*CASEDIM,(j+1)*CASEDIM);
					}
				}
				
			}
		}
		g2.dispose();
	}
	
	
}
