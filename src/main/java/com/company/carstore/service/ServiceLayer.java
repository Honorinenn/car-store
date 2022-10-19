package com.company.carstore.service;

import com.company.carstore.model.Brand;
import com.company.carstore.model.CarType;
import com.company.carstore.model.Design;
import com.company.carstore.model.Year;
import com.company.carstore.repository.BrandRepository;
import com.company.carstore.repository.CarTypeRepository;
import com.company.carstore.repository.DesignRepository;
import com.company.carstore.repository.YearRepository;
import com.company.carstore.viewmodel.BrandViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private BrandRepository brandRepository;
    private CarTypeRepository carTypeRepository;
    private DesignRepository designRepository;
    private YearRepository yearRepository;

    @Autowired
    public ServiceLayer(BrandRepository brandRepository, CarTypeRepository carTypeRepository, DesignRepository designRepository, YearRepository yearRepository) {
        this.brandRepository = brandRepository;
        this.carTypeRepository = carTypeRepository;
        this.designRepository = designRepository;
        this.yearRepository = yearRepository;
    }

    @Transactional
    public BrandViewModel saveBrand(BrandViewModel viewModel) {

        // Persist Album
        Brand b = new Brand();
        b.setTitle(viewModel.getTitle());
        b.setReleaseDate(viewModel.getReleaseDate());
        b.setListPrice(viewModel.getListPrice());
        b.setCarTypeId(viewModel.getCarType().getId());
        b.setYearId(viewModel.getYear().getId());
        b = brandRepository.save(b);
        viewModel.setId(b.getId());

        // Add Brand Id to Design and Persist Designs
        List<Design> designs = viewModel.getDesigns();

        designs.stream()
                .forEach(t ->
                {
                    t.setBrandId(viewModel.getId());
                    designRepository.save(t);
                });

        designs = designRepository.findAllDesignsByBrandId(viewModel.getId());
        viewModel.setDesigns(designs);

        return viewModel;
    }

    public BrandViewModel findBrand(int id) {

        // Get the album object first
        Optional<Brand> brand = brandRepository.findById(id);

        return brand.isPresent() ? buildBrandViewModel(brand.get()) : null;
    }

    private BrandViewModel buildBrandViewModel(Brand brand) {

        // Get the associated carType
        Optional<CarType> carType = carTypeRepository.findById(brand.getCarTypeId());

        // Get the associated year
        Optional<Year> year = yearRepository.findById(brand.getYearId());

        // Get the designs associated with the brand
        List<Design> designList = designRepository.findAllDesignsByBrandId(brand.getId());

        // Assemble the BrandViewModel
        BrandViewModel bvm = new BrandViewModel();
        bvm.setId(brand.getId());
        bvm.setTitle(brand.getTitle());
        bvm.setReleaseDate(brand.getReleaseDate());
        bvm.setListPrice(brand.getListPrice());
        bvm.setCarType(carType.get());
        bvm.setYear(year.get());
        bvm.setDesigns(designList);

        // Return the BrandViewModel
        return bvm;
    }

    public List<BrandViewModel> findAllBrands() {

        List<Brand> brandList = brandRepository.findAll();

        List<BrandViewModel> bvmList = new ArrayList<>();

        for (Brand brand : brandList) {
            BrandViewModel bvm = buildBrandViewModel(brand);
            bvmList.add(bvm);
        }

        return bvmList;
    }



}


//    Album a = new Album();
//        a.setTitle(viewModel.getTitle());
//                a.setReleaseDate(viewModel.getReleaseDate());
//                a.setListPrice(viewModel.getListPrice());
//                a.setLabelId(viewModel.getLabel().getId());
//                a.setArtistId(viewModel.getArtist().getId());
//                a = albumRepository.save(a);
//                viewModel.setId(a.getId());
//
//                // Add Album Id to Tracks and Persist Tracks
//                List<Track> tracks = viewModel.getTracks();
//
//        tracks.stream()
//        .forEach(t ->
//        {
//        t.setAlbumId(viewModel.getId());
//        trackRepository.save(t);
//        });
//
//        tracks = trackRepository.findAllTracksByAlbumId(viewModel.getId());
//        viewModel.setTracks(tracks);
//
//        return viewModel;