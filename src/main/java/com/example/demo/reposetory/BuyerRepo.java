package com.example.demo.reposetory;

import com.example.demo.model.Buyer;

public interface BuyerRepo {

    Iterable<Buyer> findAll();
    Buyer findById(String Id);
    Buyer save (Buyer buyer);

}
