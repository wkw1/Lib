package action;

import model.UserModel;

public class RegisterLoginAction {
	@SuppressWarnings("unused")
	private static UserModel user; 
	
	public int SignIn(String ID,String password,int userType){
		if(ID.equals("")){
			System.out.println("�������û�ID");
			return 1;
		}
		
		else if(password.equals("")){
			System.out.println("����������");
			return 2;
		}
		else{
			//TODO ��֤��¼�� �����ļ�
			
			System.out.println(ID);
			System.out.println(password);
			System.out.println("��¼�ɹ���");
			
		    user = new UserModel(ID);
			
			return 3;
		}
	}
	
	public int Register(String ID,String password,String passwordOK){
		if(ID.equals("")){
			System.out.println("�������û�ID");
			return 1;
		}
		else if(password==null||password.equals("")){
			System.out.println("����������");
			return 2;
		}
		else if(!password.equals(passwordOK)){
			System.out.println("p:"+password +"po:"+ passwordOK);
			return 3;
		}
		else{
			//TODO ��֤�Ƿ��Ѵ�����Ϣ����ע������Ϣ����ע���ļ�
			
			
			System.out.println("ע��ɹ�");
			return 4;
		}
		
		
	}
	
	
	
}
