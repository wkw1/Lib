package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import widget.InitWindow;
import widget.LimitNumberLenght;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import action.AdAction;
import model.UserModel;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.Color;

public class InputUserView {

	private JFrame frame;
	private JTextField name;
	private JTextField ID;
	private JTextField money;
	
	private UserModel userModel;
	private AdAction ad=null;
	
	private JButton input ;
	private JButton inputFromFile;
	private JButton cancel;
	
	private JComboBox<Integer> powerForBorrow ;
	private JComboBox<Integer> numberForBorrow; 
	
	public static InputUserView inputUserView;
	public static InputUserView getInstance(){
		if(inputUserView==null)
			 inputUserView =new InputUserView();
		return inputUserView;
	}

	public InputUserView() {
		initialize();
		action();
	}
	
	private void action(){
		input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//����������ɣ���������
				if(isDataOk()){
					if(ad.inputUser(userModel)){
						//��������
						frame.dispose();
					}
				}
				else{
					JOptionPane.showConfirmDialog(null, "����������ÿ����Ϣ������", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		//�˳�
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				inputUserView=null;
			}
		});
		//���ļ��ж���
		inputFromFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ļ��е���
				inputFromFile.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// ��ȡ�ļ�Ŀ¼
						int result = 0;
						String path = null;
						JFileChooser fileChooser = new JFileChooser();
						FileSystemView fsv = FileSystemView.getFileSystemView();
						fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
						fileChooser.setDialogTitle("��ѡ��Ҫ�ϴ����ļ�...");
						fileChooser.setApproveButtonText("ȷ��");
						fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						result = fileChooser.showOpenDialog(getFrame());
						if (JFileChooser.APPROVE_OPTION == result) {
							path = fileChooser.getSelectedFile().getPath();
							System.out.println("path: " + path);
						}
					}
				});
			}
		});
		
	}
	//�ж������Ƿ������������д��������TRUE
	private boolean isDataOk(){
		String nameString=null;
		String IDString=null;
		String schoolString=null;
		int ANBooksInt;
		int balanceInt;
		int power;
		Date date=null;
		nameString = name.getText();
		if(nameString.equals(""))
			return false;
		else if(nameString.equals(""))
			return false;
		else if(money.getText().equals(""))
			return false;
		balanceInt =  Integer.parseInt(money.getText());
		power = powerForBorrow.getSelectedIndex();
		ANBooksInt = numberForBorrow.getSelectedIndex();
						
		userModel.setANBooks(ANBooksInt);
		userModel.setBalance(balanceInt);
		userModel.setBNBooks(0);
		userModel.setID(IDString);
		userModel.setJoinDate(date);
		userModel.setName(nameString);
		userModel.setPower(power);
		userModel.setSchool(schoolString);
		return true;
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
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		label.setBounds(182, 206, 93, 41);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5B66\u53F7");
		label_1.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		label_1.setBounds(182, 315, 72, 41);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u5B66\u9662");
		label_2.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		label_2.setBounds(182, 429, 72, 41);
		panel.add(label_2);
		
		name = new JTextField();
		name.setBounds(289, 206, 151, 41);
		panel.add(name);
		name.setColumns(10);
		
		ID = new JTextField();
		ID.setBounds(289, 315, 151, 41);
		panel.add(ID);
		ID.setColumns(10);
		
		JLabel label_3 = new JLabel("\u91D1\u989D\u4FE1\u606F");
		label_3.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		label_3.setBounds(619, 206, 105, 39);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u501F\u4E66\u6743\u9650");
		label_4.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		label_4.setBounds(620, 315, 104, 41);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u5141\u8BB8\u501F\u4E66\u6570\u91CF");
		label_5.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		label_5.setBounds(619, 429, 164, 41);
		panel.add(label_5);
		
		money = new JTextField();
		money.setBounds(827, 206, 164, 44);
		panel.add(money);
		money.setColumns(10);
		money.setDocument(new LimitNumberLenght(5));
		
		powerForBorrow = new JComboBox<Integer>();
		powerForBorrow.setBounds(827, 315, 113, 39);
		panel.add(powerForBorrow);
		powerForBorrow.addItem(1);
		powerForBorrow.addItem(2);
		powerForBorrow.addItem(3);
		
		
		numberForBorrow = new JComboBox<Integer>();
		numberForBorrow.setBounds(827, 429, 113, 36);
		panel.add(numberForBorrow);
		numberForBorrow.addItem(30);
		numberForBorrow.addItem(45);
		numberForBorrow.addItem(60);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u4FE1\u606F\u5F55\u5165");
		lblNewLabel.setFont(new Font("���Ŀ���", Font.PLAIN, 50));
		lblNewLabel.setBounds(423, 41, 497, 92);
		panel.add(lblNewLabel);
		
		input = new JButton("\u5F55\u5165");
		input.setBackground(new Color(0, 128, 128));
		input.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		input.setBounds(497, 555, 113, 52);
		panel.add(input);
		
		inputFromFile = new JButton("\u6279\u91CF\u5BFC\u5165");
		inputFromFile.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		inputFromFile.setBackground(new Color(176, 224, 230));
		inputFromFile.setBounds(1022, 699, 146, 41);
		panel.add(inputFromFile);
		
		cancel = new JButton("\u53D6\u6D88");
		cancel.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		cancel.setBackground(new Color(255, 0, 0));
		cancel.setBounds(1095, 0, 87, 27);
		panel.add(cancel);
		
		JComboBox<String> school = new JComboBox<String>();
		school.setBounds(289, 429, 151, 41);
		panel.add(school);
		school.addItem("�����ѧԺ");
		school.addItem("��Ϣ��ͨ�Ź���ѧԺ");
		school.addItem("���ӹ���ѧԺ");
		school.addItem("�Զ���ѧԺ");
		school.addItem("����ռ䰲ȫѧԺ");
		school.addItem("��ѧԺ");
		school.addItem("���ù���ѧԺ");
		school.addItem("����ѧԺ");
		school.addItem("��Ϣ��ѧ�뼼��ѧԺ");
		school.addItem("������ѧѧԺ");
		school.addItem("����ý���뼼��ѧԺ");
		school.addItem("����ѧԺ");
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	
}
