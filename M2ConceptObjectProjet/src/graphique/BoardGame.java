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
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import entity.Simulation;
import entity.Team;
import entity.LivingBeing.Peon;



public class BoardGame extends JComponent implements ActionListener{
	private Image dbImage;
	private boolean end;
	int day=1;
	int month=0;
	Timer timer=new Timer(100, this);
	
	private Image backgroundImage;
	
	private Simulation simulation;
	
	BoardGame(Simulation simulation, MainMenu mainmenu)
	{
		dbImage = new ImageIcon(this.getClass().getResource("/img/font.png")).getImage();
		
		
		
		this.simulation=simulation;
		simulation.setBoardGame(this);
		this.simulation.init();
		
		
		
		
		
		setVisible(true);
	}
	
	public  void    actionPerformed(ActionEvent e)
    {
		if(end==false) {
		if(e.getSource()==timer){
			System.out.println("jour : "+day);
			for(Team t:simulation.getTeams()) {
				System.out.println(t.toString());
				for(Peon p:t.getPeons()) {
					if (p.getPE() > 0) {
						p.move();
					}
				}
				if(t.getMaitre().getKnownMasterMessage().size()==10) {
					System.out.println(t.toString()+"a gagner");
					end=true;
				}
			}
			day++;
			repaint();// this will call at every 1 second

			
		  }
		}
		
    }



	@Override
	   protected void paintComponent(Graphics g)
	   {
		g.drawImage(dbImage, 40, 40, 800, 800, this);
	      super.paintComponent(g);
		Graphics2D g2= (Graphics2D) g;
		
		
		
		
		int CASEDIM=40;
		ImageIcon img=null;
		
		
		for(int i=0;i<this.simulation.getMap().getX();i++) {
			for(int j=0;j<this.simulation.getMap().getY();j++) {
				
				g2.setPaint(new Color(255,255,255,100));
				if(this.simulation.getMap().getMap()[i][j].isObstacle()==true) {
					//load 
					g2.setPaint(new Color(0,0,0,100));
					g2.fill(new Rectangle2D.Double((i+1)*CASEDIM,(j+1)*CASEDIM,CASEDIM,CASEDIM	));
				}
				else {
					if(this.simulation.getMap().getMap()[i][j].isSafeZone()) {
						switch(this.simulation.getMap().getMap()[i][j].getSafeZoneOwner()) {
						case 0:
						    g2.setPaint(new Color(0,0,255,100));
						break;
						case 1:
							  g2.setPaint(new Color(255,0,0,100));
						break;
						case 2:
							  g2.setPaint(new Color(0,255,0,100));
						break;
						case 3:
							  g2.setPaint(new Color(255,255,0,100));
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
