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
module recolinline.database.api {

	requires transitive recolinline.controler.api;

	exports cmssi.museum.database.api.format;
	exports cmssi.museum.database.api.service;
}