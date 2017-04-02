package action;

import model.UserModel;

public class RegisterLoginAction {
	@SuppressWarnings("unused")
	private static UserModel user; 
	
	public int SignIn(String ID,String password,int userType){
		if(ID.equals("")){
			System.out.println("请输入用户ID");
			return 1;
		}
		
		else if(password.equals("")){
			System.out.println("请输入密码");
			return 2;
		}
		else{
			//TODO 验证登录者 操作文件
			
			System.out.println(ID);
			System.out.println(password);
			System.out.println("登录成功！");
			
		    user = new UserModel(ID);
			
			return 3;
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
