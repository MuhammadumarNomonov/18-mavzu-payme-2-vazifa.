import java.util.Scanner
fun main() {
    val input = Scanner(System.`in`)
    val payme = PaymeSystem()

    println("Kart raqamini kiriting: ")
    val cardNumber1 = input.next()
    println("Boshlang'ich balansni kiriting: ")
    val balance1 = input.nextDouble()
    payme.addCard(cardNumber1, balance1)

    println("Yana bir kart raqamini kiriting: ")
    val cardNumber2 = input.next()
    println("Boshlang'ich balansni kiriting: ")
    val balance2 = input.nextDouble()
    payme.addCard(cardNumber2, balance2)

    println("Qaysi kartadan pul yechilsin? 1 yoki 2: ")
    val fromCardIndex = input.nextInt()
    println("Qaysi kartaga pul o'tkazilsin? 1 yoki 2: ")
    val toCardIndex = input.nextInt()
    println("Ko'chirmoqchi bo'lgan summani kiriting: ")
    val amount = input.nextDouble()
    payme.transferMoney(fromCardIndex, toCardIndex, amount)
}

data class Card(val cardNumber: String, var balance: Double)

class PaymeSystem {
    val cards = mutableListOf<Card>()

    fun addCard(cardNumber: String, balance: Double) {
        cards.add(Card(cardNumber, balance))
        println("Karta qo'shildi: $cardNumber")
    }

    fun transferMoney(fromCardIndex: Int, toCardIndex: Int, amount: Double) {
        if (fromCardIndex !in 1..cards.size || toCardIndex !in 1..cards.size) {
            println("Noto'g'ri karta indeksi.")
            return
        }
        val fromCard = cards[fromCardIndex - 1]
        val toCard = cards[toCardIndex - 1]

        if (fromCard.balance < amount) {
            println("Yetarli mablag' mavjud emas.")
            return
        }

        fromCard.balance -= amount
        toCard.balance += amount
        println("Ko'chirildi: $amount ${fromCard.cardNumber} dan ${toCard.cardNumber} ga")
        println("${fromCard.cardNumber}: ${fromCard.balance}")
        println("${toCard.cardNumber}: ${toCard.balance}")
    }
}

