package vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity

enum class Direction(val value: Int) {
    TOP(-1),
    DEFAULT(0),
    BOTTOM(1);



    companion object {
        fun get(value:Int): Direction{
            return when (value){
                -1 -> TOP
                0 -> DEFAULT
                1 -> BOTTOM
                else -> throw IllegalArgumentException()
            }
        }
    }
}