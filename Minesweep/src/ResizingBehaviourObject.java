import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ResizingBehaviourObject  implements ComponentListener{
	
	Board minegrid;
	ResizingBehaviourObject(Board b){
		minegrid=b;
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) {
	}
	
	@Override
	public void componentMoved(ComponentEvent arg0) {
	}
	
	@Override
	public void componentResized(ComponentEvent event) {
		UserInterface ui=(UserInterface)event.getSource();
		@SuppressWarnings("unused")
		int[] dimensions = new int[2];
		minegrid.setAndNormaliseIconsToBoardSize(minegrid.getOffset());
		minegrid.sendDimensionsToCellData();
		ui.updateControlAndComponentDimension();
	}
	
	@Override
	public void componentShown(ComponentEvent arg0) {
	}

}
