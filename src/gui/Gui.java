package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class Gui {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		JFrame f=new JFrame("算账");
		

		JLabel lCsv = new JLabel("宝贝csv路径:");
        // 输入框
        JTextField tfCsv=new JTextField("");
        tfCsv.setPreferredSize(new Dimension(80, 30));
		
		f.add(lCsv);
        f.add(tfCsv);
		
		
		 // 根据外部窗体实例化JDialog
        JDialog d = new JDialog(f);
        // 设置为模态
        d.setModal(true);
 
        d.setTitle("模态的对话框");
        d.setLayout(new FlowLayout());
		
		
        
        JLabel lSale = new JLabel("总销售额:");
        // 输入框
        JTextField tfSale=new JTextField("");
        tfSale.setPreferredSize(new Dimension(80, 30));
        
        JLabel lExpress = new JLabel("总邮费:");
        // 输入框
        JTextField tfExpress=new JTextField("");
        tfExpress.setPreferredSize(new Dimension(80, 30));
        
        JLabel lZhitongche = new JLabel("直通车:");
        // 输入框
        JTextField tfZhitongche=new JTextField("");
        tfZhitongche.setPreferredSize(new Dimension(80, 30));
        
        JLabel lSoft = new JLabel("软件和其他:");
        // 输入框
        JTextField tfSoft=new JTextField("");
        tfSoft.setPreferredSize(new Dimension(80, 30));
        
        JLabel lWage = new JLabel("工资:");
        // 输入框
        JTextField tfWage=new JTextField("");
        tfWage.setPreferredSize(new Dimension(80, 30));
        
        
        JButton bEnd=new JButton("算账");
        bEnd.setBounds(50, 50, 280, 30);
        
        
        
        
        d.add(lSale);
        d.add(tfSale);
        
        d.add(lExpress);
        d.add(tfExpress);
        
        d.add(lZhitongche);
        d.add(tfZhitongche);
        
        d.add(lSoft);
        d.add(tfSoft);
        
        d.add(lWage);
        d.add(tfWage);
        
        // 把按钮加入到主窗体中
        f.add(bEnd);

        d.setSize(400, 300);
        d.setLocation(200, 200);
		//d.setVisible(true);
        
        // 主窗体设置大小
        f.setSize(400, 300);
        // 主窗体设置位置
        f.setLocation(200, 200);
        f.setLayout(new FlowLayout());

        
        // 按钮组件
        JButton b = new JButton("更新到数据库");
        // 同时设置组件的大小和位置
        b.setBounds(50, 50, 280, 30);
        // 把按钮加入到主窗体中
        
        // 按钮组件2 设置必要的参数
        JButton b2 = new JButton("设置参数");
        // 同时设置组件的大小和位置
        b2.setBounds(50, 50, 280, 30);
        // 把按钮加入到主窗体中
        f.add(b);
        f.add(b2);
        
        b2.addActionListener(new ActionListener() {
  
            // 当按钮被点击时，就会触发 ActionEvent事件
            // actionPerformed 方法就会被执行
            public void actionPerformed(ActionEvent e) {
                d.setVisible(true);
            }
        });
        
        // 关闭窗体的时候，退出程序
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        f.setVisible(true);
	}

}
