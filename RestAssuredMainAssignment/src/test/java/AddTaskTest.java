import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
@Listeners(ExtentReport.class)
public class AddTaskTest extends Base {
    @Test(priority = 2)
    public void AddTask() throws IOException {
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com/task";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        FileInputStream inputStream = new FileInputStream("C:\\HUAPI\\RestAssuredMainAssignment\\src\\test\\data\\Add20Task.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("task20");
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();
        String description = null;
        String task = null;
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j == 0) {
                    description = sheet.getRow(i).getCell(j).getStringCellValue();
                }try {
                    if (j == 1) {
                        task = sheet.getRow(i).getCell(j).getStringCellValue();
                    }
                }catch (Exception e){
                    System.out.println("Invalid Task is entered");
                    continue;
                }

            }
            String addTaskJson = "{\n" +
                    "\t\""+description+"\": \""+task+"\"\n" +
                    "}";
            Response responseaddTask = request.body(addTaskJson).post();
            String jsonString1 = responseaddTask.getBody().asString();
            String task1 = JsonPath.from(jsonString1).get("data.description");
            System.out.println(task1);
            try{
                Assert.assertEquals(task,task1);
            }
            catch (Exception e){
                System.out.println("Task description match!");
            }
            responseaddTask.prettyPrint();
            wb.close();
            inputStream.close();
        }
        log.info("Tasks Added Successfully");
    }
}
