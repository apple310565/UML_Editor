import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.sun.glass.events.WindowEvent;

public class MAIN extends JFrame implements ActionListener{
	JMenuItem Group,UnGroup,change_object_name;
	myCanvas c;
	public static void main(String[] args) {
		new MAIN();
	}
	MAIN(){
	
		//設定主視窗
        JFrame demo = new JFrame("UML 編輯器");
        demo.setSize(1000, 800);
        demo.getContentPane().setLayout(new BorderLayout());

        //載入畫布
        c = new myCanvas();
        demo.add(c, BorderLayout.CENTER);
        
        //加入上方欄(Menu)
        JMenuBar mb=new JMenuBar();  
        JMenu File_menu=new JMenu("File"); 
        JMenu Edit_menu = new JMenu("Edit");
        Group=new JMenuItem("Group");  
        UnGroup=new JMenuItem("UnGroup");
        change_object_name=new JMenuItem("change object name");  
        Edit_menu.add(Group); Edit_menu.add(UnGroup); Edit_menu.add(change_object_name);    
        mb.add(File_menu);
        mb.add(Edit_menu);
        demo.setJMenuBar(mb);
        Group.addActionListener(this);
        UnGroup.addActionListener(this);
        change_object_name.addActionListener(this);
       
        //加入左側按紐
        JPanel panel=new JPanel();  //預設為 FlowLayout
        GridLayout gird = new GridLayout(6,1);
        panel.setLayout(gird);
        JButton select_btn = new JButton(new ImageIcon("bin/img/select.png"));
        JButton association_btn = new JButton(new ImageIcon("bin/img/association.png"));
        JButton generalization_btn = new JButton(new ImageIcon("bin/img/generalization.png"));
        JButton composition_btn = new JButton(new ImageIcon("bin/img/composition.png"));
        JButton class_btn = new JButton(new ImageIcon("bin/img/class.png"));
        JButton use_case_btn = new JButton(new ImageIcon("bin/img/use_case.png"));
        panel.add(select_btn);
        panel.add(association_btn);
        panel.add(generalization_btn);
        panel.add(composition_btn);
        panel.add(class_btn);
        panel.add(use_case_btn);
        demo.add(panel, BorderLayout.WEST);
        //按鈕事件設定
        association_btn association_Obj = new association_btn(association_btn);
        association_btn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				association_Obj.click_btn(c);
			} 
		}); 
        select_btn select_Obj = new select_btn(select_btn);
        select_btn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				select_Obj.click_btn(c);
			} 
		}); 
        generalization_btn generalization_Obj = new generalization_btn(generalization_btn);
        generalization_btn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				generalization_Obj.click_btn(c);
			} 
		}); 
        composition_btn composition_Obj = new composition_btn(composition_btn);
        composition_btn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				composition_Obj.click_btn(c);
			} 
		}); 
        use_case_btn use_case_obj = new use_case_btn(use_case_btn);
        use_case_btn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				use_case_obj.click_btn(c);
			} 
		}); 
        class_btn click_obj = new class_btn(class_btn);
        class_btn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				click_obj.click_btn(c);			
			} 
		}); 
        
        demo.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		int t =0;
		objects o = null;
		for(int i=0;i<c.myArrayList.size();i++) {
			if(c.myArrayList.get(i).selected) {
				o = c.myArrayList.get(i);
				t += 1;
			}
		}
        //Menu事件設定
		if(e.getSource()==change_object_name) {
			if(t == 1)create_rename_frame(o);
			else {
				if(t==0)message("你並沒有選取任何物件");
				else if(t>1)message("你選取了多於一個的物件");
			}
		}
		else if(e.getSource()==Group) {
			if(t > 1)group();
			else {
				message("需要選取大於一個的物件才能進行群組。");
			}
		}
		else if(e.getSource()==UnGroup) {
			if(t == 1) {
				o.ungroup(c);
				message("UnGroup成功!");
			}
			else {
				if(t==0)message("你並沒有選取任何物件");
				else if(t>1)message("你選取了多於一個的物件");
			}
		}
	}
	protected void create_rename_frame(objects o) {
		JFrame frame = new JFrame("change object name");
        frame.setSize(350, 200);
        JPanel panel = new JPanel();    
        frame.add(panel);
        panel.setLayout(null);
        JLabel userLabel = new JLabel("New Name:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        JTextField newName = new JTextField(o.name,20);
        newName.setBounds(100,20,165,25);
        panel.add(newName);
        JButton Cancel_Button = new JButton("Cancel");
        Cancel_Button.setBounds(10, 80, 80, 25);
        panel.add(Cancel_Button);
        JButton OK_Button = new JButton("OK");
        OK_Button.setBounds(100, 80, 80, 25);
        panel.add(OK_Button);
        frame.setVisible(true);
        Cancel_Button.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			} 
		});
        OK_Button.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				String name = newName.getText();
				o.name = name;
				c.repaint();
				frame.dispose();
			} 
		});
	}
	protected void group() {
		int x1=-1,y1=-1,x2=-1,y2=-1;
		for(int i=0;i<c.myArrayList.size();i++) {
			if(c.myArrayList.get(i).selected) {
				if(c.myArrayList.get(i).x<x1 || x1==-1)x1=c.myArrayList.get(i).x;
				if(c.myArrayList.get(i).y<y1 || y1==-1)y1=c.myArrayList.get(i).y;
				if(c.myArrayList.get(i).x + c.myArrayList.get(i).width > x2 || x2==-1)x2=c.myArrayList.get(i).x+ c.myArrayList.get(i).width;
				if(c.myArrayList.get(i).y+ c.myArrayList.get(i).high > y2 || y2==-1)y2=c.myArrayList.get(i).y + c.myArrayList.get(i).high;
			}
		}
		composite tmp = new composite(x1,y1,x2-x1,y2-y1);
		for(int i=0;i<c.myArrayList.size();i++) {
			if(c.myArrayList.get(i).selected) {
				tmp.members.add(c.myArrayList.get(i));
			}
		}
		for(int i=0;i<tmp.members.size();i++) {
			c.myArrayList.remove(tmp.members.get(i));
		}
		c.myArrayList.add(tmp);
		tmp.selected();
		message("Group建立成功!");
	}
	protected void message(String message) {
		JFrame frame = new JFrame("Message");
		frame.setSize(350, 200);
        JPanel panel = new JPanel();  
        frame.add(panel);
        panel.setLayout(null);
        JLabel Label = new JLabel(message);
        Label.setBounds(10,20,300,25);
        JButton Button = new JButton("OK");
        Button.setBounds(10, 50, 80, 25);
        panel.add(Label);
        panel.add(Button);
        Button.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			} 
		});
        frame.setVisible(true);
	}
}
