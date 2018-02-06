package edu.gatech.seclass.gradescalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Develop a GradesDB class that’s able to access the database with the students’ info.  

public class GradesDB {
	
	 //HashSet<Student> students;
	public static HashSet<Student> students = new HashSet<Student>();
	
	static final String GRADES_DB = "DB" + File.separator
	            + "GradesDatabase6300.xlsx";
		
//	GradesDB also prints:
//	# of assignments given so far
// does this by computing the number of assignment columns
	static private int numAssignments;

//	and the # of projects given so far
// does this by computing the number of project columns

	private int numProjects;
	
	// computes the number of students for the following variable
	
	private int numStudents;

	//Add to this class methods to extract the relevant data about the students 

	// Getter and Setter Methods:
	
	public int getNumStudents() {
		return numStudents;
	}

	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}
	
	public int getNumAssignments() {
		return numAssignments;
	}
	static public void setNumAssignments(int numAssignments) {
		GradesDB.numAssignments = numAssignments;
	}
	
	public int getNumProjects() {
		return numProjects;
	}

	public void setNumProjects(int numProjects) {
		this.numProjects = numProjects;
	}

	public  HashSet<Student> getStudents() {
		return students;
	}
	
	// Workbook Methods
	
	public void loadSpreadsheet(String gradesDb) {
		
		// Use a file: XSSFWorkbook, File
		FileInputStream myInputStream = null;
		XSSFWorkbook wb = null;
		try {
			 myInputStream = new FileInputStream(gradesDb);
			 wb = new XSSFWorkbook(myInputStream); 
			  myInputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Define Sheets:
		Sheet StudentsInfoSheet = wb.getSheetAt(0);	  
	    Sheet AttendanceSheet = wb.getSheetAt(2); 	 
		Sheet IndividualGradesSheet = wb.getSheetAt(3);
		Sheet TeamGradesSheet = wb.getSheetAt(5);
				
//		// Create Student objects for each student and read in relevant info
//		
		// testGetNumAssignments code:

	    Row rowIndivGrad = IndividualGradesSheet.getRow(0);
	      int numColsIndivGrad = 0;
	      for (Cell cell : rowIndivGrad) {
	        numColsIndivGrad++; // count up number columns
	        // read in assignment grade from columns 2-4
	      }
		    setNumAssignments(numColsIndivGrad-1);  // subtract 1 from the # of columns in order to get the total number of assignments
	    	    
	    // testGetNumStudents code:
	    // get StudentsInfo Sheet 
	    int numRows = 0;
	    for (Row row : StudentsInfoSheet) {
	       	numRows++; // count up number rows
	    }
	    setNumStudents(numRows-1);  // subtract 1 from number of Rows in order to get the total number of columns
	    
	    // testGetNumProjects code:
	    
	    // get TeamGrades Sheet 
	    for (Row row : TeamGradesSheet) {
	    	 int numCols = 0;
		      for (Cell cell : row) {
		        numCols++; // count up number columns
		      }
			    setNumProjects(numCols-1);  // subtract 1 from the number of Columns in order to get the total number of projects
	    } // end for loop
	    
		Iterator<Row> rows     = StudentsInfoSheet.rowIterator ();
		
		Iterator<Row> rowsAt   = AttendanceSheet.rowIterator ();
			
	// code in order to skip the 1st row
	    Row row = rows.next (); // define StudentsInfoSheet starting row
	    Row rowAt = rowsAt.next (); // define AttendanceSheet starting row
		
	    while (rows.hasNext ()) {  
			row = rows.next ();
		
			Student aStudent = new Student(); // create student objects
			
	    	Cell cell = row.getCell(0);  // fix to the 1st column
	           
	        switch (cell.getCellType()) {
	            case Cell.CELL_TYPE_STRING:
	                
	                // // extract and set name code:
	                String name = cell.getRichStringCellValue().getString();
	        		aStudent.setName(name);
	                break;
	            case Cell.CELL_TYPE_NUMERIC: {
	                }
	                break;
	
	        } // end switch
	        
		// extract and set gtid
	        
	        Cell cell1 = row.getCell(1);
	   	            
	        switch (cell1.getCellType()) {
	            case Cell.CELL_TYPE_STRING: {
	                String gtid = cell1.getRichStringCellValue().getString();
	        		aStudent.setGtid(gtid);
	            }
	                break;
	            case Cell.CELL_TYPE_NUMERIC: {
	                   cell1.setCellType(Cell.CELL_TYPE_STRING); // convert cell to String
	                   String gtid = cell1.getRichStringCellValue().getString();
	                  //String gtid = cell1.getNumericCellValue().getRawValue();
		        	  aStudent.setGtid(gtid);               
	                }
	                break;
	
	        } // end switch
				
		// extract and set email
	                    
	        Cell cell2 = row.getCell(2);
		            
	        switch (cell2.getCellType()) {
	            case Cell.CELL_TYPE_STRING: {
	                String email = cell2.getRichStringCellValue().getString();
		        	aStudent.setEmail(email);
	            }
	                break;
	            case Cell.CELL_TYPE_NUMERIC: {
	                  cell2.setCellType(Cell.CELL_TYPE_STRING); // convert cell to String
	                  String email = cell2.getRichStringCellValue().getString();
		        	  aStudent.setEmail(email);
	                }
	                break;
	
	        } // end switch
							
			// extract and set attendance

	        rowAt = rowsAt.next ();
//			if(rowAt.getRowNum()==0){
//				   continue; //just skip the rows if row number is 0 
//				  }	  
	
				Cell cell2At = rowAt.getCell(1);  // fix to the 2nd column
			       
				// extract and set attendance
	          
			    switch (cell2At.getCellType()) {
	//		        case Cell.CELL_TYPE_STRING: {
	//		            int attendance = Math.round((double) cell2At.getRichStringCellValue().getString());
	//		            aStudent.setAttendance(attendance);
			//
	//		        }
	//		            break;
			        case Cell.CELL_TYPE_NUMERIC: {
			           	                  
			              // This version converts to String:
			              //String attendance = cell2At.getRichStringCellValue().getString();
			        	// This version converts to rounded up int:
			        	
				          int attendance = (int) Math.round(cell2At.getNumericCellValue());
			              aStudent.setAttendance(attendance);
			            }
			            break;
	
			    } // end switch
			    
			    if (students.size() < numStudents) // ensure no more students than the # of students
			    	students.add(aStudent);		   // are added to the hashSet
		} // end inner while loop

		
	} // end public void loadSpreadsheet(String gradesDb) {

	private XSSFWorkbook openWorkBook() {
		// Use a file: XSSFWorkbook, File
	    String GRADES_DB = "DB" + File.separator
	            + "GradesDatabase6300.xlsx";
		FileInputStream myInputStream = null;
		XSSFWorkbook wb = null;
		try {
			 myInputStream = new FileInputStream(GRADES_DB);
			 wb = new XSSFWorkbook(myInputStream); 
			 myInputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}
	
	/**
	 * @param wb
	 */
	private void writeWorkBookOutput(XSSFWorkbook wb) {
		FileOutputStream fileOut = null;
//	    FileOutputStream fileOutTest = null;

		try {
			fileOut = new FileOutputStream(GRADES_DB);
//			fileOutTest =  new FileOutputStream("Test.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			wb.write(fileOut);
//			wb.write(fileOutTest);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			fileOut.close();
//			 fileOutTest.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	// Other Methods:
	
	public Student getStudentByName(String string) {
		for (Student stud : students) {
			if (stud.getName().equals(string)) {
				return stud;
			}
		} // end for loop
		return null;
	} // end public Student getStudentByName(String string) {
		
	public Student getStudentByID(String string) {
		for (Student stud : students) {
			if (stud.getGtid().equals(string)) {
				return stud;
			}
		} // end for loop		
		return null;
	}
	
	public double getAverageAssignmentsGrade(Student student) {

		XSSFWorkbook wb = openWorkBook();
		
		// Define Sheets:	 
		Sheet IndividualGradesSheet = wb.getSheetAt(3);

		// 1. grab student name
		String name = student.getName();

		double assignAv = 0.0;
		double divisor = 0.0;
		// 2. search for student in worksheet
		
		for (Row row : IndividualGradesSheet) {
	    	Cell cell = row.getCell(0);  // fix to the 1st column
	    	String cellName = cell.getRichStringCellValue().getString();

	    	if (cellName.equals(name)){
	    			for (Cell cell1 : row) {
	    					divisor ++; 					
	    				 if (cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	  	  	  	    		  assignAv =  (assignAv + cell1.getNumericCellValue());
	    				 } // end if	  	    		  
	     		}// end for
		    } // end if
		    } // end for

		// IndividualGradesSheet
		
				// 3. calculate Average
				// 4. return Average
	    
	    return Math.round(assignAv/(divisor-1));
		// return (Double) null;
	} // End getAverageAssignmentsGrade(Student student)


	public double getAverageProjectsGrade(Student student) {
		// TODO Auto-generated method stub
		
		XSSFWorkbook wb = openWorkBook();
		Sheet TeamsSheet = wb.getSheetAt(1);
		Sheet IndividualContribsSheet = wb.getSheetAt(4);
		Sheet TeamGradesSheet = wb.getSheetAt(5);
		
		// 1. grab student name
		String studentName = student.getName();

		// 2. search for student name in IndividualContribsSheet
		
		// initialize array to store contribution values
		Row rowContrib= IndividualContribsSheet.getRow(0);
		int numContribs = rowContrib.getLastCellNum()-1 ;
		double[] arrIndContribs = new double[numContribs];
		// initialize array to store team grades
		double[] arrTeamGrades = new double[numContribs];
				
		for (Row row : IndividualContribsSheet) {
			Cell cell1 = row.getCell(0);
			if (cell1 == null)
				cell1 = row.createCell(0);
	    	String cellName = cell1.getRichStringCellValue().getString();
	    	
			if (cellName.equals(studentName)) {
				for (Cell cell12 : row) {
					// scan across row reading in the individual contributions and store them into an array
					if (cell12.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						arrIndContribs[cell12.getColumnIndex()-1] = cell12.getNumericCellValue() / 100.;

					} // end if (cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) 
				} // end for (Cell cell1 : row)
			} // end if (cell.getStringCellValue().equals(studentName))
		} // end for (Row row : IndividualContribsSheet)
				
		// scan the team sheet:
		String teamName = " ";

		for (Row rowTeam : TeamsSheet) {
		// for each team starting row 1
			for (Cell cellTeam : rowTeam) {
				if (cellTeam.getStringCellValue().equals(studentName)) {
					Cell teamCellName = rowTeam.getCell(0);
					teamName = teamCellName.getStringCellValue();
				} // end if (cellTeam.getStringCellValue().equals(studentName)) 
			} // end for (Cell cellTeam : rowTeam) {
		} // end for (Row rowTeam : TeamGradesSheet)
		
		// scan down TeamGrades Sheet column 0 row 1
		
			// if find Team
				// scan project
					// if find project
						// record project grades in an array
		

		for (Row row : TeamGradesSheet) {
			Cell cell = row.getCell(0);
			if (cell.getStringCellValue().equals(teamName)) {
				for (Cell cell1 : row) {
					// scan across row reading in the individual contributions and store them into an array
					if (cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						arrTeamGrades[cell1.getColumnIndex()-1] = cell1.getNumericCellValue();
					} // end if (cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) 
				} // end for (Cell cell1 : row)
			} // end if (cell.getStringCellValue().equals(studentName))
		} // end for (Row row : TeamGradesSheet)
		
		// 3. calculate Average by multiplying the arrays
		
		// initialize array which is the result of the product of arrIndContribs[i] * arrTeamGrades[i]
		double[] arrMult = new double[numContribs];
		
		for (int i=0 ; i < numContribs ; i++) {
			arrMult[i] = arrIndContribs[i] * arrTeamGrades[i];
		}
		
		double arrSum = 0.0;
		for (double i : arrMult)
			arrSum += i;
		
		// 4. return Average
		
		// check to see if any zero values in Individual Contributions
		
		int denominator = arrMult.length;
		
		for (int i = 0;  i < arrMult.length; i++) {
			if (arrIndContribs[i] == 0.0){
				denominator --;
			}
		}
		
		double arrAv = arrSum / denominator;
		return Math.round(arrAv);
		
	} //end getAverageProjectsGrade(Student student)	
	
	public void addAssignment(String string) {
		// adds an extra column to the assignment page

		
		XSSFWorkbook wb = openWorkBook();
		Sheet IndividualGradesSheet = wb.getSheetAt(3);

	    int newCell = getNumAssignments();

	    // Create new column and write in new column
	    Row row = IndividualGradesSheet.getRow(0);
	    newCell ++;

	    Cell cell = row.getCell(newCell);
	    if (cell == null)
	    	cell = row.createCell(newCell);
	    cell.setCellType(Cell.CELL_TYPE_STRING);
	    cell.setCellValue(string);
	    
	 // Write the output to a file
	    writeWorkBookOutput(wb);
	    
	    for (Row row1 : IndividualGradesSheet) {
		      int numCols = 0;
		      for (Cell cell1 : row) {
		        numCols++; // count up number columns
		        // read in assignment grade from columns 2-4
		      }
			    setNumAssignments(numCols-1);  // subtract 1 from the # of columns in order to get the total number of assignments
	    }
	} // end addAssignment()

	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> grades) {
		// TODO Auto-generated method stub
		XSSFWorkbook wb = openWorkBook();
		Sheet IndividualGradesSheet = wb.getSheetAt(3);
		
		// scan for assignment name column
		
		Row row = IndividualGradesSheet.getRow(0);
		for (Cell cell : row) {
			if (cell.getStringCellValue().equals(assignmentName)) {
				// for each student:

				for (Student stud : grades.keySet()) {
					// 		scan for student row
					for (Row row1 : IndividualGradesSheet) {
						Cell cell1 = row1.getCell(0);
						String cellName = cell1.getStringCellValue();
						if (stud.getName().equals(cellName)) {
							Cell cell2 = row1.getCell(getNumAssignments());
							// write grade to cell
							int value = grades.get(stud);
							if (cell2 == null)
								cell2 = row1.createCell(getNumAssignments());
							//cell2.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell2.setCellValue(value);
							
						} // end if (stud.getName().equals(cellName)) {
					} // end for (Row row1 : IndividualGradesSheet) {
				} // end for  Student stud : grades.keySet())						
			} // end if (cell.getRichStringCellValue().equals(assignmentName))
		} // end for (Cell cell : row) 
							
		// Write the output to a file
	    writeWorkBookOutput(wb);
	} // end addGradesForAssignment

	public void addIndividualContributions(String projectName,
			HashMap<Student, Integer> contributions) {
		// TODO Auto-generated method stub
		XSSFWorkbook wb = openWorkBook();
		Sheet IndividualContribsSheet = wb.getSheetAt(4);

		// Scan for projectName in top column
		Row row = IndividualContribsSheet.getRow(0);
		for (Cell cell : row) {
			if (cell.getStringCellValue().equals(projectName)) {
				// for each student:

				for (Student stud : contributions.keySet()) {
					// 		scan for student row
					for (Row row1 : IndividualContribsSheet) {
						Cell cell1 = row1.getCell(0);
						if (cell1 == null)
							cell1 = row1.createCell(0);
										
			            if (cell1.getCellType() == Cell.CELL_TYPE_STRING && stud.getName().equals(cell1.getRichStringCellValue().toString())) {
//							cellName = cell1.getRichStringCellValue().toString();
//			            } // end if
//			               						
//						if (stud.getName().equals(cellName)) {
			            	
			            	int cellPos = 2; // cell position to write the individual contribution to

							switch (projectName) {
								case "PROJECT 2":
									cellPos = 2;
									break;
								case "PROJECT 3":
								    cellPos = 3;
									break;
							} // end switch 
							
							Cell cell2 = row1.getCell(cellPos);
							// write grade to cell
							int value = contributions.get(stud);
							
							if (cell2 == null)
								cell2 = row1.createCell(cellPos);
							//cell2.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell2.setCellValue(value);
							
						} // end if (stud.getName().equals(cellName)) {
					} // end for (Row row1 : IndividualContribsSheet) {
				} // end for (Student stud : contributions.keySet()) {
			} // end if (cell.getStringCellValue().equals(projectName))
		}// end for (Cell cell : row) {
		
		// Write the output to a file
	    writeWorkBookOutput(wb);
	} // end addIndividualContributions

	// 2 NEW ADDED METHODS
	
	// Set default formula
	static private String formula = "AT * 0.2 + AS * 0.4 + PR * 0.4";
	
	public int getOverallGrade(Student student) {
		
		String formula = getFormula();

	    int overallGrade = evaluateFormula(student, formula);
	    			
		return overallGrade;
	}

	/**
	 * @param student
	 * @param formula
	 * @return
	 * @throws ScriptException
	 */
	private int evaluateFormula(Student student, String formula) {
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("js");
	    engine.put("AT", student.getAttendance());
	    engine.put("AS", getAverageAssignmentsGrade(student));
	    engine.put("PR", getAverageProjectsGrade(student));

	    Object result = null;;
		try {
			result = engine.eval(formula);
		} catch (GradeFormulaException | ScriptException e) {
			// TODO Auto-generated catch block
			throw new GradeFormulaException("Incorrect formula format");
			//e.printStackTrace();
		}
	    String overallGradeString = (result.toString()); 
	    
	    int overallGrade = (int) Math.round(Double.parseDouble(overallGradeString));
		return overallGrade;
	} // end public int getOverallGrade(Student student) {
	
	public String getFormula()  {
		return formula;
	}

	public void setFormula(String newFormula) {
		GradesDB.formula = newFormula;
	} // end public void setFormula(String formula) {

} // end GradesDB class
