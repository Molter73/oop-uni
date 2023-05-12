@startuml
enum Color {
    +RED
    +BLUE
    +GREEN
    +YELLOW
    +PINK
    +BROWN
    +WHITE
    +BLACK
    +EMPTY
}

class Peg {
    -Color color
    +Peg(Color)
    +Color getColor()
}

class Row {
    -Peg[] pegs
    +Row(Peg[])
    +Peg[] getPegs()
}

class Board {
    {static} Integer totalTries
    -ArrayList<Row> triedCodes
    -ArrayList<Row> keyCodes
    -Row hiddenCode
    -bool codeGuessed
    +Board()
    +bool isGameOver()
    +Row tryAnswer(Row)
    +Integer getFinalScore()
    +ArrayList<Row> getTriedCodes()
    +ArrayList<Row> getKeyCodes()
    +Row getHiddenCode()
}

class Game {
    -Integer score
    {static} main(String[])
    -Integer playGame()
    -ArrayList<Row> getRowFromPlayer()
}

Game -right- Board
Board *-- "25" Row
Row *-- "5" Peg
Peg *-- Color

@enduml
