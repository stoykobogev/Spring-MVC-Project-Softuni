package org.softuni.secondtech.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Don't fiddle with the form")
public class FormArgumentException extends RuntimeException {
}
