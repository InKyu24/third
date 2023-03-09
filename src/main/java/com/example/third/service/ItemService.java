package com.example.third.service;

import com.example.third.entity.Item;
import com.example.third.entity.ItemDto;
import com.example.third.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class ItemService {
  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public long addItem(ItemDto itemDto) {
    Item item = new Item(
        itemDto.getName(),
        itemDto.getPrice(),
        itemDto.getQuantity()
    );
    return itemRepository.save(item).getId();
  }

  public void updateItem(Long id, ItemDto itemDto) {
    itemRepository.update(id, new Item(itemDto.getName(), itemDto.getPrice(), itemDto.getQuantity()));
  }

  public List<Item> allItems() {
    return itemRepository.findAll();
  }

  public Optional<Item> findItemById(long id) {
    return itemRepository.findById(id);
  }
}
