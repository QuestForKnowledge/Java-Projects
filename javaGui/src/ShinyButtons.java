/*
 * name: Shaheen Ghazazani
 * S#: 100888025
 * date:march1st, 2014
 * assignment#:6
 * year:1
 * semester:2
 */

public class ShinyButtons{ 
	//byte values for color + rows
    public static byte RED = 0; 
    public static byte ORANGE = 1; 
    public static byte YELLOW = 2; 
    public static byte GREEN = 3; 
    public static byte BLUE = 4; 
    public static byte LIGHT_GRAY = 5; 
    public static byte DARK_GRAY = 6; 
    public static byte ROWS = 8;

    //declare byte variables button table, button state table, ect
    public byte[][] buttonTable; 
    public boolean[][] buttonStateTable;
    private String lastPiece;
    private int score;

    public ShinyButtons() { 
        buttonTable = new byte[ROWS][ROWS]; 
        buttonStateTable = new boolean[ROWS][ROWS]; 
        resetButtons(); 
    } 

    //the following deals with user clicking and interchanging selected blocks
    public void doClick(String e) {
        if (lastPiece == null) {
            lastPiece = e;
            setButtonState(e, true);
        } else {
            int[] x1 = parseActionCommand(e);
            System.out.println("x1[0]: "+ x1[0] + " x1[1]: " + x1[1]);
            int[] x2 = parseActionCommand(lastPiece);
            System.out.println("x2[0]: "+ x2[0] + " x2[1]: " + x2[1]);
            if (x1[0] == x2[0] - 1 && x1[1] == x2[1] ||
                x1[0] == x2[0] + 1 && x1[1] == x2[1] ||
                x1[1] == x2[1] - 1 && x1[0] == x2[0] ||
                x1[1] == x2[1] + 1 && x1[0] == x2[0]) {
                byte b1 = buttonTable[x1[0]][x1[1]];
                byte b2 = buttonTable[x2[0]][x2[1]];
                buttonTable[x1[0]][x1[1]] = b2;
                buttonTable[x2[0]][x2[1]] = b1;
            } 
            setButtonState(lastPiece, false);
            lastPiece = null;
        }

    }
      
    //process table, including score, and more
    public void processTable() {
        highlightRows();
        int seq = 0;
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<ROWS; c++) {
                if (buttonStateTable[r][c]) {
                    for (int r2=r; r2<ROWS; r2++) if (buttonStateTable[r2][c]) seq++;                    
                    score += doScoreSum(seq);
                    seq = 0;
                    for (int c2=c; c2<ROWS; c2++) if (buttonStateTable[r][c2]) seq++;
                    score += doScoreSum(seq);
                    seq = 0;
                }
            }
        }
    }
    //used in process to calculate the score
    public int doScoreSum(int seq) {
        int s = 0;
        if (seq >= 3) {
            s = 30;
            System.out.println("doing seq of " + seq);
            for (int i = 3; i <= seq - 1; i++){
                s += (i*30);
            }
            System.out.println("got: " + s);
            
        }
        return s;
    }
    //used in highlighting of rows if three or more are linearly found
    public void highlightRows(){
        for (int r=0; r<ROWS; r++) { 
            for (int c=0; c<ROWS; c++) {
                if (r + 1 <= ROWS - 1 && r - 1 >= 0) {
                    if (buttonTable[r][c] == buttonTable[r + 1][c] && buttonTable[r][c] == buttonTable[r - 1][c]) {
                        buttonStateTable[r][c] = true;
                        buttonStateTable[r + 1][c] = true;
                        buttonStateTable[r - 1][c] = true;
                    }
                }
                if (c + 1 <= ROWS - 1 && c - 1 >= 0) {
                    if (buttonTable[r][c] == buttonTable[r][c + 1] && buttonTable[r][c] == buttonTable[r][c - 1]) {
                        buttonStateTable[r][c] = true;
                        buttonStateTable[r][c + 1] = true;
                        buttonStateTable[r][c - 1] = true;
                    }
                }
            }
        }
    }
    
    //Cleans the table deals 
    public void cleanTable() {
        boolean newPieceAdded = false;
        int r = ShinyButtons.ROWS - 1;
        while (r >= 0) { 
            newPieceAdded = false;
            for (int c = 0; c < ShinyButtons.ROWS; c++) {
                
                if ((r >= 1 && buttonStateTable[r][c] && buttonStateTable[r-1][c]) ||
                    (c < ShinyButtons.ROWS-1 && buttonStateTable[r][c] && buttonStateTable[r][c+1]) ||
                    (c >= 1 && buttonStateTable[r][c] && buttonStateTable[r][c-1])) {
                    for (int r2 = r; r2 >= 1; r2--) {
                        buttonStateTable[r2][c] = buttonStateTable[r2-1][c];
                        buttonTable[r2][c] = buttonTable[r2-1][c];
                    }
                    buttonTable[0][c] = (byte)(Math.random()*7);
                    buttonStateTable[0][c] = false;
                    
                    newPieceAdded = true;
                }
            }
            if (!newPieceAdded) {
                r -= 1;
            }
        }
    }
    
    //reset the buttons
    public void resetButtons() { 
        lastPiece = null;
        for (int r=0; r<ROWS; r++) { 
            for (int c=0; c<ROWS; c++) {
                buttonTable[r][c] = (byte)(Math.random()*7); 
                buttonStateTable[r][c] = false;
            }
        }
    } 
    
    public void setAllFalse(){
        for (int r=0; r<ROWS; r++) { 
            for (int c=0; c<ROWS; c++) {
                buttonStateTable[r][c] = false;
            }
        }
    }

    public byte getButton(int r, int c) { return buttonTable[r][c]; }
    public int getScore() {
        return score;}
    public void setScore(int score) {
        this.score = score; }
    public boolean getButtonState(int r, int c) { return buttonStateTable[r][c]; }
    public void setButtonState(int r, int c, boolean b) { this.buttonStateTable[r][c] = b;}

    public void setButtonState(String s, boolean b) {
        int x[] = parseActionCommand(s);
        this.buttonStateTable[x[0]][x[1]] = b;
    }
    private int[] parseActionCommand(String s){
        int[] x = new int[2];
        x[0] = Integer.parseInt(Character.toString(s.charAt(0)));
        x[1] = Integer.parseInt(Character.toString(s.charAt(1)));
        return x;
    }
}

 
