package sample.entity;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import sample.Model.subscriber.AbstractType;
import sample.Model.subscriber.NameType;
import sample.Model.subscriber.Subscriber;
import sample.entity.tools.Find;
import sample.entity.tools.PrintMultiMap;
import sample.exeption.ActionException;
import sample.exeption.DataException;
import sample.sqlHelper.Config;

import java.util.Collection;
import java.util.Comparator;

public class MapEntity implements Comparator<Subscriber>, PhoneBook {
    private static final Config INSTANCE = new Config();
    Multimap<NameType, Subscriber> entity = TreeMultimap.create();

    @Override
    public int compare(Subscriber s1, Subscriber s2) {
        return s1.compareTo(s2);
    }

    public void add(Subscriber s) {
        entity.put(s.getName(), s);
    }

    public void remove(Subscriber s) {
        entity.remove(s.getName(), s);
    }

    public void clear() {
        INSTANCE.getStorage().clear();
        entity.clear();
        System.out.println("CLEAR OK");
    }

    public void edit(Subscriber old, Subscriber replacement) {
        remove(old);
        entity.put(replacement.getName(), replacement);
    }

    public void save() {
        INSTANCE.getStorage().save(entity);
        System.out.println("SAVE OK");
        //sql.save(entity);
    }

    @Override
    public <T extends AbstractType> Collection<Subscriber> find(T value) throws DataException, ActionException {
        Find f = new Find(entity);
        return f.find(value);
    }

    @Override
    public String toString() {
        return PrintMultiMap.multiMapToString(entity);
    }
}