package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class GetTable1FromDocx {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream("/Users/ivanlikhvatov/Downloads/Dialogovaya_sistema.docx");
        XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
        List<XWPFTable> tables = xdoc.getTables();

        XWPFTable table = tables.get(0);


        for (XWPFTableRow row : table.getRows()) {

            for (String string : row.getCell(1).getText().split(",")) {
                System.out.println("sinonim('" + string.toLowerCase().replaceAll(" ", "") + "', '" + row.getCell(0).getText().toLowerCase().replaceAll(" ", "") + "').");
            }

        }

//        System.out.println(table.getRow(1).getCell(1).getText());

    }
}
