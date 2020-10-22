package entity.LivingBeing;

public class Chewbacca extends Master{

	private static Chewbacca chewbacca;
	
	private Chewbacca() {
		super();
		this.image="/img/chewbi.png";
		this.name="chewbacca";
	}
	
	public static Chewbacca getInstance() {
		if(chewbacca==null) {
			chewbacca=new Chewbacca();
		}
		return chewbacca;
	}
}
