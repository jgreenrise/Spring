package com.example.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/upload1", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		// return "You can upload a file by posting to this same URL.";

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");

		Map<String, Object[]> data = new HashMap<String, Object[]>();
		data.put("1", new Object[] { "Emp No.", "Name", "Salary" });
		data.put("2", new Object[] { 1d, "John", 1500000d });
		data.put("3", new Object[] { 2d, "Sam", 800000d });
		data.put("4", new Object[] { 3d, "Dean", 700000d });

		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date)
					cell.setCellValue((Date) obj);
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
			}
		}

		try {
			FileOutputStream out = new FileOutputStream(new File("new.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("File created successfully");

		return null;

	}

	@RequestMapping(value = "/upload1", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				return "You successfully uploaded " + name + "!";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String crunchifyDisplayForm() {
		return "uploadfile";
	}
	
	private static List<List<HSSFCell>> cellGrid;

    public static void convertExcelToCsv(String inputFile, String outputFile) throws IOException {
        try {
            cellGrid = new ArrayList<List<HSSFCell>>();
            FileInputStream myInput = new FileInputStream(inputFile);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<?> rowIter = mySheet.rowIterator();

            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<?> cellIter = myRow.cellIterator();
                List<HSSFCell> cellRowList = new ArrayList<HSSFCell>();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    cellRowList.add(myCell);
                }
                cellGrid.add(cellRowList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File file = new File(outputFile);
        PrintStream stream = new PrintStream(file);
        for (int i = 0; i < cellGrid.size(); i++) {
            List<HSSFCell> cellRowList = cellGrid.get(i);
            for (int j = 0; j < cellRowList.size(); j++) {
                HSSFCell myCell = (HSSFCell) cellRowList.get(j);
                String stringCellValue = myCell.toString();
                stream.print(stringCellValue + ",");
            }
            stream.println("");
        }
    }

	@RequestMapping(value = "/savefiles", method = RequestMethod.POST)
	public String crunchifySave(@ModelAttribute("uploadForm") CrunchifyFileUpload uploadForm, Model map, MultipartHttpServletRequest request)
			throws IllegalStateException, IOException {
		String saveDirectory = "\\";

		ArrayList<MultipartFile> crunchifyFiles = (ArrayList<MultipartFile>) uploadForm.getFiles();
		ArrayList<String> shouldRevert = (ArrayList<String>) uploadForm.getRevert();
		ArrayList<String> isXandYdata = (ArrayList<String>) uploadForm.getXandy();
		ArrayList<Integer> passes = (ArrayList<Integer>) uploadForm.getPass();
		List<Float> divideByR = uploadForm.getDivideByR();
		
		
		List<String> fileNames = new ArrayList<String>();
		final Comparator<Reading> COMPARATOR = new Comparator<Reading>() {
			@Override
			public int compare(Reading t1, Reading t2) {
				return Float.compare(t1.getTime(), t2.getTime());
			}
		};
		Function<String, Reading> mapToReading = (line) -> {
			String[] p = line.split("\\s+");
			return new Reading(Float.valueOf(p[0].trim()), Float.valueOf(p[1].trim()), Float.valueOf(p[2].trim()), Float.valueOf(p[3].trim()));
		};

		System.out.println("Total files uploaded: " + fileNames.size());
		
		DateTimeFormatter format = null;	
		try {
			format = DateTimeFormatter.ofPattern("yyyy_MM_dd_hh_mm_ss_SSS");
		} catch (DateTimeException ex) {
			System.out.printf("%s can't be formatted!%n", LocalDateTime.now());
			ex.printStackTrace();
		}
		
		String excelMaxFileName = (LocalDateTime.now()).format(format) + "_output_max.xls";
		File fileMax = new File(excelMaxFileName);
		FileWriter fwMax = new FileWriter(fileMax.getAbsoluteFile());
		BufferedWriter bwMax = new BufferedWriter(fwMax);

		/**
		 * Loop through files
		 */
		if (null != crunchifyFiles && crunchifyFiles.size() > 0) {
			for (MultipartFile multipartFile : crunchifyFiles) {

				if (multipartFile == null)
					continue;

				//System.out.println("inDEX: " + crunchifyFiles.indexOf(multipartFile));

				String fileName = multipartFile.getOriginalFilename();
				if (!"".equalsIgnoreCase(fileName)) {
					
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("Sample sheet");
					bwMax.write("\n\n");

					// Convert file into list
					InputStream is = multipartFile.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					List<Reading> readings = br.lines().map(mapToReading).collect(Collectors.toList());

					// File file = new File("output_" +
					// currentDate.format(format) + ".xlsx");
					// System.out.println("File created: "+file.getName());

					// Revert
					System.out.println("\n REVERTING");
					if (shouldRevert != null && shouldRevert.size() > crunchifyFiles.indexOf(multipartFile)
							&& shouldRevert.get(crunchifyFiles.indexOf(multipartFile)) != null
							&& shouldRevert.get(crunchifyFiles.indexOf(multipartFile)).equalsIgnoreCase("on")) {
						for (int i = 0; i < readings.size(); i++) {
							Reading reading_x = readings.get(i);
							reading_x.setTime(reading_x.getTime() * -1);
							readings.set(i, reading_x);
							// System.out.println(reading_x);
						}
					}

					/**
					 * Divide by pass
					 */
					System.out.println("\n Dividing by passes");
					if (passes != null && passes.get(crunchifyFiles.indexOf(multipartFile)) != null && passes.get(crunchifyFiles.indexOf(multipartFile)) > 1) {
						for (int i = 0; i < readings.size(); i++) {
							Reading reading_x = readings.get(i);
							reading_x.setData_r(reading_x.getData_r() / passes.get(crunchifyFiles.indexOf(multipartFile)));
							reading_x.setData_theta(reading_x.getData_theta() / passes.get(crunchifyFiles.indexOf(multipartFile)));
							readings.set(i, reading_x);
							// System.out.println(reading_x.toString());
						}
					}

					/**
					 * Calculate new_x
					 * 
					 * Take average for subtraction with both New_X, and New_R.
					 * eg: for time: -3500, -3600, ...., -4000, -4500, original
					 * x: 1, 2,............................4, 5, then take
					 * average:(1+2+.....+4+5)/no of values = delta then, New_x
					 * = old_x - delta
					 */
					double average = readings.stream().filter(m -> m.getTime() < -3499 && m.getTime() > -4501).mapToDouble(Reading::getData_r).average()
							.getAsDouble();

					System.out.println("Average for new x: " + average);

					// Reading reading_4000 =
					// readings_4000.get(readings_4000.size() - 1);

					System.out.println("\n Create new column with new_x_data");
					for (int i = 0; i < readings.size(); i++) {
						Reading reading_x = readings.get(i);
						reading_x.setNew_x((float) (reading_x.getData_r() - average));
						readings.set(i, reading_x);
						// System.out.println(reading_x.toString());
					}

					// Is x and y data
					System.out.println("\n is x and y data");
					if (isXandYdata != null && isXandYdata.size() > crunchifyFiles.indexOf(multipartFile)
							&& isXandYdata.get(crunchifyFiles.indexOf(multipartFile)) != null
							&& isXandYdata.get(crunchifyFiles.indexOf(multipartFile)).equalsIgnoreCase("on")) {
						for (int i = 0; i < readings.size(); i++) {
							Reading reading_x = readings.get(i);

							float data_r = (float) Math.sqrt((Math.pow(reading_x.getData_r(), 2) + Math.pow(reading_x.getData_theta(), 2)));
							float data_theta = (float) Math.atan(reading_x.getData_theta() / reading_x.getData_r());
							reading_x.setData_r(data_r);
							reading_x.setData_theta(data_theta);
							readings.set(i, reading_x);
							// System.out.println(reading_x.toString());
						}
					}
					
					/** 
					 * Divide R. 
					 */
					if(divideByR != null && divideByR.size() > 0 && divideByR.get(crunchifyFiles.indexOf(multipartFile)) != 0.0){
						for (int i = 0; i < readings.size(); i++) {
							Reading reading_x = readings.get(i);
							reading_x.setData_r(reading_x.getData_r() / divideByR.get(crunchifyFiles.indexOf(multipartFile)));
							reading_x.setData_r_before_subtracting_from_avg(reading_x.getData_r());
							readings.set(i, reading_x);
						}
					}
					
					/**
					 * Calculate new r
					 */
					average = readings.stream().filter(m -> m.getTime() < -3499 && m.getTime() > -4501).mapToDouble(Reading::getData_r).average().getAsDouble();
					System.out.println("Average for new r: " + average);

					Map<String, Object[]> data = new HashMap<String, Object[]>();

					/**
					 * Change value of column_R for all readings. Subtract all
					 * readings by Delta
					 */
					System.out.println("\n Change value of R for all cols");
					System.out.println("\ntime\t\tzero\tdata_r\t\tdata_theta\tnew_x\tdata_r_before_subtracting_from_avg");
					for (int i = 0; i < readings.size(); i++) {
						Reading reading_x = readings.get(i);
						reading_x.setData_r((float) (reading_x.getData_r() - average));
						readings.set(i, reading_x);

						Row row = sheet.createRow(i);

						Cell cell = row.createCell(5);
						int cellnum = 0;
						row.createCell(cellnum++).setCellValue(reading_x.getTime());
						row.createCell(cellnum++).setCellValue(reading_x.getZero());
						row.createCell(cellnum++).setCellValue(reading_x.getData_r());
						row.createCell(cellnum++).setCellValue(reading_x.getData_theta());
						row.createCell(cellnum++).setCellValue(reading_x.getNew_x());
						row.createCell(cellnum++).setCellValue(reading_x.getData_r_before_subtracting_from_avg());

						// data.put(String.valueOf(i), new Object[] {
						// reading_x.getTime(), reading_x.getZero(),
						// reading_x.getData_r(), reading_x.getData_theta(),
						// reading_x.getNew_x() });

						//
						// Row row = sheet.createRow(i);
						// int cellnum = 0;
						// row.createCell(cellnum++).setCellValue(reading_x.getTime());
						// row.createCell(cellnum++).setCellValue(reading_x.getZero());
						// row.createCell(cellnum++).setCellValue(reading_x.getData_r());
						// row.createCell(cellnum++).setCellValue(reading_x.getData_theta());
						// row.createCell(cellnum++).setCellValue(reading_x.getNew_x());

						// bwMax.write(reading_x.getTime() + "," +
						// reading_x.getZero() + "," + reading_x.getData_r() +
						// "," + reading_x.getData_theta() + ","
						// + reading_x.getNew_x());
						// bwMax.newLine();
						System.out.println(reading_x.toString());
					}
					
					System.out.println("File: " + multipartFile.getOriginalFilename());
					bwMax.write("\nFile: " + multipartFile.getOriginalFilename());

					final Comparator<Reading> comp = (p1, p2) -> Float.compare(p1.getData_r(), p2.getData_r());
					Reading oldest = readings.stream().max(comp).get();
					System.out.println("Max value of r: " + oldest.getData_r());
					bwMax.write("\nMax value of r: " + oldest.getData_r());

					final Comparator<Reading> comp1 = (p1, p2) -> Float.compare(p1.getNew_x(), p2.getNew_x());
					Reading oldest2 = readings.stream().max(comp1).get();
					System.out.println("Max value of new x: " + oldest2.getNew_x());
					bwMax.write("\nMax value of new x: " + oldest2.getNew_x());

					Set<String> keyset = data.keySet();
					int rownum = 0;
					for (String key : keyset) {
						Row row = sheet.createRow(rownum++);
						Object[] objArr = data.get(key);
						int cellnum = 0;
						for (Object obj : objArr) {
							Cell cell = row.createCell(cellnum++);
							cell.setCellValue(String.valueOf(obj));
						}
					}

					try {
						String excelFileName = (LocalDateTime.now()).format(format);
						FileOutputStream out = new FileOutputStream(new File(excelFileName+".xls"));
						workbook.write(out);
						out.close();
						System.out.println("Excel written successfully. "+excelFileName);
						
						convertExcelToCsv(excelFileName+".xls", excelFileName+".csv");

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					System.out.println("File created successfully");

					// multipartFile.transferTo(new File(saveDirectory +
					// fileName));
					// fileNames.add(fileName);

					System.out.println("");

				}

			}
		}
		
		bwMax.close();

		map.addAttribute("files", fileNames);
		return "uploadfileSuccess";
	}
}
