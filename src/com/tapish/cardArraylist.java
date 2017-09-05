
package com.tapish;

import java.util.ArrayList;

/**the cardArrayList uses cardArrayBox, a data type which can connect arraylists
 *and helps in handling the functions of arena
 * 
 */
public class cardArraylist {
    private cardArrayBox cab;
	cardArraylist(){
		cab=new cardArrayBox();
		cab.last=true;
	}
	void add(playing_card card){                        //add a card to arena
		cardArrayBox cab1,cab2;
		cab1=this.cab;
		cab2=new cardArrayBox(card);
		if(this.size()==0){
			cab1.f=cab2; 
			cab2.b=cab1;
			this.cab=cab2;
		}
		else{
		while(!cab1.last){
			cab1=cab1.b;
		}
		cab2.f=cab1.f; cab1.f.b=cab2; 					
		cab2.b=cab1;
		cab1.f=cab2;
		}
		
	}
	void add(ArrayList<playing_card> alt){                  //add an arraylist to arena
		for(int i=0;i<alt.size();i++){
			this.add(alt.get(i));
		}
	}
	void add(playing_card card,int index){              //add a card at a particular position
		cardArrayBox cab1;
		cab1=this.cab;
		int i=0;
		while(i!=index&&!cab1.last){
			cab1=cab1.b; i++;
		}
		cab1.add(card);
	}
        int sizeOfGrp(int index){                           //get the size of a stack
            cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=index&&!cab1.last){
			cab1=cab1.b; i++;//System.out.println(cab1.al.get(0));
		}
                return cab1.al.size();
        }
/*	void add(ArrayList<playing_card> alt,int index){
		cardArrayBox cab1,cab2;
		cab1=this.cab;
		int i=0;
		while(i!=index&&!cab1.last){
			cab1=cab1.b; i++;
		}
		cab1.add(card);
	}*/
	int size(){                                                 //get the size of arena
		cardArrayBox cab1=this.cab;
		int count=0;
		while(!cab1.last){
			cab1=cab1.b;
			count++;
		}
		return count;
	}
	playing_card getCard(int grpindex,int cardindex){           //get a card
		cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=grpindex&&(!cab1.last)){
			cab1=cab1.b; i++;
		}
		return cab1.al.get(cardindex);
	}
	playing_card getCard(int grpindex){
		cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=grpindex&&!cab1.last){
			cab1=cab1.b; i++;//System.out.println(cab1.al.get(0));
		}
		return cab1.al.get(0);
	}
	playing_card removeCard(int grpindex,int cardindex){        //remove a card from a stack
		cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=grpindex&&!cab1.last){
			cab1=cab1.b; i++;
		}
		return cab1.al.remove(cardindex);
	}
	playing_card removeCard(int grpindex){                  //remove a card
		if(grpindex==0){
                    cardArrayBox cab1=this.cab;
                    this.cab=cab1.b;
                    return cab1.al.remove(0);
                }
            cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=grpindex&&!cab1.last){
			cab1=cab1.b; i++;
		}//System.out.println(cab1.al.get(0)+" in"+getClass());
		cab1.f.b=cab1.b; cab1.b.f=cab1.f;
		return cab1.al.remove(0);
	}
        ArrayList<playing_card> removeGrp(int grpindex){        //remove a whole group
           if(grpindex==0){
                    cardArrayBox cab1=this.cab;
                    this.cab=cab1.b;
                    return cab1.al;
                }
            cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=grpindex&&!cab1.last){
			cab1=cab1.b; i++;
		}//System.out.println(cab1.al.get(0)+" in"+getClass());
		cab1.f.b=cab1.b; cab1.b.f=cab1.f;
		return cab1.al;
        }
        int getType(int index){                             //get the type of card/stack
            cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=index&&!cab1.last){
			cab1=cab1.b; i++;
		}
                return cab1.type;
        }
        void setType(int index,int type){               //set the type of card/stack
            cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=index&&!cab1.last){
			cab1=cab1.b; i++;
		}
                cab1.type=type;
        }
        void setID(int grpindex,int stackID){           //set the ID of a stack
            cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=grpindex&&!cab1.last){
			cab1=cab1.b; i++;
		}
                cab1.stackID=stackID;
        }
        int getID(int grpindex){                        //get ID of a stack
            cardArrayBox cab1=this.cab;
		int i=0;
		while(i!=grpindex&&!cab1.last){
			cab1=cab1.b; i++;
		}
                return cab1.stackID;
        }
}
class cardArrayBox {
        static final int single=0;
        static final int nascent=1;
        static final int stack=2;
	ArrayList<playing_card> al;
	boolean last;
	cardArrayBox f,b;
        int type =cardArrayBox.single;
        int stackID=0;
	cardArrayBox(){
		al=new ArrayList<playing_card>();
	}
	cardArrayBox(playing_card card){
		al=new ArrayList<playing_card>();
		al.add(card);
	}
	void add(playing_card card){
		al.add(card);
	}
}