/**
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
open module jpms.adaptive.mysql {

	requires java.sql;
	
	exports com.mysql.cj.api;
	exports com.mysql.cj.api.authentication;
	exports com.mysql.cj.api.conf;
	exports com.mysql.cj.api.exceptions;
	exports com.mysql.cj.api.io;
	exports com.mysql.cj.api.jdbc;
	exports com.mysql.cj.api.jdbc.ha;
	exports com.mysql.cj.api.jdbc.interceptors;
	exports com.mysql.cj.api.jdbc.result;
	exports com.mysql.cj.api.log;
	exports com.mysql.cj.api.mysqla.authentication;
	exports com.mysql.cj.api.mysqla.io;
	exports com.mysql.cj.api.mysqla.result;
	exports com.mysql.cj.api.result;
	exports com.mysql.cj.api.x.core;
	exports com.mysql.cj.api.x.io;
	exports com.mysql.cj.api.xdevapi;
	exports com.mysql.cj.core;
	exports com.mysql.cj.core.admin;
	exports com.mysql.cj.core.authentication;
	exports com.mysql.cj.core.conf;
	exports com.mysql.cj.core.conf.url;
	exports com.mysql.cj.core.exceptions;
	exports com.mysql.cj.core.io;
	exports com.mysql.cj.core.log;
	exports com.mysql.cj.core.profiler;
	exports com.mysql.cj.core.result;
	exports com.mysql.cj.core.util;
	exports com.mysql.cj.jdbc;
	exports com.mysql.cj.jdbc.admin;
	exports com.mysql.cj.jdbc.exceptions;
	exports com.mysql.cj.jdbc.ha;
	exports com.mysql.cj.jdbc.integration.c3p0;
	exports com.mysql.cj.jdbc.integration.jboss;
	exports com.mysql.cj.jdbc.interceptors;
	exports com.mysql.cj.jdbc.io;
	exports com.mysql.cj.jdbc.jmx;
	exports com.mysql.cj.jdbc.result;
	exports com.mysql.cj.jdbc.util;
	exports com.mysql.cj.mysqla;
	exports com.mysql.cj.mysqla.authentication;
	exports com.mysql.cj.mysqla.io;
	exports com.mysql.cj.mysqla.result;
	exports com.mysql.cj.x.core;
	exports com.mysql.cj.x.io;
	exports com.mysql.cj.x.protobuf;
	exports com.mysql.cj.xdevapi;
	exports com.mysql.jdbc;
}