package com.example.dialogues;

import com.example.dialogues.app.dialouge.ItemPresenter;
import com.example.dialogues.app.dialouge.ItemScreen;
import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.network.download.MockSoundDownloader;
import com.example.dialogues.utils.log.UnitTestLogger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

/**
 * Created by vivek on 28/02/18.
 */

public class DialougeItemUnitTests {

    private ItemPresenter itemPresenter;

    @Mock
    private ItemScreen screen;

    private ArrayList<Item> mockItems;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockItems = new ArrayList<>();
        mockItems.add(new Item("1", "Item1", ""));
        mockItems.add(new Item("2", "Item2", ""));
        mockItems.add(new Item("3", "Item3", ""));
        mockItems.add(new Item("4", "Item4", ""));
    }

    @Test
    public void test_increment_curr_position(){
        itemPresenter = new ItemPresenter(screen, mockItems, 0, new UnitTestLogger(),
                new MockSoundDownloader());

        itemPresenter.incrementPosition();

        Assert.assertEquals(itemPresenter.getCurrPosition(), 1);
    }

    @Test
    public void test_show_first_item(){
        itemPresenter = new ItemPresenter(screen, mockItems, 0, new UnitTestLogger(),
                new MockSoundDownloader());

        itemPresenter.showCurrItem();

        Mockito.verify(screen, Mockito.times(1))
                .showItem((Item) Mockito.any());
    }

    @Test
    public void test_show_second_item(){
        itemPresenter = new ItemPresenter(screen, mockItems, 1, new UnitTestLogger(),
                new MockSoundDownloader());

        itemPresenter.showCurrItem();

        Mockito.verify(screen, Mockito.times(1))
                .showItem((Item) Mockito.any());
    }

    @Test
    public void test_navigate_to_main(){
        itemPresenter = new ItemPresenter(screen, mockItems, 4, new UnitTestLogger(),
                new MockSoundDownloader());

        itemPresenter.showCurrItem();

        Mockito.verify(screen, Mockito.times(0))
                .showItem((Item) Mockito.any());
        Mockito.verify(screen, Mockito.times(1))
                .goToMain();

    }




}
