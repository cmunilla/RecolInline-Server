package cmssi.museum.dao.service.format;

/**
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class ModelFormat extends JsonStringFormat<FieldFormat> {
		
	public ModelFormat(String name){
		super(name);
	}

	@Override
	public String doFormat(FieldFormat e) {
		return e.format();
	}	
}
