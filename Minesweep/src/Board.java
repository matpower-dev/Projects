import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//We define the variables we pass through to the Cell Data
	public Board(int squaresonboard, double bombfraction,int framesizewidth,int framesizeheight,int offset){
		setClick(0);
		offSet=offset;
		setupBackgroundVariables(squaresonboard,bombfraction);
		setupBoardBehaviour(framesizewidth,framesizeheight,squaresonboard,offset);
		createAndPlaceBombs(squaresonboard,bombfraction);
		setAndNormaliseIconsToBoardSize(offset);
		sendDimensionsToCellData();
	}
	
	public int getOffset(){
		return offSet;
		
	}
	
	public informationFeed retrieveFeed(){
		return commandFeed;
	}
	
	public void getFeed(informationFeed feed){
		commandFeed=feed;
	}
	
	public Cell[][] getCellArray(){
		return cellArray;
	}
	
	
	public void removeAllCells(){

		for (int i=0;i<squaresOnBoard;i++){
			for (int j=0;j<squaresOnBoard;j++){
				remove(cellArray[i][j]);
				cellArray[i][j]=null;
			}
		}

		
	}
	
	public void setClick(int newclick){
		Clicks=newclick;
	}
	
	public int getClicks(){
		return Clicks;
	}
	
	public void setupBoardBehaviour(int framesizewidth,int framesizeheight,double gridsize,int offset){		
		setSize(framesizewidth,framesizeheight-offset);
		GridLayout grid = new GridLayout(squaresOnBoard,squaresOnBoard);
		setLayout(grid);
	    setBackground(Color.GRAY);
		setVisible(false);
	}

	
	public void createAndPlaceBombs(int squaresonboard,double bombfraction){
		CellDataForGame.isDeactivated=false;
		cellData = new CellDataForGame(squaresonboard,bombfraction);
		CreateRandomBoard();
	}
	
	public void CreateRandomBoard(){
		Cell currentcell;
		cellArray=cellData.getCellArray();
		for (int i=0;i<squaresOnBoard;i++){
			for (int j=0;j<squaresOnBoard;j++){
			   currentcell = cellArray[i][j];
	           currentcell.setHorizontalAlignment(JLabel.CENTER);
	           add(currentcell, JLabel.CENTER);
	           currentcell.addMouseListener(
		        		   new clickbehaviour(this)
		        		   );
	    	}
		}

	}
	
    
	public ImageIcon generateIcon(Cell c){

		ImageIcon imageicon=null;
		if(c.returnSecondCoordinate()==2&&c.returnFirstCoordinate()==2){System.out.println("exception");System.out.println(c.getException());System.out.println("numberBombs");System.out.println(c.retrieveDistance());System.out.println("second");System.out.println(c.returnSecondCoordinate());System.out.println("first");System.out.println(c.returnFirstCoordinate());};
		if(c.getException()==false&&c.retrieveDoesBombExist()==1 && c.getHidden()==false){ 
	    	imageicon=BombImage;
	        c.setIcon(imageicon); 
		}else if(c.getException()==false&&c.getHidden()==false&&c.retrieveDoesBombExist()==1){    
			imageicon=cellData.returnImageFromDatabase(c.retrieveDistance());
	        c.setIcon(imageicon); 
		}else if(c.getException()==true){//&&c.retrieveDoesBombExist()==1
			
			if(c.retrieveDistance()==0){
				//imageicon=image_0;
				imageicon=Clear;
	    	}else{
	    		imageicon=cellData.returnImageFromDatabase(c.retrieveDistance());
	    	}
	        c.setIcon(imageicon);  
	    }else if(c.getCellIsClicked()==false&&c.getHidden()==false&&c.retrieveDoesBombExist()==0){
            imageicon=c.getImage();
            c.setIcon(imageicon);
	    }else if(c.getHidden()==false){    
       		imageicon=Clear;	    	
	    	c.setIcon(imageicon); 
	    }else{
	        imageicon=Hidden;
	        c.setIcon(imageicon); 
	    }
			return imageicon;

	}

	public void setupBackgroundVariables(int squaresonboard,double bombFraction){
		fractionBombs=bombFraction;
		squaresOnBoard=squaresonboard;
	}
	
	public void	sendDimensionsToCellData(){
		int width=getWidth();
		int height=getHeight()-offSet;
		int[] dim = {width,height};
		cellData.setBoardDimensions(dim);
	}
		
	public int[] setAndNormaliseIconsToBoardSize(int offset){
    invalidate();
    int[] dimensions = new int[2];
    dimensions[0]=getWidth();
    dimensions[1]=getHeight();
    try{
    	cellData.CreateImageDatabase();
		cellData.changeImageDatabase(getWidth(),getHeight());
		cellData.setAppropriateImages(cellArray);
		Clear=cellData.returnImageFromDatabase(0);
		image_0=cellData.returnImageFromDatabase(-3);
		Hidden=cellData.returnImageFromDatabase(-2);
		BombImage=cellData.returnImageFromDatabase(-1);
		for (int i=0;i<squaresOnBoard;i++){
	    	for (int j=0;j<squaresOnBoard;j++){
	    		      generateIcon(cellArray[i][j]);
			}
	    }
    validate();
    repaint();
    }catch(Exception e){
    	
    	
    	commandFeed.println("Images are too small. Try making the screen larger.");
    }
    return dimensions;
    }
	
	
	public void deleteCellData(){
		cellData=null;
	}

	int offSet;
	informationFeed commandFeed;
	double fractionBombs;
	int squaresOnBoard;
	Cell[][] cellArray;
    CellDataForGame cellData;
    ImageIcon Hidden;
    ImageIcon image_0;
    int Clicks=0;
    ImageIcon Clear;
	ImageIcon BombImage;
	}

