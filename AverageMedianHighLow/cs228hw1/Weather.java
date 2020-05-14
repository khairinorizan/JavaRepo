package cs228hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import cs228hw1.stats.Average;
import cs228hw1.stats.Histogram;
import cs228hw1.stats.Maximum;
import cs228hw1.stats.Median;
import cs228hw1.stats.Minimum;
import cs228hw1.stats.StatObject;
import cs228hw1.stats.StatisticsShell;
import cs228hw1.stats.Statistics.DATA;

public class Weather {
	public static <T extends Number> void main(String[] args)
	{
		Method method = null;
		String path = "testfile.txt";
		Boolean readFile = false;
		DATA data = DATA.TEMP;
		DATA[] enumData = DATA.values();
		StatisticsShell<T> ss = new StatisticsShell<T>(method);
		
		File newFile = new File(path);
		int count = 0;
		
		readFile = ss.ReadFile(path, data);
		
		if(readFile == true) {
			
			try {
				Scanner scanFile = new Scanner(newFile);
				
				String headers = scanFile.nextLine();
				
				ArrayList<String> addMonth = new ArrayList<String>();
				ArrayList<String> addDay = new ArrayList<String>();

				
				String value = "";
				String month = "";
				String day = "";
				
				while(scanFile.hasNextLine()) {
					String dataValues = scanFile.nextLine();
					
					Scanner scanValue = new Scanner(dataValues);
					
					
					while(scanValue.hasNext()) {
						if(count < enumData[2].ordinal()) {
							value = scanValue.next();
							count++;
						}else if(count == enumData[2].ordinal()) {
							
							
							value = scanValue.next();
							
							month = value.substring(4, 6);
							day = value.substring(6, 8);
							
							addMonth.add(month);
							addDay.add(day);
							
							
							break;
							
						}
						
					}
					count = 0;
				}
				
				int i = 0;
				int j = 0;
				
				int starts = 0;
				int ends = 0;
				
				int dayStarts = 0;
				int dayEnds = 0;
				
				while(i < addMonth.size()-1) {
					if(addMonth.get(i).equals(addMonth.get(i+1))) {
						ends++;
					}else { 
						ss.AddStatObject(new Minimum<>(), starts, ends);
						ss.AddStatObject(new Maximum<>(), starts, ends);
						starts = ends + 1;
						ends++;
					}
					i++;
				}
				ss.AddStatObject(new Minimum<>(), starts, ends);
				ss.AddStatObject(new Maximum<>(), starts, ends);
				
				
				while(j < addDay.size()-1) {
					if(addDay.get(j).equals(addDay.get(j+1))) {
						dayEnds++;
					}else {
						ss.AddStatObject(new Average<>(), dayStarts, dayEnds);
						ss.AddStatObject(new Median<>(), dayStarts, dayEnds);
						
						ss.AddStatObject(new Maximum<>(), dayStarts, dayEnds);
						
						Histogram<T> so = new Histogram<>(ss.GetStatObject(ss.Count()-1).GetData());
						so.SetMinRange(-40);
						so.SetMaxRange(110);
						so.SetNumberBins(15);
						
						ss.AddStatObject(so);
						ss.RemoveStatObject(ss.Count()-2);
						
						dayStarts = dayEnds + 1;
						dayEnds++;
					}
					j++;
				}
				
				ss.AddStatObject(new Average<>(), dayStarts, dayEnds);
				ss.AddStatObject(new Median<>(), dayStarts, dayEnds);
				
				ss.AddStatObject(new Maximum<>(), dayStarts, dayEnds);
				
				Histogram<T> so = new Histogram<>(ss.GetStatObject(ss.Count()-1).GetData());
				so.SetMinRange(-40);
				so.SetMaxRange(110);
				so.SetNumberBins(15);
				
				ss.AddStatObject(so);
				ss.RemoveStatObject(ss.Count()-2);
				
				ArrayList<ArrayList<Number>> theResults = new ArrayList<ArrayList<Number>>();
				ArrayList<Number> result = new ArrayList<Number>();
				theResults = ss.MapCar();
				
				
				for(int k = 0; k < theResults.size(); k++) {
					result = theResults.get(k);
					
					for(int m = 0; m < result.size(); m++) {
						System.out.println(result.get(m));
					}
					
				}
				
		
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
