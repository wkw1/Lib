package dao;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by ¿íÎ° on 2017/5/9.
 */
public class SystemInfoDao {
    public static Date SysTemDate;
    public static String SysTemIn;
    public static String developer;
    public static String version;
    private static String filePath="file//systemInfo.txt";
    public static boolean readUserForm(){

        InputStreamReader read = null;
        try {
            read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        int i = 1;
        try {
            eachLine = reader.readLine();
            for (String Info : eachLine.split("\\|")) {
                switch (i) {
                    case 1:
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date = sdf.parse(Info);
                        SysTemDate = new java.sql.Date(date.getTime());
                        i++;
                        break;
                    case 2:
                        SysTemIn = Info;
                        i++;
                        break;
                    case 3:
                        developer = Info;
                        i++;
                        break;
                    case 4:
                        version = Info;
                        i++;
                        break;
                }
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }
}
