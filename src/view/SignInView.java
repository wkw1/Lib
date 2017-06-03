package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.LogDao;
import db.SignInFeedback;
import widget.InitWindow;
import widget.MyButton;

import javax.swing.JTextField;

import action.RegisterLoginAction;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JPasswordField;
/**
 * ��¼ҳ��
 * @author ��ΰ
 */
public class SignInView {
	
	private RegisterLoginAction signInAction = null;

	private JFrame frame;
	private JTextField ID;
	private MyButton signIn;
	private JButton register;
	private JButton help;
	private JButton cancel;//ȡ����¼
	
	private String IDString=null;
	private String passwordString=null;
	private JTextField textField;
	private JPasswordField password;
	
	private ButtonGroup buttonGroup;
	private JRadioButton student;
	private JRadioButton teacher;
	private JRadioButton ad;
	
	private int userType=0;//��ʾ�û�ѡ��ĵ�¼����1����ѧ�� 2������ʦ 3�������Ա
	
	public static SignInView signInView=null;
	public static SignInView getInstance(){
		if(signInView==null){
			signInView=new SignInView();
		}
		return signInView;
	}
	public SignInView() {
		initialize();
		action();
	}
	//�����������
	public void action(){
		
		//��¼
		signIn.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				signInAction = new RegisterLoginAction();
				// TODO Auto-generated method stub
				setUserType();
				//ʹ��������ʽȥ���ո�
				IDString = ID.getText().replaceAll("\\s", "");
				passwordString = password.getText().replaceAll("\\s", "");
				//���ж��Ƿ������¼����

				int result= 0;
				try {
					result = signInAction.SignIn(IDString, passwordString, userType);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				if(result==SignInFeedback.SUCCESSFUL){
					if(userType==3){
						AfterAdSignInView window =AfterAdSignInView.getInstance();
						window.getFrame().setVisible(true);
						LogDao.addLogSystem("IDΪ"+IDString+"�Ĺ���Ա��¼");
					}
					else{
						AfterUserSignInView window = new AfterUserSignInView();
					    window.getFrame().setVisible(true);
						LogDao.addLogSystem("IDΪ"+IDString+"���û���¼");
					}
					//�õ�ѡ��ĵ�¼����
					frame.dispose();
				}
				else if(result== SignInFeedback.NO_ID){
					JOptionPane.showConfirmDialog(null, "������ID?", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
				else if(result==SignInFeedback.NO_PASSWORD){
				    JOptionPane.showConfirmDialog(null, "����������?", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
				else if(result==SignInFeedback.NO_INFO){
					if(userType==3)
						JOptionPane.showConfirmDialog(null, "�����ڴ˹���Ա", "��ʾ��Ϣ",
								JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showConfirmDialog(null, "���Ǳ�Уѧ������ʹ��ϵͳ", "��ʾ��Ϣ",
							     JOptionPane.PLAIN_MESSAGE);
				}
				else if(result==SignInFeedback.NOT_REGISTER){
					JOptionPane.showConfirmDialog(null, "��δע�ᣬ��ע��", "��ʾ��Ϣ",
							JOptionPane.PLAIN_MESSAGE);
				}
				else if(result==SignInFeedback.WRONG_PASSWORD){
					JOptionPane.showConfirmDialog(null, "������󣡣�", "��ʾ��Ϣ",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		//ע��
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterView registerView = new RegisterView();
				registerView.getFrame().setVisible(true);
			}
		});
		//������Ϣ
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(null, "��ϵͳ���ޱ���ѧ���ͽ�ʦʹ��\n"
						+ "�����Ѿ��ڴ�ƽ̨ע���ֱ�ӵ�¼��δע�����ע����¼", "����", JOptionPane.PLAIN_MESSAGE);
			}
		});
		//�˳���¼
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				signInView=null;
				SystemEntry.getInstance();
			}
		});
	}
	
	//�õ��û�ѡ���¼������
	private void setUserType(){
		if(student.isSelected()){
			userType =1;
		}
		else if(teacher.isSelected()){
			userType =2;
		}
		else if(ad.isSelected()){
			userType =3;
		}
		else{
			userType =0;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setHgap(46);
		InitWindow.init(frame);
		
		// ����panel��Ϊ����,�ؼ���������
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// ����Ϊ͸�������Կ���ͼƬ

		frame.getContentPane().add(panel);
		
		ID = new JTextField();
		ID.setFont(new Font("����", Font.PLAIN, 25));
		ID.setBounds(489, 363, 199, 44);
		panel.add(ID);
		ID.setColumns(10);
		
		JLabel lblId = new JLabel("ID ");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("����", Font.BOLD, 20));
		lblId.setBounds(381, 363, 59, 41);
		panel.add(lblId);
		
		JLabel label = new JLabel("\u5BC6\u7801");
		label.setFont(new Font("����", Font.BOLD, 20));
		label.setBounds(381, 451, 53, 41);
		panel.add(label);
		
		signIn = new MyButton("\u767B\u5F55");
		signIn.BUTTON_COLOR2 = new Color(169, 169, 169);
		signIn.BUTTON_COLOR1 = new Color(0, 128, 128);
		signIn.setFont(new Font("���Ŀ���", Font.PLAIN, 30));
		signIn.setBackground(new Color(0, 139, 139));
		signIn.setForeground(new Color(0, 0, 0));
		signIn.setBounds(460, 553, 161, 57);
		panel.add(signIn);
		
		teacher = new JRadioButton("\u8001\u5E08");
		teacher.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		teacher.setBounds(415, 283, 109, 27);
		panel.add(teacher);
		teacher.setOpaque(false);
		
		student = new JRadioButton("\u5B66\u751F");
		student.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		student.setSelected(true);
		student.setBounds(549, 283, 100, 27);
		panel.add(student);
		student.setOpaque(false);
		
		ad = new JRadioButton("\u7BA1\u7406\u5458");
		ad.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		ad.setBounds(665, 283, 109, 27);
		panel.add(ad);
		ad.setOpaque(false);
		
		buttonGroup= new ButtonGroup();
		buttonGroup.add(student);
		buttonGroup.add(teacher);
		buttonGroup.add(ad);
		
		register = new JButton("\u8FD8\u6CA1\u6709\u8D26\u53F7\uFF1F\u6CE8\u518C");
		register.setBackground(new Color(0, 128, 128));
		register.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		register.setBounds(435, 711, 214, 33);
		panel.add(register);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("�����п�", Font.PLAIN, 60));
		textField.setText("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		textField.setBounds(359, 62, 438, 110);
		panel.add(textField);
		
		textField.setOpaque(false);
		
		password = new JPasswordField();
		password.setFont(new Font("����", Font.PLAIN, 25));
		password.setBounds(489, 451, 199, 41);
		panel.add(password);
		
		help = new JButton("\u5E2E\u52A9");
		help.setBackground(new Color(0, 128, 128));
		help.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		help.setBounds(1041, 0, 73, 27);
		panel.add(help);
		
		cancel = new JButton("\u53D6\u6D88");
		cancel.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		cancel.setBackground(new Color(255, 69, 0));
		cancel.setBounds(1111, 0, 73, 27);
		panel.add(cancel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
