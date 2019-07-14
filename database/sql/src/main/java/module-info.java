/**
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
module recolinline.db.sql {

	requires jpms.adaptive.jboss;
	requires jpms.adaptive.mysql;
	requires jpms.adaptive.hibernate;

	exports cmssi.museum.dao.service;
	exports cmssi.museum.dao.entity;
	exports cmssi.museum;
}