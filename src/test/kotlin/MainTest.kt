import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.netology.typeCard
import ru.netology.transferVisaAndMir
import ru.netology.transferMastercardAndMaestro

class MainTest {
    @Test
    fun typeCard() {
        var nameCard = "Mastercard"
        var sum = 500
        var sumMonth = 0
        var result = typeCard(nameCard, sum, sumMonth)
        assertEquals("Комиссии нет", result)

        nameCard = "Maestro"
        sum = 1000
        sumMonth = 100000
        result = typeCard(nameCard, sum, sumMonth)
        assertEquals("Комиссия: 26.0", result)

        nameCard = "Visa"
        sum = 2000
        sumMonth = 300000
        result = typeCard(nameCard, sum, sumMonth)
        assertEquals("Комиссия: 35.0", result)

        nameCard = "Мир"
        sum = 2000
        sumMonth = 300000
        result = typeCard(nameCard, sum, sumMonth)
        assertEquals("Комиссия: 36.0", result)

        nameCard = "VK Pay"
        sum = 1000
        sumMonth = 10000
        result = typeCard(nameCard, sum, sumMonth)
        assertEquals("Комиссии нет", result)
    }

    @Test
    fun transferMastercardAndMaestro() {
        var sum = 500
        var sumMonth = 0
        var result = transferMastercardAndMaestro(sum, sumMonth)
        assertEquals("Комиссии нет", result)

        sum = 1000
        sumMonth = 100000
        result = transferMastercardAndMaestro(sum, sumMonth)
        assertEquals("Комиссия: 26.0", result)
    }

    @Test
    fun transferVisaAndMir() {
        val sum = 2000
        val result = transferVisaAndMir(sum)
        assertEquals("Комиссия: 35.0", result)
    }
}