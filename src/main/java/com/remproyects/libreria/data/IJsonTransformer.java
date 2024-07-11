package com.remproyects.libreria.data;

public interface IJsonTransformer {
    <T> T fromJson(String json, Class<T> Tclass);
}
