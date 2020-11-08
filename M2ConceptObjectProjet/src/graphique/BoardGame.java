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



// TODO: Auto-generated Javadoc
/**
 * The Class BoardGame.
 */
public class BoardGame extends JComponent implements ActionListener{
	
	/** The db image. */
	private Image dbImage;
	
	/** The end. */
	private boolean end;
	
	/** The day. */
	int day=1;
	
	/** The month. */
	int month=0;
	
	/** The timer. */
	Timer timer=new Timer(100, this);
	
	/** The background image. */
	private Image backgroundImage;
	
	/** The simulation. */
	private Simulation simulation;
	
	/** The mainmenu. */
	private MainMenu mainmenu;
	
	/**
	 * Instantiates a new board game.
	 *
	 * @param simulation the simulation
	 * @param mainmenu the mainmenu
	 */
	BoardGame(Simulation simulation, MainMenu mainmenu)
	{
		dbImage = new ImageIcon(this.getClass().getResource("/img/font.png")).getImage();
		this.mainmenu=mainmenu;
		this.simulation=simulation;
		simulation.setBoardGame(this);
		this.simulation.init();
		
		setVisible(true);
	}

	/**
	 * Action performed, executing day by day the simulation.
	 *
	 * @param e the e
	 */
	public  void    actionPerformed(ActionEvent e)
    {
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



	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
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

	/**
	 * Gets the db image.
	 *
	 * @return the db image
	 */
	public Image getDbImage() {
		return dbImage;
	}

	/**
	 * Sets the db image.
	 *
	 * @param dbImage the new db image
	 */
	public void setDbImage(Image dbImage) {
		this.dbImage = dbImage;
	}

	/**
	 * Checks if is end.
	 *
	 * @return true, if is end
	 */
	public boolean isEnd() {
		return end;
	}

	/**
	 * Sets the end.
	 *
	 * @param end the new end
	 */
	public void setEnd(boolean end) {
		this.end = end;
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day the new day
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Sets the month.
	 *
	 * @param month the new month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * Gets the timer.
	 *
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * Sets the timer.
	 *
	 * @param timer the new timer
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	/**
	 * Gets the background image.
	 *
	 * @return the background image
	 */
	public Image getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * Sets the background image.
	 *
	 * @param backgroundImage the new background image
	 */
	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	/**
	 * Gets the simulation.
	 *
	 * @return the simulation
	 */
	public Simulation getSimulation() {
		return simulation;
	}

	/**
	 * Sets the simulation.
	 *
	 * @param simulation the new simulation
	 */
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	/**
	 * Gets the mainmenu.
	 *
	 * @return the mainmenu
	 */
	public MainMenu getMainmenu() {
		return mainmenu;
	}

	/**
	 * Sets the mainmenu.
	 *
	 * @param mainmenu the new mainmenu
	 */
	public void setMainmenu(MainMenu mainmenu) {
		this.mainmenu = mainmenu;
	}
	
	/**
	 * Pause, every 100 days in the simulation.
	 */
	private void pause() {
		if (this.day % 100==0) {
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
	
	/**
	 * Check if a team has won or not.
	 *
	 * @param team the team
	 */
	private void checkWin(Team team) {
		if(team.getMaitre().getKnownMasterMessage().size()>12) {
			JOptionPane.showMessageDialog(null, team.getMaitre().getName()+" a gagner", "Gagnant: " , JOptionPane.INFORMATION_MESSAGE);
			for(Message s:team.getMaitre().getKnownMasterMessage()) {
				System.out.println(s.getMessage());
			}
			end=true;
		}
	}
	
	
	
}
