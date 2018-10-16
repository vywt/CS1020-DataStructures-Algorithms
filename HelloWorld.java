// YEO WEI TECK VICTOR(A0154004X)
// This programme enables 3 different input parsing methods

import java.util.Scanner;
class HelloWorld{
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner (System.in);

        int opsType = sc.nextInt();

        switch (opsType){
            
            case 1:

            int noOps = sc.nextInt();
            sc.nextLine(); //to absorb the enter from integer input

            for (int i=0;i<noOps;i++){
                
                String str = sc.nextLine();
                String[] arr = str.split(" ");

                switch (arr[0]){
                    
                    case "AND":
                        
                        if (Integer.parseInt(arr[1]) == 1 && Integer.parseInt(arr[2]) == 1)
                            
                            System.out.println("1");

                        else 
                            System.out.println("0");

                    break;

                    case "OR":

                        if (Integer.parseInt(arr[1]) == 1 || Integer.parseInt(arr[2]) == 1)
                            System.out.println("1");

                        else 
                            System.out.println("0");

                    break;
                    
                    }
                }

            break;

            case 2:

            do {
                
                String secStr = sc.nextLine();
                if (secStr.equals("0"))
                    break;

                String[] secArr = secStr.split(" ");
                switch (secArr[0]){
                    
                    case "AND":

                        if (Integer.parseInt(secArr[1]) == 1 && Integer.parseInt(secArr[2]) == 1)
                            
                            System.out.println("1");
                        else
                            System.out.println("0");
                    break;

                    case "OR":

                        if (Integer.parseInt(secArr[1]) == 1 || Integer.parseInt(secArr[2]) == 1)
                            
                            System.out.println("1");
                        else
                            System.out.println("0");
                    break;
                    }
                }

            while (true);

            break;

            case 3:

            do {
                
                String thiStr = sc.nextLine();

                String[] thiArr = thiStr.split(" ");
                switch (thiArr[0]){
                    
                    case "AND":

                        if (Integer.parseInt(thiArr[1]) == 1 && Integer.parseInt(thiArr[2]) == 1)
                            
                            System.out.println("1");
                        else
                            System.out.println("0");
                    break;

                    case "OR":

                        if (Integer.parseInt(thiArr[1]) == 1 || Integer.parseInt(thiArr[2]) == 1)

                            System.out.println("1");
                        else
                            System.out.println("0");
                    break;
                    
                    }
                }
            
            while (sc.hasNextLine());


            break;
            
            }
        
        }
    }
