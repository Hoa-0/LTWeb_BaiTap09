package vn.iotstar.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Sai email/mật khẩu
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
		return buildResponse(HttpStatus.UNAUTHORIZED, "Thông tin đăng nhập không hợp lệ!");
	}

	// Tài khoản bị khóa/disabled
	@ExceptionHandler(AccountStatusException.class)
	public ResponseEntity<?> handleAccountStatus(AccountStatusException ex) {
		return buildResponse(HttpStatus.FORBIDDEN, "Tài khoản đã bị khóa hoặc vô hiệu hóa!");
	}

	// JWT hết hạn
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> handleExpiredJwt(ExpiredJwtException ex) {
		return buildResponse(HttpStatus.UNAUTHORIZED, "Token đã hết hạn!");
	}

	// JWT không hợp lệ
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<?> handleJwtException(JwtException ex) {
		return buildResponse(HttpStatus.UNAUTHORIZED, "Token không hợp lệ!");
	}

	// Các lỗi khác
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneralException(Exception ex) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi: " + ex.getMessage());
	}

	// Helper để tạo JSON response
	private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", status.value());
		body.put("error", message);
		return new ResponseEntity<>(body, status);
	}
}
