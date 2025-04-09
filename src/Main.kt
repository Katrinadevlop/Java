class Account(sum: Int) {
    var totalSum: Int

    init {
        totalSum = sum + bonus
    }

    companion object {
        var bonus: Int = 100
    }
}

fun main() {
    print(Account.bonus)
    Account.bonus += 200

    val account1 = Account(150)
    print(account1.totalSum)

    val account2 = Account(10)
    print(account2.totalSum)
}