fun main() {
    val payMethod = "Visa"
    var lastPayment = 75000
    val transaction = 5000

    println("Комиссия составила ${calculate(payMethod, lastPayment, transaction)} рублей")
}

fun calculate(payMethod: String, lastPayment: Int, transaction: Int): Int {
    val minCommission = 35
    val commissionPercent = 0.75

    val commission = when (payMethod) {
        "Mastercard", "Maestro" -> {
            if (lastPayment > 75000) {
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