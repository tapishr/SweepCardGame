
package com.tapish;

/**this class represents the methods and variables used for the playing card in the game.
 *the card has basically 2 properties, the color(spades,diamonds,clubs,hearts) of the card and the number(1 to 13) of the card.
 * 
 */
public class playing_card {             
	private int colour,num; 
	 
	playing_card(int colour, int num){
		this.colour=colour;
		this.num=num;
	}
	int getColor(){
		return colour;
	}
	int getNum(){
		return num;
	}
}
