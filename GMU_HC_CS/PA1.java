import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.Charset;
 
/**
 * Program to inspect the CS honor code.
 */
public class PA1
{		
	/**
    * Entrance to the program passing in arguments
    * @param args from command line
    */
    public static void main( String[] args ) throws IOException
    {
	// declare variables
	boolean exit = false;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // initialize BufferedReader for reading input from command line
	System.out.print("\n" +"\n" +"CS Honor Code Policies"+ "\n" +
			"(Source: https://cs.gmu.edu/wiki/pmwiki.php/HonorCode/CSHonorCodePolicies)"+ "\n" +"\n" + 
			"All CS students must adhere to the GMU Honor Code. In addition to this honor code, the computer science department has further honor code policies regarding programming projects, which is detailed below. Your instructor may state further policies for his or her class as well."+"\n" +"\n" +
			"Unless otherwise stated, at the time that an assignment or project is given, all work handed in for credit is to be the result of individual effort. (In some classes group work is encouraged; if so, that will be made explicit when the assignment is given.)");
			while ( exit!=true )
			{
				int option = 0;
				// display the menu options onto the command line
				System.out.print("\n\n--------------------------------------------------------"+"\n"+
						"Select an option from the following menu:"+ "\n" +
						"\n1) View contents: You (or your group, if a group assignment) may..."+ "\n" +
						"\n2) View contents: You (or your group, if a group assignment) may not seek assistance from anyone else, other than your instructor or teaching assistant..." + "\n" +
						"\n3) View contents: Unless permission to do so is granted by the instructor, you (or your group, if a group assignment) may not..." + "\n" +
						"\n4) View contents: You must..." + "\n" +
						"\n5) Print the honor code to output.txt" + "\n" +	
						"\n6) Quit \n--------------------------------------------------------\n"+
						"\nWhat do you want to do? ");
						// checking for exceptions in the input read from command line
	        					try
							{
	            						option = Integer.parseInt(br.readLine());
	        					}
							catch(NumberFormatException nfe)
							{
	            						System.err.println("\nInvalid Format!");
	        					}
	        				// use the cases to do the respective operations
	        				switch ( option )
	        					{
	        						case 1: 
									System.out.println("\nYou (or your group,if a group assignment)may:\n\n\tseek assistance in learning to use the computing facilities;\n\n\tseek assistance in learning to use special features of a programming language's implementation;\n\n\tseek assistance in determining the syntactic correctness of a particular programming language statement or construct;\n\n\tseek an explanation of a particular syntactic error;\n\n\tseek explanations of compilation or run-time error messages.\n\n");	
	        							break;
	        						case 2: 
	        							System.out.println("\nYou (or your group, if a group assignment) may not seek assistance from anyone else, other than your instructor or teaching assistant:\n\n\tin designing the data structures used in your solution to a problem;\n\n\tin designing the algorithm to solve a problem;\n\n\tin modifying the design of an algorithm determined to be faulty;\n\n\tin implementing your algorithm in a programming language;\n\n\tin correcting a faulty implementation of your algorithm;\n\n\tin determining the semantic correctness of your algorithm.\n\n");	
									break;
	        						case 3:
									System.out.println("\nUnless permission to do so is granted by the instructor, you (or your group, if a group assignment) may not:\n\n\tgive a copy of your work in any form to another student;\n\n\treceive a copy of someone else's work in any form;\n\n\tattempt to gain access to any files other than your own or those authorized by the instructor or computer center;\n\n\tinspect or retain in your possession another student's work, whether it was given to you by another student, it was found after other student has discarded his/her work, or it accidently came into your possession;\n\n\tin any way collaborate with someone else in the design or implementation or logical revision of an algorithm;\n\n\tpresent as your own, any algorithmic procedure which is not of your own or of the instructor's design, or which is not part of the course's required reading (if you modify any procedure which is presented in the course's texts but which is not specifically mentioned in class or covered in reading assignment												s, then a citation with page number must be given;\n\tincorporate code written by others (such as can be found on the Internet).");
	        							break;
	        						case 4: 	
									System.out.println("\nYou must:\n\n\treport any violations of II and III that you become aware of;\n\n\tif part of a group assignment, be an equal 'partner' in your group's activities and productions, and represent accurately the level of your participation in your group's activities and productions.");
	        							break;
	        						case 5: // this is to write data to a file which has already been created at the specified location
	        							List<String> lines = Arrays.asList("To promote a stronger sense of mutual responsibility, trust, and fairness among all members of the Mason community, and with the desire for greater academic and personal achievement, we, the student members of the university community, have set forth this honor code:"," ", "Student members of the George Mason University community pledge not to cheat, plagiarize, steal, or lie in matters related to academic work.");
									Path file = Paths.get("output.txt");
									Files.write(file, lines, Charset.forName("UTF-8"));
	        							System.out.println("\nThe honor code has been printed to output.txt\n");
	        							break;
	        						case 6: // exit case
	        							exit=true;
	        							System.out.println("Goodbye!\n");
	        							System.exit(0);
	        							break;
	        							default: System.out.println("\nThis is not a valid selection.\n");

						        }
			}		
	}	
}


