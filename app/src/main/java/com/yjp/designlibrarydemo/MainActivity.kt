package com.yjp.designlibrarydemo

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private var drawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initInstances()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle!!.onOptionsItemSelected(item))
            return true

        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initInstances() {
        drawerToggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(drawerToggle!!)

        navigation.setNavigationItemSelectedListener {
            val id = it.itemId
            when (id) {
                R.id.navItem1 -> {
                    Toast.makeText(MainActivity@this, "点击条目1", Toast.LENGTH_SHORT).show()
                }
                R.id.navItem2 -> {
                    Toast.makeText(MainActivity@this, "点击条目2", Toast.LENGTH_SHORT).show()
                }
                R.id.navItem3 -> {
                    Toast.makeText(MainActivity@this, "点击条目3", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }

        loginButton.setOnClickListener {
            var isError  = false

            if (usernameEditText.text.isNullOrEmpty()) {
                usernameInputLayout.error = "用户名不能为空"
                isError = true
            } else {
                usernameInputLayout.isErrorEnabled = false
            }

            if (passwordEditText.text.isNullOrEmpty()) {
                passwordInputLayout.error = "密码不能为空"
                isError = true
            } else {
                passwordInputLayout.isErrorEnabled = false
            }

            if (!isError) {
                Toast.makeText(MainActivity@ this, "执行登录", Toast.LENGTH_SHORT).show()
            }
        }

        tabLayout.addTab(tabLayout.newTab().setText("条目1"))
        tabLayout.addTab(tabLayout.newTab().setText("条目2"))
        tabLayout.addTab(tabLayout.newTab().setText("条目3"))

        fab.setOnClickListener {
            Snackbar.make(it, "你好！我是Snackbar！", Snackbar.LENGTH_SHORT)
                    .setAction("操作", {

                    }).show()
        }
    }
}
