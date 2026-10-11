# Biblioteca Escolar - EFT Katherine - DOO II

**Alumna:** Katherine
**Asignatura:** Desarrollo Orientado a Objetos II - Duoc UC
**Proyecto:** BibliotecaEscolar_EFT_Katherine

### 1. Descripción
Sistema de gestión de biblioteca escolar con login, CRUD de libros, préstamos y reportes con hilos.

### 2. Patrones de Diseño Implementados (Rúbrica)
- **Singleton:** `db.DatabaseConnection` - Conexión única a MySQL.
- **DAO + Interface:** `dao.CrudDAO<T>` y `LibroDAO`, `PrestamoDAO`, `UsuarioDAO`.
- **MVC:** `modelo / vista / dao / db` separados.

### 3. POO - Herencia y Polimorfismo (Rúbrica Punto 1)
- **Clase Abstracta:** `modelo.Persona` (abstracta) con método abstracto `getPermisos()`.
- **Herencia:** `Usuario extends Persona`, `Bibliotecario extends Usuario`, `EstudianteUsuario extends Usuario`.
- **Interface:** `modelo.Prestamable` implementada por `Libro` con métodos `estaDisponible()`, `prestar()`, `devolver()`.
- **Polimorfismo:** En `UsuarioDAO.login()` se retorna `Bibliotecario` o `EstudianteUsuario` según el rol.

### 4. Concurrencia y Threads (Rúbrica Punto 3 y 5)
- **Synchronized:** En `PrestamoDAO.prestarLibro()` y `devolverLibro()` para evitar stock negativo con acceso concurrente.
- **Threads:** En `vista.ReporteFrame` se usa `new Thread()` para generar reporte sin congelar la interfaz Swing.

### 5. Credenciales y BD
- **BD:** `biblioteca_katherine` - archivo `biblioteca_katherine.sql` adjunto.
- **Login Admin:** `admin@duoc.cl / admin123`
- **Login Estudiante:** `estudiante@duoc.cl / 1234`

### 6. Cómo Ejecutar
1. Importar `biblioteca_katherine.sql` en MySQL Workbench.
2. Abrir proyecto en IntelliJ.
3. Verificar librería `mysql-connector` en Project Structure.
4. Ejecutar `vista.LoginFrame`.

### 7. Entregables
- Video: [pega tu link de YouTube aquí cuando lo grabes]
- GitHub: [pega tu link de GitHub aquí]

### 8. Pruebas realizadas
- Login con roles OK
- CRUD Libros OK
- Préstamo con control de stock OK
- Devolución OK
- Reportes con Threads OK
- ### Entrega Final
- **Video Demo (< 5 min)
- **Usuario Admin:** admin@duoc.cl / admin123
- **Usuario Estudiante:** estudiante@duoc.cl / 1234
- **BD:** biblioteca_escolar
