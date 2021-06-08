# MediaProject
this repository is for MediaProject 2021-1

<마이구미 구독 서비스-2021년 1학기 미디어 프로젝트>
-Home Activty-home fragment,receipt fragment존재
home fragment는 현재 구독중인 서비스들을 볼수 있는 recyclerview 존재,
receipt fragment는 한달에 총 얼마를 내야하는지 볼수 있는 영수증 페이지

-AddActivity:
구독 서비스를 추가 시킬수 있는 부분으로써,SQ Lite DB와 연결시켜서 CRUD기능을 가능하게 해놓았음
또한 Query문을 써서 추가적인 데이터 삽입 및 업데이트 가능하게

-sub_info Activity
이부분은 현재 구독하고 있는 서비스를 더 자세히 볼 수 있으며, homeFragment에서 recyclerview를 클릭했을때 나오는 부분
클릭 부분은 RecyclerAdapter에서 onClick으로 만듬

-Update Activity
Sq Lite DB부분에 update부분을 만든뒤,추가적으로 바꾸고 싶은 부분을 다시 person ID에 맞게 update시켜주는 부분
share하는 사람들 부분은 완벽히 구현 못함 추후에 추가적으로 다시 구현할 예정
