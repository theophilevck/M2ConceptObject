package graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import entity.Map;
import entity.Simulation;

public class MainMenu extends JFrame{

	MainMenu(){
		setTitle("star wars");
		setSize(1264,1000);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);
		
		
		/*
		 * content
		 * */
		/*JButton startButton =new JButton();
		startButton.setText("start");
		startButton.setBounds(700,50,200,25);
		add(startButton);*/
		
		
		
		
		
		BoardGame boardGame=new BoardGame(new Simulation(new Map(20,20)));
		boardGame.setBounds(10,10,1000,1000);
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
	
}
