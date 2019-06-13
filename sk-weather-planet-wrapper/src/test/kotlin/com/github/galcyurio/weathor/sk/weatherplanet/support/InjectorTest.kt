package com.github.galcyurio.weathor.sk.weatherplanet.support

import org.assertj.core.api.Assertions.assertThat
import org.intellij.lang.annotations.Language
import org.junit.Test

/**
 * @author galcyurio
 */
class InjectorTest {

    /**
     * 아래 테스트 케이스에서 필요한 클래스이다.
     * 함수 내부에 선언하면 JSON 맵핑에 실패하여 바깥으로 추출하였다.
     * */
    private data class Something(val one: Int)

    @Test
    fun `ObjectMapper는 JSON에 있는 필드가 클래스에 필드로 구현되지 않아도 에러가 일어나지 않아야 한다`() {
        // given
        val mapper = Injector.provideObjectMapper()
        @Language("JSON") val json = """
        {
          "one": 1,
          "two": 2
        }
        """.trimIndent()

        // when
        val exception = runCatching {
            mapper.readValue(json, Something::class.java)
        }.exceptionOrNull()

        // then
        assertThat(exception).isNull()
    }
}
