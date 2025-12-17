package isel.tds.exam_slotmachine_2324_t2.model

//private constructor
data class SlotState (val slot1: Byte, val slot2: Byte, val slot3: Byte) {

    init{
        require(slot1 in 0..9 && slot2 in 0..9 && slot3 in 0..9){
            "Slot values must be between 0 and 9"
        }
    }
    companion object{
        fun random(): SlotState{
            return SlotState(
                genRandomByteForSlot(),
                genRandomByteForSlot(),
                genRandomByteForSlot()
                )
        }
        private fun genRandomByteForSlot(): Byte = (0..9).random().toByte()

//        fun forTestsOnly_createSlotState(slot1,slot2,slot3) TODO
    }
}

fun SlotState.isWinner() = slot1==slot2 && slot2==slot3