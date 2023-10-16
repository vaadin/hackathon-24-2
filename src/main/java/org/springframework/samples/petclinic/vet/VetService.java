package org.springframework.samples.petclinic.vet;

import dev.hilla.BrowserCallable;
import dev.hilla.crud.ListRepositoryService;

@BrowserCallable
public class VetService extends ListRepositoryService<Vet, Integer, VetRepository> {

}
