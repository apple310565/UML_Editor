import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

//E = 1, S = 2, W = 3, N = 4
class relation{
	protected objects f, t;
	protected int f_direct,t_direct;
	protected int size = 10;
	protected void pp(Graphics g) {
		int [] f_point = f.get_point(f_direct);
		int [] t_point = t.get_point(t_direct);
		g.fillRect(f_point[0],f_point[1],size,size);
		g.fillRect(t_point[0],t_point[1],size,size);
		double [][] Revise = {{1,0.5},{0.5,1},{0,0.5},{0.5,0}};//為了讓箭頭漂亮的指在方塊上方
		pp_arrow(g,f_point[0]+(int)(Revise[f_direct-1][0]*size),f_point[1]+(int)(Revise[f_direct-1][1]*size),
				t_point[0]+(int)(Revise[t_direct-1][0]*size),t_point[1]+(int)(Revise[t_direct-1][1]*size));
	}
	protected void pp_arrow(Graphics g,int f_x,int f_y,int t_x,int t_y){}//留給sub class實作的特異化箭頭
	
	relation(objects f,objects t,int f_direct,int t_direct){
		this.f=f;
		this.t=t;
		this.f_direct=f_direct;
		this.t_direct=t_direct;
	}
}
class association_relation extends relation{
	association_relation(objects f, objects t, int f_direct, int t_direct) {
		super(f, t, f_direct, t_direct);
	}
	protected void pp_arrow(Graphics g,int f_x,int f_y,int t_x,int t_y){
		//畫箭頭
		int arrow_size=30;
		double  t= arrow_size/Math.sqrt(((f_x-t_x)*(f_x-t_x)+(f_y-t_y)*(f_y-t_y)));
		int tmp_x,tmp_y,a1_x,a1_y,a2_x,a2_y;
		tmp_x=(int)((f_x-t_x)*t+t_x);
		tmp_y=(int)((f_y-t_y)*t+t_y);
		double degrees = 45.0;
        double radians = Math.toRadians(degrees);
		a1_x = (int)(Math.cos(radians)*(tmp_x-t_x)-Math.sin(radians)*(tmp_y-t_y))+t_x;
		a1_y = (int)(Math.sin(radians)*(tmp_x-t_x)+Math.cos(radians)*(tmp_y-t_y))+t_y;
		degrees = -45.0;
		radians = Math.toRadians(degrees);
		a2_x = (int)(Math.cos(radians)*(tmp_x-t_x)-Math.sin(radians)*(tmp_y-t_y))+t_x;
		a2_y = (int)(Math.sin(radians)*(tmp_x-t_x)+Math.cos(radians)*(tmp_y-t_y))+t_y;
		g.drawLine(t_x, t_y, f_x, f_y);
		g.drawLine(a1_x,a1_y,t_x,t_y);
		g.drawLine(a2_x,a2_y,t_x,t_y);
	}
}

class generalization_relation extends relation{
	generalization_relation(objects f, objects t, int f_direct, int t_direct) {
		super(f, t, f_direct, t_direct);
	}
	protected void pp_arrow(Graphics g,int f_x,int f_y,int t_x,int t_y){
		//畫箭頭
		int arrow_size=20;
		double  t= arrow_size/Math.sqrt(((f_x-t_x)*(f_x-t_x)+(f_y-t_y)*(f_y-t_y)));
		int tmp_x,tmp_y,a1_x,a1_y,a2_x,a2_y;
		tmp_x=(int)((f_x-t_x)*t+t_x);
		tmp_y=(int)((f_y-t_y)*t+t_y);
		double degrees = 45.0;
        double radians = Math.toRadians(degrees);
		a1_x = (int)(Math.cos(radians)*(tmp_x-t_x)-Math.sin(radians)*(tmp_y-t_y))+t_x;
		a1_y = (int)(Math.sin(radians)*(tmp_x-t_x)+Math.cos(radians)*(tmp_y-t_y))+t_y;
		degrees = -45.0;
		radians = Math.toRadians(degrees);
		a2_x = (int)(Math.cos(radians)*(tmp_x-t_x)-Math.sin(radians)*(tmp_y-t_y))+t_x;
		a2_y = (int)(Math.sin(radians)*(tmp_x-t_x)+Math.cos(radians)*(tmp_y-t_y))+t_y;
		tmp_x=(int)((f_x-t_x)*t*(1/Math.sqrt(2))+t_x);
		tmp_y=(int)((f_y-t_y)*t*(1/Math.sqrt(2))+t_y);
		g.drawLine(tmp_x, tmp_y, f_x, f_y);
		g.drawLine(a1_x,a1_y,t_x,t_y);
		g.drawLine(a2_x,a2_y,t_x,t_y);
		g.drawLine(a2_x,a2_y,a1_x,a1_y);
	}
}

class composition_relation extends relation{
	composition_relation(objects f, objects t, int f_direct, int t_direct) {
		super(f, t, f_direct, t_direct);
	}
	protected void pp_arrow(Graphics g,int f_x,int f_y,int t_x,int t_y){
		//畫箭頭
		int arrow_size=15;
		double  t= arrow_size/Math.sqrt(((f_x-t_x)*(f_x-t_x)+(f_y-t_y)*(f_y-t_y)));
		int tmp_x,tmp_y,a1_x,a1_y,a2_x,a2_y;
		tmp_x=(int)((f_x-t_x)*t+t_x);
		tmp_y=(int)((f_y-t_y)*t+t_y);
		double degrees = 35.0;
        double radians = Math.toRadians(degrees);
		a1_x = (int)(Math.cos(radians)*(tmp_x-t_x)-Math.sin(radians)*(tmp_y-t_y))+t_x;
		a1_y = (int)(Math.sin(radians)*(tmp_x-t_x)+Math.cos(radians)*(tmp_y-t_y))+t_y;
		degrees = -35.0;
		radians = Math.toRadians(degrees);
		a2_x = (int)(Math.cos(radians)*(tmp_x-t_x)-Math.sin(radians)*(tmp_y-t_y))+t_x;
		a2_y = (int)(Math.sin(radians)*(tmp_x-t_x)+Math.cos(radians)*(tmp_y-t_y))+t_y;
		tmp_x=(int)((f_x-t_x)*t*(2/Math.sqrt(2))+t_x);
		tmp_y=(int)((f_y-t_y)*t*(2/Math.sqrt(2))+t_y);
		g.drawLine(tmp_x, tmp_y, f_x, f_y);
		g.drawLine(a1_x,a1_y,t_x,t_y);
		g.drawLine(a2_x,a2_y,t_x,t_y);
		g.drawLine(tmp_x,tmp_y,a1_x,a1_y);
		g.drawLine(tmp_x,tmp_y,a2_x,a2_y);
	}
}