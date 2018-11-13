import java.awt.*;
import javax.swing.*;
public class Cercle{
	int diametre,x,y;
	private Color couleur;
	boolean backX = false;
	boolean backY = false;
	
	public Cercle(int x, int y, int r) {
		super();
		this.x = x;
		this.y = y;
		this.diametre = r;
	}

	public int getX() {return x;}
	public int getY() {return y;}
	
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}

	public int getDiametre() {return diametre;}
	public Color getColor() {return couleur;}

	public void setDiametre(int r) {diametre = r;}
	public void setColor(Color c) {couleur = c;}
	
	public void draw(Graphics g) {
		g.fillOval(x, y, diametre, diametre);		
	}

	public void  move(Panneau pan) {
		int x = getX(); int y = getY();
		
		// Si la coordonnée x est inférieure à 1, on avance.
		// Si la coordonnée x est supérieure à la taille du Panneau moins la taille du rond, on recule
		if (x < 1) backX = false;
		if (x > pan.getWidth() - getDiametre()) backX = true;

		// Idem pour l'axe y
		if (y < 1) backY = false;
		if (y > pan.getHeight() - getDiametre()) backY = true;

		// Si on avance, on incrémente la coordonnée
		if (!backX) {setX(++x);}
		else setX(--x);

		// Idem pour l'axe Y
		if (!backY) {setY(++y);}
		else setY(--y);
	}
}