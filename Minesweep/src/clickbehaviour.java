import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;






public class clickbehaviour implements MouseListener{
boolean[][] haveIBeenHere;
	public clickbehaviour(Board b){
		board=b;
	}

	public void mouseClicked(MouseEvent event) {
		int[] cellClickedCoords;
		commandFeed= board.retrieveFeed();
		Cell cellClicked = (Cell) event.getSource();
		if(CellDataForGame.isDeactivated==true){
			return;
		}
		commandFeed.println("click");
		bombFraction =cellClicked.getBombFraction();
		squaresOnBoard=cellClicked.getSquaresOnBoard();
		haveIBeenHere=new boolean[squaresOnBoard][squaresOnBoard];
		cellClickedCoords = cellClicked.returnCoords();
		revealSurroundings(cellClicked,cellClickedCoords[0],cellClickedCoords[1]);
		if(cellClicked.retrieveDoesBombExist()==1 && board.getClicks()==0){
			commandFeed.println("first turn bomb");
			board.removeAllCells();
			board.deleteCellData();
			board.createAndPlaceBombs(squaresOnBoard,bombFraction);
			//cellClicked.getHidden()==true&&
		}else if(cellClicked.retrieveDoesBombExist()==1&&cellClicked.getException()==false){
			commandFeed.println("BOMB!");
			board.setClick(board.getClicks()+1);
		}else{
			board.setClick(board.getClicks()+1);
			if(haveIWon(cellClicked.getCellArray())){
				commandFeed.println("YOU HAVE DEFEATED");
				CellDataForGame.isDeactivated=true;
			}
		}
		board.setAndNormaliseIconsToBoardSize(board.getOffset());
		haveIBeenHere=new boolean[squaresOnBoard][squaresOnBoard];
	}

	public CellDataForGame revealSurroundings(Cell cellclicked,int i,int j){
		Cell[][] cellarray=cellclicked.getCellArray();
		if(cellclicked.getHidden()==false){
			return null;
		}
		
		CellDataForGame newgamecelldata;
		board.invalidate();
		if(cellclicked.retrieveDoesBombExist()==1&&cellclicked.getHidden()==true){
			showEverythingOnBoard(cellclicked.getCellArray());
			newgamecelldata=new CellDataForGame(squaresOnBoard, bombFraction);
			return newgamecelldata;
		}else if(cellclicked.retrieveDistance()!=0){
			cellclicked.setHidden(false);
			if(board.getClicks()==0){
			cellclicked.setCellIsClicked(true);
			}
			
			if(board.getClicks()==0){
                boolean isempty;
                int count=0;
				int[][] indices = new int[4][2];
				indices[0][0]=i;indices[0][1]=j+1;
				indices[1][0]=i;indices[1][1]=j-1;
				indices[2][0]=i+1;indices[2][1]=j;
				indices[3][0]=i-1;indices[3][1]=j;
				cellclicked.setHidden(false);
				cellclicked.markAsException(true);
				for(int k=0;k<4;k++){
					if(isRealCoord(squaresOnBoard,indices[k][0],indices[k][1])==true){
						isempty=recursivelyRevealNearbyEmptySpaces(cellarray,indices[k][0],indices[k][1]);
						if(isempty==true){count++;}
			     		cellarray[indices[k][0]][indices[k][1]].setHidden(false);
			     		cellarray[indices[k][0]][indices[k][1]].markAsException(true);
			     		clearAllSurroundings(cellarray,indices[k][0],indices[k][1]);
					}
				}
//the following makes sure that I can treat the situation where I am surrounded on the first turn differently. 
//as it stands i use it to mark an exception, where I reveal surroundings of a bomb square. This only happens on the first turn.
//in its present form I make no distinction between a surrounded cell and one that is not surrounded. both have their surroundings revealed on the first turn.
				
				if(count==0){
				     for(int k=0;k<4;k++){
				       	if(isRealCoord(squaresOnBoard,indices[k][0],indices[k][1])==true){
					     	if (cellarray[indices[k][0]][indices[k][1]].retrieveDoesBombExist()==1){
					     		cellarray[indices[k][0]][indices[k][1]].setHidden(false);
					     		cellarray[indices[k][0]][indices[k][1]].markAsException(true);
					     		clearAllSurroundings(cellarray,indices[k][0],indices[k][1]);
					    	}
					    }
			       	 }

				}

			};
			
			
			
			
			return null;
			
		}else{
			haveIBeenHere=new boolean[squaresOnBoard][squaresOnBoard];
			clearAllSurroundings(cellclicked.getCellArray(),i,j);
			//fillEdges(cellarray);
			board.validate();
			board.repaint();
			return null;
		}

	}
	
	
	public boolean recursivelyRevealNearbyEmptySpaces(Cell[][] cellarray,int x,int y){
		if(isRealCoord(squaresOnBoard,x,y)){
		      if(cellarray[x][y].retrieveDoesBombExist()==0){
					cellarray[x][y].setHidden(false);
			          if(cellarray[x][y].retrieveDistance()==0){
				            clearAllSurroundings(cellarray,x,y);
				            board.validate();
				            board.repaint();

			           }else{

		               }
		       }
	     }
		return (cellarray[x][y].retrieveDistance()==0&&cellarray[x][y].retrieveDoesBombExist()==0);
	}
	
	public boolean haveIWon(Cell[][] cellarray){
		boolean haveIWon=true;
		for(int i=0;i<squaresOnBoard;i++){
			for(int j=0;j<squaresOnBoard;j++){
				if(cellarray[i][j].getHidden()&&cellarray[i][j].retrieveDoesBombExist()==0){
					haveIWon=false;
				}
			}
		}
		
		return haveIWon;
	}

	public void showEverythingOnBoard(Cell[][] cellarray){	
		for(int i=0;i<squaresOnBoard;i++){
			for (int j=0;j<squaresOnBoard;j++){
				     cellarray[i][j].setHidden(false);	
				     cellarray[i][j].setCellIsClicked(false);
				     cellarray[i][j].markAsException(false);
				}
			}
		}
		
	public boolean isRealCoord(int squaresonboard,int i,int j){
		if(i<0 || j<0|| i>(squaresonboard-1)  ||j>(squaresonboard-1)){
			return false;
		}else{
			return true;
		}
	}
	
	
	public void clearAllSurroundings(Cell[][] cellarray,int i,int j){
		if(isRealCoord(squaresOnBoard,i,j)==false|| haveIBeenHere[i][j]==true){
			return;
		}
		haveIBeenHere[i][j]=true;
		if(cellarray[i][j].retrieveDistance()==0){
			cellarray[i][j].setHidden(false);	
			clearAllSurroundings(cellarray,i+1,j);
			clearAllSurroundings(cellarray,i,j+1);
			clearAllSurroundings(cellarray,i-1,j);
			clearAllSurroundings(cellarray,i,j-1);
		}else{
			cellarray[i][j].setHidden(false);
		}
	}
	
	public void clearEdges(Cell[][] cellarray,int i,int j){
		if(isRealCoord(squaresOnBoard,i-1,j-1)==true){
			if(2==countMarked(cellarray,i-1,j-1)){
				cellarray[i-1][j-1].setHidden(false);
				cellarray[i-1][j-1].markAsException(true);
			}
		}
		if(isRealCoord(squaresOnBoard,i+1,j+1)==true){
			if(2==countMarked(cellarray,i+1,j+1)){
				cellarray[i+1][j+1].setHidden(false);
				cellarray[i+1][j+1].markAsException(true);
			}
		}
		if(isRealCoord(squaresOnBoard,i-1,j+1)==true){
			if(2==countMarked(cellarray,i-1,j+1)){
				cellarray[i-1][j+1].setHidden(false);
				cellarray[i-1][j+1].markAsException(true);
			}
		}
		if(isRealCoord(squaresOnBoard,i+1,j-1)==true){
			if(2==countMarked(cellarray,i+1,j-1)){
				cellarray[i+1][j-1].setHidden(false);
				cellarray[i+1][j-1].markAsException(true);
			}
		}
	}
	
	public int countMarked(Cell[][] cellarray, int i,int j){
	int counter=0;
		if(isRealCoord(squaresOnBoard,i,j+1)){
	     	if(true==cellarray[i][j+1].getHidden()){
	    	 	counter=counter+1;
	     	}
		}
		if(isRealCoord(squaresOnBoard,i,j-1)){
	     	if(true==cellarray[i][j-1].getHidden()){
	    	 	counter=counter+1;
	     	}
		}
		if(isRealCoord(squaresOnBoard,i+1,j)){
			if(true==cellarray[i+1][j].getHidden()){
	    	 	counter=counter+1;
	     	}
		}
		if(isRealCoord(squaresOnBoard,i-1,j)){
	     	if(true==cellarray[i-1][j].getHidden()){
	    	 	counter=counter+1;
	     	}
		}
		return counter;
	}
	
	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void mousePressed(MouseEvent event) {	
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}

	informationFeed commandFeed;
	Board board;
	int squaresOnBoard;
	boolean firstTurn=true;
	double bombFraction;

}
