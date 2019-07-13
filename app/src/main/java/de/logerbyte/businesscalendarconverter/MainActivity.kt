package de.logerbyte.businesscalendarconverter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import de.logerbyte.businesscalendarconverter.readWriteFile.Bc2File
import de.logerbyte.businesscalendarconverter.readWriteFile.Bc2TaskReader

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var editText: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLayout()
    }

    private fun convertTaskToCalendar(filePath: String) {
        val bcJson = Bc2TaskReader().createBc2tJsonFromFile(filePath)
        Bc2File.createIcsFile(bcJson)
    }



    private fun initLayout() {
        button = findViewById(R.id.buConvert)
        editText = findViewById(R.id.main_filePath)

        button.setOnClickListener { convertTaskToCalendar(editText.text.toString()) }
    }
}