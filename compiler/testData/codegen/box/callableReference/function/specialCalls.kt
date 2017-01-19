// WITH_RUNTIME

fun baz(i: Int) = i
fun <T> bar(x: T): T = x

fun nullableFun(): ((Int) -> Int)? = null

fun box(): String {
    val x1: (Int) -> Int = bar(if (true) ::baz else ::baz)
    val x2: (Int) -> Int = bar(nullableFun() ?: ::baz)
    val x3: (Int) -> Int = bar(::baz ?: ::baz)

    val i = 0
    val x4: (Int) -> Int = bar(when (i) {
                                   10 -> ::baz
                                   20 -> ::baz
                                   else -> ::baz
                               })

    val x5: (Int) -> Int = bar(::baz!!)

    assert((if (true) ::baz else ::baz)(1) == 1)

    assert(x1(1) == 1)
    assert(x2(2) == 2)
    assert(x3(3) == 3)
    assert(x4(4) == 4)
    assert(x5(5) == 5)

    return "OK"
}