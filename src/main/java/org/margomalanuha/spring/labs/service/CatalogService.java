package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.Catalog;

import java.util.List;

public interface CatalogService {

    List<Catalog> getAllCatalogs();
    List<Catalog> getSubdirectoriesById(int id);
    void createCatalog(String title);
    void updateCatalog(Catalog catalog);
    void deleteCatalog(int id);

}