package model;

public class ConsumoBevande extends ConsumoProcapite<Bevanda>{
	
	private static ConsumoBevande consumi=null;

	private ConsumoBevande() {
			super();
	}
	
	public static ConsumoBevande getConsumi() {
		if(consumi==null)
			consumi=new ConsumoBevande();
		return consumi;
	}
}
