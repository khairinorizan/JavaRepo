package cs228hw1.stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class StatisticsShell<T extends Number> implements Statistics<T>  {
	private ArrayList<StatObject<T>> dataObject;//
	private ArrayList<T> dataSet;
	private Method parser;
	private ParseStrToNumber<T> parseClass;
	
	public StatisticsShell(Method pp) {
		parser = pp;
		parseClass = new ParseStrToNumber<T>();
		
		try {
			parser = ParseStrToNumber.class.getMethod("parse", String.class);
			dataObject = new ArrayList<StatObject<T>>();
			dataSet = new ArrayList<T>();
			
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public boolean ReadFile(String path, DATA d) {
		// TODO Auto-generated method stub
		File file = new File(path);
		String header = null;
		StatObject<T> newStatObject = null;
		String dataName = d.name();
		
		try {
			Scanner sc = new Scanner(file);
			
			String firstLine = sc.nextLine();
			
			Scanner sc1 = new Scanner(firstLine);
			
			int headerIndex = 0;
			
			while(sc1.hasNext()) {
				header = sc1.next();
				
				headerIndex++;
				
				if(header.equals(dataName)) {
					break;
				}
				
			}
			
			int count = 0;
			String newLine = "";
			
			String value = "";
			
			while(sc.hasNextLine()) {
				newLine = sc.nextLine();
				
				sc1 = new Scanner(newLine);
				
				while(sc1.hasNext()) {
					if(count < (headerIndex-1)) {
						value = sc1.next();
						count++;
					}else if(count == (headerIndex-1)) {
						value = sc1.next();
						
						T parseValue = null;
						try {
							parseValue = (T) parser.invoke(parseClass, value);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						dataSet.add(parseValue);
						break;
					}
				}
				count = 0;
			}
			
			return true;
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return false;
		}
	
	}

	@Override
	public void AddStatObject(StatObject<T> so) {
		// TODO Auto-generated method stub
		so.SetData(dataSet);
		
		dataObject.add(so);
				
	}

	@Override
	public void AddStatObject(StatObject<T> so, int first, int last) {
		// TODO Auto-generated method stub
		ArrayList<T> tempData = new ArrayList<T>();
		StatObject<T> newSO;
		
		for(int i = first; i <= last; i++) {
			tempData.add(dataSet.get(i));
		}
		
		so.SetData(tempData);
		
		dataObject.add(so);
	}

	@Override
	public StatObject<T> GetStatObject(int i) {
		// TODO Auto-generated method stub
		return dataObject.get(i);
	}

	@Override
	public StatObject<T> RemoveStatObject(int i) {
		// TODO Auto-generated method stub
		StatObject<T> removedStatObject = dataObject.get(i);
		
		dataObject.remove(i);
		
		return removedStatObject;
	}

	@Override
	public int Count() {
		// TODO Auto-generated method stub
		return dataObject.size();
	}

	@Override
	public ArrayList<T> GetDataSet() {
		// TODO Auto-generated method stub
		return dataSet;
	}

	@Override
	public ArrayList<ArrayList<Number>> MapCar() {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Number>> result = new ArrayList<ArrayList<Number>>();
		ArrayList<Number> soResult = new ArrayList<Number>();
		
		for(int i =0; i < dataObject.size(); i++) {
			
			soResult.addAll(dataObject.get(i).GetResult());
			
		}
		
		result.add(soResult);
		
		return result;
	}

}
