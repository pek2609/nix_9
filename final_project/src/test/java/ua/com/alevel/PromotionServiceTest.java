package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.service.promotion.PromotionService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PromotionServiceTest {

    @Autowired
    private PromotionService promotionService;

    private final static int PROMOTION_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < PROMOTION_SIZE; i++) {
            Promotion promotion = GenerationUtil.generatePromotion("prom" + i, 20 + i);
            promotionService.create(promotion);
        }
        Assertions.assertEquals(PROMOTION_SIZE, promotionService.findAll().size());
    }

    @AfterAll
    void clear() {
        promotionService.findAll().forEach(promotion -> promotionService.delete(promotion.getId()));
    }

    @Order(1)
    @Test
    void shouldBeUpdatePromotion() {
        Promotion promotion = promotionService.findAll().stream().findFirst().get();
        promotion.setPercent(38);
        promotionService.update(promotion);
        promotion = promotionService.findById(promotion.getId());
        Assertions.assertEquals(38, promotion.getPercent());
    }

    @Order(2)
    @Test
    void shouldBeGotExceptionUpdatingPromotionNotExist() {
        Promotion promotion = promotionService.findAll().stream().findFirst().get();
        promotion.setId(0L);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            promotionService.update(promotion);
        });
    }

    @Order(3)
    @Test
    void shouldBeDeletePromotion() {
        Promotion promotion = promotionService.findAll().stream().findFirst().get();
        promotionService.delete(promotion.getId());
        Assertions.assertEquals(PROMOTION_SIZE - 1, promotionService.findAll().size());
    }

    @Order(4)
    @Test
    void shouldBeNotDeleteIfPromotionNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            promotionService.delete(0L);
        });
    }

    @Order(5)
    @Test
    void shouldBeNotFindIfPromotionNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            promotionService.findById(0L);
        });
    }

    @Order(6)
    @Test
    void shouldBeFindAllWithPagination() {
        DataTableRequest dataTableRequest = GenerationUtil.getRequest("name", 1, 3);
        Assertions.assertEquals(3, promotionService.findAll(dataTableRequest).getItems().size());
    }
}
