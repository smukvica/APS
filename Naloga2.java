import java.io.*;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mitja Vendramin
 */
public class Naloga2 {
    int [] polje;
    
    public  void init(int size){
        polje = new int[size];
    }
    
    public boolean alloc(int size, int id){
        int prazno = 0;
        int mesto = 0;
        for(int i = 0; i < polje.length; i++){
            if(polje[i] == 0 && prazno == 0){
                prazno++;
                mesto = i;
            }
            else if(polje[i] == 0 && prazno > 0){
                prazno++;
            }
            else if(polje[i] != 0 && prazno < size){
                prazno = 0;
            }
            else if(polje[i] == id){
                return false;
            }
        }
        if(prazno < size){
            return false;
        }
        else{
            for(int i = mesto; i < mesto + size; i++){
                polje[i] = id;
            }
        }
        return true;
    }
    
    public int free(int id){
        boolean a = false;
        nezaseden = 0;
        for(int i = 0; i < polje.length; i++){
            if(polje[i] == id){
                polje[i] = 0;
            }
            if(polje[i] == id){
                a = true;
            }
            if(polje[i] != id && a){
                return i+1;
            }
        }
        return 0;
    }
    int nezaseden = 0;
    public void defrag(int n){
        
        int zaseden;
        int id;
        while(polje[nezaseden] != 0){
            nezaseden++;
        }
        
        while(n > 0){
            zaseden = nezaseden;
            while(polje[zaseden] == 0){
                zaseden++;
            }
            
            id = polje[zaseden];
            for( ;zaseden < polje.length; zaseden++){
                if(polje[zaseden] == id){
                    polje[nezaseden] = polje[zaseden];
                    polje[zaseden] = 0;
                    nezaseden++; 
                }
            }
            n--;
        }
    }
    
    public void izpis(String s){
        try{
            PrintWriter pw = new PrintWriter(s);
            for(int i = 0; i < polje.length; i++){
                if(polje[i]==0){
                    continue;
                }
                int koncni = konec(i, polje[i]);
                pw.println(polje[i] + "," + i + "," + koncni);
                i = koncni;
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void izpis2(){
        for(int i = 0; i < polje.length; i++){
            if(polje[i]==0){
                continue;
            }
            int koncni = konec(i, polje[i]);
            System.out.println(polje[i] + "," + i + "," + koncni);
            i = koncni;
        }
    }
    
    public int konec(int i, int id){
        while(polje[i] == id){
            i++;
            if(i >= polje.length){
                return i-1;
            }
            
        }
        return i-1;
    }

    
    public static void main(String[] args){
        try{
            Scanner br = new Scanner(System.in);
            String line;
            int a = Integer.parseInt(br.nextLine());
            Naloga2 n = new Naloga2();
            while(a>0){
                line = br.nextLine();
                String [] ukaz = line.split(",");
                switch(ukaz[0]){
                    case "i":{
                        n.init(Integer.parseInt(ukaz[1]));
                        break;
                    }
                    case "a":{
                        n.alloc(Integer.parseInt(ukaz[1]), Integer.parseInt(ukaz[2]));
                        break;
                    }
                    case "f":{
                        n.free(Integer.parseInt(ukaz[1]));
                        break;
                    }
                    case "d":{
                        n.defrag(Integer.parseInt(ukaz[1]));
                        break;
                    }
                }
                a--;
            }
            n.izpis2();
//            for(int i = 0; i < n.polje.length; i++){
//                System.out.println(i + ": " + n.polje[i]);
//            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    
}