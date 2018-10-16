/*
 Name: YEO WEI TECK VICTOR
 Matric No.: A0154004X
 */

import java.util.*;

public class Island {

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);

        int[][] arr = createGrid(sc);
        int noTix = numberOfTickets(arr);

        System.out.println(noTix);

    }

    public static int[][] createGrid (Scanner sc){
        
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] arr = new int [row][col];
        
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                arr[i][j] = sc.nextInt();
                }
            }
        
        return arr;
               
        }

    public static int numberOfTickets(int[][] arr){
        
        int count = 0;

        for (int i=0; i<arr.length; i++){
            for (int j=0; j<arr[0].length; j++){
                if (((i!=0)&&(j!=0)&&(arr[i][j-1]==0)&&(arr[i-1][j]==0)&&(arr[i][j]==1))||((i==0)&&(j==0)&&(arr[i][j]==1))||((i==0)&&(j!=0)&&(arr[i][j-1]==0)&&(arr[i][j]==1))||((i!=0)&&(j==0)&&(arr[i-1][j]==0)&&(arr[i][j]==1)))
                    count++;
                }
            }
        return count;
        
        }
}
