package cs228hw1.stats;

import java.util.ArrayList;

public class Histogram<T extends Number> extends SharedMethod<T> {
	protected Number numberBins = 10; 
	protected Number MinRange = Double.NEGATIVE_INFINITY;
	protected Number MaxRange = Double.POSITIVE_INFINITY;
	
	public Histogram() {
		dataObject = new ArrayList<T>();
		desc = "Histogram";
	}
	
	public Histogram(ArrayList<T> newDataObject) {
		dataObject = new ArrayList<T>();
		desc = "Histogram";
		
		for(int i=0; i < newDataObject.size(); i++) {
			dataObject.add(newDataObject.get(i));
		}
	}
	
	@Override
	public ArrayList<Number> GetResult() throws RuntimeException {
		// TODO Auto-generated method stub
		int[] bins = new int[numberBins.intValue()];
		Number binSize = (MaxRange.intValue() - MinRange.intValue())/numberBins.intValue();
		ArrayList<Number> Histogram = new ArrayList<Number>();
		Number currentBin = MinRange.doubleValue() + binSize.doubleValue();
		ArrayList<Number> tempData = new ArrayList <Number>();
		int k = 0;// bin array index
			
		if(MinRange.doubleValue() > MaxRange.doubleValue() || dataObject == null) {
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
				
			for(int i = 0; i < tempData.size(); i++) {
				if(tempData.get(i).doubleValue() < MinRange.doubleValue() || tempData.get(i).doubleValue() > MaxRange.doubleValue()) {
					continue;
				}else if(tempData.get(i).doubleValue() < currentBin.doubleValue()) {
					bins[k]++;
				}else {
					while(tempData.get(i).doubleValue() >= currentBin.doubleValue()) {
						currentBin = currentBin.doubleValue() + binSize.doubleValue();
						k++;
					}
					
					if(currentBin.doubleValue() > MaxRange.doubleValue()) {
						currentBin = MaxRange.doubleValue();
						k--;
						bins[k]++;
					}else if(tempData.get(i).doubleValue() < currentBin.doubleValue()) {
						bins[k]++;
					}
						
				}
					
				k = 0;
				currentBin = MinRange.doubleValue() + binSize.doubleValue();
			}
	
				
			for(int j=0; j < bins.length; j++) {
				Histogram.add(bins[j]);
			}
				
			return Histogram;
		}
		
	
	}
	
	public void SetNumberBins(int newBins) {
		numberBins = newBins;
	}
	
	public Number GetNumberBins() {
		return numberBins;
	}
	
	public void SetMinRange(Number newMinRange) {
		MinRange = newMinRange;
	}
	
	public Number GetMinRange() {
		return MinRange;
	}
	
	public void SetMaxRange(Number newMaxRange) {
		MaxRange = newMaxRange;
	}
	
	public Number GetMaxRange() {
		return MaxRange;
	}
	
	

}
