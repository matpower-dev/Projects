import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class CellDataForGame {

Cell[][] cellArray;
ImageIcon[] imageDatabase;
int lengthSquareBoard;
double fractionBombs;
int width=900;
int height=80;
public static boolean isDeactivated=false;

	public boolean getIsDeactivated(){
		return isDeactivated;
	}

    public ImageIcon resizeImageIcon(int w,int h,ImageIcon imageicon){
	    Image image;
	    Image newimage;
	    image = imageicon.getImage();
	    newimage = image.getScaledInstance(w/lengthSquareBoard-w/(lengthSquareBoard*14),h/lengthSquareBoard-h/(lengthSquareBoard*14), java.awt.Image.SCALE_SMOOTH);
	    imageicon = new ImageIcon(newimage); 
        return imageicon; 
    }

    public void setBoardDimensions(int[] bd){
    	for(int i=0;i<lengthSquareBoard;i++){
    		for(int j=0;j<lengthSquareBoard;j++){
    	           cellArray[i][j].setFrameDimensions(bd);
    		}
    	}
    }
    
    public ImageIcon returnImageFromDatabase(int s){
    	ImageIcon iconforreturn=null;
    	switch(s){
    	case 0: iconforreturn=imageDatabase[0];
    	break;
    	case 1: iconforreturn=imageDatabase[1];
    	break;
    	case 2: iconforreturn=imageDatabase[2];
    	break;
    	case 3: iconforreturn=imageDatabase[3];
    	break;
    	case 4: iconforreturn=imageDatabase[4];
    	break;
    	case -1: iconforreturn=imageDatabase[5];//bomb
    	break;
    	case -2: iconforreturn=imageDatabase[6];//hidden
    	break;
    	case -3: iconforreturn=imageDatabase[7];//exception
    	}
    	return iconforreturn;
    }
    
	public CellDataForGame(int gridsize,double bombFraction){
		int[][] randomNumberMatrix;
		lengthSquareBoard=gridsize;
		fractionBombs=bombFraction;
		cellArray=setupCellData(lengthSquareBoard);
		randomNumberMatrix = new int[lengthSquareBoard][lengthSquareBoard];
		randomNumberMatrix=createRandomNumbersForReference(randomNumberMatrix,lengthSquareBoard,fractionBombs);
		StoreBombDataInAnArray(randomNumberMatrix,cellArray);
		imageDatabase = new ImageIcon[8];
		imageDatabase = CreateImageDatabase();
		setAppropriateImages(cellArray);
	}
	
    public void setAppropriateImages(Cell[][] cd){
	    for (int i=0;i<lengthSquareBoard;i++){
	     	for (int j=0;j<lengthSquareBoard;j++){
	              getNeighbourStatusAndSetImage(cd[i][j],imageDatabase);
	        }
        }
    }
    
    public void changeImageDatabase(int w,int h){
    	for (int i=0;i<8;i++){
    		imageDatabase[i]=resizeImageIcon(w,h,imageDatabase[i]);
    	}
    }
    
   public void getNeighbourStatusAndSetImage(Cell c,ImageIcon[] imagedatabase){
    	ImageIcon tempimage = null;
    	int status=c.retrieveDistance();
    	switch(status){
    	case 0: tempimage=imagedatabase[0];
    	break;
    	case 1: tempimage=imagedatabase[1];
    	break;
    	case 2: tempimage=imagedatabase[2];
    	break;
    	case 3: tempimage=imagedatabase[3];
    	break;
    	case 4: tempimage=imagedatabase[4];
    	break;
    	case -1: tempimage= imagedatabase[5];
    	break;
    	case -2: tempimage=imagedatabase[6];
    	}
		c.setImage(tempimage);
    }
	
	public ImageIcon[] CreateImageDatabase(){
		imageDatabase[0]= new ImageIcon("/Users/Matthew/Desktop/467948_900.jpg");
		imageDatabase[1]= new ImageIcon("/Users/Matthew/Desktop/Folders/Java/eclipse/1.png");
	    imageDatabase[2]= new ImageIcon("/Users/Matthew/Desktop/Folders/Java/eclipse/2.png");
	    imageDatabase[3]= new ImageIcon("/Users/Matthew/Desktop/Folders/Java/eclipse/3.png");
	    imageDatabase[4]= new ImageIcon("/Users/Matthew/Desktop/Folders/Java/eclipse/4.png");
	    imageDatabase[5]= new ImageIcon("/Users/Matthew/Desktop/Folders/Java/eclipse/bomb.jpg");
	    imageDatabase[6]= new ImageIcon("/Users/Matthew/Desktop/Folders/Java/eclipse/Hidden.jpg");
	    imageDatabase[7]= new ImageIcon("/Users/Matthew/Desktop/Folders/Java/eclipse/image_0.jpg");
	    //imageDatabase[7]= new ImageIcon();
        return imageDatabase;
	}
	
	public int[][] createRandomNumbersForReference(int[][] randomnumbermatrix,int length,double bombfraction){
		Random random=new Random();
	    for (int i=0;i<length;i++){
	    	for (int j=0;j<length;j++){
	              randomnumbermatrix[i][j]=randInt(random,(int)0.0,(int)(100000.0/bombfraction));
	    	}
	    }
	    return randomnumbermatrix;
	}
	
	public static int randInt(Random random,int min, int max) {
	    int randomwithinlimit = random.nextInt((max - min) + 1) + min;
	    return randomwithinlimit;
	}
	
	public Cell[][] setupCellData(int length){
		Cell[][] c = new Cell[length][length];		
		for (int i=0;i<length;i++){
			for (int j=0;j<length;j++){
				c[i][j]= new Cell(i,j,length,fractionBombs);
				c[i][j].setCellArray(c);
			}
		}
		return c;
	}
	
	public Cell[][] getCellArray(){
		return cellArray;
	}
	
	public void StoreBombDataInAnArray(int[][] randomMatrix,Cell[][] c){
		Cell tempcell;
		for (int i=0;i<lengthSquareBoard;i++){
			for (int j=0;j<lengthSquareBoard;j++){
				if(randomMatrix[i][j]<100000){
					//BOMB
					tempcell = c[i][j];
					tempcell.setBombExists();
					fillInNeighbours(c,i,j);
				}
			}
		}
	}
	
	public void fillInNeighbours(Cell[][] c,int i,int j){
		   fillIn(c,i+1,j);
		   fillIn(c,i-1,j);
		   fillIn(c,i,j+1);
		   fillIn(c,i,j-1);
	}
	
	public void fillIn(Cell[][] c,int i,int j){
		if (i>-1){
			if(i<lengthSquareBoard){
				if(j>-1){
					if(j<lengthSquareBoard){
						    c[i][j].setDistance(1+c[i][j].retrieveDistance());
					}
				}
			}
		}
	}
}
