package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.CALL_TYPE_MADE
import es.iessaladillo.pedrojoya.quilloque.data.CALL_TYPE_VIDEO
import es.iessaladillo.pedrojoya.quilloque.data.DatabaseContact
import kotlinx.android.synthetic.main.dial_fragment.*
import kotlinx.android.synthetic.main.main_activity.*

class DialFragment : Fragment() {

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }
    private val viewmodel: DialFragmentViewmodel by viewModels {
        DialFragmentViewmodelFactory(DatabaseContact.getInstance(this.requireContext()).recentsDao, requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dial_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupKeyboard()
    }

    private fun setupKeyboard() {

        viewmodel.currentNumber.observe(this) {
            if(it.isNullOrEmpty()) {
                imgVideo.visibility = View.INVISIBLE
                imgBackspace.visibility = View.INVISIBLE
            } else {
                imgVideo.visibility = View.VISIBLE
                imgBackspace.visibility = View.VISIBLE
            }
        }

        lblOne.setOnClickListener { addNumber(lblOne.text.toString()) }
        lblTwo.setOnClickListener { addNumber(lblTwo.text.toString()) }
        lblThree.setOnClickListener { addNumber(lblThree.text.toString()) }
        lblFour.setOnClickListener { addNumber(lblFour.text.toString()) }
        lblFive.setOnClickListener { addNumber(lblFive.text.toString()) }
        lblSix.setOnClickListener { addNumber(lblSix.text.toString()) }
        lblSeven.setOnClickListener { addNumber(lblSeven.text.toString()) }
        lblEight.setOnClickListener { addNumber(lblEight.text.toString()) }
        lblNine.setOnClickListener { addNumber(lblNine.text.toString()) }
        lblZero.setOnClickListener { addNumber(lblZero.text.toString()) }
        lblAstherisc.setOnClickListener { addNumber(lblAstherisc.text.toString()) }
        lblPound.setOnClickListener { addNumber(lblPound.text.toString()) }

        imgBackspace.setOnClickListener { deleteNumber() }
        imgBackspace.setOnLongClickListener { clearFullNumbers() }
    }

    private fun addNumber(number: String) {
        viewmodel.setCurrentNumber(number)
        viewmodel.currentNumber.observe(this) {
            lblNumber.text = it
        }
    }
    private fun deleteNumber() {
        viewmodel.deleteNumber()
        viewmodel.currentNumber.observe(this) {
            lblNumber.text = it
        }
    }

    private fun clearFullNumbers(): Boolean {
        viewmodel.clearFullNumbers()
        viewmodel.currentNumber.observe(this) {
            lblNumber.text = it
        }
        return true
    }



}