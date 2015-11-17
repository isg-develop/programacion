/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uth.cine.view.swing;


import javax.swing.JTable;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jxl.*;
import jxl.write.*;
import jxl.write.Number;

/**
 *
 * @author DEVELOPER
 */
public class TableDataToExcel {

    JTable table;

    public TableDataToExcel(JTable table) {
        this.table = table;
    }

    public void ExportToExcel() throws Exception {

        String path = "#";
        path = PathDirectory.getSaveFilePath("Libro de Excel 97-2003 (*.xls)", "xls");
        if (path==null){
        return;
        }
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(path)); //(new File("c:/mio1.xls"));
            int row = 0;
            int col = 0;
            WritableSheet sheet = null;
            sheet = workbook.createSheet("Vacio", 0);
            int countRowSheet = workbook.getNumberOfSheets();
            int thoja = 65500;
            Integer rowss = table.getRowCount();
            Double t = rowss / new Double(thoja);
            int temp = rowss / thoja;
            if ((t - temp) > 0) {
                temp++;
            }
            row = 0;

            for (Integer numSheet = 0; numSheet < temp; numSheet++) {
                sheet = workbook.createSheet("Datos hoja " + (1 + numSheet), numSheet);
                for (int i = 0; i < table.getColumnCount(); i++) {
//                Integer tam = this.table.getColumnModel().getColumn(i).getWidth(); Ancho de columna
                    String columName = table.getColumnName(i);
                    //                 columnas, filas , dato
                    Label label = new Label(i + 1, 1, columName);
                    sheet.addCell(label);
                }

                for (Integer rowCell = 0; rowCell < thoja; rowCell++) {
                    int l = numSheet * thoja;
                    int posActual = l + rowCell;
                    if (posActual < rowss) {
                        for (int c = 0; c < table.getColumnCount(); c++) {
                            col = c + 1;
                            row = rowCell + 2;
                            Integer r = posActual;
                            if (table.getValueAt(r, c) == null) {
                                sheet.addCell(new Label(col, row, ""));

                            } else if (table.getValueAt(r, c).getClass().getSimpleName().equals("Integer")) {
                                sheet.addCell(new Number(col, row, (Integer) table.getValueAt(r, c)));

                            } else if (table.getValueAt(r, c).getClass().getSimpleName().equals("Double")) {
                                sheet.addCell(new Number(col, row, (Double) table.getValueAt(r, c)));

                            } else if (table.getValueAt(r, c).getClass().getSimpleName().equals("BigDecimal")) {
                                sheet.addCell(new Number(col, row, ((BigDecimal) table.getValueAt(r, c)).doubleValue()));

                            } else if (table.getValueAt(r, c).getClass().getSimpleName().equals("String")) {
                                sheet.addCell(new Label(col, row, (String) table.getValueAt(r, c)));

                            } else {
                                sheet.addCell(new Label(col, row, ((Object) table.getValueAt(r, c)).toString()));
                            }
                        }
                    }
                }
            }

            workbook.write();
            workbook.close();

            try {
                //Runtime.getRuntime().exec("C:/Conversiones.xls");
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);//"C:/n1.xls");

            } catch (Exception e) {
                throw new Exception("Error al abrir al intentar abrir: " + path);
            }

            JOptionPane.showMessageDialog(null, "Se genero el sig. archivo: " + path);

        } catch (WriteException | IOException ex) {
            Logger.getLogger(TableDataToExcel.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }
}
