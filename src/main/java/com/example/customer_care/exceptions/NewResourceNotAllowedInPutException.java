package com.example.customer_care.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED , reason = "Existing resource is not allowed in Put Request. Please use POST method instead.")
public class NewResourceNotAllowedInPutException  extends Exception{
}
