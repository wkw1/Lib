package action;

import dao.AdDao;
import db.SignInFeedback;
import fileOpreation.UserFormOp;
import model.UserModel;

import java.io.IOException;
import java.text.ParseException;

public class RegisterLoginAction {
	@SuppressWarnings("unused")

	public int SignIn(String ID,String password,int userType) throws IOException, ParseException {
		if(ID.equals("")){
			System.out.println("�������û�ID");
			return SignInFeedback.NO_ID;
		}
		
		else if(password.equals("")){
			System.out.println("����������");
			return SignInFeedback.NO_PASSWORD;
		}
		else{
			System.out.println(ID);
			System.out.println(password);
			//����Ա��¼
			if(userType==3){
				AdDao adDao = AdDao.getInstance();
				adDao.readUserForm();
				return adDao.signIn(ID,password);
			}
			else{//�û���¼
				UserFormOp userFormOp = UserFormOp.getInstance();
				return userFormOp.signIn(ID,password);
			}
		}
	}
	
	public int register(String ID,String password,String passwordOK){
		if(ID.equals("")){
			System.out.println("�������û�ID");
			return SignInFeedback.NO_ID;
		}
		else if(password==null||password.equals("")){
			System.out.println("����������");
			return SignInFeedback.NO_PASSWORD;
		}
		else if(!password.equals(passwordOK)){
			System.out.println("p:"+password +"po:"+ passwordOK);
			return SignInFeedback.NOT_EQUAL;
		}
		else{
			//TODO ��֤�Ƿ��Ѵ�����Ϣ����ע������Ϣ����ע���ļ�
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
