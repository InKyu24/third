package com.example.third.repository;

import com.example.third.entity.Item;
import com.example.third.entity.ItemDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryItemRepositoryTest {

  MemoryItemRepository itemRepository = new MemoryItemRepository();

  @Test
  @DisplayName("상품 저장 테스트")
  void save() {
    Item item = new Item("상품1", 2000, 100);
    Item savedItem = itemRepository.save(item);
    Item foundItemById = itemRepository.findById(savedItem.getId()).get();
    Assertions.assertThat(foundItemById).isEqualTo(savedItem);
  }

  @Test
  void findAll() {
    Item item1 = new Item("상품1", 1000, 10);
    Item item2 = new Item("상품2", 2000, 10);
    itemRepository.save(item1);
    itemRepository.save(item2);
    List<Item> all = itemRepository.findAll();
    Assertions.assertThat(all.size()).isEqualTo(2);
    Assertions.assertThat(all).hasSize(2);
    Assertions.assertThat(all.get(0)).isEqualTo(item1);
    Assertions.assertThat(all.get(1)).isEqualTo(item2);
  }

  @Test
  void update() {
    Item savedItem = itemRepository.save(new Item("상품4", 4000, 40));

    Item updatedItem = savedItem;
    updatedItem.setName("변경상품");
    updatedItem.setPrice(3500);
    updatedItem.setQuantity(5);

    itemRepository.update(savedItem.getId(), updatedItem);

    updatedItem = itemRepository.findById(savedItem.getId()).get();

    Assertions.assertThat(updatedItem.getName()).isEqualTo("변경상품");
    Assertions.assertThat(updatedItem.getPrice()).isEqualTo(3500);
    Assertions.assertThat(updatedItem.getQuantity()).isEqualTo(5);
  }
}

