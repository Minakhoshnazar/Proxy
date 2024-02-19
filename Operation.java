package Security;

import java.lang.reflect.Method;

import Security.Test.AccessnotAuthorizedException;

public interface Operation {
	
	public void executeOperation() throws AccessnotAuthorizedException;
	public void cancelOperation();
	public void operationMetadata() throws AccessnotAuthorizedException;

}
