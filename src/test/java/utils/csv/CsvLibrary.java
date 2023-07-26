package utils.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

public class CsvLibrary {

	private Random random;
	private static Logger logger = LogManager.getLogger(CsvLibrary.class);

	public List<List<String>> getCsvData(String filePathg) {
		ArrayList arrayList = new ArrayList();
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(filePathg));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				arrayList.add(nextLine);
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public List<List<String>> getRowDetails(List<List<String>> listOfData, String clmName, List<String> itemList) {
		List<List<String>> rowDetails = new ArrayList();
		Integer pointer = null;
		boolean flag = false;
		try {
			for (int i = 0; i < listOfData.get(0).size(); i++) {
				if (listOfData.get(0).get(i).equalsIgnoreCase(clmName)) {
					pointer = i;
					flag = true;
					break;
				}
			}
			List<String> data = new ArrayList();

			for (int j = 0; j < listOfData.size(); j++) {
				if (itemList.contains(listOfData.get(j).get(pointer))) {
					for (int l = 1; l < listOfData.get(0).size(); l++)
						data.add(listOfData.get(j).get(l));
				}

				rowDetails.add(data);
				data.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowDetails;
	}

	public List<List<String>> getColumnDetails(List<List<String>> listOfData, String clmName, List<String> itemList) {
		List<List<String>> rowDetails = new ArrayList();
		Integer pointer = null;
		boolean flag = false;
		try {
			for (int i = 0; i < listOfData.get(0).size(); i++) {
				if (listOfData.get(0).get(i).equalsIgnoreCase(clmName)) {
					pointer = i;
					flag = true;
					break;
				}
			}
			List<String> data = new ArrayList();

			for (int j = 0; j < listOfData.size(); j++) {
				data.add(listOfData.get(j).get(pointer));
			}

			rowDetails.add(data);
			data.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowDetails;
	}

	public List<List<String>> readCsvFile(String file) {
		List<List<String>> csvData = new ArrayList<>();
		try {
			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;
			Integer pointer = 0;

			while ((nextRecord = csvReader.readNext()) != null) {
				csvData.add(new ArrayList<String>());
				for (String cell : nextRecord) {
					csvData.get(pointer).add(cell);
				}
				pointer++;
			}
			filereader.close();
			csvReader.close();
			return csvData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csvData;
	}

	public void writeCsvFile(String filePath, List<List<String>> data) {
		File file = new File(filePath);
		try {
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile);

			List<String[]> listOfArray = new ArrayList<String[]>();
			for (List<String> element : data) {
				String[] stringArray = new String[element.size()];
				listOfArray.add(element.toArray(stringArray));
			}
			writer.writeAll(listOfArray);

			writer.close();
			outputfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeCsvFileData(String filePath, List<List<String>> data, ArrayList<String> conditionData) {
		File file = new File(filePath);
		try {
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile);

			List<String[]> listOfArray = new ArrayList<String[]>();
			String[] stringArray = null;
			for (List<String> element : data) {
				for (String ele : element) {
					if (ele.equalsIgnoreCase(conditionData.get(0)) || ele.equalsIgnoreCase(conditionData.get(1))) {
						stringArray = new String[element.size()];
						listOfArray.add(element.toArray(stringArray));
					}
				}
			}
			writer.writeAll(listOfArray);

			writer.close();
			outputfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeCsvFileDatas(String filePath, List<List<String>> data, ArrayList<String> conditionData,
			String newValue, int replaceIndex) {
		File file = new File(filePath);
		Integer objectIndexNo = data.get(0).indexOf(conditionData.get(1));
		try {
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			List<String[]> listOfArray = new ArrayList<String[]>();
			String[] stringArray = null;
			for (List<String> element : data) {
				for (String ele : element) {
					if (ele.equalsIgnoreCase(conditionData.get(0)) || ele.equalsIgnoreCase(conditionData.get(1))) {
						stringArray = new String[element.size()];
						listOfArray.add(element.toArray(stringArray));
					}
				}
			}
			for (int i = 1; i < listOfArray.size(); i++) {
				String[] a = listOfArray.get(i);
				System.out.println(a[replaceIndex]);
				a[replaceIndex] = newValue;
				System.out.println(a[replaceIndex]);
			}
			writer.writeAll(listOfArray);

			writer.close();
			outputfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeCsvFileRandomDatas(String filePath, List<List<String>> data, ArrayList<String> conditionData,
			String newValue, int replaceIndex) {
		File file = new File(filePath);
		Integer objectIndexNo = data.get(0).indexOf(conditionData.get(1));
		try {
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile);

			List<String[]> listOfArray = new ArrayList<String[]>();
			String[] stringArray = null;
			for (List<String> element : data) {
				for (String ele : element) {
					if (ele.equalsIgnoreCase(conditionData.get(0)) || ele.equalsIgnoreCase(conditionData.get(1))) {
						stringArray = new String[element.size()];
						listOfArray.add(element.toArray(stringArray));
					}
				}
			}
			for (int i = 1; i < listOfArray.size(); i++) {
				random = new Random();

				String[] a = listOfArray.get(i);
				System.out.println(a[replaceIndex]);
				a[replaceIndex] = newValue + random.nextInt(20000) + "]";
				System.out.println(a[replaceIndex]);
			}
			writer.writeAll(listOfArray);

			writer.close();
			outputfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeDataToCsvFile(String filePath, List<List<String>> data) throws IOException {
		File file = new File(filePath);
		if (file.exists()) {
			if (file.delete()) {
				logger.info("File Successfully Deleted");
			} else {
				logger.info("Failed to delete the file");
			}
		}
		file.createNewFile();
		try {
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			List<String[]> listOfArray = new ArrayList<String[]>();
			for (List<String> element : data) {
				String[] stringArray = new String[element.size()];
				listOfArray.add(element.toArray(stringArray));
			}
			writer.writeAll(listOfArray);

			writer.close();
			outputfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeDataCsvFile(String filePath, List<List<String>> data) {
		File file = new File(filePath);
		try {
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			List<String[]> listOfArray = new ArrayList<String[]>();
			for (List<String> element : data) {
				String[] stringArray = new String[element.size()];
				listOfArray.add(element.toArray(stringArray));
			}
			writer.writeAll(listOfArray);

			writer.close();
			outputfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public List<String> getCsvData(String file, String columnName) {
		List<List<String>> data = getCsvData(file);
		int pointer = 0;
		boolean flag = false;
		for (int i = 0; i < data.get(0).size(); i++) {
			if (data.get(0).get(i).equalsIgnoreCase(columnName)) {
				pointer = i;
				flag = true;
				break;
			}
		}
		List<String> columnData = new ArrayList<>();
		if (flag) {
			for (int j = 0; j < data.size(); j++) {
				columnData.add(data.get(j).get(pointer));
			}
		}
		return columnData;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	public class CsvData {
		private Integer noOfRows;
		private Integer noOfColumns;
		private List<List<String>> data;
	}

	public List<String> getCsvData(Integer columnNo, String file) {
		try {
			List<List<String>> data = getCsvData(file);
			int pointer = columnNo;
			List<String> columnData = new ArrayList<>();
			for (int j = 0; j < data.size(); j++) {
				columnData.add(data.get(j).get(pointer));
			}
			return columnData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<List<String>> getCsvData(String file, String columnName, List<String> expDataList) {
		try {
			if (expDataList.size() > 0)
				expDataList.add(0, columnName);
			else
				throw new Exception("Expected Data List Is Empty");
			List<List<String>> data = getCsvData(file);
			int pointer = 0;
			boolean flag = false;
			for (int i = 0; i < data.get(0).size(); i++) {
				if (data.get(0).get(i).equalsIgnoreCase(columnName)) {
					pointer = i;
					flag = true;
					break;
				}
			}
			List<List<String>> itemDetails = new ArrayList<>();
			if (flag) {
				outer: for (String expData : expDataList) {
					for (int j = 0; j <= data.size(); j++) {
						String expDataFromCSV = data.get(j).get(pointer);
						if ((expData.equalsIgnoreCase(expDataFromCSV))) {
							itemDetails.add(data.get(j));
							break;
						}
						if (expDataList.size() == itemDetails.size())
							break outer;
					}
				}
			}
			return itemDetails;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
