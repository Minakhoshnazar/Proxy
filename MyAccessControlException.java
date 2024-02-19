package Security.Test;

public class MyAccessControlException extends RuntimeException {

	 public MyAccessControlException(String message) {
	        super(message);
	    }

	    public MyAccessControlException() {
	        super("Access control exception occurred");
	    }

}
