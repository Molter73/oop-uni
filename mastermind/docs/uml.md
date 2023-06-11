@startuml
interface Color {
   +getColor(): String
   +useColors: boolean
}

enum PlayableColor implements Color {
    +RED
    +BLUE
    +GREEN
    +YELLOW
    +PINK
    +BROWN
    +getRandom(): Color
}

enum KeyColor implements Color {
    +WHITE
    +BLACK
    +EMPTY
}

class Peg {
    -color: Color
    +Peg(Color)
    +toString(): String
    +equals(Object): boolean
}

class Row {
    -pegs: Peg[]
    +Row(Peg[])
    +getPegs(): Peg[]
    +toString(): String
    +equals(Object): boolean
}

class Board {
    {static} totalTries: Integer
    -triedCodes: ArrayList<Row>
    -keyCodes: ArrayList<Row>
    -hiddenCode: Row
    -codeGuessed: boolean
    +isGameOver(): boolean
    +tryAnswer(Row): Row
    +getFinalScore(): Integer
    +getTriedCodes(): ArrayList<Row>
    +getKeyCodes(): ArrayList<Row>
    +getHiddenCode(): Row
    +toString
}

class UserInterface {
    -br: BufferedReader
    +getRounds(): Integer
    +getRowFromPlayer(): Row
    +printBoard(Board): void
}

class App {
    -ui: UserInterface
    -score: Integer
    {static} main(String[])
    -playGame(): Integer
}

App -- Board
App *-- UserInterface
Board *-- "25" Row
Row *-- "5" Peg
Peg *-- Color

@enduml
