package widget;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * ��ʼ�����ں���
 * �����������
 * @author ��ΰ
 *
 */

public class InitWindow {
	
	public static void init(JFrame frame){
		
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ͼ��ݹ���ϵͳ");

		// ����ͼƬ
		ImageIcon icon = new ImageIcon("images/11.jpg");
		//��ͼƬ����label��
		JLabel label = new JLabel(icon);
		// ����label�Ĵ�С
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		// ��ȡ���ڵĵڶ��㣬��label����
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		// ��ȡframe�Ķ�������,������Ϊ͸��
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);
		
	}

}