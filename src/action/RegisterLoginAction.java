package action;

import dao.AdDao;
import db.SignInFeedback;
import fileOpreation.UserFormOp;

import java.io.IOException;
import java.text.ParseException;

public class RegisterLoginAction {
	@SuppressWarnings("unused")

	public int SignIn(String ID,String password,int userType) throws IOException, ParseException {
		if(ID.equals("")){
			System.out.println("请输入用户ID");
			return SignInFeedback.NO_ID;
		}
		
		else if(password.equals("")){
			System.out.println("请输入密码");
			return SignInFeedback.NO_PASSWORD;
		}
		else{
			System.out.println(ID);
			System.out.println(password);
			//管理员登录
			if(userType==3){//TODO
				AdDao adDao = AdDao.getInstance();
				adDao.readUserForm();
				return adDao.signIn(ID,password);
			}
			else{//用户登录
				UserFormOp userFormOp = UserFormOp.getInstance();
				return userFormOp.signIn(ID,password);
			}
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
