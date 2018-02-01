
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class GameScreen {
	String current = "Player";
	JPanel panel = new JPanel();
	JButton back = new JButton("Back");
	JLabel label = new JLabel("Turn: "+current);
	Ai ai = new Ai();

	JButton[][] button = new JButton[3][3];
	public int turnCount,vcount;
	int[][] arr = {{0,0,0},{0,0,0},{0,0,0}};
	State value = new State(arr);
	int row,col,x;
	int cor1,cor2;
	int alpha = -9999999;
	int beta = 99999999;


	
	public GameScreen() {
		panel.setLayout(new BorderLayout());
		panel.add(addComponentsCenter(), BorderLayout.CENTER);
		panel.add(addComponentsAbove(), BorderLayout.NORTH);
		panel.add(addComponentsBelow(), BorderLayout.SOUTH);
		panel.add(addComponentsEast(), BorderLayout.EAST);
		panel.add(addComponentsWest(), BorderLayout.WEST);
	}
	
	public JPanel addComponentsCenter(){
		int i;
		Dimension dimension = new Dimension(30,30);
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(150, 150));
		panel.setLayout(new GridLayout(3,3));
		

		for (row = 0; row < 3; row++) {
		 for (col = 0; col < 3; col++) {
		    button[row][col] = new JButton();
		    button[row][col].addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){

		    		for (int i = 0; i < 3; i++) {
		    		  for (int j = 0; j < 3; j++) {
		    		    if( button[i][j] == e.getSource() ) {
		    		    	if(value.getIndex(i,j) == 0){
		    		    		if(current == "Player") {
		    		    			value.setIndex(i,j,x+1);
		    		    			vcount++;
		    		      			turnCount++; 
		    		    		}		
		    		    		   		      		
		    		      		
		    		      	}
		    		    }
		    		  }
		    		}
		    		render();

		    	}			
		    });
		    button[row][col].setPreferredSize(dimension);
		    panel.add(button[row][col]);
		 }
		}

		
		
		return panel;
	}
	
	public void render() {
		String winner = "";
		System.out.println(vcount);
		if (turnCount%2 == 0 && turnCount != 0) {
			current = "Player";
			label.setText("Turn: "+current);
			value.node_value = "Player";
			winner = "PC";
			
		} else if(turnCount%2 != 0 ) {
			current = "PC";
			label.setText("Turn: "+current);
			value.node_value = "PC";
			movePC();		
			winner = "Player";
		}

		ImageIcon x = new ImageIcon("x.png");
		ImageIcon o = new ImageIcon("o.png");

		Image image1 = x.getImage();
		image1 = image1.getScaledInstance(image1.getWidth(null)/4, image1.getHeight(null)/4, Image.SCALE_SMOOTH);
		x.setImage(image1);

		Image image2 = o.getImage();
		image2 = image2.getScaledInstance(image2.getWidth(null)/4, image2.getHeight(null)/4, Image.SCALE_SMOOTH);
		o.setImage(image2);

		for (int i = 0; i < 3; i++) {
		  for (int j = 0; j < 3; j++) {
		    if (value.getIndex(i,j) == 1) {
		    	button[i][j].setIcon(x);
		    } else if (value.getIndex(i,j) == 2) {
		    	button[i][j].setIcon(o);
		    }
		  }
		}

		System.out.println(winner);
		checkWin(winner);

		
	}

	private void print() {
		

		System.out.println();
		for (row = 0; row < 3; row++) {
		    for (col = 0; col < 3; col++) {
		       System.out.print(value.getIndex(row,col)+" ");
		    }
		    System.out.println();

		}
		System.out.println();
	}

	private void checkWin (String winner) {
		JOptionPane j = new JOptionPane();
		//[0] horizontal
		if (value.getIndex(0,0) == value.getIndex(0,1) && value.getIndex(0, 1) == value.getIndex(0,2) && value.getIndex(0, 2)!= 0) {
			j.showMessageDialog(null, "Winner!  -- "+winner);
			System.exit(1);
		} else if (value.getIndex(1,0) == value.getIndex(1,1) && value.getIndex(1,1) == value.getIndex(1,2) && value.getIndex(1,2)!= 0) { //[1] horizontal
			j.showMessageDialog(null, "Winner!  -- "+winner);
			System.exit(1);
		} else if (value.getIndex(2, 0) == value.getIndex(2,1) && value.getIndex(2,1) == value.getIndex(2,2) && value.getIndex(2,2)!= 0) { //[2] horizontal
			j.showMessageDialog(null, "Winner!  -- "+winner);
			System.exit(1);
		} else if (value.getIndex(0, 0) == value.getIndex(1, 0) && value.getIndex(1,0) == value.getIndex(2, 0) && value.getIndex(2, 0)!= 0) { //[0] vertical
			j.showMessageDialog(null, "Winner!  -- "+winner);
			System.exit(1);
		} else if (value.getIndex(0, 1) == value.getIndex(1, 1) && value.getIndex(1, 1) == value.getIndex(2, 1) && value.getIndex(2, 1)!= 0) { //[1] vertical
			j.showMessageDialog(null, "Winner!  -- "+winner);
			System.exit(1);
		} else if (value.getIndex(0, 2) == value.getIndex(1, 2) && value.getIndex(1, 2) == value.getIndex(2, 2) && value.getIndex(2,2)!= 0) { //[0] vertical
			j.showMessageDialog(null, "Winner!  -- "+winner);
			System.exit(1);
		} else if (value.getIndex(0, 0) == value.getIndex(1, 1) && value.getIndex(1,1) == value.getIndex(2,2) && value.getIndex(2,2)!= 0) {
			j.showMessageDialog(null, "Winner!  -- "+winner);
			System.exit(1);
		} else if (value.getIndex(0,2) == value.getIndex(1,1) && value.getIndex(1,1) == value.getIndex(2,0) && value.getIndex(2,0)!= 0) {
			j.showMessageDialog(null, "Winner!  -- "+winner);
			System.exit(1);
		} else if (vcount > 9){
			j.showMessageDialog(null, "Draw!");
			System.exit(1);
		}
	}

	public JPanel addComponentsEast(){
		JPanel panel = new JPanel();
				
		panel.setPreferredSize(new Dimension(100, 300));		
		return panel;
	}
	
	public JPanel addComponentsWest(){
		JPanel panel = new JPanel();
				
		panel.setPreferredSize(new Dimension(100, 300));		
		return panel;
	}

	public JComponent getMainComponent(){
		return panel;
	}
	
	public JPanel addComponentsAbove(){
		JPanel panel = new JPanel();
				
		panel.add(label);
		panel.setPreferredSize(new Dimension(500, 50));
		
		return panel;
	}
	
	public JPanel addComponentsBelow(){
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 50));
		
		return panel;
	}
	
	public void movePC() {
		Utility u = ai.values(value, alpha, beta);
				
		// value.setPcAdvantage(u.value);
		if (value.getIndex((int)u.move.getX(),(int)u.move.getY()) == 0) {
			value.setIndex((int)u.move.getX(),(int)u.move.getY(),2);
		}
		
		ai.resultingTree.clear();

		turnCount++;
		vcount++;
		render();
	}

		

}
