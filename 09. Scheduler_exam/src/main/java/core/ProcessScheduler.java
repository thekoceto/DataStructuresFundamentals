package core;

import model.Task;
import shared.Scheduler;

import java.util.ArrayList;
import java.util.List;

public class ProcessScheduler implements Scheduler {
    private Node first;
    private Node last;
    private int size = 0;

    static class Node {
        private Task task;
        private Node next;

        public Node(Task task) {
            this.task = task;
        }
    }
    static class NodeAndPreviously{
        Node node;
        Node previously;

        public NodeAndPreviously(Node node, Node previously) {
            this.node = node;
            this.previously = previously;
        }
    }

    @Override
    public void add(Task task) {
        Node current = new Node(task);

        if (this.first == null){
            this.first = this.last = current;
        }else{
            this.last.next = current;
            this.last = current;
        }
        this.size++;
    }

    @Override
    public Task process() {
        if (this.first == null)
            return null;

        Task taskToProcess = this.first.task;

        if (this.size == 1)
            this.first = this.last = null;
        else
            this.first = this.first.next;

        this.size--;
        return taskToProcess;
    }

    @Override
    public Task peek() {
        if (this.first == null)
            return null;

        return this.first.task;
    }

    @Override
    public Boolean contains(Task task) {
        if (this.first == null)
            return false;

        return contains(new NodeAndPreviously(this.first,null), task).node != null;
    }

    private NodeAndPreviously contains(NodeAndPreviously current, Task task) {
        if (current.node == null || current.node.task.equals(task))
            return current;

        current.previously = current.node;
        current.node = current.node.next;

        return this.contains(current, task);
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Boolean remove(Task task) {
        NodeAndPreviously current = this.contains(new NodeAndPreviously(this.first,null), task);

        if (current.node == null)
            throw new IllegalArgumentException();

        if (this.size == 1)
            this.first = this.last = null;
        else
            current.previously.next = current.node.next;

        this.size--;

        return true;
    }

    @Override
    public Boolean remove(int id) {
        NodeAndPreviously current = this.contains(new NodeAndPreviously(this.first,null), id);

        if (current.node == null)
            throw new IllegalArgumentException();

        if (this.size == 1)
            this.first = this.last = null;
        else
            current.previously.next = current.node.next;

        this.size--;

        return true;
    }


    private NodeAndPreviously contains(NodeAndPreviously current, int id) {
        if (this.first == null || current.node == null || current.node.task.getId() == id)
            return current;

        current.previously = current.node;
        current.node = current.node.next;

        return this.contains(current, id);
    }

    @Override
    public void insertBefore(int id, Task task) {
        NodeAndPreviously current = this.contains(new NodeAndPreviously(this.first,null), id);

        if (current.node == null)
            throw new IllegalArgumentException();

        Node toInsert = new Node(task);

        if (current.node == this.first) {
            toInsert.next = this.first;
            this.first = toInsert;
        }else {
            current.previously.next = toInsert;
            toInsert.next = current.node;
        }

        this.size++;
    }

    @Override
    public void insertAfter(int id, Task task) {
        NodeAndPreviously current = this.contains(new NodeAndPreviously(this.first,null), id);

        if (current.node == null)
            throw new IllegalArgumentException();

        Node toInsert = new Node(task);

        if (current.node == this.last)
            this.last = toInsert;

        toInsert.next = current.node.next;
        current.node.next = toInsert;

        this.size++;
    }

    @Override
    public void clear() {
        this.first = this.last = null;
        this.size = 0;
    }

    @Override
    public Task[] toArray() {
        Task[] result = new Task[this.size];
        int index = 0;

        for (Node current = this.first; current != this.last; current=current.next)
            result[index++] = current.task;

        return result;
    }

    @Override
    public void reschedule(Task first, Task second) {
        Node firstNode = this.contains(new NodeAndPreviously(this.first,null), first).node;
        Node secondNode = this.contains(new NodeAndPreviously(this.first,null), second).node;

        if (firstNode == null || secondNode == null)
            throw new IllegalArgumentException();

        Task aTask = firstNode.task;
        firstNode.task = secondNode.task;
        firstNode.task = aTask;
    }

    @Override
    public List<Task> toList() {
        List<Task> result = new ArrayList<>();

        for (Node current = this.first; current != null; current = current.next)
            result.add(current.task);

        return result;
    }

    @Override
    public void reverse() {
        if (this.size <= 1)
            return;

        // null <- [ 1 ] [ 2 ] [ 3 ] -> null

        //  Before changing next of current,
        //  store next node
        // next = curr.next
        //   Now change next of current
        //   This is where actual reversing happens
        // curr.next = prev
        //   Move prev and curr one step forward
        // prev = curr
        // curr = next



        this.last = this.first;

        Node prev = null;
        Node current = this.first;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.first = prev;
    }

    @Override
    public Task find(int id) {
        return this.contains(new NodeAndPreviously(this.first,null), id).node.task;
    }

    @Override
    public Task find(Task task) {
        return this.contains(new NodeAndPreviously(this.first,null), task).node.task;
    }
}
