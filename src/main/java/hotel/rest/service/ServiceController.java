package hotel.rest.service;


import hotel.db.entity.Hotel;
import hotel.db.entity.Service;
import hotel.db.repository.hoteldetail.HotelRepository;
import hotel.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/management/hotel/{hotelId}/services")
public class ServiceController {

    private final ServiceService serviceService;
    private final HotelRepository hotelRepository;

    @GetMapping
    public String listServices(@PathVariable Long hotelId, Model model) {
        model.addAttribute("services", serviceService.getServicesByHotel(hotelId));
        model.addAttribute("hotelId", hotelId);
        return "management/hotel_service/list"; //
    }

    // Form thêm dịch vụ
    @GetMapping("/add")
    public String showAddForm(@PathVariable Long hotelId, Model model) {
        model.addAttribute("service", new Service());
        model.addAttribute("hotelId", hotelId);
        return "management/hotel_service/form";
    }

    //  Lưu dịch vụ mới
    @PostMapping("/save")
    public String saveService(@PathVariable Long hotelId, @ModelAttribute("service") Service service) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();
        service.setHotel(hotel);
        serviceService.save(service);
        return "redirect:/management/hotel/" + hotelId + "/services";
    }

    //  Form sửa
    @GetMapping("/edit/{id}")
    public String editService(@PathVariable Long hotelId, @PathVariable Integer id, Model model) {
        model.addAttribute("service", serviceService.getById(id));
        model.addAttribute("hotelId", hotelId);
        return "management/hotel_service/form";
    }

    //  Xóa
    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable Long hotelId, @PathVariable Integer id) {
        serviceService.deleteById(id);
        return "redirect:/management/hotel/" + hotelId + "/services";
    }
}
