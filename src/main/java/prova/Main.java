package prova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.opencsv.exceptions.CsvException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Prenotazione;
import model.Utente;
import model.Viaggio;
import model.fileQuery;

public class Main {
	private static Utente mainUser;
	private static List<Viaggio> viaggi;
	private static List<Utente> utenti;
	private static List<Prenotazione> prenotazioni;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		viaggi = fileQuery.leggiCsvViaggi();
		utenti = fileQuery.leggiCsvUtenti();
		prenotazioni = fileQuery.leggiCsvPrenotazioni();
		menu();
		consoleRead();

	}

	public static void consoleRead() {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String input;
				try {
					while ((input = reader.readLine()) != null) {
						switch (input) {
						case "1": {
							Viaggio.printTable(fileQuery.leggiCsvViaggi());
							break;
						}
						case "2": {
							if (mainUser == null) {
								System.out.println("Devi prima creare un utente, premi 4 per aggiungerti");
							} else {

								prenota();
							}
							break;
						}
						case "3": {
							cancellaPrenotazione();
							break;
						}
						case "4": {
							aggiungiUtente(reader);
							break;
						}
						case "5": {
							fileQuery.exportCSV(viaggi);
							break;
						}
						case "0": {
							System.out.println("Programma terminato");
							System.exit(0);
						}
						}
					}
				} catch (IOException e) {

					System.out.println(e);
				}
			}
		});

		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void menu() {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Comando\t\tDescrizione");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("1\t\tPer visualizzare tutte le attività all’interno del sistema");
		System.out.println("2\t\tPer Prenotare un’attività");
		System.out.println("3\t\tPer Disdire la prenotazione di un viaggio");
		System.out.println("4\t\tPer ggiungere nuovo utente");
		System.out.println("5\t\tPer esportare un file con i viaggi ancora disponibili");
		System.out.println("0\t\tPer uscire");

	}

	public static void prenota() {
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));

		try {
			int idViaggio = 0;
			boolean exist = false;
			while (!exist) {
				System.out.print("Inseri l'Id del viaggio che si vuole prenotare: ");
				try {
					idViaggio = Integer.parseInt(reader1.readLine());

					for (Viaggio v : viaggi) {
						if (idViaggio == Integer.parseInt(v.getId().trim())) {
							exist = true;
							if (!v.getDisponibile()) {
								System.out.println("Viaggio non disponibile");
								exist = false;
							}
						}

					}
					if (!exist) {
						System.out
								.println("Il viaggio che si vuole prenotare non esiste o non è al momento disponibile");
					}

				} catch (IOException e) {
					System.out.println(e);
				}

			}
			Date data = null;
			boolean dataValida = false;
			while (!dataValida) {
				System.out.print("Inserisci la data di partenza (formato: dd/MM/yy): ");
				try {
					String dataInput = reader1.readLine();
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					formatter.setLenient(false);
					data = formatter.parse(dataInput);
					dataValida = true;
					Date timeStamp = new Date();
					DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
					String s = formatoData.format(timeStamp);
					if (timeStamp.before(data)) {
						dataValida = true;
					} else {
						System.out.println("Deve essere una data nel futuro.");
					}

				} catch (IOException e) {
					System.out.println(e);
				} catch (ParseException e) {
					System.out.println("Formato data non valido. Riprova.");
				}
			}

			System.out.print("Inserisci la durata del viaggio in ore ");
			String durata = "";
			try {
				durata = reader1.readLine();
			} catch (Exception e) {

			}
			System.out.print("Inserisci la città di partenza del viaggio ");
			String partenza = "";
			try {
				partenza = reader1.readLine();
			} catch (Exception e) {

			}

			System.out.print("Inserisci la città di arrivo del viaggio ");
			String arrivo = "";
			try {
				arrivo = reader1.readLine();
			} catch (Exception e) {

			}
			SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormattata = outputFormatter.format(data);

			String disponibile = "NO";
			String[] line = { "" + idViaggio, dataFormattata.toString(), durata, partenza, arrivo,
					disponibile + ";;;;;;;;;;;;;;;;;;;" };
			int[] lineModify = { idViaggio };
			if (fileQuery.modify(lineModify, line, fileQuery.getViaggiPath())) {

				int idPrenotazione = Prenotazione.getNextId(prenotazioni);
				String[] prenotazione = { "" + idPrenotazione, "" + idViaggio, mainUser.getId() };

				try {

					fileQuery.addRowToCSVPrenotazioni(prenotazione);

				} catch (IOException e) {
					System.out.print(e);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public static void aggiungiUtente(BufferedReader reader) {
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));

		try {
			int id = 0;
			boolean isInt = false;
			while (!isInt) {
				System.out.print("Inserisci l'ID: ");
				try {
					id = Integer.parseInt(reader1.readLine());
					isInt = true;
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("Formato data non valido. Id deve essere un numero Riprova.");

				}

			}

			System.out.print("Inserisci il nome: ");
			String nome = reader1.readLine();

			System.out.print("Inserisci il cognome: ");
			String cognome = reader1.readLine();

			Date dataDiNascita = null;
			boolean dataValida = false;
			while (!dataValida) {
				System.out.print("Inserisci la data di nascita (formato: dd/MM/yy): ");
				try {
					String dataInput = reader.readLine();
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					formatter.setLenient(false);
					dataDiNascita = formatter.parse(dataInput);
					dataValida = true;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("Formato data non valido. Riprova.");
				}
			}

			System.out.print("Inserisci l'indirizzo: ");
			String indirizzo = reader1.readLine();

			System.out.print("Inserisci il numero di documento: ");
			String numeroDocumento = reader1.readLine();

			SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormattata = outputFormatter.format(dataDiNascita);

			mainUser = new Utente("" + id, nome, cognome, "" + dataDiNascita, indirizzo, numeroDocumento);
			String[] data = { "" + id, nome, cognome, "" + dataFormattata, indirizzo, numeroDocumento };

			try {
				fileQuery.addRowToCSVUtente(data);
				System.out.println("Riga aggiunta al file CSV con successo.");
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("\nDati inseriti:");
			System.out.println("ID: " + id);
			System.out.println("Nome: " + nome);
			System.out.println("Cognome: " + cognome);
			System.out.println("Data di nascita: " + "" + dataFormattata);
			System.out.println("Indirizzo: " + indirizzo);
			System.out.println("Numero di documento: " + numeroDocumento);
			System.out.println("Premere 1 per tornare al menù principale");
			if (reader1.readLine().equals("1")) {
				menu();
				consoleRead();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader1.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	public static void cancellaPrenotazione() {

		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));

		try {
			String idPrenotazione = "";
			boolean exist = false;
			while (!exist) {
				System.out.print("Inseri l'Id del prenotazione che si vuole cancellare: ");
				try {
					idPrenotazione = reader1.readLine();

					for (Prenotazione p : prenotazioni) {
						if (idPrenotazione.equals(p.getId().trim())) {
							exist = true;

							break;
						}

					}
					if (!exist) {
						System.out.println("Il prenotazione che si vuole cancellare non esiste ");
					}

				} catch (IOException e) {
					System.out.println(e);
				}
			}

			int removeIndex = 0;
			String idViaggio = "";
			for (int i = 0; i < prenotazioni.size(); i++) {

				if (prenotazioni.get(i).getId().equals(idPrenotazione)) {
					removeIndex = i;
					System.out.println(i);
					idViaggio = prenotazioni.get(i).getIdViaggio();
				}
			}
			fileQuery.remove(++removeIndex, fileQuery.getPrenotazioniPath());
			for (int i = 0; i < viaggi.size(); i++) {
				if (viaggi.get(i).getId().equals(idViaggio)) {
					int indice[] = { i + 1 };
					Viaggio v = viaggi.get(i);
					String[] viaggio = { v.getId(), v.getData(), v.getDurata(), v.getPartenza(), v.getArrivo(), "SI" };
					fileQuery.modify(indice, viaggio, fileQuery.getViaggiPath());
					break;
				}

			}

		} catch (CsvException e) {
			System.out.print(e);
		}

	}
}
