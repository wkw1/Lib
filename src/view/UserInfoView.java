package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import widget.InitWindow;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import action.AdAction;
import dao.LogDao;
import fileOpreation.InfoFormOp;
import model.InfoModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class UserInfoView {

    private JFrame frame;
    
    private JButton exit;
    private JButton read;
    private JButton readAll;
    private JButton addBalance;
    

    private JTable table;

    private List<InfoModel> userInfos = new ArrayList<>();
    private int infoNum;//消息数量
    private Object[][] infoData;
    private String[] tatil = {"发送人", "发送人ID", "事件", "发送时间"};
    
    private int selectedRowIndex=-1;//选中表格中的行

    private DefaultTableModel tableModel;
    private AdAction adAction;


    public UserInfoView() {
        initialize();
        adAction = AdAction.getInstance();
        
        initData();
        action();
    }

    //得到消息数据
    private void initData() {
        userInfos = adAction.getUserInfo();
        if (userInfos == null) {
            InfoModel infoModel = new InfoModel();
            infoModel.setInformederID("无消息");
            userInfos.add(infoModel);
        }
       updateTable();
    }

    private void updateTable(){
        infoNum = userInfos.size();

        infoData = new Object[infoNum][4];

        for (int i = 0; i < infoNum; i++) {
            infoData[i][0] = userInfos.get(i).getInformer();
            infoData[i][1] = userInfos.get(i).getInformerID();
            infoData[i][2] = userInfos.get(i).getInformThing();
            infoData[i][3] = userInfos.get(i).getInformDate();
            System.out.println(userInfos.get(i).getInformThing());
        }
        tableModel = new DefaultTableModel(infoData, tatil);
        table.setModel(tableModel);
        table.updateUI();
    }

    private void action() {

        //删除所有发给管理员的消息
        readAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(adAction.delAllUserInfo()){
                    JOptionPane.showConfirmDialog(null, "删除成功", "提示信息", JOptionPane.PLAIN_MESSAGE);
                    userInfos.clear();
                    updateTable();
                }
                else
                    JOptionPane.showConfirmDialog(null, "删除失败", "提示信息", JOptionPane.PLAIN_MESSAGE);

            }
        });

        //删除一条消息
        read.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRowIndex == -1) {
                    JOptionPane.showConfirmDialog(null, "请选择消息", "提示信息", JOptionPane.PLAIN_MESSAGE);
                } else {
                    if(adAction.delOneInfo(userInfos.get(selectedRowIndex))){
                        userInfos.remove(selectedRowIndex);
                        updateTable();
                        selectedRowIndex=-1;
                        JOptionPane.showConfirmDialog(null, "删除成功", "提示信息", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

    	
    	//管理员给用户充值
		addBalance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedRowIndex == -1) {
					JOptionPane.showConfirmDialog(null, "请选择消息", "提示信息", JOptionPane.PLAIN_MESSAGE);
				} else {
					String ID = (String) table.getValueAt(selectedRowIndex, 1);
					String str= JOptionPane.showInputDialog(null, "输入充值金额","消息框", 
							JOptionPane.PLAIN_MESSAGE);
					float money=0;
					if(str!=null){
						money = Float.valueOf(str);
					}
					if (adAction.recharge(money, ID)) {
						JOptionPane.showConfirmDialog(null, "充值成功", "提示信息", JOptionPane.PLAIN_MESSAGE);
						LogDao.addLogSystem("管理员为ID为" + ID + "的用户充值");
					} else
						JOptionPane.showConfirmDialog(null, "充值失败", "提示信息", JOptionPane.PLAIN_MESSAGE);

				}
			}
		});

    	exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
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

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(59, 88, 709, 609);
        panel.add(scrollPane);

        table = new JTable(tableModel);
        scrollPane.setViewportView(table);


        JLabel label_1 = new JLabel("\u8BF7\u6C42\u5145\u503C\u7528\u6237");
        scrollPane.setColumnHeaderView(label_1);
        
        exit = new JButton("\u5173\u95ED");
        exit.setBackground(new Color(255, 0, 0));
        exit.setFont(new Font("宋体", Font.PLAIN, 15));
        exit.setBounds(1089, 0, 93, 32);
        panel.add(exit);
        
        read = new JButton("\u6807\u8BB0\u5DF2\u8BFB");
        read.setBackground(new Color(0, 128, 128));
        read.setForeground(new Color(0, 0, 0));
        read.setFont(new Font("华文楷体", Font.PLAIN, 20));
        read.setBounds(891, 269, 145, 45);
        panel.add(read);
        
        addBalance = new JButton("\u7ED9\u4ED6\u5145\u503C");
        addBalance.setBackground(new Color(0, 128, 128));
        addBalance.setFont(new Font("华文楷体", Font.PLAIN, 20));
        addBalance.setBounds(891, 362, 145, 45);
        panel.add(addBalance);
        
        readAll = new JButton("\u5168\u90E8\u6807\u8BB0\u5DF2\u8BFB");
        readAll.setBackground(new Color(0, 128, 128));
        readAll.setFont(new Font("华文楷体", Font.PLAIN, 20));
        readAll.setBounds(867, 161, 179, 45);
        panel.add(readAll);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
