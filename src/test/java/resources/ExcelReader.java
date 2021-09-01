package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public ArrayList<String> excelReader(String tcName) throws IOException
	{
		ArrayList<String> ar = new ArrayList<>();
		FileInputStream fis = new FileInputStream("D://EclipseWorkspace//BookBook.xlsx");
		XSSFWorkbook xs = new XSSFWorkbook(fis);
		int sheets = xs.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if(xs.getSheetName(i).equalsIgnoreCase("testdata"))
			{
				XSSFSheet sh = xs.getSheetAt(i);
				Iterator<Row> rows=sh.iterator();
				Row first=rows.next();
				Iterator<Cell> cells = first.cellIterator();
				int k=0;
				int col=0;
				while(cells.hasNext())
				{
					Cell value=cells.next();
					if(value.getStringCellValue().equalsIgnoreCase("Testcases"))
					{
						col=k;
						
					}
					k++;
				}
				while(rows.hasNext())
				{
					Row r=rows.next();
					if(r.getCell(col).getStringCellValue().equalsIgnoreCase(tcName))
					{
						Iterator<Cell> cell=r.iterator();
						while(cell.hasNext())
						{
							ar.add(cell.next().getStringCellValue());
						}
					}
				}
			}
		}
		xs.close();
		return ar;			
	}

	public static void main(String[] args) throws IOException {
		ExcelReader er = new ExcelReader();
		ArrayList<String> res= er.excelReader("create account");
		System.out.println(res.get(2));

	}

}
