
package com.tapish;

import java.util.ArrayList;
import java.util.Random;

/**The main deck is the deck of shuffled 52 cards.
 *
 *
 */
public class Main_Deck {
    	ArrayList<playing_card> pc;                                 //all cards stored in arraylist
	Random r=new Random();
	Main_Deck(){
		pc=new ArrayList();
		this.add();
			
	}
	void add(){                                                 //this method creates a shuffled deck
		for(int i=0;pc.size()<52;i++){
			int col=r.nextInt(4)+1;
			int num=r.nextInt(13)+1;
			boolean match=false;
			for(int j=0;j<pc.size();j++){
				playing_card  p_c=pc.get(j);
				if(p_c.getColor()==col&&p_c.getNum()==num) match=true;
			}
			playing_card  p_c=new playing_card(col,num);
			if(match)continue;
			else  pc.add(p_c);
		}
		
	}
	ArrayList<playing_card> getCards(int num_cards){                    //to get some cards from the main deck in an arraylist
		ArrayList<playing_card> p_c=new ArrayList<playing_card>();
		for(int i=0;i<num_cards;i++){
			p_c.add(pc.remove(pc.size()-1)); //System.out.println(p_c.get(i)+" "+i);
		}
		return p_c;
	}
        int size(){                                                //this returns the size of main deck
            return pc.size();
        }
}
