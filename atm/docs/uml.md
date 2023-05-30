@startuml
left to right direction
actor User as u

package ATM {
    usecase "Crear cuenta vacía" as CreateEmpty
    usecase "Crear cuenta con saldo" as Create
    usecase "Ingresar dinero" as Deposit
    usecase "Retirar dinero" as Withdrawal
    usecase "Ver saldo" as CheckBalance
}

u --> CreateEmpty
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

enum MenuOption {
    +CREATE_EMPTY_ACCOUNT
    +CREATE_ACCOUNT
    +DEPOSIT
    +WITHDRAWAL
    +CHECK_BALANCE
    +EXIT
}

class ATM {
    -account: Account
    -br: BufferedReader
    -menu(): MenuOption
    -createAccount(Integer): String
    -deposit(Integer): String
    -withdraw(Integer): String
    -checkBalance(): String
    -logResult(String): void
    -getUserAmount(): Integer
    +run(): void
}

Class Main {
    +{static} main(String[]): void
}

ATM -- MenuOption
ATM *-- Account
Account -- Exception
Main -- ATM
@enduml
