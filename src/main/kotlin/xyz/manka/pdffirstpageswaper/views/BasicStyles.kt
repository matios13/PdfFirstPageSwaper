package xyz.manka.pdffirstpageswaper.views

import javafx.scene.effect.DropShadow
import javafx.scene.paint.*
import tornadofx.Stylesheet
import tornadofx.multi
import tornadofx.px
import xyz.manka.pdffirstpageswaper.PRIMARY_COLOUR
import xyz.manka.pdffirstpageswaper.SECONDARY_COLOUR
import xyz.manka.pdffirstpageswaper.SHADOW_COLOUR

class BasicStyles : Stylesheet() {


    init {
        button {
            fontSize = 18.px
            //effect = DropShadow(10.0,Color.BLUE)
            and(hover) {
                effect = DropShadow(10.0, Color.valueOf(SHADOW_COLOUR))
                println("hover")
            }

            textFill = Paint.valueOf("#FFFFFF")
            backgroundColor = multi(
                LinearGradient(
                    0.0, 0.0, 90.0, 90.0, false, CycleMethod.NO_CYCLE,
                    Stop(0.0, Color.valueOf(PRIMARY_COLOUR)), Stop(1.0, Color.valueOf(SECONDARY_COLOUR))
                )
            )

        }
    }
}