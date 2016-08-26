package com.tianyalan.usercenter.domain;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianyalan.usercenter.domain.model.user.ContactInformation;
import com.tianyalan.usercenter.domain.model.user.EmailAddress;
import com.tianyalan.usercenter.domain.model.user.Enablement;
import com.tianyalan.usercenter.domain.model.user.FullName;
import com.tianyalan.usercenter.domain.model.user.Person;
import com.tianyalan.usercenter.domain.model.user.PostalAddress;
import com.tianyalan.usercenter.domain.model.user.Telephone;
import com.tianyalan.usercenter.domain.model.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-usercenter.xml" })
public abstract class DomainTest extends EventTest {

	protected String FIXTURE_PASSWORD = "SecretPassword!";
	
	@Override
	protected void setUp() throws Exception {
		
		super.setUp();
	}


	protected User userAggregate() {
		User user = new User("invitationId", "tianyalan_2", FIXTURE_PASSWORD, Enablement.indefiniteEnablement(),
				new Person(new FullName("Tianya", "Lan"),
						new ContactInformation(new EmailAddress("tianyalan@163.com"),
								new PostalAddress("Westlake", "Hangzhou", "Zhejiang", "310000", "CN"),
								new Telephone("111-555-1210"), new Telephone("222-555-1212"))));
		
		return user;
	}
}
