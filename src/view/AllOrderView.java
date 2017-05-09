package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import action.AdAction;
import model.OrderBookModel;
import tablemodel.AllOBTableModel;
import widget.InitWindow;
import java.awt.Font;
import java.awt.Color;
/**
 * 管理员的预约表视图
 * @author 宽伟
 *
 */

public class AllOrderView {

	private JFrame frame;
	
	private JTable table = null;

	private JButton close;
	private JButton nextPage;
	private JButton formerPage;
	
	private int selectedRowIndex = -1;// 选中表格中的行
	private AllOBTableModel myTableModel;
	private List<OrderBookModel> list=null;
	
	private AdAction adAction;


	
	public AllOrderView() {
		adAction = AdAction.getInstance();
		initialize();
		getData();
		action();
	}
	
	
	public static AllOrderView allOrderView=null;
	public static AllOrderView getInstance(){
		if(allOrderView==null){
			allOrderView = new AllOrderView();
		}
		return allOrderView;
	}
	
	
	//从UserAction中得到借书数据，并创建tableModel传入数据
	public void getData() {
		list = new ArrayList<>();
		// 得到总的表
		list = adAction.getAllOrderBook();
		if(list==null){
			OrderBookModel info = new OrderBookModel();
			info.setName("未找到图书！");
			list = new ArrayList<>();
			list.add(info);
		}
		myTableModel = new AllOBTableModel(table, list);
		myTableModel.initData();
	}
	
	public void action(){
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
				allOrderView =null;
				frame.dispose();
			}
		});
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 对用户选取的单行数据操作
				int selectRows = table.getSelectedRows().length;// 取得用户所选行的行数
				if (selectRows == 1) {
					selectedRowIndex = table.getSelectedRow();// 取得用户所选单行
					System.out.print(selectedRowIndex);
					String string = (String) table.getValueAt(selectedRowIndex, 1);
					System.out.print(string);
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
		
		JLabel label = new JLabel("\u9884\u7EA6\u5217\u8868");
		label.setFont(new Font("华文楷体", Font.PLAIN, 25));
		label.setBounds(487, 13, 122, 37);
		panel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 54, 1000, 596);
		panel.add(scrollPane);

		table = new JTable();
		// 设置类不可随数据大小改变
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(70);
		scrollPane.setViewportView(table);

		nextPage = new JButton("下一页");
		nextPage.setBackground(new Color(0, 139, 139));
		nextPage.setFont(new Font("华文楷体", Font.PLAIN, 20));
		nextPage.setBounds(628, 687, 113, 42);
		panel.add(nextPage);

		formerPage = new JButton("上一页");
		formerPage.setBackground(new Color(0, 139, 139));
		formerPage.setFont(new Font("华文楷体", Font.PLAIN, 20));
		formerPage.setBounds(364, 687, 113, 42);
		panel.add(formerPage);

		close = new JButton("\u9000\u51FA");
		close.setFont(new Font("华文楷体", Font.PLAIN, 15));
		close.setBackground(new Color(220, 20, 60));
		close.setBounds(1095, 0, 87, 27);
		panel.add(close);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
