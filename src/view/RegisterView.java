package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import db.SignInFeedback;
import widget.InitWindow;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;

import action.RegisterLoginAction;
/**
 * ע��ҳ��
 * @author ��ΰ
 *
 */

public class RegisterView {
	
	private RegisterLoginAction registerAction;

	private JFrame frame;
	private JTextField ID;
	private JPasswordField password;
	private JPasswordField passwordOK;
	
	private JButton cancel;
	private JButton signIn;
	private JButton help ;
	private JButton register;
	private JTextPane hint;
    private String hintString;
    
    private String IDString;
    private String passwordString;
    private String passwordOKString;

	public RegisterView() {
		initialize();
		setData();
		action();
	}
	public void setData(){
		hintString = "   ��ʾ��Ϣ��\nע���ϵͳ����Ϊ�����ʵ��ѧѧ��\n"
				+ "������Ϣ�Ѵ洢����̨,���ע��֮��ɸ���";
		hint.setText(hintString);
	}
	
	public void action(){
		//ע��
		register.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IDString = ID.getText().trim();
				passwordString = password.getText();
				passwordOKString = passwordOK.getText();
				
				registerAction = new RegisterLoginAction();
				int result =registerAction.register(IDString, passwordString, passwordOKString);
				if(result==SignInFeedback.SUCCESSFUL){
					JOptionPane.showConfirmDialog(null, "ע��ɹ������¼��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}
				else if(result== SignInFeedback.NO_ID){
					JOptionPane.showConfirmDialog(null, "������ID", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
				else if(result==SignInFeedback.NO_PASSWORD){
					JOptionPane.showConfirmDialog(null, "����������", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
				else if(result==SignInFeedback.NOT_EQUAL){
					JOptionPane.showConfirmDialog(null, "��ȷ��������������һ��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
				else if(result==SignInFeedback.DO_REGISTER){
					JOptionPane.showConfirmDialog(null, "�Ѿ�ע�ᣬ���¼", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
				else if(result==SignInFeedback.NO_INFO){
					JOptionPane.showConfirmDialog(null, "���Ǳ�Уѧ������ע��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		//����
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		InitWindow.init(frame);
		
		// ����panel��Ϊ����,�ؼ���������
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// ����Ϊ͸�������Կ���ͼƬ
		frame.getContentPane().add(panel);
		
		register = new JButton("\u6CE8\u518C");
		register.setBackground(new Color(0, 128, 128));
		register.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		register.setBounds(355, 482, 131, 52);
		panel.add(register);
		
		ID = new JTextField();
		ID.setFont(new Font("����", Font.PLAIN, 20));
		ID.setBounds(444, 240, 131, 38);
		panel.add(ID);
		ID.setColumns(10);
		
		JLabel label = new JLabel("\u5B66\u53F7\u6216\u6559\u5DE5\u53F7");
		label.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label.setBounds(291, 233, 162, 52);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label_1.setBounds(332, 291, 101, 45);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_2.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label_2.setBounds(314, 349, 101, 37);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u6B22\u8FCE\u6CE8\u518C\u52A0\u5165\u7CFB\u7EDF");
		label_3.setFont(new Font("���Ŀ���", Font.PLAIN, 60));
		label_3.setBounds(166, 53, 569, 85);
		panel.add(label_3);
		
		password = new JPasswordField();
		password.setFont(new Font("����", Font.PLAIN, 20));
		password.setBounds(444, 294, 131, 38);
		panel.add(password);
		
		passwordOK = new JPasswordField();
		passwordOK.setFont(new Font("����", Font.PLAIN, 20));
		passwordOK.setBounds(444, 359, 131, 38);
		panel.add(passwordOK);
		
		signIn = new JButton("\u5DF2\u6709\u8D26\u53F7\uFF1F\u767B\u5F55");
		signIn.setBackground(new Color(0, 128, 128));
		signIn.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		signIn.setBounds(338, 714, 158, 27);
		panel.add(signIn);
		
		cancel = new JButton("\u53D6\u6D88");
		cancel.setBackground(new Color(255, 0, 0));
		cancel.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		cancel.setBounds(1105, 0, 77, 27);
		panel.add(cancel);
		
		help = new JButton("\u5E2E\u52A9");
		help.setBackground(new Color(0, 128, 128));
		help.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		help.setBounds(1035, 0, 71, 27);
		panel.add(help);
		
		hint = new JTextPane();
		hint.setBackground(new Color(255, 140, 0));
		hint.setEditable(false);
		hint.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		hint.setBounds(924, 149, 162, 385);
		panel.add(hint);
		
	}
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
