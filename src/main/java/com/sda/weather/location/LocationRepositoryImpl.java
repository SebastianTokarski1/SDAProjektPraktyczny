package com.sda.weather.location;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LocationRepositoryImpl implements LocationRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Location save(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(location);

        transaction.commit();
        session.close();

        return location;
    }

    @Override
    public List<Location> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Location> locations = session.createQuery("FROM Location").getResultList();

        transaction.commit();
        session.close();
        return locations;
    }

    @Override
    public Optional<Location> getLocationById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Optional<Location> location = session.createQuery("SELECT l FROM Location l WHERE l.id = :id", Location.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findAny();

        transaction.commit();
        session.close();

        return location;
    }
}
