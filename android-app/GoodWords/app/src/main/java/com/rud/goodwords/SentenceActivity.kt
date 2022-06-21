package com.rud.goodwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class SentenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentence)


        val sentenceList = mutableListOf<String>()
        sentenceList.add("삶이 있는 한 희망은 있다  -키케로")
        sentenceList.add("산다는것 그것은 치열한 전투이다.  -로망로랑")
        sentenceList.add("하루에 3시간을 걸으면 7년 후에 지구를 한바퀴 돌 수 있다. -사무엘존슨")
        sentenceList.add("언제나 현재에 집중할수 있다면 행복할것이다. -파울로 코엘료")
        sentenceList.add("진정으로 웃으려면 고통을 참아야하며 , 나아가 고통을 즐길 줄 알아야 해 -찰리 채플린")
        sentenceList.add("직업에서 행복을 찾아라. 아니면 행복이 무엇인지 절대 모를 것이다 -엘버트 허버드")
        sentenceList.add("신은 용기있는자를 결코 버리지 않는다 -켄러")
        sentenceList.add("행복의 문이 하나 닫히면 다른 문이 열린다 그러나 우리는 종종 닫힌 문을 멍하니 바라보다가")


    val adapter = ListViewAdpater(sentenceList)
    val listview = findViewById<ListView>(R.id.sentenceListView)

        listview.adapter = adapter

    }



}