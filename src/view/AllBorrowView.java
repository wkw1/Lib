package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import action.AdAction;
import model.BorrowBookModel;
import model.OrderBookModel;
import tablemodel.AllBBTableModel;
import widget.InitWindow;
/**
 * 管理员得到借书表
 * 显示截至目前所有被借出的图书信息
 * @author 宽伟
 */
public class AllBorrowView{
	
	private JFrame frame;
    private JTable table=null;
	
	private List<BorrowBookModel> list=null;//搜索结果列表，保存所有搜索结果
	private AllBBTableModel myTableModel =null;
	
	private JButton close;
	private JButton nextPage;
	private JButton formerPage;
	private JButton remind;
	private AdAction adAction;
	
	private int selectedRowIndex=-1;//选中表格中的行
	//设置单例模式
	public static AllBorrowView allBorrowView=null;
	public static AllBorrowView getInstance(){
		if(allBorrowView==null){
			allBorrowView = new AllBorrowView();
		}
		return allBorrowView;
	}


	public AllBorrowView() {
		adAction = AdAction.getInstance();
		initialize();
		getData();
		action();
	}
	
	// 从AdAction中得到借书数据，并先填充一部分到table里
	private void getData() {

		list = new ArrayList<>();

		// 得到总的表
		list = adAction.getAllBorrowBook();
		if(list==null){
			BorrowBookModel info = new BorrowBookModel();
			info.setName("未找到图书！");
			list = new ArrayList<>();
			list.add(info);
		}
		myTableModel = new AllBBTableModel(table, frame, list);
		myTableModel.initData();
	}
	
	private void action(){
		//提醒归还图书
		remind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedRowIndex==-1){
					JOptionPane.showConfirmDialog(null, "请选择用户",
							"提示信息", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					String ID = (String) table.getValueAt(selectedRowIndex, 1);
					String ISBN = (String) table.getValueAt(selectedRowIndex, 3);
					if(adAction.remindUser(ID, ISBN))
						JOptionPane.showConfirmDialog(null, "提醒成功",
								"提示信息", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showConfirmDialog(null, "提醒失败",
								"提示信息", JOptionPane.PLAIN_MESSAGE);

				}
			}
		});
		
		// 点击上一页
		formerPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (myTableModel.formerPage())
					selectedRowIndex = -1;
			}
		});
		// 点击下一页
		nextPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myTableModel.nextPage())
					selectedRowIndex = -1;
			}
		});
		// 关闭此窗口
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				allBorrowView=null;
			}
		});

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 对用户选取的单行数据操作
				int selectRows = table.getSelectedRows().length;// 取得用户所选行的行数
				if (selectRows == 1) {
					selectedRowIndex = table.getSelectedRow();// 取得用户所选单行
				}
			}
		});
	}

	private void initialize() {
		frame = new JFrame();
		InitWindow.init(frame);
		
		// 设置panel作为容器,控件加入其中
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// 设置为透明，可以看到图片

		frame.getContentPane().add(panel);
		JLabel label = new JLabel("\u501F\u4E66\u8868");
		label.setFont(new Font("华文楷体", Font.PLAIN, 25));
		label.setBounds(420, 13, 113, 40);
		panel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 66, 1000, 600);
		panel.add(scrollPane);


		table = new JTable();
		// 设置类不可随数据大小改变
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(70);
		scrollPane.setViewportView(table);

	    nextPage = new JButton("下一页");
	    nextPage.setBackground(new Color(119, 136, 153));
	    nextPage.setFont(new Font("华文楷体", Font.PLAIN, 20));
		nextPage.setBounds(583, 687, 113, 42);
		panel.add(nextPage);

	    formerPage = new JButton("上一页");
	    formerPage.setBackground(new Color(119, 136, 153));
	    formerPage.setFont(new Font("华文楷体", Font.PLAIN, 20));
		formerPage.setBounds(308, 687, 113, 42);
		panel.add(formerPage);
		
		remind = new JButton("\u63D0\u9192\u5F52\u8FD8");
		remind.setBackground(new Color(0, 139, 139));
		remind.setFont(new Font("华文楷体", Font.PLAIN, 20));
		remind.setBounds(1043, 289, 113, 50);
		panel.add(remind);
		
		close = new JButton("\u9000\u51FA");
		close.setBounds(1053, 44, 103, 27);
		panel.add(close);
		
		JLabel welcome = new JLabel("\u4F60\u597D\uFF01");
		welcome.setBounds(1043, 13, 72, 18);
		panel.add(welcome);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
