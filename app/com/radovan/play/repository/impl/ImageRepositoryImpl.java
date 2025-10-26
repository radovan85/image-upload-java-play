package com.radovan.play.repository.impl;

import com.radovan.play.entity.ImageEntity;
import com.radovan.play.repository.ImageRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Singleton
public class ImageRepositoryImpl implements ImageRepository {

    private SessionFactory sessionFactory;

    @Inject
    private void initialize(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private <T> T withSession(Function<Session, T> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                T returnValue = function.apply(session);
                tx.commit();
                return returnValue;
            } catch (Exception exc) {
                tx.rollback();
                throw exc;
            }
        }
    }

    @Override
    public ImageEntity save(ImageEntity imageEntity) {
        return withSession(session -> {
            if (imageEntity.getId() == null) {
                session.persist(imageEntity);
            } else {
                session.merge(imageEntity);
            }

            session.flush();
            return imageEntity;
        });
    }

    @Override
    public Optional<ImageEntity> findById(Long imageId) {
        return withSession(session -> {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ImageEntity> query = cb.createQuery(ImageEntity.class);
            Root<ImageEntity> root = query.from(ImageEntity.class);
            query.where(cb.equal(root.get("id"), imageId));
            List<ImageEntity> resultList = session.createQuery(query).getResultList();
            return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
        });
    }

    @Override
    public List<ImageEntity> findAll() {
        return withSession(session -> {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ImageEntity> query = cb.createQuery(ImageEntity.class);
            Root<ImageEntity> root = query.from(ImageEntity.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        });
    }

    @Override
    public void deleteById(Long imageId) {
        withSession(session -> {
            ImageEntity imageEntity = session.find(ImageEntity.class, imageId);
            if (imageEntity != null) {
                session.remove(imageEntity);
            }

            return null;
        });
    }
}
