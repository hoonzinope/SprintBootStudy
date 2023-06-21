package com.example.EmbeddableDemo.service;


import com.example.EmbeddableDemo.domain.Item;
import com.example.EmbeddableDemo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> itemListReturn(List<String> menuNames) {
        List<Item> itemList = new ArrayList<>();
        menuNames.forEach(menuName -> {
            Optional<Item> optionalItem = itemRepository.findByName(menuName);
            optionalItem.ifPresent(itemList::add);
        });
        return itemList;
    }

    public Item itemReturn(Long item_id) {
        return itemRepository.findById(item_id).orElseThrow(null);
    }
}
