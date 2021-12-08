package sample.sqlHelper;

import com.google.common.collect.Multimap;
import sample.Model.subscriber.NameType;
import sample.Model.subscriber.Subscriber;

public interface Storage {
    public void save(Multimap<NameType, Subscriber> entity);

    public void clear();
}
