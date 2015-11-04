package com.eventwebapp.validators;

/**
 * Created by Xavier on 11/3/2015.
 */
public interface ValidatorInterface<T> {
    boolean validate(T entity);
}
