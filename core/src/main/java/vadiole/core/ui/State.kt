package vadiole.core.ui

class State<T>(init: T) {
    private val handlers = mutableListOf<(T) -> Unit>()
    private var _value = init
    val value = _value

    fun listen(last: Boolean = true, handler: (T) -> Unit) {
        handlers.add(handler)
        if (last) handler.invoke(_value)
    }

    fun listen2(last: Boolean = true, handler: T.() -> Unit) {
        handlers.add(handler)
        if (last) handler.invoke(_value)
    }

    fun update(v: T) {
        _value = v
        handlers.forEach {
            it.invoke(v)
        }
    }
}