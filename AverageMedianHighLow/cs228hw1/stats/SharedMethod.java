package cs228hw1.stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class SharedMethod<T extends Number> implements StatObject<T>{
	protected String desc;
	protected ArrayList<T> dataObject;

	public void SetDescription(String d) {
		desc = d;
	}
	
	public String GetDescription() {
		return desc;
	}
	
	public abstract ArrayList<Number> GetResult() throws RuntimeException;
	
	public void SetData(ArrayList<T> data) {
		
		for(int i = 0; i < data.size(); i++) {
			dataObject.add(data.get(i));
		}
		
	}
	
	public ArrayList<T> GetData() {
		// TODO Auto-generated method stub
		return dataObject;
	}
	
	
	public class sortAsc implements Comparator<Number>{

		@Override
		public int compare(Number o1, Number o2) {
			// TODO Auto-generated method stub
			if(o1.doubleValue() < o2.doubleValue()) {
				return -1;
			}else if(o1.doubleValue() > o2.doubleValue()) {
				return 1;
			}else {
				return 0;
			}
			
		}
		
	}
	
	public void listSort(ArrayList<Number> al) {
		sortAsc numComp = new sortAsc();
		
		Collections.sort(al, numComp);
		
	}

}
