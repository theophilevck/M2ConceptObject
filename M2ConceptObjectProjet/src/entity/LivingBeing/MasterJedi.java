package entity.LivingBeing;

public class MasterJedi extends Master{
	
	private static MasterJedi yoda ;
	
	private MasterJedi() {
		super();
		this.image="/img/yoda.png";
	}
	
	public static MasterJedi getInstance() {
		if(yoda==null) {
			yoda=new MasterJedi();
		}
		return yoda;
	}
}
