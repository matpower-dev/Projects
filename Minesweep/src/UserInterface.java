import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class UserInterface extends JFrame implements ActionListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
ControlPanel panel;
informationFeed commandFeed;
int offSet;
ResizingBehaviourObject currentListener;

     public UserInterface(){
           super("Minesweeper board"); 
 	       int squaresOnBoard=30;
 	       int pixelWidth=800;
 	       int pixelHeight=800;
 	       double bombFraction=0.2;
 	       int offset=80;
 	       offSet=offset;
 	       commandFeed= new informationFeed(pixelWidth,offset);
 		setIgnoreRepaint( true ) ;
 	       commandFeed.println("Initializing board with:\n"+squaresOnBoard+" board divisions,\n"+"Bomb fraction:"+bombFraction);
           panel=new ControlPanel(pixelWidth,offset);
	       setUpInitialFrameBehaviourAndShow(pixelWidth,pixelHeight);
           Board mineGrid=createNewBoardToFrameAndUpdateControlPane(pixelWidth,pixelHeight,squaresOnBoard,bombFraction,offset);
	       sendCommandBoxReferenceToBoard(mineGrid,commandFeed);
	       sendCommandBoxReferenceToPanel(panel,commandFeed);
	       panel.addCommandBoxToLayout(commandFeed);
 	       invalidate();
	       add(panel,BorderLayout.SOUTH);
	       addTheBoardToFrame(mineGrid,offset);
	       validate();
	       repaint();
	    setIgnoreRepaint( false ) ;
     }

     public void sendCommandBoxReferenceToBoard(Board minegrid,informationFeed commandfeed){
  	     minegrid.getFeed(commandfeed);
     }
     
     public void sendCommandBoxReferenceToPanel(ControlPanel panel,informationFeed commandfeed){
  	     panel.getFeed(commandfeed);
     }
     
     public ResizingBehaviourObject DefineResizingBehaviourOfFrame(Board board){
    	 ResizingBehaviourObject listener;
           addComponentListener(
        		   listener=new ResizingBehaviourObject(board)
                 );
		return listener;
       }
     
     
     public void updateControlAndComponentDimension(){
    	 int[] dimensions = new int[2];
    	 dimensions[0]=getWidth();
    	 dimensions[1]=getHeight();
    	 panel.updateControlAndComponentDimension(dimensions,offSet);
     }

     public Board setUpTheBoard(int squaresonboard,double bombfraction,int framesizewidth,int framesizeheight,int offset){
           return new Board(squaresonboard,bombfraction,framesizewidth,framesizeheight,offset);
     }


     public void addTheBoardToFrame(Board b,int offset){
           add(b,BorderLayout.CENTER);
           b.setAndNormaliseIconsToBoardSize(offset);
           setVisible(true);
     }



     public void setUpInitialFrameBehaviourAndShow(int framepixelwidth,int framepixelheight){
           setSize(framepixelwidth,framepixelheight);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	public Board createNewBoardToFrameAndUpdateControlPane(int framepixelwidth,int framepixelheight,int squaresonboard,double bombFraction,int offset){
           Board minegrid;
	       minegrid=setUpTheBoard(squaresonboard,bombFraction,framepixelwidth,framepixelheight,offset);
	       minegrid.setVisible(true);
	       currentListener=DefineResizingBehaviourOfFrame(minegrid);
	       panel.getFrame(this);
	       panel.getBoard(minegrid);
	       panel.getBombFraction(bombFraction);
	       panel.getBoardWidth(framepixelwidth);
	       panel.getGridSize(squaresonboard);
           return minegrid;
	}
	
	public Board createANewBoardOnTheCanvas(int pixelwidth,int pixelheight,int squaresonboard,double bombfraction,int offset){

	       int squaresOnBoard=squaresonboard;
	       double bombFraction=bombfraction;
	       //invalidate();
           Board mineGrid=createNewBoardToFrameAndUpdateControlPane(pixelwidth,pixelheight,squaresOnBoard,bombFraction,offset);
	       sendCommandBoxReferenceToBoard(mineGrid,commandFeed);
	       sendCommandBoxReferenceToPanel(panel,commandFeed);
	       addTheBoardToFrame(mineGrid,offset);
		   mineGrid.setAndNormaliseIconsToBoardSize(offset);
		   mineGrid.sendDimensionsToCellData();
		   //pack(); //this breaks the code.
	   	   return mineGrid;
	}
	
	
	public void removeBoardPanelAndCells(Board board,ControlPanel panel){
		remove(board);
	    getContentPane().invalidate();
	    getContentPane().validate();
	    board.removeAllCells();
	    board.deleteCellData();
	    board=null;
	    panel=null;
	    removeComponentListener(currentListener);
	}

	
}
