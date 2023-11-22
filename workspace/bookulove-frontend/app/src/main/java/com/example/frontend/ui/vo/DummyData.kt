package com.example.frontend.ui.vo

data class chatList(
    val userName:String,
    val userChat:String,
)
data class bookInfo(
    var bookName:String,
    var bookReporter:String,
    var bookReportDate:String,
    var bookReportContent:String,
)
val chatDataList = mutableListOf<chatList>(
    chatList(
        userName="김싸피",
        userChat="안녕하세요 책 대여 가능할까요?",
    ),
    chatList(
        userName="나",
        userChat="안녕하세요!",
    ),
    chatList(
        userName="나",
        userChat="가능합니다.",
    ),
    chatList(
        userName="김싸피",
        userChat="책 상태는 어떤가요?",
    ),
    chatList(
        userName="나",
        userChat="표지에 살짝 얼룩이 있습니다. 그 외에 특이사항은 없어요!",
    ),
    chatList(
        userName="김싸피",
        userChat="00일 00시 괜찮으신가요?",
    ),
    chatList(
        userName="나",
        userChat="네 괜찮아요~",
    ),
    chatList(
        userName="김싸피",
        userChat="그럼 등록하신 장소에서 00일 00시에 뵙겠습니다!",
    ),
    chatList(
        userName="나",
        userChat="감사합니다~",
    ),

)

val categoryList = mutableListOf<String>(
//    "전체",
    "독후감",
    "판매",
    "대여",
//    "내 주변"
)

var bookList = mutableListOf<bookInfo>(
    bookInfo(bookName="이기적 유전자",bookReporter="나",bookReportDate="2023-10-30",bookReportContent="유전자 독후감"),
    bookInfo(bookName="사피엔스",bookReporter="나",bookReportDate="2022-11-21", bookReportContent="사피엔스 독후감"),
    bookInfo(bookName="클린 코드",bookReporter="나",bookReportDate="2023-01-03", bookReportContent="클린 코드 독후감"),
    bookInfo(bookName="인간관계론",bookReporter="나",bookReportDate="2020-02-05", bookReportContent="인간관계론 독후감"),


)


