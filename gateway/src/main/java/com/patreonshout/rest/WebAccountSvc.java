package com.patreonshout.rest;

import com.patreonshout.PSException;
import com.patreonshout.beans.CreatorTokensBean;
import com.patreonshout.beans.IntegrationRequestBean;
import com.patreonshout.beans.NewWebAccount;
import com.patreonshout.beans.request.LoginRequest;
import com.patreonshout.beans.request.RegisterRequest;
import com.patreonshout.beans.response.LoginResponse;
import com.patreonshout.jpa.TestRepository;
import com.patreonshout.jpa.WebAccount;
import com.patreonshout.rest.interfaces.WebAccountImpl;
import com.patreonshout.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Web Account  RESTful Endpoint Interface
 *
 * <p>
 * Responsibilities:
 * 1) Add items to our webaccounts table in our database
 * 2) Direct communication between the frontend portion of our application with the backend
 * </p>
 */
@RestController
public class WebAccountSvc extends BaseSvc implements WebAccountImpl {

	/**
	 * webAccount is the wrapper class for {@link com.patreonshout.jpa.WebAccountRepository}
	 */
	@Autowired
	WebAccount webAccount;

	/**
	 * TODO:
	 */
	@Autowired
	public TestRepository testRepository;

	/**
	 * {@inheritDoc}
	 */
	public HttpStatus Register(@RequestBody RegisterRequest registerRequest) {
		NewWebAccount webAccountBean = new NewWebAccount();
		webAccountBean.setUsername("user");
		webAccountBean.setPassword("pass");
		webAccountBean.setPassword_salt("salt");

//		try {
//			testRepository.save(webAccountBean);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}

		// TODO: Ensure username and password are sanitized and fit specific requirements
//		webAccount.putAccount(registerRequest);

		return HttpStatus.CREATED; // Http 201
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest) throws PSException {
		String loginToken = webAccount.readAccount(loginRequest);

		return new ResponseEntity<>(new LoginResponse(loginToken), HttpStatus.CREATED);
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> Logout(@RequestParam(name = "login_token") String loginToken) {
		webAccount.deleteLoginToken(loginToken);

		return ResponseUtil.Generic(HttpStatus.OK, "Token deleted if it existed.");
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpStatus Integration(@RequestBody IntegrationRequestBean integrationRequestBean) {
		webAccount.putIntegration(integrationRequestBean.getWebaccount().getWebaccount_id(),
				integrationRequestBean.getIntegrationType(),
				integrationRequestBean.getData());
		return HttpStatus.OK;
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> GetPatreonTokens(@RequestParam(name = "login_token") String loginToken) throws PSException {
		CreatorTokensBean tokens = webAccount.getPatreonTokens(loginToken);

		Map<String, String> response = Map.of("access",tokens.getAccess_token(), "refresh", tokens.getRefresh_token());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}