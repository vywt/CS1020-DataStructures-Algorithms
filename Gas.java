//YEO WEI TECK VICTOR
import java.util.*;

class Gas{

    private ArrayList<City> cityList;

    public Gas(){
        cityList = new ArrayList<City>();
        }
    
    public static void main(String[] args){
        Gas gas = new Gas();
        gas.run();
        }
    
    //N[0,22]
    //X is the distance from the previous city t that city and the price (per uni) of filling the fuel at that city
    public void run(){
        Scanner sc = new Scanner (System.in);
        int N = sc.nextInt(); //number of cities in between Michael's home and Singapore
        System.out.println("check after numCities");

        for (int i=0; i<N; i++){
            int X = sc.nextInt(); //X[1,500] Distance from the previous city to the city
            int P = sc.nextInt(); //P[1,10000] Price(per unit) of filling the fuel at that city
            City city = new City(X, P);

            cityList.add(city);
            }

        City city = new City(sc.nextInt(), 0);

        cityList.add(city); //adding Singapore into the list of cities

        int minCost = minimumCost(cityList, 0, 200, 0);

        if (minCost == -1)
            System.out.println("can meh?");
        else
            System.out.println(minCost);
        }
    

    public int minimumCost(ArrayList<City> cityList, int position, int fuelLeft, int costIncurred){
        
        System.out.println("position now is " + position);

        System.out.println("fuel left is " + fuelLeft);

        System.out.println("cost incurred up to now is " + costIncurred);

        //basecase: distance to singapore == 0
        if (position == cityList.size()){
            return costIncurred;                         
            }

        if (cityList.get(position).getDistance() > 200)
            return -1;
       
        
        if (fuelLeft < cityList.get(position).getDistance())
            return costIncurred + minimumCost(cityList, position + 1, 200, costIncurred+ (200-fuelLeft)*cityList.get(position).getFuelPrice()); 

        int costFuelNow = minimumCost(cityList, position + 1, 200, costIncurred + (200-fuelLeft)*cityList.get(position).getFuelPrice());

        int costFuelLater = minimumCost(cityList, position + 1, fuelLeft - cityList.get(position).getDistance(), costIncurred); 
       



        if (costFuelLater == -1 ){
            System.out.println("1");
            return costIncurred + costFuelNow;
        }
    
        if (costFuelNow == -1){
            System.out.println("2");
            return costIncurred + costFuelLater;
        }
        
        if (costFuelLater > costFuelNow){
            System.out.println("3");
            return costIncurred + costFuelNow;
        }

        else {
            System.out.println("4");
            return costIncurred + costFuelLater;
        }
    }
}
class City{
    
    private int distance; 
    private int fuelPrice; 

    public City(int distance, int fuelPrice){
        this.distance = distance;
        this.fuelPrice = fuelPrice; 
        } 
    
    public int getDistance(){
        return distance;
        }

    public int getFuelPrice(){
        return fuelPrice;
        }
    }
