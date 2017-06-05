package view;

import action.UserAction;
import widget.Encryp;
import widget.InitWindow;


import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterInfo {
	
	private JButton change;

	private JFrame frame;
	private JPasswordField oldPassword;
	private JPasswordField newPassword;
	private JPasswordField surePassword;

	public static AlterInfo alterInfo;
	private JButton cancel;
	public static AlterInfo getInstance(){
		if(alterInfo==null)
			alterInfo = new AlterInfo();
		return alterInfo;
	}

	public AlterInfo() {
		initialize();
		action();
	}

	private void  action(){
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				String oldString = oldPassword.getText();
				@SuppressWarnings("deprecation")
				String newString = newPassword.getText();
				@SuppressWarnings("deprecation")
				String sureString = surePassword.getText();

				if(oldString==null){
					JOptionPane.showConfirmDialog(null, "请输入密码！！",
							"提示信息", JOptionPane.PLAIN_MESSAGE);
				}
				else if(newString==null||!newString.equals(sureString)){
					JOptionPane.showConfirmDialog(null, "两次密码输入不一致！！",
							"提示信息", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					UserAction userAction = UserAction.getInstance();
					String string = Encryp.Encrypt(oldString);
					if(!string.equals(userAction.user.getPassword())){
						JOptionPane.showConfirmDialog(null, "原密码不正确！！",
								"提示信息", JOptionPane.PLAIN_MESSAGE);
					}
					else{
						if(userAction.alterPassword(Encryp.Encrypt(newString))){
							JOptionPane.showConfirmDialog(null, "更改密码成功！！",
									"提示信息", JOptionPane.PLAIN_MESSAGE);
							frame.setVisible(true);
							frame.dispose();
							alterInfo=null;
						}
						else
							JOptionPane.showConfirmDialog(null, "更改密码失败！！",
									"提示信息", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});


		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				frame.dispose();
				alterInfo=null;
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
		
		JLabel label = new JLabel("\u65B0\u5BC6\u7801");
		label.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label.setBounds(158, 195, 98, 45);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_1.setBounds(158, 266, 135, 35);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u7801");
		lblNewLabel.setFont(new Font("华文楷体", Font.PLAIN, 20));
		lblNewLabel.setBounds(158, 131, 98, 35);
		panel.add(lblNewLabel);
		
		oldPassword = new JPasswordField();
		oldPassword.setBounds(263, 133, 143, 35);
		panel.add(oldPassword);
		
		newPassword = new JPasswordField();
		newPassword.setBounds(263, 202, 143, 35);
		panel.add(newPassword);
		
		surePassword = new JPasswordField();
		surePassword.setBounds(263, 268, 143, 35);
		panel.add(surePassword);
		
		JLabel label_2 = new JLabel("\u66F4\u6539\u5BC6\u7801");
		label_2.setFont(new Font("华文楷体", Font.PLAIN, 30));
		label_2.setBounds(221, 49, 195, 45);
		panel.add(label_2);
		
		change = new JButton("\u786E\u8BA4\u66F4\u6539");
		change.setBackground(new Color(0, 128, 128));
		change.setFont(new Font("华文楷体", Font.PLAIN, 25));
		change.setBounds(204, 394, 152, 45);
		panel.add(change);
		
		cancel = new JButton("\u53D6\u6D88");
		cancel.setBackground(new Color(255, 0, 0));
		cancel.setFont(new Font("宋体", Font.PLAIN, 15));
		cancel.setBounds(1089, 0, 93, 27);
		panel.add(cancel);
	}

	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
