package sample.entity;

import sample.Model.subscriber.AbstractType;
import sample.Model.subscriber.Subscriber;
import sample.exeption.ActionException;
import sample.exeption.DataException;

import java.util.Collection;

public interface PhoneBook {
    public void add(Subscriber s);

    public void remove(Subscriber s);

    public void clear();

    public void save();

    public void edit(Subscriber s1, Subscriber s2);

    public <T extends AbstractType> Collection<Subscriber> find(T value) throws DataException, ActionException;
}
