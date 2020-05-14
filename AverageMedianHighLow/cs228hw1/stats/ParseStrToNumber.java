package cs228hw1.stats;

public class ParseStrToNumber<T extends Number> implements IParser<T> {
	
	public ParseStrToNumber() {
		
	} 

	@Override
	public T parse(String str) {
		try {
			Number numStr = Double.valueOf(str);
			//Number numStr = new Double(str);
			return (T)numStr;
		}catch(NumberFormatException e) {
			return null;
		}
		// TODO Auto-generated method stub

		
		//statisticShell = new StatisticsShell<>((new DoubleParser<Number>().getClass().getMethod("parse", String.class)))
	}

}
