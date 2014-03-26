/*
 * name: Shaheen Ghazazani
 * class: Comp1406 
 * Assignment#: 10
 * due date: March 31st, 2014 (the last assignment, due monday). 
 */

public class RoomMaze {
	public static byte	ROWS = 8;
	private byte[][]	wallTable;

	public RoomMaze() {
		wallTable = new byte[ROWS][ROWS];
		resetWalls();
	}

	public void resetWalls() {
            for (int r=0; r<ROWS; r++)
                for (int c=0; c<ROWS; c++) 
                    wallTable [r][c] = (byte)(Math.random()*2);
	}

	public byte getWall(int r, int c) { return wallTable[r][c]; }

	public int identifyRooms() {
            return identifyRoomsRecursiveSearch(0, 0, 2)-1;
	}
                
        private int identifyRoomsRecursiveSearch(int r, int c, int count) {
        	//recursive function for identify rooms
            if (c == ROWS) {
                c = 0;
                r++;
            }
            if (r == ROWS) return count;
            if (getWall(r, c) == 1){
                traceRoomFrom(r, c, (byte)count);
                count++;
            }
            c++; 
            return identifyRoomsRecursiveSearch(r, c, count);
	}
        
	public void traceRoomFrom(int r, int c, byte colorIndex) {
            if (getWall(r, c) == 1) {
                wallTable[r][c] = colorIndex;
                if (r - 1 >= 0){
                	traceRoomFrom(r-1, c, colorIndex);
                }
                if (r + 1 < ROWS){
                	traceRoomFrom(r+1, c, colorIndex);
                }
                if (c - 1 >= 0){
                	traceRoomFrom(r, c-1, colorIndex);
                }
                if (c + 1 < ROWS){
                	traceRoomFrom(r, c+1, colorIndex);
                }
            }
	}
}