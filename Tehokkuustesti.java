/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietokanta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author Joona
 */
public class Tehokkuustesti {

public Tehokkuustesti() {
    
}

public void Tehokkuustesti() throws SQLException {
    
    Connection db = DriverManager.getConnection("jdbc:sqlite:tehokkuus.db");
    testiTiedostonLuominen();
    
    
    long alku = System.currentTimeMillis();
    
    Statement s = db.createStatement();
    s.execute("BEGIN TRANSACTION");
 
    PreparedStatement paikat = db.prepareStatement("INSERT INTO Paikat(nimi) VALUES (?)");
    
    for (int i = 0; i < 1000; i++) {
    paikat.setInt(1,i);
    paikat.executeUpdate();
    }
    
    PreparedStatement asiakkaat = db.prepareStatement("INSERT INTO Asiakkaat(nimi) VALUES(?)");
    
    for (int i = 0; i < 1000; i++) {
    asiakkaat.setInt(1, i);
    asiakkaat.executeUpdate();
    }
    
    PreparedStatement omistajat = db.prepareStatement("INSERT INTO Omistajat(asiakas_id, paketti_id) VALUES(?,?)");
    int j = 0;
    for (int i = 0; i < 1000; i++) {
        j++;
    omistajat.setInt(1,i);
    omistajat.setInt(2,j);
    omistajat.executeUpdate();
    }
    
    PreparedStatement paketit = db.prepareStatement("INSERT INTO Paketit(paketti_id) VALUES(?)");
    for (int i = 0; i < 1000; i++) {
    paketit.setInt(1, i);
    paketit.executeUpdate();
    }
    
    
    PreparedStatement tapahtumat = db.prepareStatement("INSERT INTO Tapahtumat(seurantakoodi, paikka, kuvaus, lisayshetki) VALUES(?,?,?,?)");
    
    for (int i = 0; i < 1000000; i++) {
    tapahtumat.setInt(1, i);
    tapahtumat.setInt(2, i);
    tapahtumat.setInt(3, i);
    tapahtumat.setInt(4, i);
    }
    
    
    
    
    s.execute("COMMIT");
    
    long loppu = System.currentTimeMillis();
    long arvo = loppu - alku;
    System.out.println("Indeksien kanssa: " + arvo + "ms");
    
    db.close();
    
    indeksitonSuoritus();
    
}

public void testiTiedostonLuominen() {
    
     
    try {
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:tehokkuus.db");
        
        // Luodaan testitiedosto indekseillä
        
        Statement s = db.createStatement();
        s.execute("CREATE TABLE Paikat (id INTEGER PRIMARY KEY, nimi TEXT)");
        
        Statement a = db.createStatement();
        a.execute("CREATE TABLE Asiakkaat (id INTEGER PRIMARY KEY, nimi TEXT, asiakas_id INTEGER)");
        
        Statement b = db.createStatement();
        b.execute("CREATE TABLE Omistajat (id INTEGER PRIMARY KEY, asiakas_id INTEGER, paketti_id INTEGER)");
       
        Statement c = db.createStatement();
        c.execute("CREATE TABLE Paketit (id INTEGER PRIMARY KEY, paketti_id INTEGER)");
      
        
        Statement d = db.createStatement();
        d.execute("CREATE TABLE Tapahtumat (id INTEGER PRIMARY KEY, seurantakoodi TEXT, paikka TEXT, kuvaus TEXT, lisayshetki TEXT)");
        
        
     db.close();
     
     
     
} catch (SQLException e) {
        System.out.println("Jotain meni vikaan");
}   
}

public void ilmanIndekseja() {
    
    try {
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:indeksiton.db");
        
      
        
        
        // Luodaan testitiedosto ilman indeksejä
        
        Statement s = db.createStatement();
        s.execute("CREATE TABLE Paikat (nimi TEXT)");
        
        Statement a = db.createStatement();
        a.execute("CREATE TABLE Asiakkaat (nimi TEXT, asiakas_id INTEGER)");
        
        Statement b = db.createStatement();
        b.execute("CREATE TABLE Omistajat (asiakas_id INTEGER, paketti_id INTEGER)");
        
        
        Statement c = db.createStatement();
        c.execute("CREATE TABLE Paketit (paketti_id INTEGER)");
        
       
        
        Statement d = db.createStatement();
        d.execute("CREATE TABLE Tapahtumat (seurantakoodi TEXT, paikka TEXT, kuvaus TEXT, lisayshetki TEXT)");
        
        
     db.close();
     
     
     
} catch (SQLException e) {
        System.out.println("Jotain meni vikaan");
}

    
    
}

public void indeksitonSuoritus() throws SQLException {
    
    Connection db = DriverManager.getConnection("jdbc:sqlite:indeksiton.db");
    ilmanIndekseja();
    
    
    long alku = System.currentTimeMillis();
    
    Statement s = db.createStatement();
    s.execute("BEGIN TRANSACTION");
 
    PreparedStatement paikat = db.prepareStatement("INSERT INTO Paikat(nimi) VALUES (?)");
    
    for (int i = 0; i < 1000; i++) {
    paikat.setInt(1,i);
    paikat.executeUpdate();
    }
    
    PreparedStatement asiakkaat = db.prepareStatement("INSERT INTO Asiakkaat(nimi) VALUES(?)");
    
    for (int i = 0; i < 1000; i++) {
    asiakkaat.setInt(1, i);
    asiakkaat.executeUpdate();
    }
    
    PreparedStatement omistajat = db.prepareStatement("INSERT INTO Omistajat(asiakas_id, paketti_id) VALUES(?,?)");
    int j = 0;
    for (int i = 0; i < 1000; i++) {
        j++;
    omistajat.setInt(1,i);
    omistajat.setInt(2,j);
    omistajat.executeUpdate();
    }
    
    PreparedStatement paketit = db.prepareStatement("INSERT INTO Paketit(paketti_id) VALUES(?)");
    for (int i = 0; i < 1000; i++) {
    paketit.setInt(1, i);
    paketit.executeUpdate();
    }
    
    
    PreparedStatement tapahtumat = db.prepareStatement("INSERT INTO Tapahtumat(seurantakoodi, paikka, kuvaus, lisayshetki) VALUES(?,?,?,?)");
    
    for (int i = 0; i < 1000000; i++) {
    tapahtumat.setInt(1, i);
    tapahtumat.setInt(2, i);
    tapahtumat.setInt(3, i);
    tapahtumat.setInt(4, i);
    }
    
    
    s.execute("COMMIT");
    
    long loppu = System.currentTimeMillis();
    long arvo = loppu - alku;
    System.out.println("Indeksitön: " + arvo + "ms");
    
    db.close();
    
}
    
   




}
