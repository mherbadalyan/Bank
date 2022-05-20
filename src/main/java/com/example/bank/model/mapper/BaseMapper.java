package com.example.bank.model.mapper;

import java.util.Collection;

public interface BaseMapper<T,E> {
    T convertToEntity(E dto);
    E convertToDto(T entity);
    Collection<T> convertToEntityColl(Collection<E> dtoCollection);
    Collection<E> convertToDtoColl(Collection<T> entityCollection);
}
