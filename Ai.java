import java.awt.Point;
import java.util.*;

public class Ai {
	private int turnCount = 0;
	private String min_node = "Player";
	private String max_node = "PC";
	public String string = "";
	public ArrayList<State> resultingTree = new ArrayList<State>();
	private int value;
	private Point move = new Point(0,0);
	public Ai() {}


	/*
	value(s)
		if s is terminal: return utility(s)
		if s is max_node: return max_value(s)
		if s is min_node: return min_value(s)
	*/	
	public Utility values(State s, int alpha, int beta) {
		// s.print();
		if (checkTerminal(s)) {
			return s.utility;
			
		} else if (s.node_value == max_node) {
			return max_value(s, alpha, beta);
		} else if (s.node_value == min_node) {
			return min_value(s, alpha, beta);
		}
		return null;
	}

	//check if state is terminal
	public boolean checkTerminal (State s) {
		//checker of winning condition
		if (s.getIndex(0,0) == s.getIndex(0,1) && s.getIndex(0, 1) == s.getIndex(0,2) && s.getIndex(0, 2)!= 0) {
			if (s.getIndex(0,0) == 1) {
				s.utility.value = -1;
			} else if (s.getIndex(0,0) == 2) {
				s.utility.value = 1;
			}
			return true;
		} else if (s.getIndex(1,0) == s.getIndex(1,1) && s.getIndex(1,1) == s.getIndex(1,2) && s.getIndex(1,2)!= 0) { //[1] horizontal
			if (s.getIndex(1,0) == 1) {
				s.utility.value = -1;
			} else if (s.getIndex(1,0) == 2) {
				s.utility.value = 1;
			}
			return true;
		} else if (s.getIndex(2, 0) == s.getIndex(2,1) && s.getIndex(2,1) == s.getIndex(2,2) && s.getIndex(2,2)!= 0) { //[2] horizontal
			if (s.getIndex(2,0) == 1) {
				s.utility.value = -1;
			} else if (s.getIndex(2,0) == 2) {
				s.utility.value = 1;
			}
			return true;
		} else if (s.getIndex(0, 0) == s.getIndex(1, 0) && s.getIndex(1,0) == s.getIndex(2, 0) && s.getIndex(2, 0)!= 0) { //[0] vertical
			if (s.getIndex(0,0) == 1) {
				s.utility.value = -1;
			} else if (s.getIndex(0,0) == 2) {
				s.utility.value = 1;
			}
			return true;
		} else if (s.getIndex(0, 1) == s.getIndex(1, 1) && s.getIndex(1, 1) == s.getIndex(2, 1) && s.getIndex(2, 1)!= 0) { //[1] vertical
			if (s.getIndex(0,1) == 1) {
				s.utility.value = -1;
			} else if (s.getIndex(0,1) == 2) {
				s.utility.value = 1;
			}
			return true;
		} else if (s.getIndex(0, 2) == s.getIndex(1, 2) && s.getIndex(1, 2) == s.getIndex(2, 2) && s.getIndex(2,2)!= 0) { //[0] vertical
			if (s.getIndex(0,2) == 1) {
				s.utility.value = -1;
			} else if (s.getIndex(0,2) == 2) {
				s.utility.value = 1;
			}
			return true;
		} else if (s.getIndex(0, 0) == s.getIndex(1, 1) && s.getIndex(1,1) == s.getIndex(2,2) && s.getIndex(2,2)!= 0) {
			if (s.getIndex(0,0) == 1) {
				s.utility.value = -1;
			} else if (s.getIndex(0,0) == 2) {
				s.utility.value = 1;
			}
			return true;
		} else if (s.getIndex(0,2) == s.getIndex(1,1) && s.getIndex(1,1) == s.getIndex(2,0) && s.getIndex(2,0)!= 0) {
			if (s.getIndex(0,2) == 1) {
				s.utility.value = -1;
			} else if (s.getIndex(0,2) == 2) {
				s.utility.value = 1;
			}
			return true;
		}
		//checker of draw condition
		if(s.getIndex(0, 0) != 0 && s.getIndex(0, 1) != 0 && s.getIndex(0,2) != 0
		   && s.getIndex(1, 0) != 0 && s.getIndex(1, 1) != 0 && s.getIndex(1, 2) != 0
		   && s.getIndex(2, 0) != 0 && s.getIndex(2, 1) != 0 && s.getIndex(2, 2) != 0)
			return true;
		return false;
	}

	//action function for getting possible action for a state
	public void Actions(State s) {
		s.list.clear();
		for (int i = 0; i < 3 ; i++ ) {
			for (int j = 0; j < 3 ; j++ ) {
				if (s.state[i][j] == 0) {
					Point x = new Point(i,j);
					s.list.add(x);
				}				
			}	
		}
	}

	//function for getting resulting state for a move
	public State Result(State s, Point a) {
		State newState = new State(s.state);
		if (s.node_value == "PC") {
			newState.node_value = "Player";
			
		} else {
			newState.node_value = "PC";
			
		}

		newState.copyList(s.list);
		newState.makeMove(a);

		newState.utility.move = a;
		resultingTree.add(newState);

		return newState;
	}

	public Utility max_value (State s, int alpha, int beta) {
		Utility n = null;
		Actions(s);
		int v;
		int m = -999999;
		for (Point p : s.list ) {
			v = values(Result(s,p), alpha, beta).value;
			if (v >= beta) {
				m = v;
				alpha = m;
				n = new Utility(p,v);
				return n;
			}
			if(v > m){
				m = v;
				alpha = max(alpha, v); 
				n = new Utility(p,v);
			}
		}

		return n;
	}

	public Utility min_value (State s, int alpha, int beta) {
		Utility n = null;
		Actions(s);
		int v;
		int m = 999999;
		for (Point p : s.list ) {
			v = values(Result(s,p), alpha, beta).value;
			if (v <= alpha) {
				m = v;
				beta = m;
				n = new Utility(p,v);
				return n;
			}
			if(v < m){
				m = v;		
				beta = min(beta, v);
				n = new Utility(p,v);
			}
		}
		return n;
	}

	public int max(int alpha, int v){
		return (alpha > v) ? alpha : v;
	}

	public int min(int beta, int v){
		return (beta < v) ? beta : v;
	}
}