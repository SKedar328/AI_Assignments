package AI_Assignments;
import java.util.Scanner;
public class TicTacToeNonAi{
    public static int check(int arr[][]){
        int sum1=0;
        int sum2=0;
        int sum3=0;
        int sum4=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
               if(arr[i][j]==1)sum1+=arr[i][j];
              if(arr[i][j]==2) sum2+=arr[i][j];
            }
            if(sum1==3||sum2==6)break;
            else{
                sum1=0;
                sum2=0;
            }
        }
        if(sum1!=3&&sum2!=6){
            sum1=0;
            sum2=0;
            for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
               if(arr[j][i]==1)sum1+=arr[j][i];
              if(arr[j][i]==2) sum2+=arr[j][i];
            }
            if(sum1==3||sum2==6)break;
            else{
                sum1=0;
                sum2=0;
            }

        }
        }
        for(int i=0;i<3;i++){
           if(arr[i][i]==1) sum3+=arr[i][i];
           if(arr[i][i]==2) sum4+=arr[i][i];
        }
        int k=0;
        if(sum3!=3&&sum4!=6){
            sum3=0;
            sum4=0;
             for(int i=2;i>=0;i--){
               if(arr[i][k]==2) sum4+=arr[i][k];
               if(arr[i][k]==1) sum3+=arr[i][k];
                k++;
        }
        }
        
        if(sum1==3)return 1;
        else if(sum2==6) return 2;
        else if(sum3==3) return 1;
        else if(sum4==6) return 2;
        else return 0;
    }
    // public static void print(String arr[][]){
    //     for (int i = 0; i < arr.length; i++) {
    //         for (int j = 0; j < arr.length; j++) {
    //             System.out.print(arr[i][j]+"|");
    //         }
    //         System.out.println();
    //     }
    // }
    private static void print(String board[][]) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    public static void doubleplayergame(){
        Scanner sc=new Scanner(System.in);
        int count=0;
        int arr[][]=new int[3][3];
        String arr1[][]=new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                arr1[i][j]="-";
            }
        }
        int ans=0;
         for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(count==0){
                    System.out.println("Player no 1 Turn");
                    int x=sc.nextInt();
                    // int y=sc.nextInt();
                   
                    if(x==1)
                    {
                    arr1[2][0]="X";
                    arr[2][0]=1;}
                    else if(x==2){
                        arr1[2][1]="X";
                    arr[2][1]=1;
                    }
                    else if(x==3){
                        arr1[2][2]="X";
                    arr[2][2]=1;
                    }
                    else if(x==6){
                        arr1[1][2]="X";
                    arr[1][2]=1;
                    }
                    else if(x==5){
                        arr1[1][1]="X";
                    arr[1][1]=1;
                    }
                    else if(x==4){
                        
                       arr1[1][0]="X";
                       arr[1][0]=1;
                    }
                    else if(x==7){
                        arr1[0][0]="X";
                    arr[0][0]=1;
                    }
                    else if(x==8){
                        arr1[0][1]="X";
                    arr[0][1]=1;
                    }
                    else if(x==9){
                        
                       arr1[0][2]="X";
                       arr[0][2]=1;
                    }

                    count=1;
                }
                else{
                    System.out.println("Player no 2 Turn");
                    int x=sc.nextInt();
                    if(x==1)
                    {
                    arr1[2][0]="O";
                    arr[2][0]=2;}
                    else if(x==2){
                        arr1[2][1]="O";
                    arr[2][1]=2;
                    }
                    else if(x==3){
                        arr1[2][2]="O";
                    arr[2][2]=2;
                    }
                    else if(x==6){
                        arr1[1][2]="O";
                    arr[1][2]=2;
                    }
                    else if(x==5){
                        arr1[1][1]="O";
                    arr[1][1]=2;
                    }
                    else if(x==4){
                        
                       arr1[1][0]="O";
                       arr[1][0]=2;
                    }
                    else if(x==7){
                        arr1[0][0]="O";
                    arr[0][0]=2;
                    }
                    else if(x==8){
                        arr1[0][1]="O";
                    arr[0][1]=2;
                    }
                    else if(x==9){
                       arr1[0][2]="O";
                       arr[0][2]=2;
                    }
                    count=0;
                }
                print(arr1);
                ans=check(arr);
                if(ans!=0)break;
            }
            if(ans!=0)break;
           
        }
        if(ans==1)System.out.println("Player 1 has won");
        else if(ans==2)System.out.println("Player 2 has won");
        else System.out.println("Game is drawn");
    }
    public static void main(String[] args) {
       doubleplayergame();
}
}