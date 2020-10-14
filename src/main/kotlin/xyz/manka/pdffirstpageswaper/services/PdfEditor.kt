package xyz.manka.pdffirstpageswaper.services

import org.apache.pdfbox.pdmodel.PDDocument
import xyz.manka.pdffirstpageswaper.DATE_SUFFIX_PATTERN
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class PdfEditor(private val pdfFile: File) {

    fun swapFirstPage(firstPage: File): String {

        val pdf = PDDocument.load(pdfFile)
        val firstPagePdf = PDDocument.load(firstPage)

        if (pdf != null && firstPagePdf != null) {
            pdf.removePage(0)
            pdf.pages.forEach { p -> firstPagePdf.addPage(p) }
            val newPath = prepareFilePath()
            firstPagePdf.save(newPath)

            return newPath
        }
        throw Error("terrible problem with pdfs either pdf : $pdf is null or firstPagePdf : $firstPagePdf is null")
    }

    private fun prepareFilePath(): String {
        val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_SUFFIX_PATTERN))
        return pdfFile.absolutePath.substring(0, pdfFile.absolutePath.lastIndexOf('.')).plus(currentDate).plus(".pdf")
    }

}