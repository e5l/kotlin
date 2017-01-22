// WITH_RUNTIME
// WITH_COROUTINES
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.SUSPENDED_MARKER
import kotlin.coroutines.intrinsics.suspendCoroutineOrReturn

class MyTest {
    suspend fun act(value: String): String = suspendCoroutineOrReturn {
        it.resume(value)
        SUSPENDED_MARKER
    }
}

suspend fun <T> testAsync(routine: suspend MyTest.() -> T): T = routine(MyTest())

suspend fun Iterable<String>.test() = testAsync {
    var sum = ""
    for (v in this@test) {
        sum += act(v)
    }
    sum
}

fun builder(x: suspend () -> Unit) {
    x.startCoroutine(EmptyContinuation)
}

fun box(): String {
    var res = ""
    builder {
        res = listOf("O", "K").test()
    }

    return res
}
