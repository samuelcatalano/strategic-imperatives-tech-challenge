package co.uk.imperatives.exercise.service.base;

import co.uk.imperatives.exercise.exception.BusinessException;
import co.uk.imperatives.exercise.entity.base.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {

    List<E> findAll();

    E findById(Long id) throws BusinessException;

    void deleteById(Long id) throws BusinessException;
}
