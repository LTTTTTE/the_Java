import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CookieJarTest {
    @Test
    public void initTest() {
        CookieJar cookieJar = new CookieJar();
        assertThat(cookieJar).isNotNull();
    }

    @Test
    public void addCookieTest() {
        CookieJar cookieJar = new CookieJar();
        assertThat(cookieJar.addCookie()).isTrue();
        assertThat(cookieJar.addCookie()).isTrue();
        assertThat(cookieJar.cookieSize()).isNotEqualTo(0);
    }

    @Test
    public void addCookiesTest() {
        CookieJar cookieJar = new CookieJar();
        assertThat(cookieJar.addCookies(50)).isTrue();
        assertThat(cookieJar.addCookies(50)).isTrue();
        assertThat(cookieJar.addCookies(1)).isFalse();
    }

    @Test
    public void addFullCookiesTest() {
        CookieJar cookieJar = new CookieJar();
        cookieJar.addFullCookies();
        assertThat(cookieJar.cookieSize()).isEqualTo(CookieJar.MAX_COOKIE_SIZE);
    }

    @Test
    public void eatCookieTest() {
        CookieJar cookieJar = new CookieJar();
        cookieJar.addCookie();
        assertThat(cookieJar.eatCookie()).isTrue();
        assertThat(cookieJar.eatCookie()).isFalse();
    }

    @Test
    public void eatCookiesTest() {
        CookieJar cookieJar = new CookieJar();
        cookieJar.addFullCookies();
        assertThat(cookieJar.eatCookies(50)).isTrue();
        assertThat(cookieJar.eatCookies(50)).isTrue();
        assertThat(cookieJar.eatCookies(1)).isFalse();
    }

    @Test
    public void eatAllCookiesTest() {
        CookieJar cookieJar = new CookieJar();
        cookieJar.addFullCookies();
        assertThat(cookieJar.cookieSize()).isNotEqualTo(0);

        cookieJar.eatAllCookies();
        assertThat(cookieJar.cookieSize()).isEqualTo(0);
    }
}