package entity.LivingBeing;

import entity.Map;

public class Sergent extends Master{
	
	private static Sergent darth_vader ;

	private Sergent(Map map) {
		super(map);
		this.image="/img/darkvador.png";
		this.name="darth_vader";
	}
	
	public static Sergent getInstance() {
		if(darth_vader==null) {
			darth_vader=new Sergent(null);
		}
		return darth_vader;
	}
}
