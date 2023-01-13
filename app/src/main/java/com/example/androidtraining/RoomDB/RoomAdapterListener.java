package com.example.androidtraining.RoomDB;

public interface RoomAdapterListener {
    void OnUpdate(int id, int position);
    void OnDelete(int id, int position);
}
