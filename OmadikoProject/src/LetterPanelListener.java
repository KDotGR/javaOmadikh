/*
       Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;



    public class LetterPanelListener extends MouseAdapter
    {
        protected LetterPanel letterPanel;
        protected int prevButton;
        protected int position;
        
        LetterPanelListener(LetterPanel letterPanel){
            this.letterPanel = letterPanel;
            this.position = letterPanel.returnPosition();
        }
        
        @Override
    	public void mouseClicked(MouseEvent me)
    	{
              //Αναφορά στο panel που κλικαρίστηκε
              JPanel clickedBox =(JPanel)me.getSource();
              
                try {
                    System.out.println("click");
                    prevButton = LetterPanel.returnPrevButton();
                    //Το κλικ είναι έγκυρο μόνο αν ο χρήστης πατήσει γειτνιακό πάνελ
                    //η δεν έχει πατήσει πάνελ προηγουμένως
                    if(prevButton == -1 || prevButton == position-9 
                       || prevButton == position-8 ||prevButton == position-7
                       || prevButton == position-1 || prevButton == position+1
                       || prevButton == position+7 || prevButton==position+8 ||prevButton == position+9)
                    {
                        Play.displayMessage(0,"");
                        SoundEffects.clickSound();
                        
                        //Περίπτωση όπου το πάνελ που επιλέχθηκε δεν είναι μπαλαντέρ
                        if(letterPanel.returnType()!= 4){
                           
                            if(letterPanel.returnType() == 3){
                                Score.addBlue();
                            }
                            clickedBox.setBackground(Color.YELLOW);
                            LetterPanel.changePrevButton(position);
                            Score.addScore(letterPanel.returnPoints());
                            Score.addChar(letterPanel.displayLetter(),letterPanel.returnPosition());
                        }
                        
                        //Περίπτωση όπου το πάνελ που επιλέχθηκε είναι μπαλαντέρ
                        else{
                            int option =Play.WildLetterChooser();
                            letterPanel.WildChanger(Play.ReturnLetterArray(option));
                            Score.resetWord();
                            LetterPanel.changePrevButton(-1);
                            Play.ResetPanels(letterPanel.position);
                        }
                    }
                    
                    //Περίπτωση όπου το πάνελ που επιλέχθηκε είναι ίδιο με το
                    //προηγούμενο
                    else if(prevButton == position ){
                        Play.displayMessage(0, "");
                        LetterPanel.changePrevButton(-1);
                        Score.resetWord();
                        Play.ResetPanels();
                    }
                    //Περίπτωση όπου το πάνελ που επιλέχθηκε δεν είναι έγκυρο
                    else{
                        SoundEffects.wrongChoiceSound();
                        Play.displayMessage(1, "Τα γράμματα που επιλέγεις πρέπει να είναι γειτονικά");
                        LetterPanel.changePrevButton(-1);
                        Score.resetWord();
                        Play.ResetPanels();
                    }
                        
                    
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    Logger.getLogger(LetterPanelListener.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }