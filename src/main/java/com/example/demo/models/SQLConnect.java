package com.example.demo.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class SQLConnect {
	private static  Connection con;
	public static void main(String[] args) {
		
		connect2DB();
		get_users() ;
	}

	
	public static void connect2DB() {
		
        String database_link = "jdbc:mysql://localhost:3306/katanemimena?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       /*
        try {
            Class.forName(database_link);
        } catch (ClassNotFoundException e) {
            String msg = "The com.mysql.cj.jdbc.Driver is missing\n"
                    + "install and rerun the application";
            JOptionPane.showMessageDialog(this, msg, this.getTitle(), JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
*/
        // connect to db
        try {
            con = DriverManager.getConnection(database_link, "root", "huastudents1!");
        } catch (SQLException e) {
            String msg = "Error Connecting to Database:\n" + e.getMessage();
           System.out.println(msg);
        }

    }
	
	public static void get_users() {
		String query="Select * From Users";
		Statement st;
		try {
			st = con.createStatement();
			ResultSet result = st.executeQuery(query);
			while (result.next()) {
				String username=result.getString("Username");
				String password=result.getString("Password");
				String name=result.getString("Name");
				String last=result.getString("Lastname");
				//User temp =new User(username,password,name,last);
				System.out.println("U:"+username+", P:"+password+", N:"+name+", L:"+last);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static boolean valid_credentials(String username,String password) {
		String query="Select Username,Password From Users";
		Statement st;
		boolean valid=false;
		try {
			st = con.createStatement();
			ResultSet result = st.executeQuery(query);
			while (result.next()) {				
				String u=result.getString("Username");
				String p=result.getString("Password");
				if (username.equals(u)==true && password.equals(p)==true){
					valid=true;
					
					break;
				}
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valid;
	}
	
	
	public static boolean check_user_availiability(String username) {
		connect2DB();
		String query="Select Username From Users Where Username="+username;
		Statement st;
		boolean availiability=true;
		try {
			st = con.createStatement();
			ResultSet result = st.executeQuery(query);
			int counter=0;
			while (result.next()) {	
				counter++;
			}
			if(counter>0) {
				availiability=false;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return availiability;
	}
	
	
	public static boolean add_student(String username,String password, String name, String lname,int semester, int am ) {
		String query="INSERT INTO USERS (Username, Password, Name, Lastname, Role) VALUES ('"+username+"', '"+password+"', '"+name+"', '"+lname+"', "+"'Student')";
		String query2="INSERT INTO STUDENTS (Username, Semester, AM) Values ('"+username+"', "+semester+", "+am+")";
		boolean availiability=check_user_availiability( username);
		boolean added=false;
		if (availiability==true) {
			connect2DB();
			try {
				Statement st = con.createStatement();
				st.executeUpdate(query);
				Statement st2 = con.createStatement();
				st2.executeUpdate(query2);
				
				con.close();
				added=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return added;
	}
	public static boolean delete_student(String username) {
		String query="DELETE FROM Users Where username='"+username+"'";
		String query2="DELETE FROM Students Where username='"+username+"'";
		boolean deleted=false;
		connect2DB();
		try {
			Statement st = con.createStatement();
			int res1=st.executeUpdate(query);
			
			Statement st2 = con.createStatement();
			int res2=st2.executeUpdate(query2);
			
			con.close();
			if (res1==1 && res2==1) {
				deleted=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleted;
	}
	
	public static boolean updateStudent(String username, String password) {
		String query="UPDATE Users SET Password='"+password+"' Where username='"+username+"'";
		boolean updated=false;
		connect2DB();
		try {
			Statement st = con.createStatement();
			int res1=st.executeUpdate(query);
			
			
			con.close();
			if (res1==1) {
				updated=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updated;
	}
	public static boolean updateStudent(String username, int semester) {
		String query="UPDATE Students SET Semester="+semester+" Where username='"+username+"'";
		boolean updated=false;
		connect2DB();
		try {
			Statement st = con.createStatement();
			int res1=st.executeUpdate(query);
			
			
			con.close();
			if (res1==1) {
				updated=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updated;
	}
	public static boolean updateStudent(String username, String password ,int semester) {
		String query="UPDATE Users SET Password='"+password+"' Where username='"+username+"'";
		String query2="UPDATE Students SET Semester="+semester+" Where username='"+username+"'";
		boolean updated=false;
		connect2DB();
		try {
			Statement st = con.createStatement();
			int res1=st.executeUpdate(query);
			Statement st2 = con.createStatement();
			int res2=st.executeUpdate(query2);
			
			con.close();
			if (res1==1 && res2==1) {
				updated=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updated;
	}
	
	public static ArrayList <Student> getAllStudents(){
		ArrayList<Student> student_list=new ArrayList<Student>();
		String query="Select * From Users";
		Statement st;
		connect2DB();
		try {
			st = con.createStatement();
			ResultSet result = st.executeQuery(query);
			while (result.next()) {
				String username=result.getString("Username");
				String password=result.getString("Password");
				String name=result.getString("Name");
				String last=result.getString("Lastname");
				String role=result.getString("Role");
				if(role.equals("Student")) {
				Student temp_st =new Student(username,password,name,last);
					//System.out.println("U:"+username+", P:"+password+", N:"+name+", L:"+last);
					student_list.add(temp_st);
				
				}
			}
			Statement st2;	
			String query2="Select * From Students";
			st2 = con.createStatement();
			
			ResultSet result2 = st.executeQuery(query2);
			while (result2.next()) {
				String username=result2.getString("Username");
				int semester=result2.getInt("Semester");
				int am =result2.getInt("AM");
				for(int i=0;i<student_list.size();i++) {
					if(username.equals(student_list.get(i).getUsername())) {
						student_list.get(i).setSemester(semester);
						student_list.get(i).setAm(am);
						break;
					}
				}
				
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student_list;//apostelletai san json
	}
	
	public static boolean add_professor(String username,String password, String name, String lname ) {
		String query="INSERT INTO USERS (Username, Password, Name, Lastname, Role) VALUES ('"+username+"', '"+password+"', '"+name+"', '"+lname+"', "+"'Professor')";
		
		boolean availiability=check_user_availiability( username);
		boolean added=false;
		if (availiability==true) {
			connect2DB();
			try {
				Statement st = con.createStatement();
				st.executeUpdate(query);
				
				con.close();
				added=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return added;
	}
	
	public static boolean add_course(String username, String course) {
		String query = "INSERT INTO PROFESSORS (Username, Course) VALUES ('"+username+"', '"+course+"')";
		boolean added=false;
		connect2DB();
		try {
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			con.close();
			added=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return added;
		
	}
	
	public static boolean remove_course(String username, String course) {
		String query="DELETE FROM Professors Where username='"+username+"' and course='"+course+"'";
		boolean deleted=false;
		connect2DB();
		try {
			Statement st = con.createStatement();
			int res1=st.executeUpdate(query);
			
			con.close();
			
			deleted=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleted;
	}
	public static boolean delete_professor(String username) {
		String query="DELETE FROM Users Where username='"+username+"'";
		String query2="DELETE FROM Professors Where username='"+username+"'";
		boolean deleted=false;
		connect2DB();
		try {
			Statement st = con.createStatement();
			int res1=st.executeUpdate(query);
			
			Statement st2 = con.createStatement();
			int res2=st2.executeUpdate(query2);
			
			con.close();
			if (res1==1 && res2==1) {
				deleted=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleted;
	}
	public static boolean updateProfessor(String username, String password) {
		String query="UPDATE Users SET Password='"+password+"' Where username='"+username+"'";
		boolean updated=false;
		connect2DB();
		try {
			Statement st = con.createStatement();
			int res1=st.executeUpdate(query);
			con.close();
			
		    updated=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updated;
	}
	public static ArrayList <Professor> getAllProfessors(){
		ArrayList<Professor> professor_list=new ArrayList<Professor>();
		String query="Select * From Users";
		Statement st;
		connect2DB();
		try {
			st = con.createStatement();
			ResultSet result = st.executeQuery(query);
			while (result.next()) {
				String username=result.getString("Username");
				String password=result.getString("Password");
				String name=result.getString("Name");
				String last=result.getString("Lastname");
				String role=result.getString("Role");
				if(role.equals("Professor")) {
				Professor temp_pr =new Professor(username,password,name,last);
					//System.out.println("U:"+username+", P:"+password+", N:"+name+", L:"+last);
					professor_list.add(temp_pr);
				
				}
			}
			Statement st2;	
			String query2="Select * From Professors";
			st2 = con.createStatement();
			
			ResultSet result2 = st.executeQuery(query2);
			while (result2.next()) {
				String username=result2.getString("Username");
				String course =result2.getString("Course");
				for(int i=0;i<professor_list.size();i++) {
					if(username.equals(professor_list.get(i).getUsername())) {
						professor_list.get(i).addCourse(course);
						break;
					}
				}
				
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return professor_list;//apostelletai san json
	}
}
