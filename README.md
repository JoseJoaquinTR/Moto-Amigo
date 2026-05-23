# Moto Amigo

Sistema de entregas a domicilio en moto, estilo Uber para repartidores. Proyecto académico desarrollado en Java con arquitectura multicapa, MongoDB como base de datos y Swing para la interfaz gráfica.

Moto Amigo conecta tres tipos de usuarios:

- **Emprendedores** que necesitan enviar paquetes o sobres a sus clientes.
- **Repartidores** que aceptan solicitudes y realizan las entregas en moto.
- **Administradores** que gestionan altas, bloqueos y reportes del sistema.

---

## Tabla de contenido

- [Características principales](#características-principales)
- [Arquitectura](#arquitectura)
- [Tecnologías](#tecnologías)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Patrones de diseño aplicados](#patrones-de-diseño-aplicados)
- [Requisitos previos](#requisitos-previos)
- [Instalación y ejecución](#instalación-y-ejecución)
- [Casos de uso implementados](#casos-de-uso-implementados)
- [Pruebas](#pruebas)
- [Autores](#autores)

---

## Características principales

- Registro y autenticación de emprendedores y repartidores con contraseñas hasheadas (BCrypt).
- Catálogo de productos y paquetes con imágenes, asociados al emprendedor.
- Solicitud de entregas con dos modalidades: paquetes (de uno o más productos) o sobres con peso libre.
- Cálculo automático de ruta, distancia y costo de envío vía integración con la API de MapBox.
- Seguimiento en tiempo real de la entrega para el emprendedor mientras el repartidor avanza.
- Sistema de incidentes y reportes para casos donde la entrega no puede completarse.
- Generación de reportes PDF del historial de paquetes utilizando JasperReports.
- Notificaciones en tiempo real entre repartidores y emprendedores mediante el patrón Observer.

---

## Arquitectura

El proyecto sigue una arquitectura **multicapa estricta** con casos de uso aislados:

```
Presentación → Control (presentación) → CU (interface) → IFachadaNegocio
                                                              ↓
                                              BO → IFachadaPersistencia → DAO → Entidad → MongoDB
```

Reglas de capas que se respetan en todo el código:

- La capa de **Presentación nunca importa Negocio directamente**, solo se comunica con casos de uso a través de interfaces.
- Los **BO no conocen los DAO directamente**, hablan con ellos a través de la `IFachadaPersistencia`.
- Cada caso de uso vive en su propio módulo Maven, lo que permite trabajar en paralelo sin pisar código entre desarrolladores.

---

## Tecnologías

| Componente | Tecnología |
|------------|------------|
| Lenguaje | Java 17 |
| Build | Maven (multi-módulo) |
| Base de datos | MongoDB 5+ |
| Driver Mongo | `mongodb-driver-sync` 4.11.1 |
| Interfaz gráfica | Java Swing + FlatLaf |
| Mapas y rutas | MapBox API |
| Reportes | JasperReports |
| Hash de contraseñas | jBCrypt 0.4 |
| Serialización auxiliar | Gson 2.10.1 |
| Pruebas | JUnit 5 |

---

## Estructura del proyecto

```
Moto amigo/
├── MotoAmigPOM/                 # POM padre que agrupa los módulos
├── MotoAmigoDto/                # DTOs y enums compartidos entre capas
├── MotoAmigoPersistencia/       # Entidades, DAOs y FachadaPersistencia
├── MotoAmigoNegocio/            # BOs, Adapters DTO↔Entidad, FachadaNegocio, Observers
├── Infraestructura/             # Integración con MapBox y servicios externos
├── CUEmprendedor/               # Caso de uso: registro de emprendedores
├── CURepartidor/                # Caso de uso: gestión de repartidores
├── CUPaquetes/                  # Caso de uso: CRUD de paquetes
├── CUProductos/                 # Caso de uso: CRUD de productos
├── CUSolicitarEntrega/          # Caso de uso: solicitar/aceptar/finalizar entrega
├── CUIncidente/                 # Caso de uso: registro de incidentes
├── CUBloqueos/                  # Caso de uso: bloqueos administrativos
└── MotoAmigoPresentacion/       # Frms Swing, controles y Mains por rol
```

Cada módulo es un proyecto Maven independiente que declara sus propias dependencias y se enlaza con los demás como dependencia interna.

---

## Patrones de diseño aplicados

- **Singleton** en BOs, DAOs, Controles, Sesión y Observers, para evitar instanciar recursos costosos como la conexión a Mongo.
- **Adapter** para convertir DTO ↔ Entidad en el módulo Negocio (`Adapter/AdapterXxx.java`), de manera que las capas no compartan tipos.
- **Facade** con `FachadaPersistencia` y `FachadaNegocio`, que actúan como punto de entrada único a cada capa.
- **Observer** con dos implementaciones distintas:
  - `PaquetesBO → ObserverCambiosPaquete → ControlPaquetes → Frms` para reflejar cambios en la UI cuando se editan paquetes.
  - `GestorNotificacionesEntrega` para notificar eventos como `PEDIDO_DISPONIBLE`, `PEDIDO_ACEPTADO`, `PEDIDO_ENTREGADO` y `UBICACION_ACTUALIZADA`.
- **DAO** clásico para encapsular el acceso a MongoDB con interfaces (`IPaquetesDAO`, `IEntregasDAO`, etc.) y excepciones específicas (`PersistenciaException`).

---

## Requisitos previos

- **Java JDK 17** o superior.
- **Apache Maven 3.8+**.
- **MongoDB Community Server** corriendo localmente en `mongodb://localhost:27017`.
- **MongoDB Compass** o **mongosh** para inspeccionar la base de datos (opcional pero recomendado).
- **NetBeans 17+** si se quiere trabajar con los `.form` de Swing.

---

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/<usuario>/moto-amigo.git
cd moto-amigo
```

### 2. Levantar MongoDB

Asegúrate de que el servicio `mongod` esté corriendo. La base de datos `MotoAmigoDB` se crea automáticamente la primera vez que se inserta un documento.

### 3. Compilar todo el proyecto

Desde la carpeta raíz, ejecuta el POM padre:

```bash
cd MotoAmigPOM
mvn clean install
```

Esto compila los módulos en el orden correcto y deja los `.jar` en el repositorio local de Maven.

### 4. Cargar datos de prueba (opcional)

En `mongosh`, dentro de la base `MotoAmigoDB`, puedes cargar los inserts incluidos en `/scripts/seed-emprendedores.js` para tener 20 emprendedores listos:

```bash
mongosh
use MotoAmigoDB
load("scripts/seed-emprendedores.js")
```

### 5. Ejecutar la aplicación

Cada rol tiene su propio `Main` dentro del módulo `MotoAmigoPresentacion`:

| Rol | Clase Main |
|-----|------------|
| Emprendedor | `Main.MainEmprendedor` |
| Repartidor | `Main.MainRepartidor` |
| Administrador | `Main.MainAdmin` |

Para correr cualquiera de ellos desde Maven:

```bash
cd MotoAmigoPresentacion
mvn exec:java -Dexec.mainClass="Main.MainEmprendedor"
```

O desde NetBeans, clic derecho sobre el `Main` deseado → **Run File**.

> Nota: Los Mains de emprendedor y repartidor corren como procesos JVM independientes. La comunicación de ubicación entre ambos se hace temporalmente vía archivo `ubicacion_repartidor.txt` mientras se implementa el módulo de login real.

---

## Casos de uso implementados

| Caso de uso | Estado | Descripción |
|-------------|--------|-------------|
| Registrar emprendedor | Completo | Alta de emprendedor con imagen, documento y cuenta bancaria. |
| Iniciar sesión | Completo | Autenticación con BCrypt. |
| CRUD de productos | Completo | Alta, edición, eliminación y consulta de productos con imagen. |
| CRUD de paquetes | Completo | Paquetes compuestos por productos, con cálculo automático de peso. |
| Historial PDF | Completo | Reporte de paquetes en PDF generado con JasperReports. |
| Solicitar entrega | Completo | Emprendedor publica solicitud con origen, destino y tipo (paquete/sobre). |
| Aceptar entrega | Completo | Repartidor visualiza pedidos disponibles y los acepta. |
| Seguimiento en tiempo real | En progreso | Mapa que se actualiza con la ubicación del repartidor cada 3 segundos. |
| Finalizar entrega | En progreso | Al llegar al destino, la entrega pasa a estado `COMPLETADA`. |
| Registrar incidente | Completo | Repartidor reporta motivo si no puede completar la entrega. |
| Bloqueos administrativos | Completo | Admin puede bloquear emprendedores o repartidores. |

---

## Pruebas

El módulo `MotoAmigoPersistencia` cuenta con pruebas unitarias para los DAOs principales usando JUnit 5:

- `PaquetesDAOTest` — 12 pruebas que cubren CRUD y consultas con filtros.
- `ProductosDAOTest` — 12 pruebas equivalentes para productos.
- `EntregasDAOTest` — 11 pruebas para el flujo de entregas.

Para correr todas las pruebas del módulo:

```bash
cd MotoAmigoPersistencia
mvn test
```

Las pruebas usan una base de datos real (`MotoAmigoDB` local), así que requieren tener MongoDB corriendo.

---

## Autores

- **Joset Hernández** — Persistencia, paquetes, productos, solicitud de entrega.
- **Jesús Omar** — Emprendedores, repartidores, administración, bloqueos.

Proyecto desarrollado como parte del curso de Arquitectura de Software del Instituto Tecnológico de Sonora.

---

## Licencia

Proyecto académico sin fines comerciales. Uso libre con fines educativos.
