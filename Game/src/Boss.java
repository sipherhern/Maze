import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class Boss {
	private int index;                // the image index
	private int state;                // the state decide the skill of the boss
	private static int health;        // the strength of the monsters
	private int damage = (int)Math.random()*90+50;  //the damage caused by the boss
	private final int TOTALHEALTH = 20000;          //the health of the boss
	private final int totalIndex = 14;              
	Image bossDie ;                                  //the various images of the boss 
	Image bossImage[] = new Image[totalIndex];        
	Image trickOne[] = new Image[totalIndex];
    Image trickTwo[] = new Image[totalIndex];
	Image trickThree[] = new Image[totalIndex];
	Image trickFour[] = new Image[totalIndex];
    Image trickFive[] = new Image[totalIndex];
    Image trickSix[] = new Image[totalIndex];
	Image trickSeven[] = new Image[totalIndex];
		
	public Boss(){          //constructor
		index = 0;
		state = 0;
		health = TOTALHEALTH;
		loadImage();
	}
	// load the Image of the boss
	public void loadImage(){                            
		for(int i = 0; i < totalIndex; i++){
			bossImage[i] = Toolkit.getDefaultToolkit().getImage("images\\boss\\boss_"+ (i+1) + ".png");
			trickOne[i] = Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\b_specialeffect\\effectOne\\"+ (i+1) + ".png");
			trickTwo[i] = Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\b_specialeffect\\effectTwo\\"+ (i+1) + ".png");
			trickThree[i] = Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\b_specialeffect\\effectThree\\"+ (i+1) + ".png");
			trickFour[i] = Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\b_specialeffect\\effectFour\\"+ (i+1) + ".png");
			trickFive[i] = Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\b_specialeffect\\effectFive\\"+ (i+1) + ".png");
		    trickSix[i] = Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\b_specialeffect\\effectSix\\"+ (i+1) + ".png");
			trickSeven[i] = Toolkit.getDefaultToolkit().getImage("images\\specialEffect\\b_specialeffect\\effectSeven\\"+ (i+1) + ".png");
			bossDie = Toolkit.getDefaultToolkit().getImage("images\\boss\\boss_die.png");
		}
	}
	
	public void setDamage(int d){        // set the value of the damage
		damage = d;
	}
	public static void setHealth(int h){     // set the current value of the health
		health = h;
	}
	public void setState(int s){         // set the value of the state
		this.state = s;
	}
    public int getState(){               // get the value of the state
		return state;
    }
    public void setIndex(int i){        // set the value of the index
		index = i;
	}
    public int getIndex(){              // get the value of the index
		return index;
	}
	public int getHealth(){              // get the current value of the state
		return health; 
	}
	public int getDamage(){              // get the value of the damage
		return damage;
	}
	public int getTotalHealth(){          // get the value of the totalhealth
		return TOTALHEALTH;
	}
	
	 public void DrawBossImage(Graphics g,JPanel i){         //draw the boss
		 if(getHealth() > 0) 
			 g.drawImage(bossImage[0], 900, 150,(ImageObserver)i);
		 else{
			 g.drawImage(bossDie, 900, 250,(ImageObserver)i);
		 }
	 }
	   
	public void DrawBoss(Graphics g,JPanel i){           //draw the skill 
		if(getState() == 1 && getIndex() < 14){
			 g.drawImage(bossImage[getIndex()], 900, 150,(ImageObserver)i);
			 g.drawImage(trickOne[getIndex()], 250, 200,(ImageObserver)i); 
			 g.drawImage(trickFive[getIndex()], 250, 200,(ImageObserver)i);
			 setIndex(getIndex()+1);
			 return ;
			}
		if(getState() == 2 && getIndex() < 14){
			 g.drawImage(bossImage[getIndex()], 900, 150,(ImageObserver)i);
			 g.drawImage(trickTwo[getIndex()], 250, 200,(ImageObserver)i); 
			 g.drawImage(trickSix[getIndex()], 250, 200,(ImageObserver)i);
			 setIndex(getIndex()+1);
			 return ;
			}
		if(getState() == 3 && getIndex() < 14){
			 g.drawImage(bossImage[getIndex()], 900, 150,(ImageObserver)i);
			 g.drawImage(trickThree[getIndex()],250, 200,(ImageObserver)i);
			 g.drawImage(trickSeven[getIndex()], 250, 200,(ImageObserver)i);
			 setIndex(getIndex()+1);
			 return ;
			}
		if(getState() == 4 && getIndex() < 14){
			 g.drawImage(bossImage[getIndex()], 900, 150,(ImageObserver)i);
			 g.drawImage(trickFour[getIndex()], 250, 200,(ImageObserver)i); 
			 setIndex(getIndex()+1);
			 return;
			}
		else if(getHealth()>0){
			 g.drawImage(bossImage[0], 900, 150,(ImageObserver)i);
			 setIndex(0);
			 setState(0);
		}
	}
	
	public void damage(Hero hero){                   //set the value of the damage while using differernt skill
		 if(this.getState() == 1){
			 hero.setHealth(hero.getHealth() - this.getDamage());
		     }
		 if(this.getState() == 2){
			 hero.setHealth(hero.getHealth() - this.getDamage()*2);
			 }
		 if(this.getState() == 3){
			 hero.setHealth(hero.getHealth() - this.getDamage()*3);    
			 }
	     }	 
}
	

