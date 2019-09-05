
package services;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.OblemicRepository;
import domain.Administrator;
import domain.Oblemic;

@Service
@Transactional
public class OblemicService {

	@Autowired
	private OblemicRepository		oblemicRepository;

	@Autowired
	private Validator				validator;

	@Autowired
	private AdministratorService	administratorService;


	//COnstructors -------------------------
	public OblemicService() {
		super();
	}

	public Oblemic create() {
		Oblemic result;

		result = new Oblemic();

		return result;
	}

	public Collection<Oblemic> findAll() {
		Collection<Oblemic> result;

		result = this.oblemicRepository.findAll();

		return result;
	}

	public Oblemic findOne(final int oblemicId) {
		Oblemic result;

		result = this.oblemicRepository.findOne(oblemicId);

		return result;
	}

	public void save(final Oblemic oblemic) {
		Assert.notNull(oblemic);

		this.oblemicRepository.save(oblemic);
	}

	public void delete(final Oblemic oblemic) {
		this.oblemicRepository.delete(oblemic);
	}

	public Double publishedRatio() {
		final Double result = this.oblemicRepository.publishedRatio();
		Assert.notNull(result);
		return result;
	}

	public Double unpublishedRatio() {
		final Double result = this.oblemicRepository.unpublishedRatio();
		Assert.notNull(result);
		return result;
	}

	public Collection<Double> statsNumberOblemic() {
		final Collection<Double> result = this.oblemicRepository.statsNumberOblemics();
		Assert.notNull(result);
		return result;
	}
	public Collection<Oblemic> findByConference(final int id) {
		Collection<Oblemic> res = new ArrayList<>();
		res = this.oblemicRepository.findByConference(id);
		return res;
	}

	public Collection<Oblemic> findFinalOblemics() {
		Collection<Oblemic> res = new ArrayList<>();
		res = this.oblemicRepository.findFinalOblemics();
		return res;
	}

	public Collection<Oblemic> findDraftOblemics() {
		Collection<Oblemic> res = new ArrayList<>();
		res = this.oblemicRepository.findDraftOblemics();
		return res;
	}

	public Collection<Oblemic> findFinalOblemicsByAdmin(final int adminId) {
		Collection<Oblemic> res = new ArrayList<>();
		res = this.oblemicRepository.findFinalOblemicsByAdmin(adminId);
		return res;
	}

	public Collection<Oblemic> findDraftOblemicsByAdmin(final int adminId) {
		Collection<Oblemic> res = new ArrayList<>();
		res = this.oblemicRepository.findDraftOblemicsByAdmin(adminId);
		return res;
	}

	public String generateTicker(final Date date) {
		String result = "";

		final SecureRandom random = new SecureRandom();

		final DateFormat dateFormat = new SimpleDateFormat("yy:MMdd");

		final int i = random.nextInt(3)+2;
		
		final String randomCode = this.generateRandomString(i);
		
		result += randomCode + ":" + dateFormat.format(date);

		return result;
	}

	public String generateRandomString(final int length) {
		final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
		final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
		final String NUMBER = "0123456789";
		final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
		final SecureRandom random = new SecureRandom();

		if (length < 1)
			throw new IllegalArgumentException();

		final StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
			final int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			final char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

			sb.append(rndChar);

		}

		return sb.toString();
	}

	public Oblemic reconstruct(final Oblemic oblemic, final BindingResult binding) {
		final Administrator administrator = this.administratorService.findByPrincipal();
		final Oblemic res = oblemic;
		final Date date = new Date();
		res.setTicker(this.generateTicker(date));
		res.setPublicationMoment(date);
		res.setAdministrator(administrator);
		this.validator.validate(res, binding);
		return res;
	}
}
