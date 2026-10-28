# 📚 EduSoft - API REST con Spring Boot

## 🧩 Descripción
Este proyecto corresponde a **EduSoft**, una **API REST** desarrollada con **Spring Boot** para la administración de una **plataforma educativa** 🧑‍🎓👩‍🏫.

La API permite gestionar la información mediante una arquitectura en capas, siguiendo buenas prácticas de desarrollo backend.

---

## 🎯 Objetivo
Desarrollar una API REST que permita:
- Administrar los datos de la plataforma educativa
- Implementar una arquitectura limpia y escalable
- Aplicar el patrón de capas usando Spring Boot
- Exponer endpoints básicos para la gestión de información

---

## 🛠️ Elementos Técnicos
El proyecto está construido utilizando los siguientes componentes y tecnologías:

- ☕ **Java**
- 🌱 **Spring Boot**
- 🗄️ **Spring Data JPA**
- 🧱 **Arquitectura en capas**
- 🧪 **Postman / Swagger (para pruebas de API)**

### 📐 Arquitectura en Capas
El proyecto está organizado en las siguientes capas:

- **Modelos (Models)**  
  Contienen las entidades que representan la información de la plataforma educativa.

- **Repositorios (Repositories)**  
  Encargados de la comunicación con la base de datos.

- **Servicios (Services)**  
  Contienen la lógica de negocio de la aplicación.

- **Controladores (Controllers)**  
  Exponen los endpoints REST para el consumo de la API.

---

## 🔗 Endpoints Disponibles
Todos los recursos de la API usan el prefijo `/apiedusoft/v1`. Entre los recursos disponibles están usuarios, profesores, cursos, matrículas, asistencias, notas, notificaciones y reportes.

La API cuenta con los siguientes endpoints principales:

- 🟢 **Guardar entidad**  
  `POST /guardar`  
  Permite registrar un nuevo recurso en la plataforma educativa.

- 🔵 **Buscar todos**  
  `GET /listar`  
  Retorna la lista completa de registros almacenados.

- 🟣 **Buscar por ID**  
  `GET /buscar/{id}`  
  Retorna un registro específico según su identificador.

---

## 🚀 Estado del Proyecto
✅ En desarrollo / Finalizado para entrega académica  

---

## 🤝 Autor
Proyecto desarrollado para la plataforma educativa EduSoft.

¡Gracias por visitar el repositorio! ⭐
