package utils;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GetTable3FromDocx {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream("/Users/ivanlikhvatov/Downloads/однокоренные.docx");
        XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
        List<XWPFTable> tables = xdoc.getTables();

        XWPFTable table = tables.get(0);

//
//        for (XWPFTableRow row : table.getRows()) {
//
//            List<String> tmp = Arrays.asList(row.getCell(1).getText()
//                    .replaceAll(",", "")
//                    .replaceAll("кого, чего\\?", "")
//                    .replaceAll("кому, чему\\?", "")
//                    .replaceAll("кого, что\\?", "")
//                    .replaceAll("кем, чем\\?", "")
//                    .replaceAll("о ком, о чём\\?", "")
//                    .replaceAll("кто, что\\?", "")
//                    .replaceAll("где\\?", "")
//                    .replaceAll("о ", "")
//                    .replaceAll("в ", "")
//                    .replaceAll("И:", "")
//                    .replaceAll("Р:", "")
//                    .replaceAll("Д:", "")
//                    .replaceAll("В:", "")
//                    .replaceAll("Т:", "")
//                    .replaceAll("П:", "")
//                    .replaceAll("М:", "")
//                    .split(" "));
//
//            List<XWPFParagraph> pars = row.getCell(1).getParagraphs();
//
//            HashSet<String> set = new HashSet<>();

//            for (XWPFParagraph par : pars) {
//
//                if (par.getText().replaceAll(" ", "").equals("кого,чего?")
//                        || par.getText().replaceAll(" ", "").matches("кому,чему\\?")
//                        || par.getText().replaceAll(" ", "").matches("кого,что\\?")
//                        || par.getText().replaceAll(" ", "").matches("кем,чем\\?")
//                        || par.getText().replaceAll(" ", "").matches("оком,очём\\?")
//                        || par.getText().replaceAll(" ", "").matches("кто,что\\?")
//                        || par.getText().replaceAll(" ", "").matches("где\\?")
//                        || par.getText().replaceAll(" ", "").matches("И:")
//                        || par.getText().replaceAll(" ", "").matches("Р:")
//                        || par.getText().replaceAll(" ", "").matches("Д:")
//                        || par.getText().replaceAll(" ", "").matches("В:")
//                        || par.getText().replaceAll(" ", "").matches("Т:")
//                        || par.getText().replaceAll(" ", "").matches("П:")
//                        || par.getText().replaceAll(" ", "").matches("М:")
//                        || par.getText().replaceAll(" ", "").matches("М:")
//                        || par.getText().replaceAll(" ", "").matches("среднийрод")
//                        || par.getText().replaceAll(" ", "").matches("мужскойрод")
//                        || par.getText().replaceAll(" ", "").matches("множественноечисло")
//                ){
//                    continue;
//                }
//
//                List<String> elems = new ArrayList<>();
//
//
//                if (set.contains(par.getText().toLowerCase().replaceAll("о ", "").replaceAll("об ", "").replaceAll("в ", "").replaceAll(" ", ""))){
//                    set.add(row.getCell(0).getText().toLowerCase().replaceAll(" ", ""));
//                    continue;
//                }
//
////                if (set.contains(row.getCell(0).getText().toLowerCase().replaceAll(" ", ""))){
////                    set.add(row.getCell(0).getText().toLowerCase().replaceAll(" ", ""));
////                    continue;
////                }
//
//
////                set.add(par.getText().toLowerCase().replaceAll("о ", "").replaceAll("в ", "").replaceAll(" ", ""));
//
//
//                set.add(row.getCell(0).getText().toLowerCase().replaceAll(" ", ""));
//
//
//
//
//
//
//                if (par.getText().toLowerCase().replaceAll("о ", "").replaceAll("об ", "").replaceAll("в ", "").replaceAll(" ", "").equals("")){
//                    continue;
//                }
//
//
//                if (par.getText().toLowerCase().replaceAll("о ", "").replaceAll("об ", "").replaceAll("в ", "").replaceAll(" ", "").equals(row.getCell(0).getText().toLowerCase().replaceAll(" ", ""))){
//                    continue;
//                }
//
//                System.out.println("single_root('" + par.getText().toLowerCase().replaceAll("о ", "").replaceAll("об ", "").replaceAll("в ", "").replaceAll(" ", "") + "', '" + row.getCell(0).getText().toLowerCase().replaceAll(" ", "") + "').");
//            }
//
//        }





        for (XWPFTableRow row : table.getRows()) {

            String[] strings = row.getCell(1).getText().split(",");


            for (String string: strings) {


                if (string.replaceAll(" ", "").toLowerCase().equals(row.getCell(0).getText().toLowerCase().replaceAll(" ", ""))){
                    continue;
                }


                System.out.println("single_root('" + string.replaceAll(" ", "").toLowerCase() + "', '" + row.getCell(0).getText().toLowerCase().replaceAll(" ", "") + "').");
            }

        }



//        System.out.println(table.getRow(1).getCell(1).getText());

    }
}
