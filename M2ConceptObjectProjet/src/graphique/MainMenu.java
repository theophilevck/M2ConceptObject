package graphique;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import entity.Map;
import entity.Simulation;

public class MainMenu extends JFrame{
	
	private JTextPane TextPane;
	MainMenu(){
		setTitle("star wars");
		setSize(1250,1000);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);
		
		this.TextPane = new JTextPane();
		TextPane.setEditable(false);
		JScrollPane jp = new JScrollPane( TextPane );  
		jp.setBounds(860,100,350,200);
		jp.setBorder(new LineBorder(Color.BLACK));
		this.appendToPane("test3\ntest3\ntest3\ntest3\ntest3\n");
		this.add( jp );
		
		BoardGame boardGame=new BoardGame(new Simulation(new Map(20,20)),this);
		boardGame.setBounds(10,10,1250,1000);
		boardGame.setVisible(true);
		add(boardGame);
		
		
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
	
	 public void appendToPane( String txt) {
		 	txt=txt+"\n";
		 	this.TextPane.setEditable(true);
	        int len = this.TextPane.getDocument().getLength();
	        this.TextPane.setCaretPosition(len);
	        this.TextPane.replaceSelection(txt);
	        this.TextPane.setEditable(false);
	        this.TextPane.update(this.TextPane.getGraphics());
	    }
	
	
}
