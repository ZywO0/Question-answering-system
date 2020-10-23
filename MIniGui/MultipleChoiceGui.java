package MIniGui;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.*;
public class MultipleChoiceGui extends JFrame{
	public static int score =0;
	public static int attempt=0;	
	public static int choice=0;
	String[][] a = new String[8][4];// the answers of each question
	String[] s = new String[8];// the sentences of four questions 
	//read text;
	{
		try {
			FileReader fr = new FileReader("question.txt");
			BufferedReader br = new BufferedReader(fr);
			for(int i=0;i<8;i++) {
				s[i] = br.readLine();
			}
			br.close();
			fr.close();
		}catch(IOException e) {
			System.out.println("error");
			System.exit(1);
		}
		try {
			FileReader fr = new FileReader("answer.txt");
			BufferedReader br = new BufferedReader(fr);
			for(int i=0;i<8;i++) {
				for(int j=0;j<4;j++)
				a[i][j] = br.readLine();
			}
			br.close();
			fr.close();
		}catch(IOException e) {
			System.out.println("error");
			System.exit(1);
		}
	}
	static JLabel[] jl = new JLabel[9];{//the question
		for (int i =0;i<8;i++)
			jl[i] = new JLabel(s[i]);
	}
	static JLabel jl1 = new JLabel("score="+score);//the current score
	static JPanel[][]jp = new JPanel[8][2];{ // all panels I need
		for(int i=0;i<8;i++) {
			for(int j=0;j<2;j++) {
				jp[i][j]=new JPanel();
			}
		}	
	}
	static JPanel sjp = new JPanel();
	static JPanel bjp = new JPanel();
	static JPanel djp = new JPanel();
	Font f = new Font(s[0],Font .BOLD ,15);//the font of four questions
	Border b;//the border in the second panel
	static JButton[][] jb = new JButton[8][4];{	//the answer buttons
		for(int i = 0; i<8 ; i++) {//question
			for(int j=0;j<4;j++) {//which button
				jb[i][j] =new JButton(a[i][j]);
				jb[i][j].addActionListener(new Answer());
				jp[i][1].add(jb[i][j]);
			}
		}
	}
	static JButton jb1 = new JButton();{// reset score button 
		jb1.setLocation(160,1);
		jb1.setSize(100,28);
		jb1.addActionListener(new Resetbut()); 
		jb1.setText("reset score");
	}
	static JRadioButton jr1,jr2,jr3,jr4,jr5;{
		jr1 = new JRadioButton("Easy");
		jr1.addActionListener(new Choice());
		jr2 =new JRadioButton("Medium");
		jr2.addActionListener(new Choice());
		jr3 = new JRadioButton("Difficult");
		jr3.addActionListener(new Choice());
		jr4 = new JRadioButton("One attempt");
		jr4.addActionListener(new Dchoice());
		jr5 = new JRadioButton("Two attempts");
		jr5.addActionListener(new Dchoice());
	}
	JLabel jl2 =new JLabel("Choose the level");
	JLabel jl3 = new JLabel("Choose the attempt");
	JLabel endl = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Congratulations!<br>If you want to continue please press \"reset score\"</html>");
	
	ButtonGroup g1 = new ButtonGroup();{
		g1.add(jr1);g1.add(jr2);g1.add(jr3);
	}
	ButtonGroup g2 = new ButtonGroup();{
		g2.add(jr4);g2.add(jr5);
	}
	public MultipleChoiceGui() {
		this.setSize(600, 350);
		this.setLayout(null);
		this.setTitle("Multiple Choice Questions");
		//choice panel
		sjp.setLayout(new GridLayout(4,1,0,10));
		sjp.setBounds(240,50, 300, 200);
		sjp.add(jl2);
		jl2.setFont(f);
		sjp.add(jr1);
		jr1.setFont(f);
		sjp.add(jr2);
		jr2.setFont(f);
		sjp.add(jr3);
		jr3.setFont(f);
		this.add(sjp);
		for(int i=0;i<8;i++) {
			//double choice panel
			djp.setLayout(new GridLayout(4,1,0,10));
			djp.setBounds(220,60, 300, 200);
			djp.add(jl3);
			jl3.setFont(f);
			djp.add(jr4);
			jr4.setFont(f);
			djp.add(jr5);
			jr5.setFont(f);
			this.add(djp);
			//the first panel
			jp[i][0].setLayout(null);
			jp[i][0].setBounds(120, 5, 530,30);
			jl[i].setText(s[i]);
			jl[i].setFont(f);
			jl[i].setLocation(0, 0);
			jl[i].setSize(530,30);
			jp[i][0].add(jl[i]);
			this.add(jp[i][0]);
			//the second panel
			jp[i][1].setBounds(120,50, 350, 200);
			jp[i][1].setLayout(new GridLayout(5, 1, 0, 10));
			b = BorderFactory.createTitledBorder("Possible Answers: click one");
			jp[i][1].setBorder (b);
			this.add(jp[i][1]);	
		}
		//the third panel
		bjp.setLayout(null);
		bjp.setBounds(120,260, 330, 30);
		bjp.add(jl1);
		jl1.setLocation(70,10);//score record
		jl1.setSize(80,10);	
		bjp.add(jb1);
		this.add(bjp);
		//end label
		endl.setBounds(120,70, 430, 100);
		endl.setVisible(false);
		endl.setFont(f);
		this.add(endl);
		clears();
		sjp.setVisible(true);
		bjp.setVisible(false);
		djp.setVisible(false);
		this.setVisible(true);
	}
	class Answer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton eventSource = (JButton) e.getSource();
			//easy question
			for(int i =0;i<3;i++) {
				for(int j=0;j<4;j++) {
					if(eventSource.equals(jb[i][j])&&i!=2){
						if(eventSource.equals(jb[0][0])) 
							rightcp(i,1);
						else 
							falsecp(i);		
						if(eventSource.equals(jb[1][2])) 
							rightcp(i,1);
						else 
							falsecp(i);			
					}else if(eventSource.equals(jb[i][j])&&i==2){
						if(eventSource.equals(jb[2][2])) {
							score = score +  1;
							jl1.setText("score="+score);
							clears();
							endl.setVisible(true);
						}else {
							clears();
							endl.setVisible(true);
						}
					}
				}
			}
			//medium questions
			if(choice == 0) {
				for(int i=3;i<6;i++) {
					for(int j=0;j<4;j++) {
						if(eventSource.equals(jb[i][j])&&i!=5) {
							if(eventSource.equals(jb[3][0])) 
								rightcp(i,2);
							else 
								falsecp(i);		
							if(eventSource.equals(jb[4][1])) 
								rightcp(i,2);
							else 
								falsecp(i);				
						}
						if(eventSource.equals(jb[i][j])&&i==5) {
							if(eventSource.equals(jb[5][3])) {
								score = score +  2;
								jl1.setText("score="+score);
								clears();
								endl.setVisible(true);
							}else {
								clears();
								endl.setVisible(true);
							}
						}
					}
				}
			}
			if(choice == 1) {
				for(int i=3;i<6;i++) {
					for(int j=0;j<4;j++) {
						if(eventSource.equals(jb[i][j])&&i!=5){
							switch (i){
								case 3:{
									if(eventSource.equals(jb[3][0])) { //right answer
										if(attempt ==0) {
											rightcp(i,2);
											attempt =0;
										
										}else {
											rightcp(i, 1);
											attempt =0;
										}
									
									}
									else {//wrong answer
										if(attempt ==0) {
											attempt =1;

										}else {
											falsecp(i);
											attempt = 0;
										}
									}
									break;
								}
								case 4:{
									if(eventSource.equals(jb[4][1])){
										if(attempt ==0) {
											rightcp(i,2);
											attempt =0;
										}else {
											rightcp(i, 1);
											attempt =0;
										}
									}
									else {
										if(attempt ==0) {
											attempt =1;
										
										}else {
											falsecp(i);
											attempt = 0;
										}
									}
									break;
								}
							}
						}else if(eventSource.equals(jb[i][j])&&i==5) {
							if(eventSource.equals(jb[5][3])) {
								if(attempt ==0) {
									score = score + 2;
									jl1.setText("score="+score);
									clears();
									endl.setVisible(true);
									choice =0;
								}
								else {
									score = score + 1;
									jl1.setText("score="+score);
									clears();
									endl.setVisible(true);
									attempt =0;
									choice =0;
								}
							}else  {
								if(attempt ==0) {
									attempt =1;
								
								}else {
									clears();
									endl.setVisible(true);
									attempt =0;
									choice =0;
								}
							}
						}
					}
				}
			}
			//hard question
			for(int i =6;i<8;i++) {
				for(int j=0;j<4;j++) {
					if(eventSource.equals(jb[i][j])&&i!=7) {
						if(eventSource.equals(jb[6][3])) 
							rightcp(i,3);
						else 
							falsecp(i);
					}
					if(eventSource.equals(jb[i][j])&&i==7) {
						if(eventSource.equals(jb[7][1])) {
							score = score +  3;
							jl1.setText("score="+score);
							clears();
							endl.setVisible(true);
						}else {
							clears();
							endl.setVisible(true);
						}
					}
				}
			}
		
		}
	 }
	class Resetbut implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			score =0;
			jl1.setText("score="+score);
			clears();
			bjp.setVisible(false);
			endl.setVisible(false);
			sjp.setVisible(true);
			g1.clearSelection();
			g2.clearSelection();
		 }
	 }
	class Choice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JRadioButton eventSource = (JRadioButton) e.getSource();
			if(eventSource.equals(jr1)) {
				 sjp.setVisible(false);
				 jp[0][0].setVisible(true);
				 jp[0][1].setVisible(true);
				 bjp.setVisible(true);
			}
			if(eventSource.equals(jr2)) {
				 sjp.setVisible(false);
				 clears();
				 djp.setVisible(true);
			}
			if(eventSource.equals(jr3)) {
				 sjp.setVisible(false);
				 clears();
				 jp[6][0].setVisible(true);
				 jp[6][1].setVisible(true);
				 bjp.setVisible(true);			 
			}
		}
	}
	class Dchoice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JRadioButton eventSource = (JRadioButton) e.getSource();
			if(eventSource.equals(jr4)) {
				djp.setVisible(false);
				jp[3][0].setVisible(true);
				jp[3][1].setVisible(true);
				bjp.setVisible(true);
				choice = 0;
			}
			if(eventSource.equals(jr5)) {
				djp.setVisible(false);
				jp[3][0].setVisible(true);
				jp[3][1].setVisible(true);
				bjp.setVisible(true);
				choice = 1;
			}
		}
	}
	public static void clears() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<2;j++)
			jp[i][j].setVisible(false);
		}
		
	}
	public static void rightcp(int i,int j) {
		score = score +  j;
		jl1.setText("score="+score);
		jp[i][0].setVisible(false);
		jp[i+1][0].setVisible(true);
		jp[i][1].setVisible(false);
		jp[i+1][1].setVisible(true);
	}
	public static void falsecp (int i) {
		jp[i][0].setVisible(false);
		jp[i+1][0].setVisible(true);
		jp[i][1].setVisible(false);
		jp[i+1][1].setVisible(true);
	}
	public static void main(String[] args) {
		MultipleChoiceGui q1 = new MultipleChoiceGui();
		q1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
}