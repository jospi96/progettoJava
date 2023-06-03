package model;

import java.util.List;

public class Viaggio {
	private String id, data, durata, partenza, arrivo;
	private Boolean disponibile;

	public Viaggio(String[] args) {
		this.setId(args[0]);
		this.setData(args[1]);
		this.setDurata(args[2]);
		this.setPartenza(args[3]);
		this.setArrivo(args[4]);
		this.setDisponibile(args[5]);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public String getPartenza() {
		return partenza;
	}

	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}

	public String getArrivo() {
		return arrivo;
	}

	public void setArrivo(String arrivo) {
		this.arrivo = arrivo;
	}

	public Boolean getDisponibile() {
		return disponibile;
	}

	public void setDisponibile(String disponibile) {
		if (disponibile.equals("NO")) {
			this.disponibile = false;
		} else {
			this.disponibile = true;
		}
	}

	public static void printTable(List<Viaggio> viaggi) {
		int larghezzaColonna1 = 8;
		int larghezzaColonna2 = 15;
		int larghezzaColonna3 = 15;
		int larghezzaColonna4 = 20;
		int larghezzaColonna5 = 20;
		int larghezzaColonna6 = 20;
		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.format("%-" + larghezzaColonna1 + "s", "ID");
		System.out.format("%-" + larghezzaColonna2 + "s", "Data");
		System.out.format("%-" + larghezzaColonna3 + "s", "Partenza");
		System.out.format("%-" + larghezzaColonna4 + "s", "Durata");
		System.out.format("%-" + larghezzaColonna5 + "s", "Arrivo");
		System.out.format("%-" + larghezzaColonna6 + "s", "Disponibile");
		System.out.println("");
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
		for (Viaggio v : viaggi) {
			String disponibile = v.getDisponibile() ? "Si" : "NO";
			System.out.format("%-" + larghezzaColonna1 + "s", v.getId());
			System.out.format("%-" + larghezzaColonna2 + "s", v.getData());
			System.out.format("%-" + larghezzaColonna3 + "s", v.getPartenza());
			System.out.format("%-" + larghezzaColonna4 + "s", v.getDurata());
			System.out.format("%-" + larghezzaColonna5 + "s", v.getArrivo());
			System.out.format("%-" + larghezzaColonna6 + "s", disponibile);
			System.out.println();
		}
	}

	public static void elencoViaggi(List<Viaggio> viaggi) {
		System.out.println("Elenco Viaggi");
		for (Viaggio via : viaggi) {

			System.out.println(via);
		}
	}

	public static boolean checkStatus(Viaggio v) {
		if (v.getDisponibile()) {
			return true;
		}
		return false;
	}
}
