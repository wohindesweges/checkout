package com.tryouts.restapi.repo;

import com.tryouts.restapi.model.District;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Erweiteretes Repository
 * TODO
 */
@Repository
@Qualifier("Extended")
public class DistrictRepositoryExtended implements DistrictRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public District customFindMethod(Long id) {
        return (District) entityManager.createQuery("FROM District u WHERE u.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public void flush() {
entityManager.flush();
    }

    public <S extends District> S saveAndFlush(S entity) {
        return null;
    }

    public <S extends District> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    public void deleteAllInBatch(Iterable<District> entities) {

    }

    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    public void deleteAllInBatch() {

    }

    public District getOne(Long aLong) {
        return null;
    }

    public District getById(Long aLong) {
        return null;
    }

    public District getReferenceById(Long aLong) {
        return null;
    }

    public <S extends District> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    public <S extends District> List<S> findAll(Example<S> example) {
        return null;
    }

    public <S extends District> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    public <S extends District> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    public <S extends District> long count(Example<S> example) {
        return 0;
    }

    public <S extends District> boolean exists(Example<S> example) {
        return false;
    }

    public <S extends District, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    public <S extends District> S save(S entity) {
        return null;
    }

    public <S extends District> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    public Optional<District> findById(Long aLong) {
        return Optional.empty();
    }

    public boolean existsById(Long aLong) {
        return false;
    }

    public List<District> findAll() {
        return null;
    }

    public List<District> findAllById(Iterable<Long> longs) {
        return null;
    }

    public long count() {
        return 0;
    }

    public void deleteById(Long aLong) {

    }

    public void delete(District entity) {

    }

    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    public void deleteAll(Iterable<? extends District> entities) {

    }

    public void deleteAll() {

    }

    public List<District> findAll(Sort sort) {
        return null;
    }

    public Page<District> findAll(Pageable pageable) {
        return null;
    }
}
