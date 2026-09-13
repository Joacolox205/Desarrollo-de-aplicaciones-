# Prácticas Kotlin - Desarrollo de Aplicaciones

Aplicación nativa para Android construida con Kotlin y Jetpack Compose que integra y potencia las prácticas del curso *Desarrollo de Aplicaciones*:

## Módulos y Funcionalidades

1. **Clasificador de Cine & Recaudación de Entradas**
   - Clasificación por rangos de edad con sus tarifas correspondientes:
     - Niño (0-12 años): $1.500 CLP
     - Adolescente (13-17 años): $2.500 CLP
     - Adulto (18-59 años): $4.000 CLP
     - Adulto Mayor (60+ años): $2.000 CLP
     - Validación de edad negativa o inválida
   - Acumulador en tiempo real de espectadores y total recaudado
   - Modo de simulación de lote (5 espectadores) e historial completo de boletos emitidos

2. **Generador de Tablas de Multiplicar (1 al 12)**
   - Selector interactivo de tablas del 1 al 12
   - Detección visual y marcado especial para múltiplos de 5 (`*`)
   - Contador de progreso y metas de tablas completadas

3. **Gestor de Playlist Musical**
   - Colección interactiva de canciones
   - Agregar canción al final de la lista
   - Agregar canción en una posición específica (índice 0 a N)
   - Eliminación de canción por nombre o por acción directa
   - Búsqueda en tiempo real con indicador de coincidencia
   - Invertir orden de la playlist (`reverse()`)
   - Ordenar alfabéticamente (`sorted()`)

4. **Evaluador Académico & Operaciones**
   - Evaluación de estudiante, asignatura y calificación con umbral de aprobación (>= 4.0 Aprobado / < 4.0 Reprobado)
   - Generación de informe académico formateado
   - Clasificador de días de la semana con expresión `when` (días hábiles vs. fin de semana)
   - Calculadora aritmética basada en funciones Kotlin (`sumar(n1, n2)`)

## Tecnologías
- **Lenguaje:** Kotlin 2.2+
- **Interfaz:** Jetpack Compose con Material Design 3 (M3)
- **Arquitectura:** MVVM (Model-View-ViewModel) con StateFlow y ciclo de vida Compose
- **Compatibilidad:** Android SDK 26+ (Target SDK 36)
