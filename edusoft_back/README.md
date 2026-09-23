# 📚 Proyecto Integrador SURA 8 - Spring Boot

## 🧩 Descripción
Este proyecto corresponde al **Proyecto Integrador SURA 8**, desarrollado con **Spring Boot**, cuyo objetivo es la creación de una **API REST** para la administración de una **plataforma educativa** 🧑‍🎓👩‍🏫.  

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
Proyecto desarrollado como parte del grupo SURA 8 CESDE.

¡Gracias por visitar el repositorio! ⭐
