/*
       Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

//Εισαγωγή των απαραίτητων πακέτων

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
 
public class Play extends JFrame {
    
    //Όλα τα στοιχεία που απαρτίζουν το γραφικό σώμα της εφαρμογής
    private final JMenuBar menuBar;
    private final JMenu menu,tools;
    private final JMenuItem newGame, endGame, changeProfile, settings, 
            searchWordFile, exit;
    
    private final JMenuItem help,about;
    private final JPanel infoPanel, mainCenterPanel, selectionandProgressPanel,
            gamePanel, selectionsPanel, progressPanel, messagePanel;
    
    private JLabel infoLabel;
    
    private final int gameRows = 8;
    private final int gameCols = 8;
    private final int NUM = gameRows * gameCols;
    private JPanel[] charPanels = new JPanel[NUM];
    
    Play(Profile profile,int StartGame){
        
        this.setTitle("Το Μονοπάτι των Λέξεων"); //Όνομα εφαρμογής -- work in progress
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        this.setSize(800,800);
        this.setLayout(new BorderLayout(10,10));
        
        //Προσθήκη του logo της εφαρμογής
        ImageIcon image = new ImageIcon("images/logo.png"); //via https://www.freepik.com
        this.setIconImage(image.getImage());
   
        //Δημιουργία των Panel
        infoPanel= new JPanel();
        mainCenterPanel = new JPanel();
        gamePanel = new JPanel();
        selectionandProgressPanel = new JPanel();
        selectionsPanel= new JPanel();
        progressPanel = new JPanel();
        messagePanel = new JPanel();
        
        //Ορισμούς του layout κάθε panel
        mainCenterPanel.setLayout(new GridLayout(1,2));
        selectionandProgressPanel.setLayout(new GridLayout(2,1));
        gamePanel.setLayout(new GridLayout(gameRows,gameCols,6,6));
        infoPanel.setLayout(new BorderLayout());
        selectionsPanel.setLayout(new BorderLayout());
        progressPanel.setLayout(new BorderLayout());
        messagePanel.setLayout(new BorderLayout());
        
        //Προσωρινή προσθήκη label στα panel για να αναγνωρίζουμε την λειτουργία
        //τους
        infoLabel=new JLabel("Νίκες: "+profile.showWins()+"  Ήττες: "+profile.showLosses()
            +"  Σερί: "+profile.showStreak());
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setVerticalAlignment(JLabel.CENTER);
        JLabel label2=new JLabel("GAME");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        JLabel label3=new JLabel("SELECTIONS");
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setVerticalAlignment(JLabel.CENTER);
        JLabel label4=new JLabel("PROGRESS");
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setVerticalAlignment(JLabel.CENTER);
        JLabel label5=new JLabel("MESSAGES");
        label5.setHorizontalAlignment(JLabel.CENTER);
        label5.setVerticalAlignment(JLabel.CENTER);
        
        //Προσωρινή προσθήκη διακριτικών label στα panel
        infoPanel.add(infoLabel);
       // gamePanel.add(label2);
        selectionsPanel.add(label3);
        progressPanel.add(label4);
        messagePanel.add(label5);
        
         // Ορισμός χρώματος και μεγέθους στα panel για διακριτικούς λόγους

        //info Panel
        infoPanel.setBackground(Color.yellow);
        infoPanel.setPreferredSize(new Dimension(100,40));
        
        //mainCenterPanel Panel ( Υπερ panel των game Panel και selectionandProgress Panel )
        mainCenterPanel.setPreferredSize(new Dimension(100,40));
        
        //message Panel
        messagePanel.setBackground(Color.MAGENTA);
        messagePanel.setPreferredSize(new Dimension(100,40));

        //Game Panel
        //gamePanel.setBackground(Color.red);
        gamePanel.setPreferredSize(new Dimension(600,40));
        
        //selectionandProgress Panel ( Υπερ panel των selection panel και progress panel )
        selectionandProgressPanel.setPreferredSize(new Dimension(600,40));
        
        //Selection Panel
        selectionsPanel.setBackground(Color.green);
        selectionsPanel.setPreferredSize(new Dimension(100,40));
        
        //Progress Panel
        progressPanel.setBackground(Color.cyan);
        progressPanel.setPreferredSize(new Dimension(100,40));
        
        
       /* // Panel border
        infoPanel.setBorder(br);
        gamePanel.setBorder(br);
        selectionsPanel.setBorder(br);
        progressPanel.setBorder(br);
        */
      
       
        //Εισαγωγή των βασικών πάνελ στο JFrame
        this.add(infoPanel,BorderLayout.NORTH);
        this.add(mainCenterPanel);
        this.add(messagePanel,BorderLayout.SOUTH);
        
        //Εισαγωγή των υπο πάνελ στο master panel τους
        mainCenterPanel.add(gamePanel);
        mainCenterPanel.add(selectionandProgressPanel);
        selectionandProgressPanel.add(selectionsPanel);
        selectionandProgressPanel.add(progressPanel);
        
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
  
        //this.pack();
        this.setVisible(true);
        
        if(StartGame==1){
            NewGame();
        }
    }
    
    public void NewGame(){
        
         Border br = BorderFactory.createLineBorder(Color.black);
         
        for(int i=0; i < NUM; i++){
            
            charPanels[i] = new JPanel();
            charPanels[i].setBorder(br);
            /*
            
                -------- Εισαγωγή κώδικα για χαρακτήρες ------------
            
            */
            gamePanel.add(charPanels[i]);
        }
        
    }
}
