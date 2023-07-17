package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static final EntityManagerFactory entityManagerFactory;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("S3D5PROJECT");
		} catch (Throwable e) {
			System.err.println("Creazione dell'EntityManagerFactory fallita" + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}