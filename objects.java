import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class objects {
	protected int x,y;
	protected String name = "";
	protected boolean selected;
	protected int width=0,high=0,size=0;
	protected objects(int x,int y){
		this.x = x;
		this.y = y;
	}
	protected int [] get_point(int direct) {
		int point_x=-1, point_y=-1;
		if(direct==1) {//E
			point_x = this.x+width;
			point_y = this.y+(int)(high/2)-(int)(size/2);
		}
		else if(direct==2) {//S
			point_x = this.x+(int)(width/2)-(int)(size/2);
			point_y = this.y+high;
		}
		else if(direct==3) {//W
			point_x = this.x-size;
			point_y = this.y+(int)(high/2)-(int)(size/2);
		}
		else if(direct==4) {//N
			point_x = this.x+(int)(width/2)-(int)(size/2);
			point_y = this.y-size;
		}
		int [] tmp = {point_x, point_y};
		return tmp;
	}
	protected int get_direct(int x, int y) {
		int direct = -1;
		float min = -1; //選離(x,y)距離最短的連接點
		int[][] tmp = {{this.x+width,this.y+(int)(high/2)},{this.x+(int)(width/2),this.y+high},{this.x,this.y+(int)(high/2)},{this.x+(int)(width/2),this.y}};
		for(int i=0;i<tmp.length;i++) {
			if((tmp[i][0]-x)*(tmp[i][0]-x)+(tmp[i][1]-y)*(tmp[i][1]-y) < min || min==-1) {
				min = (tmp[i][0]-x)*(tmp[i][0]-x)+(tmp[i][1]-y)*(tmp[i][1]-y);
				direct = i+1;
			}
		}
		return direct;
	}
	protected boolean is_dominate(int x1,int y1,int x2,int y2) {
		if(x1<x2) {
			int tmp = x2;
			x2 = x1;
			x1= tmp;
		}
		if(y1<y2) {
			int tmp = y2;
			y2 = y1;
			y1= tmp;
		}
		if(this.x>x2 && this.x+this.width<x1 && this.y > y2 && this.y+this.high <y1)return true;
		return false;
	}
	protected boolean is_in(int x, int y) {
		if((x <=this.x+width && x >= this.x) && (y>this.y && y<this.y+high)) {
			return true;
		}
		return false;
	}
	protected void move(int x1,int y1,int x2,int y2) {
		this.x+=x2-x1;
		this.y+=y2-y1;
	}
	protected void selected(){
		this.selected = true;
	}
	protected void unselected(){
		this.selected = false;
	}
	protected void pp(Graphics g){}
	protected void ungroup(myCanvas c){}
	protected objects get_base_object(int x,int y) {
		return this;
	}
}

class use_case extends objects {
	protected int width=100,high=60,size=10;
	protected void pp(Graphics g) {
		//畫use case
		g.drawString(name,x+20,y+25);
        g.setColor(Color.GRAY);
    	//g.fillOval(x,y,width,high);
    	g.setColor(Color.BLACK);
    	g.drawOval(x,y,width,high);
        if(this.selected) {
        	//g.drawOval(x,y,width,high);
            g.fillRect(x+(int)(width/2)-(int)(size/2), y-size, size, size);
            g.fillRect(x+(int)(width/2)-(int)(size/2), y+high, size, size);
            g.fillRect(x-size, y+(int)(high/2)-(int)(size/2), size, size);
            g.fillRect(x+width, y+(int)(high/2)-(int)(size/2), size, size);
        }
	}
	use_case(int x, int y){
		super(x,y);
		super.high=this.high;
		super.width=this.width;
		super.size=this.size;
	}
}

class Class extends objects {
	protected int width=100,high=110,size=10;
	protected void pp(Graphics g) {
		//畫class
        g.drawString(name,x+15,y+15);
        g.drawRect(x, y, 100, 50);
        g.drawRect(x, y+50, 100, 30);
        g.drawRect(x, y+80 ,100, 30);
        if(this.selected) {
        	g.fillRect(x+(int)(width/2)-(int)(size/2), y-size, size, size);
            g.fillRect(x+(int)(width/2)-(int)(size/2), y+high, size, size);
            g.fillRect(x-size, y+(int)(high/2)-(int)(size/2), size, size);
            g.fillRect(x+width, y+(int)(high/2)-(int)(size/2), size, size);
        }
	}
	Class(int x, int y){
		super(x,y);
		super.high=this.high;
		super.width=this.width;
		super.size=this.size;
	}
}

class composite extends objects {
	protected int width=0,high=0,size=10;
	protected ArrayList <objects> members = new ArrayList();
	protected composite(int x, int y, int width, int high) {
		super(x, y);
		this.width=width;
		this.high=high;
		super.high=this.high;
		super.width=this.width;
		super.size=this.size;
	}
	protected void pp(Graphics g) {
		for(int i=0;i<members.size();i++) {
			members.get(i).pp(g);
		}
	}
	protected void move(int x1,int y1,int x2,int y2) {
		this.x+=x2-x1;
		this.y+=y2-y1;
		for(int i=0;i<members.size();i++) {
			members.get(i).move(x1, y1, x2, y2);
		}
	}
	protected void selected() {
		this.selected = true;
		for(int i=0;i<members.size();i++) {
			members.get(i).selected();
		}
	}
	protected void unselected(){
		this.selected = false;
		for(int i=0;i<members.size();i++) {
			members.get(i).unselected();
		}
	}
	protected boolean is_in(int x, int y) {
		for(int i=0;i<members.size();i++) {
			if(members.get(i).is_in(x,y)) {
				return true;
			}
		}
		return false;
	}
	protected objects get_base_object(int x,int y) {
		objects o = null;
		for(int i=0;i<members.size();i++) {
			if(members.get(i).is_in(x,y)) {
				o = members.get(i).get_base_object(x, y);
			}
		}
		return o;
	}
	protected void ungroup(myCanvas c) {
		for(int i=0;i<members.size();i++) {
			c.myArrayList.add(members.get(i));
		}
		c.myArrayList.remove(this);
	}
}