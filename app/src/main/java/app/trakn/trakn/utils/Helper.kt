package app.trakn.trakn.utils

object Helper {
    fun String.times(times: Int): CharSequence {
        var result = this
        for (i in 0..times) {
            result += this
        }
        return result
    }
}