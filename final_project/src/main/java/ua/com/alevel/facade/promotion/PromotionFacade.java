package ua.com.alevel.facade.promotion;

import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.web.dto.promotion.PromotionRequestDto;
import ua.com.alevel.web.dto.promotion.PromotionResponseDto;

import java.util.List;

public interface PromotionFacade extends BaseFacade<PromotionRequestDto, PromotionResponseDto> {

    List<PromotionResponseDto> findAll();

}
