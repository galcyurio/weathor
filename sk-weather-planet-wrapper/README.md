# sk-weather-planet-wrapper
[![Download](https://api.bintray.com/packages/galcyurio/maven/sk-weather-planet-wrapper/images/download.svg?version=0.1.0) ](https://bintray.com/galcyurio/maven/sk-weather-planet-wrapper/0.1.0/link)

SK텔레콤의 `Weather Planet API`의 wrapper 라이브러리입니다.  
https://developers.sktelecom.com/content/sktApi/view/?svcId=10073

코틀린으로 사용했을 때를 고려하여 만들었으며 자바에서의 편의성은 아직 구현되지 않았고 추후에 예정되어 있습니다.
(https://github.com/galcyurio/weathor/issues/43)

## 시작하기
### Gradle
````groovy
repositories {
  jcenter()
}

dependencies {
  implementation "com.github.galcyurio.weathor:sk-weather-wrapper:$version"
}
````

### 초기화

#### Kotlin
```kotlin
val client = SkWeatherPlanetClient.Builder()
            .apiKey("YOUR_API_KEY")
            .build()
```

### 요청 보내기
SkWeatherPlanetClient 객체를 통해 API 요청 함수를 호출하면 내부적으로 Retrofit 을 통해서 요청하게 됩니다.
그리고 반환 타입으로 [Retrofit](https://square.github.io/retrofit/)의 
[Call](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html) 객체를 반환합니다.

반환된 `Call` 객체를 통해서 2가지 방식으로 요청 작업을 수행할 수 있습니다.
`execute()`를 통해서 동기적으로 또는 `enqueue()`를 통해서 `Callback`을 넘겨 비동기적으로 수행할 수도 있습니다. 

```kotlin
// 현재날씨(분별)
client.currentMinutely(latitude, longitude).execute().body()

// 현재날씨(시간별)
client.currentHourly(latitude, longitude).enqueue(callback)
```

### 구현되어 있는 API 목록
- [x] 현재날씨(분별)
- [x] 현재날씨(시간별)
- [x] 초단기예보
- [x] 단기예보
- [ ] 중기예보
- [ ] 기상특보
- [ ] 태풍정보
- [ ] 낙뢰정보
- [ ] 간편날씨
- [ ] 어제날씨