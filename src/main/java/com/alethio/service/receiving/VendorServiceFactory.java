package com.alethio.service.receiving;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alethio.service.catalog.Vendor;

/**
 * 벤더 서비스를 동적으로 주입하기 위한 팩토리 클래스
 */
@Component
public class VendorServiceFactory {
    private final Map<Vendor, VendorService> vendorServices = new HashMap<>();

    public VendorServiceFactory(List<VendorService> vendorServices) {
        if (CollectionUtils.isEmpty(vendorServices)) {
            throw new IllegalArgumentException("존재하는 vendorServices가 없음");
        }

        for (VendorService vendorService : vendorServices) {
            this.vendorServices.put(vendorService.getVendor(), vendorService);
        }
    }

    public VendorService getService(Vendor vendor) {
        return vendorServices.get(vendor);
    }
}