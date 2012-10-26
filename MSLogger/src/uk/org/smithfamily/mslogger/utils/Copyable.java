package uk.org.smithfamily.mslogger.utils;

public interface Copyable<T>
{
    T copy();

    T createForCopy();

    void copyTo(T dest);
}