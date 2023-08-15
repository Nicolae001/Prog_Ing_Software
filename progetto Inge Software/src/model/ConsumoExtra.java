package model;

public class ConsumoExtra extends ConsumoProcapite<GenereExtra>{
	
	private static ConsumoExtra consumi=null;

	private ConsumoExtra() {
			super();
	}
	
	public static ConsumoExtra initConsumi() {
		if(consumi==null)
			consumi=new ConsumoExtra();
		return consumi;
	}
}
