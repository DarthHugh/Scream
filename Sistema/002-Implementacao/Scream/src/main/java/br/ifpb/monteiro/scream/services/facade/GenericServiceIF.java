package br.ifpb.monteiro.scream.services.facade;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mauricio
 * @param <T>
 */
public interface GenericServiceIF<T> extends Serializable {

    int count();

    void create(T entity);

    void update(T entity);

    T findById(Long id);

    List<T> findAll();

    List<T> findRange(int[] range);

    void delete(T entity);

}