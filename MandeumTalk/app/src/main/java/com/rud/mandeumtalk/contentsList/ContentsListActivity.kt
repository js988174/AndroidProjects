package com.rud.mandeumtalk.contentsList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.R

class ContentsListActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    lateinit var myRef : DatabaseReference

    val bookmarkIdList = mutableListOf<String>()

    lateinit var rvAdapter : ContentsRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()

        rvAdapter = ContentsRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)

        val database = Firebase.database

        val category = intent.getStringExtra("category")

        if (category == "Education") {

            findViewById<TextView>(R.id.categoryTitle).text = "교육"

            myRef = database.getReference("Education")

//            myRef.push().setValue(
//                ContentModel("Kotlin 이란", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FsfgLP%2FbtqvtO99vUD%2FZO6qbJ3d5pFq1JmHDRPIL1%2Fimg.png", "https://philosopher-chan.tistory.com/22?category=781381")
//            )
//            myRef.push().setValue(
//                ContentModel("Kotlin lateinit and lazy", "https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/lateinit-vs-lazy-banner-be4c56c8a12720f6.png", "https://philosopher-chan.tistory.com/56?category=781381")
//            )
//            myRef.push().setValue(
//                ContentModel("kotlin 물음표(?) 와 느낌표(!!)에 대해서", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbok4uM%2FbtqwdicLj47%2FnX4nm2H4sKhK4wZ9bUftG1%2Fimg.png", "https://philosopher-chan.tistory.com/58?category=781381")
//            )
//            myRef.push().setValue(
//                ContentModel("추상클래스(abstract class)란?", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FLUGg3%2Fbtqw5hcwlwh%2FAPwO5Gkv5VDlWdX9ZQnVKK%2Fimg.png", "https://philosopher-chan.tistory.com/131?category=781381")
//            )
//            myRef.push().setValue(
//                ContentModel("인터페이스(interface)", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcbEPS4%2Fbtqw7Qx54mM%2FyJb19jx2fvheV1q7jRAKF0%2Fimg.png", "https://philosopher-chan.tistory.com/132?category=781381")
//            )
//            myRef.push().setValue(
//                ContentModel("lambda 람다식이란", "https://www.coderefer.com/blog/wp-content/uploads/sites/7/2019/08/lambda-functions-in-kotlin.jpg", "https://philosopher-chan.tistory.com/173?category=781381")
//            )
//            myRef.push().setValue(
//                ContentModel("Android setImage", "https://media.vlpt.us/images/jojo_devstory/post/3ce2d37e-66fe-4288-929b-6b30d2a78264/Jetpack_logo%20(2).png", "https://philosopher-chan.tistory.com/328?category=785198")
//            )
//            myRef.push().setValue(
//                ContentModel("Android BackButton Event", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fce1mgB%2FbtqzrWJuDNz%2FimEtuLPKVwIkGNXssql9ik%2Fimg.jpg", "https://philosopher-chan.tistory.com/316?category=785198")
//            )
//            myRef.push().setValue(
//                ContentModel("startActivity에서 기존 activity제거", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdKiBuI%2FbtqznufIMGi%2FMcPiJMUUEp4RhK7ct5d4A0%2Fimg.png", "https://philosopher-chan.tistory.com/315?category=785198")
//            )
//            myRef.push().setValue(
//                ContentModel("Kotlin 전역변수\n", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdgSNkF%2FbtqzhhVa2nM%2FZbzs6U48GxfG7nlg0II68K%2Fimg.jpg", "https://philosopher-chan.tistory.com/310?category=785198")
//            )
        }
        if (category == "Cooking") {

            findViewById<TextView>(R.id.categoryTitle).text = "요리"

            myRef = database.getReference("Cooking")

//            myRef.push().setValue(
//                ContentModel("밥솥 리코타치즈 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png", "https://philosopher-chan.tistory.com/1235?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("황금노른자장 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png", "https://philosopher-chan.tistory.com/1236?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("사골곰탕 파스타 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png", "https://philosopher-chan.tistory.com/1237?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("아웃백 투움바 파스타 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcOYyBM%2Fbtq67Or43WW%2F17lZ3tKajnNwGPSCLtfnE1%2Fimg.png", "https://philosopher-chan.tistory.com/1238?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("최애 당면 찜닭 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png", "https://philosopher-chan.tistory.com/1239?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("스팸 부대 국수 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F123LP%2Fbtq65qy4hAd%2F6dgpC13wgrdsnHigepoVT1%2Fimg.png", "https://philosopher-chan.tistory.com/1240?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("불닭 팽이버섯 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fl2KC3%2Fbtq64lkUJIN%2FeSwUPyQOddzcj6OAkPKZuk%2Fimg.png","https://philosopher-chan.tistory.com/1241?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("꿀호떡 샌드위치 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png", "https://philosopher-chan.tistory.com/1242?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("굽네치킨 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FlOnja%2Fbtq69Tmp7X4%2FoUvdIEteFbq4Z0ZtgCd4p0%2Fimg.png", "https://philosopher-chan.tistory.com/1243?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("야매 JMT 홈베이킹 황금레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FNNrYR%2Fbtq64wsW5VN%2FqIaAsfmFtcvh4Bketug9m0%2Fimg.png", "https://philosopher-chan.tistory.com/1244?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("자취요리 양념장레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FK917N%2Fbtq64SP5gxj%2FNzsfNAykamW7qv1hdusp1K%2Fimg.png", "https://philosopher-chan.tistory.com/1245?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("디톡스주스 레시피모음", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FeEO4sy%2Fbtq69SgK8L3%2FttCUxYHx9aPNebNwkPcI21%2Fimg.png", "https://philosopher-chan.tistory.com/1246?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("사랑듬뿍담긴 봄소풍도시락", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbdIKDG%2Fbtq64M96JFa%2FKcJiYgKuwKuP3fIyviXm90%2Fimg.png", "https://philosopher-chan.tistory.com/1247?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("참치맛나니 초간단레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png", "https://philosopher-chan.tistory.com/1248?category=941578")
//            )
//            myRef.push().setValue(
//                ContentModel("간장볶음면 마성의레시피", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png", "https://philosopher-chan.tistory.com/1249?category=941578")
//            )
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {
                    itemKeyList.add(dataModel.key.toString())
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                }
                rvAdapter.notifyDataSetChanged()

            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        myRef.addValueEventListener(postListener)

        val rv : RecyclerView = findViewById(R.id.rv)

        rv.adapter = rvAdapter

        if (category == "Education") {
            rv.layoutManager = GridLayoutManager(this, 3)
        }
        if (category == "Cooking") {
            rv.layoutManager = GridLayoutManager(this, 3)
        }

        rvAdapter.itemClick = object : ContentsRVAdapter.ItemClick {

            override fun onClick(view: View, position: Int) {

                val intent = Intent (this@ContentsListActivity, ContentsShowActivity::class.java)
                intent.putExtra("url", items[position].webUrl)
                if (category == "Education") {
                    intent.putExtra("category", "Education")
                }
                if (category == "Cooking") {
                    intent.putExtra("category", "Cooking")
                }
                startActivity(intent)
            }
        }
        getBookmarkData()
    }

    private fun getBookmarkData() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                bookmarkIdList.clear()

                for (dataModel in dataSnapshot.children) {

                    Log.d("dataSnapshot.key", dataSnapshot.children.toString())

                    bookmarkIdList.add(dataModel.key.toString())
                }
                rvAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        val database = Firebase.database
        val bookmarkRef = database.getReference("Bookmark_List")
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid.toString()
        bookmarkRef.child(uid).addValueEventListener(postListener)
    }
}