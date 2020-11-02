package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import entity.Message;
import entity.Simulation;
import entity.Team;



public class BoardGame extends JComponent implements ActionListener{
	private Image dbImage;
	private boolean end;
	int day=1;
	int month=0;
	Timer timer=new Timer(100, this);
	
	private Image backgroundImage;
	
	private Simulation simulation;
	private MainMenu mainmenu;
	
	BoardGame(Simulation simulation, MainMenu mainmenu)
	{
		dbImage = new ImageIcon(this.getClass().getResource("/img/font.png")).getImage();
		this.mainmenu=mainmenu;
		this.simulation=simulation;
		simulation.setBoardGame(this);
		this.simulation.init();
		
		setVisible(true);
	}
	
	public  void    actionPerformed(ActionEvent e)
    {
		// c et cette boucle qui execute pas a pas la simulation
		if(end==false) {
		if(e.getSource()==timer){
			System.out.println("jour : "+day);
			for(Team t: simulation.getTeams()) {
				if(end==false) {
				this.getSimulation().simumationrow(t);
				this.checkWin(t);
				repaint();
				}
			}
			this.pause();
			day++;
		  }
		}
		else {
			if(e.getSource()==timer){
				
				this.mainmenu.getStartButton().setVisible(false);
				this.mainmenu.getNewgame().setVisible(true);
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

	public Image getDbImage() {
		return dbImage;
	}

	public void setDbImage(Image dbImage) {
		this.dbImage = dbImage;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	public MainMenu getMainmenu() {
		return mainmenu;
	}

	public void setMainmenu(MainMenu mainmenu) {
		this.mainmenu = mainmenu;
	}
	
	private void pause() {
		if (this.day % 100==0) {//si le nombre de jour est modulo 100 alors on met en pause le jeu
			this.mainmenu.getResumeButton().setVisible(true);
			
			this.timer.stop();
			for(Team t:simulation.getTeams()) {
				System.out.println(t.getMaitre().getName()+" possede "+t.getMaitre().getKnownMasterMessage().size()+" messages :");
				for(Message m:t.getMaitre().getKnownMasterMessage()) {
					System.out.println(m.getMessage());
			}
			System.out.println();	
			System.out.println();	
			}
		}
	}
	
	private void checkWin(Team team) {
		if(team.getMaitre().getKnownMasterMessage().size()>6) {
			JOptionPane.showMessageDialog(null, team.getMaitre().getName()+" a gagner", "Gagnant: " , JOptionPane.INFORMATION_MESSAGE);
			for(Message s:team.getMaitre().getKnownMasterMessage()) {
				System.out.println(s.getMessage());
			}
			end=true;
		}
	}
	
	
	
}
