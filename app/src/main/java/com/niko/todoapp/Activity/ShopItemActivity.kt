package com.niko.todoapp.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.niko.todoapp.Fragments.ShopItemFragment
import com.niko.todoapp.R
import com.niko.todoapp.ViewModels.AddEditViewModel
import com.niko.todoapp.databinding.ActivityShopItemBinding

class ShopItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopItemBinding
    private var screenMode = UNDEFIND_SCREEN_MODE
    private var shopItemId = UNDEFIND_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        if (savedInstanceState == null)
            launchRightMode()
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            else -> throw RuntimeException("Unknown screen mode")
        }
        supportFragmentManager.beginTransaction().replace(R.id.ShopItemContainter, fragment)
            .commit()
    }

    private fun parseIntent() {
        checkMode()
        val mode = intent.getStringExtra(EXTRA_MODE)
        setupMode(mode)
        checkCorrectEditMode()
    }

    private fun checkCorrectEditMode() {
        if (screenMode == MODE_EDIT && !intent.hasExtra(EXTRA_SHOP_ITEM_ID))
            throw RuntimeException("Param shop item id Is Absent Mode")
        shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, UNDEFIND_ID)
    }


    private fun setupMode(mode: String?) {
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown Mode")
        }
        screenMode = mode
    }

    private fun checkMode() {
        if (!intent.hasExtra(EXTRA_MODE))
            throw RuntimeException("Param Screen Mode Is Absent Mode")
    }


    companion object {
        private const val EXTRA_MODE = "EXTRA_MODE"
        private const val EXTRA_SHOP_ITEM_ID = "EXTRA_ID"
        private const val MODE_ADD = "MODE_ADD"
        private const val MODE_EDIT = "MODE_EDIT"
        private const val UNDEFIND_ID = -1
        private const val UNDEFIND_SCREEN_MODE = ""

        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEdit(context: Context, itemID: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, itemID)
            return intent
        }
    }
}