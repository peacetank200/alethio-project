package com.alethio.service.catalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 품목 서비스를 동적으로 주입하기 위한 팩토리 클래스
 */
@Component
public class ItemServiceFactory {
    private final Map<ItemType, ItemService> itemServices = new HashMap<>();

    public ItemServiceFactory(List<ItemService> itemServices) {
        if (CollectionUtils.isEmpty(itemServices)) {
            throw new IllegalArgumentException("존재하는 itemServices가 없음");
        }

        for (ItemService itemService : itemServices) {
            this.itemServices.put(itemService.getItemType(), itemService);
        }
    }

    public ItemService getService(ItemType itemType) {
        return itemServices.get(itemType);
    }
}