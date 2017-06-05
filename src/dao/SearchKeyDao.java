package dao;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ��ΰ on 2017/5/6.
 * �����洢���عؼ����ļ���Dao��
 * ʵ�ֽ��Թؼ������Ӻ�������Ŀ�ĸ���
 */
public class SearchKeyDao {

    public class KeyWord{

        public String keyWord;//�ؼ���
        public int number;//��������

        public KeyWord(){}
        public KeyWord(String keyWord, int number) {
            this.keyWord = keyWord;
            this.number = number;
        }

    }

    private String filePath=FilePath.rootFilePath+ "\\searchKeyWord.txt";

    public List<KeyWord> keyWordLists= new ArrayList<>();
    public boolean iSModify=false;//��־�Ƿ��޸����ļ�
    public boolean iSAdd = false;//��־�Ƿ���������Ŀ

    public int keyNumber;


    public static SearchKeyDao searchKeyDao=null;
    public static SearchKeyDao getInstance(){
        if(searchKeyDao==null){
            searchKeyDao =  new SearchKeyDao();
        }
        return searchKeyDao;
    }

    //���������ؼ���
    public boolean addOne(String keyWord){
        int i;
        iSModify = true;
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
            iSAdd = true;
            return keyWordLists.add(new KeyWord(keyWord,1));
        }
        return false;
    }

    public boolean readUserForm() throws IOException, ParseException {
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        KeyWord keyWord;
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
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        keyNumber = keyWordLists.size();
        read.close();
        return true;
    }

    public void addKeywords() throws IOException {
        for(int n=keyNumber;n<keyWordLists.size();n++){
            addOneKeyWord(keyWordLists.get(n));
        }
    }

    public void addOneKeyWord(KeyWord keyWord) throws IOException {
        File f=new File(filePath);
        BufferedWriter fw = null;/////������Ҫ�ı����ʽ

        fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "GBK"));
        String BkInfo =  keyWord.keyWord + "|" + keyWord.number+"\r\n" ;
        fw.write(BkInfo);
        fw.close();
    }

    public void writeFile() throws IOException{
        File f=new File(filePath);
        BufferedWriter fw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false)));
        if(keyWordLists.size()==0)
            return;
        fw.write(keyWordLists.get(0).keyWord+"|"+keyWordLists.get(0).number+"\r\n");
        for(int i=1;i<keyWordLists.size();i++)
        {
            String BkInfo=keyWordLists.get(i).keyWord+"|"+keyWordLists.get(i).number+"\r\n";
            fw.write(BkInfo);
        }
        fw.close();
    }

}
