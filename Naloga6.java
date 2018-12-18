
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elohim
 */
public class Naloga6 {
    static int [][] mesta;
    
    static int poti = 0;
    
    static void moznosti(int zacetno, int koncno, int visina, int [] obiskana){
        if(zacetno == koncno){
            poti++;
            return;
        }
        for(int i = 0; i < mesta.length; i++){
            if(zacetno == i){
                continue;
            }
            if(obiskana[i] == 1){
                continue;
            }
            if(mesta[zacetno][i] < visina && mesta[zacetno][i] != -1){
                continue;
            }
            obiskana[i] = 1;
            moznosti(i, koncno, visina, obiskana);
            obiskana[i] = 0;  
        }
    }
    
    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);
        int a = Integer.parseInt(sc.nextLine());
        int [] obiskana = new int [a];
        mesta = new int [a][a];
        while(a>0){
            String [] s = sc.nextLine().split(",");
            mesta[Integer.parseInt(s[0])-1][Integer.parseInt(s[1])-1] = Integer.parseInt(s[2]);
            mesta[Integer.parseInt(s[1])-1][Integer.parseInt(s[0])-1] = Integer.parseInt(s[2]);
            a--;
        }
        String [] s = sc.nextLine().split(",");
        int zacetno = Integer.parseInt(s[0]);
        int koncno = Integer.parseInt(s[1]);
        int visina = Integer.parseInt(sc.nextLine());
        moznosti(zacetno-1, koncno-1, visina, obiskana);
        System.out.println(poti);
    }
}
