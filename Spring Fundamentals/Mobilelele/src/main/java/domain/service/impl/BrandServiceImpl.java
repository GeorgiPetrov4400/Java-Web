package domain.service.impl;

import domain.models.dtos.BrandDTO;
import domain.models.dtos.ModelDTO;
import domain.models.entities.Brand;
import domain.models.entities.Model;
import domain.repository.BrandRepository;
import domain.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream().map(this::mapBrand).collect(Collectors.toList());
    }

    private BrandDTO mapBrand(Brand brand) {
        List<ModelDTO> models = brand.getModels().stream().map(this::mapModel).collect(Collectors.toList());

        return new BrandDTO().setModels(models).setName(brand.getName());
    }

    private ModelDTO mapModel(Model model) {
        return new ModelDTO().setId(model.getId()).setName(model.getName());
    }
}
