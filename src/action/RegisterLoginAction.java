package action;

import db.SignInFeedback;
import fileOpreation.UserFormOp;

public class RegisterLoginAction {
	@SuppressWarnings("unused")

	public int SignIn(String ID,String password,int userType){
		if(ID.equals("")){
			System.out.println("请输入用户ID");
			return SignInFeedback.NO_ID;
		}
		
		else if(password.equals("")){
			System.out.println("请输入密码");
			return SignInFeedback.NO_PASSWORD;
		}
		else{
			//TODO 验证登录者 操作文件
			System.out.println(ID);
			System.out.println(password);
			UserFormOp userFormOp = UserFormOp.getInstance();
			return userFormOp.signIn(ID,password);
		}
	}
	
	public int Register(String ID,String password,String passwordOK){
		if(ID.equals("")){
			System.out.println("请输入用户ID");
			return 1;
		}
		else if(password==null||password.equals("")){
			System.out.println("请输入密码");
			return 2;
		}
		else if(!password.equals(passwordOK)){
			System.out.println("p:"+password +"po:"+ passwordOK);
			return 3;
		}
		else{
			//TODO 验证是否已存入信息，将注册这信息存入注册文件
			
			
			System.out.println("注册成功");
			return 4;
		}
		
		
	}
	
	
	
}
