import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class BattlePanel extends JPanel implements ActionListener,Runnable{
	Hero hero;
	Boss boss;

	private final static int timeinterval = 100;     // the time interval to refresh the panel
	private Thread hThread = null;                   
	private int WIDTH = 1280;                        // the width of the frame
	private int HEIGHT = 680;                        // the height of the frame
	  	
	final JButton skillOne = new JButton("s1");      // the buttons to show the skill
	final JButton skillTwo = new JButton("s2");
	final JButton skillThree = new JButton("s3");  
	final JButton endRound = new JButton("end");
	
	specialEffect sp = new specialEffect();         
	damageShow ds = new damageShow();

 
    public BattlePanel(){
    	super();
    	setPreferredSize(new Dimension(WIDTH,HEIGHT));
    	setFocusable(true);
    	setOpaque(false);
		init();
		setVisible(true); 	
		this.setLayout(null);
    	skillOne.addActionListener(this);
    	skillTwo.addActionListener(this);
    	skillThree.addActionListener(this);
    	endRound.addActionListener(this);
    	this.add(skillOne);
    	this.add(skillTwo);
    	this.add(skillThree);
    	this.add(endRound);
    	hThread = new Thread(this);
		hThread.start();
    	
    	}
    
    private void init(){
    	hero = new Hero("1");
    	boss = new Boss();

    	//skillOne Button
    	 skillOne.setRolloverIcon(new ImageIcon("images\\icon1.png"));
         skillOne.setBorderPainted(false);
         skillOne.setContentAreaFilled(false);
         skillOne.setIcon(new ImageIcon("images\\icon1.png"));
         skillOne.setBounds(988,511,80,55);
     	
     	//skillTwo button    
     	skillTwo.setRolloverIcon(new ImageIcon("images\\icon2.png"));
     	skillTwo.setBorderPainted(false);
     	skillTwo.setContentAreaFilled(false);
     	skillTwo.setIcon(new ImageIcon("images\\icon2.png"));
     	skillTwo.setBounds(1060,511,80,55);
     
     	//skillThree button
     	skillThree.setRolloverIcon(new ImageIcon("images\\icon3.png"));
     	skillThree.setBorderPainted(false);
     	skillThree.setContentAreaFilled(false);
     	skillThree.setIcon(new ImageIcon("images\\icon3.png"));
     	skillThree.setBounds(1132,510,80,56);
     	
     	endRound.setRolloverIcon(new ImageIcon("images\\skipButton.png"));
     	endRound.setBorderPainted(false);
     	endRound.setContentAreaFilled(false);
     	endRound.setIcon(new ImageIcon("images\\skipButton.png"));
     	endRound.setBounds(1204,510,80,56);
     	
    }
    
    public void actionPerformed(ActionEvent e) {                  //the events of the button
        if(e.getActionCommand().equals("s1")){
     	   sp.setState(1);
     	   sp.setIndex(0);	
     	   boss.setState(0);
     	   boss.setIndex(0);
        }
        if(e.getActionCommand().equals("s2")){
     	   sp.setState(2);
     	   sp.setIndex(0);
     	   boss.setState(0);
     	   boss.setIndex(0);
     	   }
        if(e.getActionCommand().equals("s3")){
      	   sp.setState(3);
      	   sp.setIndex(0);
      	   boss.setState(0);
    	   boss.setIndex(0);
      	   
      	   }
        if(e.getActionCommand().equals("end")){
           sp.setState(4);
           sp.setIndex(0);
          if(boss.getTotalHealth()*0.8 < boss.getHealth() && boss.getHealth() < boss.getTotalHealth())
        	  boss.setState(1);
           else if(boss.getTotalHealth()*0.5 < boss.getHealth() && boss.getHealth() < boss.getTotalHealth()*0.8)
        	   boss.setState(2);
           else if(boss.getTotalHealth()*0.2 < boss.getHealth() && boss.getHealth() < boss.getTotalHealth()*0.5)
        	   boss.setState(3);
           else{
        	   boss.setState(4);
           }
           boss.setIndex(0);
       	   }
        }
    
    protected void Draw(){       
    	update();
    	repaint();
    }
    
    public void paint(Graphics g) {       //draw the components 
        super.paint(g); 
        sp.Draw(g, this);
        if(boss.getState() == 0){
     	   boss.DrawBossImage(g,this);
         }
        if(boss.getState() == 1){
    	   boss.DrawBoss(g,this);
    	   boss.damage(hero);
    	   ds.calDamage(boss.getDamage());
    	   ds.DrawDamage(g, this,ds.getDigitOne(),ds.getDigitTwo(),ds.getDigitThree(),ds.getDigitFour());
        }
        if(boss.getState() == 2){
     	   boss.DrawBoss(g,this);
     	   boss.damage(hero);
     	   ds.calDamage(boss.getDamage());
   	       ds.DrawDamage(g, this,ds.getDigitOne(),ds.getDigitTwo(),ds.getDigitThree(),ds.getDigitFour());
         }
        if(boss.getState() == 3){
     	   boss.DrawBoss(g,this);
     	   boss.damage(hero);
     	   ds.calDamage(boss.getDamage());
   	       ds.DrawDamage(g, this,ds.getDigitOne(),ds.getDigitTwo(),ds.getDigitThree(),ds.getDigitFour());
         }
        if(boss.getState() == 4){
      	   boss.DrawBoss(g,this);
      	   boss.damage(hero);
      	   ds.calDamage(boss.getDamage());
    	       ds.DrawDamage(g, this,ds.getDigitOne(),ds.getDigitTwo(),ds.getDigitThree(),ds.getDigitFour());
          }
        if(sp.getState() == 1){
        	sp.DrawEfOne(g, this,sp.getIndex());
        	hero.attack();
        	ds.calDamage(hero.getAttack());
      	    ds.DrawDamage(g, this,ds.getDigitOne(),ds.getDigitTwo(),ds.getDigitThree(),ds.getDigitFour());
        }
        if(sp.getState() == 2){
        	sp.DrawEfTwo(g, this,sp.getIndex());
        	hero.attack();
        	ds.calDamage(hero.getAttack());
      	    ds.DrawDamage(g, this,ds.getDigitOne(),  ds.getDigitTwo(),ds.getDigitThree(),ds.getDigitFour());
        }
        if(sp.getState() == 3){
        	sp.DrawEfThree(g, this,sp.getIndex());
        	hero.attack();
        	ds.calDamage(hero.getAttack());
      	    ds.DrawDamage(g, this,ds.getDigitOne(),ds.getDigitTwo(),ds.getDigitThree(),ds.getDigitFour());
        }
        if(sp.getState() == 4){
        	sp.DrawEfFour(g, this,sp.getIndex());
        }
        if(sp.getState() == 0){
        	sp.Draw(g,this);
        }
       g.setColor(Color.white); 
       g.setFont(new Font("黑体",Font.BOLD,15)); 
	   g.drawString("生命值:" + hero.getHealth() + "\\" + hero.getTotalHealth(),550,580);
	   g.drawString("防御:" + "86",550,600);
	   g.drawString("智力:" + "68",550,620);
	   g.drawString("命中:" + "85",550,640);
	   hero.checkOver(g, this);
    }  
    public void update(){                             //update the index 
    	sp.setIndex(sp.getIndex()+1);
    }
     
    public void run(){                                
    	while(true){
    		Draw();  
    		try{
    			Thread.sleep(timeinterval);
    		} catch (InterruptedException e){
    			e.printStackTrace();
    		}
    	
         			        		   	
    	}	
	}
} 


