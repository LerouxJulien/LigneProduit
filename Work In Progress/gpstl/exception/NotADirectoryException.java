package gpstl.exception;

public class NotADirectoryException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotADirectoryException(String message){
		super(message);
	}
}
