
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Quolet;

@Component
@Transactional
public class QuoletToStringConverter implements Converter<Quolet, String> {

	@Override
	public String convert(final Quolet Quolet) {
		String result;
		if (Quolet == null)
			result = null;
		else
			result = String.valueOf(Quolet.getId());
		return result;
	}

}
