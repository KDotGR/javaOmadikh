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
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
 
public final class Play extends JFrame{
    
    static Letter[] letterArray;
    
    private Score score;
    
    //Όλα τα στοιχεία που απαρτίζουν το γραφικό σώμα της εφαρμογής
    private final JMenuBar menuBar;
    private final JMenu menu,tools;
    private final JMenuItem newGame, endGame, changeProfile, settings, 
            searchWordFile, exit;
    
    private final JMenuItem help,about;
    private  JPanel infoPanel, mainCenterPanel,SendButtonSelandProgPanel, selectionandProgressPanel,
            gamePanel, selectionsPanel, progressPanel;
    
    private static JPanel messagePanel;
    
    private static JLabel infoLabel;
    private static JLabel messageLabel;
    private JLabel wordsFoundLabel;
    
    private static JButton sendWord;
    
    private static JButton shuffleLine;
    private static JButton shuffleColumn;
    private static JButton shuffleEverything;
    private static JButton deleteLine;
    private static JButton swapLetters;
    private JLabel shuffleLineLeft;
    private JLabel shuffleColumnLeft;
    private JLabel shuffleEverythingLeft;
    private JLabel deleteLineLeft;
    private JLabel swapLettersLeft;
    
    private static JProgressBar progressBar;
    private static final int gameRows = 8;
    private static final int gameCols = 8;
    private static final int NUM = gameRows * gameCols;
    private static  JPanel[] charPanels;
    private static  JLabel[] charLabels;
    private static  JLabel[] pointLabels;
    
    private static LetterPanel[] letterPanels;
    
    Play(Profile profile,int StartGame){
        
        charPanels = new JPanel[NUM];
        charLabels = new JLabel[NUM];
        pointLabels = new JLabel [NUM];
        letterPanels = new LetterPanel [NUM];
        
        //Βασικά χαρακτηριστικά και λειτουργίες του Frame
        this.setTitle("Το Μονοπάτι των Λέξεων"); //Όνομα εφαρμογής
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); //Ξεκινάει σε fullscreen
       
        this.setSize(800,800);
        this.setLayout(new BorderLayout(10,10));
        
        //Αρχικοποίηση του πίνακα γραμμάτων με τα γράμματα και τις τιμές τους
        InitLetterArray();
        
        //Αρχικοποίηση του ArrayList που αποθηκεύει την λέξη ενόσω την ψάχνει
        //ο χρήστης
        Score.initArrayList();
        
        //Προσθήκη του logo της εφαρμογής
        ImageIcon image = new ImageIcon("images/logo.png"); //via https://www.freepik.com
        this.setIconImage(image.getImage());
   
        //Δημιουργία των Panel
        infoPanel= new JPanel();
        mainCenterPanel = new JPanel();
        gamePanel = new JPanel();
        SendButtonSelandProgPanel = new JPanel();
        selectionandProgressPanel = new JPanel();
        selectionsPanel= new JPanel();
        progressPanel = new JPanel();
        messagePanel = new JPanel();
        
        //Ορισμούς του layout κάθε panel
        mainCenterPanel.setLayout(new GridLayout(1,2));
        //selectionsPanel.setLayout(new GridLayout(5,2));
         selectionsPanel.setLayout(new GridLayout(5,2,9,9));
        SendButtonSelandProgPanel.setLayout(new BorderLayout());
        selectionandProgressPanel.setLayout(new GridLayout(2,1,7,7));
        gamePanel.setLayout(new GridLayout(gameRows,gameCols,6,6));
        infoPanel.setLayout(new BorderLayout());
        progressPanel.setLayout(new BorderLayout());
        messagePanel.setLayout(new BorderLayout());
        
        //Προσωρινή προσθήκη label στα panel για να αναγνωρίζουμε την λειτουργία
        //τους
        infoLabel=new JLabel();
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setVerticalAlignment(JLabel.CENTER);
        
        sendWord = new JButton("Υποβολή"); 
        sendWord.setFocusable(false);
        progressBar = new JProgressBar(0,200);
        progressBar.setStringPainted(true);
        //updateProgressBar(0);
        progressBar.setFont(new Font("Verdana",Font.BOLD,25));
        progressBar.setForeground(Color.CYAN);
        progressBar.setBackground(Color.BLACK);
        
        
        JLabel label4=new JLabel("PROGRESS");
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setVerticalAlignment(JLabel.CENTER);
        messageLabel = new JLabel("Καλωσήλθες στο Μονοπάτι των Λέξεων !");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        
        //Προσωρινή προσθήκη διακριτικών label στα panel
        infoPanel.add(infoLabel);
        //progressPanel.add(wordsFoundLabel);
        SendButtonSelandProgPanel.add(sendWord,BorderLayout.WEST);
        progressPanel.add(progressBar,BorderLayout.SOUTH);
        messagePanel.add(messageLabel);
        
         // Ορισμός χρώματος και μεγέθους στα panel για διακριτικούς λόγους

        //info Panel
        infoPanel.setBackground(Color.yellow);
        infoPanel.setPreferredSize(new Dimension(100,40));
        
        //mainCenterPanel Panel ( Υπερ panel των game Panel και selectionandProgress Panel )
        mainCenterPanel.setPreferredSize(new Dimension(100,40));
        
        //message Panel
        //messagePanel.setBackground(Color.MAGENTA);
        messagePanel.setPreferredSize(new Dimension(100,40));

        //Game Panel
        gamePanel.setPreferredSize(new Dimension(600,40));
        
        //selectionandProgress Panel ( Υπερ panel των selection panel και progress panel )
        selectionandProgressPanel.setPreferredSize(new Dimension(600,40));
        
        //Selection Panel
        selectionsPanel.setBackground(Color.GRAY);
        selectionsPanel.setPreferredSize(new Dimension(100,40));
        
        shuffleLine = new JButton("Αναδιάταξη Γραμμής");
        shuffleLine.setFocusable(false);
        shuffleLine.setFont(new Font("Verdana",Font.BOLD,25));
        shuffleLine.setPreferredSize(new Dimension(100,100));
        shuffleLine.setBorder(BorderFactory.createEmptyBorder(0, 9, 0, 0));
        selectionsPanel.add(shuffleLine);
        
        shuffleLineLeft = new JLabel("0/3");
        shuffleLineLeft.setFont(new Font("Verdana",Font.BOLD,30));
        shuffleLineLeft.setForeground(Color.WHITE);
        selectionsPanel.add(shuffleLineLeft);
        
        shuffleColumn = new JButton("Αναδιάταξη Στήλης");
        shuffleColumn.setFocusable(false);
        shuffleColumn.setFont(new Font("Verdana",Font.BOLD,25));
        shuffleColumn.setMinimumSize(new Dimension(100,100));
        selectionsPanel.add(shuffleColumn);
        
        shuffleColumnLeft = new JLabel("0/3");
        shuffleColumnLeft.setFont(new Font("Verdana",Font.BOLD,30));
        shuffleColumnLeft.setForeground(Color.WHITE);
        selectionsPanel.add(shuffleColumnLeft);
        
        shuffleEverything = new JButton("Αναδιάταξη Ταμπλό");
        shuffleEverything.setFocusable(false);
        shuffleEverything.setFont(new Font("Verdana",Font.BOLD,25));
        shuffleEverything.setPreferredSize(new Dimension(100,100));
        selectionsPanel.add(shuffleEverything);
        
        shuffleEverythingLeft = new JLabel("0/5");
        shuffleEverythingLeft.setFont(new Font("Verdana",Font.BOLD,30));
        shuffleEverythingLeft.setForeground(Color.WHITE);
        selectionsPanel.add(shuffleEverythingLeft);
        
        deleteLine = new JButton("Διαγραφή Γραμμής");
        deleteLine.setFocusable(false);
        deleteLine.setFont(new Font("Verdana",Font.BOLD,25));
        deleteLine.setPreferredSize(new Dimension(100,100));
        selectionsPanel.add(deleteLine);
        
        deleteLineLeft = new JLabel("0/3");
        deleteLineLeft.setFont(new Font("Verdana",Font.BOLD,30));
        deleteLineLeft.setForeground(Color.WHITE);
        selectionsPanel.add(deleteLineLeft);
        
        swapLetters = new JButton("Εναλλαγή Γραμμάτων");
        swapLetters.setFocusable(false);
        swapLetters.setFont(new Font("Verdana",Font.BOLD,25));
        swapLetters.setPreferredSize(new Dimension(100,100));
        selectionsPanel.add(swapLetters);
        
        swapLettersLeft = new JLabel("0/6");
        swapLettersLeft.setFont(new Font("Verdana",Font.BOLD,30));
        swapLettersLeft.setForeground(Color.WHITE);
        selectionsPanel.add(swapLettersLeft);
        
        //Progress Panel
        progressPanel.setBackground(Color.GRAY);
        progressPanel.setForeground(Color.WHITE);
        progressPanel.setPreferredSize(new Dimension(100,40));
        
       
        //Εισαγωγή των βασικών πάνελ στο JFrame
        this.add(infoPanel,BorderLayout.NORTH);
        this.add(mainCenterPanel);
        this.add(messagePanel,BorderLayout.SOUTH);
        
        //Εισαγωγή των υπο πάνελ στο master panel τους
        mainCenterPanel.add(gamePanel);
        mainCenterPanel.setBackground(Color.GRAY);
        mainCenterPanel.add(SendButtonSelandProgPanel);
        SendButtonSelandProgPanel.add(selectionandProgressPanel);
        selectionandProgressPanel.add(selectionsPanel);
        selectionandProgressPanel.add(progressPanel);
        
        
    //---------------------------Δημιουργία του Μενού---------------------------
       
    //Δημιουργία του Menu Bar
        menuBar = new JMenuBar();
        
  
        //Δημιουργία του Μενού
        menu = new JMenu("Μενού");
        ImageIcon settingsImage = new ImageIcon("images/menu.png"); //via https://www.flaticon.com/authors/mayor-icons
        menu.setIcon(settingsImage);
        
        //Επιλογή Νέο Παιχνίδι ------> Ξεκινάει νέο παιχνίδι και περνάει ως ήττα το προηγούμενο
         newGame = new JMenuItem(new AbstractAction("Νέο Παιχνίδι",new ImageIcon("images/replay.png")) { //via https://www.flaticon.com/authors/pixel-perfect
        @Override
        public void actionPerformed(ActionEvent e) {
        ProfileChooser.initGame(profile);
        profile.newLoss();
        updateInfoLabel(profile);
        dispose();
    }
});
        menu.add(newGame);
        
        //Επιλογή Ακύρωση/Τερματισμός παιχνιδιού ------> Ξεκινάει νέο παιχνίδι και περνάει ως ήττα το προηγούμενο
        //endGame = new JMenuItem("Ακύρωση/Τερματισμός παιχνιδιού",new ImageIcon("images/terminategame.png")); //
        endGame = new JMenuItem(new AbstractAction("Ακύρωση/Τερματισμός παιχνιδιού",new ImageIcon("images/terminategame.png")) { //via https://www.flaticon.com/authors/sumberrejeki
        @Override
        public void actionPerformed(ActionEvent e) {
        ProfileChooser.initGame(profile);
        profile.newLoss();
        updateInfoLabel(profile);
        dispose();
    }
});
        menu.add(endGame);
        
         //Επιλογή Αλλαγή Προφίλ
        changeProfile = new JMenuItem(new AbstractAction("Αλλαγή Προφίλ",new ImageIcon("images/user.png")) { //via https://www.freepik.com
        @Override
        public void actionPerformed(ActionEvent e) {
            new ProfileChooser(1);
            profile.newLoss();
            updateInfoLabel(profile);
            dispose();
        }
});
        menu.add(changeProfile);
        
         //Επιλογή Ρυθμίσεις
        settings = new JMenuItem("Ρυθμίσεις",new ImageIcon("images/settings.png")); //via https://www.flaticon.com/authors/juicy-fish
        menu.add(settings);
        
        //Επιλογή Αναζήτηση αρχείου λέξεων
        searchWordFile = new JMenuItem("Αναζήτηση αρχείου λέξεων",new ImageIcon("images/search.png")); //via https://www.flaticon.com/authors/dimitry-miroliubov
        menu.add(searchWordFile);
        
        //Επιλογή Εξόδου από το παιχνίδι
        exit = new JMenuItem(new AbstractAction("Έξοδος",new ImageIcon("images/exit.png")) { //via https://www.flaticon.com/authors/wr-graphic-garage
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
});
        
        menu.add(exit);
        
        //Προσθήκη του Μενού στο menu bar
        menuBar.add(menu);
        
        //Δημιουργία του Μενού Εργαλεία
        tools = new JMenu("Εργαλεία");
        
        //Επιλογή Βοήθεια
        help = new JMenuItem(new AbstractAction("Βοήθεια",new ImageIcon("images/help.png")) { // via  https://www.flaticon.com/authors/ilham-fitrotul-hayat
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,returnHelpString(),"Βοήθεια",JOptionPane.INFORMATION_MESSAGE);
        }
});
        tools.add(help);
        
        //Επιλογή Πληροφορίες
        about = new JMenuItem(new AbstractAction("Πληροφορίες",new ImageIcon("images/info.png")) { // via  https://www.flaticon.com/authors/roundicons
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,returnAboutString(),"Πληροφορίες",JOptionPane.INFORMATION_MESSAGE);
        }
});
        
        tools.add(about);
        
        menuBar.add(tools);
        this.setJMenuBar(menuBar);
        
        this.setBackground(Color.GRAY);
        //this.pack();
        this.setVisible(true);
        wordsFoundLabel =new JLabel();
        
        //Σε δεξί κλικ ακυρώνει την λέξη που είχε πατηθεί έως τώρα
        this.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                if(evt.getButton() == MouseEvent.BUTTON3){
                    displayMessage(0, "");
                    LetterPanel.changePrevButton(-1);
                    Score.resetWord();
                    ResetPanels();
                }
            }
        });
        if(StartGame==1){
           NewGame();
        }
    }
    
    protected void NewGame(){
        
        //Διαμόρφωση πλαισίου πάνελ-γραμμάτων παιχνιδιού
         Border br = BorderFactory.createLineBorder(Color.black);
         
         //Ενημέρωση του Info Label
         updateInfoLabel(ProfileChooser.returnProfile());
         
         FoundWords a = new FoundWords();
         Score.resetBlue();
         
         //Αρχικοποίηση των counter για τις βοήθειες
         RowShuffler.initCounter();
         ColumnShuffler.initCounter();
         ShuffleTable.initCounter();
         LineDeleter.initCounter();
         
         wordsFoundLabel.setFont(new Font("Sans",Font.PLAIN,30));
         wordsFoundLabel.setHorizontalAlignment(JLabel.CENTER);
         wordsFoundLabel.setVerticalAlignment(JLabel.CENTER);
         progressPanel.add(wordsFoundLabel);
         
         //Τυχαία επιλογή αριθμού από μπαλαντέρ στο παιχνίδι
         int[] placeOfWilds;
         placeOfWilds = new int[returnRandom(0,4)];
         
         //Τυχαία επιλογή θέσης μπαλαντέρ
         for(int i=0; i < placeOfWilds.length; i++)
             placeOfWilds[i]=returnRandom(0,NUM-1);
         
         
         //Τυχαία επιλογή αριθμού γραμμάτων με κόκκινο φόντο στο παιχνίδι
         int[] placeOfRedLetters;
         placeOfRedLetters = new int[returnRandom(0,2)];
         
         //Τυχαία επιλογή θέσης γραμμάτων με κόκκινο φόντο στο παιχνίδι
         for(int i=0; i < placeOfRedLetters.length; i++ )
             placeOfRedLetters[i] = returnRandom(0,NUM-1,placeOfWilds);
         
         //Tυχαία επιλογή αριθμού γραμμάτων με μπλε φόντο στο παιχνίδι
         int[] placeOfBlueLetters;
         placeOfBlueLetters = new int[returnRandom(0,3)];
         
         //Τυχαία επιλογή θέσης γραμμάτων με μπλε φόντο στο παιχνίδι
         for(int i=0; i < placeOfBlueLetters.length; i++)
             placeOfBlueLetters[i] = returnRandom(0,NUM-1,placeOfWilds,placeOfRedLetters);
         
         //Αρχικοποίηση του PrevButton σε -1
         LetterPanel.changePrevButton(-1);
         
         // --------------------- Δημιουργία των Panel ------------------------
         
         int keeper1; //Αποθηκεύει αν το panel έχει ειδική κατάσταση ή όχι
         
         for(int i=0; i < NUM; i++){
            
            charPanels[i] = new JPanel();
            charPanels[i].setLayout(new GridLayout(1,4));
            charPanels[i].setBorder(br); //Προσθήκη border στα κουμπιά-πανελ
           
            //Απόκτηση τυχαίου γράμματος
            Letter chosenLetter = RandomLetterChooser();
            
            keeper1 = 0;
            
            
            //Αν το panel έχει επιλεχθεί για μπαλαντέρ, κάνε το πάνελ μπαλαντέρ
            for(int l=0; l < placeOfWilds.length; l++){
                if(placeOfWilds[l] == i){
                    letterPanels[i] = new LetterPanel(i,chosenLetter.ReturnLetter(),chosenLetter.ReturnLetterPoints(),4);
                    keeper1 = 1;
                    break;
                }       
            }
            
            if(keeper1 == 0){
                //Αν το πάνελ έχει επιλεχθεί για κόκκινο, κάνε το πάνελ κόκκινο
                for(int l=0; l< placeOfRedLetters.length; l++){
                    if(placeOfRedLetters[l] == i){
                        letterPanels[i] = new LetterPanel(i,chosenLetter.ReturnLetter(), chosenLetter.ReturnLetterPoints(),2);
                        keeper1 = 1;
                        break;
                    }
                }
            }
            
            if(keeper1 == 0){
                //Αν το πάνελ έχει επιλεχθεί για μπλε, κάνε το πάνελ μπλε
                for(int l=0; l< placeOfBlueLetters.length; l++){
                    if(placeOfBlueLetters[l] == i){
                        letterPanels[i] = new LetterPanel(i,chosenLetter.ReturnLetter(), chosenLetter.ReturnLetterPoints(),3);
                        
                        keeper1 = 1;
                        break;
                    }
                }
            }
            
            if(keeper1 == 0){
                //Αν το πάνελ δεν έχει επιλεχθεί για ειδική λειτουργία, κάντο άσπρο
                letterPanels[i] = new LetterPanel(i,chosenLetter.ReturnLetter(), chosenLetter.ReturnLetterPoints(),1);
                
            }
       
            charLabels[i] = new JLabel(letterPanels[i].displayLetter());
            charLabels[i].setFont(new Font("Serif",Font.PLAIN, 32));
            charLabels[i].setHorizontalAlignment(JLabel.CENTER);
            pointLabels[i] = new JLabel(letterPanels[i].displayPoints());
            
            if(letterPanels[i].returnType() !=4){
                pointLabels[i] = new JLabel(letterPanels[i].displayPoints());
                pointLabels[i].setFont(new Font("Serif",Font.PLAIN, 14));
                
                charPanels[i].add(new JLabel());
                charPanels[i].add(charLabels[i]);
                charPanels[i].add(pointLabels[i]);
                charPanels[i].add(new JLabel());
            }
            else
                charPanels[i].add(charLabels[i]);
            
            //Χρωματίζει τα panel ανάλογα την λειτουργία τους 
             switch (letterPanels[i].returnType()) {
                 case 2 -> charPanels[i].setBackground(Color.red);
                 case 3 -> charPanels[i].setBackground(Color.CYAN);
                 case 4 -> charPanels[i].setBackground(Color.MAGENTA);
                 default -> { charPanels[i].setBackground(Color.WHITE);
                 }
             }
            
            //Κάνει τα panel clickable
            charPanels[i].addMouseListener(new LetterPanelListener(letterPanels[i]));
            gamePanel.add(charPanels[i]);
        }
        gamePanel.setBackground(Color.GRAY);
        
        
        
        //Κουμπί υποβολής λέξης
         sendWord.addActionListener((ActionEvent e) -> {
             LetterPanel.changePrevButton(-1);
             
             //Περίπτωση όπου η λέξη έχει ήδη βρεθεί
             if(a.checkIfWordFound(Score.returnWord()) == true){
                 displayMessage(0,"Αυτή η λέξη έχει βρεθεί ήδη!");
                 Score.resetWord();
                 ResetPanels();
             }
             
             else{
                 //Περίπτωση όπου η λέξη υπάρχει
                 if(Lexicon.doesWordExist(Score.returnWord()) == true){
                     try {
                         SoundEffects.correctWord();
                     } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                         Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     if(Score.returnBlue()==true){
                         displayMessage(3,"Συγχαρητήρια! Βρήκες την λέξη "
                        +Score.returnWord()+" και έκανες "+Score.returnBlueScore() +" βήματα!");
                         a.foundWord(Score.returnWord(), 2*Score.returnScore()); //προσθήκη της λέξης στο σετ με αυτές που έχουν βρεθεί
                     }
                     else{
                         displayMessage(3,"Συγχαρητήρια! Βρήκες την λέξη "
                        +Score.returnWord()+" και έκανες "+Score.returnScore() +" βήματα!");
                         a.foundWord(Score.returnWord(), Score.returnScore()); //προσθήκη της λέξης στο σετ με αυτές που έχουν βρεθεί
                     }
                     
                     
                    updateProgressBar(a.returnPointsGathered(),a); //update στο progressbar
                    updateWordsFoundLabel(a.returnNumberOfWordsFound());   
                    if(a.returnPointsGathered()>=10){
                            ProfileChooser.initGame(ProfileChooser.returnProfile());
                            this.dispose();
                    }
                    changeWordPanels();
                 
                 }
                 //Περίπτωση όπου η λέξη δεν υπάρχει
                 else{
                     try {
                         SoundEffects.wrongWord();
                     } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                         Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     displayMessage(1,"Δεν υπάρχει η λέξη "+Score.returnWord());
                 }
                 
                 //reset των Πάνελ 
                 ResetPanels();
                 Score.resetWord();    
             }
         });
         
         shuffleLine.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                String[] options = {"1","2","3","4","5","6","7","8"};
                int a = JOptionPane.showOptionDialog(progressPanel,
                "Διάλεξε την γραμμή που θα αναδιαταγεί",
                "Αναδιάταξη Γραμμής",JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, options , null);
                rowShuffle(a);
                RowShuffler.updateCounter();
                if(RowShuffler.returnCounter()>=3){
                    shuffleLine.setVisible(false);
                    shuffleLineLeft.setText("Έχεις χρησιμοποιήσει όλες τις διαθέσιμες"
                            + " αναδιατάξεις γραμμών!");
                    shuffleLineLeft.setFont(new Font("Verdana",Font.BOLD,14));
                }
                else
                    shuffleLineLeft.setText(RowShuffler.returnCounter()+"/3");
            }
        });
         
        shuffleColumn.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                String[] options = {"1","2","3","4","5","6","7","8"};
                int a = JOptionPane.showOptionDialog(progressPanel,
                "Διάλεξε την στήλη που θα αναδιαταγεί",
                "Αναδιάταξη Στήλης",JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, options , null);
                columnShuffle(a);
                ColumnShuffler.updateCounter();
                if(ColumnShuffler.returnCounter()>=3){
                    shuffleColumn.setVisible(false);
                    shuffleColumnLeft.setText("Έχεις χρησιμοποιήσει όλες τις διαθέσιμες"
                            + " αναδιατάξεις στηλών!");
                    shuffleColumnLeft.setFont(new Font("Verdana",Font.BOLD,14));
                }
                else
                    shuffleColumnLeft.setText(ColumnShuffler.returnCounter()+"/3");
            }
        });
        
        shuffleEverything.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                tableShuffle();
                ShuffleTable.updateCounter();
                if(ShuffleTable.returnCounter()>=5){
                    shuffleEverything.setVisible(false);
                    shuffleEverythingLeft.setText("Έχεις χρησιμοποιήσει όλες τις διαθέσιμες"
                            + " αναδιατάξεις ταμπλό!");
                    shuffleEverythingLeft.setFont(new Font("Verdana",Font.BOLD,14));
                }
                else
                    shuffleEverythingLeft.setText(ColumnShuffler.returnCounter()+"/5");
            }
        });
        
        deleteLine.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                String[] options = {"1","2","3","4","5","6","7","8"};
                int a = JOptionPane.showOptionDialog(progressPanel,
                "Διάλεξε την γραμμή που θα διαγραφεί",
                "Διαγραφή Γραμμής",JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,null, options , null);
                lineDeleter(a);
                LineDeleter.updateCounter();
                if(LineDeleter.returnCounter()>=3){
                    deleteLine.setVisible(false);
                    deleteLineLeft.setText("Έχεις χρησιμοποιήσει όλες τις διαθέσιμες"
                            + " διαγραφές γραμμής!");
                    deleteLineLeft.setFont(new Font("Verdana",Font.BOLD,14));
                }
                else
                    deleteLineLeft.setText(LineDeleter.returnCounter()+"/3");
            }
        });
    }
    
    //Αλλάζει τα γράμματα στις θέσεις όπου βρέθηκε η λέξη
    protected static void changeWordPanels(){
       
        Letter chosenLetter;
        int position;
        for(int i=0; i<Score.returnWordPositionSize(); i++){
            chosenLetter = RandomLetterChooser();
            position = Score.returnWordPositionElement(i);
            System.out.println(chosenLetter.ReturnLetter()+" "+position);
            charPanels[position].removeAll();
           // letterPanels[position]=null;
            letterPanels[position] = new LetterPanel(position,chosenLetter.ReturnLetter(),
                chosenLetter.ReturnLetterPoints(),1);
            charLabels[position].setText(letterPanels[position].displayLetter());
            pointLabels[position].setText(letterPanels[position].displayPoints());
            charPanels[position].add(new JLabel());
            charPanels[position].add(charLabels[position]);
            charPanels[position].add(pointLabels[position]);
            charPanels[position].add(new JLabel());
            charPanels[position].updateUI();
        }
    }
    //Κάνει reset τα Panels στο σωστό τους χρώμα ( για χρήση μετά από λάθος επιλογή )
    //και αλλάζει τα γράμματα σε περίπτωση που βρέθηκε λέξη
    protected static void ResetPanels(){
        
        for(int i=0; i < NUM; i++){
            charLabels[i].setText(letterPanels[i].displayLetter());
            pointLabels[i].setText(letterPanels[i].displayPoints());
            switch (letterPanels[i].returnType()) {
                 case 2 -> charPanels[i].setBackground(Color.red);
                 case 3 -> charPanels[i].setBackground(Color.CYAN);
                 case 4 -> charPanels[i].setBackground(Color.MAGENTA);
                 default -> { charPanels[i].setBackground(Color.WHITE);
                 }
            }
        }
    }
    
    //Κάνει reset τα Panels στο σωστό τους χρώμα μετά από αλλαγή μπαλαντέρ
    protected static void ResetPanels(int exWild){
        
        for(int i=0; i < NUM; i++){
            charLabels[i].setText(letterPanels[i].displayLetter());
            pointLabels[i].setText(letterPanels[i].displayPoints());
            
            //Δημιουργία των Label που δεν υπήρχαν προηγουμένως στο panel
            //επειδή ήταν Μπαλαντέρ
            if(i==exWild){
                charPanels[i].remove(charLabels[i]);
                pointLabels[i] = new JLabel(letterPanels[i].displayPoints());
                pointLabels[i].setFont(new Font("Serif",Font.PLAIN, 14));
                
                charPanels[i].add(new JLabel());
                charPanels[i].add(charLabels[i]);
                charPanels[i].add(pointLabels[i]);
                charPanels[i].add(new JLabel());
            }
            switch (letterPanels[i].returnType()) {
                 case 2 -> charPanels[i].setBackground(Color.red);
                 case 3 -> charPanels[i].setBackground(Color.CYAN);
                 case 4 -> charPanels[i].setBackground(Color.MAGENTA);
                 default -> { charPanels[i].setBackground(Color.WHITE);
                 }
            }
        }
    }
    
    //JOptionPane για επιλογή γράμματος από μπαλαντέρ
    protected static int WildLetterChooser(){
       
        String[] options = {"Α", "Β","Γ","Δ","Ε","Ζ","Η","Θ","Ι","Κ","Λ","Μ","Ν",
            "Ξ","Ο","Π","Ρ","Σ","Τ","Υ","Φ","Χ","Ψ","Ω"};
        return JOptionPane.showOptionDialog(null,
                "Διάλεξε το γράμμα που θα αντικαταστήσει τον μπαλαντέρ",
                "Μπαλαντέρ",JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, new ImageIcon("images/joker.png"), options , null); // via https://www.flaticon.com/authors/smashicons

    }
    
    //Αρχικοποιεί τον πίνακα γραμμάτων με αντικείμενα γραμμάτων
    protected void InitLetterArray(){
        
        letterArray = new Letter[24];
        //Πίνακας Γραμμάτων
        letterArray[0] = new Letter('Α',1);
        letterArray[1] = new Letter('Β',8);
        letterArray[2] = new Letter('Γ',4);
        letterArray[3] = new Letter('Δ',4);
        letterArray[4] = new Letter('Ε',1);
        letterArray[5] = new Letter('Ζ',8);
        letterArray[6] = new Letter('Η',1);
        letterArray[7] = new Letter('Θ',8);
        letterArray[8] = new Letter('Ι',1);
        letterArray[9] = new Letter('Κ',2);
        letterArray[10] = new Letter('Λ',3);
        letterArray[11] = new Letter('Μ',3);
        letterArray[12] = new Letter('Ν',1);
        letterArray[13] = new Letter('Ξ',10);
        letterArray[14] = new Letter('Ο',1);
        letterArray[15] = new Letter('Π',2);
        letterArray[16] = new Letter('Ρ',2);
        letterArray[17] = new Letter('Σ',1);
        letterArray[18] = new Letter('Τ',1);
        letterArray[19] = new Letter('Υ',2);
        letterArray[20] = new Letter('Φ',8);
        letterArray[21] = new Letter('Χ',10);
        letterArray[22] = new Letter('Ψ',10);
        letterArray[23] = new Letter('Ω',3);
    }
    
    /*Επιστρέφει ένα τυχαίο γράμμα
     
     Η επιλογή του γράμματος γίνεται με τον εξης τρόπο αναλογικά με την πιθανότητα
     ύπαρξης ενός γράμματος μέσα σε μια τυχαία λέξη :
    
     ---- Παράγεται ένας τυχαίος αριθμός από το 1-100. Επιστρέφεται η αντίστοιχη
    λέξη σύμφωνα με το ακόλουθο πινακάκι:
    
    ΤΥΧΑΙΟΣ ΑΡΙΘΜΟΣ   ΕΠΙΣΤΡΕΦΟΜΕΝΗ ΤΙΜΗ
         1-11       ->      Α  (11%)
           12       ->      Β  (1%)
           13       ->      Γ  (1%)
        14-15       ->      Δ  (2%)
        16-24       ->      Ε  (9%)
           25       ->      Ζ  (1%)
        26-30       ->      Η  (5%)
           31       ->      Θ  (1%)
        32-40       ->      Ι  (9%)
        41-44       ->      Κ  (4%)
        45-46       ->      Λ  (2%)
        47-49       ->      Μ  (3%)
        50-55       ->      Ν  (6%)
           56       ->      Ξ  (1%)
        57-67       ->      Ο  (10%)
        68-71       ->      Π  (4%)
        72-75       ->      Ρ  (4%)
        76-83       ->      Σ  (8%)
        84-91       ->      Τ  (8%)
        92-95       ->      Υ  (4%)
           96       ->      Φ  (1%)
           97       ->      Χ  (1%)
           98       ->      Ψ  (1%)
        99-100      ->      Ω  (2%)
        
    */
    protected static Letter RandomLetterChooser(){
     
        int randNum = returnRandom(1,100);
        
        // 1-11       ->      Α  (11%)
        if(randNum<=11)
            return letterArray[0];
        
        // 12       ->      Β  (1%)
        else if(randNum==12)
            return letterArray[1];
        
        // 13       ->      Γ  (1%)
        else if(randNum==13)
            return letterArray[2];
        
        // 14-15       ->      Δ  (2%)
        else if(randNum==14 || randNum==15)
            return letterArray[3];
        
        // 16-24       ->      Ε  (9%)
        else if(randNum>=16 && randNum<=24)
            return letterArray[4];
        
        // 25       ->      Ζ  (1%)
        else if(randNum==25)
            return letterArray[5];
        
        // 26-30       ->      Η  (5%)
        else if(randNum>=26 && randNum<=30)
            return letterArray[6];
        
        // 31       ->      Θ  (1%)
        else if(randNum==31)
            return letterArray[7];
        
        // 32-40       ->      Ι  (9%)
        else if(randNum>=32 && randNum<=40)
            return letterArray[8];
        
        // 41-44       ->      Κ  (4%)
        else if(randNum>=41 && randNum<=44)
            return letterArray[9];
        
        // 45-46       ->      Λ  (2%)
        else if(randNum==45 || randNum==46)
            return letterArray[10];
        
        // 47-49       ->      Μ  (3%)
        else if(randNum>=47 && randNum<=49)
            return letterArray[11];
        
        // 50-55       ->      Ν  (6%)
        else if(randNum>=50 && randNum<=55)
            return letterArray[12];
        
        // 56       ->      Ξ  (1%)
        else if(randNum==56)
            return letterArray[13];
        
        // 57-67       ->      Ο  (10%)
        else if(randNum>=57 && randNum<=67)
            return letterArray[14];
        
        // 68-71       ->      Π  (4%)
        else if(randNum>=68 && randNum<=71)
            return letterArray[15];
        
        // 72-75       ->      Ρ  (4%)
        else if(randNum>=72 && randNum<=75)
            return letterArray[16];
        
        // 76-83       ->      Σ  (8%)
        else if(randNum>=76 && randNum<=83)
            return letterArray[17];
        
        // 84-91       ->      Τ  (8%)
        else if(randNum>=84 && randNum<=91)
            return letterArray[18];
        
        // 92-95       ->      Υ  (4%)
        else if(randNum>=92 && randNum<=95)
            return letterArray[19];
        
        // 96       ->      Φ  (1%)
        else if(randNum==96)
            return letterArray[20];
        
        // 97       ->      Χ  (1%)
        else if(randNum==97)
            return letterArray[21];
        
        // 98       ->      Ψ  (1%)
        else if(randNum==98)
            return letterArray[22];
        
        //99-100      ->      Ω  (2%)
        else if(randNum==99 || randNum==100)
            return letterArray[23];
        
        return null;
    }
    
    //Επιστρέφει τυχαίο αριθμό από min έως max
    protected static int returnRandom(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max)+min;
    }
    
    // -------------------- Overload στην returnRandom --------------------
    //Επιστρέφει τυχαίο αριθμό από min έως max εκτός από τους αριθμούς που
    //περιέχονται στον πίνακα exception
    protected int returnRandom(int min, int max, int[] exception){
        Random rand = new Random();
        int keeper = 1;
        int randNum =0;
        while(keeper == 1){
            keeper = 0;
            randNum = rand.nextInt(max)+min;
            //Αν ο τυχαίος αριθμός randNum περιλαμβάνεται στον exception μένει 
            //στην while
            for(int i=0; i < exception.length; i++){
                if(randNum == exception[i])
                    keeper = 1;
            }
        }
        
        return randNum;
    }
    
    // -------------------- Overload στην returnRandom --------------------
    //Επιστρέφει τυχαίο αριθμό από min έως max εκτός από τους αριθμούς που
    //περιέχονται στους πίνακες exception
    protected int returnRandom(int min, int max, int[] exception1, int[] exception2){
        Random rand = new Random();
        int keeper = 1;
        int randNum=0;
        while(keeper == 1){
            keeper = 0;
            randNum = rand.nextInt(max)+min;
            //Αν ο τυχαίος αριθμός randNum περιλαμβάνεται στον exception1 
            //μένει στην while
            for(int i=0; i < exception1.length; i++){
                if(randNum == exception1[i])
                    keeper = 1;
            }
            //Αν ο τυχαίος αριθμός randNum περιλαμβάνεται στον exception2 
            //μένει στην while
            for(int i=0; i < exception2.length; i++){
                if(randNum == exception2[i])
                    keeper = 1;
            }
        }
        
        return randNum;
    }
    
    //Επιστρέφει ένα συγκεκριμένο αντικείμενο από τον πίνακα γραμμάτων 
    // --getter για χρήση από την κλάση LetterPanelListener
    protected static Letter ReturnLetterArray(int n){
        return letterArray[n];
    }
    
    //Εμφανίζει μήνυμα στον παίκτη στο κάτω μέρος της οθόνης
    protected static void displayMessage(int color, String message){
        messageLabel.setText(message);
        switch (color) {
            case 0 -> messagePanel.setBackground(Color.gray);
            case 1 -> messagePanel.setBackground(Color.red);
            default -> messagePanel.setBackground(Color.green);
        }
    }
    
    //Ενημερώνει το progressbar με την τιμή value
    protected void updateProgressBar(int value,FoundWords a){
        progressBar.setValue(value);
        progressBar.setString("Βήματα: "+value+"/200");
        if(value>=10){
            System.out.println(); JOptionPane.showMessageDialog(null,
            "Συγχαρητήρια! Νίκησες!","Νίκησες!",JOptionPane.INFORMATION_MESSAGE);
            ProfileChooser.returnProfile().newWin();

            //--------Διαγραφή πινάκων για ομαλή λειτουργία του επόμενου--------
            //--------------------------παιχνιδιού------------------------------
            for(int i=0; i<charPanels.length; i++){
                charPanels[i] = null;
                letterPanels[i] = null;
                pointLabels[i] = null;
                charLabels[i] = null;
            }

            gamePanel.removeAll();
            progressBar.setValue(0);
            updateWordsFoundLabel(0);
            a = null;
            
        }
    }
    
    //Ενημερώνει το πόσες λέξεις έχουν βρεθεί
    protected void updateWordsFoundLabel(int value){
        wordsFoundLabel.setText("Λέξεις που βρέθηκαν: "+value);
        wordsFoundLabel.setForeground(Color.WHITE);
    }
    
    //Ενημερώνει το πεδίο των Νικών-Ηττών-Σερί του παίκτη
    protected static void updateInfoLabel(Profile profile){
        infoLabel.setText("Νίκες: "+profile.showWins()+"  Ήττες: "+profile.showLosses()
            +"  Σερί: "+profile.showStreak());
    }
    
    //Επιλογή Αναδιάταξη γραμμής
    protected static void rowShuffle(int row){
        int[] newNumbers = new int[8];
        newNumbers = RowShuffler.rowShuffle(row);
        switch (row) {
            case 0:
                Switcharoo(0,8,newNumbers);
                break;
            case 1:
                Switcharoo(8,16,newNumbers);
                break;
            case 2:
                Switcharoo(16,24,newNumbers);
                break;
            case 3:
                Switcharoo(24,32,newNumbers);
                break;
            case 4:
                Switcharoo(32,40,newNumbers);
                break;
            case 5:
                Switcharoo(40,48,newNumbers);
                break;
            case 6:
                Switcharoo(48,56,newNumbers);
                break;
            default:
                Switcharoo(56,64,newNumbers);
                break;
        }
        ResetPanels();
    }
    
    //Επιλογή αναδιάταξη στήλης
    protected static void columnShuffle(int column){
        int[] newNumbers = new int[8];
        newNumbers = ColumnShuffler.columnShuffle(column);
        switch (column) {
            case 0:
                Switcharoo(0,newNumbers);
                break;
            case 1:
                Switcharoo(8,newNumbers);
                break;
            case 2:
                Switcharoo(16,newNumbers);
                break;
            case 3:
                Switcharoo(24,newNumbers);
                break;
            case 4:
                Switcharoo(32,newNumbers);
                break;
            case 5:
                Switcharoo(40,newNumbers);
                break;
            case 6:
                Switcharoo(48,newNumbers);
                break;
            default:
                Switcharoo(56,newNumbers);
                break;
        }
        ResetPanels();
    }
    
    //Υλοποιεί το shuffle των λέξεων στο ταμπλό
    protected static void tableShuffle(){
        int[] newNumbers = new int[64];
        LetterPanel temp1;
        for(int i=0; i<64; i++){
            charPanels[i].removeAll();
            temp1 = letterPanels[newNumbers[i]];
            letterPanels[newNumbers[i]] = letterPanels[i];
            letterPanels[i] = temp1;
            charLabels[i].setText(letterPanels[i].displayLetter());
            pointLabels[i].setText(letterPanels[i].displayPoints());
            charPanels[i].add(charLabels[i]);
            charPanels[i].add(pointLabels[i]);
            charPanels[i].updateUI();
        }
        ResetPanels();
    }
    
    //Υλοποιεί την διαγραφή γραμμής
    protected static void lineDeleter(int line){
        Letter newLetter;
        for(int i=0+(line*8); i<8+(line*8); i++){
            charPanels[i].removeAll();
            newLetter = RandomLetterChooser();
            letterPanels[i] = new LetterPanel(i,newLetter.ReturnLetter(),
                    newLetter.ReturnLetterPoints(),1);
            charLabels[i].setText(letterPanels[i].displayLetter());
            pointLabels[i].setText(letterPanels[i].displayPoints());
            charPanels[i].add(charLabels[i]);
            charPanels[i].add(pointLabels[i]);
            charPanels[i].updateUI();
        }
        ResetPanels();
    }
    
    //Υλοποιεί την αντικατάσταση των λέξεων στα πάνελ --- για shuffle στήλης
    public static void Switcharoo(int start, int[] newNumbers){
        LetterPanel temp1;
        for(int i=start; i<(start+57); i+=8){
            charPanels[i].removeAll();
            temp1 = letterPanels[newNumbers[i%8]];
            letterPanels[i].changePosition(newNumbers[i%8]);
            letterPanels[newNumbers[i%8]] = letterPanels[i];
            letterPanels[i] = temp1;
            charLabels[i].setText(letterPanels[i].displayLetter());
            pointLabels[i].setText(letterPanels[i].displayPoints());
            charPanels[i].add(charLabels[i]);
            charPanels[i].add(pointLabels[i]);
            charPanels[i].updateUI();
        }
    }
    
    //Υλοποιεί την αντικατάσταση των λέξεων στα πάνελ --- για shuffle γραμμής
    public static void Switcharoo(int min, int max,int[] newNumbers){
        LetterPanel temp1;
        for(int i=min; i<max; i++){
            charPanels[i].removeAll();
            //System.out.println(newNumbers[i%8]);
            temp1 = letterPanels[newNumbers[i%8]];
            letterPanels[i].changePosition(newNumbers[i%8]);
            letterPanels[newNumbers[i%8]] = letterPanels[i];
            letterPanels[i] = temp1;
            System.out.println(letterPanels[i].returnPosition());
            charLabels[i].setText(letterPanels[i].displayLetter());
            pointLabels[i].setText(letterPanels[i].displayPoints());
            charPanels[i].add(charLabels[i]);
            charPanels[i].add(pointLabels[i]);
            charPanels[i].updateUI();
        }
    }
    
    //Επιστρέφει το κείμενο για την επιλογή Βοήθεια
    protected static String returnHelpString(){
        return "Στόχος του παιχνιδιού είναι να κάνεις 200 βήματα και να ολοκληρώσεις το μονοπάτι. \n"
                + " Κάθε γράμμα αντιστοιχεί σε έναν αριθμό βημάτων που αναγράφονται δίπλα από"
                + " το γράμμα. \n Αν το γράμμα είναι κόκκινο τότε δίνει τα διπλάσια βήματα από"
                + " ότι συνήθως.\n Αν το γράμμα είναι μπλε διπλασιάζει τα βήματα όλης της λέξης."
                + " \n Κατά τον σχηματισμό λέξεων επιτρέπεται να επιλεχθούν μόνο γράμματα γειτονικά"
                + " στο τελευταίο επιλεγμένο γράμμα. \n Καλή επιτυχία!";
    }
    
    //Επιστρέφει το κείμενο για την επιλογή Πληροφορίες
    protected static String returnAboutString(){
        return "Δημιουργοί :\n"
                + " Δημήτρης Καραγεώργος 321/2017071\n"
                + " Αναστάσιος Κουτσώνης 321/2018106";
    }
}