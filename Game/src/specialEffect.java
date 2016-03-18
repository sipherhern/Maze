import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;


public class specialEffect {
	 Boss boss;
	 private static int state ;            //  the state of hero
     private static int index;             //  the index of images
     private final static int totalIndex = 21;
     Image bossDie ;
	 private Image image[] = new Image[totalIndex];
	 private Image image2[] = new Image[totalIndex];
	 private Image image3[] = new Image[totalIndex];
	 private Image image4[] = new Image[totalIndex];
	 private Image hero[] = new Image[totalIndex];
	 Image background = Toolkit.getDefaultToolkit().getImage("images\\background.png");
	 
	 
	 specialEffect(){     //constructor
		 boss = new Boss();
		 init();
	 }
	 
	 // load the images
	 public void init(){
		 state = 0;
		 index = 0;
		 for(int i = 0; i < totalIndex; i++){
			 hero[i]=Toolkit.getDefaultToolkit().getImage("images\\hero\\attack_"+(i+1)+".png");
			 image[i]=Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\effectOne\\"+(i+1)+".png");		 
			 image2[i]=Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\effectTwo\\"+(i+1)+".png");			
			 image3[i]=Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\effectThree\\"+(i+1)+".png");
			 image4[i]=Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\effectFour\\"+(i+1)+".png");
			 }
	     bossDie = Toolkit.getDefaultToolkit().getImage("images\\boss\\boss_die.png");
	 }
	  
	 // draw the special effect of every skill 
	 public void Draw(Graphics g,JPanel i){
		 g.drawImage(background,0,0,1280,680,(ImageObserver)i);
		 if(getState() == 0){
	     g.drawImage(hero[0], 200, 150,(ImageObserver)i);
	     if(boss.getHealth()>0)
	    	 g.drawImage(boss.bossImage[0], 900, 150,(ImageObserver)i);
	     else
	    	 g.drawImage(bossDie, 900, 250,(ImageObserver)i);
	    	 
		 }
	 }
	 
	 public void DrawEfOne(Graphics g,JPanel i,int ind){
		 if(ind < totalIndex){
			 g.drawImage(hero[ind], 200, 150,(ImageObserver)i);
			 g.drawImage(image[ind], 500, 150,(ImageObserver)i); 
			 System.out.println(index);
			
		 }       
		 if(ind == totalIndex){ 
			 g.drawImage(hero[0], 200, 150,(ImageObserver)i);
			 setState(0);
        	 setIndex(0);
        	 boss.setState(1);	
        	
		 }
	 }
	 
	 public void DrawEfTwo(Graphics g,JPanel i,int ind){
		 if(ind < totalIndex){
			g.drawImage(hero[ind], 200, 150,(ImageObserver)i);
         	g.drawImage(image2[ind],800,150,(ImageObserver)i);
         	g.drawImage(image4[ind],-10,-20,(ImageObserver)i);
		 }
		 if(ind == totalIndex){
	        	 g.drawImage(hero[0], 200, 150,(ImageObserver)i);
	        	 setState(0);
	        	 setIndex(0);
	         }
	 }
	 public void DrawEfThree(Graphics g,JPanel i,int ind){
		 if(ind < totalIndex){
			g.drawImage(hero[ind], 200, 150,(ImageObserver)i);
         	g.drawImage(image3[ind],600,-50,(ImageObserver)i);
         	g.drawImage(image3[ind],850,-50,(ImageObserver)i);
         	g.drawImage(image3[ind],730,50,(ImageObserver)i);
         	g.drawImage(image3[ind],730,-150,(ImageObserver)i);
		 }
		 if(ind == totalIndex){
	        	 g.drawImage(hero[0], 200, 150,(ImageObserver)i);
	        	 setState(0);
	        	 setIndex(0);
	         }
	 }
	 
	 public void DrawEfFour(Graphics g,JPanel i,int ind){
		 if(ind < totalIndex){
			g.drawImage(hero[0], 200, 150,(ImageObserver)i);
         	//g.drawImage(image3[ind],600,100,(ImageObserver)i);
		 }
		 if(ind == totalIndex){
	        	 g.drawImage(hero[0], 200, 150,(ImageObserver)i);
	        	 setState(0);
	        	 setIndex(0);
	         }
	 }
	 
	 public void setState(int s){
		state = s;
		}
	 public int getState(){
		return state;
	    }
	 public void setIndex(int i){
		index = i;
	    }
	 public int getIndex(){
		return index;
	    }
}

