import entity.CinemaEntity;
import entity.EmployeeEntity;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            TypedQuery<CinemaEntity> cinemaByCityQuery = entityManager.createNamedQuery("cinema.byCity", CinemaEntity.class);
            cinemaByCityQuery.setParameter(1, "Wroclaw");
            for (CinemaEntity cinema : cinemaByCityQuery.getResultList()) {
                System.out.println(cinema);
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
