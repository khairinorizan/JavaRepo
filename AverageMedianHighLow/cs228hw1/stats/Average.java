package cs228hw1.stats;

import java.util.ArrayList;

public class Average<T extends Number> extends SharedMethod<T> {
	
	public Average() {
		dataObject = new ArrayList<T>();
		desc = "Average";
	}
	
	public Average(ArrayList<T> newDataObject) {
		//dataObject = newDataObject;
		dataObject = new ArrayList<T>();
		desc = "Average";
		
		for(int i=0; i < newDataObject.size(); i++) {
			dataObject.add(newDataObject.get(i));
		}
	}

	@Override
	public ArrayList<Number> GetResult() throws RuntimeException {
		// TODO Auto-generated method stub
		ArrayList<Number> average = new ArrayList<Number>();
		Number temp = 0.0;
		ArrayList<Number> tempData = new ArrayList <Number>();
		
		
		if(dataObject.size() == 0 || dataObject == null) {
			throw new RuntimeException();
		}else {
			
			for(int i=0; i < dataObject.size(); i++) {
				if(dataObject.get(i) == null) {
					continue;
				}else {
					
					tempData.add((Number)dataObject.get(i));
				}
				
			}
			
			for(int i = 0; i < tempData.size(); i++) {
				temp = temp.doubleValue() + tempData.get(i).doubleValue();
			}
			
			temp = temp.doubleValue()/dataObject.size();//
			
			average.add(temp);
		}
		
		return average;
	}
	
	

}
