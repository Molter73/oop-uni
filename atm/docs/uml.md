@startuml
left to right direction
actor User as u

package ATM {
    usecase "Crear cuenta" as Create
    usecase "Ingresar dinero" as Deposit
    usecase "Retirar dinero" as Withdrawal
    usecase "Ver saldo" as CheckBalance
}

u --> Create
u --> Deposit
u --> Withdrawal
u --> CheckBalance
@enduml

@startuml
class InsufficientBalanceException extends Exception {
    +InsufficientBalanceException(String, String)
}

class NegativeBalanceException extends Exception {
    +NegativeBalanceException(String)
}

class Account {
    -balance: Integer
    +Account(Integer)
    +getBalance(): Integer
    +deposit(Integer): void
    +withdraw(Integer): void
    +toString(): String
    +{static} amountToString(Integer): String
}

enum State {
    +CREATE_ACCOUNT
    +DEPOSIT
    +WITHDRAWAL
    +CHECK_BALANCE
    +MAIN_MENU
    +ERROR
    +CONFIRMATION
}

class ATM {
    -account: Account
    -state: State
    -amount: Integer
    -screenMessage: String
    -handleBackToMain: EventHandler<ActionEvent>
    -handleAccept: EventHandler<ActionEvent>
    -handleAccountCreation: EventHandler<ActionEvent>
    -handleDeposit: EventHandler<ActionEvent>
    -handleWithdrawl: EventHandler<ActionEvent>
    -handleBalance: EventHandler<ActionEvent>
    -handleKeyTyped: EventHandler<KeyEvent>
    -atmFrame: HBox
    -buttonA: Button
    -buttonB: Button
    -buttonC: Button
    -buttonD: Button
    -screen: TextArea
    +initialize(): void
    -mainMenu(): void
    -acceptOrBack(String): void
    -genericBackScreen(String): void
    -errorScreen(String): void
    -confirmationScreen(String): void
}

Class App extends javafx.application.Application {
    +start(Stage): void
    +{static} main(String[]): void
}

ATM *-- Account
ATM *-- State
Account -- Exception
App -- ATM
@enduml
