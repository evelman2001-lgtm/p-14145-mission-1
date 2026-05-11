package com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WiseSayingControllerTest {
    @@Test
    @DisplayName("등록")
    void t3() {
        final String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.");
    }

}
