
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.QuoletService;
import controllers.AbstractController;
import domain.Conference;
import domain.Quolet;

@Controller
@RequestMapping("/quolet")
public class QuoletController extends AbstractController {

	@Autowired
	private QuoletService		quoletService;

	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int conferenceId) {
		ModelAndView result;
		try {

			final Conference conference = this.conferenceService.findOne(conferenceId);
			Assert.notNull(conference);
			final Collection<Quolet> quolets = this.quoletService.findByConference(conferenceId);

			result = new ModelAndView("quolet/list");
			result.addObject("requestURI", "/quolet/list.do");
			result.addObject("quolets", quolets);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int quoletId) {
		ModelAndView result;
		try {
			final Quolet quolet = this.quoletService.findOne(quoletId);
			Assert.notNull(quolet);
			Assert.isTrue(quolet.getMode().equals("FINAL"));
			result = new ModelAndView("quolet/show");

			result.addObject("requestURI", "quolet/show.do");
			result.addObject("quolet", quolet);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
}
