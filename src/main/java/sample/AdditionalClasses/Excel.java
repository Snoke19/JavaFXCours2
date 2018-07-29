package sample.AdditionalClasses;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.model.Employee;
import sample.model.Enterprise;
import sample.model.Technique;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Excel{

    private void saveExcelFile(XSSFWorkbook workbook){
        try {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(stage);

            if (file != null){
                FileOutputStream out = new FileOutputStream(file);
                workbook.write(out);
                out.close();
            }

            System.out.println(file + " файл створено");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void importExcelTechnique(ObservableList dataTechnique,
                                     TableColumn<Technique, Integer> idTech,
                                     TableColumn<Technique, String> type,
                                     TableColumn<Technique, String> nameTech,
                                     TableColumn<Technique, Integer> inventoryNumbers,
                                     TableColumn<Technique, Float> startCost,
                                     TableColumn<Technique, Float> actualCost){
        Excel excel = new Excel();

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet("Техніка");

        XSSFRow rowForInformation = spreadsheet.createRow(0);
        XSSFCell cellForInformation = rowForInformation.createCell(0);
        cellForInformation.setCellValue("Номер");

        cellForInformation = rowForInformation.createCell(1);
        cellForInformation.setCellValue("Тип");

        cellForInformation = rowForInformation.createCell(2);
        cellForInformation.setCellValue("Назва");

        cellForInformation = rowForInformation.createCell(3);
        cellForInformation.setCellValue("Інвентаризаційний номер");

        cellForInformation = rowForInformation.createCell(4);
        cellForInformation.setCellValue("Початкова ціна");

        cellForInformation = rowForInformation.createCell(5);
        cellForInformation.setCellValue("Актуальна ціна");

        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cellForInformation = rowForInformation.createCell(7);
        cellForInformation.setCellValue(new Date());
        cellForInformation.setCellStyle(cellStyle);

        for (int i = 0; i < dataTechnique.size(); i++) {
            XSSFRow rowForDataFromTable = spreadsheet.createRow(i + 1);
            XSSFCell cellForDataInTable;

            cellForDataInTable = rowForDataFromTable.createCell(0);
            cellForDataInTable.setCellValue(idTech.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(1);
            cellForDataInTable.setCellValue(type.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(2);
            cellForDataInTable.setCellValue(nameTech.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(3);
            cellForDataInTable.setCellValue(inventoryNumbers.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(4);
            cellForDataInTable.setCellValue(startCost.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(5);
            cellForDataInTable.setCellValue(actualCost.getCellData(i));
        }

        excel.saveExcelFile(workbook);
    }

    public void importExcelEnterprise(ObservableList dataEnterprise,
                                      TableColumn<Enterprise, Integer> idEnterprise,
                                      TableColumn<Enterprise, String> nameEnterprise,
                                      TableColumn<Enterprise, String> city,
                                      TableColumn<Enterprise, String> address,
                                      TableColumn<Enterprise, String> allNameDirector,
                                      TableColumn<Enterprise, String> allNameAccountant){
        Excel excel = new Excel();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Enterprise");

        XSSFRow rowForInformation = spreadsheet.createRow(0);
        XSSFCell cellForInformation = rowForInformation.createCell(0);
        cellForInformation.setCellValue("Номер");

        cellForInformation = rowForInformation.createCell(1);
        cellForInformation.setCellValue("Назва");

        cellForInformation = rowForInformation.createCell(2);
        cellForInformation.setCellValue("Місто");

        cellForInformation = rowForInformation.createCell(3);
        cellForInformation.setCellValue("Адресса");

        cellForInformation = rowForInformation.createCell(4);
        cellForInformation.setCellValue("Директор");

        cellForInformation = rowForInformation.createCell(5);
        cellForInformation.setCellValue("Бухгалтер");

        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cellForInformation = rowForInformation.createCell(7);
        cellForInformation.setCellValue(new Date());
        cellForInformation.setCellStyle(cellStyle);

        for (int i = 0; i < dataEnterprise.size(); i++) {
            XSSFRow rowForDataFromTable = spreadsheet.createRow(i + 1);
            XSSFCell cellForDataInTable;

            cellForDataInTable = rowForDataFromTable.createCell(0);
            cellForDataInTable.setCellValue(idEnterprise.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(1);
            cellForDataInTable.setCellValue(nameEnterprise.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(2);
            cellForDataInTable.setCellValue(city.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(3);
            cellForDataInTable.setCellValue(address.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(4);
            cellForDataInTable.setCellValue(allNameDirector.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(5);
            cellForDataInTable.setCellValue(allNameAccountant.getCellData(i));
        }

        excel.saveExcelFile(workbook);
    }

    public void importExcelEmployee(ObservableList dataEmployee,
                                    TableColumn<Employee, Integer> idEmployee,
                                    TableColumn<Employee, String> allNameEmployee,
                                    TableColumn<Employee, String> postEmployee,
                                    TableColumn<Employee, String> liabilityEmployee,
                                    TableColumn<Employee, Integer> numberPhoneEmployee,
                                    TableColumn<Employee, Object> enterpriseEmployee,
                                    TableColumn<Employee, Object> techniqueEmployee){
        Excel excel = new Excel();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Employee");

        XSSFRow rowForInformation = spreadsheet.createRow(0);
        XSSFCell cellForInformation = rowForInformation.createCell(0);
        cellForInformation.setCellValue("Номер");

        cellForInformation = rowForInformation.createCell(1);
        cellForInformation.setCellValue("ПІБ Працівника");

        cellForInformation = rowForInformation.createCell(2);
        cellForInformation.setCellValue("Посада");

        cellForInformation = rowForInformation.createCell(3);
        cellForInformation.setCellValue("Матеріальна відповідальність");

        cellForInformation = rowForInformation.createCell(4);
        cellForInformation.setCellValue("Номер телефона");

        cellForInformation = rowForInformation.createCell(5);
        cellForInformation.setCellValue("Компанія");

        cellForInformation = rowForInformation.createCell(6);
        cellForInformation.setCellValue("Техніка");

        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cellForInformation = rowForInformation.createCell(7);
        cellForInformation.setCellValue("Дата створення документа");
        cellForInformation.setCellValue(new Date());
        cellForInformation.setCellStyle(cellStyle);

        for (int i = 0; i < dataEmployee.size(); i++) {
            XSSFRow rowForDataFromTable = spreadsheet.createRow(i + 1);
            XSSFCell cellForDataInTable;

            cellForDataInTable = rowForDataFromTable.createCell(0);
            cellForDataInTable.setCellValue(idEmployee.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(1);
            cellForDataInTable.setCellValue(allNameEmployee.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(2);
            cellForDataInTable.setCellValue(postEmployee.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(3);
            cellForDataInTable.setCellValue(liabilityEmployee.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(4);
            cellForDataInTable.setCellValue(numberPhoneEmployee.getCellData(i));

            cellForDataInTable = rowForDataFromTable.createCell(5);
            cellForDataInTable.setCellValue(String.valueOf(enterpriseEmployee.getCellData(i)));

            cellForDataInTable = rowForDataFromTable.createCell(6);
            cellForDataInTable.setCellValue(String.valueOf(techniqueEmployee.getCellData(i)));
        }

        excel.saveExcelFile(workbook);
    }
}
