
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

import repositories.QuoletRepository;
import domain.Quolet;

@Service
@Transactional
public class QuoletService {

	@Autowired
	private QuoletRepository	quoletRepository;

	@Autowired
	private Validator			validator;


	//COnstructors -------------------------
	public QuoletService() {
		super();
	}

	public Quolet create() {
		Quolet result;

		result = new Quolet();

		return result;
	}

	public Collection<Quolet> findAll() {
		Collection<Quolet> result;

		result = this.quoletRepository.findAll();

		return result;
	}

	public Quolet findOne(final int quoletId) {
		Quolet result;

		result = this.quoletRepository.findOne(quoletId);

		return result;
	}

	public void save(final Quolet quolet) {
		Assert.notNull(quolet);

		this.quoletRepository.save(quolet);
	}

	public void delete(final Quolet quolet) {
		this.quoletRepository.delete(quolet);
	}

	public Collection<Quolet> findByConference(final int id) {
		Collection<Quolet> res = new ArrayList<>();
		res = this.quoletRepository.findByConference(id);
		return res;
	}

	public Collection<Quolet> findFinalQuolets() {
		Collection<Quolet> res = new ArrayList<>();
		res = this.quoletRepository.findFinalQuolets();
		return res;
	}

	public Collection<Quolet> findDraftQuolets() {
		Collection<Quolet> res = new ArrayList<>();
		res = this.quoletRepository.findDraftQuolets();
		return res;
	}

	public String generateTicker() {
		String result = "";

		final Date date = new Date();
		final Date aux = date;
		final DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");

		final String randomCode = this.generateRandomString(4);

		result += dateFormat.format(aux) + "-" + randomCode;

		return result;
	}

	public String generateRandomString(final int length) {
		final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
		final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
		final String NUMBER = "0123456789";
		final String DATA_FOR_RANDOM_STRING = CHAR_UPPER + NUMBER;
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
	public Quolet reconstruct(final Quolet quolet) {
		final Quolet res = quolet;
		final Date date = new Date();
		if (quolet.getId() == 0) {
			res.setTicker(this.generateTicker());
			res.setPublicationMoment(date);

		} else {
			final Quolet confDB = this.findOne(quolet.getId());
			Assert.isTrue(confDB.getMode().equals("DRAFT"));
			res.setTicker(quolet.getTicker());
			res.setPublicationMoment(quolet.getPublicationMoment());
		}

		return res;
	}

	public Quolet reconstructSave(final Quolet quolet, final BindingResult binding) {
		final Quolet res = quolet;
		final Date date = new Date();
		if (quolet.getId() == 0) {
			res.setTicker(this.generateTicker());
			res.setPublicationMoment(date);

		} else {
			final Quolet confDB = this.findOne(quolet.getId());
			Assert.isTrue(confDB.getMode().equals("DRAFT"));
			res.setTicker(quolet.getTicker());
			res.setPublicationMoment(quolet.getPublicationMoment());
		}

		this.validator.validate(res, binding);
		return res;
	}
}
