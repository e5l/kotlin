// IGNORE_BACKEND: JS, NATIVE

// FILE: Hello.java

import kotlin.internal.RedirectCall;

public class Hello {
    public static int y(Hello hello, int a, int b) {
        return hello.x(a, b) + 1;
    }

    @RedirectCall(owner = "Hello", name = "y", signature = "(LHello;II)I")
    public int x(int a, int b) {
        return b + a;
    }
}

// FILE: main.kt

fun box(): String {
    val hello = Hello()

    val x = hello.x(1, 2)

    if (x != 4) {
        return "FAIL"
    }

    return "OK"
}
