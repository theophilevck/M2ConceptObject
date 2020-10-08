package entity.LivingBeing;

public class MasterJedi extends Master{
	
	private static MasterJedi yoda = new MasterJedi();
	
	public MasterJedi() {
		super();
		this.image="/img/yoda.png";
	}
}
