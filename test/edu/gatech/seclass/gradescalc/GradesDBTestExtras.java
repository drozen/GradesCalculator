package edu.gatech.seclass.gradescalc;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradesDBTestExtras {
	
	GradesDB db = null;
    static final String GRADES_DB_GOLDEN = "DB" + File.separator
            + "GradesDatabase6300-goldenversion.xlsx";
    static final String GRADES_DB = "DB" + File.separator
            + "GradesDatabase6300.xlsx";
	
    @Before
    public void setUp() throws Exception {
        FileSystem fs = FileSystems.getDefault();
        Path dbfilegolden = fs.getPath(GRADES_DB_GOLDEN);
        Path dbfile = fs.getPath(GRADES_DB);
        Files.copy(dbfilegolden, dbfile, REPLACE_EXISTING);
        db = new GradesDB(GRADES_DB);
    }

    @After
    public void tearDown() throws Exception {
        db = null;
    }
    
  // Daniel's added tests for the task cards Daniel produced:
  
//  Task Card #3.4
//  Extend the *GradesDB* class so that it provides a way to add students.

  @Test
  public void testAddStudent() {
      Student newStudent1 = new Student("Daniel Rozen", "901234515", db);
      db.addStudent(newStudent1);
      db = new GradesDB(GRADES_DB);
      assertEquals(15, db.getNumStudents());
      Student newStudent2 = new Student("Alan Kawer", "901234516", db);
      db.addStudent(newStudent2);
      db = new GradesDB(GRADES_DB);
      assertEquals(16, db.getNumStudents());
  }
  
//  **Task Card #3.5**
//
//  Extend the *GradesDB* class so that it provides a way to add projects.
  
  @Test
  public void testAddProject() {
      db.addProject("PROJECT 4");
      db = new GradesDB(GRADES_DB);
      assertEquals(4, db.getNumProjects());
      db.addProject("PROJECT 5");
      db = new GradesDB(GRADES_DB);
      assertEquals(5, db.getNumAssignments());
  }
//  **Task Card #3.6**
//
	// Extend the *GradesDB* class so that it provides a way to add team grades
	// for a project.
  @Test
  public void testAddGradesForProject() {
      String projectName = "PROJECT 4";
      db.addProject(projectName);
      db = new GradesDB(GRADES_DB);
      db.addGradesForProject(projectName, "Team 1", 95);
      db.addGradesForProject(projectName, "Team 2", 87);
      db.addGradesForProject(projectName, "Team 3", 98);
          db = new GradesDB(GRADES_DB);
      assertEquals(95, db.getGradesForProject(projectName, "Team 1"), 1);
		assertEquals(87, db.getGradesForProject(projectName, "Team 2"), 1);
		assertEquals(98, db.getGradesForProject(projectName, "Team 3"), 1);
		
  } // end test 

		// **Task Card #3.7**
		//
		// Extend the *GradesDB* class so that it provides a way to add teams
		// for projects.
  
  @Test
  public void testAddTeams() {
      db.addTeam("Team 4");
      db = new GradesDB(GRADES_DB);
      assertEquals(4, db.getNumTeams());
      db.addTeam("Team 5");
      db = new GradesDB(GRADES_DB);
      assertEquals(5, db.getNumTeams());
  }
  
	// **Task Card #3.8**
	//
	// Extend the *GradesDB* class so that it provides a way to assign
	// students to their respective teams for a project.
  
  @Test
  public void testAddStudentToTeam() {
  	Student newStudent1 = new Student("Daniel Rozen", "901234515", db);
      db.addStudent(newStudent1);
      db = new GradesDB(GRADES_DB);
      db.addStudentToTeam(newStudent1, "Team 3");
      assertEquals(5, db.getNumStudentsInTeam());
      Student newStudent2 = new Student("Alan Kawer", "901234516", db);
      db.addStudent(newStudent2);
      db = new GradesDB(GRADES_DB);
      db.addStudentToTeam(newStudent2, "Team 3");
      assertEquals(6, db.getNumStudentsInTeam());
  }

}
