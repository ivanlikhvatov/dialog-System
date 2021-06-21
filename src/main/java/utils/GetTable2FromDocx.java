package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GetTable2FromDocx {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream("/Users/ivanlikhvatov/University/ThirdCourse/FirstSemestr/Шварц/Диалоговая_ситема/Dialogovaya_sistema.docx");
        XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
        List<XWPFTable> tables = xdoc.getTables();

        XWPFTable table = tables.get(1);


        for (XWPFTableRow row : table.getRows()) {

            for (XWPFParagraph paragraph :row.getCell(1).getParagraphs()) {
                if (!paragraph.getText().equals("")) {
                    System.out.println("keyword_answers('" + row.getCell(0).getText().toLowerCase().replaceAll(" ", "").replaceAll(" ", "") + "', ['" + paragraph.getText() + "']).");
                }
            }
        }

//        System.out.println(table.getRow(1).getCell(1).getText());

    }
}
