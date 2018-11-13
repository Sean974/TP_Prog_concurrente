import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Fenetre extends JFrame implements ActionListener{

	private Panneau pan = new Panneau();
	Mouvement bouge = new Mouvement(pan.cercle,pan,false);
	DesactivePause rebouge = new DesactivePause(pan.cercle,pan,bouge);

	JButton bt_ajoute, bt_retire, bt_pause;
	JLabel Temps = new JLabel();
	private boolean flag = false;

	int k=0;
	Timer t = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Temps.setText("Temps : "+String.valueOf(k)+"s");
			k++;
		}
	});

	public Fenetre() {
		JFrame fen = new JFrame();
		Container conteneur = fen.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(pan);

		//[gestion de la fermeture de la fenêtre
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fen.setTitle("Animation");
		fen.setSize(450, 450);
		fen.setLocationRelativeTo(null);
		fen.setVisible(true);

		//[Zone bas
		JPanel zone1 = new JPanel();
		zone1.setLayout(new FlowLayout());
		conteneur.add(zone1, BorderLayout.SOUTH);

		//[Zone haut
		JPanel zone2 = new JPanel();
		zone2.setLayout(new FlowLayout());
		conteneur.add(zone2, BorderLayout.NORTH);

		//[Zone haut centre
		JPanel zone2_centre = new JPanel();
		GridLayout g1 = new GridLayout(1,2);
		g1.setHgap(100); g1.setVgap(100);
		zone2_centre.setLayout(g1);
		zone2.add(zone2_centre, BorderLayout.CENTER);
		zone2_centre.add(bouge.lab);
		zone2_centre.add(Temps);

		//[Boutons
		bt_ajoute = new JButton("+");
		bt_retire = new JButton("-");
		bt_pause = new JButton("stop");
		zone1.add(bt_ajoute);
		zone1.add(bt_retire);
		zone1.add(bt_pause);

		//[Ecoute des évènements
		bt_ajoute.addActionListener(this);
		bt_retire.addActionListener(this);
		bt_pause.addActionListener(this);

		//[Ajout du thread Panneau
		Thread t1 = new Thread(bouge);
		Thread t2 = new Thread(rebouge);
		t1.start();
		t2.start();

		//[Ajout du temps
		t.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		Color[] Tab = {Color.black, Color.blue, Color.cyan, Color.red, Color.green, Color.magenta, Color.orange, Color.yellow };

		if(source instanceof JButton) {
			JButton bt_source = (JButton) source;
			String bt_titre = bt_source.getText();

			switch(bt_titre) {
			case "+":
				if(pan.cercle.size() < 5 && flag == false) {
					Cercle c1 = new Cercle((int)(Math.random()*(450-0)+1),(int)(Math.random()*(450-0)+1),20);
					c1.setColor(Tab[(int)(Math.random()*8)]);
					pan.cercle.add(c1);
				}
				break;

			case "-":
				if(pan.cercle.size() > 0 ) {
					pan.cercle.remove(pan.cercle.get(pan.cercle.size()-1));
					pan.repaint();
				}
				break;

			case "stop":
				bouge.setPause(true);
				bt_pause.setText("start");
				flag = true;
				t.stop();
				break;

			case "start":
				bouge.setPause(false);
				bt_pause.setText("stop");
				flag = false;
				t.start();
				break;
			}
		}
	}

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
	}
}