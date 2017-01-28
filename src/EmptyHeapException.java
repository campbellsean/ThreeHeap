/**
* This exception signifies an invalid access on an empty heap.
*/
public class EmptyHeapException extends RuntimeException {
	public EmptyHeapException() 
	{}

	public EmptyHeapException(String message) {
		super(message);
	}
}
