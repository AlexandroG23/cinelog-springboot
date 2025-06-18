# 🎬 Cinelog

**Cinelog** es una aplicación web desarrollada con **Spring Boot** que permite a los usuarios descubrir, buscar y guardar películas favoritas consultando datos en tiempo real desde **The Movie Database (TMDB)**. Ofrece autenticación de usuarios, control de acceso, y una interfaz moderna construida con **Thymeleaf** y **Tailwind CSS**.

---

## 🚀 Características

- 🔍 **Buscador de películas** en tiempo real desde TMDB.
- 🧾 **Registro e inicio de sesión** de usuarios con Spring Security.
- ⭐ **Guardar y gestionar películas favoritas** por usuario.
- 🔐 Control de acceso: los favoritos solo están disponibles tras iniciar sesión.
- 🌑 **Diseño responsive y soporte para dark mode**.
- 🛡️ Panel de administración (en progreso): gestión de usuarios y estadísticas.
- 🐬 Conexión a base de datos **MySQL** utilizando JPA y Hibernate.
- 🔑 Uso seguro de variables de entorno con `.env`.

---

## 🛠️ Tecnologías utilizadas

| Categoría        | Tecnologías / Librerías                             |
|------------------|-----------------------------------------------------|
| Backend          | Java 17, Spring Boot 3.5, Spring Security, JPA      |
| Frontend         | Thymeleaf, HTML5, Tailwind CSS                      |
| API de terceros  | [TMDB API](https://developer.themoviedb.org/)       |
| Base de Datos    | MySQL (con Docker), H2 (para desarrollo opcional)   |
| Seguridad        | BCryptPasswordEncoder, CustomUserDetailsService     |
| Configuración    | dotenv-java, dotenv-spring-boot                     |
| Build Tool       | Maven                                               |

---

## 🧪 Requisitos

- Java 17
- Maven
- MySQL o Docker
- TMDB API Key (puedes obtenerla gratis desde [TMDB](https://www.themoviedb.org/))
- `.env` con las siguientes variables:

```env
# Clave de la API de TMDB
TMDB_API_KEY=TU_API_KEY

# Configuración de la base de datos MySQL
DB_URL=jdbc:mysql://localhost:3306/cinelog?useSSL=false&serverTimezone=UTC
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
```

## ⚙️ Instalación y ejecución

1. Clona el repositorio:

```bash
git clone https://github.com/AlexandroG23/spring-boot-cinelog-tmdb.git
cd spring-boot-cinelog-tmdb
```

2. Crea tu archivo `.env` con las variables de configuración:

```bash
cp .env.example .env
```

3. Agrega tus credenciales de MySQL en el archivo `.env`:

```env
DB_URL=jdbc:mysql://localhost:{TU_PUERTO_DB}/cinelog?useSSL=false&serverTimezone=UTC
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
```

4. Levanta tu base de datos MySQL o Docker (opcional):

```bash
docker run --name mysql -e MYSQL_ROOT_PASSWORD=123456 -p 33060:3306 -d mysql:8.0
```

5. Ejecuta el proyecto:

```bash
./mvnw spring-boot:run
```

## 📂 Estructura del Proyecto

``` csharp
src
├── controller        # Controladores MVC
├── dto              # Objetos de transferencia de datos
├── model            # Entidades JPA
├── repository       # Interfaces de acceso a datos
├── service          # Lógica de negocio
├── security         # Configuración de autenticación y autorización
└── resources
    ├── templates    # Vistas Thymeleaf
    └── static       # Archivos estáticos (JS, CSS)
```