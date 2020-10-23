package gui1;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphics01 extends JFrame{
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Mydraw draw = new Mydraw();
		jf.add(draw);
		jf.setSize(1000, 1000);
		jf.setVisible(true);
	}
}
class Mydraw extends JPanel{
	Font f = new Font("TimesRoman",Font.BOLD,18);
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.setFont(f);
		g.drawString("hellosdfsdf".toUpperCase(), 30, 40);
		FontMetrics fm = g.getFontMetrics();
		Integer a = fm.getAscent();
		Integer b = fm.getMaxAdvance();
		System.out.println(a.toString()+" "+b.toString());
		//------
		g.setColor(Color.red);
		g.fillRect(50, 50, 80, 50);
		//------
		g.drawOval(40, 40, 400, 400);
		g.drawArc(0, 0, 230, 230, 90, 90);
		int[] x = {0,getWidth()/2,getWidth()};
		g.setColor(Color.GREEN);
		int[] y = {getHeight()/3,0,getHeight()/3};
		g.drawPolygon(x, y, 3);
	}
}
