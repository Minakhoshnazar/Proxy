package Security.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import Security.AccessControl;
import Security.Operation;

class SecureOperationTest {

	@Test
	void blockNonExistingUser() {
		TransferMockOperation transfermockoperation = new TransferMockOperation();
		Operation operation = new SecureOperation (transfermockoperation);
		
		CurrentUser.setUser("Guerra");
		assertThrows(MyAccessControlException.class, () -> operation.cancelOperation());
		transfermockoperation.assertNoMethodCalled();
		
	}
	
	@Test
	void authorizedUser() {
		AccessControl.getInstance().addUser("Guerra");
		AccessControl.getInstance().addAccess("Guerra", TransferMockOperation.class, "cancelOperation");
		
		TransferMockOperation transfermockoperation = new TransferMockOperation();
		Operation operation = new SecureOperation (transfermockoperation);
		
		CurrentUser.setUser("Guerra");
		
		operation.cancelOperation();
		transfermockoperation.assertLastMethod("cancelOperation");
		
	}
	
	 @Test
	    void executeOperationAuthorizedUser() throws AccessnotAuthorizedException {
		    AccessControl.getInstance().addUser("Guerra");
	        AccessControl.getInstance().addAccess("Guerra", TransferMockOperation.class, "executeOperation");

	        TransferMockOperation transfermockoperation = new TransferMockOperation();
	        Operation operation = new SecureOperation(transfermockoperation);

	        CurrentUser.setUser("Guerra");

	        assertDoesNotThrow(() -> operation.executeOperation());
	        transfermockoperation.assertLastMethod("executeOperation");
	
	 }
	 
	  @Test
	    void operationMetadataAuthorizedUser() {
		    AccessControl.getInstance().addUser("Guerra");
	        AccessControl.getInstance().addAccess("Guerra", TransferMockOperation.class, "operationMetadata");

	        TransferMockOperation transfermockoperation = new TransferMockOperation();
	        Operation operation = new SecureOperation(transfermockoperation);

	        CurrentUser.setUser("Guerra");

	        assertDoesNotThrow(() -> operation.operationMetadata());
	        transfermockoperation.assertLastMethod("operationMetadata");
	
	 }
	    
	
	@AfterEach
	void resetSingleton() {
		AccessControl.reset();
	}
	
	

}
