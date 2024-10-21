package restApiFirst.exception;

public class CustomeremailAlreadyExistException extends Exception{
	
	public CustomeremailAlreadyExistException(String msg) {
		super(msg);
	}

}