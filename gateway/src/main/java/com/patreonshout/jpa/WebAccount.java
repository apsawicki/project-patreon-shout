package com.patreonshout.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Functions for WebAccount endpoints that allow interaction with the database
 */
@Component
public class WebAccount {

	/**
	 * webAccountRepository is the {@link com.patreonshout.jpa.WebAccountRepository} class that handles all logic regarding
	 * database connections with the webaccounts table
	 */
	@Autowired
	WebAccountRepository webAccountRepository;

	/**
	 * Attempts to add a {@link WebAccount} into the database
	 *
	 * @param username is the username for the {@link WebAccount}
	 * @param password is the password for the {@link WebAccount}
	 * @return {@link HttpStatus} 200 if the registration was successful
	 * {@link HttpStatus} 409 if the account already exists
	 */
	public HttpStatus putAccount(String username, String password) {
		try {
			webAccountRepository.putAccount(username, password);
		} catch (DataIntegrityViolationException ex) { // Username already exists in webaccounts table
			return HttpStatus.CONFLICT;
		}

		return HttpStatus.OK; // No error
	}
}
