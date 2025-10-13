package hotel.service.service;


import hotel.db.entity.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getServicesByHotel(Long hotelId);
    Service getById(Integer id);
    Service save(Service service);
    void deleteById(Integer id);
}