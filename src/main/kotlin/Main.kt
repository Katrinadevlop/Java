fun main() {
    var min = 60
    println(agoToText(min))

    min = 360 //6 мин
    println(agoToText(min))

    min = 3600 //1 час
    println(agoToText(min))

    min = 36000 //10 часов
    println(agoToText(min))

    min = 100000 //1 сутки
    println(agoToText(min))
}

fun agoToText(time: Int): String {
    var result = ""

    when (time) {
        in 0..60 -> result = "был(а) только что"
        in 61..60 * 60 - 1 -> {
            when (time / 60) {
                1, 21, 31, 41, 51 -> result = "был(а) ${time / 60} минуту назад"
                in 2..4, in 22..24, in 32..34, in 42..44, in 52..54 -> result = "был(а) ${time / 60} минуты назад"
                in 5..9, in 25..29, in 35..39, in 45..49, in 55..59 -> result = "был(а) ${time / 60} минут назад"
                in 11..14 -> result = "был(а) ${time / 60} минут назад"
                else -> result = "был(а) ${time / 60} минут назад"
            }
        }

        in 60 * 60..24 * 60 * 60 -> {
            when (time / 3600) {
                1, 21 -> result = "был(а) ${time / 3600} час назад"
                in 2..4, in 22..24 -> result = "был(а) ${time / 3600} часа назад"
                in 5..20 -> result = "был(а) ${time / 3600} часов назад"
                else -> result = "был(а) ${time / 3600} часов назад"
            }
        }

        in 24 * 60 * 60 + 1..2 * 24 * 60 * 60 -> result = "был(а) вчера"
        in 2 * 24 * 60 * 60 + 1..3 * 24 * 60 * 60 -> result = "был(а) позавчера"
        in 3 * 24 * 60 * 60..100 * 24 * 60 * 60 -> result = "был(а) давно"
        else -> result = "был(а) очень давно"
    }

    return result
}