package com.example.third.repository;

import com.example.third.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaItemRepository implements ItemRepository {

  private final EntityManager em;

  @Autowired
  public JpaItemRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public Item save(Item item) {
    em.persist(item);
    return item;
  }

  @Override
  public Optional<Item> findById(Long id) {
    return Optional.ofNullable(em.find(Item.class, id));
  }

  @Override
  public List<Item> findAll() {
    List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
    return items;
  }

  @Override
  public void update(Long id, Item item) {
    Item oldItem = em.find(Item.class, id);
    oldItem.setName(item.getName());
    oldItem.setPrice(item.getPrice());
    oldItem.setQuantity(item.getQuantity());
    em.merge(oldItem);
  }
}
