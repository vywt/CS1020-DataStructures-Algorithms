/**
 * Name   : YEO WEI TECK VICTOR
 * Matric No.  : A0154004X
 * PLab Acct.  : -
 */

import java.util.*;
public class BarryPutter {

    private void run() {
        Scanner sc = new Scanner(System.in);
        int numChambers = sc.nextInt(); //number of chambers (<=1)
        int numApp = sc.nextInt(); //number of apparations(<=10) 

        Chamber[] chamberList = new Chamber[numChambers];
        
        for (int i=0; i<numChambers; i++){
            int indexChamber = sc.nextInt();

            Chamber chamber = new Chamber(sc.nextInt(), sc.nextInt());

            chamber.addPath(sc);

            chamberList[indexChamber] = chamber;
            }

        System.out.println(maxGoldCoin(chamberList, 0, numApp));     
        
    }


    public int maxGoldCoin(Chamber[] chamberList, int position, int numApp){
        
        //base case
        if (numApp == 0 && position <chamberList.length){
            return chamberList[position].getNumGold();
            }

        if (position >= chamberList.length || position < 0){
            return 0;
            }
        
        else{
            int maxGold = 0;
            int pathNum = 0;
            int[] pathList = chamberList[position].getPathList();
            for (int i=0; i<chamberList[position].getNumPath(); i++){
                int goldCoin = maxGoldCoin(chamberList, position + pathList[i], numApp-1);
 
                if (goldCoin >= maxGold){
                    maxGold = goldCoin;
                    pathNum = pathList[i];
                    }
                }
        
            return chamberList[position].getNumGold() + maxGoldCoin(chamberList, position+pathNum, numApp-1); 
           }

        }

    public static void main(String[] args) {
        BarryPutter barry = new BarryPutter();
        barry.run();
    }
}



class Chamber{
    
    private int numGoldCoin;
    private int numPath;
    private int[] pathList; 


    public Chamber(int numGoldCoin, int numPath){
        this.numGoldCoin = numGoldCoin;
        this.numPath = numPath;
        pathList = new int[numPath];
        }
    
    public void addPath(Scanner sc){
        for (int i=0; i<numPath; i++){
            pathList[i] = sc.nextInt();
            }
        }


    public int getNumGold() {
        return numGoldCoin;
        }
        
    public int getNumPath(){
        return numPath;
        }

    public int[] getPathList(){
        return pathList;
        }
    
    }
