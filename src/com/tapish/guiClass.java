
package com.tapish;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**this class creates the gui for the game.
 *
 * 
 */
public class guiClass extends JFrame implements ActionListener {
    Main_Deck md;                           //the main deck
	player_deck pl1,pl2;                //the player decks
	cardArraylist cal;                  //the arena deck
	Image img;                          
	ImageIcon iic;
        JMenuBar jmb;
        JMenu jm;
        JMenuItem jmi;
    	guiClass(){
            jmi=new JMenuItem("New Game");
                jmi.addActionListener(this);
                jm=new JMenu("Game");
                jm.add(jmi);
                jmb=new JMenuBar();
                jmb.add(jm);
                jmb.setVisible(true);
                
            inita();
       
                this.setJMenuBar(jmb);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300,750);
		setTitle("SWEEP");
		setVisible(true);
		
	}
/*	public static void main (String[] args) {
		new guiClass();
}*/
    @Override
 public void actionPerformed(ActionEvent e){
     inita();
 }
    @Override
 public Insets getInsets() {
         // Specify how much space to leave between the edges of
         // the applet and the components it contains.  The background
         // color shows through in this border.
      return new Insets(3,3,3,3);
}

    void redistribute(){
            pl1.addFromDeck(12,md);
            pl2.addFromDeck(12,md);
        }
    private void inita(){
        boolean check=true;
        pl1=new player_deck();
    	md=new Main_Deck();
        pl2=new player_deck();
        cal=new cardArraylist();
        iic=new ImageIcon("Images/back.png");
	img=iic.getImage();
    	   do {            
            pl1.addFromDeck(12, md);
            
            pl2.addFromDeck(12, md);
            
            cal.add(md.getCards(4));
            for(byte b=0;b<pl1.size();b++){                         //to ensure that both players get at least one card above 8
                if(pl1.seeCard(b).getNum()>8){
                    for(byte c=0;c<pl2.size();c++){
                        if(pl2.seeCard(c).getNum()>8){
                            check=false;
                            break;
                        }
                    }if(!check) break;
                }
            }
        }while(check);
        setLayout(new BorderLayout());
	//System.out.println("testing");
        
		
	this.getContentPane().add(new guiPanel(this),BorderLayout.CENTER);
    }
}
