package model;

import java.util.List;

public class Utente {
	private String id;
	private String name;
	private String surname;
	private String address;
	private String dateOfBirth;
	private String idDocument;

	public Utente(String id, String nome, String cognome, String dataDiNascita, String indirizzo,
			String numeroDocumento) {
		this.setId(id);
		this.setName(nome);
		this.setSurname(cognome);
		this.setDateOfBirth(dataDiNascita);
		this.setAddress(indirizzo);
		this.setIdDocument(numeroDocumento);
	}

	public Utente(String[] arg) {
		this.setId(arg[0]);
		this.setName(arg[1]);
		this.setSurname(arg[2]);
		this.setDateOfBirth(arg[3]);
		this.setAddress(arg[4]);
		this.setIdDocument(arg[5]);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;

	}

	public String getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(String idDocument) {
		this.idDocument = idDocument;

	}

	public static void printTable(List<Utente> utenti) {
		int larghezzaColonna1 = 8;
		int larghezzaColonna2 = 15;
		int larghezzaColonna3 = 15;
		int larghezzaColonna4 = 20;
		int larghezzaColonna5 = 30;
		int larghezzaColonna6 = 20;
		System.out.println(
				"------------------------------------------------------------------------------------------------------------");
		System.out.format("%-" + larghezzaColonna1 + "s", "ID");
		System.out.format("%-" + larghezzaColonna2 + "s", "Nome");
		System.out.format("%-" + larghezzaColonna3 + "s", "Cognome");
		System.out.format("%-" + larghezzaColonna4 + "s", "Data-di-nascita");
		System.out.format("%-" + larghezzaColonna5 + "s", "Indirizzo");
		System.out.format("%-" + larghezzaColonna6 + "s", "Documento-ID");
		System.out.println("");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------");
		for (Utente u : utenti) {
			System.out.format("%-" + larghezzaColonna1 + "s", u.getId());
			System.out.format("%-" + larghezzaColonna2 + "s", u.getName());
			System.out.format("%-" + larghezzaColonna3 + "s", u.getSurname());
			System.out.format("%-" + larghezzaColonna4 + "s", u.getDateOfBirth());
			System.out.format("%-" + larghezzaColonna5 + "s", u.getAddress());
			System.out.format("%-" + larghezzaColonna6 + "s", u.getIdDocument());
			System.out.println();
		}

	}

}
