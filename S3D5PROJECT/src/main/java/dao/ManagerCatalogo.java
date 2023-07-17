
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Elemento;
import entities.Prestito;
import entities.Utente;

public class ManagerCatalogo {
	private final EntityManager em;

	public ManagerCatalogo(EntityManager em) {
		this.em = em;
	}

//Aggiungo prodtto
	public void addProdotto(Elemento e) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(e);

		t.commit();
		System.out.println("Prodotto salvato!");
	}

//Aggiungo utente
	public void addUtente(Utente u) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(u);

		t.commit();
		System.out.println("Utente salvato!");
	}

//Aggiungo prestito
	public void addPrestito(Prestito p) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		if (!em.contains(p.getElementoPrestato())) {
			em.persist(p.getElementoPrestato());
		}

		em.persist(p);

		t.commit();
		System.out.println("Prestito salvato!");
	}

//Cancello prodotto tramite ISBN
	public void cancellaTramiteIsbnCode(Long isbnCode) {
		Elemento elemento = em.find(Elemento.class, isbnCode);
		if (elemento != null) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.remove(elemento);

			t.commit();
			System.out.println("Prodotto " + isbnCode + " eliminato!");
		} else {
			System.out.println("Prodotto non trovato");
		}
	}

//Cerco prodotto tramite ISBN
	public Elemento cercaTramiteIsbnCode(Long isbn) {
		return em.find(Elemento.class, isbn);
	}

//Cerco per anno di pubblicazione
	public List<Elemento> cercaPerAnnoPubblicazione(int anno) {
		TypedQuery<Elemento> query = em.createQuery(
				"SELECT e FROM Elemento e WHERE EXTRACT(YEAR FROM e.annoPubblicazione) = :anno", Elemento.class);
		query.setParameter("anno", anno);
		return query.getResultList();
	}

//Cerco tramite autore
	public List<Elemento> cercaTramiteAutore(String autore) {
		TypedQuery<Elemento> query = em.createQuery("SELECT e FROM Elemento e WHERE e.autore = :autore",
				Elemento.class);
		query.setParameter("autore", autore);
		return query.getResultList();
	}

//Cerco tramite titolo
	public List<Elemento> cercaTramiteTitolo(String titolo) {
		TypedQuery<Elemento> query = em.createQuery("SELECT e FROM Elemento e WHERE e.titolo LIKE :titolo",
				Elemento.class);
		query.setParameter("titolo", "%" + titolo + "%");
		return query.getResultList();
	}

//Cerco i prestiti degli utenti
	public List<Prestito> cercaPrestitiUtente(int i) {
		TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tessera",
				Prestito.class);
		query.setParameter("tessera", i);
		return query.getResultList();
	}

//Cerco i prestiti scaduti degli utenti
	public List<Prestito> cercaPrestitiScaduti() {
		TypedQuery<Prestito> query = em.createQuery(
				"SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL",
				Prestito.class);
		return query.getResultList();
	}
}
