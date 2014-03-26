/*
 * name: Shaheen Ghazazani
 * S#: 100888025
 * date:march1st, 2014
 * assignment#:6
 * year:1
 * semester:2
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ShinyButtonsApp extends JFrame {
    
    private final ShinyButtons game;
    private Timer timer;
    private ButtonPanel buttonPanel;
    private JTextField scoreBox;
    

    public ShinyButtonsApp(String title) {
        //starting stuff
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(578, 634);
        getContentPane().setLayout(null);
        setResizable(false);
        //this is the shinybuttons object that does most of the game stuff
        game = new ShinyButtons();
        //make the timer
        timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.cleanTable();
                refreshButtons();
            }
        });      
        timer.start();
        //make the button panel action listener
        ActionListener a = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.doClick(e.getActionCommand());
                System.out.println(e.getActionCommand());
                doButtonStuff();
            }
        };
        
        //make all them buttons and add them to the panel
        buttonPanel = new ButtonPanel(game, a);
        buttonPanel.setLocation(10, 10);
        add(buttonPanel);
        
        //now time for the labels and text boxes
        JLabel label = new JLabel("Score:");
        label.setLocation(10,570); 
        label.setSize(80, 30);
        add(label); 

        scoreBox = new JTextField();
        scoreBox.setLocation(50, 570);
        scoreBox.setSize(90,30); 
        scoreBox.setHorizontalAlignment(scoreBox.RIGHT); 
        scoreBox.setText(String.valueOf(game.getScore()));
        add(scoreBox);

        JButton button1 = new JButton("New Game");
        button1.setLocation(360, 570);
        button1.setSize(100,25);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanupGame();
            }
        });
        add(button1); 
        
        JButton button2 = new JButton("Quit"); 
        button2.setLocation(460, 570);
        button2.setSize(100,25);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        add(button2); 
        
        //do this last
        refreshButtons();
        
    }
        
    public static void main (String[] args){
        ShinyButtonsApp app; 
        app = new ShinyButtonsApp("Shiny Buttons"); 
        app.setVisible(true); 
    }


    private void cleanupGame() {
        game.setScore(0);
        game.resetButtons();
        doButtonStuff();
    }
    
    private void refreshButtons() {
        game.processTable();
        doButtonStuff();
    }
    
    private void doButtonStuff() {
        buttonPanel.checkButtonStates();
        buttonPanel.generateButtonIcons();
        scoreBox.setText(String.valueOf(game.getScore()));
    }
    

     
}
