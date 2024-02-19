import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Establecer conexión a la base de datos
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mi_db", "root", "")) {
            PrintWriter out = response.getWriter();

            // Obtener la acción del formulario
            String accion = request.getParameter("accion");

            // Realizar la acción correspondiente
            if ("crear".equals(accion)) {
                UsuarioManager.crearUsuario(connection, request.getParameter("nombre"), request.getParameter("correo"));
            } else if ("consultar".equals(accion)) {
                UsuarioManager.consultarUsuarios(connection, out);
            } else if ("actualizar".equals(accion)) {
                UsuarioManager.actualizarUsuario(connection, request.getParameter("id_usuario"), request.getParameter("nombre"), request.getParameter("correo"));
            } else if ("eliminar".equals(accion)) {
                UsuarioManager.eliminarUsuario(connection, request.getParameter("id_usuario"));
            }

            // Redirigir de nuevo al formulario
            response.sendRedirect("formulario.html");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al conectar a la base de datos.");
        }
    }
}

