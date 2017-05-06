package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宽伟 on 2017/5/6.
 * 操作存储搜素关键字文件的Dao类
 * 实现将对关键字增加和搜索数目的更改
 */
public class SearchKeyDao {

    public class KeyWord{

        public String keyWord;//关键字
        public int number;//搜索次数

        public KeyWord(){}
        public KeyWord(String keyWord, int number) {
            this.keyWord = keyWord;
            this.number = number;
        }

    }

    public static List<KeyWord> keyWordLists= new ArrayList<>();


    public static SearchKeyDao searchKeyDao=null;
    public static SearchKeyDao getInstance(){
        if(searchKeyDao==null){
            searchKeyDao =  new SearchKeyDao();
        }
        return searchKeyDao;
    }

    public boolean addOne(String keyWord){
        int i;
        for( i=0;i<keyWordLists.size();i++){
            if(keyWord.equals(keyWordLists.get(i).keyWord)){
                int n=++keyWordLists.get(i).number;
                for(int j=i;j>=0;j--){
                    if(keyWordLists.get(j).number>n){
                        keyWordLists.add(j+1,keyWordLists.get(i));
                        keyWordLists.remove(i+1);
                        return true;
                    }
                }
                return true;
            }
        }
        if(i==keyWordLists.size()){
            return keyWordLists.add(new KeyWord(keyWord,1));
        }
        return false;
    }



    public boolean readUserForm() throws IOException, ParseException {
        String filePath="file//searchKeyWord.txt";
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        KeyWord keyWord;
        int UserNum=0;
        try {
            while((eachLine=reader.readLine())!=null)
            {
                int i=1;
                keyWord=new SearchKeyDao.KeyWord();
                for(String Info:eachLine.split("\\|"))
                {
                    switch(i){
                        case 1:
                            keyWord.keyWord=Info;
                            i=i+1;
                            break;
                        case 2:
                            keyWord.number=Integer.valueOf(Info);
                            i=i+1;
                            break;
                    }
                }
                keyWordLists.add(keyWord);
                UserNum=UserNum+1;
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        read.close();
        return true;
    }

}
