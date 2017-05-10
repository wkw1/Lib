package widget;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 宽伟 on 2017/5/5.
 * 密码编码函数，暂时未使用
 */
public class Encryp {
    public static String Encrypt(String Pres) {
        MessageDigest md5=null;
        try {
            md5=MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        MessageDigest sha=null;
        try {
            sha=MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        byte[] bta= new byte[0];
        try {
            bta = Pres.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] mda=md5.digest(bta);
        mda=sha.digest(mda);
        StringBuffer hexValue=new StringBuffer();
        for (int i=0; i<mda.length;i++) {
            int val =((int)mda[i]) & 0xff;
            if(val<16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
