package action;

import dao.AdDao;
import db.SignInFeedback;
import fileOpreation.UserFormOp;
import model.UserModel;

import java.io.IOException;
import java.text.ParseException;

public class RegisterLoginAction {

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
			if(userType==3){
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
	
	public int register(String ID,String password,String passwordOK){
		if(ID.equals("")){
			System.out.println("请输入用户ID");
			return SignInFeedback.NO_ID;
		}
		else if(password==null||password.equals("")){
			System.out.println("请输入密码");
			return SignInFeedback.NO_PASSWORD;
		}
		else if(!password.equals(passwordOK)){
			System.out.println("p:"+password +"po:"+ passwordOK);
			return SignInFeedback.NOT_EQUAL;
		}
		else{
			//TODO 验证是否已存入信息，将注册这信息存入注册文件
            UserFormOp userFormOp = UserFormOp.getInstance();
            return userFormOp.register(ID,password);
		}
	}

	public static boolean signOut(){
		UserModel.userModel=null;
		UserAction.userAction=null;
		return true;
	}
}
