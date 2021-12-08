package sample;

import lombok.SneakyThrows;
import sample.Model.subscriber.NameType;
import sample.Model.subscriber.PhoneType;
import sample.Model.subscriber.Subscriber;
import sample.entity.MapEntity;
import sample.exeption.ActionException;
import sample.exeption.DataException;

import java.math.BigInteger;

public class Main2 {
    public static void main(String[] args) {
        getMapEntity();
    }

    @SneakyThrows
    public static MapEntity getMapEntity() {
        MapEntity book = new MapEntity();
        // book.add(new Subscriber(new NameType("Alina", "Mikhaleva"), new PhoneType(BigInteger.valueOf(2705541))));
        book.add(new Subscriber(new NameType("Max", "Dorn"), new PhoneType(BigInteger.valueOf(1907765))));
        book.add(new Subscriber(new NameType("Max", "K"), new PhoneType(BigInteger.valueOf(5555555))));
        //   book.add(new Subscriber(new NameType("Kl", "haha"), new PhoneType(BigInteger.valueOf(8907765))));
        //   book.add(new Subscriber(new NameType("Max", "D"), new PhoneType(BigInteger.valueOf(1111111))));
        book.add(new Subscriber(new NameType("Alena", "Abrasheva"), new PhoneType(BigInteger.valueOf(2222222))));
        book.add(new Subscriber(new NameType("Alena", "Abrasheva"), new PhoneType(BigInteger.valueOf(4444444))));
        book.add(new Subscriber(new NameType("Doch", "My"), new PhoneType(BigInteger.valueOf(2222222))));
        book.add(new Subscriber(new NameType("Elena", "Cat"), new PhoneType(BigInteger.valueOf(6547869))));


        System.out.println(book);
        System.out.println("find " + book.find(new NameType("Max", null)));
        book.save();
        return book;
    }
}
