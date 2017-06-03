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
    private int infoNum;//��Ϣ����
    private Object[][] infoData;
    private String[] tatil = {"������", "������ID", "�¼�", "����ʱ��"};
    
    private int selectedRowIndex=-1;//ѡ�б���е���

    private DefaultTableModel tableModel;
    private AdAction adAction;


    public UserInfoView() {
        initialize();
        adAction = AdAction.getInstance();
        
        initData();
        action();
    }

    //�õ���Ϣ����
    private void initData() {
        userInfos = adAction.getUserInfo();
        if (userInfos == null) {
            InfoModel infoModel = new InfoModel();
            infoModel.setInformederID("����Ϣ");
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

        //ɾ�����з�������Ա����Ϣ
        readAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(adAction.delAllUserInfo()){
                    JOptionPane.showConfirmDialog(null, "ɾ���ɹ�", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
                    userInfos.clear();
                    updateTable();
                }
                else
                    JOptionPane.showConfirmDialog(null, "ɾ��ʧ��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);

            }
        });

        //ɾ��һ����Ϣ
        read.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRowIndex == -1) {
                    JOptionPane.showConfirmDialog(null, "��ѡ����Ϣ", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
                } else {
                    if(adAction.delOneInfo(userInfos.get(selectedRowIndex))){
                        userInfos.remove(selectedRowIndex);
                        updateTable();
                        selectedRowIndex=-1;
                        JOptionPane.showConfirmDialog(null, "ɾ���ɹ�", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

    	
    	//����Ա���û���ֵ
		addBalance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedRowIndex == -1) {
					JOptionPane.showConfirmDialog(null, "��ѡ����Ϣ", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				} else {
					String ID = (String) table.getValueAt(selectedRowIndex, 1);
					String str= JOptionPane.showInputDialog(null, "�����ֵ���","��Ϣ��", 
							JOptionPane.PLAIN_MESSAGE);
					float money=0;
					if(str!=null){
						money = Float.valueOf(str);
					}
					if (adAction.recharge(money, ID)) {
						JOptionPane.showConfirmDialog(null, "��ֵ�ɹ�", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
						LogDao.addLogSystem("����ԱΪIDΪ" + ID + "���û���ֵ");
					} else
						JOptionPane.showConfirmDialog(null, "��ֵʧ��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);

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
				// ���û�ѡȡ�ĵ������ݲ���
				int selectRows = table.getSelectedRows().length;// ȡ���û���ѡ�е�����
				if (selectRows == 1) {
					selectedRowIndex = table.getSelectedRow();// ȡ���û���ѡ����
				}
			}
		});

    }

    private void initialize() {
        frame = new JFrame();
        InitWindow.init(frame);
        // ����panel��Ϊ����,�ؼ���������
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1200, 800);
        panel.setLayout(null);
        panel.setOpaque(false);// ����Ϊ͸�������Կ���ͼƬ
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
        exit.setFont(new Font("����", Font.PLAIN, 15));
        exit.setBounds(1089, 0, 93, 32);
        panel.add(exit);
        
        read = new JButton("\u6807\u8BB0\u5DF2\u8BFB");
        read.setBackground(new Color(0, 128, 128));
        read.setForeground(new Color(0, 0, 0));
        read.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
        read.setBounds(891, 269, 145, 45);
        panel.add(read);
        
        addBalance = new JButton("\u7ED9\u4ED6\u5145\u503C");
        addBalance.setBackground(new Color(0, 128, 128));
        addBalance.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
        addBalance.setBounds(891, 362, 145, 45);
        panel.add(addBalance);
        
        readAll = new JButton("\u5168\u90E8\u6807\u8BB0\u5DF2\u8BFB");
        readAll.setBackground(new Color(0, 128, 128));
        readAll.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
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
