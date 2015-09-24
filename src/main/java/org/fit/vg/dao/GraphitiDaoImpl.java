package org.fit.vg.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.fit.vg.model.Images;
import org.springframework.stereotype.Repository;
/**
 * Dao Implementation to perform Data access using hibernate
 * @author Ravi Kumar Singh
 *
 */
@Transactional
@Repository("graphitiDao")
public class GraphitiDaoImpl extends AbstractDao<Images> implements GraphitiDao {

	@Override
	public Images getGraphitiByloc(double lat, double lng) {
		// TODO Auto-generated method stub
		return (Images) getSession()
				.getNamedQuery("getGraphitiByloc").setDouble("latitude", lat).setDouble("longitude", lng);
	}

	@Override
	public List<Images> getGraphitiByUser(Images images) {
		// TODO Auto-generated method stub
		return (List<Images>) getSession().createQuery("from Images where userName = :uname").setParameter("uname", images.getUserName()).list();
	}

	@Override
	public List<Images> getGraphitiByradius(double startlat, double endlat,
			double startlng, double endlng) {
		// TODO Auto-generated method stub
		return (List<Images>) getSession().createQuery("from Images where latitude between :startlat and :endlat"
				+ " and longitude between :startlng and :endnlg")
				.setParameter("startlat", startlat)
				.setParameter("endlat", endlat)
				.setParameter("startlng", startlng)
				.setParameter("endnlg", endlng).list();
	}

	@Override
	public Images getGraphitiByUserIdImageId(Images images) {
		// TODO Auto-generated method stub
		return (Images)getSession().createQuery("from Images where userName = :uname and id = :id")
				.setParameter("uname", images.getUserName())
				.setParameter("id", images.getId()).list().get(0);
	}

}
