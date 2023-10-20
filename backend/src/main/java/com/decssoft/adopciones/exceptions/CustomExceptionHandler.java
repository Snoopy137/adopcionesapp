package com.decssoft.adopciones.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author mis_p
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("Hora registro", new Date());
        respuesta.put("status", status.value());
        List<String> errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        respuesta.put("mensaje", errores);
        return new ResponseEntity<>(respuesta, headers, status);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail badCredentialException(BadCredentialsException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        detail.setProperty("mensaje", "Credenciales incorrectas");
        return detail;
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail expiredTokenException(ExpiredJwtException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        detail.setProperty("mensaje", "Su sesion ha expirado, vuelva a ingresar al sistema");
        return detail;
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ProblemDetail signatureException(MalformedJwtException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        detail.setProperty("mensaje", "El codigo de accesso es invalido, intente volver a ingresar al sistema");
        return detail;
    }

    @ExceptionHandler(SignatureException.class)
    public ProblemDetail exception(SignatureException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        detail.setProperty("mensaje", "El codigo de atuenticación es invalido, intente volver a ingresar al sistema");
        return detail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail exception(AccessDeniedException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        detail.setProperty("mensaje", "No poseé authizacoin para realizar esta operación");
        return detail;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail exception(AuthenticationException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        detail.setProperty("mensaje", "Debe ingrear al sistema para realizar esta operación");
        return detail;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail exception(NoSuchElementException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Resource not found");;
        detail.setProperty("mensaje", ex.getMessage());
        return detail;
    }

}
