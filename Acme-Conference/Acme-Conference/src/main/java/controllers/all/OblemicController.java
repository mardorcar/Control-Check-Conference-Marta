
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
import services.OblemicService;
import controllers.AbstractController;
import domain.Conference;
import domain.Oblemic;

@Controller
@RequestMapping("/oblemic")
public class OblemicController extends AbstractController {

	@Autowired
	private OblemicService		oblemicService;

	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int conferenceId) {
		ModelAndView result;
		try {

			final Conference conference = this.conferenceService.findOne(conferenceId);
			Assert.notNull(conference);
			final Collection<Oblemic> oblemics = this.oblemicService.findByConference(conferenceId);

			result = new ModelAndView("oblemic/list");
			result.addObject("requestURI", "/oblemic/list.do");
			result.addObject("oblemics", oblemics);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int oblemicId) {
		ModelAndView result;
		try {
			final Oblemic oblemic = this.oblemicService.findOne(oblemicId);
			Assert.notNull(oblemic);
			Assert.isTrue(oblemic.getMode().equals("FINAL"));
			result = new ModelAndView("oblemic/show");

			result.addObject("requestURI", "oblemic/show.do");
			result.addObject("oblemic", oblemic);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");
		}

		return result;
	}
}
