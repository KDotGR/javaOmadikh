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
        
        LetterPanelListener(LetterPanel letterPanel){
            this.letterPanel = letterPanel;
        }
        
    	public void mouseClicked(MouseEvent me)
    	{
              JPanel clickedBox =(JPanel)me.getSource(); // get the reference to the box that was clicked 
              System.out.println("beep boop");
                try {
                    SoundEffects.clickSound();
                    if(letterPanel.returnType()!=4)
                        clickedBox.setBackground(Color.YELLOW);
                    else{
                        Play.WildLetterChooser();
                        Play.ResetPanels();
                    }
                        
                    
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    Logger.getLogger(LetterPanelListener.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }