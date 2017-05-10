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
 * 录入图书页面，管理员更改图书信息页面
 * @author 宽伟
 *
 */

public class InputBookView {
	
	private int what;//代表要操作的时间1表示录入图书2表示修改信息，从构造方法中获得

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

	private int outNumber;//图书借出量
	
	public static InputBookView inputBookView;
	public static InputBookView getInstance(BookModel bookModel,int what){
		if(inputBookView==null)
			inputBookView=new InputBookView(bookModel,what);
		return inputBookView;
	}
	
	//传入2 表示更改信息，传入1表示录入图书
	public InputBookView(BookModel bookModel,int what) {
		initialize();
		this.what=what;
		if (what == 2) {
			input.setText("保存");
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
	//检测信息是否输入完整
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

		if(what==1){//录入图书时产生新的ISBN，更新时则不变
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
		
		//关闭此窗口
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		//录入
		input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(infoIsOk()){
					ad = AdAction.getInstance();
					//录入图书
					if(what==1){
						// 将图书信息存入文件
						if(ad.inputBook(bookModel)){
							int i = JOptionPane.showConfirmDialog(null, "录入成功，继续录入？？", " 提示信息!",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
							// 不继续录入
							if (i == 1 || i == -1) {
								frame.dispose();
							} else {
								bookName.setText("");
								bookAuthor.setText("");
								bookPress.setText("");
								introduction.setText("");
								bookNumber.setText("");
							}
							LogDao.addLogSystem("管理员成功录入一本图书");
						}
						else{
							JOptionPane.showConfirmDialog(null, "录入失败？", 
									"提示信息", JOptionPane.PLAIN_MESSAGE);
							LogDao.addLogSystem("管理员录入图书失败");
						}
						
					}
					//更改图书
					else{//更新成功失败？？？
						if(outNumber>bookModel.getTN()){
							JOptionPane.showConfirmDialog(null, "更改后的图书数量小于借出数量，更改失败？",
									"提示信息", JOptionPane.PLAIN_MESSAGE);
						}
						else if (ad.updateBook(bookModel)) {
							SearchResultView view = SearchResultView.getInstance("", "", 2);
							view.updateData(bookModel);//更新表格
							frame.dispose();
							LogDao.addLogSystem("管理员成功更改图书");
						} else {
							JOptionPane.showConfirmDialog(null, "更新信息失败？",
									"提示信息", JOptionPane.PLAIN_MESSAGE);
							frame.dispose();
						}
					}
				}
				else{
					JOptionPane.showConfirmDialog(null, "请完整输入每个信息！！！",
							"提示信息", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		//从文件中导入
		inputFromFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//获取文件目录
				int result=0;
				String path=null;
				JFileChooser fileChooser = new JFileChooser();
				FileSystemView fsv = FileSystemView.getFileSystemView();
				fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
				fileChooser.setDialogTitle("请选择要上传的文件...");
				fileChooser.setApproveButtonText("确定");
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

		// 设置panel作为容器,控件加入其中
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// 设置为透明，可以看到图片
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("\u4E66\u540D");
		label.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label.setBounds(282, 198, 91, 44);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u4F5C\u8005");
		label_1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_1.setBounds(282, 292, 91, 35);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u51FA\u7248\u793E");
		label_2.setFont(new Font("华文楷体", Font.PLAIN, 20));
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
		lblNewLabel.setFont(new Font("华文楷体", Font.PLAIN, 20));
		lblNewLabel.setBounds(624, 205, 86, 31);
		panel.add(lblNewLabel);
		
		bookNumber = new JTextField();
		bookNumber.setBounds(745, 198, 98, 44);
		panel.add(bookNumber);
		bookNumber.setColumns(10);
		//限制只能输入七个数字
		bookNumber.setDocument(new LimitNumberLenght(7));
		
		
		JLabel label_3 = new JLabel("\u501F\u4E66\u6240\u9700\u6743\u9650");
		label_3.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_3.setBounds(611, 292, 120, 35);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u7B80\u4ECB");
		label_4.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_4.setBounds(624, 405, 86, 31);
		panel.add(label_4);
		
		introduction = new JTextArea();
		introduction.setFont(new Font("华文新魏", Font.PLAIN, 20));
		introduction.setBounds(721, 416, 326, 191);
		panel.add(introduction);
		introduction.setLineWrap(true);//自动换行
		
		input = new JButton("\u5F55\u5165");
		input.setBackground(new Color(0, 128, 128));
		input.setFont(new Font("宋体", Font.PLAIN, 25));
		input.setBounds(480, 665, 113, 44);
		panel.add(input);
		
		JLabel label_5 = new JLabel("\u4E66\u7684\u79CD\u7C7B");
		label_5.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_5.setBounds(282, 514, 89, 30);
		panel.add(label_5);
		
		BookType = new JComboBox<String>(ArrayDB.bookTypes);
		BookType.setBounds(375, 514, 116, 33);
		panel.add(BookType);
		
		JLabel label_6 = new JLabel("\u56FE\u4E66\u5F55\u5165");
		label_6.setFont(new Font("华文楷体", Font.PLAIN, 40));
		label_6.setBounds(476, 52, 215, 61);
		panel.add(label_6);
		
		powerForBorrow = new JComboBox<Integer>(ArrayDB.powerTypes);
		powerForBorrow.setBounds(745, 299, 98, 31);
		panel.add(powerForBorrow);

		inputFromFile = new JButton("\u6279\u91CF\u5BFC\u5165");
		inputFromFile.setBackground(new Color(0, 139, 139));
		inputFromFile.setFont(new Font("华文楷体", Font.PLAIN, 20));
		inputFromFile.setBounds(773, 665, 113, 44);
		panel.add(inputFromFile);
		
		close = new JButton("\u5173\u95ED");
		close.setBackground(new Color(184, 134, 11));
		close.setFont(new Font("华文楷体", Font.PLAIN, 20));
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
