import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioManager {


    public static void crearUsuario(Connection connection, String nombre, String correo) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuarios (nombre, correo) VALUES (?, ?)")) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, correo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al crear el usuario.");
        }
    }

    public static void consultarUsuarios(Connection connection, PrintWriter writer) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_usuario, nombre, correo FROM usuarios");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            // Generar tabla HTML con resultados
            writer.println("<table border=\"1\">");
            writer.println("<tr><th>ID</th><th>Nombre</th><th>Correo</th></tr>");

            while (resultSet.next()) {
                int idUsuario = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");
                writer.printf("<tr><td>%d</td><td>%s</td><td>%s</td></tr>%n", idUsuario, nombre, correo);
            }

            writer.println("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
            writer.println("Error al consultar usuarios.");
        }
    }

    public static void actualizarUsuario(Connection connection, String idUsuario, String nuevoNombre, String nuevoCorreo) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usuarios SET nombre=?, correo=? WHERE id_usuario=?")) {
            preparedStatement.setString(1, nuevoNombre);
            preparedStatement.setString(2, nuevoCorreo);
            preparedStatement.setInt(3, Integer.parseInt(idUsuario));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el usuario.");
        }
    }

    public static void eliminarUsuario(Connection connection, String idUsuario) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usuarios WHERE id_usuario=?")) {
            preparedStatement.setInt(1, Integer.parseInt(idUsuario));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el usuario.");
        }
    }
}
