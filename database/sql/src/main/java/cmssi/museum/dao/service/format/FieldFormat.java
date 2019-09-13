package cmssi.museum.dao.service.format;

/**
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class FieldFormat extends JsonStringFormat<String> {

	public FieldFormat(String name) {
		super(name);
	}

	@Override
	public String doFormat(String e) {
		return e;
	}
}
