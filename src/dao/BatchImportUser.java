package dao;

/**
 * Created by ��ΰ on 2017/5/28.
 */

import fileOpreation.UserFormOp;
import model.UserModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.SystemEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ��ΰ on 2017/5/23.
 * ��������ͼ����
 */
public class BatchImportUser {
    public List<UserModel> userModels =  new ArrayList<>();

    public boolean importUser(String fileName){
        if(!getFromExcel(fileName))
            return false;
        return inputList();
    }

    public boolean inputList(){
        UserFormOp userFormOp =  UserFormOp.getInstance();
        for(int i = 0;i<userModels.size();i++){
            userFormOp.addOne(userModels.get(i));
        }
        return true;
    }

    //�ļ���������
    public boolean getFromExcel(String filename){
        InputStream is=null;
        Workbook wb;
        String type=filename.substring(filename.lastIndexOf(".")+1);//��ȡ�ļ�����
        File file=new File(filename);
        if(file==null){
            System.out.println("���ļ�ʧ�ܣ���");
            return false;
        }
        try {
            is=new FileInputStream(file);
            if(type.equals("xls")){
                wb=new HSSFWorkbook(is);
                readXls(wb);
            }else if(type.equals("xlsx")){
                wb=new XSSFWorkbook(is);
                readXlsx(wb);
            }
            else{
                return false;
            }
        } catch (Exception e) {
            System.out.println("���ļ�ʧ�ܣ���");
        } finally{
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    /**
     * @param wb:excel�ļ�����
     */
    public void readXls(Workbook wb){
        UserModel userModel;
        Sheet sheet=wb.getSheetAt(0);//��Ӧexcel���Ķ���
        for(int i=1;i<=sheet.getLastRowNum();i++){
            HSSFRow hssfrow=(HSSFRow) sheet.getRow(i);//��
            userModel = new UserModel();

            //���ζ�ȡ��������
            HSSFCell userName = hssfrow.getCell(0);
            userName.setCellType(Cell.CELL_TYPE_STRING);
            userModel.setName(userName.getStringCellValue());

            HSSFCell ID = hssfrow.getCell(1);
            ID.setCellType(Cell.CELL_TYPE_STRING);
            userModel.setID(ID.getStringCellValue());

            HSSFCell school = hssfrow.getCell(2);
            school.setCellType(Cell.CELL_TYPE_STRING);
            userModel.setSchool(school.getStringCellValue());


            HSSFCell power = hssfrow.getCell(3);
            power.setCellType(Cell.CELL_TYPE_STRING);
            int powerNum = Integer.valueOf(power.getStringCellValue());//�õ�����Ȩ�ޣ�������ʦѧ��
            userModel.setPower(powerNum);

            if(powerNum==1){
                userModel.setBalance(20);
                userModel.setANBooks(30);
            }
            else{
                userModel.setBalance(30);
                userModel.setANBooks(45);
            }
            userModel.setPassword("");
            userModel.setJoinDate(SystemEntry.date);
            userModel.setBNBooks(0);
            userModels.add(userModel);
        }
    }
    /**
     * ��ʱ������
     */
    public void readXlsx(Workbook wb){
        Sheet sheet=wb.getSheetAt(0);
        for (int i = 0; i <=sheet.getLastRowNum(); i++) {
            XSSFRow xssfrow=(XSSFRow) sheet.getRow(i);
            for (int j = 0; j <xssfrow.getLastCellNum(); j++) {
                XSSFCell xssfcell=xssfrow.getCell(j);
                if(xssfcell!=null){
                    xssfcell.setCellType(Cell.CELL_TYPE_STRING);
                    String cellValue=xssfcell.getStringCellValue();
                    System.out.print(cellValue);
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}

