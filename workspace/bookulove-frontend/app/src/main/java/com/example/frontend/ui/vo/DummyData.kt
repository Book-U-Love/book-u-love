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
        userName="asdf",
        userChat="hiasdfasdfasdfasdfadasdfdasfsdsf",
    ),
    chatList(
        userName="나",
        userChat="hi",
    ),
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
    chatList(
        userName="나",
        userChat="hiasdfasdfasdfdasfasdfadsfsffsdfasdfadsfsdfafsdfasdfasdfsdfasdfasdfdsfasdfadsfadsfadsfdsafdsafadsfadsfdasfadsfdasfdasfdsfdsfadsfdsfadsfasdfds",
    ),
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
)

val categoryList = mutableListOf<String>(
    "독후감",
    "판매",
    "대여",
    "내 주변"
)

var bookList = mutableListOf<bookInfo>(
    bookInfo(bookName="이기적 유전자",bookReporter="나",bookReportDate="2023-10-30",bookReportContent="유전자 독후감"),
    bookInfo(bookName="사피엔스",bookReporter="나",bookReportDate="2022-11-21", bookReportContent="사피엔스 독후감"),
    bookInfo(bookName="클린 코드",bookReporter="나",bookReportDate="2023-01-03", bookReportContent="클린 코드 독후감"),
    bookInfo(bookName="인간관계론",bookReporter="나",bookReportDate="2020-02-05", bookReportContent="인간관계론 독후감"),
    bookInfo(bookName="이기적 유전자",bookReporter="나",bookReportDate="2023-10-30",bookReportContent="유전자 독후감"),
    bookInfo(bookName="사피엔스",bookReporter="나",bookReportDate="2022-11-21", bookReportContent="사피엔스 독후감"),
    bookInfo(bookName="클린 코드",bookReporter="나",bookReportDate="2023-01-03", bookReportContent="클린 코드 독후감"),
    bookInfo(bookName="인간관계론",bookReporter="나",bookReportDate="2020-02-05", bookReportContent="인간관계론 독후감"),
    bookInfo(bookName="이기적 유전자",bookReporter="나",bookReportDate="2023-10-30",bookReportContent="유전자 독후감"),
    bookInfo(bookName="사피엔스",bookReporter="나",bookReportDate="2022-11-21", bookReportContent="사피엔스 독후감"),
    bookInfo(bookName="클린 코드",bookReporter="나",bookReportDate="2023-01-03", bookReportContent="클린 코드 독후감"),
    bookInfo(bookName="인간관계론",bookReporter="나",bookReportDate="2020-02-05", bookReportContent="인간관계론 독후감"),
)


