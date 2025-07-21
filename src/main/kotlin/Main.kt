package ru.netology

import kotlin.math.*

const val sumLimDay = 150000
const val sumLimMonth = 600000

fun main() {
    typeCard("Mastercard", 10000, 74000)
    typeCard("Mastercard", 5000, 20000)
    typeCard("Visa", 3000, 100000)
    typeCard("Visa", 10000, 200000)
    typeCard("Мир", 10000, 200000)
    typeCard("Visa", 160000, 0)
}

fun typeCard(nameCard: String, sum: Int, sumMonth: Int = 0) {
    if (sumMonth <= sumLimMonth) {
        if (sum <= sumLimDay) {
            when (nameCard) {
                "Mastercard" -> {
                    if (sumMonth + sum > 75000) {
                        val commission = sum * 0.006 + 20
                        println("Комиссия: $commission")
                    } else {
                        println("Комиссии нет")
                    }
                }

                "Visa" -> {
                    val commission = max(sum * 0.0075, 35.0)
                    println("Комиссия: $commission")
                }

                "Мир" -> println("Комиссии нет")
            }
        } else {
            println("Операция блокируется, превышен лимит на сутки")
        }
    } else {
        println("Операция блокируется, превышен лимит на месяц")
    }
}