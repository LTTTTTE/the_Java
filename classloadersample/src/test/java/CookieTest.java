import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CookieTest {
    @Test
    public void initTest() {
        Cookie cookie = new Cookie();
        assertThat(cookie).isNotNull();
    }

    @Test
    public void isDeliciousTest(){
        Cookie cookie = new Cookie();
        assertThat(cookie.isDelicious()).isTrue();
    }
}
