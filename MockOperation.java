package Security.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import Security.Operation;

public class MockOperation implements Operation {

	 private String lastMethod;

	    @Override
	    public void executeOperation() {
	        lastMethod = "executeOperation";
	    }

	    @Override
	    public void cancelOperation() {
	        lastMethod = "cancelOperation";
	    }

	    @Override
	    public void operationMetadata() {
	        lastMethod = "operationMetadata";
	    }

	    public void assertLastMethod(String expectedMethod) {
	        assertEquals(expectedMethod, lastMethod);
	    }

	    public void assertNoMethodCalled() {
	        assertNull(lastMethod);
	    }

}
