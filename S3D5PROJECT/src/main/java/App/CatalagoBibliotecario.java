package App;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.ManagerCatalogo;
import entities.Elemento;
import entities.Libri;
import entities.Prestito;
import entities.Riviste;
import entities.TipoRivista;
import entities.Utente;
import utils.JpaUtil;

public class CatalagoBibliotecario {
	static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		ManagerCatalogo catalogo = new ManagerCatalogo(em);
//ISTANZE
		Utente utente1 = new Utente("Aldo", "Baglio", LocalDate.of(1980, 03, 03), 1);
		Utente utente2 = new Utente("Giacomo", "Poretti", LocalDate.of(1950, 07, 04), 2);

		Libri libro1 = new Libri(1234L, "Harry Potter", LocalDate.of(2002, 06, 06), 600, "JKROWLING", "Fantasy");
		Libri libro2 = new Libri(5678L, "Ciao Giovane", LocalDate.of(2000, 01, 02), 800, "Luca Quaglino", "Horror");

		Riviste rivista1 = new Riviste(656565L, "Forbes", LocalDate.of(2000, 3, 8), 78, TipoRivista.SETTIMANALE);
		Riviste rivista2 = new Riviste(56565656L, "Chi", LocalDate.of(2010, 02, 20), 124, TipoRivista.MENSILE);

//METODI
		catalogo.addUtente(utente1);
		catalogo.addUtente(utente2);

		catalogo.addProdotto(libro1);
		catalogo.addProdotto(libro2);
		catalogo.addProdotto(rivista1);
		catalogo.addProdotto(rivista2);

		catalogo.cancellaTramiteIsbnCode(1234L);

		List<Elemento> elementiAnno = catalogo.cercaPerAnnoPubblicazione(2000);
		System.out.println("PRODOTTI CON ANNO DI PUBBLICAZIONE SELEZIONATO:");
		System.out.println(elementiAnno);

		Elemento item = catalogo.cercaTramiteIsbnCode(1234L);
		System.out.println("PRODOTTO CON CODICE ISBN SELEZIONATO:");
		if (item != null) {

			System.out.println(item);
		} else {
			System.err.println("PRODOTTO NON TROVATO");
		}

		List<Elemento> elementiAutore = catalogo.cercaTramiteAutore("Luca Quaglino");
		System.out.println("PRESTITI CON AUTORE SELEZIONATO:");
		System.out.println(elementiAutore);

		List<Elemento> elementiTitolo = catalogo.cercaTramiteTitolo("Forbes");
		System.out.println("PRODOTTI CON TITOLO SELEZIONATO:");
		System.out.println(elementiTitolo);

		Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.of(2023, 4, 15), LocalDate.of(2023, 4, 18));
		Prestito prestito2 = new Prestito(utente2, rivista2, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 2));
		Prestito prestito3 = new Prestito(utente2, libro2, LocalDate.of(2022, 3, 1), null);
		catalogo.addPrestito(prestito1);
		catalogo.addPrestito(prestito2);
		catalogo.addPrestito(prestito3);
		List<Prestito> prestitiUtente = catalogo.cercaPrestitiUtente(1);
		System.out.println("PRESTITI UTENTE SCELTO:");
		System.out.println(prestitiUtente);

		List<Prestito> prestitiScadutiNonRestituiti = catalogo.cercaPrestitiScaduti();
		System.out.println("PRESTITI SCADUTI:");
		System.out.println(prestitiScadutiNonRestituiti);

		em.close();
		emf.close();
	}
}
