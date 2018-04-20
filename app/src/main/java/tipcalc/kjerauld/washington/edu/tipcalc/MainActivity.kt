package tipcalc.kjerauld.washington.edu.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    var money: Double = 0.0
    var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tipButton: Button = findViewById(R.id.tipbutton)
        val entryEdit: EditText = findViewById(R.id.moneyEditText)

        tipButton.setEnabled(false)

        entryEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                var testerd = 3
                val log_value = entryEdit.getText().toString()
                if (log_value.trim().length > 0) {
                    val log_slice = log_value.indexOf('.')
                    tipButton.setEnabled(true)
                    if (log_slice != -1) {
                        val logger = log_value.substring(0, log_slice)
                        if(log_value.trim().length >= log_slice + testerd) {
                            entryEdit.removeTextChangedListener(this)
                            if(!log_value.contains("$")) {
                                val text_setter = "$" + log_value.substring(0, log_slice + 3)
                                entryEdit.setText(text_setter)
                            } else {
                                val money_index = log_value.indexOf('$')
                                if(money_index == 0) {
                                    val bobla = log_value.substring(0, log_slice + 3)
                                    entryEdit.setText(bobla)
                                } else {
                                    val text_setter = log_value.substring(money_index, log_slice + 3)
                                    entryEdit.setText(text_setter)
                                }

                            }

                            testerd = 4
                            entryEdit.addTextChangedListener(this)
                        }
                    }
                }
            }
        })

        tipButton.setOnClickListener {
            if(entryEdit.text.toString().length > 0 ) {
                val holder = entryEdit.text.toString()
                var holder02 = ""
                if (holder.contains('$')) {
                    holder02 = holder.substring(1, holder.length)
                } else {
                    holder02 = holder.substring(0, holder.length)
                }
                money = holder02.toDouble()
                total = money * 0.15
                total = Math.round((total * 100)).toDouble() / 100.0

                Toast.makeText(this, "Suggegsted tip of $" + total.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
