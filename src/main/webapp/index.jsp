<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Operaciones CRUD de Usuarios</title>
</head>
<body>
    <h1>Operaciones CRUD de Usuarios</h1>

    <form action="UsuarioServlet" method="post">
        <!-- Campos del formulario para crear usuario -->
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
        <br>
        <label for="correo">Correo:</label>
        <input type="text" id="correo" name="correo" required>
        <br>
        <button type="submit" name="accion" value="crear">Crear Usuario</button>
    </form>

    <!-- Otros formularios para consultar, actualizar y eliminar usuarios -->

</body>
</html>
