package com.example.third.repository;

import com.example.third.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
  public Item save(Item item);
  public Optional<Item> findById(Long id);
  public List<Item> findAll();
  public void update(Long id, Item item);

  public void delete(Long id);

}
