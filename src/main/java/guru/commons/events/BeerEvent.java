package guru.commons.events;

import guru.sfg.beer.inventory.service.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerEvent implements Serializable {
    @Serial
    private static final long serialVersionUID = -6482454950084426969L;

    private BeerDto beerDto;
}
