package cs228hw1.stats;

import java.util.ArrayList;

public class StdDeviation<T extends Number> extends SharedMethod<T> {
	
	public StdDeviation() {
		
		dataObject = new ArrayList<T>();
		desc = "Std Deviation";
	}
	
	public StdDeviation(ArrayList<T> newDataObject) {
		
		dataObject = new ArrayList<T>();
		desc = "Std Deviation";
		
		for(int i=0; i < newDataObject.size(); i++) {
			dataObject.add(newDataObject.get(i));
		}
	}

	@Override
	public ArrayList<Number> GetResult() throws RuntimeException {
		// TODO Auto-generated method stub
		ArrayList<Number> stdDeviation = new ArrayList <Number>();
		ArrayList<Number> tempData = new ArrayList <Number>();
		Number meanValue = 0;
		
		if(dataObject == null || dataObject.size() == 0) {
			throw new RuntimeException();
		}else {

			for(int i=0; i < dataObject.size(); i++) {
				if(dataObject.get(i) == null) {
					continue;
				}else {
					tempData.add((Number)dataObject.get(i));
					
				}
				
			}
			
			//sums all the number in the arrayList
			for(int i=0; i < tempData.size(); i++) {
				meanValue = meanValue.doubleValue() + tempData.get(i).doubleValue();
			}
			
			meanValue = meanValue.doubleValue()/dataObject.size();
			
			//subtracting each number with the mean score
			for(int i=0; i < tempData.size(); i++) {
				Number minusMeanValue = tempData.get(i).doubleValue() - meanValue.doubleValue();
				
				if(minusMeanValue.doubleValue() < 0) {
					minusMeanValue = Math.abs(Math.pow(minusMeanValue.doubleValue(), 2));
				}else {
					minusMeanValue = Math.pow(minusMeanValue.doubleValue(), 2);
				}
				
				tempData.set(i, minusMeanValue);
			}
			
			Number squareDeviationSum = 0;
			
			for(int i=0; i < tempData.size(); i++) {
				squareDeviationSum = squareDeviationSum.doubleValue() + tempData.get(i).doubleValue();
			}
			
			squareDeviationSum = squareDeviationSum.doubleValue()/(dataObject.size() - 1);
			
			squareDeviationSum = Math.sqrt(squareDeviationSum.doubleValue());
			
			stdDeviation.add(squareDeviationSum);
			
			
			return stdDeviation;	
		}
		
	}

}
