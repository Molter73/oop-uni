@startuml

class Asignatura {
    -String titulo
    -int creditos
    -String profesor
    -int calificacionFinal
}

class Curso {
    -String titulacion
    +bool inscribir(Estudiante)
}

class Estudiante {
    -String tutor
    -String titulacion
    -Asignatura[] asignaturasAprobadas
    +Asignatura[] listarAsignaturasAprobadas()
}

class Profesor {
    +int evaluar(Asignatura, Estudiante)
}

class Tutor {
    +int obtenerPromedio(Estudiante)
}

Curso *--- "1..n" Asignatura
Curso *-- "20" Estudiante
Profesor "n" *--* "1" Asignatura
Profesor -- Estudiante
Tutor -- Estudiante

@enduml

@startuml
(*top) --> "Solicitar inscrpción"
if "Curso lleno?" then
  -->[Si] "Rechazar inscripción"
  -> (*)
else
  -> [No] "Validar Matricula"
  if "Curso != 1 && matriculaInsc != matriculaAlumno" then
    --> [Si] "Rechazar inscripción"
  else
  --> [No] "Validar cursos previos"
    if "Curso != 1 && !CursosPrevios" then
    --> [Si] "Rechazar inscripción"
    else
    -> [No] "Aprobar inscripción"
    -> "Asignar tutor"
    -> (*)
endif
@enduml

@startuml
:Alumno: -> (Anotarse en curso)
:Profesor: -> (Evaluar alumno)
(Evaluar alumno) --> :Alumno:
:Tutor: -> (Conseguir nota media)
(Conseguir nota media) --> :Alumno:
@enduml
