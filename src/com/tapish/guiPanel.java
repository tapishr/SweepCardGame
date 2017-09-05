
package com.tapish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**this class creates the central panel in which the game is played.
 *
 * 
 */
class guiPanel extends JPanel implements Runnable{
	Main_Deck md;
	player_deck pl1,pl2;
        compPlay cp;
	cardArraylist cal;
	Image img;
	ImageIcon iic;
        Thread t;
        boolean gameOn=true,playerturn=true;
        guiClass gc;
        String result,addres="Images/";
	int startX=100,startY=600,width,height,mouseX,mouseY,points1,points2,sweep1=0,sweep2=0;
	ArrayList<playing_card> mouse,eaten;

	
	guiPanel(guiClass gc){
            super();
                cp=new compPlay();
		mouse=new ArrayList<>();
                eaten=new ArrayList<>();
                this.gc=gc;
		 pl1=gc.pl1;
                 pl2=gc.pl2;
                 cal=gc.cal;
                 img=gc.img;
			width=img.getWidth(this);
			height=img.getHeight(this);
    	addMouseListener(new MouseActions());
    	
    	addMouseMotionListener(new MouseMotionAdapter(){
            @Override
    		public void mouseMoved(MouseEvent me){              //displays picked cards
    		mouseX=me.getX();
    		mouseY=me.getY();	
                if(!mouse.isEmpty()){
    				
                                //System.out.println("inside mousemoved");
    				repaint();
    			}
    		}
    	});
    	//setBackground( Color.green );
     	//setForeground( Color.green );
	}
        int createdialog(Object[] options){
             
             return JOptionPane.showOptionDialog(this, null, null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        }
    @Override
        public void addNotify(){
            super.addNotify();
            t=new Thread(this);
            t.start();
        }
    
    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            if(pl1.size()==0&&pl2.size()==0){
                if(gc.md.size()==0) {
                    cleanup();
                    gameOn=false;
                }
                else gc.redistribute();
            }
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 50 - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
    }
    @Override
	public void paint(Graphics g){
         super.paint(g); 
         if(gameOn){
        setBackground( new Color(0,120,0) );
        //test(g);
		drawCompcards(g); //displays computer cards
		drawArena(g);       //displays arena cards
		drawplcards(g);     //displays player cards
                mouseDraw(g);       //displays cards picked by mouse
         }
         else{
             draweaten(g);          //displays eaten cards
             gameOver();            //calculates points
             g.drawString(result+" Your points = "+points1+" Computer points = "+points2, 500, 500 );
             
         }
	//	Toolkit.getDefaultToolkit().sync();
	
	//	if(mouse.size()!=0) mouseDraw(g);
	}
        /*void test(Graphics g){
            g.drawImage(img,50,50,this);
        }*/
        void drawCompcards(Graphics g){
		short x=100,y=100;
                for(int b=0;b<pl2.size();b++){
			
			if(pl2.visibility(b)){//System.out.println(b);
			String name="back.png";
			iic=new ImageIcon(addres+name);
			img=iic.getImage();
			g.drawImage(img,x,y,this);
			}
			x+=img.getWidth(this)+10;
		}
                g.setColor(Color.yellow);
                g.drawString("Points="+points2, x, y);
	}
	void mouseDraw(Graphics g){
		int x=(mouseX+15),y=mouseY;
		for(byte b=0;b<mouse.size();b++){
			playing_card pc=mouse.get(b);
			String name=(pc.getColor())+"_"+(pc.getNum())+".png";
			iic=new ImageIcon(addres+name);
			img=iic.getImage();
			g.drawImage(img,x,y,this);
			y+=18;
		}
	}
	void drawplcards(Graphics g){
		int x=startX,y=startY;
		for(int b=0;b<pl1.size();b++){
			
			if(pl1.visibility(b)){//System.out.println(b);
			String name=(pl1.seeCard(b).getColor())+"_"+(pl1.seeCard(b).getNum())+".png";
			iic=new ImageIcon(addres+name);
			img=iic.getImage();
			g.drawImage(img,x,y,this);
			}
			x+=img.getWidth(this)+10;
		}
                g.setColor(Color.yellow);
                g.drawString("Points="+points1, x, y);
	}
	
	void drawArena(Graphics g){
		short x=400,y=300;
		for(byte b=0;b<cal.size();b++){
		for(byte c=0;c<cal.sizeOfGrp(b);c++){	
                    String name=(cal.getCard(b,c).getColor())+"_"+(cal.getCard(b,c).getNum())+".png";
			iic=new ImageIcon(addres+name);
			img=iic.getImage();
			g.drawImage(img,x,y,this);
			y+=18;
                }
                x+=img.getWidth(this)+10;
                y=300;
		}
	}
        void draweaten(Graphics g){
            int x=100,y=100;
            g.setColor(Color.yellow);
            for(byte b=0;b<cp.eaten.size();b++){
                String name=(cp.eaten.get(b).getColor())+"_"+(cp.eaten.get(b).getNum())+".png";
			iic=new ImageIcon(addres+name);
			img=iic.getImage();
                g.drawImage(img, x, y, this);
                x+=width+10;
                if(x+width>this.getWidth()){
                    x=100;
                    y+=height+10;
                }
                
            }
            x=100; y=510;
            for(byte b=0;b<eaten.size();b++){
                
                String name=(eaten.get(b).getColor())+"_"+(eaten.get(b).getNum())+".png";
			iic=new ImageIcon(addres+name);
			img=iic.getImage();
                g.drawImage(img, x, y, this);
                x+=width+10;
                if(x+width>this.getWidth()){
                    x=100;
                    y+=height+10;
                }
            }
        }
        void cleanup(){
            while(cal.size()!=0){
                cp.eaten.addAll(cal.removeGrp(0));
            }
        }
        void repair(){
            for(byte b=0;b<cal.size();b++){
                if(cal.sizeOfGrp(b)>1){
                    int sum=0;
                    for(byte c=0;c<cal.sizeOfGrp(b);c++){
                        sum+=cal.getCard(b, c).getNum();
                    }
                    if(sum<=13) {//System.out.println("nascent for "+b);
                        cal.setType(b, cardArrayBox.nascent);
                    }
                    else{//System.out.println("stack for "+b);
                        cal.setType(b, cardArrayBox.stack);
                    }
                    for(byte c=13;c>8;c--){
                        if(sum==36||sum==60){
                            break;
                        }
                        
                        if(sum%c==0){
                            cal.setID(b, c);//System.out.println("StackID of "+b+"="+c);
                        }
                    }
                    
                }
            }
            
        }
        void changeTurn(){                                  //enables change of turn
           // System.out.println("inside chengeturn");
            playerturn=false;
            repair();
            if(cal.size()==0){
                sweep2++;
            }
            cp.play(pl2, cal);
            repair();
            if(cal.size()==0){
                sweep1++;
            }
            playerturn=true;
            gameOver();
        }
        void gameOver(){
            int sum1=0,sum2=0;
            for(byte b=0;b<eaten.size();b++){
                if(eaten.get(b).getColor()==4) sum1+=eaten.get(b).getNum();
                else{
                    if(eaten.get(b).getNum()==1) sum1+=eaten.get(b).getNum();
                    if(eaten.get(b).getNum()==10&&eaten.get(b).getColor()==3)sum1+=eaten.get(b).getNum();
                }
            }
            int s1=sweep1;
            while(s1!=0){
                sum1-=50;
                s1--;
            }
            for(byte b=0;b<cp.eaten.size();b++){
                if(cp.eaten.get(b).getColor()==4) sum2+=cp.eaten.get(b).getNum();
                else{
                    if(cp.eaten.get(b).getNum()==1) sum1+=cp.eaten.get(b).getNum();
                    if(cp.eaten.get(b).getNum()==10&&cp.eaten.get(b).getColor()==3)sum1+=cp.eaten.get(b).getNum();
                }
            }
            int s2=sweep2;
            while(s2!=0){
                sum2-=50;
                s2--;
            }
            points1=sum1;
            points2=sum2;
            if(sum1>sum2)result="You Win";
            else result="You Lose";
        }
class MouseActions extends MouseAdapter{
    @Override
    	public void mouseClicked(MouseEvent me){
    		
                if (playerturn) {
            int mx = me.getX();
            int my = me.getY();
            if(cal.size()==0){
                if (my > startY && my < (startY + height) && mx > startX) {
                    byte b;                    
                    for (b = 1; b <= 12; b++) {
                        if (mx < (startX + (width * b)) + (10 * (b - 1))) {
                            // System.out.println(pl1.size()+" "+b);
                            cal.add(pl1.removeCard(b - 1));
                            
                            // pl1.setVisibilityFalse(b-1);
                            
                           break;
                        }
                    }changeTurn();
                }
            }else{
            if (mouse.isEmpty()) {
                if (my > startY && my < (startY + height) && mx > startX) {
                    byte b;                    
                    for (b = 1; b <= 12; b++) {
                        if (mx < (startX + (width * b)) + (10 * (b - 1))) {
                            // System.out.println(pl1.size()+" "+b);
                            mouse.add(pl1.removeCard(b - 1));
                            // pl1.setVisibilityFalse(b-1);
                            
                            break;
                        }
                    }
                }
            }
            else {
                if (mouse.size()==1) {
                    if (my > startY && my < (startY + height) && mx > startX) {
                        for (byte b = 1; b <= 12; b++) {
                            if (mx < (startX + (width * b)) + (10 * (b - 1))) {
                                // System.out.println(pl1.size()+" "+b);
                                mouse.add(pl1.removeCard(b - 1));
                                pl1.addCard(mouse.remove(0), b - 1);
                                // pl1.setVisibilityFalse(b-1);
                                break;
                            }
                        }
                    }
                }
                if (my > 300 && my < (300 + height) && mx > 400) {
                    for (byte b = 1; b <= cal.size(); b++) {
                        //System.out.println("mx=" + mx);
                        if (mx < (400 + (width * b) + 10 * (b - 1))) {
                            //System.out.println("here");
                            if (mouse.size()==1) {
                                Object[] options = {"Eat", "Pick", "make stack", "throw"};
                                doCard(createdialog(options), b);
                                break;
                            } else {
                                Object[] options = {"Eat", "Pick", "make stack"};
                                doCard(createdialog(options), b);
                                break;
                            }
                        }
                    }
                }
            }
            }
        }
					
    	}
    void doCard(int num,int b){
        if(num==0){
            if(mouse.size()==1){
                if(cal.getID(b-1)==mouse.get(0).getNum()){
                    eaten.addAll(cal.removeGrp(b-1));
                    eaten.add(mouse.remove(0));
                }
               else if(cal.getCard(b-1).getNum()==mouse.get(0).getNum()){
                    eaten.add(cal.removeCard(b-1));
                    eaten.add(mouse.remove(0));
                }
                else {num=3;
                   JOptionPane.showMessageDialog(null, "Illegal Action");
               }
            }
            else{
                if (cal.sizeOfGrp(b-1)==1) {
                    int sum = cal.getCard(b - 1).getNum();
                    for (byte c = 1; c < mouse.size(); c++) {
                        sum += mouse.get(c).getNum();
                    }
                    //System.out.println("sum=" + sum);
                    if (sum % mouse.get(0).getNum() == 0) {
                        eaten.add(cal.removeCard(b - 1));
                        eaten.addAll(mouse);
                        mouse.removeAll(mouse);
                        
                    } else {num=3;
                        JOptionPane.showMessageDialog(null, "Illegal Action");
                    }
                } 
                else {
                    if(cal.getType(b-1)!=cardArrayBox.single){
                        int sum=cal.getID(b-1);
                        for(byte c=1;c<mouse.size();c++){
                            sum+=mouse.get(c).getNum();
                        }
                        if(sum%mouse.get(0).getNum()==0){
                            eaten.addAll(cal.removeGrp(b-1));
                            eaten.addAll(mouse);
                            mouse.removeAll(mouse);
                        }
                        else {num=3;
                            JOptionPane.showMessageDialog(null, "Illegal Action");
                        }
                    }
                    else {num=3;
                        JOptionPane.showMessageDialog(null, "Illegal Action");
                    }
 
                }
            }
            
        }
        if(num==1){
            mouse.addAll(cal.removeGrp(b-1));
        }
        if(num==2){
            if(cal.sizeOfGrp(b-1)==1){
            int sum=cal.getCard(b-1).getNum();
                for(byte c=0;c<mouse.size();c++){
                    sum+=mouse.get(c).getNum();
                }
                boolean check=false; 
            for(byte d=9;d<=13;d++){
                if(sum==36||sum==60)break;
                if(sum%d==0){
                    for(byte e=0;e<pl1.size();e++){
                        if(d==pl1.seeCard(e).getNum()) {check=true;
                         while(!mouse.isEmpty()){
                         cal.add(mouse.remove(0),b-1);
                         }
                        cal.setID(b-1,d);
                        if(sum==d) cal.setType(b-1,cardArrayBox.nascent);
                        else cal.setType(b-1,cardArrayBox.stack);
                        }
                    }
                if(!check) {num=3; check=true;
                    JOptionPane.showMessageDialog(null, "Illegal Action\nYou do not have "+d+" with you");
                }
                }
            }if(!check) {num=3;
                JOptionPane.showMessageDialog(null, "Illegal Stack");
            } //System.out.println("StackID="+cal.getID(b-1));   
        }
            else{
                if(mouse.size()==1){//System.out.println("mouse="+mouse.get(0).getNum()+" "+cal.getID(b-1));
                    boolean check=false;
                    if(mouse.get(0).getNum()==cal.getID(b-1)){
                        for(byte c=0;c<pl1.size();c++){
                            if(pl1.seeCard(c).getNum()==cal.getID(b-1)) {check=true;
                            cal.add(mouse.remove(0),b-1);
                            break;
                            }
                        }if(!check) {num=3;
                            JOptionPane.showMessageDialog(null, "Illegal Move");
                        }
                        
                    }
                    else if(cal.getType(b-1)==cardArrayBox.nascent){
                        for(byte c=0;c<pl1.size();c++){
                            if(pl1.seeCard(c).getNum()==(cal.getID(b-1)+mouse.get(0).getNum())) {check=true;
                            cal.setID(b-1, (cal.getID(b-1)+mouse.get(0).getNum()));
                            cal.add(mouse.remove(0),b-1);
                            break;
                            }
                        }if(!check) {num=4;
                            JOptionPane.showMessageDialog(null, "Illegal Move");
                        }
                    }
                    
                    else {num=3;
                        JOptionPane.showMessageDialog(null, "Illegal Action");
                    }
                }
                else {                    
                    int sum = 0;
                    for (byte c = 0; c < mouse.size(); c++) {
                        sum += mouse.get(c).getNum();
                    }
                    if (sum % cal.getID(b - 1) == 0) {
                        while (!mouse.isEmpty()) {
                            cal.add(mouse.remove(0), b - 1);
                        }
                    } else {num=3;
                        JOptionPane.showMessageDialog(null, "Illegal Move");
                    }
                }
            }
            
        }
        if(num==3){
            for(byte c=0;c<mouse.size();c++){
                cal.add(mouse.remove(0));
            }
            
            
        }if(num!=1)
        changeTurn();
    }
}
	
	
}

