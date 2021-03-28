import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	informationFeed commandFeed;
	UserInterface userinterface;
	ControlPanel panel=this;
	Board board;
	JTextField squaresOnBoardButton;
	JTextField changeDifficultyButton;
	int panelPixelWidth;
	int panelPixelHeight;
    int squaresOnBoard; 
    int framePixelWidth;
    int framePixelHeight;
    double bombFraction;
    int boardPixelWidth;
	
	public ControlPanel(int panelpixelwidth,int panelpixelheight){
		panelPixelWidth=panelpixelwidth;
		panelPixelHeight=panelpixelheight;
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(panelpixelwidth, panelpixelheight));
	    setBackground(Color.GRAY);
		setVisible(true);
        JButton newgamebutton = new JButton("NEW GAME");
        newgamebutton.setPreferredSize(new Dimension(panelpixelwidth/3, panelpixelheight/2));
        newgamebutton.addActionListener(this);
        changeDifficultyButton=new JTextField("Enter the percentage of bombs");
        changeDifficultyButton.setPreferredSize(new Dimension(panelpixelwidth/3, panelpixelheight/2));
        changeDifficultyButton.setColumns(panelPixelWidth*21/900);
        changeDifficultyButton.setForeground(Color.GRAY);
        Font font = new Font("SansSerif", Font.PLAIN, panelPixelWidth*12/900);
        changeDifficultyButton.setFont(font);
        changeDifficultyButton.addActionListener(
        		new ActionListener(){
        	           public void actionPerformed(ActionEvent a){
        	        	   double userdifficultyinput = 0.0;
        	        	         try{ 
             	        	          userdifficultyinput=Double.valueOf(changeDifficultyButton.getText());
             	     	        	  if (userdifficultyinput <= -0.0|| userdifficultyinput>1) {
             	     	        	       throw new IllegalArgumentException();
             	     	        	   }
                  	        	      userinterface.invalidate();
             	        	          int squaresonboard=squaresOnBoard;
                  	        	      userinterface.removeBoardPanelAndCells(board,panel);
                	        	      userinterface.createANewBoardOnTheCanvas(framePixelWidth,framePixelHeight,squaresonboard,userdifficultyinput,panelPixelHeight);
                	        	      System.out.println("BOARD SET UP");
                	        	      bombFraction=userdifficultyinput;
                	        	      commandFeed.println("Initializing board with:\n"+squaresOnBoard+" board divisions,\n"+"Bomb fraction:"+userdifficultyinput);
                	        	      changeDifficultyButton.setText("");
                	        	      userinterface.validate();
                	        	      userinterface.repaint();
        	        		     }catch (Exception e) {
        	        		          commandFeed.println("The value you have input is not a valid double");
        	        		     }
        	           }
                }
        );
        changeDifficultyButton.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            	changeDifficultyButton.setText("");
            	changeDifficultyButton.setForeground(Color.BLACK);
            }
            public void focusLost(FocusEvent e) {
            	changeDifficultyButton.setText("Change Bomb Frequency");
            	changeDifficultyButton.setForeground(Color.GRAY);
			}
        });
      
        squaresOnBoardButton = new JTextField("Enter the number of rows and columns");
        squaresOnBoardButton.setForeground(Color.GRAY);
        squaresOnBoardButton.setPreferredSize(new Dimension(panelPixelWidth/3, panelPixelHeight/2));
        squaresOnBoardButton.setColumns(panelPixelWidth*21/900);
        squaresOnBoardButton.setFont(font);
        squaresOnBoardButton.addActionListener(
        		new ActionListener(){
     	           public void actionPerformed(ActionEvent a){
     	        	   Scanner scanner = new Scanner(squaresOnBoardButton.getText());
     	        	   @SuppressWarnings("unused")
					   int i;
     	        	   int usergridinput = 0;
     	        	   try{
     	        		   while(scanner.hasNext()){
     	        			   i=scanner.nextInt(); 
     	        		   }
     	        		   usergridinput=Integer.parseInt(squaresOnBoardButton.getText());
     	        		   if (usergridinput < 0) {
     	        			   throw new IllegalArgumentException();
     	        		   }
     	        		   commandFeed.println("Initializing board with:\n"+usergridinput+" board divisions,\n"+"Bomb fraction:"+bombFraction);
     	        		   squaresOnBoardButton.setText("");
     	        		   userinterface.invalidate();
     	        		   userinterface.removeBoardPanelAndCells(board,panel);
     	        		   userinterface.createANewBoardOnTheCanvas(framePixelWidth,framePixelHeight,usergridinput,bombFraction,panelPixelHeight);
     	        		   userinterface.validate();
     	        		   userinterface.repaint();
     	        		   squaresOnBoard=usergridinput;
     	        	   }catch (Exception e) {
     	        		   commandFeed.println("The value you have input is not a valid integer");
	        		   }
     	           }
        		}
        );
        
        squaresOnBoardButton.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            	squaresOnBoardButton.setText("");
            	squaresOnBoardButton.setForeground(Color.BLACK);
            }
            public void focusLost(FocusEvent e) {
            	squaresOnBoardButton.setText("Change Grid Size");
            	squaresOnBoardButton.setForeground(Color.GRAY);

			}
        });
      
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        GridBagConstraints c = new GridBagConstraints();

        c.weightx=0.333;
        c.weighty=1;
        c.gridx=1;
        c.gridy=0;
        c.gridwidth=2;
        c.gridheight=2;
        c.fill = GridBagConstraints.BOTH;
        c.ipady=0;
        c.ipadx=0;
        
        add(newgamebutton, c);
        
        c = new GridBagConstraints();
        
        c.weightx=0.333;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.fill = GridBagConstraints.BOTH;
        
        add(changeDifficultyButton,c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx=0.333;
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=1;

        add(squaresOnBoardButton,c);
	}
    
    public void getBoard(Board b){
    	board=b;
    }
    
    public void getBombFraction(double bombfraction){
    	bombFraction=bombfraction;
    }
    
    public int getPixelWidth(int panelpixelwidth){
    	return panelpixelwidth;
    }
    
    public int getPixelHeight(int panelpixelheight){
    	return panelpixelheight;
    }
    
    public void getBoardWidth(int w){
        boardPixelWidth=w;
    }
    public void getGridSize(int gridsize){
    	squaresOnBoard=gridsize;
    }
    
    public void getFrame(UserInterface input){
    	userinterface=input;
    }
    
	@Override
	public void actionPerformed(ActionEvent event) {
		board.removeAllCells();
		board.deleteCellData();
		board.setClick(0);
		commandFeed.println("Board reset!");
		board.createAndPlaceBombs(squaresOnBoard,bombFraction);
		board.setAndNormaliseIconsToBoardSize(panelPixelHeight);      
	}
	
	public void addCommandBoxToLayout(informationFeed feed){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx=0.333;
        c.gridx=4;
        c.gridy=0;
        c.ipadx=0;
        c.gridheight=2;

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx=0.333;
        c.gridx=4;
        c.gridy=0;
        c.ipadx=0;

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx=0.333;
        c.gridx=3;
        c.gridy=0;
        c.ipadx=0;
        c.gridheight=2;
        add(feed,c);
        
    }
	
	public void getFeed(informationFeed feed){
		commandFeed=feed;
	}
	
	public void updateControlAndComponentDimension(int[] dimensions,int offset){
		commandFeed.updateInformationFeed(dimensions,offset);
		changeDifficultyButton.setColumns((int) (dimensions[0]*21.0/900));
        squaresOnBoardButton.setColumns((int) (dimensions[0]*21.0/900));
        Font font = new Font("SansSerif", Font.PLAIN, dimensions[0]*12/900);
        squaresOnBoardButton.setFont(font);
        changeDifficultyButton.setFont(font);
        panelPixelWidth=dimensions[0];
        framePixelWidth=dimensions[0];
        framePixelHeight=dimensions[1];
	}
}


