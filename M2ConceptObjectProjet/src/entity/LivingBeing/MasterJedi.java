package entity.LivingBeing;

import entity.Map;

public class MasterJedi extends Master{
	
	private static MasterJedi yoda ;
	
	private MasterJedi(Map map) {
		super(map);
		this.image="/img/yoda.png";
		this.name="yoda";
	}
	
	public static MasterJedi getInstance() {
		if(yoda==null) {
			yoda=new MasterJedi(null);
		}
		return yoda;
	}
}
