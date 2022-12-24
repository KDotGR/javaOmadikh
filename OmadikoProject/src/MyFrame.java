/*
      Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.*;
 
public class MyFrame extends JFrame {
    
    JMenuBar menuBar;
    
    JMenu menu;
    JMenu tools;
    
    JMenuItem newGame;
    JMenuItem endGame;
    JMenuItem changeProfile;
    JMenuItem settings;
    JMenuItem searchWordFile;
    JMenuItem exit;
    
    JMenuItem help;
    JMenuItem about;
    
    MyFrame(){
        
        //Δημιουργία του Menu Bar
        menuBar = new JMenuBar();
        
        //Δημιουργία του Μενού
        menu = new JMenu("Μενού");
        ImageIcon settingsImage = new ImageIcon("images/menu.png"); //via https://www.flaticon.com/authors/mayor-icons
        menu.setIcon(settingsImage);
        
        //Επιλογή Νέο Παιχνίδι
        newGame = new JMenuItem("Νέο Παιχνίδι",new ImageIcon("images/replay.png")); //via https://www.flaticon.com/authors/pixel-perfect
        menu.add(newGame);
        
        //Επιλογή Ακύρωση/Τερματισμός παιχνιδιού
        endGame = new JMenuItem("Ακύρωση/Τερματισμός παιχνιδιού",new ImageIcon("images/terminategame.png")); //via https://www.flaticon.com/authors/sumberrejeki
        menu.add(endGame);
        
         //Επιλογή Αλλαγή Προφίλ
        changeProfile = new JMenuItem("Αλλαγή Προφίλ",new ImageIcon("images/user.png")); //via https://www.freepik.com
        menu.add(changeProfile);
        
         //Επιλογή Ρυθμίσεις
        settings = new JMenuItem("Ρυθμίσεις",new ImageIcon("images/settings.png")); //via https://www.flaticon.com/authors/juicy-fish
        menu.add(settings);
        
        //Επιλογή Αναζήτηση αρχείου λέξεων
        searchWordFile = new JMenuItem("Αναζήτηση αρχείου λέξεων",new ImageIcon("images/search.png")); //via https://www.flaticon.com/authors/dimitry-miroliubov
        menu.add(searchWordFile);
        
        //Επιλογή Αναζήτηση αρχείου λέξεων
        exit = new JMenuItem("Έξοδος",new ImageIcon("images/exit.png")); //via https://www.flaticon.com/authors/wr-graphic-garage
        menu.add(exit);
        
        //Προσθήκη του Μενού στο menu bar
        menuBar.add(menu);
        this.setJMenuBar(menuBar); 
        
        
        
        //Δημιουργία του Μενού Εργαλεία
        tools = new JMenu("Εργαλεία");
        
        //Επιλογή Βοήθεια
        help = new JMenuItem("Βοήθεια",new ImageIcon("images/help.png")); //via  https://www.flaticon.com/authors/ilham-fitrotul-hayat
        tools.add(help);
        
        //Επιλογή Πληροφορίες
        about = new JMenuItem("Πληροφορίες",new ImageIcon("images/info.png")); //via  https://www.flaticon.com/authors/roundicons
        tools.add(about);
        
        menuBar.add(tools);
        this.setJMenuBar(menuBar);
   
        this.setTitle("Το Μονοπάτι των Λέξεων"); //Όνομα εφαρμογής -- work in progress
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800,800);
        this.setVisible(true);
        
        //Προσθήκη του logo της εφαρμογής
        ImageIcon image = new ImageIcon("images/logo.png"); //via https://www.freepik.com
        this.setIconImage(image.getImage());
       
    }
}
