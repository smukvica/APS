
import java.io.*;
import java.util.*;

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
        int a = 0;
        for(int i = 0; i < polje.length-1; i++){
            if(polje[i] == id){
                polje[i] = 0;
                a++;
            }
        }
        return a;
    }
    
    public void defrag(int n){
        while(n>0){
            int nezaseden = 0;
            int zaseden = 0;
            int velikost = 0;
            int id = 0;
            for(int i = 0; i < polje.length; i++){
                if(polje[i] == 0){
                    nezaseden = i;
                    break;
                }
            }
            for(int i = nezaseden; i < polje.length; i++){
                if(polje[i] != 0){
                    zaseden = i;
                    id = polje[i];
                    break;
                }
            }
            for(int i = zaseden; i < polje.length; i++){
                if(polje[i] == id){
                    velikost++;
                }else{
                    break;   
                }
            }
            free(id);
            alloc(velikost, id);
            n--;
        }
    }

    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String line;
        int a = Integer.parseInt(sc.nextLine());
        Naloga2 n = new Naloga2();
        while(a>0){
            line = sc.nextLine();
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
        for(int i = 0; i < n.polje.length; i++){
            System.out.println(i + ": " + n.polje[i]);
        }
    }
    
    
}
