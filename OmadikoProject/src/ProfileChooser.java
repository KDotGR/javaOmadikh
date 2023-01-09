/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class ProfileChooser extends JFrame implements ActionListener{
    
    CardLayout card;
    private final JButton newProfileButton;
    protected static Profile[] profiles;
    protected int numofProfiles;
    private final JFrame frame = new JFrame ();
    private final JPanel welcomePanel,mainPanel,rowPanel1,rowPanel2,rowPanel3;
    private final JLabel welcomeLabel,chooseProfileLabel,enterUsername;
    private JTextField username;
    
    ProfileChooser(){
        numofProfiles=0;
        profiles = new Profile[numofProfiles+1];
        //Προσθήκη του logo της εφαρμογής
        ImageIcon image = new ImageIcon("images/logo.png"); //via https://www.freepik.com
        frame.setIconImage(image.getImage());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //frame.setSize(420,420);
        frame.setLayout(new BorderLayout());
        
        username = new JTextField(5);
        
        //Δημιουργία των labels
        welcomeLabel = new JLabel ("Καλωσήρθες στο Μονοπάτι των Λέξεων!");
        chooseProfileLabel = new JLabel ("Διάλεξε Προφίλ");
        enterUsername= new JLabel ("Enter Username");
        
        //Δημιουργία των panels
        welcomePanel = new JPanel();
        welcomePanel.setPreferredSize(new Dimension(750,60));
        //welcomePanel.setBackground(Color.MAGENTA);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(100,300));
        mainPanel.setBackground(Color.BLUE);
        
        //προσαρμογή του Label καλωσορίσματος και εισαγωγή στο panel
        welcomeLabel.setBounds(0,0,800,50);
        welcomeLabel.setFont(new Font(null,Font.BOLD,30));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomePanel.add(welcomeLabel);

        frame.add(welcomePanel,BorderLayout.NORTH); //Εισαγωγή του Panel στο frame
        
        rowPanel1 = new JPanel();
        
        //Προσαρμογή του label και εισαγωγή στο panel
        rowPanel1.setLayout(new FlowLayout());
        chooseProfileLabel.setBounds(120,0,500,50);
        chooseProfileLabel.setFont(new Font(null,Font.PLAIN,25));
        chooseProfileLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseProfileLabel.setVerticalAlignment(JLabel.CENTER);
        rowPanel1.add(chooseProfileLabel);
        mainPanel.add(rowPanel1);
         
        
        //κουμπί για νέο προφίλ
        newProfileButton = new JButton("Νέο Προφίλ");
        rowPanel2 = new JPanel();
        card = new CardLayout(10,10);
        rowPanel2.setLayout(card);
        
        newProfileButton.setFocusable(false);
        newProfileButton.addActionListener(this);
        rowPanel2.add(newProfileButton); //Εισαγωγή του κουμπιού στο panel
        
        
        //Προσαρμογή του textField ----- θα εμφανίζεται μόνο σε περίπτωση
        //που πατηθεί το κουμπί "Νέο Προφίλ"
        username = new JTextField("Όνομα");
        username.addActionListener((ActionEvent e) -> {
            profiles[numofProfiles] =new Profile(username.getText());
            initGame(profiles[numofProfiles]);
            //Play a = new Play(new Profile(username.getText()),1);
            frame.dispose();
        });
        
        username.addFocusListener(new FocusListener(){
            
            @Override
            public void focusGained(FocusEvent e){
                username.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        rowPanel3 = new JPanel();
        rowPanel3.setLayout(new FlowLayout());
        
        
        rowPanel2.add(username);
        mainPanel.add(rowPanel2);
        
        frame.add(mainPanel,BorderLayout.CENTER); //Εισαγωγή του panel στο frame
        frame.setVisible(true);
        frame.pack();
        
    }

    //Όταν πατηθεί το κουμπί "Νέο Προφίλ" το κουμπί εξαφανίζεται και την
    //θέση του παίρνει το textfield εισαγωγής ονόματος
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newProfileButton){
  
            card.next(rowPanel2);
        }
    }
    
    //Επιστρέφει το τελευταίο στοιχείο του πίνακα profiles
    protected static Profile returnProfile(){
        return profiles[profiles.length-1];
    }
    
    public static void initGame(Profile chosenProf){
        Play play = new Play(chosenProf,1);
    }
}
