package com.xujie.business.application.es.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Xujie
 * @since 2025/2/24 11:24
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "cxx_order_status")
public class OrderStatusEsDTO {
    @Id
    private Long id;
    private String ptname;
    private String school;
    private String name;
    private String user;
    private String kcname;
    private String addtime;
    private String status;
    private String process;
    private String remarks;
}
