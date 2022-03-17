

import java.awt.*;  
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class myCanvas extends Canvas implements MouseListener,MouseMotionListener {  
	protected buttons mode = null;
	protected ArrayList <objects> myArrayList = new  ArrayList();
	protected ArrayList <relation> myRelations = new  ArrayList();
	private myCanvas c;
	protected int x=0, y=0;
	protected int pressed_x = -1,pressed_y = -1, dragged_x=-1, dragged_y=-1;
    public myCanvas(){
        this.addMouseListener(this);
        addMouseMotionListener(this);
        c=this;
    }
    public void paint(Graphics g) {
    	g.drawString("Canvas",25,25);
    	for(int i=0;i<myArrayList.size();i++) {
    		(myArrayList.get(i)).pp(g);
    	}
    	for(int i=0;i<myRelations.size();i++) {
    		(myRelations.get(i)).pp(g);
    	}
    } 
	public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        if(mode!=null) {
        	mode.mouseClicked(c);
        }
    }
	
	public void mousePressed(MouseEvent e){
		pressed_x = e.getX();
		pressed_y = e.getY();
        x=e.getX();
        y=e.getY();
        dragged_x=e.getX();
        dragged_y=e.getY();
		if(mode!=null) {
        	mode.mousePressed(c);
        }
    }
	public void mouseReleased(MouseEvent e) {
		x=e.getX();
        y=e.getY();
        if(mode!=null) {
        	mode.mouseReleased(c);
        }
        pressed_x = -1;
		pressed_y = -1;
		dragged_x = -1;
        dragged_y = -1;
	}
	
	public void mouseDragged(MouseEvent e) {
		x=e.getX();
        y=e.getY();
        if(mode!=null) {
        	mode.mouseDragged(c);
        }
        dragged_x=e.getX();
        dragged_y=e.getY();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {		
	}
  
}  