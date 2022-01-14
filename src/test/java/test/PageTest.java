package test;

import lombok.val;
import org.junit.jupiter.api.*;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.getAnotherAuthInfo;
import static data.DataHelper.getAuthInfo;
import static data.DatabaseHelper.*;

public class PageTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:3366");
    }

    @AfterEach
    void tidyUp() {
        clearCodes();
    }

    @AfterAll
    static void totalTidyUp() {
        clearTables();
    }

    @Test
    void shouldLogIn() {
        val verificationPage = new LoginPage().validLogin(getAuthInfo());
        verificationPage.validVerify(getCode());
    }

    @Test
    @Disabled
    void shouldBeBlocked() {
        LoginPage page = new LoginPage();
        page.login(getAnotherAuthInfo());
        page.login(getAnotherAuthInfo());
        page.blockingLogin(getAnotherAuthInfo());
    }
}

