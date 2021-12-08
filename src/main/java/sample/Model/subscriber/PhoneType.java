package sample.Model.subscriber;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sample.exeption.DataException;

import java.math.BigInteger;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class PhoneType extends AbstractType implements Comparable<PhoneType> {
    private static final int phoneSize = 7;
    private BigInteger phone;

    public PhoneType(BigInteger phone) throws DataException {
        this.phone = phone;
        if (!isCorrect(String.valueOf(phone))) throw new DataException();
    }

    @Override
    boolean isCorrect(String str) {
        return str.length() == phoneSize;
    }

    @Override
    public int compareTo(PhoneType o) {
        return this.phone.compareTo(o.phone);
    }

    @Override
    public String toString() {
        return phone.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneType phoneType = (PhoneType) o;
        return Objects.equals(phone, phoneType.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }
}