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
            //the logic here is that 0 is walls, 1 is the starting room number,
            //and 2 is the number we want to give the first room
            //then the -1 is the cause we wanna use this number for the color index in the app
            return identifyRoomsHelper(0, 0, 2)-1;
	}
                
        private int identifyRoomsHelper(int r, int c, int count) {
            if (c == ROWS) {//if were at the end of col, jump to the start of next row
                c = 0;
                r++;
            }
            if (r == ROWS) return count;//or maybe we're done?
            if (getWall(r, c) == 1){//if we haven't looked at this square yet
                traceRoomFrom(r, c, (byte)count);//trace a room from it...
                count++;//...and we found a room!
            }
            c++;//c++!
            return identifyRoomsHelper(r, c, count);//call again with new c (and r?)
	}
        
	public void traceRoomFrom(int r, int c, byte colorIndex) {
            if (getWall(r, c) == 1) {//if the tile hasn't been messed with yet
                wallTable[r][c] = colorIndex;//avoid stackoverflow of jumping to tiles before we diddle them
                if (r - 1 >= 0) traceRoomFrom(r-1, c, colorIndex);//jump left
                if (r + 1 < ROWS) traceRoomFrom(r+1, c, colorIndex);//jump right
                if (c - 1 >= 0) traceRoomFrom(r, c-1, colorIndex);//jump up
                if (c + 1 < ROWS) traceRoomFrom(r, c+1, colorIndex);//jump down
            }
	}
}