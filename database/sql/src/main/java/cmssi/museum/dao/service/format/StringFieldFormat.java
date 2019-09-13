/**
 * 
 */
package cmssi.museum.dao.service.format;

/**
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class StringFieldFormat extends FieldFormat {

	public StringFieldFormat(String name) {
		super(name);
	}

	@Override
	public String doFormat(String e) {
		return new StringBuilder().append('"').append(e).append('"').toString();
	}
}
