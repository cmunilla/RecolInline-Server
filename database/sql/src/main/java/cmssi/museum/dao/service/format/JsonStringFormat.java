/**
 * 
 */
package cmssi.museum.dao.service.format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * &lt;E&gt; typed elements collection to be represented 
 * as a JSON formated String
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public abstract class JsonStringFormat<E> {

	protected static final Number readNumber(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        try {  	
     		char b = s.charAt(0);                
     		if ((b >= '0' && b <= '9') || b == '.' || b == '-' || b == '+') {
                if (b == '0') {
                	try {
                        if (s.length() > 2 && (s.charAt(1) == 'x' || s.charAt(1) == 'X')) {                            
                                return Integer.parseInt(s.substring(2), 16);
                        } else {
                                return Integer.parseInt(s, 8);
                        }
                	} catch(Exception ignore ){}
                }
                Number num;      
                if(s.indexOf('.') < 0) {
                	num = new BigInteger(s);
                	if(((BigInteger)num).compareTo(new BigInteger(String.valueOf(Long.MAX_VALUE))) <= 0) {
                    	Long myLong = Long.valueOf(s);
                        if (myLong.longValue() == myLong.intValue()) {
                            num = myLong.intValue();
                        } else {
                            num = myLong;
                        }
                	}
                	return num;
                } else {
                    num = new BigDecimal(s);
                    if((((BigDecimal)num).compareTo(new BigDecimal(Double.MAX_VALUE)) <= 0
                    	&& ((BigDecimal)num).compareTo(new BigDecimal(Double.MIN_VALUE)) >= 0)
                    	||  ((BigDecimal)num).intValue() == 0) {
	                    num = Double.valueOf(s);
                    }
                    return num;
                }
            }
 		} catch(Exception ignore) {}
        return null;
    }

	protected static final Boolean readBoolean(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("1")) {
            return Boolean.TRUE;
        }
        if (s.equalsIgnoreCase("false") || s.equalsIgnoreCase("no") || s.equalsIgnoreCase("0")) {
            return Boolean.FALSE;
        }
        return null;
    }
		
	/**
	 * Returns the appropriate JSON String formated representation
	 * of the &lt;E&gt; typed element passed as parameter
	 * 
	 * @return JSON formated String representation of the specified 
	 * &lt;E&gt; typed element
	 */
	public abstract String doFormat(E e);
	
	/**
	 * this JsonStringFormat name
	 */
	protected String name;

	/**
	 * the &lt;E&gt; typed elements collection
	 */
	protected List<E> elements;

	/**
	 * Constructor
	 */
	public JsonStringFormat() {
		this.elements = new ArrayList<>();
	}
	
	/**
	 * Constructor 
	 * 
	 * @param name the String name of this JsonStringFormat to
	 * be instantiated
	 */
	public JsonStringFormat(String name) {
		this();
		this.name = name;
	}	
	
	/**
	 * Returns the name of this JsonStringFormat
	 * 
	 * @return this JsonStringFormat name
	 */
	public String getName() {
		return this.name;
	}	

	/**
	 * Adds the <E> typed element passed as parameter to 
	 * the collection held by this JsonStringFormat
	 * 
	 * @param element the <E> typed element to be added
	 */
	public <K> void append(E e) {
		if(e == null) {
			return;
		}
		if(!JsonStringFormat.class.isAssignableFrom(e.getClass())) {			
		  this.elements.add(e);
		} else {
			JsonStringFormat<K> jsf = (JsonStringFormat<K>) e;
			String name = jsf.getName();
			if(name == null) {
				return;
			}
			boolean found = false;
			for(JsonStringFormat<K> jsfk :(List<JsonStringFormat<K>>)this.elements) {
				if(name.equals(jsfk.getName())) {						
					jsf.elements.forEach(x -> {jsfk.append(x);});
					found = true;
				}
			}
			if(!found){
				this.elements.add(e);
			}
		}
	}
	
	/**
	 * Returns a JSON formated String representation of the 
	 * collection of &lt;E&gt; typed elements held by this 
	 * JsonStringFormat
	 * 
	 * @return JSON formated String representation
	 * of the held collection
	 */
	public String format() {
		var chars = new char[] { ' ' , ',' };
		var builder = new StringBuilder();
		builder.append("{ \"").append(this.getName()).append("\" : [");
		this.elements.forEach(e -> {	
			builder.append(chars[0]);
			builder.append(doFormat(e));
			chars[0] = chars[1];
		});
		builder.append(" ] }");
		return builder.toString();
	}
}
