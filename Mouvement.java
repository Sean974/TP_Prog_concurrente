import java.util.*;

import javax.swing.JLabel;

public class Mouvement extends Thread{
	ArrayList<Cercle> cercle = new ArrayList<Cercle>();
	private Panneau pan = new Panneau();
	public JLabel lab = new JLabel("Score : 0");
	public int nb_collision = 0;
	public boolean pause = false;

	public Mouvement(ArrayList<Cercle> c, Panneau pan, boolean p) {
		this.pause = p;
		this.cercle = c;
		this.pan = pan;
		for(int i=0; i<cercle.size();i++) {
			int x = (int)(Math.random()*(300-0)+1);
			int y = (int)(Math.random()*(300-0)+1);

			cercle.get(i).setX(x);
			cercle.get(i).setY(y);
		}
	}
	
	public synchronized void relance(){
		notifyAll();
	}

	public synchronized void run() {
		while(true) {
			try {
				while(pause == true) wait();
				for(int i=0;i<cercle.size();i++) {cercle.get(i).move(pan);}
				Collision();
				pan.repaint();
				sleep(3);
			}catch(InterruptedException e) {}
		}
	}
	
	public boolean getPause() {return pause;}
	public void setPause(boolean p) {this.pause = p;}
	
	public void Collision() {
		int x = 0;
		int y = 0;
		int distance = 0;

		int savedI=0;
		int savedH=0;
		boolean collision = false;

		for(int i=0;i<cercle.size();i++) {
			for(int h=0;h<cercle.size();h++) {
				if(i != h) {
					x = cercle.get(i).getX() - cercle.get(h).getX();
					y = cercle.get(i).getY() - cercle.get(h).getY();
					distance = x*x + y*y;

					if(distance < ((cercle.get(i).getDiametre()/2 + cercle.get(h).getDiametre())/2) * (cercle.get(i).getDiametre()/2 + cercle.get(h).getDiametre()/2)) {
						collision = true;
						savedI = i;
						savedH = h;
					}
				}
			}
		}
		
		if(collision == true) {
			cercle.remove(savedI);
			cercle.remove(savedH);
			collision = false;
			nb_collision++;
			
			ThreadCollisionAffiche p = new ThreadCollisionAffiche(lab,nb_collision);
			p.start();
			System.out.println("Nombre de collision: "+nb_collision);
		}
	}
}