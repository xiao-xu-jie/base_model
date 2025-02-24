package com.xujie.business.application.es.repository;

import com.xujie.business.application.es.DTO.OrderStatusEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xujie
 * @since 2025/2/24 11:34
 **/

@Repository
public interface OrderStatusRepository extends ElasticsearchRepository<OrderStatusEsDTO, Long> {
    List<OrderStatusEsDTO> findByUser(String phone);
}
