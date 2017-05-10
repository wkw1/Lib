package view;


import javax.swing.JFrame;
import javax.swing.JPanel;

import widget.InitWindow;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.BookModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class BookDetailView {

	private JFrame frame;
	private JLabel ISBN ;
	private JLabel bookName ;
	private JLabel press ;
	private JLabel author ;
	private JTextArea introduction;
	
	private BookModel bookModel;
	
	private JButton close;


	public BookDetailView(BookModel bookModel) {
		this.bookModel = bookModel;
		initialize();
		setData();
		action();
	}
	
	private void action(){
		close.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
	}
	
	private void setData(){
		ISBN.setText(bookModel.getISBN());
		bookName.setText(bookModel.getName());
		press.setText(bookModel.getPress());
		author.setText(bookModel.getAuthor());
		introduction.setText(bookModel.getIntroduction());
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
		
		JLabel lblNewLabel = new JLabel("\u8BE6\u60C5");
		lblNewLabel.setFont(new Font("华文楷体", Font.PLAIN, 30));
		lblNewLabel.setBounds(565, 30, 146, 45);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E66\u540D");
		lblNewLabel_1.setFont(new Font("华文楷体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(121, 162, 94, 34);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u4F5C\u8005");
		lblNewLabel_2.setFont(new Font("华文楷体", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(121, 297, 94, 34);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u51FA\u7248\u793E");
		lblNewLabel_3.setFont(new Font("华文楷体", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(121, 419, 94, 34);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u7B80\u4ECB");
		lblNewLabel_4.setFont(new Font("华文楷体", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(639, 162, 94, 34);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("ISBN");
		lblNewLabel_5.setFont(new Font("华文楷体", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(121, 533, 94, 45);
		panel.add(lblNewLabel_5);
		
		introduction = new JTextArea();
		introduction.setLineWrap(true);
		introduction.setText("\u4E66\u7684\u7B80\u4ECB");
		introduction.setFont(new Font("华文新魏", Font.PLAIN, 20));
		introduction.setBackground(new Color(60, 179, 113));
		introduction.setEditable(false);
		introduction.setEnabled(false);
		introduction.setBounds(639, 245, 420, 383);
		panel.add(introduction);
		
		bookName = new JLabel("\u4E66\u540D");
		bookName.setForeground(new Color(0, 0, 128));
		bookName.setFont(new Font("宋体", Font.PLAIN, 20));
		bookName.setBounds(251, 166, 266, 30);
		panel.add(bookName);
		
		author = new JLabel("\u4F5C\u8005");
		author.setFont(new Font("宋体", Font.PLAIN, 20));
		author.setBounds(251, 301, 266, 30);
		panel.add(author);
		
		press = new JLabel("\u51FA\u7248\u793E");
		press.setForeground(new Color(0, 0, 139));
		press.setFont(new Font("宋体", Font.PLAIN, 20));
		press.setBounds(251, 423, 266, 30);
		panel.add(press);
		
		ISBN = new JLabel("ISBN");
		ISBN.setFont(new Font("宋体", Font.PLAIN, 20));
		ISBN.setBounds(251, 540, 280, 35);
		panel.add(ISBN);
		
		close = new JButton("\u5173\u95ED");
		close.setFont(new Font("华文楷体", Font.PLAIN, 15));
		close.setBackground(new Color(255, 0, 0));
		close.setBounds(1103, 0, 79, 27);
		panel.add(close);
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	
}
