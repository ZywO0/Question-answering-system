package gui1;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
public class Simplegui extends JFrame{
	
	public static void main(String[] args) {
//		JFrame myFrame = new JFrame();
//		Font f = new Font("TimesRoman",Font.BOLD,18);
//		JPanel jp = new JPanel();
////		JButton myButton = new JButton("����");
//		JLabel j1 = new JLabel("ysm1");
//		j1.setLocation(100,100);
//		jp.add(j1);
//		JLabel j2 = new JLabel("ysm2");
//		j2.setLocation(70,30);
//		jp.add(j2);
////		myButton.setFont(f);
////		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		myFrame.getContentPane().add(myButton);
//		myFrame.add(jp);
//		myFrame.setSize(200, 200);
//		myFrame.setVisible(true);
////		System.out.println("\t\t\tsss\nfdf");
		JLabel j1 = new JLabel("ysm1");
		j1.setBounds(150, 50, 100, 50);
		JLabel j2 = new JLabel("ysm2");
		j2.setBounds(150, 100, 100, 50);
		JLabel j3 = new JLabel("ysm3");
		j3.setBounds(150, 150, 100, 50);
		JLabel j4 = new JLabel(4+"");
		j4.setBounds(150, 200, 100, 50);
		JLabel j5 = new JLabel(5+"");
		JLabel j6 = new JLabel(6+"");
		
		JFrame myFrame = new JFrame();
		myFrame.setSize(500, 500);
		myFrame.setLayout(null);
		
	
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(6,1,0,10));
		jp.setBounds(150, 50, 300, 400);
		
		jp.add(j1);
		jp.add(j2);
		jp.add(j3);
		jp.add(j4);
		jp.add(j5);
		jp.add(j6);
		
		myFrame.add(jp);
		myFrame.setVisible(true);
	}
}
