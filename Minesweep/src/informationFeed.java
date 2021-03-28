
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;


public class informationFeed extends JPanel {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JTextArea display;
	
	
	public informationFeed(int pixelwidth,int pixelheight){
	int textwidth=(int)(pixelwidth*11.0/400);
	display = new JTextArea ( 4, textwidth );
	DefaultCaret caret = (DefaultCaret)display.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    setBackground(Color.GRAY);
    display.setLineWrap(true);
    display.setFont(new Font("SansSerif", Font.PLAIN, 10));
    display.setEditable ( false ); // set textArea non-editable
    display.getCaret().setVisible(true);
    JScrollPane scroll = new JScrollPane ( display );
    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    scroll.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
    add ( scroll );
    setSize(display.getSize());
    setPreferredSize(display.getSize());
	}
	
	
	public void message(String messagetoprint){
		display.append(messagetoprint+"\n");
	}
	
	public void println(String messagetoprint){
		display.append(messagetoprint+"\n");
	}


	public void updateInformationFeed(int[] dimensions,int offset) {
		int width=dimensions[0];
		int height=dimensions[1];
		@SuppressWarnings("unused")
		int textheight=(int)((height)*11.0/(400));
		int textwidth=(int)(width*11.0/400);
		display.setColumns(textwidth);
		//display.setFont(new Font("SansSerif", Font.PLAIN, dimensions[0]*10/800));

	    setSize(display.getSize());
	    setPreferredSize(display.getSize());
		
		
	}
	
	
	
}
