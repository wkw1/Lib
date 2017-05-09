package dao;

import view.SystemEntry;

import java.io.*;

/**
 * Created by 宽伟 on 2017/5/9.
 * 日志文件
 */
public class LogDao {
    public static File f=new File("file//Log.txt");

    public static void addLogSystem(String log){
        BufferedWriter fw= null;/////可能需要改编码格式
        try {
            fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true),"GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String BkInfo="\r事件："+log+" 时间："+ SystemEntry.date;
        try {
            fw.write(BkInfo);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
