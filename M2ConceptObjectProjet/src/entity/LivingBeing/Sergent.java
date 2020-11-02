package entity.LivingBeing;

import entity.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Sergent.
 */
public class Sergent extends Master{
	
	/** The darth vader. */
	private static Sergent darth_vader ;

	/**
	 * Instantiates a new sergent.
	 *
	 * @param map the map
	 */
	private Sergent(Map map) {
		super(map);
		this.image="/img/darkvador.png";
		this.name="darth_vader";
	}
	
	/**
	 * Gets the single instance of Sergent.
	 *
	 * @return single instance of Sergent
	 */
	public static Sergent getInstance() {
		if(darth_vader==null) {
			darth_vader=new Sergent(null);
		}
		return darth_vader;
	}
}
