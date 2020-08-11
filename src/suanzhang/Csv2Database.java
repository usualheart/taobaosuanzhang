package suanzhang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

//这种第三方包暂时的问题无法解决 190402!
public class Csv2Database {
	public static  Reader getReader(String relativePath) {
        
        try {
			return new InputStreamReader(Csv2Database.class.getResourceAsStream(relativePath), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        return null;
    }
	public static void main(String[] args) {
		
		File file = new File("D:/java-test/csv/csv.csv");
        InputStream in=null;
        InputStreamReader reader=null;
		try {
			in = new FileInputStream(file);
	        reader = new InputStreamReader(in, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		
		// TODO 自动生成的方法存根
		CsvParserSettings settings = new CsvParserSettings();
		//the file used in the example uses '\n' as the line separator sequence.
		//the line separator sequence is defined here to ensure systems such as MacOS and Windows
		//are able to process this file correctly (MacOS uses '\r'; and Windows uses '\r\n').
		settings.getFormat().setLineSeparator("\n");

		// creates a CSV parser
		CsvParser parser = new CsvParser(settings);
		// parses all rows in one go.
		//Reader rd=getReader();
		List<String[]> allRows = parser.parseAll(reader);
		
		for(String[] tmp:allRows) {
			for(String x:tmp) {
				System.out.print(x+" ");
			}
			System.out.println();
		}
		
	}

}
