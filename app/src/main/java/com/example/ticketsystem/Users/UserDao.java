package com.example.ticketsystem.Users;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {


    @Insert
    void insert(Model model);

    @Query("SELECT EXISTS(SELECT *FROM Model WHERE uid = :userId )")
    Boolean is_exist(int userId);

    @Query("SELECT *FROM Model")
    List<Model> getalltittle();

    @Query("SELECT EXISTS(SELECT *FROM Model WHERE `Nhi Number` = :nhi )")
    Boolean exist(String nhi);

    @Query("SELECT *FROM Model WHERE `Nhi Number` = :nhi" )
    Model singleitem(String nhi);
    @Update
    void update(Model model);
    //@Delete
    //void delete(User user);



}