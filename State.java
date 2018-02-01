import java.util.*;
import java.awt.Point;

public class State{
	public int[][] state = new int[3][3];
	public String node_value;
	public ArrayList<Point> list = new ArrayList<Point>(); 	//list of possible actions
	public Utility utility;
	// public int alpha;
	// public int beta;

	public State(int[][] state){
		int length = state.length;
		for(int i = 0; i < length; i++){
			System.arraycopy(state[i], 0, this.state[i], 0, state[i].length); 
		}
		utility = new Utility(new Point(0,0),0);
	}
	//getters
	public int[][] getState(){
		return this.state;
	}

	public int getIndex(int x, int y){
		return state[x][y];
	}

	//setters
	public void setIndex(int x, int y, int value){
		state[x][y] = value;
	}

	public void copyList(List<Point> l) {
		if (list.size() != 0) {
			for (int i = 0 ;i < l.size() ; i++ ) {
				this.list.set(i,l.get(i));
			}	
		}
			
	}

	//updating state when pc makes a move
	public void makeMove(Point p) {
		if (this.node_value == "PC") {
			this.state[(int)p.getX()][(int)p.getY()] = 1;
		} else {
			this.state[(int)p.getX()][(int)p.getY()] = 2;
		}
	}

	public void print() {
		

		System.out.println();
		for (int row = 0; row < 3; row++) {
		    for (int col = 0; col < 3; col++) {
		       System.out.print(getIndex(row,col)+" ");
		    }
		    System.out.println();

		}
		System.out.println();
	}
}