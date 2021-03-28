
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Cell extends JLabel{

	private static final long serialVersionUID = 1L;
int bombExists;
int bombDistance;
boolean Hidden;
int gridRow;
int gridColumn;
int[] gridCoordinates;
Cell[][] cellArray;
ImageIcon associatedIcon;
int[] frameDimensions;
int lengthSquareBoard;
CellDataForGame cellDataContainer;
double bombFraction;
boolean cellIsClicked;
boolean singleCellReveal;
boolean exceptionStatus=false;

    public CellDataForGame getCellData(){
	    return cellDataContainer;
    }

    public void receiveCellDataForGameObject(CellDataForGame input){
    	cellDataContainer=input;
    }
    
    public boolean getException(){
    	return exceptionStatus;
    }
    
    public void markAsException(boolean input){
    	exceptionStatus=input;
    }
    
    public Cell(int i,int j,int gridsize,double bombfraction){
    	frameDimensions=new int[2];
    	bombDistance=0;
    	bombExists=0;
    	bombFraction=bombfraction;
        lengthSquareBoard=gridsize;
        Hidden =true;
	    gridRow=i;
	    gridColumn=j;
	    gridCoordinates=new int[2];
	    cellIsClicked=false;
	    singleCellReveal=false;
	    

    }
    
    
    public double getBombFraction(){
    	return bombFraction;
    }
    
    public int getSquaresOnBoard(){
    	return lengthSquareBoard;
    }
    
	public void setFrameDimensions(int[] x){
		frameDimensions=x;
	}
	
	public void setCellIsClicked(boolean bool){
		cellIsClicked = bool;
	}
	
	public boolean getCellIsClicked(){
		return cellIsClicked;
	}
	
	public int[] getFrameDimensions(){
		return frameDimensions;
	}
	
    public Cell[][] getCellArray(){
    	return cellArray;
    }
    
    public void setCellArray(Cell[][] x){
    	cellArray=x;
    }
    
    public void setImage(ImageIcon x){
    	associatedIcon=x;
    }
    
    public ImageIcon getImage(){
    	return associatedIcon;
    }
    
	
    public int returnFirstCoordinate(){
    	return gridRow;
    }
    
    public int[] returnCoords(){
    	gridCoordinates[0]=gridRow;
    	gridCoordinates[1]=gridColumn;
    	return gridCoordinates;
    }
    
    public int returnSecondCoordinate(){
    	return gridColumn;
    }
    
	public void setBombExists(){
        bombExists=1;
	}
	
	public void setDistance(int x){
		bombDistance=x;
	}
	
	public int retrieveDistance(){
		return bombDistance;
	}
	
	public int retrieveDoesBombExist(){
		return bombExists;
	}
	
	public boolean getHidden(){
		return Hidden;
	}
	
	public void setHidden(boolean x){
        Hidden=x;
	}
	

}

