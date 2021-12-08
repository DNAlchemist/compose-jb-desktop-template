package sample.entity.tools;

import com.google.common.collect.Multimap;
import sample.Model.subscriber.AbstractType;
import sample.Model.subscriber.NameType;
import sample.Model.subscriber.PhoneType;
import sample.Model.subscriber.Subscriber;
import sample.exeption.ActionException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class Find {
    private final Multimap<NameType, Subscriber> entity;
    ArrayList<Subscriber> rez = new ArrayList<>();

    public Find(Multimap<NameType, Subscriber> entity) {
        this.entity = entity;
    }

    public <T extends AbstractType> Collection<Subscriber> find(T search) throws ActionException {
        if (search.getClass().equals(NameType.class)) {
            String fName = ((NameType) search).getFirstName();
            String sName = ((NameType) search).getSecondName();
            if (fName == null && sName == null) throw new ActionException();
            else if (fName != null && sName != null) return findByFullName((NameType) search);
        }

        Set<NameType> keys = entity.keySet();
        for (NameType key : keys) {
            Collection<Subscriber> values = entity.get(key);
            for (Subscriber v : values) {
                doResult(v, search);
            }
        }
        return rez;
    }

    private Collection<Subscriber> findByFullName(NameType key) {
        return entity.get(key);
    }

    private boolean similarName(String name1, String name2) {
        return name1.equals(name2);
    }


    private <T extends AbstractType> void doResult(Subscriber v, T value) {
        //Get  class name
        if (value.getClass().equals(NameType.class)) {
            addType(v, (NameType) value);

        } else if (value.getClass().equals(PhoneType.class)) {
            addType(v, (PhoneType) value);
        } else throw new RuntimeException("class " + value.getClass() + " was not found");

    }

    private void addType(Subscriber v, NameType search) {
        if (search.getSecondName() == null) {
            if (similarName(v.getName().getFirstName(), search.getFirstName())) rez.add(v);
        } else if (similarName(v.getName().getSecondName(), search.getSecondName())) this.rez.add(v);
    }

    private void addType(Subscriber v, PhoneType search) {
        if (v.getPhone().equals(search)) rez.add(v);
    }
}