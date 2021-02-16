/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietokanta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Joona
 */
public class Toiminnot {
    
    
public Toiminnot() {
    
}    
    
    

public void Ensimmainen() {    
    
    // 1. Tietokannan luominen
    
    try {
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        
        // Paikat
        
        Statement s = db.createStatement();
        
        s.execute("CREATE TABLE Paikat (id INTEGER PRIMARY KEY, nimi TEXT)");
        s.execute("INSERT INTO Paikat (nimi) VALUES ('Varasto')");
        s.execute("INSERT INTO Paikat (nimi) VALUES ('Satama')");
        s.execute("INSERT INTO Paikat (nimi) VALUES ('Rahtilaiva')");
        s.execute("INSERT INTO Paikat (nimi) VALUES ('Lentokone')");
        s.execute("INSERT INTO Paikat (nimi) VALUES ('Katuoja')");
        
       
        System.out.println("Tietokanta on luotu seuraavilla arvoilla:");
        System.out.println("");
        
        // Asiakkaat
        
        Statement a = db.createStatement();
        
        a.execute("CREATE TABLE Asiakkaat (id INTEGER PRIMARY KEY, nimi TEXT)");
        a.execute("INSERT INTO Asiakkaat (nimi) VALUES ('Matti')");
        a.execute("INSERT INTO Asiakkaat (nimi) VALUES ('Mikko')");
        a.execute("INSERT INTO Asiakkaat (nimi) VALUES ('Mervi')");
        a.execute("INSERT INTO Asiakkaat (nimi) VALUES ('Matilda')");
        a.execute("INSERT INTO Asiakkaat (nimi) VALUES ('Mirko')");
        
        // Paketit
        
        
        Statement p = db.createStatement();
        
        p.execute("CREATE TABLE Paketit (id INTEGER PRIMARY KEY, seurantakoodi TEXT)");
        p.execute("INSERT INTO Paketit (seurantakoodi) VALUES ('C00000120')");
        p.execute("INSERT INTO Paketit (seurantakoodi) VALUES ('C00000125')");
        p.execute("INSERT INTO Paketit (seurantakoodi) VALUES ('C00000126')");
        p.execute("INSERT INTO Paketit (seurantakoodi) VALUES ('C00000127')");
        p.execute("INSERT INTO Paketit (seurantakoodi) VALUES ('C00000130')");
        
        // Pakettien omistajat
       
        
        Statement o  = db.createStatement();
       
        o.execute("CREATE TABLE Omistajat (id INTEGER PRIMARY KEY, Asiakas_id INTEGER, Paketti_id INTEGER)");
        o.execute("INSERT INTO Omistajat (Asiakas_id,Paketti_id) VALUES (1,3)");
        o.execute("INSERT INTO Omistajat (Asiakas_id,Paketti_id) VALUES (1,4)");
        o.execute("INSERT INTO Omistajat (Asiakas_id,Paketti_id) VALUES (2,1)");
        o.execute("INSERT INTO Omistajat (Asiakas_id,Paketti_id) VALUES (3,2)");
        o.execute("INSERT INTO Omistajat (Asiakas_id,Paketti_id) VALUES (4,5)");
        
        // Tapahtumat   
        
        Statement q = db.createStatement();
        
        q.execute("CREATE TABLE Tapahtumat (id INTEGER PRIMARY KEY, Seurantakoodi TEXT, Paikka TEXT, Kuvaus TEXT, Lisayshetki DATETIME)");
        q.execute("INSERT INTO Tapahtumat (Seurantakoodi,Paikka,Kuvaus,Lisayshetki) VALUES ('C00000120', 'Satama', 'Odottaa rahtia', CURRENT_TIMESTAMP)");
        q.execute("INSERT INTO Tapahtumat (Seurantakoodi,Paikka,Kuvaus,Lisayshetki) VALUES ('C00000125', 'Satama', 'Odottaa rahtia', CURRENT_TIMESTAMP)");
        q.execute("INSERT INTO Tapahtumat (Seurantakoodi,Paikka,Kuvaus,Lisayshetki) VALUES ('C00000126', 'Rahtilaiva', 'Ei kuvausta', CURRENT_TIMESTAMP)");
        q.execute("INSERT INTO Tapahtumat (Seurantakoodi,Paikka,Kuvaus,Lisayshetki) VALUES ('C00000127', 'Lentokone', 'Odottaa rahtia', CURRENT_TIMESTAMP)");
        q.execute("INSERT INTO Tapahtumat (Seurantakoodi,Paikka,Kuvaus,Lisayshetki) VALUES ('C00000130', 'Katuoja', 'Valmiina kuljetukseen', CURRENT_TIMESTAMP)");
        

            
       
        ResultSet s1 = s.executeQuery("SELECT * FROM Paikat");
        while (s1.next()) {
            System.out.println(s1.getInt("id")+" "+s1.getString("nimi"));
        }
        
        System.out.println("");
        
        ResultSet b = a.executeQuery("SELECT * FROM Asiakkaat");
        while (b.next()) {
            System.out.println(b.getInt("id")+" "+b.getString("nimi"));
        }
        
        System.out.println("");
        
        ResultSet d = p.executeQuery("SELECT * FROM Paketit");
        while (d.next()) {
            System.out.println(d.getInt("id")+" "+d.getString("seurantakoodi"));
        }
        
        System.out.println("");
        
        ResultSet c = o.executeQuery("SELECT * FROM Omistajat");
        while (c.next()) {
            System.out.println(c.getInt("id")+" "+c.getInt("Asiakas_id")+" "+c.getInt("Paketti_id"));
        }
        
        System.out.println("");
        
        ResultSet r1 = q.executeQuery("SELECT * FROM Tapahtumat");
        while (r1.next()) {
            System.out.println(r1.getInt("id")+" "+r1.getString("seurantakoodi")+" "+r1.getString("Paikka")+" "+r1.getString("Kuvaus"));
        }
        
        
        System.out.println("");
        
        db.close();
        
        
        } catch (Exception e) {
            System.out.println("Tietokanta on jo olemassa.");
        }
        }

public void Toinen() {
    
     try {
            
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        
        Scanner paikanNimi = new Scanner(System.in);
        System.out.println("Anna paikan nimi: ");
        String nimi = paikanNimi.nextLine();
        
        // Tarkistus
        
        String queryCheck = "SELECT COUNT(*) FROM Paikat WHERE nimi = ?";
        PreparedStatement ps = db.prepareStatement(queryCheck);
        ps.setString(1, nimi);
        ResultSet resultSet = ps.executeQuery();
        int n = 0;
        
        if (resultSet.next()) {
            n = resultSet.getInt(1);
        } 
        
        // Tuotteen lisääminen
        
        if (n == 0) {
        
        PreparedStatement h = db.prepareStatement("INSERT INTO Paikat(nimi) VALUES (?)");
        h.setString(1,nimi);
        h.executeUpdate();
       
        System.out.println("Tuote lisätty.");
        
       
        } else {
            System.out.println("Tuote on jo olemassa.");
        }   
        
        db.close();  
        
        } catch (Exception e) {
            System.out.println("Tuotteen lisääminen epäonnistui.");
        }
        }

    
public void Kolmas() throws SQLException {
    
     try {
                
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        
        Scanner asiakkaanNimi = new Scanner(System.in);
        System.out.println("Anna asiakkaan nimi: ");
        String asiakasNimi = asiakkaanNimi.nextLine();
        
        
        // Tarkistus
        
        String queryCheck = "SELECT COUNT(*) FROM Asiakkaat WHERE nimi = ?";
        PreparedStatement ps = db.prepareStatement(queryCheck);
        ps.setString(1, asiakasNimi);
        ResultSet resultSet = ps.executeQuery();
        int n = 0;
        
        if (resultSet.next()) {
            n = resultSet.getInt(1);
        } 
        
        // Tuotteen lisääminen
        
        if (n == 0) {
        
            
        PreparedStatement h = db.prepareStatement("INSERT INTO Asiakkaat(nimi) VALUES (?)");
        h.setString(1,asiakasNimi);
        h.executeUpdate();
        System.out.println("Asiakas lisätty.");
        
      
     
        } else {
            System.out.println("Tuote on jo olemassa.");
        }
        db.close();
      
        
        } catch (Exception e) {
            System.out.println("Asiakkaan lisääminen epäonnistui.");
} 
}

public void Neljas() throws SQLException {
    
    
     try {
                
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        
        Scanner luettava = new Scanner(System.in);
        System.out.println("Anna paketin seurantakoodi: ");
        String seurantaKoodi = luettava.nextLine();
        System.out.println("Anna asiakkaan nimi: ");
        String asiakkaanNimi = luettava.nextLine();
        
        // Asiakkaiden määrän tarkistus
        
        String queryCheck = "SELECT COUNT(*) FROM Asiakkaat WHERE nimi = ?";
        PreparedStatement ps = db.prepareStatement(queryCheck);
        ps.setString(1, asiakkaanNimi);
        ResultSet resultSet = ps.executeQuery();
        int asiakkaidenMaara = 0;
        
        if (resultSet.next()) {
            asiakkaidenMaara = resultSet.getInt(1);
        } 
        
        
        // Tuotteen lisääminen
        
        if (asiakkaidenMaara == 1) {
        PreparedStatement s = db.prepareStatement("INSERT INTO Omistajat(Asiakas_id) VALUES (?)");
        s.setString(1,asiakkaanNimi);
        s.executeUpdate();
        }
        
        
        
        PreparedStatement h = db.prepareStatement("INSERT INTO Paketit(seurantakoodi) VALUES (?)");
        h.setString(1,seurantaKoodi);
        h.executeUpdate();
        
        PreparedStatement i = db.prepareStatement("INSERT INTO Omistajat(Paketti_id) VALUES (?)");
        i.setString(1,seurantaKoodi);
        i.executeUpdate();
        
      
        db.close();
        
        System.out.println("Paketti lisätty.");
        System.out.println("Asiakas lisätty.");
        
        } catch (Exception e) {
                System.out.println("Paketin lisääminen epäonnistui.");
                System.out.println("Asiakkaan lisääminen epäonnistui.");  
            } 
            }   


public void Viides() throws SQLException {
    
    try {
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        
        
        Scanner lukija = new Scanner(System.in);
        System.out.println("Anna paketin seurantakoodi: ");
        String koodi = lukija.nextLine();
        
        System.out.println("Anna tapahtuman paikka: ");
        String paikka = lukija.nextLine();
        
        System.out.println("Anna tapahtuman kuvaus: ");
        String kuvaus = lukija.nextLine();
        
      
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
       
        
        
        Statement s = db.createStatement();
        s.execute("BEGIN TRANSACTION");
        PreparedStatement vs = db.prepareStatement("INSERT INTO Tapahtumat(seurantakoodi, paikka, kuvaus, aika) VALUES (?,?,?,?)");
        vs.setString(1, koodi);
        vs.setString(2, paikka);
        vs.setString(3, kuvaus);
        vs.setTimestamp(4, date);
        vs.executeUpdate();
        s.execute("COMMIT");
            
        System.out.println("toimiiko");
        
        db.close();
        
    } catch (SQLException e) {
        System.out.println("rikki");
    }
    
    
    
}

public void Kuudes() throws SQLException {
    
    try {
    
    Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
    
    Scanner lukija = new Scanner(System.in);
    
    System.out.println("Millä seurantakoodilla haetaan?");
    String seurantakoodi = lukija.nextLine();
    
    
    PreparedStatement ps = db.prepareStatement("SELECT * FROM Tapahtumat WHERE seurantakoodi=?");
    ps.setString(1,seurantakoodi);
    ResultSet rs = ps.executeQuery();
    
    if (!rs.next()) {
        System.out.println("Seurantakoodia ei ole olemassa.");
    } else {
            System.out.println(rs.getInt("id"));
                               
        }
    
    } catch (SQLException e) {
        System.out.println("Seurantakoodia ei ole olemassa.");
    }
}
    
public void Seitsemas() {
    
    try {
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        
        System.out.println("Syötä asiakkaan nimi: ");
        Scanner lukija = new Scanner(System.in);
        String nimi = lukija.nextLine();
        
        PreparedStatement ps = db.prepareStatement("SELECT Asiakkaat.nimi, COUNT(tapahtumat.id)\n" +
                                                   "FROM Asiakkaat\n" +
                                                   "JOIN Omistajat ON Omistajat.asiakas_id = Asiakkaat.id\n" +
                                                   "JOIN Tapahtumat ON Omistajat.paketti_id = Seurantakoodi\n" +
                                                   "WHERE Asiakkaat.nimi=?");
        
        
    } catch (Exception e) {
        System.out.println("Asiakasta ei löydy.");
    }
    
    
    
}

public void Kahdeksas() {
        
    try {
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        Scanner luettava = new Scanner(System.in);
        System.out.println("Mistä paikasta haetaan tapahtumien määrä?");
        String paikka = luettava.nextLine();
        
        PreparedStatement ps = db.prepareStatement("SELECT * FROM Tapahtumat JOIN Paikat ON Paikat.id=Tapahtumat.paikka_id WHERE paikka=?");
        ps.setString(1,paikka);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            System.out.println("Minkä päivän tapahtumat?");
            String aika = luettava.nextLine();
            
            ps = db.prepareStatement("SELECT COUNT(Tapahtumat.id) FROM Tapahtumat WHERE aika=?");
            ps.setString(1, aika);
            rs = ps.executeQuery();
            
            if (!rs.next()) {
                System.out.println("Ei tapahtumia tänä päivänä.");
            } else {
                System.out.println("Paikka: " + paikka);
                System.out.println("Tapahtumien määrä: ");
                
                while (rs.next()) {
                    int maara = rs.getInt("COUNT(tapahtumat.id)");
                    System.out.println(maara);
                }
            }
        } else {
            System.out.println("Paikkaa ei ole olemassa.");
        }
} catch (Exception e) {
        System.out.println("Haku epäonnistui.");
}

}

}







