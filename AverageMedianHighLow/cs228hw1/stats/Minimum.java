package cs228hw1.stats;

import java.util.ArrayList;

public class Minimum<T extends Number> extends SharedMethod<T> {
	
	public Minimum() {
		dataObject = new ArrayList<T>();
		desc = "";
	}
	
	public Minimum(ArrayList<T> newDataObject) {
		dataObject = new ArrayList<T>();
		
		for(int i=0; i < newDataObject.size(); i++) {
			dataObject.add(newDataObject.get(i));
		}
	}

	@Override
	public ArrayList<Number> GetResult() throws RuntimeException {
		// TODO Auto-generated method stub
		ArrayList<Number> min = new ArrayList <Number>();
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
			
			listSort(tempData);
			
			min.add(tempData.get(0)); 
			
			return min;
		}
		
	}

}
