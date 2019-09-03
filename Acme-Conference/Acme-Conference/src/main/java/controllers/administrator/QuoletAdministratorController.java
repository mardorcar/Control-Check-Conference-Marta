
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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
import services.QuoletService;
import controllers.AbstractController;
import domain.Conference;
import domain.Quolet;

@Controller
@RequestMapping("/quolet/administrator")
public class QuoletAdministratorController extends AbstractController {

	@Autowired
	private QuoletService			quoletService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ConferenceService		conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {

			this.administratorService.findByPrincipal();

			final Collection<Quolet> finalQuolets = this.quoletService.findFinalQuolets();
			final Collection<Quolet> draftQuolets = this.quoletService.findDraftQuolets();

			result = new ModelAndView("quolet/list");
			result.addObject("requestURI", "/quolet/administrator/list.do");
			result.addObject("finalQuolets", finalQuolets);
			result.addObject("draftQuolets", draftQuolets);

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
			final Quolet quolet = this.quoletService.create();
			result = new ModelAndView("quolet/edit");
			result.addObject("quolet", quolet);
			result.addObject("conferences", conferences);

		} catch (final Throwable oops) {
			final Collection<Quolet> finalQuolets = this.quoletService.findFinalQuolets();
			final Collection<Quolet> draftQuolets = this.quoletService.findDraftQuolets();
			final Collection<Conference> conferences = this.conferenceService.findNextConferences();
			if (conferences.isEmpty()) {
				result = new ModelAndView("quolet/list");
				result.addObject("finalQuolets", finalQuolets);
				result.addObject("draftQuolets", draftQuolets);
				result.addObject("message", "quolet.conferences.error");

			} else
				result = new ModelAndView("redirect:/#");
		}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("quoletId") final int quoletId) {

		ModelAndView result;

		try {
			this.administratorService.findByPrincipal();
			final Collection<Conference> conferences = this.conferenceService.findNextConferences();
			Assert.notEmpty(conferences);
			final Quolet quolet = this.quoletService.findOne(quoletId);
			Assert.notNull(quolet);
			Assert.isTrue(quolet.getMode().equals("DRAFT"));
			result = new ModelAndView("quolet/edit");
			result.addObject("quolet", quolet);
			result.addObject("conferences", conferences);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/#");

		}

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("quolet") final Quolet quolet, final BindingResult binding) {

		ModelAndView result;
		this.administratorService.findByPrincipal();
		final Quolet quoletF = this.quoletService.reconstructSave(quolet, binding);
		if (binding.hasErrors()) {

			final Collection<Conference> conferences = this.conferenceService.findNextConferences();
			Assert.notEmpty(conferences);
			result = new ModelAndView("quolet/edit");
			result.addObject("quolet", quolet);
			result.addObject("conferences", conferences);

		} else
			try {

				this.quoletService.save(quoletF);
				result = new ModelAndView("redirect:/quolet/administrator/list.do");

			} catch (final Throwable oops) {
				result = new ModelAndView("quolet/edit");
				result.addObject("quolet", quolet);
				result.addObject("message", "quolet.commit.error");
			}

		return result;

	}
	@RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Quolet quolet, final BindingResult binding) {
		ModelAndView result;
		final Quolet res = this.quoletService.findOne(quolet.getId());

		try {
			this.administratorService.findByPrincipal();
			this.quoletService.delete(res);
			result = new ModelAndView("redirect:/quolet/administrator/list.do");
		} catch (final Throwable oops) {

			final Collection<Conference> conferences = this.conferenceService.findNextConferences();
			result = new ModelAndView("quolet/edit");
			result.addObject("quolet", quolet);
			result.addObject("conferences", conferences);
			result.addObject("message", oops.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int quoletId) {
		ModelAndView result;
		try {
			this.administratorService.findByPrincipal();
			final Quolet quolet = this.quoletService.findOne(quoletId);
			Assert.notNull(quolet);

			result = new ModelAndView("quolet/show");

			result.addObject("requestURI", "quolet/administrator/show.do");
			result.addObject("quolet", quolet);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

}
