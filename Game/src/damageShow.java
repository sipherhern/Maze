import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;


public class damageShow {
	private int digitOne,digitTwo,digitThree,digitFour;  // four digit
	private Image number[] = new Image[10];   
	//constructor
	public damageShow(){
		loadImage();
	}
	//load the image
	public void loadImage(){
		digitOne = digitTwo = digitThree = digitFour = 0;
		for(int i = 0 ; i < 10;i++)
			number[i] = Toolkit.getDefaultToolkit().getImage("images\\number\\" + i + ".png");
	}
	// set the image of the number
	public void calDamage(int damage){
		int temp = 1000;
		digitOne = damage/1000;
		damage = damage%temp;
		temp = 100;
		digitTwo = damage/100;
		damage = damage%temp;
		temp = 10;
		digitThree = damage/10;
		damage = damage%temp;
		digitFour = damage;
	}
	//get the value of each digit
	public int getDigitOne(){
		return digitOne;
	}
	public int getDigitTwo(){
		return digitTwo;
	}
	public int getDigitThree(){
		return digitThree;
	}
	public int getDigitFour(){
		return digitFour;
	}
	// draw the image of the number
	 public void DrawDamage(Graphics g,JPanel i,int a,int b,int c,int d){
		 g.drawImage(number[a],800,200,100,100,(ImageObserver)i);
		 g.drawImage(number[b],830,200,100,100,(ImageObserver)i);
		 g.drawImage(number[c],860,200,100,100,(ImageObserver)i);
		 g.drawImage(number[d],890,200,100,100,(ImageObserver)i);
	 }
}
