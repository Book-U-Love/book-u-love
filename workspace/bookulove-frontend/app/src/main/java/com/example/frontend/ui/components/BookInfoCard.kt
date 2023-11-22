package com.example.frontend.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.frontend.R
import com.example.frontend.ui.vo.Routes

@ExperimentalMaterial3Api
@Composable
fun BookInfoCard(searchRes: Map<String,String>?, navController:NavController,route:String?){
    val title = when(searchRes!!.getValue("title").length>12){true -> searchRes!!.getValue("title").substring(0,12)+"..."; false-> searchRes!!.getValue("title")}
    val author = when(searchRes!!.getValue("author").length>12){true -> searchRes!!.getValue("author").substring(0,12)+"..."; false-> searchRes!!.getValue("author")}
    ElevatedCard(onClick = {
        if(route.equals("report"))navController.navigate(Routes.BOOKREPORTREGIST)
                           else navController.navigate(Routes.BOOKTRANSACTIONREGIST)},modifier= Modifier
        .padding(top = 15.dp)
        .fillMaxWidth()

        .padding(15.dp)) {
        Image(painter = rememberImagePainter(searchRes.get("cover").toString()),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Color.White))
        Text(title,modifier=Modifier.padding(top=30.dp, start=30.dp, end=30.dp, bottom=15.dp), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Box(modifier= Modifier
            .padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
            .fillMaxWidth()){
            Column(modifier= Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 15.dp)){
                Box(modifier = Modifier.fillMaxWidth().padding(bottom=15.dp)){
                    Text(author, fontSize=18.sp, modifier=Modifier.align(
                        Alignment.BottomEnd))
                }
                Box(modifier=Modifier.fillMaxWidth()){
                    Text(searchRes.getValue("category"), fontSize = 18.sp)
                }
                Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
                    Text(searchRes.getValue("publisher"), fontSize = 16.sp)
                    Text(searchRes.getValue("pubDate"), fontSize = 16.sp)
                }


            }
        }

    }
}
@ExperimentalMaterial3Api
@Composable
fun QuestionCard(){
    ElevatedCard(onClick = { /*TODO*/ },modifier= Modifier
        .padding(top = 15.dp)
        .fillMaxWidth()
        .padding(15.dp)) {
        Image(painter = painterResource(id = R.drawable.baseline_question_mark_24),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Color.White))
        Text("도서정보를 찾을 수 없습니다.",modifier=Modifier.padding(top=30.dp, start=30.dp, end=30.dp, bottom=15.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("ISBN을 다시 확인해주세요.",modifier=Modifier.padding(start=30.dp, end=30.dp, bottom=15.dp), fontSize = 18.sp,color= Color.Black.copy(alpha=0.5f))
    }
}