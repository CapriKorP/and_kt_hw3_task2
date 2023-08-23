fun main() {
    val payMethod = "Visa"
    var lastPayment = 75000
    val transaction = 10000

    println("Комиссия составила ${calculate(payMethod, lastPayment, transaction)} рублей")
}

fun calculate(payMethod: String = "VK Pay", lastPayment: Int = 0, transaction: Int): Int {
    val maxMonthTransaction = 75000
    val minCommission = 35
    val commissionPercent = 0.75
    val dailyLimitVkPay = 15000
    val monthLimitVkPay = 40000
    val dailyLimitCard = 150000
    val monthLimitCard = 600000

    val commission = when (payMethod) {
        "Mastercard", "Maestro" -> {
            if (lastPayment + transaction > maxMonthTransaction) {
                (transaction * 0.6 / 100) + 20
            } else {
                0
            }
        }
        "Visa", "МИР" -> if (transaction * commissionPercent / 100 <= minCommission) minCommission.toDouble() else transaction * commissionPercent / 100
        "VK Pay" -> 0
        else -> 0
    }
    return commission.toInt()
}