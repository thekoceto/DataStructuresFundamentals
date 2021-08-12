package core;

import interfaces.Buffer;
import interfaces.Entity;
import model.BaseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Loader implements Buffer {
    private List <Entity> entities;

    public Loader() {
        this.entities = new ArrayList<>();
    }

    @Override
    public void add(Entity entity) {
        this.entities.add(entity);
    }

    @Override
    public Entity extract(int id) {
        Entity toExtract = null;
        for (int i = 0; i <  this.entities.size(); i++) {
            if (this.entities.get(i).getId() == id) {
                toExtract = this.entities.get(i);
                this.entities.remove(i);
                break;
            }
        }
        return toExtract;
    }

    @Override
    public Entity find(Entity entity) {
        return this.entities.stream()
                .filter(current -> current.equals(entity))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean contains(Entity entity) {
        return this.find(entity) != null;
    }

    @Override
    public int entitiesCount() {
        return this.entities.size();
    }

    @Override
    public void replace(Entity oldEntity, Entity newEntity) {
        int index = this.entities.indexOf(oldEntity);

        if (index == -1)
            throw new IllegalStateException("Entity not found");

        this.entities.set(index, newEntity);
    }

    @Override
    public void swap(Entity first, Entity second) {
        int firstIndex  = this.entities.indexOf(first);
        int secondIndex = this.entities.indexOf(second);

        if (firstIndex == -1 || secondIndex == -1)
            throw new IllegalStateException("Entity not found");

        this.entities.set(firstIndex, second);
        this.entities.set(secondIndex, first);
    }

    @Override
    public void clear() {
        this.entities.clear();
    }

    @Override
    public Entity[] toArray() {
        return this.entities.toArray(new Entity[0]);
    }

    @Override
    public List<Entity> retainAllFromTo(BaseEntity.Status lowerBound, BaseEntity.Status upperBound) {

        return this.entities.stream()
                .filter(entity -> entity.getStatus().compareTo(lowerBound) >= 0)
                .filter(entity -> entity.getStatus().compareTo(upperBound) <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> getAll() {
        return new ArrayList<>(this.entities);
    }

    @Override
    public void updateAll(BaseEntity.Status oldStatus, BaseEntity.Status newStatus) {
        for (Entity entity : this.entities) {
            if (entity.getStatus() == oldStatus)
                entity.setStatus(newStatus);
        }
    }

    @Override
    public void removeSold() {
        for (int i = this.entities.size() -1 ; i >= 0; i--) {
            if (this.entities.get(i).getStatus() == BaseEntity.Status.SOLD)
                this.entities.remove(i);
        }
    }

    @Override
    public Iterator<Entity> iterator() {
        return new Iterator<Entity>() {
            int iterIndex = 0;
            @Override
            public boolean hasNext() {
                return iterIndex < entities.size();
            }

            @Override
            public Entity next() {
                return entities.get(iterIndex++);
            }
        };
    }

}
