package isel.tds.exam_slotmachine_2324_t2.view

import exam_slotmachine_2324_t2.composeapp.generated.resources.Res
import exam_slotmachine_2324_t2.composeapp.generated.resources.apple
import exam_slotmachine_2324_t2.composeapp.generated.resources.banana
import exam_slotmachine_2324_t2.composeapp.generated.resources.bar
import exam_slotmachine_2324_t2.composeapp.generated.resources.bell
import exam_slotmachine_2324_t2.composeapp.generated.resources.cherries
import exam_slotmachine_2324_t2.composeapp.generated.resources.diamond
import exam_slotmachine_2324_t2.composeapp.generated.resources.grappes
import exam_slotmachine_2324_t2.composeapp.generated.resources.lemon
import exam_slotmachine_2324_t2.composeapp.generated.resources.seven
import exam_slotmachine_2324_t2.composeapp.generated.resources.watermelon
import org.jetbrains.compose.resources.DrawableResource

enum class SlotImage(val resource: DrawableResource) {
    APPLE(Res.drawable.apple),
    BAR(Res.drawable.bar),
    BELL(Res.drawable.bell),
    CHERRIES(Res.drawable.cherries),
    DIAMOND(Res.drawable.diamond),
    GRAPPES(Res.drawable.grappes),
    LEMON(Res.drawable.lemon),
    SEVEN(Res.drawable.seven),
    WATERMELON(Res.drawable.watermelon),
    BANANA(Res.drawable.banana);
}

fun Byte.toSlotImageResource(): DrawableResource {
    return when (this) {
        0.toByte() -> SlotImage.APPLE.resource
        1.toByte() -> SlotImage.BAR.resource
        2.toByte() -> SlotImage.BELL.resource
        3.toByte() -> SlotImage.CHERRIES.resource
        4.toByte() -> SlotImage.DIAMOND.resource
        5.toByte() -> SlotImage.GRAPPES.resource
        6.toByte() -> SlotImage.LEMON.resource
        7.toByte() -> SlotImage.SEVEN.resource
        8.toByte() -> SlotImage.WATERMELON.resource
        9.toByte() -> SlotImage.BANANA.resource
        else -> throw kotlin.IllegalArgumentException("Invalid slot value: $this")
    }
}