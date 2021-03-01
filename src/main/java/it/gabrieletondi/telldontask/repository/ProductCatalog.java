package it.gabrieletondi.telldontask.repository;

import it.gabrieletondi.telldontask.domain.Product;

public interface ProductCatalog {
    Product getByName(String name);
}
