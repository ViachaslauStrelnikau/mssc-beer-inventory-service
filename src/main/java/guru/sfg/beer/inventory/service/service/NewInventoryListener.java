package guru.sfg.beer.inventory.service.service;


import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.brewery.model.events.NewInventoryEvent;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static guru.sfg.beer.inventory.service.config.JmsConfig.NEW_INVENTORY_QUEUE;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;
    private final JmsTemplate jmsTemplate;


    @JmsListener(destination = NEW_INVENTORY_QUEUE)
    @Transactional
    public void newInventory(NewInventoryEvent inventoryEvent){
        BeerDto beerDto = inventoryEvent.getBeerDto();
        BeerInventory beerInventory = BeerInventory.builder()
                .beerId(beerDto.getId())
                .upc(beerDto.getUpc())
                .quantityOnHand(beerDto.getQuantityOnHand())
                .build();

        beerInventoryRepository.save(beerInventory);

        log.debug("Saving to inventory:{}",beerInventory);
    }
}
