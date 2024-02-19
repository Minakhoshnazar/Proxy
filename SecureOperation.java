package Security.Test;

import Security.AccessControl;
import Security.Operation;

public class SecureOperation implements Operation {

	private Operation operation;
	
	public SecureOperation(Operation operation) {
		this.operation = operation;
		
	}
	public SecureOperation(MockOperation mockOperation) {
		this.operation = mockOperation;
	}
	@Override
	public void executeOperation() throws AccessnotAuthorizedException {
		  AccessControl securityStorage = AccessControl.getInstance();

	        if (!securityStorage.verifyAccess(CurrentUser.getUser(), operation.getClass(), "executeOperation")) {
	            throw new AccessnotAuthorizedException();
	        }

	        operation.executeOperation();
	}

	

	@Override
	public void cancelOperation() {
		if (CurrentUser.getUser() == null) {
			throw new MyAccessControlException();
		}
		AccessControl ac = AccessControl.getInstance();
		if (!ac.verifyAccess(CurrentUser.getUser(), operation.getClass(), "cancelOperation")) {
			throw new MyAccessControlException();
		}
        operation.cancelOperation();
	}

	@Override
	public void operationMetadata() throws AccessnotAuthorizedException {
		  AccessControl securityStorage = AccessControl.getInstance();

	        if (!securityStorage.verifyAccess(CurrentUser.getUser(), operation.getClass(), "operationMetadata")) {
	            throw new AccessnotAuthorizedException();
	        }

	        operation.operationMetadata();

	}

}
