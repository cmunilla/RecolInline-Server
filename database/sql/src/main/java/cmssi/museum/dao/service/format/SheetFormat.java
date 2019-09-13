/**
 * 
 */
package cmssi.museum.dao.service.format;

/**
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class SheetFormat extends JsonStringFormat<ModelFormat>  {

	public SheetFormat() {
		super();
	}

	@Override
	public String doFormat(ModelFormat e) {
		return e.format();
	}

	public void setName(String name) {
		super.name = name;
	}
}
