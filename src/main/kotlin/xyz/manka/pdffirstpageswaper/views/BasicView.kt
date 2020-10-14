package xyz.manka.pdffirstpageswaper.views

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.text.Text
import javafx.stage.FileChooser
import xyz.manka.pdffirstpageswaper.services.PdfEditor
import tornadofx.*
import xyz.manka.pdffirstpageswaper.FIRST_PAGE_DEFAULT_PATH
import java.io.File

class BasicView : View() {
    private var pdfEditor: ObjectProperty<PdfEditor> = SimpleObjectProperty()
    private var firstPage: File = File(FIRST_PAGE_DEFAULT_PATH)
    private val pdfFileChooser = arrayOf(FileChooser.ExtensionFilter("pdf only", "*.pdf"))

    override val root = vbox {
        style {
            startMargin = 10.px
            endMargin = 10.px
            padding = box(40.px)
        }
        hbox {
            style { padding = box(20.px) }
            text("Replace firs page of PDF") {
                hboxConstraints {
                    alignment = Pos.CENTER
                }
                style {
                    fontSize = 30.px
                    centerShapeProperty()
                }
            }
        }

        hbox {
            style {
                padding = box(10.px)
            }
            hboxConstraints { alignment = Pos.CENTER }
            vbox {
                hboxConstraints {
                    marginRight = 20.0
                }
                button("choose pdf") {
                    vboxConstraints {
                        alignment = Pos.TOP_CENTER
                    }
                    action {
                        val files: List<File> =
                            chooseFile("Select PDF", pdfFileChooser, null, FileChooserMode.Single, scene.window)
                        if (files.isNotEmpty()) {
                            val file = files[0]
                            pdfEditor.set(PdfEditor(file))
                            (scene.lookup("#pdfFileText") as Text).text = file.name
                        }
                    }
                }
                text("select") {
                    vboxConstraints {
                        alignment = Pos.BOTTOM_CENTER
                    }
                    id = "pdfFileText"
                }
            }
            vbox {
                hboxConstraints {
                    marginRight = 20.0
                }
                button("choose first page") {
                    action {
                        val files: List<File> =
                            chooseFile("Select PDF", pdfFileChooser, null, FileChooserMode.Single, scene.window)
                        if (files.isNotEmpty()) {
                            firstPage = files[0]
                            (scene.lookup("#pdfFirsPageFileText") as Text).text = firstPage.name
                        }
                    }
                }
                text(firstPage.name) {
                    vboxConstraints {
                        alignment = Pos.BOTTOM_CENTER
                    }
                    id = "pdfFirsPageFileText"
                }

            }
        }
        hbox {
            hboxConstraints { alignment = Pos.CENTER }
            button("Swap first page") {
                hboxConstraints {
                    marginTop = 20.0
                    alignment = Pos.CENTER
                }
                disableProperty().bind(pdfEditor.isNull)
                action {
                    val test = pdfEditor.get().swapFirstPage(firstPage)
                    println(test)
                }
            }
        }

    }


}
