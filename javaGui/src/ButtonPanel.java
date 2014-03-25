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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	//declare shinybuttons private + create 2d buttons array
    private ShinyButtons game;
    public JButton[][] buttons;
    //below are the imageicon lists given to use for the .png files including selected list + files
    public static ImageIcon[] icons = {new ImageIcon("RedButton.png"),
                                       new ImageIcon("OrangeButton.png"),
                                       new ImageIcon("YellowButton.png"),
                                       new ImageIcon("GreenButton.png"),
                                       new ImageIcon("BlueButton.png"),
                                       new ImageIcon("LightGrayButton.png"),
                                       new ImageIcon("DarkGrayButton.png")}; 
    
    public static ImageIcon[] selectedIcons = {new ImageIcon("SelectedRedButton.png"),
                                               new ImageIcon("SelectedOrangeButton.png"),
                                               new ImageIcon("SelectedYellowButton.png"),
                                               new ImageIcon("SelectedGreenButton.png"),
                                               new ImageIcon("SelectedBlueButton.png"),
                                               new ImageIcon("SelectedLightGrayButton.png"),
                                               new ImageIcon("SelectedDarkGrayButton.png")}; 


    //set size, layout, and button size + creating the grid
    public ButtonPanel(ShinyButtons game, ActionListener a){
        setLayout(null);
        setSize(552, 552);
        this.game = game;
        buttons = new JButton[ShinyButtons.ROWS][ShinyButtons.ROWS];
        
        for (int row = 0; row < ShinyButtons.ROWS; row++) {
            for (int col = 0; col < ShinyButtons.ROWS; col++) {
                final JButton butan = new JButton();
                butan.setSize(69,69); 
                butan.setActionCommand(row + "" + col);
                butan.setLocation(row*69, col*69);
                butan.addActionListener(a);
                buttons[row][col] = butan;
                add(buttons[row][col]);
            }
        }
    }
    //generating the pictures
    public void generateButtonIcons() {
        for (int row = 0; row < ShinyButtons.ROWS; row++) {
            for (int col = 0; col < ShinyButtons.ROWS; col++) {
                buttons[row][col].setIcon(icons[game.getButton(row, col)]);
                buttons[row][col].setSelectedIcon(selectedIcons[game.getButton(row, col)]);
            }
        }
    }
    //check state of the buttons
    public void checkButtonStates() {
        for (int row = 0; row < ShinyButtons.ROWS; row++) {
            for (int col = 0; col < ShinyButtons.ROWS; col++) {
                buttons[row][col].setSelected(game.buttonStateTable[row][col]);
            }
        }
    }
}
