package ua.com.alevel.facade.promotion.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.promotion.PromotionFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.service.promotion.PromotionService;
import ua.com.alevel.service.route.RouteService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.dto.datatable.PageAndSizeData;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.datatable.SortData;
import ua.com.alevel.web.dto.promotion.PromotionRequestDto;
import ua.com.alevel.web.dto.promotion.PromotionResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionFacadeImpl implements PromotionFacade {

    private final PromotionService promotionService;
    private final RouteService routeService;

    public PromotionFacadeImpl(PromotionService promotionService, RouteService routeService) {
        this.promotionService = promotionService;
        this.routeService = routeService;
    }

    @Override
    public void create(PromotionRequestDto promotionRequestDto) {
        Promotion promotion = getPromotionFromDto(promotionRequestDto);
        promotionService.create(promotion);
    }

    @Override
    public void update(PromotionRequestDto promotionRequestDto, Long id) {
        Promotion promotion = getPromotionFromDto(promotionRequestDto);
        promotion.setId(id);
        promotionService.update(promotion);
    }

    @Override
    public void delete(Long id) {
        promotionService.delete(id);
    }

    @Override
    public PromotionResponseDto findById(Long id) {
        return new PromotionResponseDto(promotionService.findById(id));
    }

    @Override
    public PageData<PromotionResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Promotion> all = promotionService.findAll(dataTableRequest);
        List<PromotionResponseDto> buses = all.getItems()
                .stream()
                .map(PromotionResponseDto::new)
                .collect(Collectors.toList());


        PageData<PromotionResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(buses, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Deprecated
    @Override
    public List<PromotionResponseDto> findAll() {
        List<PromotionResponseDto> all = new ArrayList<>();
        for (Promotion promotion : promotionService.findAll()) {
            PromotionResponseDto promotionResponseDto = new PromotionResponseDto(promotion);
//            promotionResponseDto.setRoutes(routeService.findAllByTripsGroupsByRoute(promotion.getTrips()));
            all.add(promotionResponseDto);
        }
        return all;
    }

    private Promotion getPromotionFromDto(PromotionRequestDto promotionRequestDto) {
        Promotion promotion = new Promotion();
        promotion.setImage(promotionRequestDto.getImage());
        promotion.setName(promotionRequestDto.getName());
        promotion.setStart(promotionRequestDto.getStart());
        promotion.setEnd(promotionRequestDto.getEnd());
        promotion.setPercent(promotionRequestDto.getPercent());
        return promotion;
    }
}
