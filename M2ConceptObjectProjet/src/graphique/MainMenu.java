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
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import entity.Map;
import entity.Simulation;

public class MainMenu extends JFrame  implements ActionListener{
	
	 
	
	private JTextPane TextPane;
	private BoardGame boardGame;
	private JButton startButton;
	private JButton resumeButton;
	private JButton newgame;
	MainMenu(){
		setTitle("star wars");
		setSize(1250,1000);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);
		
		this.TextPane = new JTextPane();
		TextPane.setEditable(false);
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
	
	/*start
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				new MainMenu().setVisible(true);
				
				}
	});
		
		
}
	
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
	
	 
	 public class Interceptor extends PrintStream
	    {
	        public Interceptor(OutputStream out)
	        {
	            super(out,true);
	        }
	        @Override
	        public void print(String s)
	        {
	            super.print(s);
	            TextPane.setText(TextPane.getText()+s);
	        }

	        @Override 
	        public void println(String s)
	        {
	            super.println(s);
	            TextPane.setText(TextPane.getText()+"\n");
	        }       
	    }


	public JTextPane getTextPane() {
		return TextPane;
	}

	public void setTextPane(JTextPane textPane) {
		TextPane = textPane;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getResumeButton() {
		return resumeButton;
	}

	public void setResumeButton(JButton resumeButton) {
		this.resumeButton = resumeButton;
	}

	public JButton getNewgame() {
		return newgame;
	}

	public void setNewgame(JButton newgame) {
		this.newgame = newgame;
	}
	
	
}
