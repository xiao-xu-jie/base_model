package com.xujie.business.DTO.res.quotation;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 周数据返回DTO
 *
 * @author Xujie
 * @since 2024/10/8 13:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizEggWeekDataResDTO {
  List<String> dateList;
  List<BizEggQuotationDayItemResDTO> salesList;
  List<BizEggQuotationDayItemResDTO> buyList;
}
