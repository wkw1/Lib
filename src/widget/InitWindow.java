package widget;


import javax.swing.*;

/**
 * 
 * ��ʼ�����ں���
 * �����������
 * @author ��ΰ
 *
 */

public class InitWindow {
	
	public static void init(JFrame frame){
		
		frame.setBounds(100, 100, 1188, 788);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ͼ��ݹ���ϵͳ");
		frame.setResizable(false);

		// ����ͼƬ
		ImageIcon icon = new ImageIcon("images/13.jpg");
		//��ͼƬ����label��
		JLabel label = new JLabel(icon);
		// ����label�Ĵ�С
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		// ��ȡ���ڵĵڶ��㣬��label����
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		// ��ȡframe�Ķ�������,������Ϊ͸��
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);

		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

}
