package in.sp.backend;

import java.security.Timestamp;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Scanner;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

public class emergencyhelp {

	private static final String url = "jdbc:mysql://127.0.0.1:3306/help";
	private static final String user = "root";
	private static final String password = "Atish@1193";

	public static void main(String[] args) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("Driver loaded successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Connection con = DriverManager.getConnection(url, user, password);

			Scanner sc = new Scanner(System.in);

			while (true) {

				System.out.println("a. Register ");
				System.out.println("b. login");

				System.out.println("Enter your choice  : ");
				char choice = sc.next().charAt(0);

				if (choice == 'a') {

					System.out.println("enter your name : ");
					String name = sc.next();

					System.out.println("enter your password ");
					String password = sc.next();

					String query = String.format("insert into user(name,password) values(?,?)");

					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, name);
					ps.setString(2, password);

					int count = ps.executeUpdate();

					if (count > 0) {
						System.out.println("registration successfully.. ");
					} else {
						System.out.println("registration failed");
					}
				}

				else if (choice == 'b') {

					System.out.println("1. i am Admin ");
					System.out.println("2. i am user  ");

					System.out.println("Enter your choice : ");
					int choice1 = sc.nextInt();
					sc.nextLine();

					if (choice1 == 1) {

						System.out.println("Enter your email : ");
						String email = sc.next();

						System.out.println("Enter your password : ");
						String pass = sc.next();

						String query = "select * from admin where email=? and password = ?";
						PreparedStatement ps = con.prepareStatement(query);

						ps.setString(1, email);
						ps.setString(2, pass);

						ResultSet rs = ps.executeQuery();

						if (rs.next()) {
							System.out.println("Login successful. Welcome, " + email + " Admin !!!");

							while (true) {
								System.out.println("a. Show complaints ");
								System.out.println("b. The users ");
								System.out.println("c. Exit");

								System.out.println("Enter your choice : ");
								char option = sc.next().charAt(0);

								switch (option) {
								case 'a':

									String emrgQuery = "SELECT * FROM emergency";
									PreparedStatement psEmrg = con.prepareStatement(emrgQuery);
									ResultSet rsemrg1 = psEmrg.executeQuery();

									System.out.printf("\n%-5s %-30s %-30s %-15s%n", "ID", "Emergency Name", "Location",
											"Phone No");
									System.out.println(
											"----------------------------------------------------------------------------------");

									boolean found = false;
									while (rsemrg1.next()) {
										found = true;
										int id = rsemrg1.getInt("emergency_id");
										String name = rsemrg1.getString("emergency_name");
										String location = rsemrg1.getString("location");
										String phone = rsemrg1.getString("phone_number");

										System.out.printf("%-5d %-30s %-30s %-15s%n", id, name, location, phone);
									}

									if (!found) {
										System.out.println("No emergency records found.");
									}

								case 'b':

									String userQuery = "SELECT * FROM user";
									PreparedStatement psUser = con.prepareStatement(userQuery);
									ResultSet rsUser = psUser.executeQuery();

									System.out.printf("\n%-5s %-20s %-15s%n", "ID", "Name", "Password");
									System.out.println("----------------------------------------------");

									boolean found1 = false;
									while (rsUser.next()) {
										found1 = true;
										int id = rsUser.getInt("id");
										String name = rsUser.getString("name");
										String password = rsUser.getString("password");

										System.out.printf("%-5d %-20s %-15s%n", id, name, password);
									}

									if (!found1) {
										System.out.println("No user records found.");
									}
									break;
								case 'c':
									System.out.println("Logging out from Admin Panel");

									break;

								default:
									System.out.println("invalid choice ..");

								}
								if (option == 'c') {
									break;
								}

							}
						} else {

							System.out.println("Error: Invalid username or password. Please try again.");

						}

					} else if (choice1 == 2) {

						System.out.println("Enter your username : ");
						String username = sc.next();

						System.out.println("Enter your password : ");
						String pass = sc.next();

						String query = "select * from user where name=? and password = ?";
						PreparedStatement ps = con.prepareStatement(query);

						ps.setString(1, username);
						ps.setString(2, pass);

						ResultSet rs = ps.executeQuery();

						if (rs.next()) {
							System.out.println("Login successful. Welcome, " + username + "!");

							while (true) {

								System.out.println(
										"------------------------------------------------------------------------------");

								System.out.println(" Welcome  to our portal now its time to make an help...  ");

								System.out.println("1. create emergency ");

								System.out.println("2. update emergency  ");

								System.out.println("3. Issue solve now i want to delete my report ");

								System.out.println("4. Retrieve the data ");

								System.out.println(
										"------------------------------------------------------------------------------");

								System.out.println("Enter your choice : ");
								int choice11 = sc.nextInt();
								switch (choice11) {
								case 1:
									System.out.println("How many data you want to insert : ");
									int size = sc.nextInt();

									for (int i = 0; i < size; i++) {
										System.out.println("enter emergency_id : ");
										int emergency_id = sc.nextInt();
										sc.nextLine();

										System.out.println("enter emergency name : ");
										String emergency_name = sc.nextLine();

										System.out.println("enter description : ");
										String description = sc.nextLine();

										System.out.println("enter location : ");
										String location = sc.nextLine();

										System.out.println("enter status : ");
										String status = sc.nextLine();

										System.out.println("enter phone no : ");
										String phone_no = sc.nextLine();

										String query1 = String.format(
												"insert into emergency(emergency_id,emergency_name,description,location,status,phone_number) values(?,?,?,?,?,?)");

										PreparedStatement ps1 = con.prepareStatement(query1);
										ps1.setInt(1, emergency_id);
										ps1.setString(2, emergency_name);
										ps1.setString(3, description);
										ps1.setString(4, location);
										ps1.setString(5, status);
										ps1.setString(6, phone_no);

										int rowInserted = ps1.executeUpdate();
										if (rowInserted > 0) {
											System.out.println("Data inserted successfully!");
										} else {
											System.out.println("Insertion failed!");
										}

									}
									break;

								case 2:
									System.out.println("Enter the column name you want to update: ");
									String columnName = sc.next();

									System.out.println("You want to update the column: " + columnName);

									System.out.println("Enter the value to update: ");
									sc.nextLine();
									String value = sc.nextLine();

									System.out.println("Enter the id of the record you want to update: ");
									int id1 = sc.nextInt();

									String query1 = String.format("UPDATE emergency SET %s = ? WHERE emergency_id = ?",
											columnName);

									PreparedStatement ps1 = con.prepareStatement(query1);
									ps1.setString(1, value);
									ps1.setInt(2, id1);

									int count = ps1.executeUpdate();

									if (count > 0) {
										System.out.println("Data updated successfully!");
									} else {
										System.out.println("No data updated.");
									}
									break;

								case 3:

									System.out.println("Enter the id u want to delete : ");
									int id2 = sc.nextInt();

									String query11 = String.format("delete from emergency where emergency_id = ?");

									PreparedStatement ps11 = con.prepareStatement(query11);
									ps11.setInt(1, id2);

									int count1 = ps11.executeUpdate();

									if (count1 > 0) {
										System.out.println("data deleted successfully ");
									} else {
										System.out.println("not data deleted successfully");
									}
									break;

								case 4:

									System.out.println("a. My emergency table view ..");
									System.out.println("b. Nearest Hospitals");
									System.out.println("c. Nearest Fire team");
									System.out.println("d. Nearest Police Station");
									System.out.println("e. Exit");

									System.out.println("Enter your choice : ");

									char choice111 = sc.next().charAt(0);

									switch (choice111) {
									case 'a':
										String query3 = "SELECT * FROM emergency";
										PreparedStatement ps2 = con.prepareStatement(query3);
										ResultSet rs1 = ps2.executeQuery();

										System.out.printf("\n%-15s %-20s %-30s %-20s %-15s %-15s %-25s%n",
												"Emergency ID", "Name", "Description", "Location", "Status", "Phone No",
												"Date");
										System.out.println(
												"----------------------------------------------------------------------------------------------------------------------------------");

										while (rs1.next()) {
											int ids = rs1.getInt("emergency_id");
											String nm = rs1.getString("emergency_name");
											String dsc = rs1.getString("description");
											String lc = rs1.getString("location");
											String sts = rs1.getString("status");
											String pno = rs1.getString("phone_number");
											java.sql.Timestamp ldate = rs1.getTimestamp("localdate");

											System.out.printf("%-15d %-20s %-30s %-20s %-15s %-15s %-25s%n", ids, nm,
													dsc, lc, sts, pno, ldate);
										}
										break;

									case 'b':
										System.out.println("\nFetching nearest hospitals...");

										System.out.print("Enter your location: ");
										sc.nextLine(); // Clear previous newline if needed
										String adr = sc.nextLine();

										String hospitalQuery = "SELECT * FROM hospital WHERE hospital_location = ?";
										PreparedStatement psHospitals = con.prepareStatement(hospitalQuery);
										psHospitals.setString(1, adr.toLowerCase());
										ResultSet rsHospitals = psHospitals.executeQuery();

										System.out.printf("\n%-5s %-30s %-30s %-15s%n", "ID", "Hospital Name",
												"Address", "Phone No");
										System.out.println(
												"----------------------------------------------------------------------------------");

										boolean found = false;
										while (rsHospitals.next()) {
											found = true;
											int id = rsHospitals.getInt("hospital_id");
											String name = rsHospitals.getString("hospital_name");
											String address = rsHospitals.getString("hospital_location");
											String phone = rsHospitals.getString("phone_number");

											System.out.printf("%-5d %-30s %-30s %-15s%n", id, name, address, phone);
										}

										if (!found) {
											System.out.println("No hospitals found for the entered location.");
										}
										break;

									case 'c':
										System.out.println("\nFetching nearest fire teams...");

										System.out.print("Enter your location: ");
										sc.nextLine(); // Clear previous newline if needed
										String adr1 = sc.nextLine();

										String fteam = "SELECT * FROM fire_team WHERE location = ?";
										PreparedStatement pfteam = con.prepareStatement(fteam);
										pfteam.setString(1, adr1.toLowerCase());
										ResultSet rsfteam = pfteam.executeQuery();

										System.out.printf("\n%-5s %-30s %-30s %-15s%n", "ID", "Fire team Name",
												"Address", "Phone No");
										System.out.println(
												"------------------------------------------------------------------");

										boolean found1 = false;
										while (rsfteam.next()) {
											found1 = true;
											int id = rsfteam.getInt("fireteam_id");
											String name = rsfteam.getString("fireteam_name");
											String address = rsfteam.getString("location");
											String phone = rsfteam.getString("phone_number");

											System.out.printf("%-5d %-30s %-30s %-15s%n", id, name, address, phone);
										}

										if (!found1) {
											System.out.println("No hospitals found for the entered location.");
										}
										break;

									case 'd':
										System.out.println("\nFetching nearest police stations...");

										System.out.print("Enter your location: ");
										sc.nextLine();
										String adr11 = sc.nextLine();

										String policeQuery = "SELECT * FROM police_station WHERE location = ?";
										PreparedStatement psPolice = con.prepareStatement(policeQuery);
										psPolice.setString(1, adr11.toLowerCase());
										ResultSet rsPolice = psPolice.executeQuery();

										System.out.printf("\n%-5s %-30s %-30s %-15s%n", "ID", "Police Station Name",
												"Address", "Phone No");
										System.out.println(
												"------------------------------------------------------------------");

										boolean found11 = false;
										while (rsPolice.next()) {
											found11 = true;
											int id = rsPolice.getInt("station_id");
											String name = rsPolice.getString("station_name");
											String address = rsPolice.getString("location");
											String phone = rsPolice.getString("phone_number");

											System.out.printf("%-5d %-30s %-30s %-15s%n", id, name, address, phone);
										}

										if (!found11) {
											System.out.println("No police stations found for the entered location.");
										}
										break;
									case 'e':
										System.out.println("Logging out from Admin Panel");

										break;

									default:
										System.out.println("invalid choice ");
										break;

									}

									if (choice111 == 'e') {
										break;
									}

								}
							}
						}

					} else {
						System.out.println("Invalid username or password. Please try again.");
					}

				} else {
					System.out.println("invalid choice..... ");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
