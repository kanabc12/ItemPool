package cn.javass.commons.file.util;

public class FTPClientException extends Exception {
	public FTPClientException(String message, Throwable cause) {
		super(message, cause);
	}
	public FTPClientException(String message) {
		super(message);
	}
}
