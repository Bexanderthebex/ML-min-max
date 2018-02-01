import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main{
	private static GameScreen g;
	private static Dimension dimension = new Dimension(700, 500);
	private static JPanel cards = new JPanel(new CardLayout());

	public Main() {
		g = new GameScreen();
	}

	public static void main(String[] args) {
		Main m = new Main();
		JPanel p = new JPanel(new BorderLayout());
		

		JFrame frame = new JFrame("TicTacToe");
		frame.setPreferredSize(dimension);
		frame.setMinimumSize(dimension);
		frame.setResizable(false);
		
		Container c = frame.getContentPane();

		

		//panel.add(addComponentsCenter(), BorderLayout.CENTER);
		p.add(setCenterComponents(), BorderLayout.CENTER);

		cards.add(p,"start");
		cards.add(g.panel,"game");
				
		c.add(cards);

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	private static JPanel setCenterComponents() {
		JPanel p = new JPanel();
		JButton player = new JButton("Player");
		JButton pc = new JButton("PC");

		player.setPreferredSize(new Dimension(300, 100));
		pc.setPreferredSize(new Dimension(300, 100));

		player.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				g.turnCount = 0;
				g.vcount = 0;

				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.show(cards, "game");

				g.render();
			}			
		});

		pc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				g.turnCount = 1;
				g.vcount = 1;

				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.show(cards, "game");

				g.render();
			}			
		});
		p.add(player);
		p.add(pc);

		return p;

	}

	
}