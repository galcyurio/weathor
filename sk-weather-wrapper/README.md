# sk-weather-wrapper
SK텔레콤의 Weather API 서비스의 wrapper 라이브러리입니다.  
https://developers.sktelecom.com/content/sktApi/view/?svcId=10073

## 미리 해두어야 할 작업들
해당 라이브러리를 쓰기 전에 미리 해두어야 할 작업들이 있습니다.  
먼저 [SK텔레콤의 개발자 페이지](https://developers.sktelecom.com/)에서 회원가입을 해야 합니다.  
다음으로 `Weather` API에 대해 사용신청을 하고 `apiKey`(TDCProjectKey)를 받아두셔야 합니다. (`Weather Planet`이 아니라 `Weather`)  
`apiKey`는 SK텔레콤 개발자 페이지에서 workspace를 만든 뒤에 `Key`페이지에서 `키 생성`을 눌러서 생성할 수 있습니다.  

## 시작하기
### Gradle
````groovy
repositories {
  jcenter()
}

dependencies {
  implementation "com.github.galcyurio.weathor:sk-weather-wrapper:${version}"
}
````

### 초기화
````java
SkWeatherClient client = new SkWeatherClient.Builder()
     .apiKey(apiKey)
     .build();
````

### 요청(Request)
````java
// 동기
retrofit2.Response<SkWeatherStatus> response = client.call(latitude, longitude);

// 비동기
retrofit2.Call<SkWeatherStatus> call = client.callAsync(latitude, longitude, callback);
````
`SkWeatherClient`는 동기, 비동기 2가지 방식으로 요청할 수 있습니다. 반환되는 객체는 `Retrofit`의 `Response`와 `Call` 객체가 반환됩니다.
`SkWeatherClient.callAsync()`메서드에서 3번째 매개변수는 retrofit의 `Callback` 객체를 받습니다.

