package hotel.service.service;


import hotel.db.entity.Service;
import hotel.db.repository.service.ServiceRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Override
    public List<Service> getServicesByHotel(Long hotelId) {
        return serviceRepository.findByHotel_Id(hotelId);
    }

    @Override
    public Service getById(Integer id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void deleteById(Integer id) {
        serviceRepository.deleteById(id);
    }
}
