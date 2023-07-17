package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor

@Getter
@Setter

public class Riviste extends Elemento {
	@Enumerated(EnumType.STRING)
	private TipoRivista tipoRivista;

	public Riviste(Long codiceISBN, String titolo, LocalDate annoPubblicazione, int numeroPagine,
			TipoRivista tipoRivista) {
		super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
		this.setTipoRivista(tipoRivista);
	}

	@Override
	public String toString() {
		return "Rivista [periodicita=" + tipoRivista + ", codiceISBN()=" + getCodiceISBN() + ", Titolo=" + getTitolo()
				+ ", anno pubblicazione" + getAnnoPubblicazione() + ", numero pagine" + getNumeroPagine() + "]";
	}
}
