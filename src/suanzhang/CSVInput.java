package suanzhang;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class CSVInput {
	

	
	/**
	 * Sets up the processors used for the examples. There are 10 CSV columns, so 10 processors are defined. Empty
	 * columns are read as null (hence the NotNull() for mandatory columns).
	 * 
	 * @return the cell processors
	 */
	private static CellProcessor[] getProcessors() {
	        
	        final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+"; // just an example, not very robust!
	        StrRegEx.registerMessage(emailRegex, "must be a valid email address");
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	               // new UniqueHashCode(), // customerNo (must be unique)
	        		//new LMinMax(0L, LMinMax.MAX_LONG), // loyaltyPoints
	        		new NotNull(), // 如何解决订单编号的问题？
	                new NotNull(), //
	               // new ParseDate("dd/MM/yyyy"), // birthDate
	                new NotNull(), // mailingAddress
	                //new Optional(new ParseBool()), // married
	                //new Optional(new ParseInt()), // numberOfKids
	                new NotNull(), // favouriteQuote
	                //new StrRegEx(emailRegex), // email
	                //new LMinMax(0L, LMinMax.MAX_LONG), // loyaltyPoints
	                new NotNull(), 
	                new NotNull(), 
	                new NotNull(), 
	                new NotNull(), 
	                new NotNull(), 
	                new NotNull(), 
	        };
	        
	        return processors;
	}
	/**
	 * An example of reading using CsvListReader.
	 */
	//filePath指定了csv的文件地址
	static List readWithCsvListReader(String filePath) throws Exception {
	        
	
	        //用于存储结果的
	        List <List> allList=new ArrayList<List>();
		
	        ICsvListReader listReader = null;
	        try {	//Csv文件的路径
	                listReader = new CsvListReader(new FileReader(filePath), CsvPreference.STANDARD_PREFERENCE);
	                listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	                
	                final CellProcessor[] processors = getProcessors();
	                
	                List<Object> customerList;
	                while( (customerList = listReader.read(processors)) != null ) {
                       // System.out.println(String.format("lineNo=%s, rowNo=%s, customerList=%s",
                        		//listReader.getLineNumber(), listReader.getRowNumber(), customerList));
                        allList.add(customerList);
	                }
	                
	        }
	        finally {
	                if( listReader != null ) {
	                        listReader.close();
	                }
	        }
			return allList;
	}
	
	
	//遍历输出List的值
	public static void show(List<List> allList) {
		for(List tmp:allList) {
			for(Object x:tmp) {
				String y=(String) x;
				System.out.print(y+" ");
			}
			System.out.println();
		}
	}
	
	
	

}
