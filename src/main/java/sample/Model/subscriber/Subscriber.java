package sample.Model.subscriber;

import lombok.Data;
import sample.exeption.DataException;

import java.math.BigInteger;

@Data
public class Subscriber implements Comparable<Subscriber> {
    // private String uuid;
    private NameType name;
    private PhoneType phone;

    public Subscriber(NameType name, PhoneType phone) {
        this.name = name;
        this.phone = phone;
        //    setUuid();
    }

    public Subscriber(String name, String phone) throws DataException {
        this(new NameType(name, name), new PhoneType(BigInteger.valueOf(Long.parseLong(phone))));
    }

//    private void setUuid(){
//        this.uuid=UUID.randomUUID().toString();
//        Objects.requireNonNull(uuid, "uuid must not be null");
//
//    }

    @Override
    public int compareTo(Subscriber o) {
        System.out.println("__________________");

        if (this.getName().compareTo(o.getName()) > 0) {
            System.out.println(this.getName() + " > " + o.getName());
            return 1;
        } else if (this.getName().compareTo(o.getName()) < 0) {
            System.out.println(this.getName() + " < " + o.getName());
            return -1;
        } else {
            System.out.println(this.getName() + " = " + o.getName());
            return this.phone.compareTo(o.phone);
        }
    }

    public NameType getName() {
        return name;
    }

    public void setName(NameType name) {
        this.name = name;
    }

    public PhoneType getPhone() {
        return phone;
    }

    public void setPhone(PhoneType phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + " " + phone + "\n";
    }
}