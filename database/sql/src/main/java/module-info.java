/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
/**
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
module recolinline.database.sql {

	requires jpms.adaptive.jboss;
	requires jpms.adaptive.mysql;
	requires jpms.adaptive.hibernate;
	
	requires transitive recolinline.database.api;
		
	exports cmssi.museum.dao.service;
	exports cmssi.museum.dao.entity;
	exports cmssi.museum;

	opens cmssi.museum.dao.entity to jpms.adaptive.hibernate;
}