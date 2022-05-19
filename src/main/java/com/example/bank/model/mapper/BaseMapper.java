package com.example.bank.model.mapper;

import java.util.Collection;

public interface BaseMapper<T,E> {
    T convertToEntity(E dto);
    T convertToDto(E entity);
    Collection<T> convertToEntityColl(Collection<E> dtoCollection);
    Collection<T> convertToDtoColl(Collection<E> entityCollection);
}
