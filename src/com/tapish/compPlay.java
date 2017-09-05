
package com.tapish;

import java.util.ArrayList;

/**this class determines how the computer will play the game
 *
 * 
 */
public class compPlay {
    player_deck pl;
    cardArraylist Arena;
    ArrayList<playing_card> eaten;
    compPlay(){
        eaten=new ArrayList();
    }
    
    void play(player_deck pl,cardArraylist Arena){
        this.pl=pl;
        this.Arena=Arena;
            
        if(!sweepArena()){boolean chck=true;
            if(pl.size()==1){
                for(byte b=0;b<Arena.size();b++){
                    if(Arena.getType(b)!=cardArrayBox.single){
                    if(pl.seeCard(0).getNum()==Arena.getID(b)){
                        eaten.add(pl.removeCard(0));
                        eaten.addAll(Arena.removeGrp(b));
                        chck=false;
                        break;
                    }
                        }
                    else{
                        if(pl.seeCard(0).getNum()==Arena.getCard(b).getNum()){
                        eaten.add(pl.removeCard(0));
                        eaten.addAll(Arena.removeGrp(b));
                        chck=false;
                        break;
                    }
                    }
                }
            }
            if (chck) {
                switch (Arena.size()) {
                    case (1):
                        //System.out.println("inside case 1");
                        throwUp();
                        break;
                    case (2):
                        //System.out.println("inside case 2");
                        if (Arena.getType(0) == cardArrayBox.single && Arena.getType(1) == cardArrayBox.single) {
                            boolean check = false;
                            if (makeCardToStack(Arena.getCard(0).getNum(), 0)) {
                                check = true;
                                break;
                            } else {
                                if (makeCardToStack(Arena.getCard(1).getNum(), 1)) {
                                    check = true;
                                    break;
                                } else {
                                    throwUp();
                                    break;
                                }
                            }
                        }
                        if (Arena.getType(0) != cardArrayBox.single && Arena.getType(1) != cardArrayBox.single) {
                            if (Arena.getType(0) == cardArrayBox.nascent) {
                                
                                if (makeCardToStack(Arena.getID(0), 0)) {
                                    break;
                                }
                            }
                            if (Arena.getType(1) == cardArrayBox.nascent) {
                                
                                if (makeCardToStack(Arena.getID(1), 1)) {
                                    break;
                                }
                            }
                            
                            int[] r = findRepeats(Arena.getID(0));boolean poss=true;
                            if (r[1] != 100) {
                                for(byte b=0;b<Arena.size();b++){
                                    if(Arena.getID(b)==cardArrayBox.single){
                                        if(Arena.getCard(b).getNum()==pl.seeCard(r[0]).getNum()){
                                            
                                            poss=false;
                                            break;
                                        }
                                    }
                                    else{
                                        if(Arena.getID(b)==pl.seeCard(r[0]).getNum()){
                                            poss=false;
                                            break;
                                        }
                                    }
                                }if(poss){
                                //System.out.println("inside nsingle,repeat,0");
                                Arena.add(pl.removeCard(r[0]));
                                poss=false;
                                }
                            } if(poss) {
                                r = findRepeats(Arena.getID(1));
                               
                                if (r[1] != 100) {
                                for(byte b=0;b<Arena.size();b++){
                                    if(Arena.getID(b)==cardArrayBox.single){
                                        if(Arena.getCard(b).getNum()==pl.seeCard(r[0]).getNum()){
                                            
                                            poss=false;
                                            break;
                                        }
                                    }
                                    else{
                                        if(Arena.getID(b)==pl.seeCard(r[0]).getNum()){
                                            poss=false;
                                            break;
                                        }
                                    }
                                }if(poss){
                                    //System.out.println("inside nsingle,repeat,1");
                                    Arena.add(pl.removeCard(r[0]));
                                    poss=false;
                                }
                                } if(poss) {
                                    //System.out.println("inside nsingle,throwUp");
                                    throwUp();
                                }
                            }
                            
                            break;                            
                        }
                        if (Arena.getType(0) != cardArrayBox.single) {
                            if (Arena.getType(0) == cardArrayBox.stack) {
                                if (makeCardToStack(Arena.getCard(1).getNum(), 1)) {
                                    break;
                                }
                                
                            } else {
                                if (makeCardToStack(Arena.getID(0), 0)) {
                                    break;
                                }
                            }
                            int[] r = findRepeats(Arena.getID(0));boolean poss=true;
                            if (r[1] != 100) {
                                for(byte b=0;b<Arena.size();b++){
                                    if(Arena.getID(b)==cardArrayBox.single){
                                        if(Arena.getCard(b).getNum()==pl.seeCard(r[0]).getNum()){
                                            
                                            poss=false;
                                            break;
                                        }
                                    }
                                    else{
                                        if(Arena.getID(b)==pl.seeCard(r[0]).getNum()){
                                            poss=false;
                                            break;
                                        }
                                    }
                                }if(poss){
                                //System.out.println("inside nsingle,repeat,0");
                                Arena.add(pl.removeCard(r[0]));
                                poss=false;
                                }
                            } if(poss) {
                                r = findRepeats(Arena.getID(1));
                               
                                if (r[1] != 100) {
                                for(byte b=0;b<Arena.size();b++){
                                    if(Arena.getID(b)==cardArrayBox.single){
                                        if(Arena.getCard(b).getNum()==pl.seeCard(r[0]).getNum()){
                                            
                                            poss=false;
                                            break;
                                        }
                                    }
                                    else{
                                        if(Arena.getID(b)==pl.seeCard(r[0]).getNum()){
                                            poss=false;
                                            break;
                                        }
                                    }
                                }if(poss){
                                    //System.out.println("inside nsingle,repeat,1");
                                    Arena.add(pl.removeCard(r[0]));
                                    poss=false;
                                }
                                } if(poss) {
                                   // System.out.println("inside nsingle,throwUp");
                                    throwUp();
                                }
                            }
                            
                            break;                            
                        }
                        if (Arena.getType(1) != cardArrayBox.single) {
                            if (Arena.getType(1) == cardArrayBox.stack) {
                                if (makeCardToStack(Arena.getCard(0).getNum(), 0)) {
                                    break;
                                }
                                
                            } else {
                                if (makeCardToStack(Arena.getID(1), 1)) {
                                    break;
                                }
                            }
                            int[] r = findRepeats(Arena.getID(0));boolean poss=true;
                            if (r[1] != 100) {
                                for(byte b=0;b<Arena.size();b++){
                                    if(Arena.getID(b)==cardArrayBox.single){
                                        if(Arena.getCard(b).getNum()==pl.seeCard(r[0]).getNum()){
                                            
                                            poss=false;
                                            break;
                                        }
                                    }
                                    else{
                                        if(Arena.getID(b)==pl.seeCard(r[0]).getNum()){
                                            poss=false;
                                            break;
                                        }
                                    }
                                }if(poss){
                               // System.out.println("inside nsingle,repeat,0");
                                Arena.add(pl.removeCard(r[0]));
                                poss=false;
                                }
                            } if(poss) {
                                r = findRepeats(Arena.getID(1));
                               
                                if (r[1] != 100) {
                                for(byte b=0;b<Arena.size();b++){
                                    if(Arena.getID(b)==cardArrayBox.single){
                                        if(Arena.getCard(b).getNum()==pl.seeCard(r[0]).getNum()){
                                            
                                            poss=false;
                                            break;
                                        }
                                    }
                                    else{
                                        if(Arena.getID(b)==pl.seeCard(r[0]).getNum()){
                                            poss=false;
                                            break;
                                        }
                                    }
                                }if(poss){
                                   // System.out.println("inside nsingle,repeat,1");
                                    Arena.add(pl.removeCard(r[0]));
                                    poss=false;
                                }
                                } if(poss) {
                                    //System.out.println("inside nsingle,throwUp");
                                    throwUp();
                                }
                            }
                            
                            break;                            
                        }
                    default:
                        //System.out.println("inside default");
                        byte single = 0,
                         nascent = 0,
                         stack = 0;
                        byte[] asingle = new byte[20];
                        byte[] anascent = new byte[3];
                        byte[] astack = new byte[3];
                        for (byte b = 0; b < Arena.size(); b++) {
                            if (Arena.getType(b) == cardArrayBox.nascent) {
                                anascent[nascent] = b;
                                nascent++;
                            }
                            if (Arena.getType(b) == cardArrayBox.stack) {
                                astack[stack] = b;
                                stack++;
                            }
                            if (Arena.getType(b) == cardArrayBox.single) {
                                asingle[single] = b;
                                single++;
                            }
                        }
                        boolean check = false;
                        if (single == 1) {
                           // System.out.println("in single==1");
                            if (stack > 1) {
                                for (byte b = 0; b < stack; b++) {
                                    int a = findCard(Arena.getID(astack[b]));
                                    if (a != 100) {
                                        check = true;
                             //           System.out.println("inside single==1");
                                        eaten.add(pl.removeCard(a));
                                        eaten.addAll(Arena.removeGrp(astack[b]));
                                    }
                                }
                                if (check) {
                                    break;
                                }
                            }
                            if (check) {
                                break;
                            }
                            if (stack > 0) {
                                for (byte b = 0; b < stack; b++) {
                                    int a = findCard(Arena.getID(astack[b]));
                                    if (a != 100) {
                                        for (byte c = 0; c < single; c++) {
                                            a = findCard(Arena.getID(astack[b]) - Arena.getCard(asingle[c]).getNum());
                                            if (a != 100) {
                                                boolean cont = false;
                                                for (byte d = 0; d < Arena.size(); d++) {
                                                    if (Arena.getType(d) != cardArrayBox.single) {
                                                        if (Arena.getID(d) == pl.seeCard(a).getNum()) {
                                                            cont = true;
                                                            break;
                                                        }
                                                    }
                                                }
                                                if (cont) {
                                                    continue;
                                                }
                                             //   System.out.println("inside single==1,stack>0");
                                                check = true;
                                                Arena.add(Arena.getCard(asingle[c]), astack[b]);
                                                Arena.add(pl.removeCard(a), astack[b]);
                                                Arena.removeCard(asingle[c]);
                                                break;
                                            }
                                        }
                                        if (check) {
                                            break;
                                        }
                                    }
                                }
                                if (check) {
                                    break;
                                }
                            }
                            if (nascent > 0) {
                                for (byte b = 0; b < nascent; b++) {
                                    int a = findCard(Arena.getID(anascent[b]));
                                    if (a != 100) {
                                        for (byte c = 0; c < single; c++) {
                                            a = findCard(Arena.getID(anascent[b]) - Arena.getCard(asingle[c]).getNum());
                                            if (a != 100) {
                                                boolean cont = false;
                                                for (byte d = 0; d < Arena.size(); d++) {
                                                    if (Arena.getType(d) != cardArrayBox.single) {
                                                        if (Arena.getID(d) == pl.seeCard(a).getNum()) {
                                                            cont = true;
                                                            break;
                                                        }
                                                    }
                                                }
                                                if (cont) {
                                                    continue;
                                                }
                                               // System.out.println("inside single==1,nascent>0");
                                                check = true;
                                                Arena.add(Arena.getCard(asingle[c]), anascent[b]);
                                                Arena.add(pl.removeCard(a), anascent[b]);
                                                Arena.removeCard(asingle[c]);
                                                break;
                                            }
                                        }
                                        if (check) {
                                            break;
                                        }
                                    }
                                }
                                if (check) {
                                    break;
                                }
                                for (byte b = 0; b < nascent; b++) {
                                    if (makeCardToStack(Arena.getID(anascent[b]), anascent[b])) {
                                       // System.out.println("inside single==1,changeStack");
                                        check = true;
                                        break;
                                    }
                                }
                                if (check) {
                                    break;
                                }
                            }
                            for (byte b = 0; b < single; b++) {
                                if (makeCardToStack(Arena.getCard(asingle[b]).getNum(), asingle[b])) {
                                    //System.out.println("inside single==1,makeCardToStack");
                                    check = true;
                                    break;
                                } else {
                                    if (tryToEat(Arena.getCard(asingle[single]).getNum(), asingle[single]))
                                    {
                                       // System.out.println("inside single==1,tryTOEat");
                                        check = true;
                                        break;
                                    }
                                }
                            }
                            if (check) {
                                break;
                            } else {
                               // System.out.println("inside single==1,throwUp");
                                throwUp();
                            }
                            break;
                        }
                        if (single == 0) {
                            //System.out.println("in single==0");
                            for (byte b = 0; b < stack; b++) {
                                if (tryToEat(Arena.getID(astack[b]), astack[b])){
                                    check = true;
                                   // System.out.println("inside single==0,trytoeat stack");
                                    break;
                                }
                            }
                            if (check) {
                                break;
                            }
                            for (byte b = 0; b < nascent; b++) {
                                if (tryToEat(Arena.getID(anascent[b]), anascent[b])) {
                                   // System.out.println("inside single==0,trytoeat nascent");
                                    check = true;
                                    break;
                                }
                            }
                            if (check) {
                                break;
                            } else {
                                //System.out.println("inside single==0,throwup");
                                throwUp();
                            }
                            break;
                        }
                        if (single == 2) {
                            //System.out.println("in single==2");
                            if (stack > 0) {
                                for (byte b = 0; b < stack; b++) {
                                    int a = findCard(Arena.getID(astack[b]));
                                    if (a != 100) {
                                        for (byte c = 0; c < single; c++) {
                                            a = findCard(Arena.getID(astack[b]) - Arena.getCard(asingle[c]).getNum());
                                            if (a != 100) {
                                                boolean cont = false;
                                                for (byte d = 0; d < Arena.size(); d++) {
                                                    if (Arena.getType(d) != cardArrayBox.single) {
                                                        if (Arena.getID(d) == pl.seeCard(a).getNum()) {
                                                            cont = true;
                                                            break;
                                                        }
                                                    }
                                                }
                                                if (cont) {
                                                    continue;
                                                }
                                                //System.out.println("inside single==2, stack");
                                                check = true;
                                                Arena.add(Arena.getCard(asingle[c]), astack[b]);
                                                Arena.add(pl.removeCard(a), astack[b]);
                                                Arena.removeCard(asingle[c]);
                                                break;
                                            }
                                        }
                                        if (check) {
                                            break;
                                        }
                                    }
                                }
                                if (check) {
                                    break;
                                }
                            }
                            if (nascent > 0) {
                               // System.out.println("in nascent");
                                for (byte b = 0; b < nascent; b++) {
                                   // System.out.println("in first for loop");
                                    int a = findCard(Arena.getID(anascent[b]));
                                    if (a != 100) {
                                        for (byte c = 0; c < single; c++) {
                                          //  System.out.println("in second for loop");
                                            a = findCard(Arena.getID(anascent[b]) - Arena.getCard(asingle[c]).getNum());
                                            if (a != 100) {
                                                boolean cont = false;
                                                for (byte d = 0; d < Arena.size(); d++) {
                                                    if (Arena.getType(d) != cardArrayBox.single) {
                                                        if (Arena.getID(d) == pl.seeCard(a).getNum()) {
                                                            cont = true;
                                                            break;
                                                        }
                                                    }
                                                }
                                                if (cont) {
                                                    continue;
                                                }
                                               // System.out.println("inside single==2,nascent");
                                                check = true;
                                                Arena.add(Arena.getCard(asingle[c]), anascent[b]);
                                                Arena.add(pl.removeCard(a), anascent[b]);
                                                Arena.removeCard(asingle[c]);
                                                break;
                                            }
                                        }
                                        if (check) {
                                            break;
                                        }
                                    }
                                }
                                if (check) {
                                    break;
                                }
                            }
                            for (byte b = 0; b < single; b++) {
                                if (makeCardToStack(Arena.getCard(asingle[b]).getNum(), asingle[b])) {
                                   // System.out.println("inside single==2,makeCardToStack");
                                    check = true;
                                    break;
                                }
                            }
                            if (check) {
                                break;
                            }
                            for (byte b = 0; b < nascent; b++) {
                                if (tryToEat(Arena.getID(anascent[b]), anascent[b])) {
                                    check = true;
                                    //System.out.println("inside single==2,trytoeat nascent");
                                    break;
                                }
                            }
                            if (check) {
                                break;
                            } else {
                                //System.out.println("inside single==2,throwup");
                                throwUp();
                            }
                            break;
                        }
                        if (single >= 3) {
                           // System.out.println("in single==3");
                            for (byte b = 0; b < single; b++) {
                                if (makeCardToStack(Arena.getCard(asingle[b]).getNum(), asingle[b])) {
                                 //   System.out.println("inside single==3,makeCardToStack");
                                    check = true;
                                    break;
                                }
                            }
                            if (check) {
                                break;
                            }
                            for (byte b = 0; b < single; b++) {
                                if (tryToEat(Arena.getCard(asingle[b]).getNum(), asingle[b])) {
                                   // System.out.println("inside single==3,trytoeat");
                                    check = true;
                                    break;
                                }
                            }
                            if (check) {
                                break;
                            } else {
                                //System.out.println("inside single==3,throwup");
                                throwUp();
                                break;
                            }
                            
                        }
                        
                        break;
                    
                }
            }
        }
        
    }
    boolean tryToEat(int num,int index){
        int a=findCard(num);//System.out.println("a="+a);
        if(a!=100){
            eaten.add(pl.removeCard(a));
            eaten.addAll(Arena.removeGrp(index));
            for(byte b=0;b<Arena.size();b++){
                if(b!=index){
                    if(Arena.getType(b)==cardArrayBox.single){
                        if(Arena.getCard(b).getNum()==num){
                            eaten.add(Arena.removeCard(b));
                        }
                    }
                    else{
                        if(Arena.getID(b)==num){
                            eaten.addAll(Arena.removeGrp(b));
                        }
                    }
                }
            }
            return true;
        }//System.out.println("what happened here? a="+a);
        return false;
    }
    boolean makeCardToStack(int num,int index){
        boolean check=false;
                  for(byte b=13;b>8;b--){
                      byte c=0;boolean cont=false;
                      for(byte d=0;d<Arena.size();d++){
                              if(Arena.getType(d)!=cardArrayBox.single){
                                  if(Arena.getID(d)==b){
                                      cont=true;//System.out.println("b="+b);
                                      break;
                                  }
                              }
                              else{
                                  if(Arena.getCard(d).getNum()==b){
                                      cont=true;//System.out.println("b="+b);
                                      break;
                                  }
                              }
                          }if(cont){
                              continue;
                          }
                      while(c<pl.size()){
                          
                          
                          
                          int diff1=b-num;
                                    if(diff1<0) break;
                                if(pl.seeCard(c).getNum()==b){
                                     if(diff1>0){
                                         if(findCard(diff1)==100){
                                             c++; 
                                             continue;
                                         }
                                         else{
                                             Arena.add(pl.removeCard(findCard(diff1)), index);
                                             Arena.setID(index, b);
                                             check=true;
                                         }break;
                                     }
                                }
                                c++;
                            }if(check) break;
                        }
                        return check;
    }
    
    
    void throwUp(){//System.out.println("in throwup");
        boolean check=false;
        for(byte b=1;b<=13;b++){
            int[] a=findRepeats(b);
            if(a[1]!=100){ boolean cont=false;//System.out.println("card = "+pl.seeCard(a[0]).getNum());
                for(byte c=0;c<Arena.size();c++){
                    if(Arena.getType(c) ==cardArrayBox.single&&pl.seeCard(a[0]).getNum()==Arena.getCard(c).getNum())
                    {//System.out.println("c="+c);
                        cont=true;
                        break;
                    }
                    if(Arena.getType(c) !=cardArrayBox.single&&Arena.getID(c)==pl.seeCard(a[0]).getNum()){
                        cont=true;//System.out.println("c="+c);
                        break;
                    }
                    
                }if(cont)continue;
                
                Arena.add(pl.removeCard(a[0]));//System.out.println("removed");
                check=true;
                break;
            }
        }
        
        if (!check) {
            for (byte b = 0; b < pl.size(); b++) {
                if (pl.seeCard(b).getNum() < 9) {
                    //System.out.println("card removed="+pl.seeCard(b).getNum());
                    Arena.add(pl.removeCard(b));
                    check = true;
                    break;
                }
            }
        }
        if(!check){
            for (byte b = 0; b < pl.size(); b++) {
                for (byte c = 0; c < Arena.size(); c++) {
                    if (Arena.getType(c) == cardArrayBox.single) {
                        if (Arena.getCard(c).getNum() == pl.seeCard(b).getNum()) {
                            eaten.add(pl.removeCard(b));
                            eaten.add(Arena.removeCard(c));
                            check = true;
                            break;
                        }
                        
                    } else {
                        if (Arena.getID(c) == pl.seeCard(b).getNum()) {
                            eaten.add(pl.removeCard(b));
                            eaten.addAll(Arena.removeGrp(c));
                            check = true;
                            break;
                        }
                    }
                }if(check)break;
            }
        }
        if(!check){//System.out.println("last resort");
            
            Arena.add(pl.removeCard(0));
        }
    }
    int[] findRepeats(int num){
        int[] a=new int[4];a[0]=100;a[1]=100;
        for(byte b=0,c=0;b<pl.size()&&c<2;b++){
            if(pl.seeCard(b).getNum()==num) {
                 a[c]=b;
                 c++;
            }
        }//System.out.println("a[0]="+pl.seeCard(a[0]).getNum()+"a[1]="+pl.seeCard(a[1]).getNum());
        return a;
    }
    int findCard(int num){
       for(byte b=0;b<pl.size();b++){
           if(pl.seeCard(b).getNum()==num) return b;
       }
       return 100;
    }
    boolean sweepArena(){
        switch(Arena.size()){
            case(1):for(byte b=0;b<pl.size();b++){
                if(Arena.getCard(0).getNum()==pl.seeCard(b).getNum()){
                    eaten.add(Arena.removeCard(0));
                    return eaten.add(pl.removeCard(b));
                    }
                    }return false;                
            case(2): if(Arena.sizeOfGrp(0)>1&&Arena.sizeOfGrp(1)>1) return false;
                     if(Arena.sizeOfGrp(0)==1&&Arena.sizeOfGrp(1)==1){
                         int sum=(Arena.getCard(0).getNum()+Arena.getCard(1).getNum());
                         if(sum<13){
                             for(byte b=0;b<pl.size();b++){
                                if(sum==pl.seeCard(b).getNum()){
                                    eaten.add(pl.removeCard(b));
                                    eaten.add(Arena.removeCard(0));
                                    return eaten.add(Arena.removeCard(0));
                                } 
                             }return false;
                             
                         }
                         else return false;
                     }
                     if(Arena.sizeOfGrp(0)==1){
                         if((Arena.getCard(0).getNum()+Arena.getID(1))<13&&Arena.getType(1)==cardArrayBox.nascent){
                             int sum=Arena.getCard(0).getNum()+Arena.getID(1);
                             for(byte b=0;b<pl.size();b++){
                                if(sum==pl.seeCard(b).getNum()){
                                    eaten.add(pl.removeCard(b));
                                    eaten.add(Arena.removeCard(0));
                                    return eaten.addAll(Arena.removeGrp(0));
                                } 
                             }return false;
                        }return false;
                    }
                    if(Arena.sizeOfGrp(1)==1){
                         if((Arena.getCard(1).getNum()+Arena.getID(0))<13&&Arena.getType(0)==cardArrayBox.nascent){
                             int sum=Arena.getCard(1).getNum()+Arena.getID(0);
                             for(byte b=0;b<pl.size();b++){
                                if(sum==pl.seeCard(b).getNum()){
                                    eaten.add(pl.removeCard(b));
                                    eaten.add(Arena.removeCard(1));
                                    return eaten.addAll(Arena.removeGrp(0));
                                } 
                             }return false;
                        }return false;
                    }break;
                case(3):int single=0,nsingle=0;
                    for(byte b=0;b<3;b++){
                        if(Arena.getType(b)==cardArrayBox.single)single++;
                        else nsingle++;
                    }
                    if(single==3){
                        int sum=Arena.getCard(0).getNum()+Arena.getCard(1).getNum()+Arena.getCard(2).getNum();
                        if(sum<13){
                           for(byte b=0;b<pl.size();b++){
                                if(sum==pl.seeCard(b).getNum()){
                                    eaten.add(pl.removeCard(b));
                                    eaten.add(Arena.removeCard(1));
                                    return eaten.add(Arena.removeCard(0));
                                }  
                        }return false;
                        } else return false;
                    }
                    if(single==2){
                        byte b=4;
                        if(Arena.getType(0)!=cardArrayBox.single) b=0;
                        if(Arena.getType(1)!=cardArrayBox.single) b=1;
                        if(Arena.getType(2)!=cardArrayBox.single) b=2;
                        
                            
                            for (int i = 0; i < pl.size(); i++) {
                                if (pl.seeCard(i).getNum()==Arena.getID(b)) {
                                    int sum=0;
                                    if (b == 0) {
                                        sum = Arena.getCard(1).getNum() + Arena.getCard(2).getNum();
                                    }
                                    if (b == 1) {
                                        sum = Arena.getCard(0).getNum() + Arena.getCard(2).getNum();
                                    }
                                    if (b == 2) {
                                        sum = Arena.getCard(1).getNum() + Arena.getCard(0).getNum();
                                    }
                                    if(sum==Arena.getID(b)){
                                        eaten.add(pl.removeCard(i));
                                        eaten.addAll(Arena.removeGrp(2));
                                        eaten.addAll(Arena.removeGrp(1));
                                        return eaten.addAll(Arena.removeGrp(0));
                                        
                                    }else return false;
                                }
                            }
                        if(Arena.getType(b)==cardArrayBox.nascent){
                            return false;
                        }
                        else return false;
                                       
                        
                    }
                    if(single==1){
                        return false;
                    }
                    if(single==0){
                        return false;
                    }
                default:return false;  
                }
        return false;
    }
    
}

