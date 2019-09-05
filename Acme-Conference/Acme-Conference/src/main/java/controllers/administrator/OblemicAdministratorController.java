
package controllers.administrator;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.ConferenceService;
import services.OblemicService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Conference;
import domain.Oblemic;

@Controller
@RequestMapping("/oblemic/administrator")
public class OblemicAdministratorController extends AbstractController {

	@Autowired
	private OblemicService			oblemicService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ConferenceService		conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {

			final Administrator admin = this.administratorService.findByPrincipal();

			final Date fecha = new Date();
			final Long date = fecha.getTime();

			final Collection<Oblemic> finalOblemicsAdmin = this.oblemicService.findFinalOblemicsByAdmin(admin.getId());
			final Collection<Oblemic> draftOblemicsAdmin = this.oblemicService.findDraftOblemicsByAdmin(admin.getId());

			final Collection<Oblemic> finalOblemics = this.oblemicService.findFinalOblemics();
			final Collection<Oblemic> draftOblemics = this.oblemicService.findDraftOblemics();

			final Locale l = LocaleContextHolder.getLocale();
			final String lang = l.getLanguage();

			result = new ModelAndView("oblemic/list");
			result.addObject("date", date);
			result.addObject("requestURI", "/oblemic/administrator/list.do");
			result.addObject("finalOblemicsAdmin", finalOblemicsAdmin);
			result.addObject("draftOblemicsAdmin", draftOblemicsAdmin);
			result.addObject("finalOblemics", finalOblemics);
			result.addObject("draftOblemics", draftOblemics);
			result.addObject("lang", lang);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			this.administratorService.findByPrincipal();
			final Collection<Conference> conferences = this.conferenceService.findNextConferences();
			Assert.notEmpty(conferences);
			final Oblemic oblemic = this.oblemicService.create();
			result = new ModelAndView("oblemic/edit");
			result.addObject("oblemic", oblemic);
			result.addObject("conferences", conferences);

		} catch (final Throwable oops) {

			final Administrator admin = this.administratorService.findByPrincipal();

			final Date fecha = new Date();
			final Long date = fecha.getTime();

			final Collection<Oblemic> finalOblemicsAdmin = this.oblemicService.findFinalOblemicsByAdmin(admin.getId());
			final Collection<Oblemic> draftOblemicsAdmin = this.oblemicService.findDraftOblemicsByAdmin(admin.getId());

			final Collection<Oblemic> finalOblemics = this.oblemicService.findFinalOblemics();
			final Collection<Oblemic> draftOblemics = this.oblemicService.findDraftOblemics();

			final Collection<Conference> conferences = this.conferenceService.findNextConferences();
			if (conferences.isEmpty()) {
				result = new ModelAndView("oblemic/list");
				result.addObject("finalOblemics", finalOblemics);
				result.addObject("draftOblemics", draftOblemics);
				result.addObject("finalOblemicsAdmin", finalOblemicsAdmin);
				result.addObject("draftOblemicsAdmin", draftOblemicsAdmin);
				result.addObject("date", date);
				result.addObject("message", "oblemic.conferences.error");

			} else
				result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("oblemicId") final int oblemicId) {

		ModelAndView result;

		try {
			this.administratorService.findByPrincipal();
			final Collection<Conference> conferences = this.conferenceService.findNextConferences();
			Assert.notEmpty(conferences);
			final Oblemic oblemic = this.oblemicService.findOne(oblemicId);
			Assert.notNull(oblemic);
			Assert.isTrue(oblemic.getMode().equals("DRAFT"));
			result = new ModelAndView("oblemic/edit");
			result.addObject("oblemic", oblemic);
			result.addObject("conferences", conferences);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("oblemic") final Oblemic oblemic, final BindingResult binding) {

		ModelAndView result;

		try {

			this.administratorService.findByPrincipal();
			final Oblemic oblemicF = this.oblemicService.reconstruct(oblemic, binding);
			if (binding.hasErrors()) {

				final Collection<Conference> conferences = this.conferenceService.findNextConferences();
				Assert.notEmpty(conferences);
				result = new ModelAndView("oblemic/edit");
				result.addObject("oblemic", oblemic);
				result.addObject("conferences", conferences);

			} else
				try {

					this.oblemicService.save(oblemicF);
					result = new ModelAndView("redirect:/oblemic/administrator/list.do");

				} catch (final Throwable oops) {
					result = new ModelAndView("oblemic/edit");
					result.addObject("oblemic", oblemic);
					result.addObject("message", "oblemic.conferences.error");
				}
		} catch (final Throwable oops) {
			result = new ModelAndView("oblemic/edit");
			result.addObject("oblemic", oblemic);
			result.addObject("message", "oblemic.commit.error");
		}

		return result;

	}
	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Oblemic oblemic, final BindingResult binding) {
		ModelAndView result;
		final Oblemic res = this.oblemicService.findOne(oblemic.getId());

		try {
			this.administratorService.findByPrincipal();
			this.oblemicService.delete(res);
			result = new ModelAndView("redirect:/oblemic/administrator/list.do");
		} catch (final Throwable oops) {

			final Collection<Conference> conferences = this.conferenceService.findNextConferences();
			result = new ModelAndView("oblemic/edit");
			result.addObject("oblemic", oblemic);
			result.addObject("conferences", conferences);
			result.addObject("message", oops.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int oblemicId) {
		ModelAndView result;
		try {
			this.administratorService.findByPrincipal();
			final Oblemic oblemic = this.oblemicService.findOne(oblemicId);
			Assert.notNull(oblemic);

			final Locale l = LocaleContextHolder.getLocale();
			final String lang = l.getLanguage();

			result = new ModelAndView("oblemic/show");

			result.addObject("requestURI", "oblemic/administrator/show.do");
			result.addObject("oblemic", oblemic);
			result.addObject("lang", lang);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

}
