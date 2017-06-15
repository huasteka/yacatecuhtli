package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends EntityRepository<PaymentType> {

    PaymentType findByNameLikeIgnoreCase(String name);

}
