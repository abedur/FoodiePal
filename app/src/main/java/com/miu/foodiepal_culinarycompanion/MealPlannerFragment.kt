package com.miu.foodiepal_culinarycompanion

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.foodiepal_culinarycompanion.databinding.DialogMealPlannerBinding
import com.miu.foodiepal_culinarycompanion.databinding.FragmentMealPlannerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MealPlannerFragment : Fragment() {
    lateinit var plannerBinding: FragmentMealPlannerBinding
    lateinit var dialogBinding: DialogMealPlannerBinding
    lateinit var etDay: EditText
    lateinit var etDesc: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        plannerBinding = FragmentMealPlannerBinding.inflate(inflater, container, false)
        dialogBinding = DialogMealPlannerBinding.inflate(inflater, container, false)

        val btnAddPlan: FloatingActionButton = plannerBinding.fabPlanner //rootView.findViewById(R.id.fabBlog)

        etDesc = dialogBinding.etDesc

        dialogBinding.etDay.setOnClickListener {
            showDatePickerDialog()
        }


        btnAddPlan.setOnClickListener {
            showAddPlanDialog()
        }

        return plannerBinding.root
    }

    private fun showDatePickerDialog(){
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                // The user selected a date
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                // Format the selected date to show only the day name
                val dateFormat = SimpleDateFormat("EEEE",Locale.getDefault())
                val dayName = dateFormat.format(selectedDate.time)

                dialogBinding.etDay.setText(dayName)
            }, year,
            month,
            day
            )
        datePickerDialog.datePicker.calendarViewShown = false
        datePickerDialog.show()

    }

    private fun showAddPlanDialog() {

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Plan")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                val dayName = dialogBinding.etDay.text.toString()
                val desc = dialogBinding.etDesc.text.toString()

                if (dayName.isNotEmpty() && desc.isNotEmpty()) {
                    addRowToTable(dayName, desc)
                } else {
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { _, _ ->
                // Handle cancel
            }
            .create()

        alertDialog.show()
    }

    private fun addRowToTable(dayName: String, desc: String) {

       val newRow = TableRow(requireContext())

        val dayTextView = TextView(requireContext())
        val descTextView = TextView(requireContext())

        dayTextView.text = dayName
        descTextView.text = desc

        newRow.addView(dayTextView)
        newRow.addView(descTextView)

        plannerBinding.tblMPlanner.addView(newRow)
    }
}