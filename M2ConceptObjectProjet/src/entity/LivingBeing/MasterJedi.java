package entity.LivingBeing;

import entity.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class MasterJedi.
 */
public class MasterJedi extends Master{
	
	/** The yoda. */
	private static MasterJedi yoda ;
	
	/**
	 * Instantiates a new master jedi.
	 *
	 * @param map the map
	 */
	private MasterJedi(Map map) {
		super(map);
		this.image="/img/yoda.png";
		this.name="yoda";
	}
	
	/**
	 * Gets the single instance of MasterJedi.
	 *
	 * @return single instance of MasterJedi
	 */
	public static MasterJedi getInstance() {
		if(yoda==null) {
			yoda=new MasterJedi(null);
		}
		return yoda;
	}
}
