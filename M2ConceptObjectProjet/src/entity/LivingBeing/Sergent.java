package entity.LivingBeing;

public class Sergent extends Master{
	
	private static Sergent darth_vader ;

	private Sergent() {
		super();
		this.image="/img/darkvador.png";
		this.name="darth_vader";
	}
	
	public static Sergent getInstance() {
		if(darth_vader==null) {
			darth_vader=new Sergent();
		}
		return darth_vader;
	}
}
