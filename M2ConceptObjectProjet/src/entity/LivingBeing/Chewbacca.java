package entity.LivingBeing;

public class Chewbacca extends Master{

	private static Chewbacca chewbacca;
	
	private Chewbacca() {
		super();
		this.image="/img/chewbi.png";
	}
	
	public static Chewbacca getInstance() {
		if(chewbacca==null) {
			chewbacca=new Chewbacca();
		}
		return chewbacca;
	}
}
