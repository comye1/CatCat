# CatCat
Cat 관련 Api들에서 데이터를 불러와보자

```Jetpack Compose```
```Compose Navigation```
```ViewModel```
```Repository```
```Coil```
```Retrofit2```
```Room```
```Pager```

## Cat Fact
- [cat-fact api](https://cat-fact.herokuapp.com/facts)
- 하루에 한 번 fetch
  - SharedPreference로 fetch 날짜를 저장
  - Room에 저장
  - Repository의 fetchCatFacts 호출 시 오늘 날짜와 fetch 날짜를 비교하여 Room 또는 Api에서 불러옴 
  
<img src = "https://user-images.githubusercontent.com/50735594/148345215-b7179274-4910-44ed-afcb-a55e065d8b96.png" width="300dp"/>


## Random Cat
- [thatcopy.pw/catapi](https://thatcopy.pw/catapi/rest/)
- 어플 첫 실행 및 Refrech 버튼 클릭 시 Api 호출
- Coil 라이브러리 사용
- 로딩 애니메이션
  - Api 호출 시간 동안
  - painter state가 Loading일 동안

<img src = "https://user-images.githubusercontent.com/50735594/148345948-551551e9-83c6-4852-be04-4349dd19b130.png" width="300dp"/>

## Cat Breeds
- [TheCatApi](https://docs.thecatapi.com/)
- LazyList
  - 아이템 클릭 시 CatBreedDetail로 이동
- 검색 
  - filter
  - 검색어가 Empty일 때 scrollState 애니메이션 (index 0으로)

<img src = "https://user-images.githubusercontent.com/50735594/148347209-e5b17894-8a16-4d6b-876d-8d3f0bd7310a.png" width="300dp"/>

## Cat Breed Detail
- 이미지 로드 및 Pager
  -  클릭한 Breed에 해당하는 Cat Image를 최대 10개 불러와서 Pager에 로드
  -  Coil 라이브러리 사용
  -  Box로 이미지 뒤에 로딩 텍스트를 배치해 사용자 경험 향상 
<img src = "https://user-images.githubusercontent.com/50735594/148347593-abd850f2-0799-4156-abea-839e71c09884.png" width="300dp"/>


https://user-images.githubusercontent.com/50735594/148910214-5d82057e-2507-466b-8325-0c264fbdee0b.mp4



https://user-images.githubusercontent.com/50735594/148348778-c7972346-fd08-4e49-b312-1758cfe6c581.mp4


https://user-images.githubusercontent.com/50735594/148348820-1017892f-dc0a-45cd-beaf-8512bdf18e47.mp4
