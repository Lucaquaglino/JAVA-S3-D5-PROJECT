package entities;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Libri extends Elemento {
	private String autore;
	private String genere;

	public Libri(Long codiceISBN, String titolo, LocalDate annoPubblicazione, int numeroPagine, String autore,
			String genere) {
		super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "Libro [autore=" + autore + ", genere=" + genere + ", codiceISBN=" + getCodiceISBN() + ", titolo="
				+ getTitolo() + ", anno pubblicazione=" + getAnnoPubblicazione() + ", numero pagine="
				+ getNumeroPagine() + "]";
	}

}
