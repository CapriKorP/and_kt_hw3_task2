fun main() {
    val payMethod = "Mastercard"
    var lastPayment = 600000
    val transaction = 15000
    println(calculate(payMethod, lastPayment, transaction))
}

fun calculate(payMethod: String = "VK Pay", lastPayment: Int = 0, transaction: Int): String {
    val maxMonthTransaction = 75000
    val minCommission = 35
    val commissionPercent = 0.75
    val dailyLimitVkPay = 15000
    val monthLimitVkPay = 40000
    val dailyLimitCard = 150000
    val monthLimitCard = 600000
    val stringCommission = "Комиссия составила"
    val stringRubbles = "руб."
    val stringDayLimitVk = "Превышен суточный лимит при оплате через VK Pay"
    val stringMonthLimitVk = "Превышен месячный лимит при оплате через VK Pay"
    val stringDayLimitCard = "Превышен суточный лимит при оплате картой"
    val stringMonthLimitCard = "Превышен месячный лимит при оплате картой"
    val stringZeroCommission = "Комиссия составила 0 рублей"


    var commission: String = when (payMethod) {
        "Mastercard", "Maestro" -> {
            if (lastPayment + transaction > maxMonthTransaction) {
                "$stringCommission ${(transaction * 0.6 / 100) + 20} $stringRubbles"
            } else {
                "$stringZeroCommission"
            }
        }
        "Visa", "МИР" -> "$stringCommission ${if (transaction * commissionPercent / 100 <= minCommission) minCommission.toDouble() else transaction * commissionPercent / 100} $stringRubbles"
        "VK Pay" -> "$stringZeroCommission"
        else -> "Ошибка перевода!!!!!"
    }

    if (payMethod == "VK Pay" && transaction > dailyLimitVkPay) {
        commission = stringDayLimitVk
    }
    if (payMethod == "VK Pay" && lastPayment + transaction > monthLimitVkPay) {
        commission = stringMonthLimitVk
    }
    if (payMethod != "VK Pay" && transaction > dailyLimitCard) {
        commission = stringDayLimitCard
    }
    if (payMethod != "VK Pay" && lastPayment + transaction > monthLimitCard) {
        commission = stringMonthLimitCard
    }
    return commission
}