package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import action.AdAction;
import dao.LogDao;
import db.ArrayDB;
import model.BookModel;
import widget.ISBNCreate;
import widget.InitWindow;
import widget.LimitNumberLenght;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
/**
 * ¼��ͼ��ҳ�棬����Ա����ͼ����Ϣҳ��
 * @author ��ΰ
 *
 */

public class InputBookView {
	
	private int what;//����Ҫ������ʱ��1��ʾ¼��ͼ��2��ʾ�޸���Ϣ���ӹ��췽���л��

	private JFrame frame;
	private JTextField bookName;
	private JTextField bookAuthor;
	private JTextField bookPress;
	private JTextField bookNumber;
	private JTextArea introduction;
	
	private JComboBox<Integer> powerForBorrow;
	private JComboBox<String> BookType;
	
	private JButton inputFromFile ;
	private JButton input;
	
	private BookModel bookModel=null;
	private BookModel bookModel1=null;

	private AdAction ad=null;
	
	private String bookNameString=null;
	private String bookISBNString=null;
	private String bookAuthorString=null;
	private String bookPressString=null;
	private String introductionString=null;
	private int bookNumberInt=0;
	private int powerForBorrowInt=1;
	private JButton close;

	private int outNumber;//ͼ������
	
	public static InputBookView inputBookView;
	public static InputBookView getInstance(BookModel bookModel,int what){
		if(inputBookView==null)
			inputBookView=new InputBookView(bookModel,what);
		return inputBookView;
	}
	
	//����2 ��ʾ������Ϣ������1��ʾ¼��ͼ��
	public InputBookView(BookModel bookModel,int what) {
		initialize();
		this.what=what;
		if (what == 2) {
			input.setText("����");
			inputFromFile.setVisible(false);
			bookAuthor.setText(bookModel.getAuthor());
			bookName.setText(bookModel.getName());
			bookPress.setText(bookModel.getPress());
			BookType.setSelectedItem(bookModel.getBookType());
			introduction.setText(bookModel.getIntroduction());
			bookNumber.setText(String.valueOf(bookModel.getTN()));
			bookISBNString = bookModel.getISBN();
			powerForBorrow.setSelectedItem(bookModel.getPowerNeed());
			outNumber = bookModel.getTN()-bookModel.getRN();
			bookModel1 = bookModel;
		}
		action();
	}
	//�����Ϣ�Ƿ���������
	public boolean infoIsOk(){
		bookNameString =bookName.getText().replaceAll("\\s", "");
		bookAuthorString =bookAuthor.getText().replaceAll("\\s", "");
		bookPressString=bookPress.getText().replaceAll("\\s", "");
		if(!bookNumber.getText().equals(""))
		     bookNumberInt = Integer.parseInt(bookNumber.getText());
		introductionString = introduction.getText().replaceAll("\\s", "");
		
		if(bookNameString.equals(""))
			return false;
		else if(bookAuthorString.equals(""))
			return false;
		else if(bookPressString.equals(""))
			return false;
		else if(introductionString.equals(""))
			return false;

		powerForBorrowInt = (Integer)powerForBorrow.getSelectedItem();
		bookModel = new BookModel();
		bookModel.setAuthor(bookAuthorString);
		bookModel.setBookType((String)BookType.getSelectedItem());
		bookModel.setIntroduction(introductionString);
		bookModel.setName(bookNameString);
		bookModel.setPowerNeed(powerForBorrowInt);
		bookModel.setPress(bookPressString);

		bookModel.setTN(bookNumberInt);

		if(what==1){//¼��ͼ��ʱ�����µ�ISBN������ʱ�򲻱�
			bookISBNString =ISBNCreate.CreISBN(bookModel);
			bookModel.setRN(bookNumberInt);
			bookModel.setStorageTime(SystemEntry.date);
		}

		else{
			bookModel.setRN(bookModel1.getTN()-outNumber);
			bookModel.setStorageTime(bookModel1.getStorageTime());
		}
		bookModel.setISBN(bookISBNString);
		return true;
	}
	
	public void action(){
		
		//�رմ˴���
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		//¼��
		input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(infoIsOk()){
					ad = AdAction.getInstance();
					//¼��ͼ��
					if(what==1){
						// ��ͼ����Ϣ�����ļ�
						if(ad.inputBook(bookModel)){
							int i = JOptionPane.showConfirmDialog(null, "¼��ɹ�������¼�룿��", " ��ʾ��Ϣ!",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
							// ������¼��
							if (i == 1 || i == -1) {
								frame.dispose();
							} else {
								bookName.setText("");
								bookAuthor.setText("");
								bookPress.setText("");
								introduction.setText("");
								bookNumber.setText("");
							}
							LogDao.addLogSystem("����Ա�ɹ�¼��һ��ͼ��");
						}
						else{
							JOptionPane.showConfirmDialog(null, "¼��ʧ�ܣ�", 
									"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
							LogDao.addLogSystem("����Ա¼��ͼ��ʧ��");
						}
						
					}
					//����ͼ��
					else{//���³ɹ�ʧ�ܣ�����
						if(outNumber>bookModel.getTN()){
							JOptionPane.showConfirmDialog(null, "���ĺ��ͼ������С�ڽ������������ʧ�ܣ�",
									"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
						}
						else if (ad.updateBook(bookModel)) {
							SearchResultView view = SearchResultView.getInstance("", "", 2);
							view.updateData(bookModel);//���±��
							frame.dispose();
							LogDao.addLogSystem("����Ա�ɹ�����ͼ��");
						} else {
							JOptionPane.showConfirmDialog(null, "������Ϣʧ�ܣ�",
									"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
							frame.dispose();
						}
					}
				}
				else{
					JOptionPane.showConfirmDialog(null, "����������ÿ����Ϣ������",
							"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		//���ļ��е���
		inputFromFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//��ȡ�ļ�Ŀ¼
				int result=0;
				String path=null;
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
		
		JLabel label = new JLabel("\u4E66\u540D");
		label.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label.setBounds(282, 198, 91, 44);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u4F5C\u8005");
		label_1.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label_1.setBounds(282, 292, 91, 35);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u51FA\u7248\u793E");
		label_2.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label_2.setBounds(280, 398, 91, 38);
		panel.add(label_2);
		
		bookName = new JTextField();
		bookName.setBounds(378, 198, 113, 44);
		panel.add(bookName);
		bookName.setColumns(10);
		
		bookAuthor = new JTextField();
		bookAuthor.setBounds(378, 292, 113, 44);
		panel.add(bookAuthor);
		bookAuthor.setColumns(10);
		
		bookPress = new JTextField();
		bookPress.setBounds(378, 398, 113, 44);
		panel.add(bookPress);
		bookPress.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u603B\u6570\u91CF");
		lblNewLabel.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		lblNewLabel.setBounds(624, 205, 86, 31);
		panel.add(lblNewLabel);
		
		bookNumber = new JTextField();
		bookNumber.setBounds(745, 198, 98, 44);
		panel.add(bookNumber);
		bookNumber.setColumns(10);
		//����ֻ�������߸�����
		bookNumber.setDocument(new LimitNumberLenght(7));
		
		
		JLabel label_3 = new JLabel("\u501F\u4E66\u6240\u9700\u6743\u9650");
		label_3.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label_3.setBounds(611, 292, 120, 35);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u7B80\u4ECB");
		label_4.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label_4.setBounds(624, 405, 86, 31);
		panel.add(label_4);
		
		introduction = new JTextArea();
		introduction.setFont(new Font("������κ", Font.PLAIN, 20));
		introduction.setBounds(721, 416, 326, 191);
		panel.add(introduction);
		introduction.setLineWrap(true);//�Զ�����
		
		input = new JButton("\u5F55\u5165");
		input.setBackground(new Color(0, 128, 128));
		input.setFont(new Font("����", Font.PLAIN, 25));
		input.setBounds(480, 665, 113, 44);
		panel.add(input);
		
		JLabel label_5 = new JLabel("\u4E66\u7684\u79CD\u7C7B");
		label_5.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label_5.setBounds(282, 514, 89, 30);
		panel.add(label_5);
		
		BookType = new JComboBox<String>(ArrayDB.bookTypes);
		BookType.setBounds(375, 514, 116, 33);
		panel.add(BookType);
		
		JLabel label_6 = new JLabel("\u56FE\u4E66\u5F55\u5165");
		label_6.setFont(new Font("���Ŀ���", Font.PLAIN, 40));
		label_6.setBounds(476, 52, 215, 61);
		panel.add(label_6);
		
		powerForBorrow = new JComboBox<Integer>(ArrayDB.powerTypes);
		powerForBorrow.setBounds(745, 299, 98, 31);
		panel.add(powerForBorrow);

		inputFromFile = new JButton("\u6279\u91CF\u5BFC\u5165");
		inputFromFile.setBackground(new Color(0, 139, 139));
		inputFromFile.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		inputFromFile.setBounds(773, 665, 113, 44);
		panel.add(inputFromFile);
		
		close = new JButton("\u5173\u95ED");
		close.setBackground(new Color(184, 134, 11));
		close.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		close.setBounds(1077, 13, 91, 35);
		panel.add(close);
	}
	
	public BookModel getBookModel() {
		return bookModel;
	}

	public void setBookModel(BookModel bookModel) {
		this.bookModel = bookModel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
