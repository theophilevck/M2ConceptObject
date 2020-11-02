package entity.LivingBeing;

import entity.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class JangoFett.
 */
public class JangoFett extends Master{
	
	/** The jango fett. */
	private static JangoFett jangoFett;

	/**
	 * Instantiates a new jango fett.
	 *
	 * @param map the map
	 */
	private JangoFett(Map map) {
		super(null);
		this.image="/img/jangofett.png";
		this.name="jangoFett";
	}
	
	/**
	 * Gets the single instance of JangoFett.
	 *
	 * @return single instance of JangoFett
	 */
	public static JangoFett getInstance() {
		if(jangoFett==null) {
			jangoFett=new JangoFett(null);
		}
		return jangoFett;
	}
}
