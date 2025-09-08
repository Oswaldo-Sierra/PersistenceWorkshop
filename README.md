# PersistenceWorkshop
Este proyecto es una implementación en **Java** de un sistema de gestión de parqueadero,
realizado como parte del taller de persistencia de datos.

## Descripción

El sistema permite registrar vehículos, controlar su ingreso y salida del parqueadero,
gestionar usuarios, y generar reportes de uso y dinero recaudado.  
La información se maneja con diferentes mecanismos de persistencia:

- **Usuarios:** Serialización (formato `.ser`).
- **Vehículos:** Persistencia en **XML**.
- **Registros de parqueo:** Persistencia en **JSON**.

## Funcionalidades

### Gestión de usuarios
- Registro de nuevos usuarios (username + password).
- Inicio de sesión con validación de credenciales.
- Evita la creación de usuarios duplicados.

### CRUD de vehículos
- **Añadir** vehículo (placa, tipo, dueño, modelo, color).
- **Eliminar** vehículo (solo si no tiene registros de parqueo activos).
- **Actualizar** dueño y/o color.
- **Consultar** vehículos registrados.

### CRUD de registros de parqueo
- **Añadir** registro de entrada (validando: vehículo existente, no duplicado, cupos disponibles).
- **Eliminar** registro de parqueo (solo si no tiene salida registrada).
- **Actualizar** registro → asignar fecha/hora de salida y calcular total a pagar.
- **Consultar** registros actuales:
  - Número de vehículos ingresados en un día dado
  - Total de dinero recaudado en un día dado

## Validaciones implementadas
- Control de **cupos disponibles** al ingresar un vehículo.
- No permitir duplicar placas en vehículos.
- No permitir ingresar un vehículo que ya está dentro del parqueadero.
- Fecha de **salida debe ser posterior a la entrada** (validado en `RecordParking`).
- No se permite eliminar un vehículo con registros de parqueo activos.
- Manejo de errores por formato de fecha inválido.
- Mensajes claros cuando no hay registros o reportes disponibles.

## Tecnologías utilizadas
- Colecciones (`ArrayList`)
- API de fechas: `LocalDateTime`, `LocalDate`, `DateTimeFormatter`
- Persistencia:
  - Serialización (`.ser`)
  - XML
  - JSON

## Estructura del proyecto
- `Model/` → Clases principales (`Vehicle`, `RecordParking`, `User`).
- `Controller/` → Lógica de negocio (`Parking`).
- `Persistence/` → Manejo de persistencia en diferentes formatos.
- `GUI/` → Clase `View`, interfaz por consola para interactuar con el sistema.

## Ejecución
Compilar y ejecutar desde la clase `app`:

## Autor
Proyecto desarrollado como parte de un taller académico en la UPTC.
