package com.example.viewmodeltutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeltutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "MyNumber"
    }

    // 나중에 값이 설정될 변수
    lateinit var myNumberViewModel: MyNumberViewModel

    // 뷰 바인딩 : MainActivity -> ActivityMainBinding 이와 같은 형태도 자동생성 된다.
    private lateinit var activityMainBinding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 자동으로 완성된 액티비티 메인 바인딩 클래스 인스턴스를 가져옴 (...inflate(layoutInflater))
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        // 뷰 바인딩과 연결
        setContentView(activityMainBinding.root)

        // 뷰모델 프로바이더를 통해 뷰모델 가져오기
        // 라이브사이클을 가지고 있는 녀석을 넣어줌 (자기자신)
        // 우리가 가져오고 싶은 뷰모델 클래스 넣어 뷰모델을 가져오기
        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)
        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            activityMainBinding.numberTextview.text = it.toString()
        })
        // 리스너 연결
        activityMainBinding.minusBtn.setOnClickListener(this)
        activityMainBinding.plusBtn.setOnClickListener(this)
    }

    // 클릭 이벤트
    override fun onClick(view: View?) {
        val userInput: Int = activityMainBinding.userinputEditText.text.toString().toInt()
        // 뷰모델에 라이브데이터 값을 변경하는 메소드 실행
        when (view) {
            activityMainBinding.plusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            activityMainBinding.minusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }
}