package graphique;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import entity.Map;
import entity.Simulation;

// TODO: Auto-generated Javadoc
/**
 * The Class MainMenu.
 */
public class MainMenu extends JFrame  implements ActionListener{
	
	
	/** The Text pane. */
	private JTextPane TextPane;
	
	/** The Text score team 1. */
	private JTextPane TextScoreTeam1;
	
	/** The Text score team 2. */
	private JTextPane TextScoreTeam2;
	
	/** The Text score team 3. */
	private JTextPane TextScoreTeam3;
	
	/** The Text score team 4. */
	private JTextPane TextScoreTeam4;
	
	/** The board game. */
	private BoardGame boardGame;
	
	/** The start button. */
	private JButton startButton;
	
	/** The resume button. */
	private JButton resumeButton;
	
	/** The newgame. */
	private JButton newgame;
	
	/**
	 * Instantiates a new main menu.
	 */
	MainMenu(){
		setTitle("star wars");
		setSize(1250,1000);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);
		
		this.TextPane = new JTextPane();
		TextPane.setEditable(false);
		
		this.TextScoreTeam1 = new JTextPane();
		TextScoreTeam1.setEditable(false);
		TextScoreTeam1.setBounds(860,500,300,20);
		TextScoreTeam1.setBorder(new LineBorder(Color.BLACK));
		TextScoreTeam1.setText("Team 1: 0 messages");
		this.add( TextScoreTeam1 );
		
		this.TextScoreTeam2 = new JTextPane();
		TextScoreTeam2.setEditable(false);
		TextScoreTeam2.setBounds(860,550,300,20);
		TextScoreTeam2.setBorder(new LineBorder(Color.BLACK));
		TextScoreTeam2.setText("Team 2: 0 messages");
		this.add( TextScoreTeam2 );
		
		this.TextScoreTeam3 = new JTextPane();
		TextScoreTeam3.setEditable(false);
		TextScoreTeam3.setBounds(860,600,300,20);
		TextScoreTeam3.setBorder(new LineBorder(Color.BLACK));
		TextScoreTeam3.setText("Team 3: 0 messages");
		this.add( TextScoreTeam3 );
		
		this.TextScoreTeam4 = new JTextPane();
		TextScoreTeam4.setEditable(false);
		TextScoreTeam4.setBounds(860,650,300,20);
		TextScoreTeam4.setBorder(new LineBorder(Color.BLACK));
		TextScoreTeam4.setText("Team 4: 0 messages");
		this.add( TextScoreTeam4 );
		
		JScrollPane jp = new JScrollPane( TextPane );  
		jp.setBounds(860,100,350,700);
		jp.setBorder(new LineBorder(Color.BLACK));
		this.add( jp );
		
		this.startButton =new JButton();
		startButton.setText("start");
		startButton.setBounds(975,50,100,25);
		startButton.addActionListener(this);
		this.add(startButton);
		
		this.resumeButton =new JButton();
		resumeButton.setText("resume");
		resumeButton.setBounds(975,825,100,25);
		resumeButton.addActionListener(this);
		resumeButton.setVisible(false);
		this.add(resumeButton);
		
		this.newgame =new JButton();
		newgame.setText("newgame");
		newgame.setBounds(975,50,100,25);
		newgame.addActionListener(this);
		newgame.setVisible(false);
		this.add(newgame);
		
		this.boardGame=new BoardGame(new Simulation(new Map(20,20)),this);
		boardGame.setBounds(10,10,1250,1000);
		boardGame.setVisible(true);
		add(boardGame);
		
		PrintStream interceptor = new Interceptor(System.out);
        System.setOut(interceptor);
		
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	/*start
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				new MainMenu().setVisible(true);
				
				}
	});
		
		
}
	
	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	public  void    actionPerformed(ActionEvent e) {
		
		if(e.getSource()==startButton){
			this.boardGame.timer.start();
			System.out.println("start");
		}
		
		if(e.getSource()==resumeButton){
			this.boardGame.timer.start();
			System.out.println("resume");
			resumeButton.setVisible(false);
		}
		
		if(e.getSource()==newgame){
			this.boardGame.getTimer().stop();
			this.boardGame.setSimulation(new Simulation(new Map(20,20)));
			this.boardGame.getSimulation().init();
			this.boardGame.setDay(1);
			this.boardGame.setEnd(false);
			this.boardGame.repaint();
			this.getNewgame().setVisible(false);
			this.getStartButton().setVisible(true);
		}
		
	}
	
	/**
	 * Prints the log.
	 */
	private void printLog() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Time now is " + (new Date()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
	
	 
	 /**
 	 * The Class Interceptor.
 	 */
 	public class Interceptor extends PrintStream
	    {
	        
        	/**
        	 * Instantiates a new interceptor.
        	 *
        	 * @param out the out
        	 */
        	public Interceptor(OutputStream out)
	        {
	            super(out,true);
	        }
	        
        	/**
        	 * Prints the.
        	 *
        	 * @param s the s
        	 */
        	@Override
	        public void print(String s)
	        {
	            super.print(s);
	            TextPane.setText(TextPane.getText()+s);
	        }

	        /**
        	 * Println.
        	 *
        	 * @param s the s
        	 */
        	@Override 
	        public void println(String s)
	        {
	            super.println(s);
	            TextPane.setText(TextPane.getText()+"\n");
	        }       
	    }


	/**
	 * Gets the text pane.
	 *
	 * @return the text pane
	 */
	public JTextPane getTextPane() {
		return TextPane;
	}

	/**
	 * Sets the text pane.
	 *
	 * @param textPane the new text pane
	 */
	public void setTextPane(JTextPane textPane) {
		TextPane = textPane;
	}

	/**
	 * Gets the board game.
	 *
	 * @return the board game
	 */
	public BoardGame getBoardGame() {
		return boardGame;
	}

	/**
	 * Sets the board game.
	 *
	 * @param boardGame the new board game
	 */
	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	/**
	 * Gets the start button.
	 *
	 * @return the start button
	 */
	public JButton getStartButton() {
		return startButton;
	}

	/**
	 * Sets the start button.
	 *
	 * @param startButton the new start button
	 */
	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	/**
	 * Gets the resume button.
	 *
	 * @return the resume button
	 */
	public JButton getResumeButton() {
		return resumeButton;
	}

	/**
	 * Sets the resume button.
	 *
	 * @param resumeButton the new resume button
	 */
	public void setResumeButton(JButton resumeButton) {
		this.resumeButton = resumeButton;
	}

	/**
	 * Gets the newgame.
	 *
	 * @return the newgame
	 */
	public JButton getNewgame() {
		return newgame;
	}

	/**
	 * Sets the newgame.
	 *
	 * @param newgame the new newgame
	 */
	public void setNewgame(JButton newgame) {
		this.newgame = newgame;
	}
	
	
}
