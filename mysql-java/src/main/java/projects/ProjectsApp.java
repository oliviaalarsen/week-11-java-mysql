package projects;

import java.sql.*; // Import SQL classes for database connection and queries

public class ProjectsApp {

    public static void main(String[] args) {
        // Database connection credentials
        String url = "jdbc:mysql://localhost:3306/week11_project";
        String user = "root";
        String password = "Much2dorn1728!"; 

        try {
            // Connect to the MySQL database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            // Optional: Delete an existing project by ID (if it exists)
            deleteProject(4); // Change the ID as needed

            // Insert a new project into the database
            insertProject("Final Stretch", "Completing the last task of Week 11");

            // Create a statement object to execute a SQL SELECT query
            Statement stmt = conn.createStatement();

            // Execute the query and get all projects from the table
            ResultSet rs = stmt.executeQuery("SELECT * FROM projects");

            // Print all the projects to the console
            System.out.println("\nProjects:");
            while (rs.next()) {
                int id = rs.getInt("id"); // Get project ID
                String name = rs.getString("name"); // Get project name
                String description = rs.getString("description"); // Get project description

                // Print formatted project data
                System.out.println(id + ": " + name + " — " + description);
            }

            // Close the database connection
            conn.close();

        } catch (SQLException e) {
            // Catch and print any SQL/database errors
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Inserts a new project into the database
    public static void insertProject(String name, String description) {
        String url = "jdbc:mysql://localhost:3306/week11_project";
        String user = "root";
        String password = "Much2dorn1728!";

        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // SQL query to insert data into the projects table
            String sql = "INSERT INTO projects (name, description) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set values for the placeholders
            stmt.setString(1, name);
            stmt.setString(2, description);

            // Execute the update
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Project inserted successfully!");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    // Deletes a project from the database based on its ID
    public static void deleteProject(int id) {
        String url = "jdbc:mysql://localhost:3306/week11_project";
        String user = "root";
        String password = "Much2dorn1728!";

        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // SQL query to delete a project
            String sql = "DELETE FROM projects WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set the ID for the project to delete
            stmt.setInt(1, id);

            // Execute the delete
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Project deleted successfully!");
            } else {
                System.out.println("No project found with that ID.");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }

}
