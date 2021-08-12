package core;

import model.Order;
import shared.Shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnlineShop implements Shop {
    private List<Order> orders;

    public OnlineShop() {
        this.orders = new ArrayList<>();
    }

    @Override
    public void add(Order order) {
        this.orders.add(order);
    }

    @Override
    public Order get(int index) {
        indexValidation(index);
        return this.orders.get(index);
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= this.size())
            throw new IndexOutOfBoundsException();
    }

    @Override
    public int indexOf(Order order) {
        return this.orders.indexOf(order);
    }

    @Override
    public Boolean contains(Order order) {
        return this.orders.contains(order);
    }

    @Override
    public Boolean remove(Order order) {
        return this.orders.remove(order);
    }

    @Override
    public Boolean remove(int id) {
        return this.orders.remove(id) != null;
    }

    @Override
    public void set(int index, Order order) {
        indexValidation(index);
        this.orders.set(index, order);
    }

    @Override
    public void set(Order oldOrder, Order newOrder) {
        int index = this.orders.indexOf(oldOrder);
        indexValidation(index);
        this.orders.set(index, newOrder);
    }

    @Override
    public void clear() {
        this.orders.clear();
    }

    @Override
    public Order[] toArray() {
        return this.orders.toArray(new Order[0]);
    }

    @Override
    public void swap(Order first, Order second) {
        int index1 = this.orders.indexOf(first);
        indexValidation(index1);
        int index2 = this.orders.indexOf(second);
        indexValidation(index2);

        Collections.swap(this.orders, index1, index2);
    }

    @Override
    public List<Order> toList() {
        return new ArrayList<>(this.orders);
    }

    @Override
    public void reverse() {
        Collections.reverse(this.orders);
    }

    @Override
    public void insert(int index, Order order) {
        indexValidation(index);
        this.orders.add(index, order);
    }

    @Override
    public Boolean isEmpty() {
        return this.orders.isEmpty();
    }

    @Override
    public int size() {
        return this.orders.size();
    }
}
