
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Me
 */
public class Naloga1 {
    
    int najkrajsa = 500000;
    String pot = "";
    static int [][] koordinate;
    
    public void razdalja(int x, int y, int dolzina, int[] pobran, int stPotnikov, int stStrank, int stMest, String potek){
        if(dolzina > najkrajsa){
            return;
        }
        int k = 0;
        for(int i = 0; i < stStrank; i++){
            if(pobran[i] == 0){
                if(stMest < stPotnikov){
                    int d = dolzina + Math.abs(x - koordinate[i][0]) + Math.abs(y - koordinate[i][1]);
                    pobran[i] = 1;
                    String s = potek + (i+1) + ",";
                    razdalja(koordinate[i][0],koordinate[i][1], d, pobran, stPotnikov, stStrank, stMest+1, s);
                    pobran[i] = 0;
                    continue;
                }
            }
            if(pobran[i] == 1){
                int d = dolzina + Math.abs(x - koordinate[i][2]) + Math.abs(y - koordinate[i][3]);
                pobran[i] = 2;
                String s = potek + (i+1) + ",";
                razdalja(koordinate[i][2],koordinate[i][3], d, pobran, stPotnikov, stStrank, stMest-1, s);
                pobran[i] = 1;
                continue;
            }
            if(pobran[i] == 2){
                k++;
            }
        }
        if(k == stStrank){
            najkrajsa = dolzina;
            pot = potek;
        }
        
    }
    public void izpis(){
        String a = pot.substring(0, pot.length() - 1);
        System.out.println(a);
    }
    public void izpis2(String s){
        try{
            PrintWriter pw = new PrintWriter(s);
            String a = pot.substring(0, pot.length() - 1);
            pw.print(a);
            pw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void main(String [] args){
        try{
            long s = System.nanoTime();

            Scanner sc = new Scanner(System.in);
            int stPotnikov = Integer.parseInt(sc.nextLine());

            String line = sc.nextLine();
            String [] k = line.split(",");

            int zX = Integer.parseInt(k[0]);
            int zY = Integer.parseInt(k[1]);

            int stStrank = Integer.parseInt(sc.nextLine());

            koordinate = new int [stStrank][4];

            int [] pobran = new int [stStrank];

            for(int i = 0; i < stStrank; i++){
                line = sc.nextLine();
                k = line.split(",");
                koordinate[i][0] = Integer.parseInt(k[1]);
                koordinate[i][1] = Integer.parseInt(k[2]);
                koordinate[i][2] = Integer.parseInt(k[3]);
                koordinate[i][3] = Integer.parseInt(k[4]);
            }
            Naloga1 n = new Naloga1();
            n.razdalja(zX, zY, 0, pobran, stPotnikov, stStrank, 0, "");
            n.izpis();
            long z = System.nanoTime();
            long cas = (z - s) / 1000000;
            System.out.println(cas);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
