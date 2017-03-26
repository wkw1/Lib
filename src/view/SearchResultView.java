package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import widget.InitWindow;
import widget.LimitNumberLenght;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import action.AdAction;
import action.UserAction;
import model.BookModel;
import tablemodel.SBookTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
/**
 * 搜搜结果页面
 * 包括管理员和用户的搜搜结果
 * 根据传入的值的不同判断点击操作和控件名字
 * @author 宽伟
 *
 */

public class SearchResultView{

	private JFrame frame;
	private JTable table=null;
	private List<BookModel> lists=null;//搜索结果列表，保存所有搜索结果
	private SBookTableModel myTableModel =null;
	private UserAction userAction;
	private AdAction adAction;
	private int who=0;//who表示用户还是管理员为1表示用户，为2表示管理员
	
	private JButton formerPage;
	private JButton nextPage;
	private JButton borrow;
	private JButton order;
	private JButton Gopage;//前往输入的页数
	
	private int selectedRowIndex=-1;//用户鼠标选中的行
    
    private String keyWord;
    private String searchType;
    private JButton back;
    private JLabel pages;
    //显示当前页数，和总页数
    private int pageNow=1;
    private int pageAll=1;
    private JTextField pageGo;//输入页数框
    private int pageInput;
	/**
	 * 
	 * @param keyWord
	 * @param searchType
	 * @param who表示用户还是管理员为1表示用户，为2表示管理员
	 */
	public SearchResultView(String keyWord,String searchType,int who){
		this.keyWord=keyWord;
		this.searchType=searchType;
		this.who=who;
		initialize();
		if(who==1){
			userAction = new UserAction();
		}
		else{
			adAction=new AdAction();
			//根据用户不同更新页面按钮名称
			borrow.setText("删除");
			order.setText("修改");
		}
		frame.setVisible(true);
		getData();
		action();
	}
	//根据用户不同更新页面按钮名称
	public static SearchResultView searchResultView=null;

	// 单例模式，得到目前的此页面
	public static SearchResultView getInstance(String keyWord,String searchType,int who) {
		if (searchResultView == null) {
			searchResultView = new SearchResultView(keyWord, searchType, who);
		}
		return searchResultView;
	}
	
	//管理员修改信息后更改图书表图书表
	public void updateData(BookModel bm){
		myTableModel.setUpdateData(bm);
	}
	
	//查找到数据
	public void getData(){
		lists = new ArrayList<>();
		System.out.println("关键字："+keyWord+"搜索类型："+searchType);
		if(who==1){
			lists = userAction.searchBook(keyWord, searchType);
		}
		else{
			lists = adAction.searchBook(keyWord, searchType);
		}
		
		myTableModel =  new SBookTableModel(table, frame, lists);
		myTableModel.initData();
		
		pageAll = myTableModel.getRowListAll()/8+1;
		pages.setText("第"+ pageNow +"页 共"+  pageAll +"页");
	}
	
	//各种点击操作
	public void action() {
		
		//去往指定页
		Gopage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//将字符串转化为整形
				if(pageGo.getText().equals(""))
				{
					JOptionPane.showConfirmDialog(null, "请输入合适的页数！！", "提示信息", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					pageInput = Integer.parseInt(pageGo.getText());
					if (pageInput <= pageAll)
						myTableModel.goPage(pageInput);
					else
						JOptionPane.showConfirmDialog(null, "请输入合适的页数！！", "提示信息", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
		
		//点击上一页
		formerPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(myTableModel.formerPage()){
					selectedRowIndex=-1;//使选中的行数归零
					pageNow--;
					pages.setText("第"+ pageNow +"页 共"+  pageAll +"页");
				}
			}
		});
		
		// 点击下一页，刷新表格
		nextPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(myTableModel.nextPage()){
					selectedRowIndex=-1;//使选中的行数归零
					pageNow++;
					pages.setText("第"+ pageNow +"页 共"+  pageAll +"页");
				}
				    
			}
		});

		// 点击借阅/删除
		borrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//用户 借阅
				if(who==1){
					if (selectedRowIndex == -1 || table.getValueAt(selectedRowIndex, 6) == null) {
						System.out.println("请选择要借阅的图书");
						JOptionPane.showConfirmDialog(null, "请选择要借阅的图书", "提示信息", JOptionPane.PLAIN_MESSAGE);
					} else {
						// 借书操作,判断借书权限 TODO
						String ISBN = (String) table.getValueAt(selectedRowIndex, 1);
						if (userAction.borrowBook(ISBN)) {
							// TODO 借书成功，刷新表格
							myTableModel.updateData(selectedRowIndex);

							JOptionPane.showConfirmDialog(null, "借书成功", "提示信息", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showConfirmDialog(null, "借书失败？？？", "提示信息", JOptionPane.PLAIN_MESSAGE);
						}
					}
				}
				else{//管理员删除
					if (selectedRowIndex == -1 || table.getValueAt(selectedRowIndex, 6) == null) {
						JOptionPane.showConfirmDialog(null, "请选择要删除的图书", "提示信息", JOptionPane.PLAIN_MESSAGE);
					} else {
						String ISBN = (String) table.getValueAt(selectedRowIndex, 1);
						if (adAction.delBook(ISBN)) {
							// TODO 删除成功
							
							
							myTableModel.updateDelData(selectedRowIndex);
							//无选中行
							selectedRowIndex=-1;
							//重新计算当前页数
							pageAll = myTableModel.getRowListAll()/8+1;
							pages.setText("第"+ pageNow +"页 共"+  pageAll +"页");
							JOptionPane.showConfirmDialog(null, "删除成功", "提示信息", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showConfirmDialog(null, "删除失败？？？", "提示信息", JOptionPane.PLAIN_MESSAGE);
						}
					}
				}
				
			}
		});

		// 点击预约/更改
		order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (who == 1) {//用户预约
					if (selectedRowIndex == -1 || table.getValueAt(selectedRowIndex, 6) == null) {
						System.out.println("请选择要预约的图书");
						JOptionPane.showConfirmDialog(null, "请选择要预约的图书", "提示信息", JOptionPane.PLAIN_MESSAGE);
					} else {
						int RN = (int) table.getValueAt(selectedRowIndex, 6);
						if (RN > 0) {
							System.out.println("此书可直接借阅，不用预约");
							JOptionPane.showConfirmDialog(null, "此书可直接借阅，不用预约", "提示信息", JOptionPane.PLAIN_MESSAGE);
						} else {
							// 预约操作
							String ISBN = (String) table.getValueAt(selectedRowIndex, 0);
							if (userAction.orderBook(ISBN)) {
								JOptionPane.showConfirmDialog(null, "预约成功", "提示信息", JOptionPane.PLAIN_MESSAGE);
							} else {
								JOptionPane.showConfirmDialog(null, "预约失败？？？", "提示信息", JOptionPane.PLAIN_MESSAGE);
							}
						}
					}
				}
				else{//管理员更改
					if (selectedRowIndex == -1 || table.getValueAt(selectedRowIndex, 6) == null) {
						JOptionPane.showConfirmDialog(null, "请选择要更改的图书", "提示信息", JOptionPane.PLAIN_MESSAGE);
					} else {
						InputBookView inputBookView = new InputBookView(myTableModel.getChoose(selectedRowIndex), 2);
						inputBookView.getFrame().setVisible(true);
					}
				}
			}
		});

		// 点击表格事件
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
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
					// System.out.print(list.get(selectedRowIndex).get("i"));
					System.out.print(selectedRowIndex);

					String string = (String) table.getValueAt(selectedRowIndex, 1);

					System.out.print(string);
				}
			}
		});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchResultView =null;
				frame.dispose();
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
		
		JLabel label = new JLabel("\u641C\u7D22\u7ED3\u679C");
		label.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label.setBounds(471, 13, 113, 31);
		panel.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 57, 1000, 600);
		panel.add(scrollPane);
		
		table = new JTable();
		//设置类不可随数据大小改变
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.setRowHeight(70);
		scrollPane.setViewportView(table);
		
		
	    nextPage = new JButton("下一页");
	    nextPage.setFont(new Font("华文楷体", Font.PLAIN, 25));
	    nextPage.setBackground(new Color(102, 204, 204));
		nextPage.setBounds(503, 676, 113, 44);
		panel.add(nextPage);
		
	    formerPage = new JButton("上一页");
	    formerPage.setBackground(new Color(102, 204, 204));
	    formerPage.setFont(new Font("华文楷体", Font.PLAIN, 25));
		formerPage.setBounds(280, 676, 113, 44);
		panel.add(formerPage);
		
		borrow = new JButton("借阅");
		borrow.setFont(new Font("华文楷体", Font.PLAIN, 25));
		borrow.setBackground(new Color(153, 204, 153));
		borrow.setBounds(1038, 284, 130, 44);
		panel.add(borrow);
		
		order = new JButton("预约");
		order.setBackground(new Color(153, 102, 255));
		order.setFont(new Font("华文楷体", Font.PLAIN, 25));
		order.setBounds(1038, 398, 130, 44);
		panel.add(order);
		
		back = new JButton("退出");
		back.setFont(new Font("华文楷体", Font.PLAIN, 15));
		back.setBounds(1079, 17, 89, 27);
		panel.add(back);
		
	    pages = new JLabel("第 i 页 共 n 页");
		pages.setFont(new Font("华文楷体", Font.PLAIN, 20));
		pages.setBounds(897, 683, 119, 35);
		panel.add(pages);
		
		JLabel label_1 = new JLabel("第          页");
		label_1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_1.setBounds(661, 685, 102, 31);
		panel.add(label_1);
		
		pageGo = new JTextField();
		pageGo.setBounds(687, 687, 38, 31);
		panel.add(pageGo);
		pageGo.setColumns(10);
		pageGo.setDocument(new LimitNumberLenght(3));
		
		Gopage = new JButton("前往");
		Gopage.setBackground(new Color(220, 20, 60));
		Gopage.setFont(new Font("华文楷体", Font.PLAIN, 20));
		Gopage.setBounds(776, 689, 77, 31);
		panel.add(Gopage);
		
	}	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
