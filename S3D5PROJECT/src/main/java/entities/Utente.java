package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Utente {
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	@Id
	private int numeroTessera;
//
//	public Utente(String nome, String cognome, LocalDate dataNascita, int numeroTessera) {
//		// TODO Auto-generated constructor stub
//		this.nome = nome;
//		this.cognome = cognome;
//		this.dataNascita = dataNascita;
//		this.numeroTessera = numeroTessera;
//	}

	@Override
	public String toString() {
		return "Utente [numeroTessera=" + numeroTessera + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita="
				+ dataNascita + "]";
	}
}
