package entity.LivingBeing;

import entity.Map;

public class JangoFett extends Master{
	
	private static JangoFett jangoFett;

	private JangoFett(Map map) {
		super(null);
		this.image="/img/jangofett.png";
		this.name="jangoFett";
	}
	
	public static JangoFett getInstance() {
		if(jangoFett==null) {
			jangoFett=new JangoFett(null);
		}
		return jangoFett;
	}
}
