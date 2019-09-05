
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.OblemicRepository;
import domain.Oblemic;

@Component
@Transactional
public class StringToOblemicConverter implements Converter<String, Oblemic> {

	@Autowired
	OblemicRepository	OblemicRepository;


	@Override
	public Oblemic convert(final String text) {
		Oblemic result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.OblemicRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
