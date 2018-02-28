package com.example.dialogues;

import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.app.welcome.WelcomePresenter;
import com.example.dialogues.app.welcome.WelcomeScreen;
import com.example.dialogues.localPersistance.MockLocalStorage;
import com.example.dialogues.network.MockNetworkClient;
import com.example.dialogues.utils.log.UnitTestLogger;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by vivek on 28/02/18.
 */

public class WelcomeUnitTests {

    private WelcomePresenter welcomePresenter;

    @Mock
    private WelcomeScreen welcomeScreen;

    private MockWebServer server;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);

        server = new MockWebServer();
    }

    @Test
    public void test_getItems_success() throws IOException, InterruptedException {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"data\":[{\"itemId\":\"1\",\"desc\":\"Batcave\",\"audio\":\"https://storage.googleapis.com/assignment-perpule/17.mp3\"},{\"itemId\":\"2\",\"desc\":\"Fight Club rules\",\"audio\":\"https://storage.googleapis.com/assignment-perpule/2514.mp3\"},{\"itemId\":\"3\",\"desc\":\"Make an offer\",\"audio\":\"https://storage.googleapis.com/assignment-perpule/47.mp3\"},{\"itemId\":\"4\",\"desc\":\"Batcave again\",\"audio\":\"https://storage.googleapis.com/assignment-perpule/17.mp3\"},{\"itemId\":\"5\",\"desc\":\"Chainsaw\",\"audio\":\"https://storage.googleapis.com/assignment-perpule/3706.mp3\"},{\"itemId\":\"6\",\"desc\":\"Crowd Cheering\",\"audio\":\"https://storage.googleapis.com/assignment-perpule/3705.mp3\"}]}")
        );
        server.start();

        welcomePresenter = new WelcomePresenter(welcomeScreen, new UnitTestLogger(),
                MockNetworkClient.getDialogueService(server.getUrl("/").toString()),
                new MockLocalStorage());

        welcomePresenter.getItems();

        Mockito.verify(welcomeScreen, Mockito.times(1))
                .showLoadingProgress();
        Thread.sleep(1000);
        Mockito.verify(welcomeScreen, Mockito.times(1))
                .gotItems((ArrayList<Item>) Mockito.any());
        Mockito.verify(welcomeScreen, Mockito.times(1))
                .hideLoadingProgress();

        server.shutdown();
    }

    @Test
    public void test_getItems_fail() throws IOException, InterruptedException {
        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody("{}")
        );
        server.start();

        welcomePresenter = new WelcomePresenter(welcomeScreen, new UnitTestLogger(),
                MockNetworkClient.getDialogueService(server.getUrl("/").toString()),
                new MockLocalStorage());

        welcomePresenter.getItems();

        Mockito.verify(welcomeScreen, Mockito.times(1))
                .showLoadingProgress();
        Thread.sleep(1000);
        Mockito.verify(welcomeScreen, Mockito.times(1))
                .showNetworkError();
        Mockito.verify(welcomeScreen, Mockito.times(1))
                .hideLoadingProgress();

        server.shutdown();
    }

}
