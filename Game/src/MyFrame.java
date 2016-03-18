import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;


public class MyFrame extends JFrame{
	public MyFrame(){
		setTitle("¾ö¶·");
		BattlePanel panel = new BattlePanel();
		Container contentPane = getContentPane();
		contentPane.add(panel);
		
		pack();
		
	}
	
	public static void main(String[] args){
		MyFrame e1 = new MyFrame();
		e1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		e1.setVisible(true);
	}
} 

