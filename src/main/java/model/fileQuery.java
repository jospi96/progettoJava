package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class fileQuery {

	private static final String VIAGGI_PATH = "./resource/viaggi.csv";
	private static final String UTENTI_PATH = "./resource/utenti.csv";
	private static final String PRENOTAZIONI_PATH = "./resource/prenotazioni.csv";

	public static String getUserPath() {
		return UTENTI_PATH;
	}

	public static String getViaggiPath() {
		return VIAGGI_PATH;
	}

	public static String getPrenotazioniPath() {
		return PRENOTAZIONI_PATH;
	}

	public static List<Viaggio> leggiCsvViaggi() {
		List<Viaggio> lines = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(VIAGGI_PATH))) {
			String[] line;
			try {
				while ((line = reader.readNext()) != null) {
					for (int i = 0; i < line.length; i++) {
						String[] viaggio = line[i].split(";");
						if (viaggio.length != 0) {
							lines.add(new Viaggio(viaggio));
							System.out.print(viaggio.length);
						}
					}

				}
			} catch (CsvValidationException e) {
				System.out.print(e);

			}
		} catch (IOException e) {
			System.out.print(e);

		}
		lines.remove(0);
		return lines;
	}

	public static List<Utente> leggiCsvUtenti() {
		List<Utente> lines = new ArrayList<>();

		try (CSVReader reader = new CSVReader(new FileReader(UTENTI_PATH))) {
			String[] line;
			try {
				while ((line = reader.readNext()) != null) {
					for (int i = 0; i < line.length; i++) {
						String[] utente = line[i].split(";");
						if (utente.length != 0) {
							lines.add(new Utente(utente));
						}
					}

				}
			} catch (CsvValidationException e) {
				e.printStackTrace();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		lines.remove(0);

		return lines;
	}

	public static List<Prenotazione> leggiCsvPrenotazioni() {
		List<Prenotazione> lines = new ArrayList<>();

		try (CSVReader reader = new CSVReader(new FileReader(PRENOTAZIONI_PATH))) {
			String[] line;
			try {
				while ((line = reader.readNext()) != null) {
					for (int i = 0; i < line.length; i++) {
						String[] prenotazione = line[i].split(";");
						if (prenotazione.length != 0) {
							lines.add(new Prenotazione(prenotazione));
						}
					}

				}
			} catch (CsvValidationException e) {
				e.printStackTrace();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		lines.remove(0);
		return lines;
	}

	public static void scriviCSV(String filePath, List<String[]> lines) {
		try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
			writer.writeAll(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static void addRowToCSVUtente(String[] rowData) throws IOException {
		FileWriter fileWriter = new FileWriter(UTENTI_PATH, true);

		CSVWriter csvWriter = new CSVWriter(fileWriter, ';', CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

		csvWriter.writeNext(rowData);

		fileWriter.close();
	}

	@SuppressWarnings("resource")
	public static void addRowToCSVViaggi(String[] rowData) throws IOException {
		FileWriter fileWriter = new FileWriter(VIAGGI_PATH, true);
		CSVWriter csvWriter = new CSVWriter(fileWriter, ';', CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		csvWriter.writeNext(rowData);
		fileWriter.close();
	}

	@SuppressWarnings("resource")
	public static void addRowToCSVPrenotazioni(String[] rowData) throws IOException {
		FileWriter fileWriter = new FileWriter(PRENOTAZIONI_PATH, true);
		CSVWriter csvWriter = new CSVWriter(fileWriter, ';', CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		csvWriter.writeNext(rowData);
		fileWriter.close();
	}

	public static boolean modify(int[] rowsToModify, String[] newValues, String path) throws CsvException {
		String csvFilePath = path;
		List<String[]> rows = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
			rows = reader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < rowsToModify.length; i++) {
			int rowIndex = rowsToModify[i];
			if (rowIndex >= 0 && rowIndex < rows.size()) {
				rows.set(rowIndex, newValues);
			}
		}

		try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath), ';', CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
			writer.writeAll(rows);
			System.out.println("Modifiche salvate nel file CSV.");
			return true;
		} catch (IOException e) {
			System.out.print(e);
			return false;
		}
	}

	public static boolean remove(int rowsDrop, String path) throws CsvException {
		String csvFilePath = path;
		List<String[]> rows = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
			rows = reader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] f = rows.get(rowsDrop);
		for (String h : f) {
			System.out.print(h);
		}
		rows.remove(rowsDrop);
		try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath), ';', CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
			writer.writeAll(rows);
			System.out.println("Modifiche salvate nel file CSV.");
			return true;
		} catch (IOException e) {
			System.out.print(e);
			return false;
		}
	}

	public static void exportCSV(List<Viaggio> viaggi) throws IOException {
		Date timeStamp = new Date();
		SimpleDateFormat outputFormatter = new SimpleDateFormat("dd_MM_yyyy");
		String dataFormattata = outputFormatter.format(timeStamp);
		FileWriter myWriter = new FileWriter("viaggii_" + dataFormattata + ".csv");
		myWriter.append("Id");
		myWriter.append(";");
		myWriter.append("Data");
		myWriter.append(";");
		myWriter.append("Durata");
		myWriter.append(";");
		myWriter.append("Partenza");
		myWriter.append(";");
		myWriter.append("Arrivo");
		myWriter.append(";");
		myWriter.append('\n');
		for (Viaggio v : viaggi) {
			if (v.getDisponibile()) {
				myWriter.append(v.getId());
				myWriter.append(";");
				myWriter.append(v.getData());
				myWriter.append(";");
				myWriter.append(v.getDurata());
				myWriter.append(";");
				myWriter.append(v.getPartenza());
				myWriter.append(";");
				myWriter.append(v.getArrivo());
				myWriter.append(";");
				myWriter.append('\n');
			}

		}
		myWriter.flush();

	}

}