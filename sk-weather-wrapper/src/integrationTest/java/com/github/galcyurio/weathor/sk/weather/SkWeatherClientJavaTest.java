package com.github.galcyurio.weathor.sk.weather;

import com.github.galcyurio.weathor.sk.weather.data.SkWeatherStatus;
import com.github.galcyurio.weathor.commons.WeathorCallbackAdapter;

import org.jetbrains.annotations.NotNull;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.*;

/**
 * @author galcyurio
 */
public class SkWeatherClientJavaTest {

    private static SkWeatherClient client;
    private static final double latitude = 37.5660649;
    private static final double longitude = 126.9826791;

    @BeforeClass
    public static void setUp() throws Exception {
        InputStream inputStream = SkWeatherClientJavaTest.class.getClassLoader().getResourceAsStream("secret.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String apiKey = properties.getProperty("apiKey");

        client = new SkWeatherClient.Builder()
            .apiKey(apiKey)
            .build();
    }

    @Test
    public void _Weather_API_서버에_동기적으로_요청() {
        SkWeatherStatus skWeatherStatus = client.call(latitude, longitude).body();
        assertThat(skWeatherStatus).isNotNull();
        assertThat(skWeatherStatus).hasNoNullFieldsOrProperties();
        System.out.println(skWeatherStatus);
    }

    @Test
    public void _Weather_API_서버에_비동기적으로_요청() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        client.callAsync(latitude, longitude, new WeathorCallbackAdapter<SkWeatherStatus>() {
            @Override
            public void onSuccess(@NotNull Call<SkWeatherStatus> call, @NotNull Response<SkWeatherStatus> response) {
                SkWeatherStatus status = response.body();
                assertThat(status).isNotNull();
                assertThat(status).hasNoNullFieldsOrProperties();
                System.out.println(status);
            }
        });
        lock.await(1L, TimeUnit.SECONDS);
    }
}
