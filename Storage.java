/**
 * Name         : YEO WEI TECK VICTOR 
 * Matric No.   : A0154004X
 * Plab Acct.   :
 */
import java.util.*;

public class Storage {

    private ArrayList<Box> remoteStorage;
    private ArrayList<Item> ownItem;
    private ArrayList<Item> allItem;
    private int _numBox;
    private int _boxSize;
    private int _numCarry;

    public Storage(){

        remoteStorage = new ArrayList<Box>();
        ownItem = new ArrayList<Item>();
        allItem = new ArrayList<Item>();

    }

    public void run() {

        Scanner sc = new Scanner (System.in);
        _numBox = sc.nextInt();
        _numCarry = sc.nextInt();
        _boxSize = sc.nextInt();

        createBox();

        int numQueries = sc.nextInt();

        sc.nextLine();

        for (int i=0; i<numQueries; i++){
            String[] command = sc.nextLine().split(" ");
            switch (command[0]){

                case "purchase":
                    purchaseItem(command[1], Integer.parseInt(command[2]));    
                    break;

                case "deposit":
                    depositItem(command[1]);

                    break;

                case "withdraw":
                    withdrawItem(command[1]);

                    break;

                case "location":
                    locateItem(command[1]);
                    break;

                case "valuable":
                    mostValuable();
                    break;


            }
        }

    }
    //this method allows the purchase of item and keep them with Evan if he still can hold or deposit them in remote storage
    public void purchaseItem(String name, int value){

        Item item = new Item (name, value);
        allItem.add(item);

        if (ownItem.size() + 1 <= _numCarry){
            ownItem.add(item);
            System.out.println ("item " + item.getName() + " is now being held");
        }
        else{

            depositItem(name);

        }
    }

    public static void main(String[] args) {
        Storage myStorageSystem = new Storage();
        myStorageSystem.run();
    }

    public void depositItem(String name){
        
        for (int i=0; i<ownItem.size(); i++){
            if (ownItem.get(i).getName().equals(name))
                ownItem.remove(i);
            }


        //check whether the item is already in storage
        boolean inStorage = false;
        for (int i=0; i<remoteStorage.size(); i++){
            for (int j=0; j<remoteStorage.get(i).getItemList().size(); j++){

                if (remoteStorage.get(i).getItemList().get(j).getName().equals(name))
                    inStorage = true;
            }
        }

        if (inStorage)
            System.out.println("item " + name + " is already in storage");
        else {
            Item item = null;
            for (int i=0; i<allItem.size(); i++){
                if (allItem.get(i).getName().equals(name)){
                    item = allItem.get(i);
                    break;
                }
                else
                    item = null;

            }

            //if item was not bought, it will not be in all item list. Hence, item reference will still be null
            if (item == null)
                System.out.println("item " + name + " does not exist");

            else{
                for (int i=0; i< remoteStorage.size(); i++){
                    if (remoteStorage.get(i).getItemList().size() + 1 <= _boxSize){
                        remoteStorage.get(i).getItemList().add(item);
                        System.out.println("item " + name + " has been deposited to box " + (i+1));
                        break;
                    }
                }   
            }
        }
    }
    //this method withdraws an item from remote storage, only if it is present
    public void withdrawItem(String name){

        //checks whether the item is on the hand already
        boolean onHand = false;
        for (int i=0; i<ownItem.size(); i++){
            if (ownItem.get(i).getName().equals(name))
                onHand = true;
        }
        if(onHand)
            System.out.println("item " + name + " is already being held");

        else{

            Item item = null;
            for (int i=0; i<allItem.size(); i++){
                if (allItem.get(i).getName().equals(name)){
                    item = allItem.get(i);
                    break;
                }
                else
                    item = null;
            }

            if (item == null)
                System.out.println("item " + name + " does not exist");

            else {
                if (ownItem.size() + 1 <= _numCarry){
                    ownItem.add(item);
                    for (int i=0; i<remoteStorage.size(); i++){
                        if (remoteStorage.get(i).getItemList().contains(item))
                            remoteStorage.get(i).getItemList().remove(item);
                        }
                    System.out.println("item " + name + " has been withdrawn");
                }

                else{

                    System.out.println("cannot hold any more items");

                }


            }
        } 
    }

    //this method determines where the item is currently located
    public void locateItem(String name){

        //check whether the item is already in storage

        Item item = null;
        for (int i=0; i<allItem.size(); i++){
            if (allItem.get(i).getName().equals(name)){
                item = allItem.get(i);
                break;
            }
            else
                item = null;
        }

        //if item was not bought, it will not be in all item list. Hence, item reference will still be null
        if (item == null)
            System.out.println("item " + name + " does not exist");

        else{
            if (ownItem.contains(item))
                System.out.println("item " + name + " is being held");
            else{ 
                for (int i=0; i<remoteStorage.size(); i++){
                    if (remoteStorage.get(i).getItemList().contains(item)){
                        System.out.println("item " + name + " is in box " + (1+i));
                        break;
                    }
                }
            }
        }
    }
    public void createBox(){

        for (int i=0; i<_numBox; i++){

            Box box = new Box(_boxSize);
            remoteStorage.add(box);

        }     
    }
    
    //this method checks for the most valuable item, and prints out the maximum value item with lexicographically smallest name
    public void mostValuable(){
        int max = 0;
        Item mostValuableItem = new Item ("a", 0);
        for (int i=0 ;i< allItem.size(); i++){
            if (allItem.get(i).getValue() > max){
                mostValuableItem = allItem.get(i);
                max = mostValuableItem.getValue();
            }

            else if (allItem.get(i).getValue() == max){
                if (allItem.get(i).getName().compareTo(mostValuableItem.getName()) < 0)
                    mostValuableItem = allItem.get(i);
            }
        }

        System.out.println(mostValuableItem.getName());
    }

}
class Box {

    private int _size;
    private ArrayList<Item> itemList;

    public Box (int size){

        _size = size;
        itemList = new ArrayList<Item>();
    }

    public int getSize (){

        return _size;
    }

    public void addItem(Item item) {

        this.itemList.add(item);
    }

    public ArrayList<Item> getItemList(){

        return this.itemList;
    }
}

class Item {

    private String _name;
    private int _value;

    public Item(String name, int value) {

        _name = name;
        _value = value;

    }

    public String getName(){

        return _name;
    } 

    public int getValue(){

        return _value; 
    }
}
