package ru.netology

import kotlin.math.*

const val sumLimDay = 150000
const val sumLimMonth = 600000

fun main() {
    typeCard("Mastercard", 1000, 100000)
    typeCard("Mastercard", 500, 0)
    typeCard("Visa", 3000, 100000)
    typeCard("Visa", 10000, 200000)
    typeCard("Мир", 2000, 300000)
    typeCard("Visa", 160000, 0)
}

fun typeCard(nameCard: String, sum: Int, sumMonth: Int = 0): String {
    return if (sumMonth <= sumLimMonth) {
        if (sum <= sumLimDay) {
            when (nameCard) {
                "Mastercard" -> {
                    transferMastercardAndMaestro(sum, sumMonth)
                }

                "Maestro" -> {
                    transferMastercardAndMaestro(sum, sumMonth)
                }

                "Visa" -> {
                    transferVisaAndMir(sum)
                }

                "Мир" -> {
                    transferVisaAndMir(sum)
                }

                "VK Pay" -> {
                    if (sum > 15_000 || sumMonth + sum > 40_000) {
                        "Операция блокируется, превышен лимит VK Pay"
                    } else {
                        "Комиссии нет"
                    }
                }
                else -> { "Такой карты нет" }
            }
        } else {
             "Операция блокируется, превышен лимит на сутки"
        }
    } else {
         "Операция блокируется, превышен лимит на месяц"
    }
}

fun transferMastercardAndMaestro(sum: Int, sumMonth: Int = 0): String  {
    if (sum < 300 || sumMonth + sum > 75000) {
        val commission = sum * 0.006 + 20
        return "Комиссия: $commission"
    } else {
        return "Комиссии нет"
    }
}

fun transferVisaAndMir(sum: Int): String  {
    val commission = max(sum * 0.0075, 35.0)
    return "Комиссия: $commission"
}