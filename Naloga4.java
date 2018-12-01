
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mitja Vendramin
 */
public class Naloga4{

    public static void main(String [] args){
        int count = 0;
        try{
            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            int stevilo = Integer.parseInt(br.readLine());
            clen c = new clen();
            
            while(stevilo > 0){
                String s = br.readLine();
                String [] k = s.split(",");
                switch(k[0]){
                    case "s":{
                        c.init(Integer.parseInt(k[1]));
                        break;
                    }
                    case "i":{
                        c.insert(Integer.parseInt(k[1]), Integer.parseInt(k[2]));
                        break;
                    }
                    case "r":{
                        c.remove(Integer.parseInt(k[1]));
                        break;
                    }
                }
                stevilo--;
                count++;
            }
            PrintWriter pw = new PrintWriter(args[1]);
            pw.write(c.izpis());
            pw.close();
            
        }catch(Exception e){
            System.out.println(e);
            System.out.println(count);
        }
    }
}
class clen{
    
    clen naslednji = null;
    int [] elementi;
    int prost;
    int st;
    
    
    public void init(int n){
        elementi = new int [n];
        prost = n;
        st = n;
    }
    
    public boolean insert(int v, int p){
        if(p > st-prost){
            naslednji.insert(v, p-(st-prost));
        }
        else{
            if(elementi[p] == 0){
                elementi[p] = v;
                prost--;
                return true;
            }else{
                if(prost > 0){
                    shiftR(p);
                    elementi[p] = v;
                    prost--;
                    return true;
                }else{
                    if(naslednji == null){
                        naslednji = new clen();
                        naslednji.init(st);
                    }else{
                        clen t = naslednji;
                        naslednji = new clen();
                        naslednji.init(st);
                        naslednji.naslednji = t;

                    }
                    int a = st/2;
                    for(int i = a; i < st; i++){
                        naslednji.insert(elementi[i], i-a);
                        elementi[i] = 0;
                        prost++;
                    }
                    if(p <= a){
                        this.insert(v, p);
                    }else{
                        naslednji.insert(v, p-a);
                    }
                }
            }
        }
        return false;
    }
    
    public boolean remove(int p){
        if(p > st - 1 - prost){
            naslednji.remove(p - (st-prost));
        }
        else{
            elementi[p] = 0;
            prost++;
            if(prost != st){
                this.shiftL(p);
            }
            if(prost > st/2 + 1 && naslednji != null){
                elementi[st/2 - 1] = naslednji.prvi();
                naslednji.shiftL(0);
                naslednji.prost++;
                if(naslednji.prost == st){
                    naslednji = naslednji.naslednji;
                }
                prost--;
            }
        }
//        else{
//            if(elementi[p] == 0){
//                if(naslednji != null){
//                    int a = st/2;
//                    naslednji.remove(p - a);
//                    if(naslednji.prost == st){
//                        naslednji = naslednji.naslednji;
//                    }
//                }else{
//                    return false;
//                }
//            }else{
//                elementi[p] = 0;
//                prost++;
//                if(prost != st){
//                    this.shiftL(p);
//                }
//                if(prost > st/2 + 1 && naslednji != null){
//                    elementi[st/2 - 1] = naslednji.prvi();
//                    naslednji.shiftL(0);
//                    naslednji.prost++;
//                    if(naslednji.prost == st){
//                        naslednji = naslednji.naslednji;
//                    }
//                    prost--;
//                }
//            }
//        }
        
        return true;
    }
    
    public void shiftL(int a){
        for(int i = a; i < st-1; i++){
            elementi[i] = elementi[i+1];
        }
        elementi[st-1] = 0;
    }
    
    public void shiftR(int a){
        for(int i = st-1; i > a; i--){
            elementi[i] = elementi[i-1];
        }
    }
    
    public int prvi(){
        return elementi[0];
    }
    
    public String izpis(){
        int a = 1;
        clen t = naslednji;
        while(t != null){
            t = t.naslednji;
            a++;
        }
        return "" + a + "\n" +izpis2();
    }
    
    public String izpis2(){
        String s = "";
        for(int i = 0; i < st; i++){
            if(elementi[i] == 0){
                s += "NULL,";
            }else{
                s += elementi[i] + ",";
            }
        }
        s = s.substring(0, s.length()-1);
        
        if(naslednji != null){
            s += "\n" + naslednji.izpis2();
        }
        return s;
    }
    
}
