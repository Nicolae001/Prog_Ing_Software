package model;

public class ConsumoBevande extends ConsumoProcapite<Bevanda>{
	
	private static ConsumoBevande consumi=null;

	private ConsumoBevande() {
			super();
	}
	
	public static ConsumoBevande initConsumi() {
		if(consumi==null)
			consumi=new ConsumoBevande();
		return consumi;
	}
}
