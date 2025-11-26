package com.smartshop.component;

import com.smartshop.dto.order.OrderItemRequestDTO;
import com.smartshop.entity.Product;
import com.smartshop.exeptions.BusinessRuleViolationException;
import com.smartshop.exeptions.ResourceNotFoundException;
import com.smartshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EntityValidator {
    @Autowired
    private ProductRepository productRepository;

    public <T, ID> void ensureAllExist(List<ID> requestedIds,
                                       JpaRepository<T, ID> repository,
                                       String entityName,
                                       Function<T, ID> idExtractor) { // <--- NEW ARGUMENT

        List<ID> distinctRequestedIds = requestedIds.stream().distinct().toList();

        List<T> foundEntities = repository.findAllById(distinctRequestedIds);

        // 3. Extract IDs from the entities we found (Using the function you pass)
        List<ID> foundIds = foundEntities.stream()
                .map(idExtractor)
                .toList();

        // 4. Compare: Which requested IDs are NOT in the found list?
        List<ID> missingIds = distinctRequestedIds.stream()
                .filter(reqId -> !foundIds.contains(reqId))
                .collect(Collectors.toList());

        // 5. Throw error with SPECIFIC missing IDs
        if (!missingIds.isEmpty()) {
            throw new ResourceNotFoundException(
                    "The following " + entityName + " IDs do not exist: " + missingIds
            );
        }
    }
    public void ensureQuantity(List<OrderItemRequestDTO> item){
        item.forEach((o)->{
            Product product = productRepository.findById(o.getProductId()).orElseThrow(()->{
                throw  new ResourceNotFoundException("product with Id " + o.getProductId() + " not found");
            });
            if (product.getStock()-o.getQuantity()<0){
                throw new BusinessRuleViolationException("Quantity of " +product.getName()+"is less than stock");
            }
        });
    }
}