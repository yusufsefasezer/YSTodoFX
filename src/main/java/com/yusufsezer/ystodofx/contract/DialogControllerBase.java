package com.yusufsezer.ystodofx.contract;

public interface DialogControllerBase<T> {
    public abstract T create();
    public abstract T edit(T entity);
    public abstract void set(T entity);
}
