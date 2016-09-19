package uy.montdeo.orion.core.database.converter;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.util.StringUtils.hasText;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Class implementing the {@link AttributeConverter} interface to convert all the String 
 * entity attributes into upper case format when data is retrieved from/sent to the database.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AttributeConverter
 */
@Converter(autoApply = false)
public class UpperCaseJpaAttributeConverter implements AttributeConverter<String, String> {

	/*
	 * (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
	 */
	@Override
	public String convertToDatabaseColumn(String attribute) {
		if(hasText(attribute)) {
			return attribute.toUpperCase(getLocale());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
	 */
	@Override
	public String convertToEntityAttribute(String data) {
		if(hasText(data)) {
			return data.toUpperCase(getLocale());
		}
		return null;
	}

}
