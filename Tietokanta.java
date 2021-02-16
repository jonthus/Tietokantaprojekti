/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietokanta;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Joona
 */
public class Tietokanta {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        // Alustetaan instanssit
        
        Toiminnot luokka = new Toiminnot();
        Tehokkuustesti testi = new Tehokkuustesti();
        
        
        // Toiminnan valinta:
        
        Scanner lukija = new Scanner(System.in);
        
        while (true) {
        
        System.out.println("Valitse toiminto (1-9): ");
        String toiminto = lukija.nextLine();
        
        // Toiminto 1. Tietokannan luominen:
  
            
        if (toiminto.equals("1")) {  
        luokka.Ensimmainen();
        }
        
        // Toiminto 2. Uuden paikan lisääminen
 
        
        if (toiminto.equals("2")) {
        luokka.Toinen();
        }
        // Toiminto 3. Uuden asiakkaan lisääminen
       
        
        if (toiminto.equals("3")) {
        luokka.Kolmas();
        }
        
        // Toiminto 4. Paketin seurantakoodin lisääminen asiakkaalle
        
        if (toiminto.equals("4")) {
        luokka.Neljas();               
        }
        
        // Toiminto 5. 
        
        if (toiminto.equals("5")) {
        luokka.Viides();
        }
        
        // Toiminto 6.
        
        if (toiminto.equals("6")) {
        luokka.Kuudes();    
        }
        
        // Toiminto 7.
        
        if (toiminto.equals("7")) {
        luokka.Seitsemas();    
        }
        
        // Toiminto 8.
        
        if (toiminto.equals("8")) {
        luokka.Kahdeksas();    
   
        }
            
        // Toiminto 9. Tehokkuustesti
            
            
            
        
        
        if (toiminto.equals("9")) {
        testi.Tehokkuustesti();  
        }
        
        
        }    
    }
}
       
        
        
        
        
      
  

    


    


    
