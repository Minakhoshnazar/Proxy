package Security.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import Security.Operation;

public class TransferMockOperation implements Operation {
	
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
	
	public void assertLastMethod(String lastmethod) {
		assertEquals(this.lastMethod, lastmethod);
	}
	
	public void assertNoMethodCalled() {
		assertNull(lastMethod);
	}

}
