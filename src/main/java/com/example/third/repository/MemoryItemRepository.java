package com.example.third.repository;

import com.example.third.entity.Item;
import com.example.third.entity.ItemDto;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryItemRepository implements ItemRepository {

  private static final Map<Long, Item> itemMap = new HashMap<>();
  private static Long seq = 0L;

  @Override
  public Item save(Item item) {
    item.setId(++seq);
    itemMap.put(item.getId(), item);
    return item;
  }

  @Override
  public Optional<Item> findById(Long id) {
    return Optional.ofNullable(itemMap.get(id));
  }

  @Override
  public List<Item> findAll() {
    return new ArrayList<>(itemMap.values());
  }

  @Override
  public void update(Long id, Item item) {
    findById(id).ifPresent(findItem -> {
      item.setName(findItem.getName());
      item.setPrice(findItem.getPrice());
      item.setQuantity(findItem.getQuantity());
    });
    itemMap.put(id, item);
  }
}
