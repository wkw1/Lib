package dao;

import view.SystemEntry;

import java.io.*;

/**
 * Created by ��ΰ on 2017/5/9.
 * ��־�ļ�
 */
public class LogDao {
    public static File f=new File("file//Log.txt");

    public static void addLogSystem(String log){
        BufferedWriter fw= null;/////������Ҫ�ı����ʽ
        try {
            fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true),"GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String BkInfo="\r�¼���"+log+" ʱ�䣺"+ SystemEntry.date;
        try {
            fw.write(BkInfo);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
