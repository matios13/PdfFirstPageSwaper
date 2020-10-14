package xyz.manka.pdffirstpageswaper.app

import xyz.manka.pdffirstpageswaper.views.BasicStyles
import xyz.manka.pdffirstpageswaper.views.BasicView
import tornadofx.App
import tornadofx.launch

class MyApp : App(BasicView::class, BasicStyles::class)

fun main(args: Array<String>) {
    launch<MyApp>(args)
}