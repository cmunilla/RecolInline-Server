/**
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
module recolinline.controler.api {

	requires transitive cmssi.lyson;
	
	exports cmssi.museum.controler.api;
	exports cmssi.museum.controler.api.format;
	exports cmssi.museum.controler.api.format.sign;
}