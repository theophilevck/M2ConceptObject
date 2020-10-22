package entity.LivingBeing;

public class JangoFett extends Master{
	
	private static JangoFett jangoFett;

	private JangoFett() {
		super();
		this.image="/img/jangofett.png";
		this.name="jangoFett";
	}
	
	public static JangoFett getInstance() {
		if(jangoFett==null) {
			jangoFett=new JangoFett();
		}
		return jangoFett;
	}
}
