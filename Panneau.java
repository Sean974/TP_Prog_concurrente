import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;

public class Panneau extends JPanel {
	ArrayList<Cercle> cercle = new ArrayList<Cercle>();
	
	public void paintComponent(Graphics g) {

		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		Color c = g.getColor();		
		for(int i=0; i<cercle.size();i++) {
			g.setColor(cercle.get(i).getColor());
			cercle.get(i).draw(g);
			g.setColor(c);
		}
	}
}