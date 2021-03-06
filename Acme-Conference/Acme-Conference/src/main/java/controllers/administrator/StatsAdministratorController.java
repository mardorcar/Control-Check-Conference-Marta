
package controllers.administrator;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceCommentService;
import services.ConferenceService;
import services.PanelCommentService;
import services.PaperService;
import services.PresentationCommentService;
import services.OblemicService;
import services.RegistrationService;
import services.SubmissionService;
import services.TutorialCommentService;
import controllers.AbstractController;

@Controller
@RequestMapping("/stats/administrator")
public class StatsAdministratorController extends AbstractController {

	@Autowired
	private ConferenceService			conferenceService;

	@Autowired
	private ConferenceCommentService	conferenceCommentService;

	@Autowired
	private PresentationCommentService	presentationCommentService;

	@Autowired
	private PanelCommentService			panelCommentService;

	@Autowired
	private TutorialCommentService		tutorialCommentService;

	@Autowired
	private SubmissionService			submissionService;

	@Autowired
	private RegistrationService			registrationService;

	@Autowired
	private PaperService				paperService;

	@Autowired
	private OblemicService				oblemicService;


	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		result = new ModelAndView("stats/display");

		try {
			final Collection<Double> conferencesPerDays = this.conferenceService.statsConferencesDays();
			final Collection<Double> conferencesPerCategory = this.conferenceService.statsConferencePerCategory();
			final Collection<Double> conferenceCommentsPerConference = this.conferenceCommentService.statsCommentsPerConference();
			final Collection<Double> commentsPerPresentation = this.presentationCommentService.statsCommentsPerPresentation();
			final Collection<Double> commentsPerPanel = this.panelCommentService.statsCommentsPerPanel();
			final Collection<Double> commentsPerTutorial = this.tutorialCommentService.statsCommentsPerTutorial();
			final Collection<Double> submissionsPerConference = this.submissionService.statsSubmissionsPerConference();
			final Collection<Double> registrationsPerConference = this.registrationService.statsRegistrationsPerConference();
			final Collection<Double> conferencesFee = this.conferenceService.statsConferencesFee();
			final Collection<Double> numberOblemics = this.oblemicService.statsNumberOblemic();
			final Double publishedOblemics = this.oblemicService.publishedRatio();
			final Double unpublishedOblemics = this.oblemicService.unpublishedRatio();

			result.addObject("conferencesPerDays", conferencesPerDays);
			result.addObject("conferencesPerCategory", conferencesPerCategory);
			result.addObject("conferenceCommentsPerConference", conferenceCommentsPerConference);
			result.addObject("commentsPerPresentation", commentsPerPresentation);
			result.addObject("commentsPerPanel", commentsPerPanel);
			result.addObject("commentsPerTutorial", commentsPerTutorial);
			result.addObject("submissionsPerConference", submissionsPerConference);
			result.addObject("registrationsPerConference", registrationsPerConference);
			result.addObject("conferencesFee", conferencesFee);
			result.addObject("numberOblemics", numberOblemics);
			result.addObject("publishedOblemics", publishedOblemics);
			result.addObject("unpublishedOblemics", unpublishedOblemics);

			result.addObject("requestURI", "/stats/administrator/display.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");

		}
		return result;
	}
	@RequestMapping(value = "/score", method = RequestMethod.GET)
	public ModelAndView score() {
		ModelAndView result;
		result = new ModelAndView("stats/score");
		final List<String> score = this.paperService.statsAuthors();
		result.addObject("score", score);

		try {

			result.addObject("requestURI", "/stats/administrator/score.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:/#");

		}
		return result;
	}
}
