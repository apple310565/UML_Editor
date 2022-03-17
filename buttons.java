import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;

class buttons {
	public boolean selected = false;
	protected JButton mybutton;
	protected buttons(JButton b){
		this.mybutton=b;
		this.mybutton.setBackground(Color.WHITE);
	}
	protected void click_btn(myCanvas c) {
		//取消選取所有物件
		for(int i=0;i<c.myArrayList.size();i++) {
			c.myArrayList.get(i).unselected();
		}
		c.repaint();
		//變換按鈕的型態及切mode
		if(this.selected==true) {
			this.selected=false;
			c.mode = null;
			this.mybutton.setBackground(Color.WHITE);
		}
		else {
			if(c.mode!=null) {
				c.mode.unselected();
			}
			c.mode = this;
			this.selected=true;
			this.mybutton.setBackground(Color.BLACK);
		}
	}
	protected void unselected() {
		this.selected=false;
		this.mybutton.setBackground(Color.WHITE);
	}
	protected void mouseClicked(myCanvas c){}
	protected void mouseDragged(myCanvas c) {}
	protected void mousePressed(myCanvas c) {}
	protected void mouseReleased(myCanvas c) {}

}

class select_btn extends  buttons {
	public select_btn(JButton b){
		super(b);
	}
	protected void mouseDragged(myCanvas c) {
		objects tmp=null;
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			if(c.myArrayList.get(i).selected) {
				tmp = c.myArrayList.get(i);
				break;
			}
		}
		if(tmp!=null && c.dragged_x>0 &&c.dragged_y>0 &&(c.dragged_x!=c.x || c.dragged_y != c.y)) {
			//tmp.x=c.x;
			//tmp.y=c.y;
			tmp.move(c.dragged_x, c.dragged_y, c.x, c.y);
			c.repaint();
		}
	}
	protected void mouseReleased(myCanvas c) {
		objects tmp=null;
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			if(c.myArrayList.get(i).selected) {
				tmp = c.myArrayList.get(i);
				break;
			}
		}
		if(tmp == null) {
			for(int i=c.myArrayList.size()-1;i>=0;i--) {
				if(c.myArrayList.get(i).is_dominate(c.pressed_x,c.pressed_y,c.x,c.y)) {
					c.myArrayList.get(i).selected();
				}
			}
			c.repaint();
		}
	}
	protected void mousePressed(myCanvas c) {
		objects tmp=null;
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			if(c.myArrayList.get(i).is_in(c.x, c.y)) {
				tmp = c.myArrayList.get(i);
				break;
			}
		}
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			c.myArrayList.get(i).unselected();
		}
		if(tmp!=null)tmp.selected();
		c.repaint();
	}
}

class association_btn extends  buttons {
	public association_btn(JButton b){
		super(b);
	}
	protected void mouseReleased(myCanvas c) {
		objects f = null, t=null;
		int f_direct=-1,t_direct=-1;
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			if(c.myArrayList.get(i).is_in(c.x, c.y)) {
				t = c.myArrayList.get(i).get_base_object(c.x, c.y);
				t_direct = t.get_direct(c.x, c.y);
				break;
			}
		}
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			if(c.myArrayList.get(i).is_in(c.pressed_x, c.pressed_y)) {
				f = c.myArrayList.get(i).get_base_object(c.pressed_x, c.pressed_y);
				f_direct = f.get_direct(c.pressed_x, c.pressed_y);
				break;
			}
		}
		if(f!=null && t!=null && f!=t) {
			relation tmp = new association_relation(f,t,f_direct,t_direct);
			c.myRelations.add(tmp);
			c.repaint();
		}
	}
}

class generalization_btn extends  buttons {
	public generalization_btn(JButton b){
		super(b);
	}
	protected void mouseReleased(myCanvas c) {
		objects f = null, t=null;
		int f_direct=-1,t_direct=-1;
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			if(c.myArrayList.get(i).is_in(c.x, c.y)) {
				t = c.myArrayList.get(i).get_base_object(c.x, c.y);
				t_direct = t.get_direct(c.x, c.y);
				break;
			}
		}
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			if(c.myArrayList.get(i).is_in(c.pressed_x, c.pressed_y)) {
				f = c.myArrayList.get(i).get_base_object(c.pressed_x, c.pressed_y);
				f_direct = f.get_direct(c.pressed_x, c.pressed_y);
				break;
			}
		}
		if(f!=null && t!=null && f!=t) {
			relation tmp = new generalization_relation(f,t,f_direct,t_direct);
			c.myRelations.add(tmp);
			c.repaint();
		}
	}
}

class composition_btn extends  buttons {
	public composition_btn(JButton b){
		super(b);
	}
	protected void mouseReleased(myCanvas c) {
		objects f = null, t=null;
		int f_direct=-1,t_direct=-1;
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			if(c.myArrayList.get(i).is_in(c.x, c.y)) {
				t = c.myArrayList.get(i).get_base_object(c.x, c.y);
				t_direct = t.get_direct(c.x, c.y);
				break;
			}
		}
		for(int i=c.myArrayList.size()-1;i>=0;i--) {
			if(c.myArrayList.get(i).is_in(c.pressed_x, c.pressed_y)) {
				f = c.myArrayList.get(i).get_base_object(c.pressed_x, c.pressed_y);
				f_direct = f.get_direct(c.pressed_x, c.pressed_y);
				break;
			}
		}
		if(f!=null && t!=null && f!=t) {
			relation tmp = new composition_relation(f,t,f_direct,t_direct);
			c.myRelations.add(tmp);
			c.repaint();
		}
	}
}

class class_btn extends  buttons {
	public class_btn(JButton b){
		super(b);
	}
	protected void mouseClicked(myCanvas c) {
		Class tmp = new Class(c.x,c.y);
		c.myArrayList.add(tmp);
		c.repaint();
	}
}

class use_case_btn extends  buttons {
	public use_case_btn(JButton b){
		super(b);
	}
	protected void mouseClicked(myCanvas c){
		use_case tmp = new use_case(c.x,c.y);
		c.myArrayList.add(tmp);
		c.repaint();
	}
}