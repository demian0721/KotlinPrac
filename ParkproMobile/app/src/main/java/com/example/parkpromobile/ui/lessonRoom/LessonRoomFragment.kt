package com.example.parkpromobile.ui.lessonRoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parkpromobile.databinding.FragmentLessonRoomBinding

class LessonRoomFragment : Fragment() {

    private var _binding: FragmentLessonRoomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val lessonRoomViewModel =
            ViewModelProvider(this).get(LessonRoomViewModel::class.java)

        _binding = FragmentLessonRoomBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLessonRoom
        lessonRoomViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}