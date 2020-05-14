package cs228hw1.stats;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import cs228hw1.stats.Statistics.DATA;


public class testClass {
	public static <T extends Number> void main(String[] args) throws FileNotFoundException{
		
		
		
		ArrayList<Number> al = new ArrayList<Number>();
		ArrayList<Number> result = new ArrayList<Number>();
		al.add(-10);
		al.add(0);
		al.add(-7);
		al.add(40);
		al.add(41);
		al.add(31);
		al.add(21);
		al.add(20);
		al.add(10);	
		al.add(-12);
		al.add(30);
		al.add(30);
		al.add(40);
		Histogram<Number> test = new Histogram<Number>();
		
		test.SetMinRange(-10);
		test.SetMaxRange(40);
		test.SetNumberBins(5);
		test.GetResult();
		
		for(int i = 0; i < test.GetResult().size(); i++) {
			result.add(test.GetResult().get(i));
		}
		
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		
//		ArrayList<Number> al = new ArrayList<Number>();
//		
//
//		al.add(9);
//		al.add(2);
//		al.add(5);
//		al.add(4);
//		al.add(12);
//		al.add(7);
//		al.add(8);
//		al.add(11);
//		al.add(9);
//		al.add(3);
//		al.add(7);
//		al.add(4);
//		al.add(12);
//		al.add(5);
//		al.add(4);
//		al.add(10);
//		al.add(9);
//		al.add(6);
//		al.add(9);
//		al.add(4);
//		
//		StdDeviation<Number> test = new StdDeviation<Number>(al);
//		
//		System.out.println(test.GetResult().get(0));
		
		
		
		
//		Class parseClass = ParseStrToNumber.class;
//		//Class tetString = String.class;
//		Method method = null;
//		//Method[] method = parseClass.getMethods();
//		
//		try {
//			method = parseClass.getMethod("parse", String.class);
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String pathName = "testfile.txt";
//		DATA data = DATA.TEMP;
//		
//		
//		StatisticsShell<T> ss = new StatisticsShell<T>(method);
//		
//		ss.ReadFile(pathName, data);
//		ParseStrToNumber<T> testParse = new ParseStrToNumber<T>();
//		
//		System.out.println(testParse.parse("f"));
		
//		ArrayList<Number> histogramResult = new ArrayList<Number>();
//		ArrayList<T> test = new ArrayList<T>();
//		test.add((T)new Double(9));
//		test.add((T)new Double(12));
//		test.add((T)new Double(14));
//		test.add((T)new Double(18));
//		test.add((T)new Double(23));
//		
//		Histogram<T> histogram = new Histogram<T>(test);
//		
//		histogram.SetNumberBins(10);
//		histogram.setMinRange(10);
//		histogram.setMaxRange(40);
//		
//		histogramResult = histogram.GetResult();
//		
//		for(int i = 0; i < histogramResult.size(); i++) {
//			System.out.println(histogramResult.get(i));
//		}
		
		
//		SharedMethod<Number> test2 = new StdDeviation<Number>(test);
//		ArrayList<Number> result = new ArrayList<Number>();
//		
//		
//		test2.SetDescription("Average Test");
//		System.out.println(test2.GetDescription());
//		
//		ArrayList<Number> testGet = test2.GetData();
//		
//		for(int i = 0; i < testGet.size(); i++) {
//			System.out.println(testGet.get(i));
//		}
//		
//		test2.listSort(testGet);
//		
//		for(int i=0; i < testGet.size(); i++) {
//			System.out.println(testGet.get(i));
//		}
//		
//		result = test2.GetResult();
//		
//		for(int i=0; i < result.size(); i++) {
//			System.out.println(result.get(i));
//		}
		
		
	}
}
