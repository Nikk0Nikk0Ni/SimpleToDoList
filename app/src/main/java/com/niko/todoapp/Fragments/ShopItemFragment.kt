package com.niko.todoapp.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.niko.todoapp.Activity.MainActivity
import com.niko.todoapp.ViewModels.AddEditVMFactory
import com.niko.todoapp.ViewModels.AddEditViewModel
import com.niko.todoapp.databinding.FragmentShopItemBinding

class ShopItemFragment() : Fragment() {
    private var screenMode: String = UNDEFIND_SCREEN_MODE
    private var shopItemId: Int = UNDEFIND_ID
    private lateinit var binding: FragmentShopItemBinding
    private val viewModel by lazy {
        ViewModelProvider(this,AddEditVMFactory(requireActivity().application)
        )[AddEditViewModel::class.java]
    }
    var onEditingFinishedListener: OnEditingFinishedListenner? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchRightMode()
        observError()
        removeInputName()
        removeErrorAmount()
        closeScreen()
    }

    private fun closeScreen() {
        viewModel.isEnd.observe(viewLifecycleOwner)
        {
            if (requireActivity().javaClass.simpleName == "ShopItemActivity") {
                requireActivity().finish()
            } else
                onEditingFinishedListener?.onEditingFinished()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_ADD -> launchModeAdd()
            MODE_EDIT -> launchModeEdit()
        }
    }

    private fun observError() {
        viewModel.errorInputName.observe(viewLifecycleOwner)
        {
            if (it == true)
                binding.tilName.error = "Incorrect name"
            else{
                binding.tilName.error = null
            }
        }
        viewModel.errorInputAmount.observe(viewLifecycleOwner)
        {
            if (it == true)
                binding.tilAmount.error = "Incorrect amount"
            else {
                binding.tilAmount.error = null
            }
        }
    }

    private fun launchModeEdit() = with(viewModel) {
        getShopItem(shopItemId)
        shopItem.observe(viewLifecycleOwner) {
            binding.inputName.setText(it.name)
            binding.inputAmount.setText(it.count.toString())
        }
        binding.Save.setOnClickListener {
            val name = binding.inputName.text?.toString()
            val count = binding.inputAmount.text?.toString()
            editItem(name, count)

        }
    }

    private fun removeInputName() {
        binding.inputName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun removeErrorAmount() {
        binding.inputAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputAmount()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun launchModeAdd() {
        binding.Save.setOnClickListener {
            val name = binding.inputName.text?.toString()
            val count = binding.inputAmount.text?.toString()
            viewModel.addItem(name, count)
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!(args.containsKey(SCREEN_MODE))) {
            throw RuntimeException("Param is Absent")
        }
        screenMode = args.getString(SCREEN_MODE)!!
        checkMode()
        checkCorrectEditMode()
    }

    private fun checkCorrectEditMode() {
        if (screenMode == MODE_EDIT && !(requireArguments().containsKey(SHOP_ITEM_ID)))
            throw RuntimeException("Param shop item id Is Absent Mode")
        shopItemId = requireArguments().getInt(SHOP_ITEM_ID, UNDEFIND_ID)
    }


    private fun checkMode() {
        if (screenMode != MODE_ADD && screenMode != MODE_EDIT)
            throw RuntimeException("Param Screen Mode Is Absent Mode")

    }

    interface OnEditingFinishedListenner {
        fun onEditingFinished()
    }

    companion object {
        private const val SCREEN_MODE = "EXTRA_MODE"
        private const val SHOP_ITEM_ID = "EXTRA_ID"
        private const val MODE_ADD = "MODE_ADD"
        private const val MODE_EDIT = "MODE_EDIT"
        private const val UNDEFIND_ID = -1
        private const val UNDEFIND_SCREEN_MODE = ""

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(id: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(SHOP_ITEM_ID, id)
                }
            }
        }
    }
}