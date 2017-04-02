package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import model.UserModel;
import tablemodel.SUserTableModel;
import widget.InitWindow;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchUserResultView {

	private JFrame frame;
	private List<UserModel> lists=null;
	private SUserTableModel myTableModel;
	
	private JTable table;
	private int selectedRowIndex=-1;//用户鼠标选中的行
	
	public static SearchUserResultView searchUserResultView;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton button;
	public static SearchUserResultView getInstance(String keyWord,String searchType){
		if(searchUserResultView==null)
			searchUserResultView = new SearchUserResultView(keyWord, searchType);
		return searchUserResultView;
	}
	
	
	public SearchUserResultView(String keyWord,String searchType) {
		initialize();
		initData(keyWord,searchType);
		action();
	}
	
	//初始化数据
	private void initData(String keyWord,String searchType){
		lists = new ArrayList<>();
		System.out.println("关键字："+keyWord+"搜索类型："+searchType);
		
		myTableModel =  new SUserTableModel(table,lists);
		myTableModel.initData();
	}
	
	private void action(){
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// 对用户选取的单行数据操作
				int selectRows = table.getSelectedRows().length;// 取得用户所选行的行数
				if (selectRows == 1) {
					selectedRowIndex = table.getSelectedRow();// 取得用户所选单行
					// System.out.print(list.get(selectedRowIndex).get("i"));
					System.out.print(selectedRowIndex);

					String string = (String) table.getValueAt(selectedRowIndex, 1);

					System.out.print(string);
				}
			}
		});
	}
	
	
	

	private void initialize() {
		frame = new JFrame();
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setHgap(46);
		InitWindow.init(frame);
		// 设置panel作为容器,控件加入其中
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// 设置为透明，可以看到图片
		frame.getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 61, 889, 561);
		panel.add(scrollPane);
		
		table = new JTable();
		//设置类不可随数据大小改变
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.setRowHeight(70);
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("\u4E0A\u4E00\u9875");
		btnNewButton.setBounds(327, 682, 113, 27);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u4E0B\u4E00\u9875");
		btnNewButton_1.setBounds(552, 682, 113, 27);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(1042, 223, 113, 27);
		panel.add(btnNewButton_2);
		
		button = new JButton("\u5220\u9664 \u6B64\u7528\u6237");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(1042, 336, 113, 27);
		panel.add(button);
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	
}
