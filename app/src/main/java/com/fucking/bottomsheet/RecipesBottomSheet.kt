package com.fucking.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fucking.bottomsheet.databinding.RecipesBottomSheetBinding
import com.fucking.bottomsheet.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RecipesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: RecipesBottomSheetBinding
    private lateinit var dialog: BottomSheetDialog
    private val viewModel: MainViewModel by viewModels(ownerProducer = { requireActivity() })


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = LayoutInflater.from(context).inflate(R.layout.recipes_bottom_sheet, null)

        dialog = BottomSheetDialog(requireContext(), R.style.Theme_Widget_BottomSheet)

        dialog.setContentView(view)

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecipesBottomSheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.root.filterTouchesWhenObscured = true

        binding.recyclerview.shimmerItemCount = viewModel.contentCount.value ?: 2
        binding.recyclerview.showShimmer()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val behavior: BottomSheetBehavior<ViewGroup> = dialog.getBottomSheetBehavior()
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.peekHeight = 0
    }

    private fun BottomSheetDialog.getBottomSheetBehavior(): BottomSheetBehavior<ViewGroup> {
        return BottomSheetBehavior.from(getBottomSheet(this))
    }

    private fun getBottomSheet(bottomSheetDialog: BottomSheetDialog): ViewGroup {
        return bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
    }

    companion object {
        fun newInstance() = RecipesBottomSheet().apply {
            arguments = Bundle().apply {}
        }
    }

}