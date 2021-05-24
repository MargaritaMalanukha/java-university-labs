package org.margomalanuha.spring.labs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.repository.CatalogRepository;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceImplTest {

    private CatalogRepository repository;
    private CatalogServiceImpl catalogService;

    @Before
    public void mock() {
        repository = Mockito.mock(CatalogRepository.class);
        repository.setTest();
        catalogService = new CatalogServiceImpl(repository);
    }

    @Test
    public void getAllCatalogs_whenCatalogsAreInDatabase_returnListOfAllCatalogs() {
        //GIVEN
        List<Catalog> catalogs = new LinkedList<>();
        catalogs.add(new Catalog(1, "milk products", 0));
        catalogs.add(new Catalog(2, "sweet products", 0));
        catalogs.add(new Catalog(3, "cheeses", 1));
        Mockito.doReturn(catalogs).when(repository).getAll();

        //WHEN
        List<Catalog> actual = catalogService.getAllCatalogs();

        //THEN
        Mockito.verify(repository, Mockito.times(1)).getAll();
        Assertions.assertEquals(catalogs, actual);
    }

    @Test
    public void getSubdirectoriesById_whenCatalogsAreInDatabase_returnListOfSubdirectoriesOfCatalog() {
        //GIVEN
        Catalog catalog = new Catalog(1, "milk products", 0);
        List<Catalog> catalogs = new LinkedList<>();
        catalogs.add(catalog);
        catalogs.add(new Catalog(2, "cheeses", 1));
        catalogs.add(new Catalog(3, "butters", 1));
        catalogs.add(new Catalog(4, "fishes", 0));
        Mockito.doReturn(catalogs).when(repository).getAll();

        //WHEN
        List<Catalog> actual = catalogService.getAllCatalogs();

        //THEN
        Mockito.verify(repository, Mockito.times(1)).getAll();
        catalogs.remove(3);
        catalogs.remove(0);
        Assertions.assertEquals(catalogs, actual);
    }

    @Test
    public void createCatalog_whenDataIsCorrect_createNewCatalog() {
        //GIVEN
        String title = "milk products";
        int upperCatalogId = 0;
        Mockito.doReturn(1).when(repository).create(new Catalog(title, upperCatalogId));

        //WHEN
        catalogService.createCatalog(title, upperCatalogId);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).create(new Catalog(title, upperCatalogId));
    }

    @Test
    public void updateCatalog_whenDataIsCorrect_updateCatalog() {
        //GIVEN
        Catalog catalog = new Catalog("milk products", 0);
        Mockito.doReturn(1).when(repository).update(catalog);

        //WHEN
        catalogService.updateCatalog(catalog);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).update(catalog);
    }

    @Test
    public void deleteCatalog_whenCatalogExists_deleteCatalog() {
        //GIVEN
        int catalogId = 100;
        Mockito.doReturn(1).when(repository).delete(catalogId);

        //WHEN
        catalogService.deleteCatalog(catalogId);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).delete(catalogId);
    }

    @Test
    public void createCatalogInCatalog_whenCatalogExists_createNewSubCatalog() {
        //GIVEN
        String title = "cheeses";
        Catalog catalog = new Catalog(1, "milk products", 0);
        Mockito.doReturn(1).when(repository).create(new Catalog(title, catalog.getId()));

        //WHEN
        catalogService.createCatalog(title, catalog.getId());

        //THEN
        Mockito.verify(repository, Mockito.times(1)).create(new Catalog(title, catalog.getId()));
    }


}