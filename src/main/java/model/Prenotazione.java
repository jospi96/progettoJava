package model;

import java.util.List;

public class Prenotazione {
	private String id, idViaggio, IdUtente;

	public Prenotazione() {
	}

	public Prenotazione(String[] args) {
		this.setId(args[0]);
		this.setIdViaggio(args[1]);
		this.setIdUtente(args[2]);
	}

	public Prenotazione(String id, String idViaggio, String idUtente) {
		this.setId(idUtente);
		this.setIdViaggio(idViaggio);
		this.setIdUtente(idUtente);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdViaggio() {
		return idViaggio;
	}

	public void setIdViaggio(String idViaggio) {
		this.idViaggio = idViaggio;
	}

	public String getIdUtente() {
		return IdUtente;
	}

	public void setIdUtente(String idUtente) {
		IdUtente = idUtente;
	}

	@Override
	public String toString() {
		return id + " " + idViaggio + " " + IdUtente + "";
	}

	public static int getNextId(List<Prenotazione> pre) {
		if (pre.isEmpty()) {
			return 0;
		}
		int id = 0;
		for (Prenotazione prenot : pre) {
			if (Integer.parseInt(prenot.getId()) > id) {
				id = Integer.parseInt(prenot.getId());
			}
		}
		return ++id;
	}

	public static void printTable(List<Prenotazione> prenotazioni) {
		int larghezzaColonna1 = 15;
		int larghezzaColonna2 = 15;
		int larghezzaColonna3 = 15;

		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.format("%-" + larghezzaColonna1 + "s", "Id");
		System.out.format("%-" + larghezzaColonna2 + "s", "Id Utente");
		System.out.format("%-" + larghezzaColonna3 + "s", "Id Viaggio");

		System.out.println("");
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
		for (Prenotazione p : prenotazioni) {

			System.out.format("%-" + larghezzaColonna1 + "s", p.getId());
			System.out.format("%-" + larghezzaColonna2 + "s", p.getIdUtente());
			System.out.format("%-" + larghezzaColonna3 + "s", p.getIdViaggio());
			System.out.println();
		}
	}

}
