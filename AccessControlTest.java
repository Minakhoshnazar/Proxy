package Security.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import Security.AccessControl;

class AccessControlTest {

	@Test
	void noUser() {
		AccessControl ac = AccessControl.getInstance();
		boolean access = ac.verifyAccess("Guerra",
				TransferMockOperation.class, "executeOperation");
		assertFalse(access);
	}
	
	@Test
	void newUser() {
		AccessControl ac = AccessControl.getInstance();
		ac.addUser("Guerra");
		ac.addAccess("Guerra", TransferMockOperation.class, "executeOperation");
		boolean access = ac.verifyAccess("Guerra",
				TransferMockOperation.class, "executeOperation");
		assertTrue(access);
	}

	@Test
	void notAuthorizeUser() {
		AccessControl ac = AccessControl.getInstance();
		ac.addUser("Guerra");
		ac.addAccess("Guerra", TransferMockOperation.class, "executeOperation");
		boolean access = ac.verifyAccess("Guerra",
				TransferMockOperation.class, "cancelOperation");
		assertFalse(access);
	}
	
	@Test
	void addAccessToNonExistingUser() {
		AccessControl ac = AccessControl.getInstance();

        assertThrows(RuntimeException.class, () -> ac.addAccess("NonExistingUser", TransferMockOperation.class, "executeOperation"));
	}
	
	@Test
	void addDuplicatedUser() {
		  AccessControl ac = AccessControl.getInstance();

		    ac.addUser("DuplicatedUser");

		    assertThrows(RuntimeException.class, () -> ac.addUser("DuplicatedUser"));
		}
	
	@Test
	void addDuplicatedAccess() {
		AccessControl ac = AccessControl.getInstance();

	    ac.addUser("DuplicatedUser");
	   
	    assertThrows(RuntimeException.class, () -> {
	        ac.addUser("DuplicatedUser");
	    });
	}
	
	@AfterEach
	void resetSingleton() {
		AccessControl.reset();
	}
	
	

}	
	
	
