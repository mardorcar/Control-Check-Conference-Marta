package controllers.administrator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.ConfigurationParameters;
import services.ConfigurationParametersService;

@Controller
@RequestMapping("/configuration/administrator")
public class ConfigurationParametersAdministratorController extends AbstractController {

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final ConfigurationParameters config = this.configurationParametersService.find();
		result = this.createEditModelAndView(config);
		return result;

	}

	@RequestMapping(value = "/setup", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ConfigurationParameters config, final BindingResult binding) {
		ModelAndView result;
		Boolean b;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(config);
			b = false;
			result.addObject("b", b);
		} else
			try {
				final ConfigurationParameters saved = this.configurationParametersService.save(config);

				//Creamos el model and view de la p�gina a la que nos lleva el form
				result = this.createEditModelAndView(saved);

				//rellenamos los datos necesarios para que en la p�gina de welcome aparezcan el banner, el mensaje del
				//sistema, la hora, etc.
				SimpleDateFormat formatter;
				String moment;

				//el tipo Locale nos permite hacer operaciones con el idioma del sistema
				final Locale l = LocaleContextHolder.getLocale();
				final String lang = l.getLanguage();

				final String name = saved.getSystemName();
				String sysMessage = "";

				if (lang == "en")
					sysMessage = sysMessage + saved.getMessage();
				else if (lang == "es")
					sysMessage = sysMessage + saved.getMessageEs();

				formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				moment = formatter.format(new Date());

				//a�adimos en el modelo todos los elementos necesarios
				result.addObject("name", name);
				result.addObject("sysMessage", sysMessage);
				result.addObject("moment", moment);
				result.addObject("lang", lang);

				final String banner = saved.getBanner();
				result.addObject("banner", banner);
				b = true;
				result.addObject("b", b);

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(config, "configurationParameters.commit.error");
				b = false;
				result.addObject("b", b);
			}

		Assert.notNull(config);
		return result;

	}

	protected ModelAndView createEditModelAndView(final ConfigurationParameters config) {
		return this.createEditModelAndView(config, null);
	}

	protected ModelAndView createEditModelAndView(final ConfigurationParameters configurationParameters, final String messageCode) {
		final ModelAndView result;
		result = new ModelAndView("configuration/setup");
		result.addObject("configurationParameters", configurationParameters);
		result.addObject("message", messageCode);

		return result;
	}
}
