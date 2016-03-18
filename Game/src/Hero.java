import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JPanel;


public class Hero {
	Boss boss;
	Random random = new Random(100);       
	private static String name;
	private static int health;
	public int index;
	private int ATTACK = (int)Math.random()*100+50;
	private final int TOTALHEALTH = 20000;
	
	Image win = Toolkit.getDefaultToolkit().getImage("images\\win.png");
	Image lose = Toolkit.getDefaultToolkit().getImage("images\\lose.png");
	
	specialEffect sp = new specialEffect();
	//constrcutor
	Hero(String name){ 
		this.name = name;
		health = TOTALHEALTH;
		index = 0;
		boss = new Boss();		
	}

	public void setName(String n){      //set the name of the hero
		name = n;
	}
	public void setHealth(int h){       //set the value of the health
		health = h;
	}
	public String getName(){            //get the name of the hero
		return name;
	}
	public int getHealth(){             //get the value of the health
		return health; 
	}
	public int getAttack(){             //get the value of the attack
		return ATTACK;
	}
	public void setIndex(int x){        //set the value of the index
		index = x;
	}
	public int getIndex(){               //get the value of the index
		return index;
	} 
	public int getTotalHealth(){          //get the value of the health
		return TOTALHEALTH;
	}
	//check which side wins the game
	public boolean isWin(int heroHeal,int bossHeal){              
		if(heroHeal > 0 && bossHeal <= 0)
			return true;
		else 
			return false;
	}
    //check whether the game is over
	public void checkOver(Graphics g,JPanel i){
		 if(isWin(this.getHealth(),boss.getHealth())){
	    	   g.drawImage(win,500,200,i);
	       }
	     if(this.getHealth() < 0 || this.getHealth() == 0){
	    	 setHealth(0);
	    	 g.drawImage(lose,500,200,i);
	     }
	      //new Music("¹¥»÷.au");
	}
	
	// set the kind of the attack
	public void attack(){
		 if(sp.getState() == 1){
			 boss.setHealth(boss.getHealth() - this.getAttack());
		    
		     }
		 if(sp.getState() == 2){
			 boss.setHealth(boss.getHealth() - this.getAttack());
			
			 }
		 if(sp.getState() == 3){
			 boss.setHealth(boss.getHealth() - this.getAttack());
			 
			 }
	     }	 
	
	

	
}
