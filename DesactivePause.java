import java.util.ArrayList;

public class DesactivePause extends Thread{
	ArrayList<Cercle> cercle = new ArrayList<Cercle>();
	private Panneau pan = new Panneau();
	Mouvement move = new Mouvement(cercle,pan,false);
	
	public DesactivePause(ArrayList<Cercle> c, Panneau p, Mouvement m) {
		this.cercle = c;
		this.pan = p;
		this.move = m;
	}

	public synchronized void run(){
		boolean a = move.getPause();
		while(true) {
			if(a == false) move.relance();
		}
	}
}