package entity.LivingBeing;

import entity.Map;

public class Chewbacca extends Master{

	private static Chewbacca chewbacca;
	
	private Chewbacca(Map map) {
		super(null);
		this.image="/img/chewbi.png";
		this.name="chewbacca";
	}
	
	public static Chewbacca getInstance() {
		if(chewbacca==null) {
			chewbacca=new Chewbacca(null);
		}
		return chewbacca;
	}
}
