@startuml

class Asignatura {
    -String titulo
    -int creditos
    -String profesor
}

class Curso {
    -String titulacion
    +bool inscribir(Estudiante)
}

class Estudiante {
    -String tutor
    -String titulacion
    +int obtenerPromedio()
    +Asignatura[] listarAsignaturasAprobadas()
}

class Profesor {
    +int evaluar(Asignatura, Estudiante)
}

Curso *--- "1..n" Asignatura
Curso *-- "20" Estudiante
Profesor "n" *--* "1" Asignatura
Profesor -- Estudiante

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
