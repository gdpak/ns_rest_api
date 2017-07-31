package domain;


import java.util.Date;
import java.util.Optional;

/*
 * CRUD API for Virtual Link Resource
 * 
 * @author Deepak Agrawal
 */

public class VirtualLinkManager {
	private static VirtualLinkDb Vldb = new VirtualLinkDb();
    static { // Add some Data to this list for test
		Date dt = new Date();
		Vldb.add(new VirtualLink(1, "1.1.1.1", "external", 1, 1, 0, 0, dt ));
		Vldb.add(new VirtualLink(2, "2.2.2.2", "external", 1, 1, 0, 0, dt ));
	}
	/*
	 * Get all VL List
	 */
	public static VirtualLinkDb getVls() {
		return Vldb;
	}
	
	/*
	 * @GET - Get one specified VL
	 */
	public static VirtualLink find(int id) {
		final Optional<VirtualLink> vl = Vldb.stream()
				.filter(VirtualLink -> VirtualLink.getId() == id).findFirst();
		return vl.orElse(null);
	}
	/*
	 * @POST - Add a VL 
	 */
	
	public static void add(VirtualLink vl) {
		Vldb.add(vl);
	}

	/*
	 * @DELETE - Delete a VL 
	 */
	public static void delete(int id) {
		VirtualLink vl = find(id);
		Vldb.remove(vl);
	}
	
	/*
	 * @PUT - Update a VL
	 */
	public static VirtualLink update(VirtualLink vl) {
		final Optional<VirtualLink> vl_origin = Vldb.stream()
				.filter(VirtualLink -> VirtualLink.getId() == vl.getId()).findFirst();
		if (vl_origin == null) {
			return null;
		}
		/*
		 * Delete the old VL
		 */
		delete(vl_origin.get().getId());
		add(vl);
		return vl;
	}
	

}
