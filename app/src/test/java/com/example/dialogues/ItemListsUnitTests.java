package com.example.dialogues;

import com.example.dialogues.app.itemsList.ItemsListPresenter;
import com.example.dialogues.app.itemsList.ItemsListScreen;
import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.network.download.MockSoundDownloader;
import com.example.dialogues.network.download.ServiceSoundDownloader;
import com.example.dialogues.utils.log.UnitTestLogger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

/**
 * Created by vivek on 28/02/18.
 */

public class ItemListsUnitTests {


    private ItemsListPresenter itemsListPresenter;

    @Mock
    private ItemsListScreen screen;

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
    public void test_items_loaded(){
        itemsListPresenter = new ItemsListPresenter(screen, mockItems, new UnitTestLogger(),
                new MockSoundDownloader());

        itemsListPresenter.loadItems();

        Mockito.verify(screen, Mockito.times(1))
                .onItemsLoaded((ArrayList<Item>) Mockito.any());
    }

    @Test
    public void test_items_not_loaded(){
        itemsListPresenter = new ItemsListPresenter(screen, null, new UnitTestLogger(),
                new MockSoundDownloader());

        itemsListPresenter.loadItems();

        Mockito.verify(screen, Mockito.times(1))
                .onItemsError();
    }




}
