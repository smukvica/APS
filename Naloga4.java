import java.io.*;
import java.util.Scanner;

public class Naloga4{
     public static void main(String[] args) throws IOException{
        
            //String dat = args[0];
            //BufferedReader br = null;
            //File vhod = new File(dat);
            //br = new BufferedReader(new FileReader(vhod));
            Scanner sc = new Scanner(System.in);
            //int stUkazov = Integer.parseInt(br.readLine());
            Naloga4 list = new Naloga4();
            int count = 0;
            int stUkazov = sc.nextInt();
            while(stUkazov != 0){
                
                String preberi = sc.nextLine();
                String[] vrstica =  preberi.split(",");
                String ukaz = vrstica[0];

                switch(ukaz){
                    case "s": 
                        int velikost = Integer.parseInt(vrstica[1]);     
                        list.addFirst(velikost);
                         break;
                         
                    case "i":
                        int vrednost = Integer.parseInt(vrstica[1]);
                        int pozicija = Integer.parseInt(vrstica[2]);
                        list.insert(vrednost, pozicija);
                        //System.out.println("Tabele:"+count);
                        count++;
                        //list.tabele();
                        break;
                        
                    case "r":
                        int poz = Integer.parseInt(vrstica[1]);
                        //System.out.println("Tabele:"+count);
                        count++;
                        
                        list.remove(poz);
                        //list.tabele();
                }
                 stUkazov--;
            }
            //System.out.println(list.size);
            list.tabele();
            
            //br.close();
            
            //int tabelaZasedenosti[] = list.zasedenost();
            //int[] izpis = new int[velikost+1];
            
            /*for(int i = 0;i<tabelaZasedenosti.length;i++){
                   izpis[(velikost-tabelaZasedenosti[i])]++;
            }*/
            
         /*
         //TESTIRANJE
         Naloga4 list = new Naloga4();
         
         list.addFirst(5);
         boolean test = list.insert(7, 0);
         list.insert(3, 1);
         list.insert(4, 0);
         list.insert(2,3);
         list.insert(1, 4);
         list.insert(5, 3);
         list.insert(8, 2);
         list.remove(0);
         list.remove(1);
         list.remove(3);
         list.remove(1);
         //System.out.println("Je nrdilo ? "+test);
         
        list.tabele();
         */
     }

Node root;
int velikost;
int size = 0;
public Naloga4(){
    root = new Node();
}

public void tabele(){
    Node thisNode = this.root;
    
    
    while(thisNode != null){
        //System.out.println("Tabela:");
        int[] tabela = thisNode.getPolje();
        for(int i = 0; i < tabela.length;i++){
            if(i == tabela.length-1){
                if(tabela[i] == 0)
                    System.out.print("NULL");
                else
                    System.out.print(tabela[i]);
            }
            else{
                if(tabela[i]==0)
                    System.out.print("NULL,");
                else
                    System.out.print(tabela[i]+",");
            }
        }
        thisNode = thisNode.getNaslednji();
        System.out.println("");
    }
    
}

public Node addFirst(int space){
    Node newNode = new Node(space,root);
    this.root = newNode;
    velikost = space;
    size++;
    return newNode;
}

public boolean remove(int pozicija){
    
    Node thisNode = this.root;
    Node naslednji = thisNode.getNaslednji();
    while(pozicija > thisNode.getZaseden()){
        //System.out.println("Tale je zaseden "+thisNode.getZaseden());
        pozicija -= thisNode.getZaseden();
        thisNode = thisNode.getNaslednji(); 
        naslednji = thisNode.getNaslednji();
        //System.out.println("Tle je pozicija "+pozicija);
    }
    boolean rezanje = thisNode.odstraniVrednost(pozicija);
    //System.out.println("Rezanje"+rezanje);
    
    if(rezanje == false){
        int temp[] = naslednji.getPolje();
        //System.out.println(temp[1]);
        for(int i = 0; i < temp.length; i++){
            if(temp[i] == 0)
                break;
            thisNode.dodajVrednost(temp[i], thisNode.zaseden);
            //System.out.println("Dodamo "+temp[i]);
        }
        if(thisNode.getNaslednji() == null)
            naslednji.setNaslednji(null);
        else
            naslednji.setNaslednji(thisNode.getNaslednji());
        size--;
    }
    
    else if(rezanje == true)
        return true;
    
    
    return false;
}

public boolean insert(int vrednost, int pozicija){
    Node thisNode = this.root;
    int vstavljanje = pozicija;

    while(vstavljanje > thisNode.getZaseden()){
        //System.out.println("Tale je zaseden "+thisNode.getZaseden());
        vstavljanje -= thisNode.getZaseden();
        thisNode = thisNode.getNaslednji();    
    }
    if(thisNode.getFree() > 0){
        //System.out.println(thisNode.getFree());
        //System.out.println("Vstavljamo "+vrednost+" na pozicijo "+vstavljanje);
        thisNode.dodajVrednost(vrednost, vstavljanje);
        return true;
    }
   
    if(thisNode.getFree() <= 0){
        int[] tabelaClena = thisNode.getPolje();
        

        Node newNode = new Node(velikost,thisNode.getNaslednji());
        
        for(int i = velikost; i > (velikost/2); i--){    
            newNode.dodajVrednost(tabelaClena[i-1], 0);
            tabelaClena[i-1] = 0;
        }
        thisNode.setPolje(tabelaClena);
        thisNode.setZaseden(velikost/2);
        
        
        
      
        
        //System.out.println("Dodali smo nov člen");
        //System.out.println("Tabela prvega ");
        for(int i = 0; i < velikost; i++){
            //System.out.println(thisNode.getPolje()[i]);
        }
        //System.out.println("Tabela novega");
        for(int i = 0; i < velikost; i++){
            //System.out.println(newNode.getPolje()[i]);
        }
        
        
        if(thisNode.getNaslednji() == null)
            thisNode.setNaslednji(newNode);
        else{
            newNode.setNaslednji(thisNode.getNaslednji());
            thisNode.setNaslednji(newNode);
        }
        
        size++;
        //System.out.println("Pozicija "+pozicija);
        return insert(vrednost,pozicija);
    }
    
    
    return false;
}

class Node{
    Node nextNode;
    int free;
    int zaseden;
    int tabela[];
    int velikost;
    
    Node(){
    }
    
    Node(int velikost, Node naslednji){
        this.free = velikost;
        this.nextNode = null;
        this.zaseden = 0;
        this.tabela = new int[velikost];
        this.velikost = velikost;
    }
    
    boolean odstraniVrednost(int pozicija){
        
        /*System.out.println("tabela pred:");
        for(int i = 0; i<tabela.length; i++){
             System.out.print(tabela[i]);
        }
        System.out.println("");*/
        tabela[pozicija] = 0;
        free +=1;
        zaseden -= 1;
        int[] temp = tabela;
        //System.out.println("Odstranimo clen na poziciji "+pozicija);
        //int count = 0;
        int swap = 0;
        
        for(int i = 0; i < tabela.length; i++){
            if(temp[i]!=0){
                tabela[swap] = temp[i];
                swap++;  
                }
        }

        if(nextNode != null && zaseden < velikost/2){
            int prvi = nextNode.vrniPrvega();
            dodajVrednost(prvi,zaseden);
            return nextNode.odstraniVrednost(0);
        }
        if(nextNode == null && zaseden < velikost/2){
            //System.out.println("pride sm in vrne false ?");
            tabela[zaseden] = 0;
            return false;
        }
        
        else 
            tabela[zaseden] = 0;
            return true;
        
           
        
       /* System.out.println("Tabela po:");
        for(int i = 0; i<tabela.length; i++){
             System.out.print(tabela[i]);
        }
        System.out.println("");*/

    }

    void dodajVrednost(int vrednost, int pozicija){
        
        if(tabela[pozicija] == 0)
            tabela[pozicija] = vrednost;
        else{
            int temp = tabela[pozicija];
            for(int i = pozicija; i < tabela.length-1; i++){  
                int temp1 = tabela[i+1];
                tabela[i+1] = temp;
                temp = temp1; 
                //System.out.println("Vrednost "+temp+" premaknemo na "+(i+1));     
            }
            tabela[pozicija] = vrednost;
        }
        
        this.free -= 1;
        this.zaseden += 1;
        
       // System.out.println("Vrednost dodana na mesto "+pozicija);
        
        /*for(int i = 0; i<tabela.length;i++){
            System.out.println(tabela[i]);
        }*/
        
    }
    
    int vrniPrvega(){
        return tabela[0];
    }
    void setNaslednji(Node naslednji){
        this.nextNode = naslednji;
    }
    Node getNaslednji(){
        return this.nextNode;
    }
    int getZaseden(){
        return zaseden;
    }
    int getFree(){
        return free;
    }
    void setZaseden(int zaseden){
        this.zaseden=zaseden;
        this.free = velikost - zaseden;
    }
    void setPolje(int[] polje){
        tabela = polje;
    }
    int[] getPolje(){
        return tabela;
    }
    
}
}