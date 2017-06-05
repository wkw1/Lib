package dao;

import fileOpreation.BookFormOp;
import model.BookModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.SystemEntry;
import widget.ISBNCreate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宽伟 on 2017/5/23.
 * 批量导入图书类
 */
public class BatchImportBook {
    public List<BookModel> bookModels =  new ArrayList<>();

    public boolean importBook(String fileName){
        if(!getFromExcel(fileName))
            return false;
        return inputList();
    }

    public boolean inputList(){
        BookFormOp bookFormOp =  BookFormOp.getInstance();
        for(int i = 0;i<bookModels.size();i++){
            bookFormOp.inputOne(bookModels.get(i));
        }
        return true;
    }

    //文件操作方法
    public boolean getFromExcel(String filename){
        InputStream is=null;
        Workbook wb;
        String type=filename.substring(filename.lastIndexOf(".")+1);//获取文件类型
        File file=new File(filename);
        if(file==null){
            System.out.println("打开文件失败！！");
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
            System.out.println("打开文件失败！！");
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
     * @param wb:excel文件对象
     */
    public void readXls(Workbook wb){
        BookModel bookModel;
        Sheet sheet=wb.getSheetAt(0);//对应excel正文对象
        for(int i=1;i<=sheet.getLastRowNum();i++){
            HSSFRow hssfrow=(HSSFRow) sheet.getRow(i);//行
            bookModel = new BookModel();

            //依次读取四列数据
            HSSFCell booName = hssfrow.getCell(0);
            booName.setCellType(Cell.CELL_TYPE_STRING);
            bookModel.setName(booName.getStringCellValue());

            HSSFCell introduction = hssfrow.getCell(1);
            introduction.setCellType(Cell.CELL_TYPE_STRING);
            bookModel.setIntroduction(introduction.getStringCellValue());

            HSSFCell type = hssfrow.getCell(2);
            type.setCellType(Cell.CELL_TYPE_STRING);
            bookModel.setBookType(type.getStringCellValue());

            HSSFCell author = hssfrow.getCell(3);
            author.setCellType(Cell.CELL_TYPE_STRING);
            bookModel.setAuthor(author.getStringCellValue());

            HSSFCell press = hssfrow.getCell(4);
            press.setCellType(Cell.CELL_TYPE_STRING);
            bookModel.setPress(press.getStringCellValue());

            HSSFCell totalNumber = hssfrow.getCell(5);
            totalNumber.setCellType(Cell.CELL_TYPE_STRING);
            bookModel.setTN(Integer.valueOf(totalNumber.getStringCellValue()));

            HSSFCell power = hssfrow.getCell(6);
            power.setCellType(Cell.CELL_TYPE_STRING);
            bookModel.setPowerNeed(Integer.valueOf(power.getStringCellValue()));

            bookModel.setRN(bookModel.getTN());
            bookModel.setStorageTime(SystemEntry.date);
            bookModel.setISBN(ISBNCreate.CreISBN(bookModel));

            bookModels.add(bookModel);
        }
    }
    /**
     * 暂时不可用
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
