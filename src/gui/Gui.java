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
		// TODO �Զ����ɵķ������
		JFrame f=new JFrame("����");
		

		JLabel lCsv = new JLabel("����csv·��:");
        // �����
        JTextField tfCsv=new JTextField("");
        tfCsv.setPreferredSize(new Dimension(80, 30));
		
		f.add(lCsv);
        f.add(tfCsv);
		
		
		 // �����ⲿ����ʵ����JDialog
        JDialog d = new JDialog(f);
        // ����Ϊģ̬
        d.setModal(true);
 
        d.setTitle("ģ̬�ĶԻ���");
        d.setLayout(new FlowLayout());
		
		
        
        JLabel lSale = new JLabel("�����۶�:");
        // �����
        JTextField tfSale=new JTextField("");
        tfSale.setPreferredSize(new Dimension(80, 30));
        
        JLabel lExpress = new JLabel("���ʷ�:");
        // �����
        JTextField tfExpress=new JTextField("");
        tfExpress.setPreferredSize(new Dimension(80, 30));
        
        JLabel lZhitongche = new JLabel("ֱͨ��:");
        // �����
        JTextField tfZhitongche=new JTextField("");
        tfZhitongche.setPreferredSize(new Dimension(80, 30));
        
        JLabel lSoft = new JLabel("���������:");
        // �����
        JTextField tfSoft=new JTextField("");
        tfSoft.setPreferredSize(new Dimension(80, 30));
        
        JLabel lWage = new JLabel("����:");
        // �����
        JTextField tfWage=new JTextField("");
        tfWage.setPreferredSize(new Dimension(80, 30));
        
        
        JButton bEnd=new JButton("����");
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
        
        // �Ѱ�ť���뵽��������
        f.add(bEnd);

        d.setSize(400, 300);
        d.setLocation(200, 200);
		//d.setVisible(true);
        
        // ���������ô�С
        f.setSize(400, 300);
        // ����������λ��
        f.setLocation(200, 200);
        f.setLayout(new FlowLayout());

        
        // ��ť���
        JButton b = new JButton("���µ����ݿ�");
        // ͬʱ��������Ĵ�С��λ��
        b.setBounds(50, 50, 280, 30);
        // �Ѱ�ť���뵽��������
        
        // ��ť���2 ���ñ�Ҫ�Ĳ���
        JButton b2 = new JButton("���ò���");
        // ͬʱ��������Ĵ�С��λ��
        b2.setBounds(50, 50, 280, 30);
        // �Ѱ�ť���뵽��������
        f.add(b);
        f.add(b2);
        
        b2.addActionListener(new ActionListener() {
  
            // ����ť�����ʱ���ͻᴥ�� ActionEvent�¼�
            // actionPerformed �����ͻᱻִ��
            public void actionPerformed(ActionEvent e) {
                d.setVisible(true);
            }
        });
        
        // �رմ����ʱ���˳�����
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �ô����ÿɼ�
        f.setVisible(true);
	}

}
