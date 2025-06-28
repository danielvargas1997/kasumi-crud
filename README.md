## Proyecto Kasumi - Java Desktop CRUD con Autenticación y JDBC

Aplicación de escritorio desarrollada en **Java SE 11+** con **JDBC** y **Swing**, que implementa:

* **Sistema de autenticación** por usuarios y roles
* **Redireccionamiento** al módulo de gestión de clientes según rol
* **Operaciones CRUD** (Crear, Leer, Actualizar, Eliminar) para clientes

Proyecto académico del programa Tecnólogo en Análisis y Desarrollo de Software (Ficha 2977435) del SENA.

---

## 🛠️ Tecnologías utilizadas

* **Java SE 11+**
* **JDBC (Connector/J 8.x)**
* **NetBeans IDE**
* **MySQL**
* **Swing** (JFrame, JTable, JButton, JTextField...)
* **POO** (paquetes, clases, métodos)

---

## 📂 Estructura del proyecto

```
kasumi-java-desktop/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  ├─ co.kasumi.gui/        # Clases Swing (Login, MainFrame, ClienteForm)
│  │  │  ├─ co.kasumi.model/      # Entidades Java (Usuario, Rol, Cliente)
│  │  │  ├─ co.kasumi.util/       # DbUtil.java para conexión JDBC
│  │  │  ├─ co.kasumi.loader/     # DataLoader.java carga inicial de roles y admin
│  ├─ resources/
│     ├─ scripts/
│         └─ kasumi_schema.sql    # Script de creación de tablas y datos
├─ README.md                     # Documentación general
└─ .gitignore
```

---

## 🚀 Configuración y ejecución

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/TuUsuario/KasumiJavaDesktop.git
   ```

2. **Importar en NetBeans**

   * File → Open Project → seleccionar carpeta del proyecto

3. **Crear base de datos MySQL**

   * Abrir MySQL Workbench o CLI
   * Ejecutar:

     ```sql
     CREATE DATABASE KasumiDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
     USE KasumiDB;
     SOURCE scripts/kasumi_schema.sql;
     ```

4. **Configurar credenciales**

   * Editar `co.kasumi.util.DbUtil.java`:

     ```java
     private static final String URL =
         "jdbc:mysql://localhost:3306/KasumiDB?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
     private static final String USER = "root";
     private static final String PASSWORD = "1234567";
     ```

5. **Ejecutar la aplicación**

   * Botón Run (F6) en NetBeans → aparece ventana de Login

---

## 🔐 Lógica de autenticación y DataLoader

* **DataLoader.java** (dentro de `co.kasumi.loader`) es un `CommandLineRunner` que se ejecuta al iniciar la app y verifica:

  1. Si no existen roles predefinidos (`Administrador`, `Recepcionista`, `Contador`), los inserta.
  2. Si no existe el usuario `admin` (rol Administrador), lo crea con contraseña `admin123`.

> **¿Por qué DataLoader?** Para garantizar siempre la existencia de un usuario y roles mínimos para probar la aplicación sin tener que poblar manualmente la base de datos.

La autenticación se realiza comparando las credenciales ingresadas en el login Swing contra la tabla `Usuario`.

---

## 📦 Funcionalidades principales

1. **Login**

   * Campos: nombreUsuario, contraseña, selección de rol.
   * Redirecciona según rol:

     * **Administrador** / **Recepcionista** → Módulo Clientes
     * **Contador** → acceso restringido

2. **Módulo Clientes (CRUD)**

   * **Listado**: JTable con todos los clientes
   * **Crear**: formulario modal para nuevo cliente
   * **Actualizar**: precarga datos y guarda cambios
   * **Eliminar**: confirm dialog y elimina
   * **Buscar**: filtra por nombre o apellido

3. **Conexión JDBC**

   * Uso de `PreparedStatement` para todas las operaciones (seguridad contra SQL Injection)
   * `DbUtil` gestiona apertura y cierre de `Connection`.

---

## 🧪 Pruebas con Postman

Aunque la aplicación es de escritorio, incluimos ejemplos REST expuestos en el backend (puerto 9090) para facilitar integraciones. En Postman:

1. **Crear una nueva colección o pestaña** para tus requests.

2. **Configurar autenticación Basic Auth**:

   * Ve a la pestaña **Authorization**.
   * En **Type** selecciona **Basic Auth**.
   * Ingresa **Username**: `admin` y **Password**: `admin123`.
   * Postman rellenará automáticamente el header `Authorization: Basic YWRtaW46YWRtaW4xMjM=`.

3. **GET /api/clientes**

   * **URL**: `http://localhost:9090/api/clientes`
   * Selecciona **GET** y haz clic en **Send**.
   * **Respuesta esperada**: **200 OK** con JSON de lista de clientes.

4. **POST /api/clientes**

   * **URL**: `http://localhost:9090/api/clientes`
   * Selecciona **POST**.
   * En la pestaña **Body**, selecciona **raw** → **JSON** y pega:

     ```json
     {
       "nombre": "Juan",
       "apellido": "Pérez",
       "telefono": "3001234567",
       "email": "juan.perez@mail.com",
       "fechaNacimiento": "1992-08-10"
     }
     ```
   * Haz **Send**. Debes recibir **201 Created** con el objeto creado.

5. **PUT /api/clientes/{id}**

   * **URL**: `http://localhost:9090/api/clientes/1` (reemplaza `1` por el id real).
   * Selecciona **PUT**, configura **Authorization** igual que antes.
   * En **Body** → **raw JSON** actualiza algún campo:

     ```json
     {
       "nombre": "Juan Carlos",
       "apellido": "Pérez",
       "telefono": "3001234567",
       "email": "juan.perez@mail.com",
       "fechaNacimiento": "1992-08-10"
     }
     ```
   * **Send**. Debes recibir **200 OK** con el objeto actualizado.

6. **DELETE /api/clientes/{id}**

   * **URL**: `http://localhost:9090/api/clientes/1`.
   * Selecciona **DELETE**, asegúrate de la autenticación.
   * Haz **Send**. Debes recibir **204 No Content**.

---

## 👥 Autores

* Daniel Alejandro Vargas Uzuriaga
* Daniela López Chica
* Jonathan Cardona Calderón
* Luz Eleidis Baldovino González

**Proyecto académico SENA - Ficha 2977435**
