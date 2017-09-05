
package com.tapish;

import java.util.ArrayList;

/** this class contains methods and variables to deal with the deck of a player.
 *
 *
 */
public class player_deck {
    private ArrayList<playing_card> pc;                         //all the cards are stored in the arraylist
	private boolean[] visible=new boolean[12]; 
	player_deck(){
		pc=new ArrayList();
                for(byte b=0;b<12;b++){
                    visible[b]=true;
                }
	}
	
	playing_card removeCard(int index){                 //to remove a card
		return pc.remove(index);
	}
	playing_card seeCard(int index){                    //to see a card
		return pc.get(index);
	}
	void setVisibilityFalse(int index){             // to set the visibility as false
		visible[index]=false;
	}
	boolean visibility(int index){                  // to get the value of visibility of a card
		return visible[index];
	}
	void addCard(playing_card card,int index){      //to add a card to the player deck
		pc.add(index,card);
	}
        int size(){                                     //to get the size of the player deck
            return pc.size();
        }

    void addFromDeck(int num, Main_Deck md) {           //to add from main deck to player deck
        ArrayList<playing_card>p_c=md.getCards(num);
		for(int i=0;i<num;i++){
			pc.add(p_c.remove(p_c.size()-1));
		}
    }
}
