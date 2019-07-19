/**
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
module cmssi.recolinline.sql {

	requires jpms.adaptive.jboss;
	requires jpms.adaptive.mysql;
	requires jpms.adaptive.hibernate;
	
	exports cmssi.museum.dao.service;
	exports cmssi.museum.dao.entity;
	exports cmssi.museum;

	opens cmssi.museum.dao.entity to jpms.adaptive.hibernate;
}