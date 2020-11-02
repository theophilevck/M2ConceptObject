package entity.LivingBeing;

import entity.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Chewbacca.
 */
public class Chewbacca extends Master{

	/** The chewbacca. */
	private static Chewbacca chewbacca;
	
	/**
	 * Instantiates a new chewbacca.
	 *
	 * @param map the map
	 */
	private Chewbacca(Map map) {
		super(null);
		this.image="/img/chewbi.png";
		this.name="chewbacca";
	}
	
	/**
	 * Gets the single instance of Chewbacca.
	 *
	 * @return single instance of Chewbacca
	 */
	public static Chewbacca getInstance() {
		if(chewbacca==null) {
			chewbacca=new Chewbacca(null);
		}
		return chewbacca;
	}
}
