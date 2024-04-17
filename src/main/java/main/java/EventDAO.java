package main.java;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import org.primefaces.model.ScheduleEvent;

import java.util.*;

public class EventDAO {
    @Getter
    private EntityManagerFactory entityManagerFactory;

    public EventDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public void createEvent(ScheduleEvent event) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<ScheduleEvent> getAllEvents() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        List<ScheduleEvent> events = new List<ScheduleEvent>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<ScheduleEvent> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(ScheduleEvent scheduleEvent) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends ScheduleEvent> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends ScheduleEvent> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public ScheduleEvent get(int index) {
                return null;
            }

            @Override
            public ScheduleEvent set(int index, ScheduleEvent element) {
                return null;
            }

            @Override
            public void add(int index, ScheduleEvent element) {

            }

            @Override
            public ScheduleEvent remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<ScheduleEvent> listIterator() {
                return null;
            }

            @Override
            public ListIterator<ScheduleEvent> listIterator(int index) {
                return null;
            }

            @Override
            public List<ScheduleEvent> subList(int fromIndex, int toIndex) {
                return List.of();
            }
        };
        try {
            events = entityManager.createQuery("SELECT e FROM ScheduleEvent e", ScheduleEvent.class).getResultList();
        } catch (Exception e) {
            System.out.println("No Event's");
        }
        entityManager.close();
        return events;
    }

    public void deleteEvents() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        for (ScheduleEvent ev:getAllEvents()) {
            entityManager.remove(ev);
        }
        entityManager.close();
    }
}
