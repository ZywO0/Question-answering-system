package gui1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animations {
	int x= 50;
	int y = 50;
	public void go() {
		JFrame jf = new JFrame();
		MyDrawingPanel md = new MyDrawingPanel();
		jf.add(md);
		jf.setSize(500,500);
		jf.setVisible(true);
		for(int i=0;i<500;i++) {
			x++;y++;
			md.repaint();
			try {
				Thread.sleep(50);
			}catch(Exception ex) {
				
			}
		}
	}
	class MyDrawingPanel extends JPanel{
		public void paintComponent(Graphics mg) {
			int red = (int) (Math.random()*255);
			int green = (int) (Math.random()*255);
			int blue = (int) (Math.random()*255);
			Color randomco = new Color(red,green,blue); 
			mg.setColor(randomco);
			mg.fillRect(x, y, 50, 50);
		}
	}

	public static void main(String[] args) {
		Animations an = new Animations();
		an.go();
		
	}
}
