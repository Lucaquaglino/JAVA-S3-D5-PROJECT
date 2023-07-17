package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Prestito {
	@Id
	@GeneratedValue
	private Long idPrestito;

	@ManyToOne
	private Utente utente;
	@ManyToOne
	private Elemento elementoPrestato;
	private LocalDate dataInizioPrestito;
	private LocalDate dataRestituzionePrevista;
	private LocalDate dataRestituzioneEffettiva;

	public Prestito(Utente utente, Elemento elementoPrestato, LocalDate dataInizioPrestito,
			LocalDate dataRestituzioneEffettiva) {

		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;

		this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);

		if (dataRestituzioneEffettiva != null) {
			this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
			this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
		}
	}

	@Override
	public String toString() {
		return "Prestito [utente=" + utente + ", prodotto=" + elementoPrestato + ", data Inizio Prestito="
				+ dataInizioPrestito + ", dataRestituzionePrevista=" + dataRestituzionePrevista
				+ ", data Restituzione Effettiva=" + dataRestituzioneEffettiva + "]";
	}
}
