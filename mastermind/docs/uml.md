@startuml
interface Color {
   +getColor(): String
}

enum PlayableColor implements Color {
    +RED
    +BLUE
    +GREEN
    +YELLOW
    +PINK
    +BROWN
}

enum KeyColor implements Color {
    +WHITE
    +BLACK
    +EMPTY
}

class Peg {
    -color: Color
    +Peg(Color)
    +getColor(): Color
}

class Row {
    -pegs: Peg[]
    +Row(Peg[])
    +getPegs(): Peg[]
}

class Board {
    {static} totalTries: Integer
    -triedCodes: ArrayList<Row>
    -keyCodes: ArrayList<Row>
    -hiddenCode: Row
    -codeGuessed: bool
    +isGameOver(): bool
    +tryAnswer(Row): Row
    +getFinalScore(): Integer
    +getTriedCodes(): ArrayList<Row>
    +getKeyCodes(): ArrayList<Row>
    +getHiddenCode(): Row
}

class Game {
    -score: Integer
    {static} main(String[])
    -playGame(): Integer
    -getRowFromPlayer(): ArrayList<Row>
}

Game -right- Board
Board *-- "25" Row
Row *-- "5" Peg
Peg *-- Color

@enduml
